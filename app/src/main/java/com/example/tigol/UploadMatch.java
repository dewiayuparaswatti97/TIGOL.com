package com.example.tigol;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.Map;

public class UploadMatch extends AppCompatActivity {
    int REQUEST = 91, REQUEST_GET_SINGLE_FILE = 202, REQUEST_CAPTURE_IMAGE = 234;
    Bitmap bitmap;
    ImageView foto, foto2;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    Uri uri;
    Spinner spinnerHome, spinnerAway, spinnerStadium;
    String imagePath;
    EditText namamatch, hargaVIP, hargaReguler, hargaOutdoor, tanggal, waktu;

    FirebaseStorage storage;
    StorageReference storageReference;

    Map<String, Object> menu;

    ProgressBar progressBar;

    boolean success = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadmatch);
        foto = findViewById(R.id.inImg);
        foto2 = findViewById(R.id.inImg2);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        //Init
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        namamatch = findViewById(R.id.edNamaInput);
        hargaOutdoor = findViewById(R.id.edHargaInputOutdoor);
        hargaReguler = findViewById(R.id.edHargaInputReguler);
        hargaVIP = findViewById(R.id.edHargaInputVIP);
        tanggal = findViewById(R.id.edDeskInput);
        waktu = findViewById(R.id.edJamInput);
        spinnerHome = findViewById(R.id.spinner);
        spinnerAway = findViewById(R.id.spinner2);
        spinnerStadium = findViewById(R.id.spinnerStadium);

        progressBar = findViewById(R.id.progressBar);

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);

        //spinner
        final TypedArray imgs = getResources().obtainTypedArray(R.array.DaftarGambar);
        spinnerHome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                foto.setImageResource(imgs.getResourceId(position,-1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerAway.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                foto2.setImageResource(imgs.getResourceId(position,-1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @SuppressLint("StaticFieldLeak")
    public void tambahMenu(View view) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Match");
        String key = ref.push().getKey();
        ref = ref.child(key);
        ref.child("key").setValue(key);
        ref.child("nama").setValue(namamatch.getText().toString());
        ref.child("Home").setValue(spinnerHome.getSelectedItemPosition());
        ref.child("Away").setValue(spinnerAway.getSelectedItemPosition());
        ref.child("hargaOutdoor").setValue(Integer.valueOf(hargaOutdoor.getText().toString()));
        ref.child("hargaReguler").setValue(Integer.valueOf(hargaReguler.getText().toString()));
        ref.child("hargaVIP").setValue(Integer.valueOf(hargaVIP.getText().toString()));
        ref.child("tanggal").setValue(tanggal.getText().toString());
        ref.child("jam").setValue(waktu.getText().toString());
        ref.child("stadium").setValue(spinnerStadium.getSelectedItemPosition());
        finish();

//        new AsyncTask<Void, Boolean, Boolean>() {
//
//
//            @Override
//            protected void onPreExecute() {
//                progressBar.setVisibility(View.VISIBLE);
//                menu = new HashMap<>();
//
//                super.onPreExecute();
//            }
//
//            @Override
//            protected void onPostExecute(Boolean aBoolean) {
//                if (aBoolean) {
//                    Toast.makeText(UploadMatch.this, "Berhasil", Toast.LENGTH_SHORT).show();
//                    finish();
//
//                }
//                super.onPostExecute(aBoolean);
//            }
//
//            @Override
//            protected Boolean doInBackground(Void... voids) {
//                menu.put("NamaMenu", namamatch.getText().toString());
//                menu.put("Harga", hargaVIP.getText().toString());
//                menu.put("Deskripsi", tanggal.getText().toString());
//                menu.put("Waktu",waktu.getText().toString());
//                menu.put("Date",new SimpleDateFormat().format(new Date()));
//
//
//                db.collection(mAuth.getUid())
//                        .add(menu)
//                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                            @Override
//                            public void onSuccess(DocumentReference documentReference) {
////                                docRef = documentReference.getId();
//                                //Upload data berhasil;
//                                success = true;
//                                if (uri != null) {
//                                    StorageReference ref = storageReference.child("images/" + documentReference.getId());
//                                    ref.putFile(uri)
//                                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                                                @Override
//                                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                                    Toast.makeText(UploadMatch.this, "Uploaded", Toast.LENGTH_SHORT).show();
//
//                                                }
//                                            })
//                                            .addOnFailureListener(new OnFailureListener() {
//                                                @Override
//                                                public void onFailure(@NonNull Exception e) {
//                                                    Toast.makeText(UploadMatch.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                                                }
//
//                                            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                                        @Override
//                                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                                            Toast.makeText(UploadMatch.this, "Uploading..", Toast.LENGTH_SHORT).show();
//                                        }
//                                    });
//                                }
//                                finish();
//                                progressBar.setVisibility(View.GONE);
//                            }
//                        });
//
//                return success;
//            }
//        }.execute();

    }

    public void upFireStore() {

    }

    public void selectPict(View view) {
        if (ContextCompat.checkSelfPermission(UploadMatch.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(UploadMatch.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(UploadMatch.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "Select Picture"),
                    REQUEST_GET_SINGLE_FILE);
        }
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAPTURE_IMAGE) {
                if (data != null && data.getExtras() != null) {
                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            bitmap = (Bitmap) data.getExtras().get("data");
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            foto.setImageBitmap(bitmap);
                            super.onPostExecute(aVoid);
                        }
                    }.execute();
                }
            } else if (requestCode == REQUEST_GET_SINGLE_FILE) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected void onPreExecute() {
                        uri = data.getData();
                        super.onPreExecute();
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        foto.setImageBitmap(bitmap);
                        super.onPostExecute(aVoid);
                    }

                    @Override
                    protected Void doInBackground(Void... voids) {
                        imagePath = getPathFromURI(getApplicationContext(), uri);
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),
                                    uri);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }.execute();
            }
        }


    }

    private static String getPathFromURI(Context context, Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        Log.i("URI", uri + "");
        String result = uri + "";
        // DocumentProvider
        //  if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
        if (isKitKat && (result.contains("media.documents"))) {
            String[] ary = result.split("/");
            int length = ary.length;
            String imgary = ary[length - 1];
            final String[] dat = imgary.split("%3A");
            final String docId = dat[1];
            final String type = dat[0];
            Uri contentUri = null;
            if ("image".equals(type)) {
                contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equals(type)) {
            } else if ("audio".equals(type)) {
            }
            final String selection = "_id=?";
            final String[] selectionArgs = new String[]{
                    dat[1]
            };
            return getDataColumn(context, contentUri, selection, selectionArgs);
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }
}
