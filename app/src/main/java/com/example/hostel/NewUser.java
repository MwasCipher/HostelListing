package com.example.hostel;

public class NewUser {

    public NewUser() {
    }

    private String nUserName, nUserIdNumber, nUserEmail, nUserPhone, nStudentRegistration, nStudentCourse, nOwnerHostel, id;

    public NewUser(String nUserName, String nUserIdNumber, String nUserEmail, String nUserPhone, String nStudentRegistration, String nStudentCourse,String id) {
        this.nUserName = nUserName;
        this.nUserIdNumber = nUserIdNumber;
        this.nUserEmail = nUserEmail;
        this.nUserPhone = nUserPhone;
        this.nStudentRegistration = nStudentRegistration;
        this.nStudentCourse = nStudentCourse;
        this.id = id;
    }

    public NewUser(String nUserName, String nUserEmail, String nUserIdNumber, String nUserPhone, String nOwnerHostel,String id) {
        this.nUserName = nUserName;
        this.nUserIdNumber = nUserIdNumber;
        this.nUserEmail = nUserEmail;
        this.nUserPhone = nUserPhone;
        this.nOwnerHostel = nOwnerHostel;
        this.id = id;
    }

    public String getnUserIdNumber() {
        return nUserIdNumber;
    }

    public void setnUserIdNumber(String nUserIdNumber) {
        this.nUserIdNumber = nUserIdNumber;
    }

    public String getnUserName() {
        return nUserName;
    }

    public void setnUserName(String nUserName) {
        this.nUserName = nUserName;
    }

    public String getnUserEmail() {
        return nUserEmail;
    }

    public void setnUserEmail(String nUserEmail) {
        this.nUserEmail = nUserEmail;
    }

    public String getnUserPhone() {
        return nUserPhone;
    }

    public void setnUserPhone(String nUserPhone) {
        this.nUserPhone = nUserPhone;
    }

    public String getnStudentRegistration() {
        return nStudentRegistration;
    }

    public void setnStudentRegistration(String nStudentRegistration) {
        this.nStudentRegistration = nStudentRegistration;
    }

    public String getnStudentCourse() {
        return nStudentCourse;
    }

    public void setnStudentCourse(String nStudentCourse) {
        this.nStudentCourse = nStudentCourse;
    }

    public String getnOwnerHostel() {
        return nOwnerHostel;
    }

    public void setnOwnerHostel(String nOwnerHostel) {
        this.nOwnerHostel = nOwnerHostel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
