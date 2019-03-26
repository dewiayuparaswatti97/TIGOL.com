package com.example.tigol;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPage extends AppCompatActivity {

    private EditText enterEmail;
    private EditText enterPassword;
    private Button btnLogin;
    private TextView tvForgot;
    private TextView tvDaftar;
    private FirebaseAuth frAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //firebase
        frAuth = FirebaseAuth.getInstance();


        enterEmail = findViewById(R.id.edt_emaillogin);
        enterPassword = findViewById(R.id.edt_passlogin);
        btnLogin = findViewById(R.id.btn_login);
        tvForgot = findViewById(R.id.tv_forgetpass);
        tvDaftar = findViewById(R.id.tv_daftar);

        tvDaftar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, RegisterPage.class);
                startActivity(intent);
            }
        });

        tvForgot.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, ForgotPassword.class);
                startActivity(intent);
            }
        });

    }


    public void login(View view) {

        if (!validate())
            return;
        frAuth.signInWithEmailAndPassword(enterEmail.getText().toString(), enterPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(LoginPage.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginPage.this, "Login Gagal", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    //anti empty
    private boolean validate() {
        boolean isValid = true;
        if (enterEmail.getText().toString().equals("")) {
            enterEmail.setError("Email kosong, tolong diisi.");
            isValid = false;
        }
        if (enterPassword.getText().toString().equals("")) {
            enterPassword.setError("Password kosong, tolong diisi.");
            isValid = false;
        }
        return isValid;
    }
}
