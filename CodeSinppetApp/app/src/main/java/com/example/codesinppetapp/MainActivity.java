package com.example.codesinppetapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Handler;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // start intent after 3 seconds
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, EditorActivity.class);
            startActivity(intent);
        }, 3000);
    }
}



































// new possible features
// 1.  Search Functionality (🔍 30 min)
//     Add a search bar at the top of EditorActivity to filter snippets by title or content.
//     Use SQLiteDatabase.query() with a WHERE title LIKE ? clause.

// 2. Categories/Tags for Snippets (📂 45 min)
//    Add a category dropdown when creating a snippet.
//    Modify the database to include a category column.
//    Filter snippets by category.

// 3.  Share Snippets (📤 15 min)
//Add a "Share" button to send snippets via email, WhatsApp, etc.
//Use Android’s Intent.ACTION_SEND.

// code.
 // Intent shareIntent = new Intent(Intent.ACTION_SEND);
//shareIntent.setType("text/plain");
//shareIntent.putExtra(Intent.EXTRA_TEXT, snippetContent);
//startActivity(Intent.createChooser(shareIntent, "Share via"));

// 4. Auto-Save Drafts (💾 45 min)
//Save unsaved snippets automatically in SharedPreferences.
//Restore drafts when opening the app.

// 5.  Dark Mode Toggle (🌙 30 min)
//Add a dark mode/light mode switch in EditorActivity.
//Save user preference in SharedPreferences.

// AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

// 6. Undo Delete (⏪ 30 min)
//When deleting, show a Snackbar with "Undo".
//Restore the snippet if "Undo" is pressed.

//Snackbar.make(view, "Snippet deleted", Snackbar.LENGTH_LONG)
//        .setAction("Undo", v -> restoreSnippet(deletedSnippet))
//        .show();

