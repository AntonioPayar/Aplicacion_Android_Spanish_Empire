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

public class CrearMercanciasNuevaGranada extends AppCompatActivity implements MercanciasInterfaz {

    private View decorView;
    private MediaPlayer media;
    private PanelDeControl control;
    public static int time;
    private int contador = 0;

    private TextView oro;
    private TextView plata;
    private TextView tabaco;
    private TextView cafe;

    private SeekBar oro_seekbar;
    private SeekBar plata_seekbar;
    private SeekBar tabaco_seekbar;
    private SeekBar cafe_seekbar;

    private TextView oro_kg;
    private TextView plata_kg;
    private TextView tabaco_kg;
    private TextView cafe_kg;

    private Button botonOro;
    private Button botonPlata;
    private Button botonTabaco;
    private Button botonCafe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_mercancias_nueva_granada);

        control = Juego.getPanelDeControl();
        time = getIntent().getIntExtra("segundosMerc", 4);

        oro = (TextView)findViewById(R.id.oroNuevaGranada_txtv);
        plata = (TextView)findViewById(R.id.plataNuevaGranada_txtv);
        tabaco = (TextView)findViewById(R.id.tabacoNuevaGranada_txtv);
        cafe = (TextView)findViewById(R.id.cafeNuevaGranada_txtv);

        oro_seekbar = (SeekBar)findViewById(R.id.seekBar_oroNuevaGranada);
        plata_seekbar = (SeekBar)findViewById(R.id.seekBar_plataNuevaGranada);
        tabaco_seekbar = (SeekBar)findViewById(R.id.seekBar_tabacoNuevaGranada);
        cafe_seekbar = (SeekBar)findViewById(R.id.seekBar_cafeNuevaGranada);

        oro_kg = (TextView)findViewById(R.id.oro_kilosNuevaGranada);
        plata_kg = (TextView)findViewById(R.id.plata_kilosNuevaGranada);
        tabaco_kg = (TextView)findViewById(R.id.tabaco_kilosNuevaGranada);
        cafe_kg = (TextView)findViewById(R.id.cafe_kilosNuevaGranada);


        botonOro = (Button)findViewById(R.id.boton_oroNuevaGranada);
        botonPlata = (Button)findViewById(R.id.boton_plataNuevaGranada);
        botonTabaco = (Button)findViewById(R.id.boton_tabacoNuevaGranada);
        botonCafe = (Button)findViewById(R.id.boton_cafeNuevaGranada);

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
        botonPlata.setOnClickListener(this);
        botonTabaco.setOnClickListener(this);
        botonCafe.setOnClickListener(this);
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

        oro.setText(control.getEspana().getNuevaGranda().getRecoleccionOro().getNombre()+" "+control.getEspana().getNuevaGranda().getRecoleccionOro().getCantidad()+" kg");
        plata.setText(control.getEspana().getNuevaGranda().getRecoleccionPlata().getNombre()+" "+control.getEspana().getNuevaGranda().getRecoleccionPlata().getCantidad()+" kg");
        tabaco.setText(control.getEspana().getNuevaGranda().getRecoleccionTabaco().getNombre()+" "+control.getEspana().getNuevaGranda().getRecoleccionTabaco().getCantidad()+" kg");
        cafe.setText(control.getEspana().getNuevaGranda().getRecoleccionCafe().getNombre()+" "+control.getEspana().getNuevaGranda().getRecoleccionCafe().getCantidad()+" kg");
    }

    /**
     * Método que se encargar de inicializar los deslizadores y que a la hora de seleccionar un valor se reproduzca en la caja de texto
     * mediante el método onProgressChanged
     */

    public void colocarSeekBars() {

        oro_seekbar.setMax(control.getEspana().getNuevaGranda().getRecoleccionOro().getCantidad());
        plata_seekbar.setMax(control.getEspana().getNuevaGranda().getRecoleccionPlata().getCantidad());
        tabaco_seekbar.setMax(control.getEspana().getNuevaGranda().getRecoleccionTabaco().getCantidad());
        cafe_seekbar.setMax(control.getEspana().getNuevaGranda().getRecoleccionCafe().getCantidad());

        oro_seekbar.setOnSeekBarChangeListener(this);
        plata_seekbar.setOnSeekBarChangeListener(this);
        tabaco_seekbar.setOnSeekBarChangeListener(this);
        cafe_seekbar.setOnSeekBarChangeListener(this);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if(seekBar.getId() == oro_seekbar.getId()){
            oro_kg.setText(""+progress);
        }else if(seekBar.getId() == plata_seekbar.getId()){
            plata_kg.setText(""+progress);
        }else if(seekBar.getId() == tabaco_seekbar.getId()){
            tabaco_kg.setText(""+progress);
        }else{
            cafe_kg.setText(""+progress);
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
                    if (Integer.parseInt(String.valueOf(oro_kg.getText())) > (control.getEspana().getNuevaGranda().getRecoleccionOro().getCantidad() * 50) / 100) {
                        control.crearMercancias(control.getEspana().getNuevaGranda(), Integer.parseInt(String.valueOf(oro_kg.getText())), ProductoNombre.Oro);
                        oro.setText(control.getEspana().getNuevaGranda().getRecoleccionOro().getNombre() + " " + control.getEspana().getNuevaGranda().getRecoleccionOro().getCantidad() + " kg");
                        oro_seekbar.setMax(control.getEspana().getNuevaGranda().getRecoleccionOro().getCantidad());
                        Toast.makeText(this, "Mercancia de Oro creada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, " Tiene que crear una mercancia superior a " + (control.getEspana().getNuevaGranda().getRecoleccionOro().getCantidad() * 50)/100, Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(this, "Debe introducir una cantidad para crear una mercancía", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }else if(v.getId() == botonPlata.getId()){

            try {
                if (!plata_kg.getText().equals("0") && !plata_kg.getText().equals("")) {
                    if (Integer.parseInt(String.valueOf(plata_kg.getText())) > (control.getEspana().getNuevaGranda().getRecoleccionPlata().getCantidad() * 50) / 100) {
                        control.crearMercancias(control.getEspana().getNuevaGranda(), Integer.parseInt(String.valueOf(plata_kg.getText())), ProductoNombre.Plata);
                        plata.setText(control.getEspana().getNuevaGranda().getRecoleccionPlata().getNombre() + " " + control.getEspana().getNuevaGranda().getRecoleccionPlata().getCantidad() + " kg");
                        plata_seekbar.setMax(control.getEspana().getNuevaGranda().getRecoleccionPlata().getCantidad());
                        Toast.makeText(this, "Mercancia de Maiz creada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, " Tiene que crear una mercancia superior a " + (control.getEspana().getNuevaGranda().getRecoleccionPlata().getCantidad() * 50)/100, Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(this, "Debe introducir una cantidad para crear una mercancía", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }else if(v.getId() == botonTabaco.getId()){

            try {
                if (!tabaco_kg.getText().equals("0") && !tabaco_kg.getText().equals("")) {
                    if (Integer.parseInt(String.valueOf(tabaco_kg.getText())) > (control.getEspana().getNuevaGranda().getRecoleccionTabaco().getCantidad() * 50) / 100) {
                        control.crearMercancias(control.getEspana().getNuevaGranda(), Integer.parseInt(String.valueOf(tabaco_kg.getText())), ProductoNombre.Tabaco);
                        tabaco.setText(control.getEspana().getNuevaGranda().getRecoleccionTabaco().getNombre() + " " + control.getEspana().getNuevaGranda().getRecoleccionTabaco().getCantidad() + " kg");
                        tabaco_seekbar.setMax(control.getEspana().getNuevaGranda().getRecoleccionTabaco().getCantidad());
                        Toast.makeText(this, "Mercancia de Tabaco creada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, " Tiene que crear una mercancia superior a " + (control.getEspana().getNuevaGranda().getRecoleccionTabaco().getCantidad() * 50)/100, Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(this, "Debe introducir una cantidad para crear una mercancía", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }else{

            try {
                if (!cafe_kg.getText().equals("0") && !cafe_kg.getText().equals("")) {
                    if (Integer.parseInt(String.valueOf(cafe_kg.getText())) > (control.getEspana().getNuevaGranda().getRecoleccionCafe().getCantidad() * 50) / 100) {
                        control.crearMercancias(control.getEspana().getNuevaGranda(), Integer.parseInt(String.valueOf(cafe_kg.getText())), ProductoNombre.Cafe);
                        cafe.setText(control.getEspana().getNuevaGranda().getRecoleccionCafe().getNombre() + " " + control.getEspana().getNuevaGranda().getRecoleccionCafe().getCantidad() + " kg");
                        cafe_seekbar.setMax(control.getEspana().getNuevaGranda().getRecoleccionCafe().getCantidad());
                        Toast.makeText(this, "Mercancia de Café creada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, " Tiene que crear una mercancia superior a " + (control.getEspana().getNuevaGranda().getRecoleccionCafe().getCantidad() * 50)/100, Toast.LENGTH_LONG).show();
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