package com.example.hostel;

public class BookedSuccessful {

    private String bookedStudentName;
    private String bookedStudentId;
    private String bookedHostelName;
    private String bookedHostelAmount;
    private String bookedStudentRegNumber, phoneNumber;
    private String bookedHostelOwnerName;
    private String id, hostelOwnerId;
    private Boolean transactionStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public BookedSuccessful() {

    }

    public BookedSuccessful(String bookedStudentId, String bookedStudentName,
                            String phoneNumber, String bookedHostelName, String hostelOwnerId, String bookedHostelAmount) {
        this.bookedStudentName = bookedStudentName;
        this.bookedStudentId = bookedStudentId;
        this.bookedHostelAmount = bookedHostelAmount;
        this.bookedHostelName = bookedHostelName;
        this.hostelOwnerId = hostelOwnerId;
        this.bookedStudentRegNumber = bookedStudentRegNumber;
        this.phoneNumber = phoneNumber;


    }

    public String getBookedStudentName() {
        return bookedStudentName;
    }

    public void setBookedStudentName(String bookedStudentName) {
        this.bookedStudentName = bookedStudentName;
    }

    public String getBookedStudentId() {
        return bookedStudentId;
    }

    public void setBookedStudentId(String bookedStudentId) {
        this.bookedStudentId = bookedStudentId;
    }

    public String getBookedHostelName() {
        return bookedHostelName;
    }

    public void setBookedHostelName(String bookedHostelName) {
        this.bookedHostelName = bookedHostelName;
    }

    public String getBookedHostelAmount() {
        return bookedHostelAmount;
    }

    public void setBookedHostelAmount(String bookedHostelAmount) {
        this.bookedHostelAmount = bookedHostelAmount;
    }

    public String getBookedStudentRegNumber() {
        return bookedStudentRegNumber;
    }

    public void setBookedStudentRegNumber(String bookedStudentRegNumber) {
        this.bookedStudentRegNumber = bookedStudentRegNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHostelOwnerId() {
        return hostelOwnerId;
    }

    public void setHostelOwnerId(String hostelOwnerId) {
        this.hostelOwnerId = hostelOwnerId;
    }

}
