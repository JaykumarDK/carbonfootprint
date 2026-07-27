package com.infosys.carbonfootprint.serviceimpl;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.carbonfootprint.dto.GovernmentDocumentDTO;
import com.infosys.carbonfootprint.dto.PersonalDetailsDTO;
import com.infosys.carbonfootprint.dto.UserAddressDTO;
import com.infosys.carbonfootprint.dto.UserRegistrationDTO;
import com.infosys.carbonfootprint.entity.PersonalDetails;
import com.infosys.carbonfootprint.entity.User;
import com.infosys.carbonfootprint.entity.UserAddress;
import com.infosys.carbonfootprint.entity.UserGovernmentDocument;
import com.infosys.carbonfootprint.repository.PersonalDetailsRepository;
import com.infosys.carbonfootprint.repository.UserGovernmentDocumentRepository;
import com.infosys.carbonfootprint.repository.UserRepository;
import com.infosys.carbonfootprint.service.EmailService;
import com.infosys.carbonfootprint.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PersonalDetailsRepository personalDetailsRepository;
    private final UserGovernmentDocumentRepository governmentDocumentRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public UserServiceImpl(
        UserRepository userRepository,
        PersonalDetailsRepository personalDetailsRepository,
        UserGovernmentDocumentRepository governmentDocumentRepository,
        PasswordEncoder passwordEncoder,
        EmailService emailService) {

        this.userRepository = userRepository;
        this.personalDetailsRepository = personalDetailsRepository;
        this.governmentDocumentRepository = governmentDocumentRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @Override
    @Transactional
    public String registerUser(UserRegistrationDTO dto) throws IOException {

        PersonalDetailsDTO personalDetailsDTO = dto.getPersonalDetails();
        GovernmentDocumentDTO documentDTO = dto.getGovernmentDocument();

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException(
                "Email is already registered"
            );
        }

        if (personalDetailsRepository.existsByMobileNumber(
            personalDetailsDTO.getMobileNumber())) {

            throw new IllegalArgumentException(
                "Mobile number is already registered"
            );
        }

        if (governmentDocumentRepository.existsByDocumentNumber(
            documentDTO.getDocumentNumber())) {

            throw new IllegalArgumentException(
                "Government document number is already registered"
            );
        }

        User user = new User();

        user.setEmail(dto.getEmail());
        user.setRole("USER");
        user.setStatus("PENDING");
        user.setFirstLogin(true);

        PersonalDetails personalDetails =
            createPersonalDetails(
                personalDetailsDTO,
                user
            );

        UserAddress userAddress =
            createUserAddress(
                dto.getAddress(),
                user
            );

        UserGovernmentDocument governmentDocument =
            createGovernmentDocument(
                documentDTO,
                user
            );

        user.setPersonalDetails(personalDetails);
        user.setUserAddress(userAddress);
        user.setGovernmentDocument(governmentDocument);

        userRepository.save(user);

        return "Registration submitted successfully. Waiting for admin approval.";
    }

    private PersonalDetails createPersonalDetails(
        PersonalDetailsDTO dto,
        User user) {

        PersonalDetails personalDetails =
            new PersonalDetails();

        personalDetails.setFirstName(dto.getFirstName());
        personalDetails.setMiddleName(dto.getMiddleName());
        personalDetails.setLastName(dto.getLastName());
        personalDetails.setAge(dto.getAge());
        personalDetails.setGender(dto.getGender());
        personalDetails.setDateOfBirth(dto.getDateOfBirth());
        personalDetails.setMobileNumber(dto.getMobileNumber());
        personalDetails.setAlternateContactNumber(
            dto.getAlternateContactNumber()
        );
        personalDetails.setUser(user);

        return personalDetails;
    }

    private UserAddress createUserAddress(
        UserAddressDTO dto,
        User user) {

        UserAddress userAddress = new UserAddress();

        userAddress.setHouseNumber(dto.getHouseNumber());
        userAddress.setStreet(dto.getStreet());
        userAddress.setArea(dto.getArea());
        userAddress.setLandmark(dto.getLandmark());
        userAddress.setCity(dto.getCity());
        userAddress.setState(dto.getState());
        userAddress.setCountry(dto.getCountry());
        userAddress.setPinCode(dto.getPinCode());
        userAddress.setUser(user);

        return userAddress;
    }

    private UserGovernmentDocument createGovernmentDocument(
        GovernmentDocumentDTO dto,
        User user) throws IOException {

        UserGovernmentDocument governmentDocument =
            new UserGovernmentDocument();

        governmentDocument.setDocumentType(
            dto.getDocumentType()
        );

        governmentDocument.setDocumentNumber(
            dto.getDocumentNumber()
        );

        governmentDocument.setDocumentFile(
            dto.getDocumentFile().getBytes()
        );

        governmentDocument.setUser(user);

        return governmentDocument;
    }

    @Override
    public List<User> getPendingUsers() {

        return userRepository.findByStatus("PENDING");
    }

    @Override
    @Transactional
    public String approveUser(Long userId) {

        User user = userRepository.findById(userId)
            .orElseThrow(() ->
                new IllegalArgumentException(
                    "User not found"
                )
            );

        if (!"PENDING".equals(user.getStatus())) {
            throw new IllegalArgumentException(
                "Only pending users can be approved"
            );
        }

        String username = generateUsername(user);
        String temporaryPassword =
            generateTemporaryPassword();

        String encodedPassword =
            passwordEncoder.encode(
                temporaryPassword
            );

        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setStatus("APPROVED");
        user.setFirstLogin(true);

        userRepository.save(user);

        emailService.sendAccountCredentials(
            user.getEmail(),
            username,
            temporaryPassword
        );

        return "User approved successfully";
    }

    @Override
    @Transactional
    public String rejectUser(Long userId) {

        User user = userRepository.findById(userId)
            .orElseThrow(() ->
                new IllegalArgumentException(
                    "User not found"
                )
            );

        if (!"PENDING".equals(user.getStatus())) {
            throw new IllegalArgumentException(
                "Only pending users can be rejected"
            );
        }

        user.setStatus("REJECTED");

        userRepository.save(user);

        return "User rejected successfully";
    }

    private String generateUsername(User user) {

        String emailPrefix =
            user.getEmail()
                .substring(
                    0,
                    user.getEmail().indexOf("@")
                );

        return emailPrefix + user.getUserId();
    }

    private String generateTemporaryPassword() {

        return UUID.randomUUID()
            .toString()
            .replace("-", "")
            .substring(0, 10);
    }
}
