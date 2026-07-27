package com.infosys.carbonfootprint.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserRegistrationDTO {

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email address")
    private String email;

    @Valid
    @NotNull(message = "Personal details are required")
    private PersonalDetailsDTO personalDetails;

    @Valid
    @NotNull(message = "Address is required")
    private UserAddressDTO address;

    @Valid
    @NotNull(message = "Government document details are required")
    private GovernmentDocumentDTO governmentDocument;

    public UserRegistrationDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PersonalDetailsDTO getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(PersonalDetailsDTO personalDetails) {
        this.personalDetails = personalDetails;
    }

    public UserAddressDTO getAddress() {
        return address;
    }

    public void setAddress(UserAddressDTO address) {
        this.address = address;
    }

    public GovernmentDocumentDTO getGovernmentDocument() {
        return governmentDocument;
    }

    public void setGovernmentDocument(
        GovernmentDocumentDTO governmentDocument) {
        this.governmentDocument = governmentDocument;
    }
}
