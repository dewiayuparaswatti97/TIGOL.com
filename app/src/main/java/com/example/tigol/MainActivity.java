package com.example.tigol;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth frAuth;
    private FloatingActionButton fabMain;
    private Button btnLogoutMain;
    private Button btnYourticket;
    Spinner spinnerTim;
    Spinner spinnerStadion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frAuth = FirebaseAuth.getInstance();
        btnLogoutMain = findViewById(R.id.btn_logoutmain);
        fabMain = findViewById(R.id.fabMain);
        spinnerTim = findViewById(R.id.tim_spinner);
        spinnerStadion = findViewById(R.id.stadion_spinner);


        btnLogoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //firebase signout
                frAuth.signOut();
                Intent intent = new Intent(MainActivity.this, LoginPage.class);
                Toast.makeText(MainActivity.this, "Sign-out Berhasil!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DisplayCard.class);
                intent.putExtra("timId", spinnerTim.getSelectedItemPosition());
                intent.putExtra("stadionId", spinnerStadion.getSelectedItemPosition());
                startActivity(intent);
            }
        });

    }
}
