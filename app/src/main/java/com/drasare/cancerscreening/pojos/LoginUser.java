package com.drasare.cancerscreening.pojos;

import com.google.gson.annotations.SerializedName;

public class LoginUser{

    @SerializedName("username")
    public String UserName;

    @SerializedName("password")
    public String Password;

    public LoginUser(String userName, String password) {
        UserName = userName;
        Password = password;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

}
