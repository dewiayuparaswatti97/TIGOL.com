package com.example.tigol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterPage extends AppCompatActivity {

        private EditText regsEmail;
        private EditText regsPassword;
        private EditText regsPasswordConf;
        private Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        regsEmail = findViewById(R.id.edt_emailregist);
        regsPasswordConf = findViewById(R.id.edt_passconfregist);
        regsPassword = findViewById(R.id.edt_passregist);
        btnCreate = findViewById(R.id.btn_createregist);
    }


}
