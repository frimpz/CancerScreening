package com.drasare.cancerscreening.pojos;

import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("user")
    LoginUser user;

    public LoginUser getUser() {
        return user;
    }

    public Login(LoginUser user) {
        this.user = user;
    }

    public void setUser(LoginUser user) {
        this.user = user;
    }
}

