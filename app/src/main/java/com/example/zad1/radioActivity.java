package com.example.zad1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class radioActivity extends AppCompatActivity {

    private Button anuluj;
    private Button akceptuj;

    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);

        radioGroup = findViewById(R.id.radioGroup);

        Intent t = getIntent();
        final String temp = t.getStringExtra("temp");

        anuluj = (Button) findViewById(R.id.button4);
        anuluj.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(radioActivity.this, MainActivity.class);
                intent.putExtra("radioButton",temp);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        akceptuj = (Button) findViewById(R.id.button3);
        akceptuj.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);

                Intent intent = new Intent(radioActivity.this, MainActivity.class);
                intent.putExtra("radioButton",radioButton.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
        }
        });
    }
}
