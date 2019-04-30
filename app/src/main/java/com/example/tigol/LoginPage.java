package com.example.tigol;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private Button btnLogin;
    private TextView tvForgot;
    private TextView tvDaftar;
    private TextView tvAdmin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //firebase
        mAuth = FirebaseAuth.getInstance();


        email = findViewById(R.id.edt_emaillogin);
        pass = findViewById(R.id.edt_passlogin);
        btnLogin = findViewById(R.id.btn_login);
        tvForgot = findViewById(R.id.tv_forgetpass);
        tvDaftar = findViewById(R.id.tv_daftar);
        tvAdmin = findViewById(R.id.tv_loginadmin);

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

        tvAdmin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, RegisterPageAdmin.class);
                startActivity(intent);
            }
        });

    }


    public void login(View view) {

        if (!validate())
            return;
        mAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getCurrentUser().getUid());
                            ref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Intent intent;

                                    SharedPreferences sharedpreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                    editor.putString("nama",dataSnapshot.child("nama").getValue(String.class));
                                    editor.commit();

                                    if (dataSnapshot.child("status").getValue().equals("Admin")){
                                        intent = new Intent(LoginPage.this, MainAdmin.class);
                                    }
                                    else {
                                        intent = new Intent(LoginPage.this, MainActivity.class);
                                    }
                                    startActivity(intent);
                                    finish();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        } else {
                            Toast.makeText(LoginPage.this, "Login Gagal", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    //anti empty
    private boolean validate() {
        boolean isValid = true;
        if (email.getText().toString().equals("")) {
            email.setError("email kosong, tolong diisi.");
            isValid = false;
        }
        if (pass.getText().toString().equals("")) {
            pass.setError("Password kosong, tolong diisi.");
            isValid = false;
        }
        return isValid;
    }
}
