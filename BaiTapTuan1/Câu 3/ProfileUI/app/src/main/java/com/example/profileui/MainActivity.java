package com.example.profileui;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnBack, btnEdit;
    private TextView tvName, tvLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các view trong layout
        btnBack = findViewById(R.id.btnBack);
        btnEdit = findViewById(R.id.btnEdit);
        tvName = findViewById(R.id.tvName);
        tvLocation = findViewById(R.id.tvLocation);

        // Gán dữ liệu tĩnh để hiển thị
        tvName.setText("Johan Smith");
        tvLocation.setText("California, USA");
    }
}
