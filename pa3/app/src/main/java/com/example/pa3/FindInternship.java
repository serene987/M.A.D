package com.example.pa3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class FindInternship extends AppCompatActivity {

    private EditText editTextSearch;
    private Button buttonSearch, btnBack;
    private LinearLayout resultsContainer;
    private List<Internship> internshipList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_activity);

        editTextSearch = findViewById(R.id.editTextSearch);
        buttonSearch = findViewById(R.id.buttonSearch);
        btnBack = findViewById(R.id.btnBack);
        resultsContainer = findViewById(R.id.resultsContainer);

        loadDummyData();

        buttonSearch.setOnClickListener(v -> searchInternship());

        btnBack.setOnClickListener(v -> finish());
    }

    private void loadDummyData() {
        internshipList.add(new Internship("Software Engineer Intern", "Google", "Work on real-world projects using AI and cloud computing.", "9 AM - 5 PM", "3 Months", "Mountain View, CA"));
        internshipList.add(new Internship("Software Engineer Intern", "Amazon", "Work on projects using AI.", "9 AM - 5 PM", "6 Months", "Goa, GA"));

        internshipList.add(new Internship("Data Science Intern", "Facebook", "Analyze large datasets to gain business insights.", "10 AM - 6 PM", "6 Months", "Menlo Park, CA"));
        internshipList.add(new Internship("Marketing Intern", "Amazon", "Develop creative marketing strategies for product launches.", "9 AM - 4 PM", "4 Months", "Seattle, WA"));
        internshipList.add(new Internship("UI/UX Designer Intern", "Apple", "Design intuitive interfaces for mobile and web applications.", "10 AM - 5 PM", "5 Months", "Cupertino, CA"));
        internshipList.add(new Internship("Cybersecurity Intern", "Microsoft", "Work on securing networks and preventing cyber attacks.", "8 AM - 4 PM", "6 Months", "Redmond, WA"));
        internshipList.add(new Internship("AI Research Intern", "OpenAI", "Develop and train state-of-the-art AI models for real-world applications.", "9 AM - 6 PM", "3 Months", "San Francisco, CA"));
        internshipList.add(new Internship("Blockchain Developer Intern", "Ethereum Foundation", "Contribute to open-source blockchain projects and smart contract development.", "10 AM - 5 PM", "4 Months", "Remote"));
        internshipList.add(new Internship("Embedded Systems Intern", "Intel", "Work on IoT and hardware-optimized software development.", "9 AM - 5 PM", "5 Months", "Santa Clara, CA"));
        internshipList.add(new Internship("Game Developer Intern", "Epic Games", "Develop and test new game mechanics using Unreal Engine.", "11 AM - 7 PM", "3 Months", "Cary, NC"));
        internshipList.add(new Internship("Cloud Computing Intern", "AWS", "Learn and implement scalable cloud infrastructure and DevOps practices.", "8 AM - 4 PM", "6 Months", "Seattle, WA"));
    }

    private void searchInternship() {
        String searchText = editTextSearch.getText().toString().trim().toLowerCase();
        resultsContainer.removeAllViews();

        if (searchText.isEmpty()) {
            Toast.makeText(this, "Please enter a job title to search!", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean found = false;
        for (Internship internship : internshipList) {
            if (internship.getTitle().toLowerCase().contains(searchText)) {
                found = true;
                addResultView(internship);
            }
        }

        if (!found) {
            Toast.makeText(this, "No internships found!", Toast.LENGTH_SHORT).show();
        }
    }

    private void addResultView(Internship internship) {
        TextView textView = new TextView(this);
        textView.setText("🔹 " + internship.getTitle() + "\n🏢 " + internship.getCompany());
        textView.setPadding(20, 20, 20, 20);
        textView.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        textView.setTextSize(16);

        textView.setOnClickListener(v -> {
            Intent intent = new Intent(FindInternship.this, Details.class);
            intent.putExtra("title", internship.getTitle());
            intent.putExtra("company", internship.getCompany());
            intent.putExtra("description", internship.getDescription());
            intent.putExtra("workingTime", internship.getWorkingTime());
            intent.putExtra("duration", internship.getDuration());
            intent.putExtra("location", internship.getLocation());
            startActivity(intent);
        });

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 10, 0, 10);
        textView.setLayoutParams(params);

        resultsContainer.addView(textView);
    }

    static class Internship {
        private final String title;
        private final String company;
        private final String description;
        private final String workingTime;
        private final String duration;
        private final String location;

        public Internship(String title, String company, String description, String workingTime, String duration, String location) {
            this.title = title;
            this.company = company;
            this.description = description;
            this.workingTime = workingTime;
            this.duration = duration;
            this.location = location;
        }

        public String getTitle() { return title; }
        public String getCompany() { return company; }
        public String getDescription() { return description; }
        public String getWorkingTime() { return workingTime; }
        public String getDuration() { return duration; }
        public String getLocation() { return location; }
    }
}
