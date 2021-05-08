package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Opciones extends AppCompatActivity{

    private View decorView;
    private Button volver;
    private MediaPlayer media2;
    private int time;
    private MainActivity a;
    private static boolean tutorialActivo = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);

        volver = (Button)findViewById(R.id.boton_salir);

        Switch tutorial = findViewById(R.id.tutorial);

        time = getIntent().getIntExtra("segundos", 4);

        a = new MainActivity();
        media2 = MediaPlayer.create(this, R.raw.menu_theme);
        media2.setVolume(10, 10);
        media2.setLooping(true);
        media2.seekTo(time);
        media2.start();

        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility == 0){
                    decorView.setSystemUiVisibility(hideSystemBars());
                }
            }
        });

        tutorial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    tutorialActivo = true;
                }else{
                    tutorialActivo = false;
                }
            }
        });
    }



    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            decorView.setSystemUiVisibility(hideSystemBars());
        }
    }

    public int hideSystemBars(){
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
    }

    @Override
    public void onBackPressed(){

        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("segundos", media2.getCurrentPosition());
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        this.finish();
    }

    public void volverAtras(View vista){

        onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        this.finish();
    }
    public void mensaje1(View vista){

        MediaPlayer media = new MediaPlayer();
        media = MediaPlayer.create(this, R.raw.a);
        media.setVolume(10, 10);
        media.setLooping(false);
        media.seekTo(3200);
        media.start();
    }
    public void mensaje2(View vista){

        Toast.makeText(this, "puto", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(media2.isPlaying()){
            media2.stop();
            media2.release();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if(media2 != null && media2.isPlaying()) {
            time = media2.getCurrentPosition();
            media2.stop();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility == 0){
                    decorView.setSystemUiVisibility(hideSystemBars());
                }
            }
        });

        if(!media2.isPlaying() && media2 != null){
            media2 = MediaPlayer.create(this, R.raw.menu_theme);
            media2.setVolume(10, 10);
            media2.setLooping(true);
            media2.seekTo(time);
            media2.start();
        }

    }

    public int devolverSegundos(){

        int segundos = 0;

        segundos = media2.getCurrentPosition();
        return segundos;
    }

    public static boolean getTutorial(){
        return tutorialActivo;
    }



}