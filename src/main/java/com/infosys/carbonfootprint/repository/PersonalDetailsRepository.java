package com.infosys.carbonfootprint.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.carbonfootprint.entity.PersonalDetails;

public interface PersonalDetailsRepository
    extends JpaRepository<PersonalDetails, Long> {

    Optional<PersonalDetails> findByUserUserId(Long userId);

    boolean existsByMobileNumber(String mobileNumber);
    long countByGender(String gender);
}

