package com.example.pa3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity{

//    private LinearLayout snippetsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.home_activity);

        Button btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, FindInternship.class));
            }
        });
    }

    public void startLooking(View view) {
        Toast.makeText(this, "Wait for it to load", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Home.this, FindInternship.class));
    }
}
