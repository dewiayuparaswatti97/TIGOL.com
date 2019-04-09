package com.example.tigol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainAdmin extends AppCompatActivity {


    private FirebaseAuth frAuth;
    private Button btnLogoutMain;
    private Button btnRegisterMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        frAuth = FirebaseAuth.getInstance();
        btnRegisterMatch = findViewById(R.id.btnRegisterMatch);
        btnLogoutMain = findViewById(R.id.btn_logoutadmin);

        btnLogoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //firebase signout
                frAuth.signOut();
                Intent intent = new Intent(MainAdmin.this, LoginPage.class);
                Toast.makeText(MainAdmin.this, "Sign-out Berhasil!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        btnRegisterMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAdmin.this, UploadMatch.class);
                startActivity(intent);
            }
        });
    }

}
