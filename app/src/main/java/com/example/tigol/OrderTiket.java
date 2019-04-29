package com.example.tigol;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.Locale;

public class OrderTiket extends AppCompatActivity {

    TextView jumlah_textView;
    RadioGroup radioButtonGroup;
    RadioButton radioButton;
    String selectedtext;
    int metodeIndex;
    int seat;
    int kelas;
    int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_tiket);

        jumlah_textView = findViewById(R.id.jumlah_textView);

        radioButtonGroup = findViewById(R.id.metode_radioGroup);


        String[] classArray = getResources().getStringArray(R.array.Class);

        Locale localeID = new Locale("in", "ID");
        final NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        seat = getIntent().getExtras().getInt("jumlah");
        kelas = getIntent().getExtras().getInt("class");

        if(kelas == 0)
            total = getIntent().getExtras().getInt("vip") * seat;
        else if(kelas == 1)
            total = getIntent().getExtras().getInt("reguler") * seat;
        else if(kelas == 2)
            total = getIntent().getExtras().getInt("outdoor") * seat;
        else
            total = getIntent().getExtras().getInt("outdoor");

        jumlah_textView.setText(formatRupiah.format(total));

    }

    public void checkout(View view){
        int radioButtonID = radioButtonGroup.getCheckedRadioButtonId();
        radioButton = radioButtonGroup.findViewById(radioButtonID);
        metodeIndex = radioButtonGroup.indexOfChild(radioButton);
        RadioButton r = (RadioButton) radioButtonGroup.getChildAt(metodeIndex);
        selectedtext = r.getText().toString();

        SharedPreferences sharedPref = getSharedPreferences("user", Context.MODE_PRIVATE);
        String nama = sharedPref.getString("nama","John Doe");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("orderMatch");
        String key = ref.push().getKey();
        ref = ref.child(key);
        ref.child("key").setValue(key);
        ref.child("match").setValue(getIntent().getExtras().getString("key"));
        ref.child("user").setValue(nama);
        ref.child("seat").setValue(seat);
        ref.child("total").setValue(total);
        ref.child("metode").setValue(selectedtext);
        ref.child("verified").setValue(false);

        Toast.makeText(this, "Order Berhasil, silahkan Bayar", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(OrderTiket.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
