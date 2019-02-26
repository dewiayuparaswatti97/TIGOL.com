package com.example.tigol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {

    private EditText enterEmail;
    private EditText enterUserName;
    private Button btnLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        enterEmail = findViewById(R.id.edt_emailsign);
        enterUserName = findViewById(R.id.edt_passsign);
        btnLogin = findViewById(R.id.btn_signin);


    }
}
