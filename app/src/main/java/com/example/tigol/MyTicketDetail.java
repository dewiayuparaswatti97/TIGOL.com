package com.example.tigol;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.text.NumberFormat;
import java.util.Locale;

import id.zelory.compressor.Compressor;


public class MyTicketDetail extends AppCompatActivity {

    private TextView mTitleText;
    private TextView mTanggalText;
    private TextView mWaktuText;
    private TextView mHomeText;
    private TextView mAwayText;
    private TextView mVersusText;
    private TextView mStadionText;

    private TextView mPembeliText;
    private TextView mTotalText;
    private TextView mSeatText;
    private TextView mMetodeText;
    private TextView mStatusText;

    private ImageView mHomeImage;
    private ImageView mPhotoImage;
    private ImageView mAwayImage;
    private Spinner mClassSpinner;
    private EditText mJumlahED;
    CardView firstPhoto_card;
    int REQUEST = 91;
    Uri newUri;
    StorageReference storageReference;
    FirebaseStorage storage;
    FirebaseFirestore db;
    String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ticket_detail);

        key = getIntent().getExtras().getString("key");

        mTitleText = findViewById(R.id.title_textView);
        mPembeliText = findViewById(R.id.pembeli_textView);
        mTotalText = findViewById(R.id.total_textView);
        mSeatText = findViewById(R.id.seat_textView);
        mStatusText = findViewById(R.id.status_textView);
        mMetodeText = findViewById(R.id.metode_textView);
        mTanggalText = findViewById(R.id.tanggal_textView);
        mWaktuText = findViewById(R.id.waktu_textView);
        mHomeText = findViewById(R.id.home_textView);
        mAwayText = findViewById(R.id.away_textView);
        mVersusText = findViewById(R.id.versus_textView);
        mStadionText = findViewById(R.id.stadion_textView);
        mPhotoImage = findViewById(R.id.photo_imageView);
        db = FirebaseFirestore.getInstance();

        mHomeImage = findViewById(R.id.home_image);
        mAwayImage = findViewById(R.id.away_image);
        firstPhoto_card = findViewById(R.id.firstPhoto_cardView);

        mClassSpinner = findViewById(R.id.class_spinner);
        mJumlahED = findViewById(R.id.jumlah_editText);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        // Populate the textviews with data.
        String[] tim = getResources().getStringArray(R.array.Teamlist);
        ;
        String[] stadion = getResources().getStringArray(R.array.Stadium);
        ;

        Locale localeID = new Locale("in", "ID");
        final NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        mTitleText.setText(getIntent().getExtras().getString("title"));
        mPembeliText.setText(getIntent().getExtras().getString("namauser"));
        mStatusText.setText(getIntent().getExtras().getString("verified"));
        mMetodeText.setText(getIntent().getExtras().getString("metode"));
        mTanggalText.setText(getIntent().getExtras().getString("tanggal"));
        mWaktuText.setText(getIntent().getExtras().getString("jam"));
        mSeatText.setText(String.valueOf(getIntent().getExtras().getInt("seat")));
        mTotalText.setText(formatRupiah.format(getIntent().getExtras().getInt("total")));
        mHomeText.setText(tim[getIntent().getExtras().getInt("home")]);
        mAwayText.setText(tim[getIntent().getExtras().getInt("away")]);
        mStadionText.setText(stadion[getIntent().getExtras().getInt("stadium")]);
        mVersusText.setText(mHomeText.getText() + " VS " + mAwayText.getText());

        final TypedArray imgs = getResources().obtainTypedArray(R.array.DaftarGambar);
        mHomeImage.setImageResource(imgs.getResourceId(getIntent().getExtras().getInt("home"), -1));
        mAwayImage.setImageResource(imgs.getResourceId(getIntent().getExtras().getInt("away"), -1));

        firstPhoto_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                try {
                    File compressed = new Compressor(this).
                            setQuality(25).
                            compressToFile(new File(result.getUri().getPath()));

                    newUri = Uri.fromFile(compressed);
                    Log.d("selectedImage", newUri.toString());

                    Glide.with(this)
                            .load(newUri)
                            .placeholder(R.mipmap.ic_launcher)
                            .error(R.mipmap.ic_launcher)
                            .into(mPhotoImage);
                } catch (Exception e) {

                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    private void selectImage(){
        if (ContextCompat.checkSelfPermission(MyTicketDetail.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MyTicketDetail.this,
                    new String[]{Manifest.permission.CAMERA},
                    REQUEST);
        } else {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
//                    .setAspectRatio(1, 1)
                    .start(MyTicketDetail.this);
        }
    }

    public void selectImage(View view){
        selectImage();
    }

    public void upload(View view) {
        StorageReference ref = storageReference.child("images/" + key);
        ref.putFile(newUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                                                imgNumberSuccess++;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                Log.d("UploadImage", String.valueOf(progress) + "%");
                if (progress == 100) {
                    Snackbar.make(findViewById(R.id.detail_layoutView), "Bukti Pembayaran berhasil diupload", Snackbar.LENGTH_LONG).setDuration(2000).show();
                }
            }
        });
    }

//
//    public void payment(View view){
//        Intent orderTiket = new Intent(MyTicketDetail.this, OrderTiket.class);
//        orderTiket.putExtra("key", getIntent().getExtras().getString("key"));
//        orderTiket.putExtra("title", getIntent().getExtras().getString("title"));
//        orderTiket.putExtra("tanggal", getIntent().getExtras().getString("tanggal"));
//        orderTiket.putExtra("jam", getIntent().getExtras().getString("jam"));
//        orderTiket.putExtra("outdoor", getIntent().getExtras().getInt("outdoor"));
//        orderTiket.putExtra("reguler", getIntent().getExtras().getInt("reguler"));
//        orderTiket.putExtra("vip", getIntent().getExtras().getInt("vip"));
//        orderTiket.putExtra("home", getIntent().getExtras().getInt("home"));
//        orderTiket.putExtra("away", getIntent().getExtras().getInt("away"));
//        orderTiket.putExtra("stadium", getIntent().getExtras().getInt("stadium"));
//        orderTiket.putExtra("jumlah", Integer.valueOf(mJumlahED.getText().toString()));
//        orderTiket.putExtra("class", mClassSpinner.getSelectedItemPosition());
//        startActivity(orderTiket);
//    }
}
