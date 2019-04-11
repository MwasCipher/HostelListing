package com.example.hostel;

public class NewUser {

    public NewUser() {
    }

    private String nUserName, nUserIdNumber, nUserEmail, nUserPhone,
            nStudentRegistration, nStudentCourse, nOwnerHostel, id;
    private String userType;

    public NewUser(String id, String userType,String nUserName, String nUserIdNumber, String nUserEmail, String nUserPhone,
                   String nStudentRegistration, String nStudentCourse ,String nOwnerHostel) {

        this.id = id;
        this.nUserName = nUserName;
        this.nUserIdNumber = nUserIdNumber;
        this.nUserEmail = nUserEmail;
        this.nUserPhone = nUserPhone;
        this.nStudentRegistration = nStudentRegistration;
        this.nStudentCourse = nStudentCourse;
        this.userType = userType;
        this.nOwnerHostel = nOwnerHostel;

    }
//    public NewUser(String id,String nUserName, String nUserIdNumber,
//                   String nUserEmail, String nUserPhone, String nOwnerHostel, String userType) {
//        this.id = id;
//        this.nUserName = nUserName;
//        this.nUserIdNumber = nUserIdNumber;
//        this.nUserEmail = nUserEmail;
//        this.nUserPhone = nUserPhone;
//        this.nOwnerHostel = nOwnerHostel;
//        this.userType = userType;
//
//    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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
