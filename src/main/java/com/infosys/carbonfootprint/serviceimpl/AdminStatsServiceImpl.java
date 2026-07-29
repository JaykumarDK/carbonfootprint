package com.infosys.carbonfootprint.serviceimpl;

import com.infosys.carbonfootprint.dto.AdminStatsDto;
import com.infosys.carbonfootprint.repository.PersonalDetailsRepository;
import com.infosys.carbonfootprint.repository.UserRepository;
import com.infosys.carbonfootprint.service.AdminStatsService;
import org.springframework.stereotype.Service;



@Service
public class AdminStatsServiceImpl implements AdminStatsService {


    private final UserRepository userRepository;
    private final PersonalDetailsRepository personalDetailsRepository;


    public AdminStatsServiceImpl(
        UserRepository userRepository,
        PersonalDetailsRepository personalDetailsRepository) {

        this.userRepository = userRepository;
        this.personalDetailsRepository = personalDetailsRepository;
    }


    @Override
    public AdminStatsDto getStatistics() {


        int totalUsers =
            (int) userRepository.count();


        int pendingUsers =
            (int) userRepository.countByStatus("PENDING");


        int approvedUsers =
            (int) userRepository.countByStatus("APPROVED");


        int rejectedUsers =
            (int) userRepository.countByStatus("REJECTED");


        int male =
            (int) personalDetailsRepository.countByGender("Male");


        int female =
            (int) personalDetailsRepository.countByGender("Female");


        return new AdminStatsDto(
            totalUsers,
            pendingUsers,
            approvedUsers,
            rejectedUsers,
            male,
            female
        );
    }
}
