package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.proyecto.Base.BaseDatos;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer media;
    private static int time;
    private View decorView;
    private EditText etusuaio;

    public void abrirOpciones(View vista){

        Intent i = new Intent(this, Opciones.class);
        i.putExtra("segundos", media.getCurrentPosition());
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void abrirOpcionesBaseDatos(View vista){

        Intent i = new Intent(this, OpcionesBaseDatos.class);
        i.putExtra("segundos", media.getCurrentPosition());
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        this.etusuaio=findViewById(R.id.editTextTextPersonName);
        Glide.with(getApplicationContext()).load(R.drawable.barconav).into(imageView);

        /**Poner gif de backgroud**/
        ImageView imageView02 = (ImageView)findViewById(R.id.imageView20);
        Glide.with(getApplicationContext()).load(R.drawable.mar).into(imageView02);

        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility == 0){
                    decorView.setSystemUiVisibility(hideSystemBars());
                }
            }
        });
//        ActionBar actionBar = getActionBar();
//        actionBar.hide();

        time = getIntent().getIntExtra("segundos", 1400);

        media = MediaPlayer.create(this, R.raw.menu_theme);
        media.setVolume(10, 10);
        media.setLooping(true);
        media.seekTo(time);
        media.start();
    }

    public void comenzarPartida(View vista) throws InterruptedException {
        BaseDatos database= new BaseDatos(this);
        Intent intent;

        if(Opciones.getTutorial() == true) {
            intent = new Intent(this, Tutorial.class);
            intent.putExtra("tutorial", true);
            startActivity(intent);
            overridePendingTransition(R.anim.partida_in, R.anim.partida_out);
            this.finish();
        }else{
            if(this.etusuaio.getText().length()>2){
                if(database.recorrerNombrePartida(this.etusuaio.getText().toString())){
                    intent = new Intent(this, Juego.class);
                    Thread.sleep(2000);
                    //Intent intent = new Intent(this, Juego.class);
                    intent.putExtra("tutorial", false);
                    intent.putExtra("user",this.etusuaio.getText().toString());
                    Thread.sleep(2000);
                    startActivity(intent);
                    overridePendingTransition(R.anim.partida_in, R.anim.partida_out);
                    this.finish();
                }else{
                    Toast.makeText(this, "Cambie el nombre ese ya esta disponible", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(this, "Introduzca un nombre de usuario", Toast.LENGTH_SHORT).show();
            }
        }
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
    public void onDestroy() {
        super.onDestroy();
        if(media.isPlaying()){
            media.stop();
            media.release();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if(media != null && media.isPlaying()) {
            time = media.getCurrentPosition();
            media.stop();
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

        if(!media.isPlaying() && media != null){
            media = MediaPlayer.create(this, R.raw.menu_theme);
            Log.i("a", ""+time);
            media.setVolume(10, 10);
            media.setLooping(true);
            media.seekTo(time);
            media.start();
        }

    }

//    public int devolverSegundos(){
//
//        int segundos = 0;
//
//        segundos = media.getCurrentPosition();
//        return segundos;
//    }
}