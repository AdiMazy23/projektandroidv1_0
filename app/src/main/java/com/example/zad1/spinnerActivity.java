package com.example.zad1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class spinnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button anuluj;
    private Button akceptuj;

    String tekst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        Intent t = getIntent();
        final String temp2 = t.getStringExtra("temp2");

        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.spin, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        anuluj = (Button) findViewById(R.id.button5);
        anuluj.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(spinnerActivity.this, MainActivity.class);
                intent.putExtra("spin1",temp2);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        akceptuj = (Button) findViewById(R.id.button6);
        akceptuj.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(spinnerActivity.this, MainActivity.class);
                intent.putExtra("spin1", tekst);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        tekst = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
