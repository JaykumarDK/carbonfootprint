package com.infosys.carbonfootprint.dto;

public class PendingUserDto {

    private Long userId;

    private String firstName;
    private String middleName;
    private String lastName;

    private Integer age;
    private String gender;

    private String email;
    private String status;

    private Long governmentDocumentId;
    private String documentType;
    private String documentNumber;

    public PendingUserDto() {
    }

    public PendingUserDto(
        Long userId,
        String firstName,
        String middleName,
        String lastName,
        Integer age,
        String gender,
        String email,
        String status,
        Long governmentDocumentId,
        String documentType,
        String documentNumber) {

        this.userId = userId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.status = status;
        this.governmentDocumentId = governmentDocumentId;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getGovernmentDocumentId() {
        return governmentDocumentId;
    }

    public void setGovernmentDocumentId(Long governmentDocumentId) {
        this.governmentDocumentId = governmentDocumentId;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
}
