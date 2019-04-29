package com.example.tigol;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class TicketOrderDetail extends AppCompatActivity {

    private TextView mTitleText;
    private TextView mTanggalText;
    private TextView mWaktuText;
    private TextView mHargaRegulerText;
    private TextView mHargaOutdoorText;
    private TextView mHargaVIPText;
    private TextView mHomeText;
    private TextView mAwayText;
    private TextView mVersusText;
    private TextView mStadionText;
    private ImageView mHomeImage;
    private ImageView mAwayImage;
    private Spinner mClassSpinner;
    private EditText mJumlahED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_order_detail);

        mTitleText = findViewById(R.id.title_textView);
        mTanggalText = findViewById(R.id.tanggal_textView);
        mWaktuText = findViewById(R.id.waktu_textView);
        mHargaOutdoorText = findViewById(R.id.hargaOutdoor_textView);
        mHargaRegulerText = findViewById(R.id.hargaReguler_textView);
        mHargaVIPText = findViewById(R.id.hargaVIP_textView);
        mHomeText = findViewById(R.id.home_textView);
        mAwayText = findViewById(R.id.away_textView);
        mVersusText = findViewById(R.id.versus_textView);
        mStadionText = findViewById(R.id.stadion_textView);

        mHomeImage = findViewById(R.id.home_image);
        mAwayImage = findViewById(R.id.away_image);

        mClassSpinner = findViewById(R.id.class_spinner);
        mJumlahED = findViewById(R.id.jumlah_editText);

        int key;

        // Populate the textviews with data.
        String[] tim = getResources().getStringArray(R.array.Teamlist);;
        String[] stadion = getResources().getStringArray(R.array.Stadium);;

        Locale localeID = new Locale("in", "ID");
        final NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        mTitleText.setText(getIntent().getExtras().getString("title"));
        mTanggalText.setText(getIntent().getExtras().getString("tanggal"));
        mWaktuText.setText(getIntent().getExtras().getString("jam"));
        mHargaOutdoorText.setText(formatRupiah.format(getIntent().getExtras().getInt("outdoor")) + " [OUD]");
        mHargaRegulerText.setText(formatRupiah.format(getIntent().getExtras().getInt("reguler")) + " [REG]");
        mHargaVIPText.setText(formatRupiah.format(getIntent().getExtras().getInt("vip")) + " [VIP]");
        mHomeText.setText(tim[getIntent().getExtras().getInt("home")]);
        mAwayText.setText(tim[getIntent().getExtras().getInt("away")]);
        mStadionText.setText(stadion[getIntent().getExtras().getInt("stadium")]);
        mVersusText.setText(mHomeText.getText() + " VS " + mAwayText.getText());
        key = getIntent().getExtras().getInt("key");

        final TypedArray imgs = getResources().obtainTypedArray(R.array.DaftarGambar);
        mHomeImage.setImageResource(imgs.getResourceId(getIntent().getExtras().getInt("home"),-1));
        mAwayImage.setImageResource(imgs.getResourceId(getIntent().getExtras().getInt("away"),-1));
    }

    public void payment(View view){
        Intent orderTiket = new Intent(TicketOrderDetail.this, OrderTiket.class);
        orderTiket.putExtra("key", getIntent().getExtras().getString("key"));
        orderTiket.putExtra("title", getIntent().getExtras().getString("title"));
        orderTiket.putExtra("tanggal", getIntent().getExtras().getString("tanggal"));
        orderTiket.putExtra("jam", getIntent().getExtras().getString("jam"));
        orderTiket.putExtra("outdoor", getIntent().getExtras().getInt("outdoor"));
        orderTiket.putExtra("reguler", getIntent().getExtras().getInt("reguler"));
        orderTiket.putExtra("vip", getIntent().getExtras().getInt("vip"));
        orderTiket.putExtra("home", getIntent().getExtras().getInt("home"));
        orderTiket.putExtra("away", getIntent().getExtras().getInt("away"));
        orderTiket.putExtra("stadium", getIntent().getExtras().getInt("stadium"));
        orderTiket.putExtra("jumlah", Integer.valueOf(mJumlahED.getText().toString()));
        orderTiket.putExtra("class", mClassSpinner.getSelectedItemPosition());
        startActivity(orderTiket);
    }
}
