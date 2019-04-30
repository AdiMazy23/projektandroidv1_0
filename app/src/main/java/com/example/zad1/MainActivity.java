package com.example.zad1;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button ringtone;
    private Button kontakt;

    ImageView avatar;
    TextView textView;

    String muzyka_tmp;
    String kontakt_tmp;
    private int aktualny_dzwonek = 0;

    private MediaPlayer przycisk_pauzastop;
    static public Uri[] tab_dzwieki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        tab_dzwieki = new Uri[5];
        tab_dzwieki[0] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.dzwiek_1);
        tab_dzwieki[1] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.dzwiek_2);
        tab_dzwieki[2] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.dzwiek_3);
        tab_dzwieki[3] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.dzwiek_4);
        tab_dzwieki[4] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.dzwiek_5);

        avatar = (ImageView) findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);

        int avatar_domyslny = R.drawable.default_avatar;
        avatar.setImageResource(avatar_domyslny);

        przycisk_pauzastop = new MediaPlayer();
        przycisk_pauzastop.setAudioStreamType(AudioManager.STREAM_MUSIC);

        kontakt = (Button) findViewById(R.id.przycisk_01);
        kontakt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                otworzSpinner();
            }
        });

        ringtone = (Button) findViewById(R.id.przycisk_02);
        ringtone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                otworzRadio();
            }
        });

        przycisk_pauzastop.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(przycisk_pauzastop.isPlaying()){
                    przycisk_pauzastop.stop();
                }
                else{
                    przycisk_pauzastop.reset();
                    try {
                        przycisk_pauzastop.setDataSource(getApplicationContext(), tab_dzwieki[aktualny_dzwonek]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    przycisk_pauzastop.prepareAsync();
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        int[] tabela_avatarow = {
                R.drawable.hhhhh1,
                R.drawable.hhhhh2,
                R.drawable.hhhhh3,
                R.drawable.hhhhh4,
                R.drawable.hhhhh5};

        if (resultCode == RESULT_OK) {
            if(requestCode == 1)
            {
                muzyka_tmp = data.getStringExtra("radioButton");
                switch(muzyka_tmp){
                    case "Sound_0":
                        aktualny_dzwonek = 0;
                        break;
                    case "Sound_1":
                        aktualny_dzwonek = 1;
                        break;
                    case "Sound_2":
                        aktualny_dzwonek = 2;
                        break;
                    case "Sound_3":
                        aktualny_dzwonek = 3;
                        break;
                    case "Sound_4":
                        aktualny_dzwonek = 4;
                        break;
                }

            }
            else if(requestCode == 2){
                kontakt_tmp = data.getStringExtra("spin1");
                textView.setText(kontakt_tmp);

                switch(kontakt_tmp){
                    case "Adam Małysz":
                        avatar.setImageResource(tabela_avatarow[0]);
                        break;
                    case "Justyna Steczkowska":
                        avatar.setImageResource(tabela_avatarow[1]);
                        break;
                    case "Agnieszka Radwańska":
                        avatar.setImageResource(tabela_avatarow[2]);
                        break;
                    case "JayZ":
                        avatar.setImageResource(tabela_avatarow[3]);
                        break;
                    case "Frank Lampard":
                        avatar.setImageResource(tabela_avatarow[4]);
                        break;
                }
            }
        }
        }

    public void otworzRadio(){
        Intent intent = new Intent(this, radioActivity.class);
        intent.putExtra("temp", aktualny_dzwonek);
        startActivityForResult(intent, 1);
    }

    public void otworzSpinner(){
        Intent intent = new Intent(this, spinnerActivity.class);
        intent.putExtra("temp2", kontakt_tmp);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onPause() {
        super.onPause();
        przycisk_pauzastop.pause();
    }

}