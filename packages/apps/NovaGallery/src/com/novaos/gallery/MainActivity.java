package com.novaos.gallery;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView grid = findViewById(R.id.photo_grid);
        grid.setLayoutManager(new GridLayoutManager(this, 3));
        
        // Mock photo loading logic
    }
}
