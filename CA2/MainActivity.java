package com.example.ca_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

    }
    public void Ok(View view){
        Toast.makeText(this, "Going to Next Page", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,CA2.class));
    }
}












































//1. Create a new Activity
//First, you need to create another activity where you want to navigate.
//
//In Android Studio, right-click on the src folder and select New -> Activity -> Empty Activity.
//Name your new activity, for example, SecondActivity.
//2. Add a Button in your XML Layout
//In the layout file of your main activity (activity_main.xml), add a Button:
//
//<Button
//android:id="@+id/buttonNext"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:text="Go to Next Page"
//android:onClick="goToNextPage" />
//Here, the onClick attribute is used to reference the function that will handle the button click event.
//
//        3. Set Up the Button Click Listener in MainActivity
//In your MainActivity.java or MainActivity.kt file, define the method goToNextPage. This method will be called when the button is clicked.
//
//Java:
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//
//    // This method will be triggered when the button is clicked
//    public void goToNextPage(View view) {
//        // Create an Intent to navigate to the second activity
//        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//        startActivity(intent);
//    }
//}
//Kotlin:
//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }
//
//    // This method will be triggered when the button is clicked
//    fun goToNextPage(view: View) {
//        // Create an Intent to navigate to the second activity
//        val intent = Intent(this, SecondActivity::class.java)
//        startActivity(intent)
//    }
//}
//4. Set up the Second Activity Layout
//Make sure your SecondActivity has its own layout, which you can design in activity_second.xml.
//
//5. Add the Second Activity to AndroidManifest.xml
//Ensure that the new activity is declared in the AndroidManifest.xml:
//
//<activity android:name=".SecondActivity"></activity>
//Now, when you run the app and click the button, it will take you to SecondActivity.
//
//
