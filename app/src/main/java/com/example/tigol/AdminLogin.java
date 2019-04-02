package com.example.tigol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AdminLogin extends AppCompatActivity {

    private EditText enterEmail;
    private EditText enterUserName;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        enterEmail = findViewById(R.id.edit_emailsign);
        enterUserName = findViewById(R.id.edit_passsign);
        btnLogin = findViewById(R.id.btn_signin);

    }
}
