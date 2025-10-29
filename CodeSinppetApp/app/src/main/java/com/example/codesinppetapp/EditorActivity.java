package com.example.codesinppetapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.TextAppearanceInfo;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


public class EditorActivity extends AppCompatActivity {
    private static final String DATABASE_NAME = "snippets.db";
    private static final String TABLE_NAME = "snippets";
    private SQLiteDatabase database;
    private LinearLayout snippetsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editor);

        Button createButton = findViewById(R.id.buttonCreate);
        snippetsContainer = findViewById(R.id.snippetsContainer);

        // Open database
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditorActivity.this, CreateActivity.class));
            }
        });

        loadSnippets();
    }

    public void CreateSnippet(View view) {
        Toast.makeText(this, "Wait for it to load", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditorActivity.this, CreateActivity.class));
    }


    private void loadSnippets() {
        snippetsContainer.removeAllViews(); // Clear previous views. & if new is created it imedaiatly adds here

        Cursor cursor = database.query(TABLE_NAME, new String[]{"id", "title", "content"},
                null, null, null, null, "id DESC");

        if (cursor != null && cursor.moveToFirst()) {
            do {
                final int snippetId = cursor.getInt(0);
                // Declare it as final here inorder to select a particular snippet to edit or delete
                String title = cursor.getString(1);
                String content = cursor.getString(2);

                // Create a Card-like Layout for better visibility
                LinearLayout snippetLayout = new LinearLayout(this);
                snippetLayout.setOrientation(LinearLayout.VERTICAL);
                snippetLayout.setPadding(20, 20, 20, 20);
                snippetLayout.setBackgroundResource(R.drawable.snippet_background); // Add background

                // Title TextView
                TextView titleView = new TextView(this);
                titleView.setText("✏\uFE0F" + "  " + title);
                // Set color from colors.xml
                titleView.setTextColor(ContextCompat.getColor(this, R.color.white));
                titleView.setTextSize(20); // Increase font size
                titleView.setTypeface(null, Typeface.BOLD);
                titleView.setPadding(10, 10, 10, 10);

                // Content TextView
                TextView contentView = new TextView(this);
                contentView.setText(content);
                contentView.setTextColor(ContextCompat.getColor(this, R.color.white));
                contentView.setTextSize(18); // Increase font size
                contentView.setPadding(10, 10, 10, 10);


                // When clicked, open EditSnippetActivity to edit and delete
//                snippetLayout.setOnClickListener(v -> {
//                    Intent intent = new Intent(EditorActivity.this, EditDeleteSnippetActivity.class);
//                    intent.putExtra("id", snippetId);
//                    intent.putExtra("title", title);
//                    intent.putExtra("content", content);
//                    startActivity(intent);
//                });

                // Add views to snippet layout
                snippetLayout.addView(titleView);
                snippetLayout.addView(contentView);
                // Add snippet layout to main container
                snippetsContainer.addView(snippetLayout);

            } while (cursor.moveToNext());

            cursor.close();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadSnippets(); // Reload snippets when returning to this activity
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (database != null) {
            database.close();
        }
    }
}
