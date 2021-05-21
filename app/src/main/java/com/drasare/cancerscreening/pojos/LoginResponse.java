package com.drasare.cancerscreening.pojos;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("user")
    User user;

    public User getUser() {
        return user;
    }

    public LoginResponse(User user) {
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
