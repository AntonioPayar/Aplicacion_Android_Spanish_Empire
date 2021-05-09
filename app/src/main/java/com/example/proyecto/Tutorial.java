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
//        txtTutorial.setText(cadena);
        imagenDiapositiva = (ConstraintLayout)findViewById(R.id.imagenTutorial);
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
                        } else if (diapositiva == 24) {
                            botonHaciaAtras.setVisibility(View.VISIBLE);
                            comenzarPartida.setVisibility(View.VISIBLE);
                        } else {
                            botonHaciaAtras.setVisibility(View.VISIBLE);
                            pasarPic.setVisibility(View.VISIBLE);
                        }
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
                cadena = "!Saludos viajero¡ Bienvenido a SyberPun, en este sencillo tutorial te enseñaré poco a poco como es el juego. ¡Comenzemos!";
                imagenDiapositiva.setBackgroundResource(R.drawable.tutorial);
                txtTutorial.setText(cadena);
                mostrarTexto(cadena);
                break;
            case 1:
                cadena = "El objetivo para seguir adelante y no perder la partida es cumplir una serie de demandas en cada turno para cada Reino";
                imagenDiapositiva.setBackgroundResource(R.drawable.tutorial);
                txtTutorial.setText(cadena);
                mostrarTexto(cadena);
                break;
            case 2:
                cadena = "Por ejemplo, Castilla demanda Plata, por lo tanto tendremos que cumplir su demanda y así con los demás Reinos";
                imagenDiapositiva.setBackgroundResource(R.drawable.tutorial);
                txtTutorial.setText(cadena);
                mostrarTexto(cadena);
                break;
            case 3:
                cadena = "Esta es la interfaz del juego, os explicaré cada una de sus funciones";
                imagenDiapositiva.setBackgroundResource(R.drawable.tutorial1);
                txtTutorial.setText(cadena);
                mostrarTexto(cadena);
                break;
            case 4:
                cadena = "Como os habia dicho antes, en cada turno se demandarán una serie de recados y se os mostrará en la sección Demandas";
                imagenDiapositiva.setBackgroundResource(R.drawable.tutorial5);
                txtTutorial.setText(cadena);
                mostrarTexto(cadena);
                break;
            case 5:
                cadena = "Este es el mapa, donde podreís ver la producción de cada Reino presionando la zona del Reino que queramos ver";
                imagenDiapositiva.setBackgroundResource(R.drawable.tutorial2);
                txtTutorial.setText(cadena);
                mostrarTexto(cadena);
                break;
            case 6:
                cadena = "Por ejemplo, si queremos ver la mercancía de Austria, presionamos la zona de Austria y se mostrará";
                imagenDiapositiva.setBackgroundResource(R.drawable.tutorial4);
                txtTutorial.setText(cadena);
                mostrarTexto(cadena);
                break;
            case 7:
                cadena = "Tambien podremos alternar entre Europa y América, presionando el boton Cambio";
                imagenDiapositiva.setBackgroundResource(R.drawable.tutorial3);
                txtTutorial.setText(cadena);
                mostrarTexto(cadena);
                break;
            case 8:
                cadena = "Una vez informados, el siguiente paso es crear dichas mercancias presionando el boton Crear Mercancías";
                imagenDiapositiva.setBackgroundResource(R.drawable.tutorial6);
                txtTutorial.setText(cadena);
                mostrarTexto(cadena);
                break;
            case 9:
                cadena = "Se os mostrarán la lista de Reinos que hay en total, presionamos al Reino que queremos crear la mercancias";
                imagenDiapositiva.setBackgroundResource(R.drawable.tutorial6);
                txtTutorial.setText(cadena);
                mostrarTexto(cadena);
                break;
            case 10:
                cadena = "Una vez dentro, podremos crear las mercancias que queramos, siempre y cuando haya suficiente cantidad";
                imagenDiapositiva.setBackgroundResource(R.drawable.tutorial7);
                txtTutorial.setText(cadena);
                mostrarTexto(cadena);
                break;
            case 11:
                cadena = "Además tendremos que crear una mercancia con un mínimo de cantidad";
                imagenDiapositiva.setBackgroundResource(R.drawable.tutorial7);
                txtTutorial.setText(cadena);
                mostrarTexto(cadena);
                break;
            case 12:
                cadena = "Una vez creada las mercancias, tendremos que volver atrás y dirigirnos a la seccion Crear Flota";
                imagenDiapositiva.setBackgroundResource(R.drawable.tutorial6);
                txtTutorial.setText(cadena);
                mostrarTexto(cadena);
                break;
            case 14:
                cadena = "Por lo tanto, almacenaremos las mercancias que queremos en la flota";
                imagenDiapositiva.setBackgroundResource(R.drawable.tutorial7);
                txtTutorial.setText(cadena);
                mostrarTexto(cadena);
                break;
            case 15:
                cadena = "Os saldrá un aviso, asegurando de si quereis almacenar esa mercancia en la flota";
                imagenDiapositiva.setBackgroundResource(R.drawable.tutorial9);
                txtTutorial.setText(cadena);
                mostrarTexto(cadena);
                break;
            case 16:
                cadena = "Ahora, nos encargaremos de enviar la flota al Reino que demanda las mercancias creadas en la sección Enviar Flota";
                imagenDiapositiva.setBackgroundResource(R.drawable.tutorial6);
                txtTutorial.setText(cadena);
                mostrarTexto(cadena);
                break;
            case 17:
                cadena = "Aquí, se os mostrará las mercancias almacenadas y los destinos disponibles, seleccionamos un destino y presionaremos embarcar";
                imagenDiapositiva.setBackgroundResource(R.drawable.tutorial10);
                txtTutorial.setText(cadena);
                mostrarTexto(cadena);
                break;
            case 18:
                cadena = "Por último, una vez terminado las demandas, pasaremos al siguiente turno, donde a su vez podremos retornar las flotas enviadas";
                imagenDiapositiva.setBackgroundResource(R.drawable.tutorial1_1);
                txtTutorial.setText(cadena);
                mostrarTexto(cadena);
                break;
            case 19:
                cadena = "Una vez seleccionado las flotas que queremos que vuelvan, se confirmará dicha selección y pasaremos al siguiente turno con nuevas demandas";
                imagenDiapositiva.setBackgroundResource(R.drawable.tutorial12);
                txtTutorial.setText(cadena);
                mostrarTexto(cadena);
                break;
            case 20:
                cadena = "En el caso de que no se haya cumplido alguna demanda, no se podrá acceder a las funciones de dicho Reino, por lo tanto habrás perdido la amistad con ese Reino";
                imagenDiapositiva.setBackgroundResource(R.drawable.tutorial13);
                txtTutorial.setText(cadena);
                mostrarTexto(cadena);
                break;
            case 21:
                cadena = "Y en el caso de que hayas cumplido dicha demanda pero no retornado su flota, no podrás acceder tampoco a dicho Reino hasta que la retornes";
                imagenDiapositiva.setBackgroundResource(R.drawable.tutorial14);
                txtTutorial.setText(cadena);
                mostrarTexto(cadena);
                break;
            case 22:
                cadena = "Y así sucesivamente hasta perder la amistad de todos los Reinos, entonces es cuando se acabará la partida";
                imagenDiapositiva.setBackgroundResource(R.drawable.tutorial);
                txtTutorial.setText(cadena);
                mostrarTexto(cadena);
                break;
            case 23:
                cadena = "Para desactivar este tutorial vaya a las opciones del menú y desactive el tutorial";
                imagenDiapositiva.setBackgroundResource(R.drawable.tutorial15);
                txtTutorial.setText(cadena);
                comenzarPartida.setVisibility(View.INVISIBLE);
                mostrarTexto(cadena);
                break;
            case 24:
                cadena = "¡Dicho esto, mucha suerte y a disfrutar viajero!";
                imagenDiapositiva.setBackgroundResource(R.drawable.tutorial);
                txtTutorial.setText(cadena);
                mostrarTexto(cadena);
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
