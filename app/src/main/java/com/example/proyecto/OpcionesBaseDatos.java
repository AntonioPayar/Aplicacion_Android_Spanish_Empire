package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.proyecto.Base.BaseDatos;

import java.util.ArrayList;
import java.util.List;

public class OpcionesBaseDatos extends AppCompatActivity {

    private View decorView;
    private Button volver;
    private MediaPlayer media2;
    private int time;
    private ListView listaPartidas;
    private CustomListPartidas adaptador;
    private BaseDatos database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_base_datos);
        time = getIntent().getIntExtra("segundos", 4);
        media2 = MediaPlayer.create(this, R.raw.menu_theme);
        media2.setVolume(10, 10);
        media2.setLooping(true);
        media2.seekTo(time);
        media2.start();

        this.listaPartidas=findViewById(R.id.list_partidas);
        database= new BaseDatos(this);
        List<String> lista =database.selectPartidas();
        if(lista.size()==0){
            lista=new ArrayList<>();
            lista.add("No hay ninguna partida de momento");
        }
        System.out.println(lista.size());
        this.adaptador = new CustomListPartidas(this,R.layout.row_partidas,lista);
        this.listaPartidas.setAdapter(this.adaptador);

        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility == 0){
                    decorView.setSystemUiVisibility(hideSystemBars());
                }
            }
        });
    }

    public void atras(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
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
}