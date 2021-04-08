package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.Clases.ProductoNombre;

public class CrearMercanciasCastilla extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    private View decorView;
    private MediaPlayer media;
    private PanelDeControl control;
    public static int time;
    private int contador = 0;

    private TextView trigo;
    private TextView uvas;
    private TextView hierro;

    private SeekBar trigo_seekbar;
    private SeekBar uvas_seekbar;
    private SeekBar hierro_seekbar;

    private TextView trigo_kg;
    private TextView uvas_kg;
    private TextView hierro_kg;

    private Button botonTrigo;
    private Button botonUvas;
    private Button botonHierro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_mercancias_castilla);

        control = Juego.getPanelDeControl();
        time = getIntent().getIntExtra("segundosMerc", 4);

        trigo = (TextView)findViewById(R.id.trigoCastilla_txtv);
        uvas = (TextView)findViewById(R.id.uvasCastilla_txtv);
        hierro = (TextView)findViewById(R.id.hierroCastilla_txtv);

        trigo_seekbar = (SeekBar)findViewById(R.id.seekBar_trigoCastilla);
        uvas_seekbar = (SeekBar)findViewById(R.id.seekBar_uvasCastilla);
        hierro_seekbar = (SeekBar)findViewById(R.id.seekBar_hierroCastilla);

        trigo_kg = (TextView)findViewById(R.id.trigo_kilosCastilla);
        uvas_kg = (TextView)findViewById(R.id.uvas_kilosCastilla);
        hierro_kg = (TextView)findViewById(R.id.hierro_kilosCastilla);

        botonTrigo = (Button)findViewById(R.id.boton_trigoCastilla);
        botonUvas = (Button)findViewById(R.id.boton_uvasCastilla);
        botonHierro = (Button)findViewById(R.id.boton_hierroCastilla);

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
        botonTrigo.setOnClickListener(this);
        botonUvas.setOnClickListener(this);
        botonHierro.setOnClickListener(this);
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

    /*public int devolverSegundos(){

        int segundos = 0;

        segundos = media.getCurrentPosition();
        return segundos;
    }*/

    public void mostrarCantidadProductos() {

        //trigo ------ x Kg
        trigo.setText(control.getEspana().getCastilla().getRecoleccionTrigo().getNombre()+" "+control.getEspana().getCastilla().getRecoleccionTrigo().getCantidad()+" kg");
        //Uvas ------ x Kg
        uvas.setText(control.getEspana().getCastilla().getRecoleccionUvas().getNombre()+" "+control.getEspana().getCastilla().getRecoleccionUvas().getCantidad()+" kg");
        //Hierro ------ x Kg
        hierro.setText(control.getEspana().getCastilla().getRecoleccionHierro().getNombre()+" "+control.getEspana().getCastilla().getRecoleccionHierro().getCantidad()+" kg");
    }

    /**
     * Método que se encargar de inicializar los deslizadores y que a la hora de seleccionar un valor se reproduzca en la caja de texto
     * mediante el método onProgressChanged
     */

    public void colocarSeekBars() {

        //		slider1.contains(0, 1000);
        trigo_seekbar.setMax(control.getEspana().getCastilla().getRecoleccionTrigo().getCantidad());
        //		slider2.contains(0, 1000);
        uvas_seekbar.setMax(control.getEspana().getCastilla().getRecoleccionUvas().getCantidad());
        //		slider3.contains(0, 1000);
        hierro_seekbar.setMax(control.getEspana().getCastilla().getRecoleccionHierro().getCantidad());

        trigo_seekbar.setOnSeekBarChangeListener(this);
        uvas_seekbar.setOnSeekBarChangeListener(this);
        hierro_seekbar.setOnSeekBarChangeListener(this);


    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if(seekBar.getId() == trigo_seekbar.getId()){
            trigo_kg.setText(""+progress);
        }else if(seekBar.getId() == uvas_seekbar.getId()){
            uvas_kg.setText(""+progress);
        }else{
            hierro_kg.setText(""+progress);
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

        if(v.getId() == botonTrigo.getId()) {

            try {
                if (!trigo_kg.getText().equals("0") && !trigo_kg.getText().equals("")) {
                    if (Integer.parseInt(String.valueOf(trigo_kg.getText())) > (control.getEspana().getCastilla().getRecoleccionTrigo().getCantidad() * 50) / 100) {
                        control.crearMercancias(control.getEspana().getCastilla(), Integer.parseInt(String.valueOf(trigo_kg.getText())), ProductoNombre.Trigo);
                        trigo.setText(control.getEspana().getCastilla().getRecoleccionTrigo().getNombre() + " " + control.getEspana().getCastilla().getRecoleccionTrigo().getCantidad() + " kg");
                        trigo_seekbar.setMax(control.getEspana().getCastilla().getRecoleccionTrigo().getCantidad());
                        Toast.makeText(this, "Mercancia de Trigo creada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, " Tiene que crear una mercancia superior a " + (control.getEspana().getCastilla().getRecoleccionTrigo().getCantidad() * 50)/100, Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(this, "Debe introducir una cantidad para crear una mercancía", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }else if(v.getId() == botonUvas.getId()){

            try {
                if(!uvas_kg.getText().equals("0") && !uvas_kg.getText().equals("")) {
                    if(Integer.parseInt(String.valueOf(uvas_kg.getText()))>(control.getEspana().getCastilla().getRecoleccionUvas().getCantidad()*50)/100) {
                        control.crearMercancias(control.getEspana().getCastilla(), Integer.parseInt(String.valueOf(uvas_kg.getText())), ProductoNombre.Uvas);
                        uvas.setText(control.getEspana().getCastilla().getRecoleccionUvas().getNombre()+" "+control.getEspana().getCastilla().getRecoleccionUvas().getCantidad()+" kg");
                        uvas_seekbar.setMax(control.getEspana().getCastilla().getRecoleccionUvas().getCantidad());
                        Toast.makeText(this, "Mercancia de Uvas creada", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, "Tiene que crear una mercancia superior a "+(control.getEspana().getCastilla().getRecoleccionUvas().getCantidad()*50)/100, Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(this, "Debe introducir una cantidad para crear una mercancía", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }else{

            try {
                if(!hierro_kg.getText().equals("0") && !hierro_kg.getText().equals("")) {
                    if(Integer.parseInt(String.valueOf(hierro_kg.getText()))>(control.getEspana().getCastilla().getRecoleccionHierro().getCantidad()*50)/100) {
                        control.crearMercancias(control.getEspana().getCastilla(), Integer.parseInt(String.valueOf(hierro_kg.getText())), ProductoNombre.Hierro);
                        hierro.setText(control.getEspana().getCastilla().getRecoleccionHierro().getNombre()+" "+control.getEspana().getCastilla().getRecoleccionHierro().getCantidad()+" kg");
                        hierro_seekbar.setMax(control.getEspana().getCastilla().getRecoleccionHierro().getCantidad());
                        Toast.makeText(this, "Mercancia de Hierro creada", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this," Tiene que crear una mercancia superior a "+(control.getEspana().getCastilla().getRecoleccionHierro().getCantidad()*50)/100, Toast.LENGTH_LONG).show();
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