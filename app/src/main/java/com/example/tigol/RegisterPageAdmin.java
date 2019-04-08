package com.example.tigol;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterPageAdmin extends AppCompatActivity {

    private EditText regsNama;
    private EditText regsEmail;
    private EditText regsPassword;
    private EditText regsPasswordConf;
    private EditText regsNoEmp;
    private Button btnCreate;
    private FirebaseAuth frAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin);

        regsNama = findViewById(R.id.edt_nameregistadmin);
        regsEmail = findViewById(R.id.edt_emailregistadmin);
        regsPasswordConf = findViewById(R.id.edt_passconfregistadmin);
        regsPassword = findViewById(R.id.edt_passregistadmin);
        regsNoEmp = findViewById(R.id.edt_noempadmin);
        btnCreate = findViewById(R.id.btn_createregistadmin);

        //firebase
        frAuth = FirebaseAuth.getInstance();

        btnCreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                doRegister();
            }
        });
    }

    void doRegister() {
        if (!validate())
            return;

        frAuth.createUserWithEmailAndPassword(regsEmail.getText().toString(), regsPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = frAuth.getCurrentUser();
                            UserProfileChangeRequest builderUser = new UserProfileChangeRequest.Builder().setDisplayName(regsNama.getText().toString()).build();
                            user.updateProfile(builderUser);

                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid());
                            ref.child("status").setValue("Admin");
                            ref.child("email").setValue(regsEmail.getText().toString());
                            ref.child("nama").setValue(regsNama.getText().toString());
                            ref.child("no emp").setValue(regsNoEmp.getText().toString());


                            Toast.makeText(RegisterPageAdmin.this, "Register berhasil!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(RegisterPageAdmin.this, LoginPage.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(RegisterPageAdmin.this, "Registerasi gagal : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    //anti empty
    private boolean validate() {
        boolean isValid = true;
        if (regsEmail.getText().toString().equals("")) {
            regsEmail.setError("Email kosong, tolong diisi.");
            isValid = false;
        }
        if (regsNama.getText().toString().equals("")) {
            regsNama.setError("Nama kosong, tolong diisi.");
            isValid = false;
        }
        if (regsNoEmp.getText().toString().equals("")) {
            regsNama.setError("Nomor Employee kosong, tolong diisi.");
            isValid = false;
        }
        if (regsPassword.getText().toString().equals("")) {
            regsPassword.setError("Password kosong, tolong diisi.");
            isValid = false;
        }
        if (regsPasswordConf.getText().toString().equals("")) {
            regsPasswordConf.setError("Password Confirm kosong, tolong diisi.");
            isValid = false;
        }
        if (!regsPassword.getText().toString().equals(regsPasswordConf.getText().toString())) {
            regsPasswordConf.setError("Password dan Confirmation password salah");
            regsPasswordConf.requestFocus();
            isValid = false;
        }

        return isValid;
    }
}
