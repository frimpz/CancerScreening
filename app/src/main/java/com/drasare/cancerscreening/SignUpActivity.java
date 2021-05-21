package com.drasare.cancerscreening;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.drasare.cancerscreening.pojos.User;
import com.drasare.cancerscreening.retrofit.MyRetrofit;
import com.drasare.cancerscreening.retrofit.Services;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sign_up);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        final EditText nameText = findViewById(R.id.input_name);
        final EditText emailText = findViewById(R.id.input_email);
        final EditText passwordText = findViewById(R.id.input_password);
        final Button signUpBtn = findViewById(R.id.btn_signup);
        final TextView loginLink = findViewById(R.id.link_login);
        signUpBtn.setOnClickListener(this);
        loginLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_signup:
                finish();
                break;
            case R.id.link_login:
                signup();
                break;
            default:
                break;
        }
    }

    private void signup() {
        finish();
    }

    public void signUpService(){
        Services service = MyRetrofit.getRetrofitInstance().create(Services.class);
        Call<User> call = service.getUser(1);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(SignUpActivity.this, response.body().getFullName(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("retrofiterror",t.getLocalizedMessage());
                Toast.makeText(SignUpActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
