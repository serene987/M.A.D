package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';

    private static char currentSymbol;

    private double firstVal = Double.NaN;
    private double secondVal;
    private TextView inputDisplay, outputDisplay;

    private int btnM1,btnM2,btnM3,btnM4,btnM5,btnM6,btnM7,btnM8,btnM9,btnM10,btnM11,btnM12,btnM13,btnM14,btnM15,btnM17,txtV;


//    new Button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

//        inputDisplay = findViewById(R.id.input);
//        outputDisplay = findViewById(R.id.output);


        btn1 = findViewById(R.id.btnM1);
        btn2=findViewById(R.id.btnM2);
        btn3=findViewById(R.id.btnM3);
        btn4=findViewById(R.id.btnM4);
        btn5=findViewById(R.id.btnM5);
        btn6=findViewById(R.id.btnM6);
        btn7=findViewById(R.id.btnM7);
        btn8=findViewById(R.id.btnM8);
        btn9=findViewById(R.id.btnM9);
        btn10=findViewById(R.id.btnM10);
        btn11=findViewById(R.id.btnM11);
        btn12=findViewById(R.id.btnM12);
        btn13=findViewById(R.id.btnM13);
        btn14=findViewById(R.id.btnM14);
        btn15=findViewById(R.id.btnM15);
        btn17=findViewById(R.id.btnM17);

        txtV=findViewById(R.id.textView4);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                txtV.setText("1");
                Toast.makeText(getApplicationContext(),"button was clicked", Toast.LENGTH_LONG).show();
            }
        });

    }
}