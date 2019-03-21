package com.example.tigol;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.TabStopSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText edtemail;
    private Button btnforget;
    private FirebaseAuth frAuth;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        frAuth = FirebaseAuth.getInstance();

        edtemail = findViewById(R.id.edt_emailforget);
        btnforget = findViewById(R.id.btn_submitforget);
        btnforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //forget password method bla bla

                email = edtemail.getText().toString();
                frAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotPassword.this, "Email reset telah dikirimkan!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(ForgotPassword.this, LoginPage.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(ForgotPassword.this, "Email Tidak Terdaftar", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
