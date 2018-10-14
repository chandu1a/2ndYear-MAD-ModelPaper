package com.example.chand.modelpaper;

public class UserBean {
    public String ID;
    public String userName;
    public String password;
    public String DOB;
    public String Gender;


    public UserBean(){

    }

    public UserBean(String ID, String userName, String password, String DOB, String gender) {
        this.ID = ID;
        this.userName = userName;
        this.password = password;
        this.DOB = DOB;
        Gender = gender;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
}
