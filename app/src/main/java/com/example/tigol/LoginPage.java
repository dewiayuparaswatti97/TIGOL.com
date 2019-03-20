package com.example.tigol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {

    private EditText enterEmail;
    private EditText enterPassword;
    private Button btnLogin;
    private TextView tvForgot;
    private TextView tvDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        enterEmail = findViewById(R.id.edt_emaillogin);
        enterPassword = findViewById(R.id.edt_passlogin);
        btnLogin = findViewById(R.id.btn_login);
        tvForgot = findViewById(R.id.tv_forgetpass);
        tvDaftar = findViewById(R.id.tv_daftar);

        tvDaftar.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view){
                Intent intent = new Intent(LoginPage.this, RegisterPage.class);
                startActivity(intent);
            }
        });

        tvForgot.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view){
                Intent intent = new Intent(LoginPage.this, ForgotPassword.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(LoginPage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
