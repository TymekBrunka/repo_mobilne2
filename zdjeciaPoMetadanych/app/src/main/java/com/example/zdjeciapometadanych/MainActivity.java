package com.example.zdjeciapometadanych;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    Button btnImage;
    ImageView imageView;
    TextView textInfo;

    //Obsługa obrazków w oknie wczytywania
    private final ActivityResultLauncher<Intent> imagePicker = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
            Uri imageUri = result.getData().getData();
            if (imageUri != null) {
                imageView.setImageURI(imageUri);
                showInfo(imageUri);
            }
        }
    });

    private void showInfo(Uri uri) {
        String name = "brak";
        long size = 0;

        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri, null, null, null, null);
        if (cursor != null) {
            int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            int sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);

            if (cursor.moveToFirst()) {
                name = cursor.getString(nameIndex);
                size = cursor.getLong(sizeIndex);
            }
            cursor.close();
        }

        int width = 0;
        int height = 0;

        try {
            InputStream inputStream = resolver.openInputStream(uri);
        } catch (FileNotFoundException e) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnImage = findViewById(R.id.btnImage);
        imageView = findViewById(R.id.imageView);
        textInfo = findViewById(R.id.textInfo);

        btnImage.setOnClickListener(v -> openImagePicker());
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        imagePicker.launch(intent);
    }
}