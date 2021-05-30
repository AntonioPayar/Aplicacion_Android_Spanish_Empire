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
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class Tutorial extends AppCompatActivity {

    private View decorView;
    private static MediaPlayer media;
    private static PanelDeControl pdc;
    private static int time;
    private int sec;
    private static int diapositiva;
    private TextView txtTutorial;
    private String cadena;
    private Thread t;
    private static int contador = 0;

    private ImageButton botonHaciaAtras;
    private ImageButton imagenVolverAtras;
    private ImageButton pasarPic;
    private ImageButton comenzarPartida;
    private ImageView imagenDiapositiva;
    private ImageView imagenIlloJuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        String cadena = "!Saludos grumete¡ Bienvenido a SyberPun, en este escueto tutorial te enseñaré poco las funcionalidades de este juego. ¡Comenzemos!";

        ImageView iv = (ImageView)findViewById(R.id.fondoTutorial);
        Glide.with(getApplicationContext()).load(R.drawable.mar).into(iv);

        diapositiva = -1;

        imagenIlloJuan = (ImageView)findViewById(R.id.imageView13);
        txtTutorial = (TextView)findViewById(R.id.textoTutorial);
//        txtTutorial.setText(cadena);
        imagenVolverAtras = (ImageButton)findViewById(R.id.volverAtrasPic2);
        imagenDiapositiva = findViewById(R.id.imageTuto);
        comenzarPartida = (ImageButton)findViewById(R.id.comenzarPartida);
        botonHaciaAtras = (ImageButton)findViewById(R.id.volverAtrasPic);
        pasarPic = (ImageButton)findViewById(R.id.pasarPic);
        pasarPic.setVisibility(View.INVISIBLE);
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
        Glide.with(getApplicationContext()).load(R.drawable.tutos10).into(imagenDiapositiva);
        Glide.with(getApplicationContext()).load(R.drawable.illojuangif).into(imagenIlloJuan);
        imagenDiapositiva.setBackgroundResource(R.drawable.oceano);
        mostrarTexto(cadena);
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

        t = new Thread() {
            public void run() {

                if (diapositiva == -1) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (int i = 0; i < txt.length(); i++) {
                    contador++;
                    cadena += String.valueOf(txt.charAt(i));
                    txtTutorial.setText(cadena);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        if (diapositiva == 0 || diapositiva == -1) {
                            pasarPic.setVisibility(View.VISIBLE);
                        } else if (diapositiva == 21) {
                            botonHaciaAtras.setVisibility(View.VISIBLE);
                            comenzarPartida.setVisibility(View.VISIBLE);
                        } else {
                            botonHaciaAtras.setVisibility(View.VISIBLE);
                            pasarPic.setVisibility(View.VISIBLE);
                        }

                        Glide.with(getApplicationContext()).load(R.drawable.illojuan_ii).into(imagenIlloJuan);
                    }
                });
            }
        };

        t.start();
        contador = 0;
    }

    public void aplicarDiapositiva()  {

        String cadena = "";

        botonHaciaAtras.setVisibility(View.INVISIBLE);
        pasarPic.setVisibility(View.INVISIBLE);

        switch(diapositiva){
            case 0:
                cadena = "!Saludos grumete¡ Bienvenido a SyberPun, en este escueto tutorial te enseñaré las funcionalidades de esta aplicación. ¡Comenzemos!";
                imagenDiapositiva.setBackgroundResource(R.drawable.oceano);
                txtTutorial.setText(cadena);
                Glide.with(getApplicationContext()).load(R.drawable.illojuangif).into(imagenIlloJuan);
                botonHaciaAtras.setVisibility(View.INVISIBLE);
                mostrarTexto(cadena);
                break;
            case 1:
                cadena = "Este es el menu principal, Antes de empezar tu aventura tendras que introducir un nombre de usuario";
                Glide.with(getApplicationContext()).load(R.drawable.tutos10).into(imagenDiapositiva);
                txtTutorial.setText(cadena);
                Glide.with(getApplicationContext()).load(R.drawable.illojuangif).into(imagenIlloJuan);
                mostrarTexto(cadena);
                break;
            case 2:
                cadena = "Una vez comenzada nuestra aventura todo te parecera nuevo y un tanto complejo, Pero ya veras que con mi compañia esta aventura sera de lo mas amena";
                Glide.with(getApplicationContext()).load(R.drawable.tutos15).into(imagenDiapositiva);
                txtTutorial.setText(cadena);
                Glide.with(getApplicationContext()).load(R.drawable.illojuangif).into(imagenIlloJuan);
                mostrarTexto(cadena);
                break;
            case 3:
                cadena = "Nuestro principal objetivo como gestores de las Cortes de Indias es conocer lo que Producen nuetras Regiones y sobre todo lo que Demandan.";
                Glide.with(getApplicationContext()).load(R.drawable.tutos15).into(imagenDiapositiva);
                txtTutorial.setText(cadena);
                Glide.with(getApplicationContext()).load(R.drawable.illojuangif).into(imagenIlloJuan);
                mostrarTexto(cadena);
                break;
            case 4:
                cadena = "Si presionas sobre alguna region del mapa te mostrara los productos que se poducen en esa determinada zona, usa el boton de CAMBIO para cambiar de continente";
                Glide.with(getApplicationContext()).load(R.drawable.tutos16).into(imagenDiapositiva);
                txtTutorial.setText(cadena);
                Glide.with(getApplicationContext()).load(R.drawable.illojuangif).into(imagenIlloJuan);
                mostrarTexto(cadena);
                break;
            case 5:
                cadena = "Una vez sabemos las producciones que generan cada zona. Presiona sobre el boton de DEMANDAS";
                Glide.with(getApplicationContext()).load(R.drawable.tutos14).into(imagenDiapositiva);
                txtTutorial.setText(cadena);
                Glide.with(getApplicationContext()).load(R.drawable.illojuangif).into(imagenIlloJuan);
                mostrarTexto(cadena);
                break;
            case 6:
                cadena=" En esta ventana te mostrara los productos que requieren nuestas regiones, pero ¡Cuidado! satifacelas todas o caeran en Sublevacion";
                Glide.with(getApplicationContext()).load(R.drawable.tutos14).into(imagenDiapositiva);
                txtTutorial.setText(cadena);
                Glide.with(getApplicationContext()).load(R.drawable.illojuangif).into(imagenIlloJuan);
                mostrarTexto(cadena);
                break;
            case 7:
                cadena = "Para crear una mercancia presionamos el boton de CREAR MERCANCIAS este nos desplegara un glosario ";
                Glide.with(getApplicationContext()).load(R.drawable.tutos12).into(imagenDiapositiva);
                txtTutorial.setText(cadena);
                Glide.with(getApplicationContext()).load(R.drawable.illojuangif).into(imagenIlloJuan);
                mostrarTexto(cadena);
                break;
            case 8:
                cadena="En el que podremos seleccionar la cantidad y productos y simplemente dando a su boton especifico se nos creara una mercancia con esas propiedades";
                Glide.with(getApplicationContext()).load(R.drawable.tutos4).into(imagenDiapositiva);
                txtTutorial.setText(cadena);
                Glide.with(getApplicationContext()).load(R.drawable.illojuangif).into(imagenIlloJuan);
                mostrarTexto(cadena);
                break;
            case 9:
                cadena = "Una vez creada nuestra Mercancia tenemos que proceder a Embarcarla ";
                Glide.with(getApplicationContext()).load(R.drawable.tutos3).into(imagenDiapositiva);
                txtTutorial.setText(cadena);
                Glide.with(getApplicationContext()).load(R.drawable.illojuangif).into(imagenIlloJuan);
                mostrarTexto(cadena);
                break;
            case 10:
                cadena="Para ello pulsaremos sobre el boton de CREAR FLOTA. Y seleccionamos las mercancias que queremos embarcar";
                Glide.with(getApplicationContext()).load(R.drawable.tutos7).into(imagenDiapositiva);
                txtTutorial.setText(cadena);
                Glide.with(getApplicationContext()).load(R.drawable.illojuangif).into(imagenIlloJuan);
                mostrarTexto(cadena);
                break;
            case 11:
                cadena = "Una vez Embarcadas todas las mercancias solo queda enviarlas a nuestro destino.";
                Glide.with(getApplicationContext()).load(R.drawable.tutos2).into(imagenDiapositiva);
                txtTutorial.setText(cadena);
                Glide.with(getApplicationContext()).load(R.drawable.illojuangif).into(imagenIlloJuan);
                mostrarTexto(cadena);
                break;
            case 12:
                cadena=" Para ello presiona sobre el boton ENVIAR FLOTAS y seleccionamos un destino al que queremos enviar dicha flota";
                Glide.with(getApplicationContext()).load(R.drawable.tutos6).into(imagenDiapositiva);
                txtTutorial.setText(cadena);
                Glide.with(getApplicationContext()).load(R.drawable.illojuangif).into(imagenIlloJuan);
                mostrarTexto(cadena);
                break;
            case 13:
                cadena = "Para confirmar todos los envios solo queda pulsar SIGUIENTE TURNO ";
                Glide.with(getApplicationContext()).load(R.drawable.tutos13).into(imagenDiapositiva);
                txtTutorial.setText(cadena);
                Glide.with(getApplicationContext()).load(R.drawable.illojuangif).into(imagenIlloJuan);
                mostrarTexto(cadena);
                break;
            case 14:
                cadena=" Recuerda presionar sobre las mismas flotas enviadas.Tienes que marcar su regreso o si no el siguiente turno no estaran disponibles";
                Glide.with(getApplicationContext()).load(R.drawable.tutos5).into(imagenDiapositiva);
                txtTutorial.setText(cadena);
                Glide.with(getApplicationContext()).load(R.drawable.illojuangif).into(imagenIlloJuan);
                mostrarTexto(cadena);
                break;
            case 15:
                cadena = "Genial ya has pasado de turno. Recuerda satisfacer todas las demandas o los paises caeran en Sublevacion.";
                Glide.with(getApplicationContext()).load(R.drawable.tutos9).into(imagenDiapositiva);
                txtTutorial.setText(cadena);
                Glide.with(getApplicationContext()).load(R.drawable.illojuangif).into(imagenIlloJuan);
                mostrarTexto(cadena);
                break;
            case 16:
                cadena = "Una vez TODOS tus reinos se sublevan habras perdido. El objetivo del juego es mantener el mayor nuemero de turnos tu Imperio";
                Glide.with(getApplicationContext()).load(R.drawable.tutos9).into(imagenDiapositiva);
                txtTutorial.setText(cadena);
                Glide.with(getApplicationContext()).load(R.drawable.illojuangif).into(imagenIlloJuan);
                mostrarTexto(cadena);
                break;
            case 17:
                cadena = "Una vez terminada tu partida puedes ver todas las interacciones que has hecho en tu partida gracias a la base de datos";
                Glide.with(getApplicationContext()).load(R.drawable.tutos11).into(imagenDiapositiva);
                txtTutorial.setText(cadena);
                Glide.with(getApplicationContext()).load(R.drawable.illojuangif).into(imagenIlloJuan);
                mostrarTexto(cadena);
                break;
            case 18:
                cadena = "Solo tienes que seleccionar el nombre de usuario que has introducido al iniciar tu partida y la seccion que quieres ojear";
                Glide.with(getApplicationContext()).load(R.drawable.tutos1).into(imagenDiapositiva);
                txtTutorial.setText(cadena);
                Glide.with(getApplicationContext()).load(R.drawable.illojuangif).into(imagenIlloJuan);
                mostrarTexto(cadena);
                break;
            case 19:
                cadena = "Una vez dentro puedes revisar hasta el ultimo apice de tus interacciones";
                Glide.with(getApplicationContext()).load(R.drawable.tutos8).into(imagenDiapositiva);
                txtTutorial.setText(cadena);
                Glide.with(getApplicationContext()).load(R.drawable.illojuangif).into(imagenIlloJuan);
                mostrarTexto(cadena);
                break;
            case 20:
                cadena = "Bueno espero que todo este claro.";
                Glide.with(getApplicationContext()).load(R.drawable.tutos10).into(imagenDiapositiva);
                txtTutorial.setText(cadena);
                pasarPic.setVisibility(View.VISIBLE);
                Glide.with(getApplicationContext()).load(R.drawable.illojuangif).into(imagenIlloJuan);
                mostrarTexto(cadena);
                break;
            case 21:
                cadena = "Te deseo una feliz experiencia y nos veremos en proximas aventuras.¡Hasta la vista marinero!";
                Glide.with(getApplicationContext()).load(R.drawable.tutos10).into(imagenDiapositiva);
                txtTutorial.setText(cadena);
                pasarPic.setVisibility(View.INVISIBLE);
                comenzarPartida.setVisibility(View.INVISIBLE);
                Glide.with(getApplicationContext()).load(R.drawable.illojuangif).into(imagenIlloJuan);
                mostrarTexto(cadena);
                break;
        }
    }

//    public void comenzarPartida(View vista){
//        Intent i = new Intent(this, Juego.class);
//        startActivity(i);
//        overridePendingTransition(R.anim.partida_in, R.anim.partida_out);
//        this.finish();
//    }

    public void volverAtras(View vista){

        onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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
