package com.drasare.cancerscreening.retrofit;

import com.drasare.cancerscreening.pojos.Login;
import com.drasare.cancerscreening.pojos.LoginResponse;
import com.drasare.cancerscreening.pojos.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Services {
        @POST("users/login")
        Call<LoginResponse>  getLogin(@Body Login login);

        @GET("users/{user}")
        Call<User>  getUser(@Path("user") int id);
}
