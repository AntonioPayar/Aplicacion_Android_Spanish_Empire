package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Tutorial extends AppCompatActivity {

    private View decorView;
    private static MediaPlayer media;
    private static PanelDeControl pdc;
    private static int time;
    private int sec;
    private static int diapositiva = -1;
    private TextView txtTutorial;
    private String cadena;
    private Thread t;
    private static int contador = 0;

    private ImageButton botonHaciaAtras;
    private ImageButton pasarPic;
    private ImageButton comenzarPartida;
    private ConstraintLayout imagenDiapositiva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        String cadena = "!Saludos viajero¡ Bienvenido a SyberPun, en este sencillo tutorial te enseñaré poco a poco como es el juego. ¡Comenzemos!";

        txtTutorial = (TextView)findViewById(R.id.textoTutorial);
        txtTutorial.setText(cadena);
        imagenDiapositiva = (ConstraintLayout)findViewById(R.id.imagenTutorial);
        comenzarPartida = (ImageButton)findViewById(R.id.comenzarPartida);
        botonHaciaAtras = (ImageButton)findViewById(R.id.volverAtrasPic);
        pasarPic = (ImageButton)findViewById(R.id.pasarPic);
        botonHaciaAtras.setVisibility(View.INVISIBLE);
        comenzarPartida.setVisibility(View.INVISIBLE);

        sec = 0;
        media = MediaPlayer.create(this, R.raw.partida);
        media.setVolume(10, 10);
        media.setLooping(true);
        media.seekTo(sec);
        media.start();

        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility == 0){
                    decorView.setSystemUiVisibility(hideSystemBars());
                }
            }
        });

//        ThreadTexto t = new ThreadTexto(cadena, txtTutorial, diapositiva);
//        t.run();
//        mostrarTexto(cadena);
    }

    public void pasarDiapositiva(View vista){
        if(diapositiva == -1){
            diapositiva = 1;
        }else {
            diapositiva++;
        }
        aplicarDiapositiva();
    }

    public void volverDiapositivaAnterior(View vista){
        diapositiva--;
        aplicarDiapositiva();
    }

    public void mostrarTexto(String txt) {

        cadena = "";
        txtTutorial.setText("");

        if(contador != 0){
            t.interrupt();
//            try {
//                t.join();
//            }catch(InterruptedException e){
//                e.printStackTrace();
//            }
        }


        t = new Thread(){

            public void run() {

                if(diapositiva == -1){
                    try {
                        Thread.sleep(3000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }

                for(int i = 0; i<txt.length(); i++){
                    contador++;
                    cadena += String.valueOf(txt.charAt(i));
                    txtTutorial.setText(cadena);
                    try {
                        Thread.sleep(100);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };

        t.start();
        contador = 0;

//        for(int i = 0; i<txt.length(); i++){
//            cadena += String.valueOf(txt.charAt(i));
//            txtTutorial.setText(cadena);
//            try {
//                Thread.sleep(100);
//            }catch(InterruptedException e){
//                e.printStackTrace();
//            }
//        }
    }

    public void aplicarDiapositiva()  {

        String cadena = "";
//        if(contador != 0){
//            t.interrupt();
////            try {
////                t.join();
////            }catch(InterruptedException e){
////                e.printStackTrace();
////            }
//        }

        switch(diapositiva){
            case 0:
                cadena = "!Saludos viajero¡ Bienvenido a SyberPun, en este sencillo tutorial te enseñaré poco a poco como es el juego. ¡Comenzemos!";
                imagenDiapositiva.setBackgroundResource(R.drawable.continentes_por_defecto);
                botonHaciaAtras.setVisibility(View.INVISIBLE);
                txtTutorial.setText(cadena);
//                t = new ThreadTexto(cadena, txtTutorial, diapositiva);
//                t.run();
//                mostrarTexto(cadena);
                break;
            case 1:
                cadena = "Y esto es otro ejemplo un poco mas largo pero bueno, arriba españa";
                imagenDiapositiva.setBackgroundResource(R.drawable.felipe_iv);
                botonHaciaAtras.setVisibility(View.VISIBLE);
                txtTutorial.setText(cadena);
//                t = new ThreadTexto(cadena, txtTutorial, diapositiva);
//                t.run();
//                mostrarTexto(cadena);
                break;
            case 2:
                imagenDiapositiva.setBackgroundResource(R.drawable.barconav);
                break;
            case 3:
                imagenDiapositiva.setBackgroundResource(R.drawable.botonhasburgo);
                break;
            case 4:
                imagenDiapositiva.setBackgroundResource(R.drawable.peru_to_nueva_espana);
                break;
            case 5:
                imagenDiapositiva.setBackgroundResource(R.drawable.botonchile);
                break;
            case 6:
                imagenDiapositiva.setBackgroundResource(R.drawable.peru_to_nueva_espana);
                break;
            case 7:
                imagenDiapositiva.setBackgroundResource(R.drawable.continentes_por_defecto);
                break;
            case 8:
                imagenDiapositiva.setBackgroundResource(R.drawable.splash);
                break;
            case 9:
                imagenDiapositiva.setBackgroundResource(R.drawable.botonchile);
                pasarPic.setVisibility(View.VISIBLE);
                comenzarPartida.setVisibility(View.INVISIBLE);
                break;
            case 10:
                pasarPic.setVisibility(View.INVISIBLE);
                comenzarPartida.setVisibility(View.VISIBLE);
                imagenDiapositiva.setBackgroundResource(R.drawable.fragmenamerica01);
                break;
        }
    }

    public void comenzarPartida(View vista){
        Intent i = new Intent(this, Juego.class);
        startActivity(i);
        overridePendingTransition(R.anim.partida_in, R.anim.partida_out);
        this.finish();
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

//        time += CrearMercanciasCastilla.time - time;

        if(!media.isPlaying() && media != null){
            media = MediaPlayer.create(this, R.raw.partida);
            Log.i("a", ""+time);
            media.setVolume(10, 10);
            media.setLooping(true);
            media.seekTo(time);
            media.start();
        }

    }

}
