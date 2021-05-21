package com.drasare.cancerscreening.pojos;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    public String ID;

    @SerializedName("email")
    public String email;

    @SerializedName("username")
    public String UserName;

    @SerializedName("thumb")
    public String Thumb;

    @SerializedName("user_status")
    public String UserStatus;

    @SerializedName("full_name")
    public String FullName;

    @SerializedName("token")
    public String Token;


    public User(String ID, String email, String userName, String thumb, String userStatus, String fullName, String token) {
        this.ID = ID;
        this.email = email;
        UserName = userName;
        Thumb = thumb;
        UserStatus = userStatus;
        FullName = fullName;
        Token = token;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getThumb() {
        return Thumb;
    }

    public void setThumb(String thumb) {
        Thumb = thumb;
    }

    public String getUserStatus() {
        return UserStatus;
    }

    public void setUserStatus(String userStatus) {
        UserStatus = userStatus;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getToken() {
        return Token;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "ID='" + ID + '\'' +
                ", Password='" + email + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Thumb='" + Thumb + '\'' +
                ", UserStatus='" + UserStatus + '\'' +
                ", FullName='" + FullName + '\'' +
                ", Token='" + Token + '\'' +
                '}';
    }

    public void setToken(String token) {
        Token = token;
    }
}
