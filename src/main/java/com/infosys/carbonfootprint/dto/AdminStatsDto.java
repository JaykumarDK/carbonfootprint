package com.infosys.carbonfootprint.dto;

public class AdminStatsDto {
    private int totalUsers;
    private int pendingUsers;
    private int approvedUsers;
    private int rejectedUsers;
    private int male;
    private int female;
    public AdminStatsDto() {

    }
    public AdminStatsDto(int totalUsers, int pendingUsers, int approvedUsers, int rejectedUsers, int male, int female) {
        this.totalUsers = totalUsers;
        this.pendingUsers = pendingUsers;
        this.approvedUsers = approvedUsers;
        this.rejectedUsers = rejectedUsers;
        this.male = male;
        this.female = female;
    }

    public int getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(int totalUsers) {
        this.totalUsers = totalUsers;
    }

    public int getPendingUsers() {
        return pendingUsers;
    }

    public void setPendingUsers(int pendingUsers) {
        this.pendingUsers = pendingUsers;
    }

    public int getApprovedUsers() {
        return approvedUsers;
    }

    public void setApprovedUsers(int approvedUsers) {
        this.approvedUsers = approvedUsers;
    }

    public int getRejectedUsers() {
        return rejectedUsers;
    }

    public void setRejectedUsers(int rejectedUsers) {
        this.rejectedUsers = rejectedUsers;
    }

    public int getMale() {
        return male;
    }

    public void setMale(int male) {
        this.male = male;
    }

    public int getFemale() {
        return female;
    }

    public void setFemale(int female) {
        this.female = female;
    }

    @Override
    public String toString() {
        return "Admin_dto{" +
            "totalUsers=" + totalUsers +
            ", pendingUsers=" + pendingUsers +
            ", approvedUsers=" + approvedUsers +
            ", rejectedUsers=" + rejectedUsers +
            ", male=" + male +
            ", female=" + female +
            '}';
    }
}
