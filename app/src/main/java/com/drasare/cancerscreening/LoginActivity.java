package com.drasare.cancerscreening;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.drasare.cancerscreening.pojos.Login;
import com.drasare.cancerscreening.pojos.LoginResponse;
import com.drasare.cancerscreening.pojos.LoginUser;
import com.drasare.cancerscreening.retrofit.MyRetrofit;
import com.drasare.cancerscreening.retrofit.Services;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.basgeekball.awesomevalidation.ValidationStyle.COLORATION;
import static com.basgeekball.awesomevalidation.ValidationStyle.UNDERLABEL;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText emailText;
    EditText passwordText;
    AwesomeValidation mAwesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        emailText  = findViewById(R.id.user_name);
        passwordText = findViewById(R.id.input_password);

        mAwesomeValidation = new AwesomeValidation(COLORATION);
        mAwesomeValidation.setColor(Color.YELLOW);

//        String regexUsername = "^[a-zA-Z0-9](_(?!(\\.|_))|\\.(?!(_|\\.))|[a-zA-Z0-9]){6,18}[a-zA-Z0-9]$";
//        mAwesomeValidation.addValidation(LoginActivity.this, R.id.user_name, regexUsername, R.string.username_error);
//
//        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
//        mAwesomeValidation.addValidation(LoginActivity.this, R.id.input_password, regexPassword, R.string.err_password);

        final Button loginBtn = findViewById(R.id.btn_login);
        final TextView signUpLink = findViewById(R.id.link_signup);
        loginBtn.setOnClickListener(this);
        signUpLink.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                login(emailText.getText().toString(), passwordText.getText().toString());
                break;
            case R.id.link_signup:
                signup();
                break;
            default:
                break;
        }
    }

    private void login(String username, String password) {
        /*if (checkValidation()) {
            loginService(username, password);
        }*/
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private void signup() {
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
    }

    public void loginService(String username, String password){
        Services service = MyRetrofit.getRetrofitInstance().create(Services.class);
        Call<LoginResponse> call = service.getLogin(new Login(new LoginUser(username, password)
        ));
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.code() == 200){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                else if(response.code() == 401){
                    Toast.makeText(LoginActivity.this, response.message(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Add validation
    public boolean checkValidation() {
        return  mAwesomeValidation.validate();
    }
}
