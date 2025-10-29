package com.example.codesinppetapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import android.graphics.Bitmap;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class CreateActivity extends AppCompatActivity {
    private static final String DATABASE_NAME = "snippets.db";
    private static final String TABLE_NAME = "snippets";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_CONTENT = "content";
    private static final String COLUMN_IMAGE = "image"; // New column for image

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText editTextTitle, editTextContent;
    private ImageView imageViewSnippet;
    private Button buttonSelectImage;
    private SQLiteDatabase database;

    private byte[] imageBytes; // Store image data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextContent = findViewById(R.id.editTextContent);
        imageViewSnippet = findViewById(R.id.imageViewSnippet);
        buttonSelectImage = findViewById(R.id.buttonSelectImage);
        Button buttonSave = findViewById(R.id.buttonSave);

        // Open or create database
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

        // Create table if not exists
        String createTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_CONTENT + " TEXT, " +
                COLUMN_IMAGE + " BLOB)"; // Image stored as BLOB (big chunk of data)
        database.execSQL(createTable);

        // Select Image Button Click
        buttonSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
            }
        });

        // Save Snippet Button Click
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSnippet();
            }
        });
    }

    // Go back button
    public void GoBack(View view) {
        Toast.makeText(this, "Going back", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(CreateActivity.this, EditorActivity.class));
    }

    // Open Gallery to Select Image
    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    // Handle Selected Image Result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageViewSnippet.setImageBitmap(bitmap);
                imageBytes = getBytesFromBitmap(bitmap); // Convert image to byte array
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Convert Bitmap to Byte Array for Storage
    private byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    private void saveSnippet() {
        String title = editTextTitle.getText().toString().trim();
        String content = editTextContent.getText().toString().trim();

        if (title.isEmpty() || content.isEmpty()) {
            Toast.makeText(this, "Please enter both title and content", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_CONTENT, content);

        if (imageBytes != null) {
            values.put(COLUMN_IMAGE, imageBytes); // Save the selected image
        } else {
            values.putNull(COLUMN_IMAGE); // If no image is selected, store null
        }

        long result = database.insert(TABLE_NAME, null, values);
        if (result != -2) {
            Toast.makeText(this, "Snippet saved!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Error saving snippet", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (database != null) {
            database.close();
        }
    }
}
