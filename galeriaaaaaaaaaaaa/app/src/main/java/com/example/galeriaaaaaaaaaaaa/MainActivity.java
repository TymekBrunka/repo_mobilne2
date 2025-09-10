package com.example.galeriaaaaaaaaaaaa;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView txtFileSize, txtImageSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        txtFileSize = findViewById(R.id.txtFileSize);
        txtImageSize = findViewById(R.id.txtImageSize);

        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);

        btn1.setOnClickListener(v->imageLoad(R.drawable.photo1));
        btn2.setOnClickListener(v->imageLoad(R.drawable.photo2));
        btn3.setOnClickListener(v->imageLoad(R.drawable.photo3));
        btn4.setOnClickListener(v->imageLoad(R.drawable.photo4));
        btn5.setOnClickListener(v->imageLoad(R.drawable.photo5));
    }

    private void imageLoad(int resId){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
        imageView.setImageBitmap(bitmap);

        int h = bitmap.getHeight();
        int w = bitmap.getWidth();

        txtImageSize.setText("Rozmiar obrazu: " + w + "x" + h);

        try {
            InputStream inputStream = getResources().openRawResource(resId);
            int size = inputStream.available();
            inputStream.close();
            txtFileSize.setText("Rozmiar pliku: " + (size/1024) + "KB\nNazwa pliku: " + getResources().getResourceName(resId));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}