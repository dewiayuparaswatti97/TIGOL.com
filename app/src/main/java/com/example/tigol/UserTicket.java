package com.example.tigol;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class UserTicket extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Card> daftar;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    ProgressBar progressBarMain;
    int REQUEST_MENU = 404;
    DatabaseReference orderMatch = FirebaseDatabase.getInstance().getReference("orderMatch");
    DatabaseReference match = FirebaseDatabase.getInstance().getReference("Match");
    private ArrayList<MyMatch> mWorkerData;
    private UserMatchAdapter mAdapter;
//    int timId;
//    int stadionId;

    int homeTeam;
    int awayTeam;
    int stadium;

    int hargaOutdoor;
    int hargaReguler;
    int hargaVIP;
    String jam;
    String title;
    String tanggal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_ticket);


        mAuth = FirebaseAuth.getInstance();
        progressBarMain = findViewById(R.id.progressBar2);

//        timId = getIntent().getExtras().getInt("timId");
//        stadionId = getIntent().getExtras().getInt("stadionId");

        recyclerView = findViewById(R.id.rvMain);
        daftar = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(UserTicket.this));
        db = FirebaseFirestore.getInstance();
//        Log.d("TimID", String.valueOf(timId));
//        match.orderByChild('Firstname').equalTo().addValueEventListener(new ValueEventListener() {
        orderMatch.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mWorkerData = new ArrayList<>();

                mWorkerData.clear();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    try{
                        final String key = ds.child("key").getValue(String.class);
                        final String namaUser = ds.child("user").getValue(String.class);
                        final String metode = ds.child("metode").getValue(String.class);
                        final String matchId = ds.child("match").getValue(String.class);
                        final String userID = ds.child("userID").getValue(String.class);
                        final int seat = ds.child("seat").getValue(Integer.class);
                        final int total = ds.child("total").getValue(Integer.class);
                        final boolean verified = ds.child("verified").getValue(Boolean.class);

                        match.orderByChild("key").equalTo(matchId).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                    Log.d("getMatch", String.valueOf(ds.child("hargaOutdoor").getValue(Integer.class)));

                                    homeTeam = ds.child("Home").getValue(Integer.class);
                                    awayTeam = ds.child("Away").getValue(Integer.class);
                                    stadium = ds.child("stadium").getValue(Integer.class);

                                    hargaOutdoor = ds.child("hargaOutdoor").getValue(Integer.class);
                                    hargaReguler = ds.child("hargaReguler").getValue(Integer.class);
                                    hargaVIP = ds.child("hargaVIP").getValue(Integer.class);
                                    jam = ds.child("jam").getValue(String.class);
                                    title = ds.child("nama").getValue(String.class);
                                    tanggal = ds.child("tanggal").getValue(String.class);

                                    MyMatch ternak = new MyMatch(key, namaUser, userID, metode, seat, total, verified, homeTeam, awayTeam, stadium, hargaVIP, hargaReguler, hargaOutdoor, jam, title, tanggal);
                                    mWorkerData.add(ternak);
                                }
                                Log.d("getMatch-out", String.valueOf(hargaOutdoor));
                                mAdapter = new UserMatchAdapter(UserTicket.this, mWorkerData);
                                recyclerView.setAdapter(mAdapter);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }catch (Exception e){

                    }


//
//                    String key = ds.child("key").getValue(String.class);
//                    int homeTeam = ds.child("Home").getValue(Integer.class);
//                    int awayTeam = ds.child("Away").getValue(Integer.class);
//                    int stadium = ds.child("stadium").getValue(Integer.class);

//                    int hargaOutdoor = ds.child("hargaOutdoor").getValue(Integer.class);
//                    int hargaReguler = ds.child("hargaReguler").getValue(Integer.class);
//                    int hargaVIP = ds.child("hargaVIP").getValue(Integer.class);
//                    String jam = ds.child("jam").getValue(String.class);
//                    String title = ds.child("nama").getValue(String.class);
//                    String tanggal = ds.child("tanggal").getValue(String.class);


                }

                // Initialize the adapter and set it to the RecyclerView.


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
