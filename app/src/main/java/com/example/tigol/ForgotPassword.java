package com.example.tigol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ForgotPassword extends AppCompatActivity {

    private EditText edtemail;
    private Button btnforget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        edtemail = findViewById(R.id.edt_emailforget);
        btnforget = findViewById(R.id.btn_submitforget);

        btnforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPassword.this, LoginPage.class);
                startActivity(intent);
            }
        });
    }
}
