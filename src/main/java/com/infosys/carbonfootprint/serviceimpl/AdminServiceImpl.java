package com.infosys.carbonfootprint.serviceimpl;
import com.infosys.carbonfootprint.service.EmailService;
import com.infosys.carbonfootprint.dto.PendingUserDto;
import com.infosys.carbonfootprint.dto.UserDetailsToAdminDto;
import com.infosys.carbonfootprint.entity.PersonalDetails;
import com.infosys.carbonfootprint.entity.User;
import com.infosys.carbonfootprint.entity.UserAddress;
import com.infosys.carbonfootprint.entity.UserGovernmentDocument;
import com.infosys.carbonfootprint.repository.PersonalDetailsRepository;
import com.infosys.carbonfootprint.repository.UserAddressRepository;
import com.infosys.carbonfootprint.repository.UserGovernmentDocumentRepository;
import com.infosys.carbonfootprint.repository.UserRepository;
import com.infosys.carbonfootprint.service.AdminService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.ArrayList;
import java.util.Optional;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final PersonalDetailsRepository personalDetailsRepository;
    private final UserGovernmentDocumentRepository governmentDocumentRepository;
    private final UserAddressRepository userAddressRepository;
    private final EmailService emailService;
    private final BCryptPasswordEncoder passwordEncoder =
        new BCryptPasswordEncoder();
    private List<PendingUserDto> convertToDto(List<User> users){

        List<PendingUserDto> list = new ArrayList<>();

        for(User user : users){

            PersonalDetails personal =
                personalDetailsRepository
                    .findByUserUserId(user.getUserId())
                    .orElse(null);


            PendingUserDto dto =
                new PendingUserDto(
                    user.getUserId(),

                    personal != null ? personal.getFirstName() : null,
                    personal != null ? personal.getMiddleName() : null,
                    personal != null ? personal.getLastName() : null,

                    personal != null ? personal.getAge() : null,
                    personal != null ? personal.getGender() : null,

                    user.getEmail(),
                    user.getStatus(),

                    null,
                    null,
                    null
                );


            list.add(dto);
        }

        return list;
    }


    public AdminServiceImpl(
        UserRepository userRepository,
        PersonalDetailsRepository personalDetailsRepository,
        UserGovernmentDocumentRepository governmentDocumentRepository,
        UserAddressRepository userAddressRepository, EmailService emailService) {

        this.userRepository = userRepository;
        this.personalDetailsRepository = personalDetailsRepository;
        this.governmentDocumentRepository = governmentDocumentRepository;
        this.userAddressRepository = userAddressRepository;
        this.emailService = emailService;
    }

    @Override
    public List<PendingUserDto> getPendingUsers() {

        List<User> pendingUsers =
            userRepository.findByStatus("PENDING");

        return pendingUsers.stream()
            .map(user -> {

                PersonalDetails personal =
                    personalDetailsRepository
                        .findByUserUserId(user.getUserId())
                        .orElse(null);

                UserGovernmentDocument document =
                    governmentDocumentRepository
                        .findByUserUserId(user.getUserId())
                        .orElse(null);

                return new PendingUserDto(
                    user.getUserId(),
                    personal != null ? personal.getFirstName() : null,
                    personal != null ? personal.getMiddleName() : null,
                    personal != null ? personal.getLastName() : null,
                    personal != null ? personal.getAge() : null,
                    personal != null ? personal.getGender() : null,
                    user.getEmail(),
                    user.getStatus(),
                    document != null ? document.getGovernmentDocumentId() : null,
                    document != null ? document.getDocumentType() : null,
                    document != null ? document.getDocumentNumber() : null
                );
            })
            .toList();
    }

    @Override
    public UserDetailsToAdminDto getUserDetails(Long id) {

        User user = userRepository
            .findById(id)
            .orElse(null);

        if (user == null) {
            return null;
        }

        PersonalDetails personal =
            personalDetailsRepository
                .findByUserUserId(id)
                .orElse(null);

        UserAddress address =
            userAddressRepository
                .findByUserUserId(id)
                .orElse(null);

        UserGovernmentDocument document =
            governmentDocumentRepository
                .findByUserUserId(id)
                .orElse(null);

        UserDetailsToAdminDto dto =
            new UserDetailsToAdminDto();

        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setStatus(user.getStatus());

        if (personal != null) {

            dto.setFirstName(personal.getFirstName());
            dto.setMiddleName(personal.getMiddleName());
            dto.setLastName(personal.getLastName());

            dto.setAge(personal.getAge());
            dto.setGender(personal.getGender());
            dto.setDateOfBirth(personal.getDateOfBirth());

            dto.setMobileNumber(personal.getMobileNumber());
            dto.setAlternateContactNumber(
                personal.getAlternateContactNumber());
        }

        if (address != null) {

            dto.setHouseNumber(address.getHouseNumber());
            dto.setStreet(address.getStreet());
            dto.setArea(address.getArea());

            dto.setLandmark(address.getLandmark());

            dto.setCity(address.getCity());
            dto.setState(address.getState());
            dto.setCountry(address.getCountry());

            dto.setPinCode(address.getPinCode());
        }

        if (document != null) {

            dto.setGovernmentDocumentId(
                document.getGovernmentDocumentId());

            dto.setDocumentType(
                document.getDocumentType());

            dto.setDocumentNumber(
                document.getDocumentNumber());
        }

        return dto;
    }

    @Override
    public String approveUser(Long id) {
        Optional<User> optionalUser =
            userRepository.findById(id);

        if(optionalUser.isEmpty()) {
            return "User Not Found";
        }

        User user = optionalUser.get();
        String username =
            user.getEmail()
                .split("@")[0]
                +id;
        String temporaryPassword =
            "CarbonFootprint"+id+"123";

        String encryptedPassword = passwordEncoder.encode(temporaryPassword );
        user.setUsername(username);

        user.setPassword(encryptedPassword);

        user.setFirstLogin(true);


        user.setStatus("APPROVED");
        user.setFirstLogin(true);


        userRepository.save(user);
        emailService.sendApprovalEmail(
            user.getEmail(),
            username,
            temporaryPassword
        );

        return "User Approved Successfully"
            + username
            + " Temporary Password: "
            + temporaryPassword;
    }

    @Override
    public String rejectUser(Long id) {
        Optional<User> optionalUser =
            userRepository.findById(id);

        if(optionalUser.isEmpty()) {
            return "User Not Found";
        }

        User user = optionalUser.get();

        user.setStatus("REJECTED");

        userRepository.save(user);

        return "User Rejected";
    }

    @Override
    public List<PendingUserDto> getApprovedUsers() {
        List<User> users =
            userRepository.findByStatus("APPROVED");


        return convertToDto(users);
    }

    @Override
    public List<PendingUserDto> getRejectedUsers() {
        List<User> users =
            userRepository.findByStatus("REJECTED");


        return convertToDto(users);
    }
}
