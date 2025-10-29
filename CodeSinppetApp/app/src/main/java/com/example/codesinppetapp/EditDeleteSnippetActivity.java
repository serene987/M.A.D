package com.example.codesinppetapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditDeleteSnippetActivity extends AppCompatActivity {
    private SQLiteDatabase database;
    private int snippetId;
    private EditText editTitle, editContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdelete_snippet);

        editTitle = findViewById(R.id.editTitle);
        editContent = findViewById(R.id.editContent);
        Button saveButton = findViewById(R.id.saveButton);
        Button deleteButton = findViewById(R.id.deleteButton);

        database = openOrCreateDatabase("snippets.db", MODE_PRIVATE, null);

        // Get data from Intent
        snippetId = getIntent().getIntExtra("id", -1);
        editTitle.setText(getIntent().getStringExtra("title"));
        editContent.setText(getIntent().getStringExtra("content"));

        // Save changes
        saveButton.setOnClickListener(v -> updateSnippet());

        // Delete snippet
        deleteButton.setOnClickListener(v -> deleteSnippet());
    }

    private void updateSnippet() {
        String newTitle = editTitle.getText().toString().trim();
        String newContent = editContent.getText().toString().trim();

        ContentValues values = new ContentValues();
        values.put("title", newTitle);
        values.put("content", newContent);

        database.update("snippets", values, "id=?", new String[]{String.valueOf(snippetId)});
        Toast.makeText(this, "Snippet Updated!", Toast.LENGTH_SHORT).show();

        finish(); // Close activity and go back
    }

    private void deleteSnippet() {
        database.delete("snippets", "id=?", new String[]{String.valueOf(snippetId)});
        Toast.makeText(this, "Snippet Deleted!", Toast.LENGTH_SHORT).show();

        finish(); // Close activity and go back
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (database != null) {
            database.close();
        }
    }
}
