package com.example.tigol;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DisplayCard extends AppCompatActivity {
    private ImageView arrow;
    RecyclerView recyclerView;
    ArrayList<Card> daftar;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    ProgressBar progressBarMain;
    int REQUEST_MENU = 404;
    DatabaseReference match = FirebaseDatabase.getInstance().getReference("Match");
    private ArrayList<Match> mWorkerData;
    private MatchAdapter mAdapter;
    int timId;
    int stadionId;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaycard);

        mAuth = FirebaseAuth.getInstance();
        progressBarMain = findViewById(R.id.progressBar2);
        arrow = findViewById(R.id.imageView4);

        timId = getIntent().getExtras().getInt("timId");
        stadionId = getIntent().getExtras().getInt("stadionId");

        recyclerView = findViewById(R.id.rvMain);
        daftar = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(DisplayCard.this));
        db = FirebaseFirestore.getInstance();
        Log.d("TimID", String.valueOf(timId));
//        match.orderByChild('Firstname').equalTo().addValueEventListener(new ValueEventListener() {
        match.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mWorkerData = new ArrayList<>();

                mWorkerData.clear();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = ds.child("key").getValue(String.class);
                    int homeTeam = ds.child("Home").getValue(Integer.class);
                    int awayTeam = ds.child("Away").getValue(Integer.class);
                    int stadium = ds.child("stadium").getValue(Integer.class);

                    if (stadium == stadionId && (homeTeam == timId || awayTeam == timId)) {
                        int hargaOutdoor = ds.child("hargaOutdoor").getValue(Integer.class);
                        int hargaReguler = ds.child("hargaReguler").getValue(Integer.class);
                        int hargaVIP = ds.child("hargaVIP").getValue(Integer.class);
                        String jam = ds.child("jam").getValue(String.class);
                        String title = ds.child("nama").getValue(String.class);
                        String tanggal = ds.child("tanggal").getValue(String.class);

                        Match ternak = new Match(key, homeTeam, awayTeam, stadium, hargaVIP, hargaReguler, hargaOutdoor, jam, title, tanggal);
                        mWorkerData.add(ternak);
                    }
                }

                // Initialize the adapter and set it to the RecyclerView.
                mAdapter = new MatchAdapter(DisplayCard.this, mWorkerData);
                recyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayCard.this, MainActivity.class);
                startActivity(intent);
            }
        });

        init();

    }

    @SuppressLint("StaticFieldLeak")
    public void init() {
        daftar.clear();
        progressBarMain.setVisibility(View.VISIBLE);
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }

            @Override
            protected Void doInBackground(Void... voids) {

                getthat();
                return null;
            }
        }.execute();

    }

    public void loadMenu(View view) {
        init();
    }

    public void getthat() {
        db.collection(mAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {

                        Log.d("Result", document.getId() + " => " + document.getData());
                        daftar.add(new Card(document.getId(), document.get("NamaMenu").toString(), document.get("Harga").toString(), document.get("Deskripsi").toString()));
                    }
//                    adapterCard.notifyDataSetChanged();
                }
                progressBarMain.setVisibility(View.GONE);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        init();
        super.onActivityResult(requestCode, resultCode, data);
    }
}
