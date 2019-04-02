package com.example.android.tigol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class JumlahTiket extends AppCompatActivity {

    Button tambah;
    Button kurang;
    TextView jumlahtiket;
    String jumlahtiket_beli;
    int tiket=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jumlah_tiket);

        tambah= findViewById(R.id.tambah);
        kurang = findViewById(R.id.kurang);
        jumlahtiket = findViewById(R.id.jumlahtiket);



        tambah.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v){

                        tiket++;
                        jumlahtiket_beli = Integer.toString(tiket);
                        jumlahtiket.setText(jumlahtiket_beli);

                    }
                }
        );
        kurang.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v){

                        tiket--;
                        jumlahtiket_beli = Integer.toString(tiket);
                        jumlahtiket.setText(jumlahtiket_beli);
                    }
                }
        );

    }
}
