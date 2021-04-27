package com.example.proyecto.Mercancias;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.Clases.ProductoNombre;
import com.example.proyecto.Juego;
import com.example.proyecto.PanelDeControl;
import com.example.proyecto.R;

public class CrearMercanciasNuevaEspana extends AppCompatActivity implements MercanciasInterfaz {

    private View decorView;
    private MediaPlayer media;
    private PanelDeControl control;
    public static int time;
    private int contador = 0;

    private TextView oro;
    private TextView maiz;
    private TextView tomate;
    private TextView trigo;

    private SeekBar oro_seekbar;
    private SeekBar maiz_seekbar;
    private SeekBar tomate_seekbar;
    private SeekBar trigo_seekbar;

    private TextView oro_kg;
    private TextView maiz_kg;
    private TextView tomate_kg;
    private TextView trigo_kg;

    private Button botonOro;
    private Button botonMaiz;
    private Button botonTomates;
    private Button botonTrigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_mercancias_nueva_espana);

        control = Juego.getPanelDeControl();
        time = getIntent().getIntExtra("segundosMerc", 4);

        oro = (TextView)findViewById(R.id.oroNuevaEspana_txtv);
        maiz = (TextView)findViewById(R.id.maizNuevaEspana_txtv);
        tomate = (TextView)findViewById(R.id.tomatesNuevaEspana_txtv);
        trigo = (TextView)findViewById(R.id.trigoNuevaEspana_txtv);

        oro_seekbar = (SeekBar)findViewById(R.id.seekBar_oroNuevaEspana);
        maiz_seekbar = (SeekBar)findViewById(R.id.seekBar_maizNuevaEspana);
        tomate_seekbar = (SeekBar)findViewById(R.id.seekBar_tomatesNuevaEspana);
        trigo_seekbar = (SeekBar)findViewById(R.id.seekBar_TrigoNuevaEspana);

        oro_kg = (TextView)findViewById(R.id.oro_kilosNuevaEspana);
        maiz_kg = (TextView)findViewById(R.id.maiz_kilosNuevaEspana);
        tomate_kg = (TextView)findViewById(R.id.tomates_kilosNuevaEspana);
        trigo_kg = (TextView)findViewById(R.id.trigo_kilosNuevaEspana);


        botonOro = (Button)findViewById(R.id.boton_oroNuevaEspana);
        botonMaiz = (Button)findViewById(R.id.boton_maizNuevaEspana);
        botonTomates = (Button)findViewById(R.id.boton_tomatesNuevaEspana);
        botonTrigo = (Button)findViewById(R.id.boton_trigoNuevaEspana);

        media = MediaPlayer.create(this, R.raw.partida);
        media.setVolume(10, 10);
        media.setLooping(true);
        media.seekTo(time);
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

        mostrarCantidadProductos();
        colocarSeekBars();
        botonOro.setOnClickListener(this);
        botonMaiz.setOnClickListener(this);
        botonTomates.setOnClickListener(this);
        botonTrigo.setOnClickListener(this);
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
            media = MediaPlayer.create(this, R.raw.partida);
            Log.i("a", ""+time);
            media.setVolume(10, 10);
            media.setLooping(true);
            media.seekTo(time);
            media.start();
        }

    }

    @Override
    public void onBackPressed(){

        Juego.setMedia(media.getCurrentPosition());
        overridePendingTransition(R.anim.entrada, R.anim.salida);
        this.finish();
    }

    public void volverAtras(View vista){

        Juego.setMedia(media.getCurrentPosition());
        onBackPressed();
        overridePendingTransition(R.anim.entrada, R.anim.salida);
        this.finish();
    }

    public int devolverSegundos(){

        int segundos = 0;

        segundos = media.getCurrentPosition();
        return segundos;
    }

    public void mostrarCantidadProductos() {

        oro.setText(control.getEspana().getNuevaEspana().getRecoleccionOro().getNombre()+" "+control.getEspana().getNuevaEspana().getRecoleccionOro().getCantidad()+" kg");
        maiz.setText(control.getEspana().getNuevaEspana().getRecoleccionMaiz().getNombre()+" "+control.getEspana().getNuevaEspana().getRecoleccionMaiz().getCantidad()+" kg");
        tomate.setText(control.getEspana().getNuevaEspana().getRecoleccionTomate().getNombre()+" "+control.getEspana().getNuevaEspana().getRecoleccionTomate().getCantidad()+" kg");
        trigo.setText(control.getEspana().getNuevaEspana().getRecoleccionTrigo().getNombre()+" "+control.getEspana().getNuevaEspana().getRecoleccionTrigo().getCantidad()+" kg");
    }

    /**
     * Método que se encargar de inicializar los deslizadores y que a la hora de seleccionar un valor se reproduzca en la caja de texto
     * mediante el método onProgressChanged
     */

    public void colocarSeekBars() {

        oro_seekbar.setMax(control.getEspana().getNuevaEspana().getRecoleccionOro().getCantidad());
        maiz_seekbar.setMax(control.getEspana().getNuevaEspana().getRecoleccionMaiz().getCantidad());
        tomate_seekbar.setMax(control.getEspana().getNuevaEspana().getRecoleccionTomate().getCantidad());
        trigo_seekbar.setMax(control.getEspana().getNuevaEspana().getRecoleccionTrigo().getCantidad());

        oro_seekbar.setOnSeekBarChangeListener(this);
        maiz_seekbar.setOnSeekBarChangeListener(this);
        tomate_seekbar.setOnSeekBarChangeListener(this);
        trigo_seekbar.setOnSeekBarChangeListener(this);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if(seekBar.getId() == oro_seekbar.getId()){
            oro_kg.setText(""+progress);
        }else if(seekBar.getId() == maiz_seekbar.getId()){
            maiz_kg.setText(""+progress);
        }else if(seekBar.getId() == tomate_seekbar.getId()){
            tomate_kg.setText(""+progress);
        }else{
            trigo_kg.setText(""+progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View v) {

        if(v.getId() == botonOro.getId()) {

            try {
                if (!oro_kg.getText().equals("0") && !oro_kg.getText().equals("")) {
                    if (Integer.parseInt(String.valueOf(oro_kg.getText())) > (control.getEspana().getNuevaEspana().getRecoleccionOro().getCantidad() * 50) / 100) {
                        control.crearMercancias(control.getEspana().getNuevaEspana(), Integer.parseInt(String.valueOf(oro_kg.getText())), ProductoNombre.Oro);
                        oro.setText(control.getEspana().getNuevaEspana().getRecoleccionOro().getNombre() + " " + control.getEspana().getNuevaEspana().getRecoleccionOro().getCantidad() + " kg");
                        oro_seekbar.setMax(control.getEspana().getNuevaEspana().getRecoleccionOro().getCantidad());
                        Toast.makeText(this, "Mercancia de Oro creada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, " Tiene que crear una mercancia superior a " + (control.getEspana().getNuevaEspana().getRecoleccionOro().getCantidad() * 50)/100, Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(this, "Debe introducir una cantidad para crear una mercancía", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }else if(v.getId() == botonMaiz.getId()){

            try {
                if (!maiz_kg.getText().equals("0") && !maiz_kg.getText().equals("")) {
                    if (Integer.parseInt(String.valueOf(maiz_kg.getText())) > (control.getEspana().getNuevaEspana().getRecoleccionMaiz().getCantidad() * 50) / 100) {
                        control.crearMercancias(control.getEspana().getNuevaEspana(), Integer.parseInt(String.valueOf(maiz_kg.getText())), ProductoNombre.Maiz);
                        maiz.setText(control.getEspana().getNuevaEspana().getRecoleccionMaiz().getNombre() + " " + control.getEspana().getNuevaEspana().getRecoleccionMaiz().getCantidad() + " kg");
                        maiz_seekbar.setMax(control.getEspana().getNuevaEspana().getRecoleccionMaiz().getCantidad());
                        Toast.makeText(this, "Mercancia de Maiz creada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, " Tiene que crear una mercancia superior a " + (control.getEspana().getNuevaEspana().getRecoleccionMaiz().getCantidad() * 50)/100, Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(this, "Debe introducir una cantidad para crear una mercancía", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }else if(v.getId() == botonTomates.getId()){

            try {
                if (!tomate_kg.getText().equals("0") && !tomate_kg.getText().equals("")) {
                    if (Integer.parseInt(String.valueOf(tomate_kg.getText())) > (control.getEspana().getNuevaEspana().getRecoleccionTomate().getCantidad() * 50) / 100) {
                        control.crearMercancias(control.getEspana().getNuevaEspana(), Integer.parseInt(String.valueOf(tomate_kg.getText())), ProductoNombre.Tomate);
                        tomate.setText(control.getEspana().getNuevaEspana().getRecoleccionTomate().getNombre() + " " + control.getEspana().getNuevaEspana().getRecoleccionTomate().getCantidad() + " kg");
                        tomate_seekbar.setMax(control.getEspana().getNuevaEspana().getRecoleccionTomate().getCantidad());
                        Toast.makeText(this, "Mercancia de Tomates creada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, " Tiene que crear una mercancia superior a " + (control.getEspana().getNuevaEspana().getRecoleccionTomate().getCantidad() * 50)/100, Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(this, "Debe introducir una cantidad para crear una mercancía", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }else{

            try {
                if (!trigo_kg.getText().equals("0") && !trigo_kg.getText().equals("")) {
                    if (Integer.parseInt(String.valueOf(trigo_kg.getText())) > (control.getEspana().getNuevaEspana().getRecoleccionTrigo().getCantidad() * 50) / 100) {
                        control.crearMercancias(control.getEspana().getNuevaEspana(), Integer.parseInt(String.valueOf(trigo_kg.getText())), ProductoNombre.Trigo);
                        trigo.setText(control.getEspana().getNuevaEspana().getRecoleccionTrigo().getNombre() + " " + control.getEspana().getNuevaEspana().getRecoleccionTrigo().getCantidad() + " kg");
                        trigo_seekbar.setMax(control.getEspana().getNuevaEspana().getRecoleccionTrigo().getCantidad());
                        Toast.makeText(this, "Mercancia de Trigo creada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, " Tiene que crear una mercancia superior a " + (control.getEspana().getNuevaEspana().getRecoleccionTrigo().getCantidad() * 50)/100, Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(this, "Debe introducir una cantidad para crear una mercancía", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }
    }
}