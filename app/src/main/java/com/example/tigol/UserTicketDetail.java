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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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


public class UserTicketDetail extends AppCompatActivity {

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
    Button submit;
    Button reject;
    View line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_ticket_detail);

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
        submit = findViewById(R.id.submit_btn);
        mHomeImage = findViewById(R.id.home_image);
        mAwayImage = findViewById(R.id.away_image);
        firstPhoto_card = findViewById(R.id.firstPhoto_cardView);
        line = findViewById(R.id.line);

        mClassSpinner = findViewById(R.id.class_spinner);
        mJumlahED = findViewById(R.id.jumlah_editText);
        reject = findViewById(R.id.reject_btn);
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

        if (getIntent().getExtras().getBoolean("rejected")) {
            reject.setText("UNREJECT");
            submit.setVisibility(View.GONE);
            firstPhoto_card.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
        }else{
            if (getIntent().getExtras().getBoolean("verify")){
                submit.setText("UNVERIFIED");
                firstPhoto_card.setVisibility(View.GONE);
                line.setVisibility(View.GONE);
            }
            else
                firstPhoto_card.setVisibility(View.VISIBLE);
        }

        if (getIntent().getExtras().getBoolean("verify")) {
        }
        final TypedArray imgs = getResources().obtainTypedArray(R.array.DaftarGambar);
        mHomeImage.setImageResource(imgs.getResourceId(getIntent().getExtras().getInt("home"), -1));
        mAwayImage.setImageResource(imgs.getResourceId(getIntent().getExtras().getInt("away"), -1));

        StorageReference islandRef1 = FirebaseStorage.getInstance().getReference().child("images/" + key);
        islandRef1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplicationContext()).load(uri.toString()).into(mPhotoImage);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                firstPhoto_card.setVisibility(View.GONE);
            }
        });



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

    private void selectImage() {
        if (ContextCompat.checkSelfPermission(UserTicketDetail.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(UserTicketDetail.this,
                    new String[]{Manifest.permission.CAMERA},
                    REQUEST);
        } else {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
//                    .setAspectRatio(1, 1)
                    .start(UserTicketDetail.this);
        }
    }

    public void selectImage(View view) {
        selectImage();
    }

    public void upload(View view) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("orderMatch");
        ref = ref.child(key);
//        if (getIntent().getExtras().getBoolean("verify"))
//            ref.child("verified").setValue(1);
//        else
//            ref.child("verified").setValue(2);
//        finish();
//        startActivity(getIntent());

        Intent intent = getIntent();
        if (getIntent().getExtras().getBoolean("verify")){
            intent.putExtra("verify", false);
            ref.child("verified").setValue(1);
        }
        else{
            intent.putExtra("verify", true);
            ref.child("verified").setValue(2);
        }
        finish();
        startActivity(intent);
    }
    public void reject(View view) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("orderMatch");
        ref = ref.child(key);
        Intent intent = getIntent();
        if (getIntent().getExtras().getBoolean("rejected")){
            intent.putExtra("rejected", false);
            ref.child("verified").setValue(1);
        }
        else{
            intent.putExtra("rejected", true);
            ref.child("verified").setValue(0);
        }
        finish();
        startActivity(intent);
    }

}
