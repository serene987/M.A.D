package com.example.pa3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Details extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        TextView textViewDetails = findViewById(R.id.textTitle);
        Button btnBack = findViewById(R.id.btnBack);

        // Get internship details from intent
        Intent intent = getIntent();
        String details = "Title: " + intent.getStringExtra("title") + "\n" +
                "Company: " + intent.getStringExtra("company") + "\n" +
                "Description: " + intent.getStringExtra("description") + "\n" +
                "Working Time: " + intent.getStringExtra("workingTime") + "\n" +
                "Duration: " + intent.getStringExtra("duration") + "\n" +
                "Location: " + intent.getStringExtra("location");

        textViewDetails.setText(details);

        btnBack.setOnClickListener(v -> {
            Intent backIntent = new Intent(Details.this, FindInternship.class);
            backIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(backIntent);
            finish();
        });
    }
}
