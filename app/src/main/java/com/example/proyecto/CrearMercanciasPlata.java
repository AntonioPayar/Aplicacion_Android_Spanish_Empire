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

public class CrearMercanciasPlata extends AppCompatActivity implements MercanciasInterfaz {

    private View decorView;
    private MediaPlayer media;
    private PanelDeControl control;
    public static int time;
    private int contador = 0;

    private TextView plata;
    private TextView tabaco;
    private TextView cafe;
    private TextView patatas;

    private SeekBar plata_seekbar;
    private SeekBar tabaco_seekbar;
    private SeekBar cafe_seekbar;
    private SeekBar patatas_seekbar;

    private TextView plata_kg;
    private TextView tabaco_kg;
    private TextView cafe_kg;
    private TextView patatas_kg;

    private Button botonPlata;
    private Button botonTabaco;
    private Button botonCafe;
    private Button botonPatatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_mercancias_plata);

        control = Juego.getPanelDeControl();
        time = getIntent().getIntExtra("segundosMerc", 4);

        plata = (TextView)findViewById(R.id.plataPlata_txtv);
        tabaco = (TextView)findViewById(R.id.tabacoPlata_txtv);
        cafe = (TextView)findViewById(R.id.cafePlata_txtv);
        patatas = (TextView)findViewById(R.id.patatasPlata_txtv);

        plata_seekbar = (SeekBar)findViewById(R.id.seekBar_plataPlata);
        tabaco_seekbar = (SeekBar)findViewById(R.id.seekBar_tabacoPlata);
        cafe_seekbar = (SeekBar)findViewById(R.id.seekBar_cafePlata);
        patatas_seekbar = (SeekBar)findViewById(R.id.seekBar_patatasPlata);

        plata_kg = (TextView)findViewById(R.id.plata_kilosPlata);
        tabaco_kg = (TextView)findViewById(R.id.tabaco_kilosPlata);
        cafe_kg = (TextView)findViewById(R.id.cafe_kilosPlata);
        patatas_kg = (TextView)findViewById(R.id.patatas_kilosPlata);


        botonPlata = (Button)findViewById(R.id.boton_plataPlata);
        botonTabaco = (Button)findViewById(R.id.boton_tabacoPlata);
        botonCafe = (Button)findViewById(R.id.boton_cafePlata);
        botonPatatas = (Button)findViewById(R.id.boton_patatasPlata);

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
        botonPlata.setOnClickListener(this);
        botonTabaco.setOnClickListener(this);
        botonCafe.setOnClickListener(this);
        botonPatatas.setOnClickListener(this);
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

    public void mostrarCantidadProductos() {

        plata.setText(control.getEspana().getPlata().getRecoleccionPlata().getNombre()+" "+control.getEspana().getPlata().getRecoleccionPlata().getCantidad()+" kg");
        tabaco.setText(control.getEspana().getPlata().getRecoleccionTabaco().getNombre()+" "+control.getEspana().getPlata().getRecoleccionTabaco().getCantidad()+" kg");
        cafe.setText(control.getEspana().getPlata().getRecoleccionCafe().getNombre()+" "+control.getEspana().getPlata().getRecoleccionCafe().getCantidad()+" kg");
        patatas.setText(control.getEspana().getPlata().getRecoleccionPatata().getNombre()+" "+control.getEspana().getPlata().getRecoleccionPatata().getCantidad()+" kg");
    }

    /**
     * Método que se encargar de inicializar los deslizadores y que a la hora de seleccionar un valor se reproduzca en la caja de texto
     * mediante el método onProgressChanged
     */

    public void colocarSeekBars() {

        plata_seekbar.setMax(control.getEspana().getPlata().getRecoleccionPlata().getCantidad());
        tabaco_seekbar.setMax(control.getEspana().getPlata().getRecoleccionTabaco().getCantidad());
        cafe_seekbar.setMax(control.getEspana().getPlata().getRecoleccionCafe().getCantidad());
        patatas_seekbar.setMax(control.getEspana().getPlata().getRecoleccionPatata().getCantidad());

        plata_seekbar.setOnSeekBarChangeListener(this);
        tabaco_seekbar.setOnSeekBarChangeListener(this);
        cafe_seekbar.setOnSeekBarChangeListener(this);
        patatas_seekbar.setOnSeekBarChangeListener(this);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if(seekBar.getId() == plata_seekbar.getId()){
            plata_kg.setText(""+progress);
        }else if(seekBar.getId() == tabaco_seekbar.getId()){
            tabaco_kg.setText(""+progress);
        }else if(seekBar.getId() == cafe_seekbar.getId()){
            cafe_kg.setText(""+progress);
        }else{
            patatas_kg.setText(""+progress);
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

        if(v.getId() == botonPlata.getId()) {

            try {
                if (!plata_kg.getText().equals("0") && !plata_kg.getText().equals("")) {
                    if (Integer.parseInt(String.valueOf(plata_kg.getText())) > (control.getEspana().getPlata().getRecoleccionPlata().getCantidad() * 50) / 100) {
                        control.crearMercancias(control.getEspana().getPlata(), Integer.parseInt(String.valueOf(plata_kg.getText())), ProductoNombre.Plata);
                        plata.setText(control.getEspana().getPlata().getRecoleccionPlata().getNombre() + " " + control.getEspana().getPlata().getRecoleccionPlata().getCantidad() + " kg");
                        plata_seekbar.setMax(control.getEspana().getPlata().getRecoleccionPlata().getCantidad());
                        Toast.makeText(this, "Mercancia de Plata creada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, " Tiene que crear una mercancia superior a " + (control.getEspana().getPlata().getRecoleccionPlata().getCantidad() * 50)/100, Toast.LENGTH_LONG).show();
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
                    if (Integer.parseInt(String.valueOf(tabaco_kg.getText())) > (control.getEspana().getPlata().getRecoleccionTabaco().getCantidad() * 50) / 100) {
                        control.crearMercancias(control.getEspana().getPlata(), Integer.parseInt(String.valueOf(tabaco_kg.getText())), ProductoNombre.Tabaco);
                        tabaco.setText(control.getEspana().getPlata().getRecoleccionTabaco().getNombre() + " " + control.getEspana().getPlata().getRecoleccionTabaco().getCantidad() + " kg");
                        tabaco_seekbar.setMax(control.getEspana().getPlata().getRecoleccionTabaco().getCantidad());
                        Toast.makeText(this, "Mercancia de Tabaco creada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, " Tiene que crear una mercancia superior a " + (control.getEspana().getPlata().getRecoleccionTabaco().getCantidad() * 50)/100, Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(this, "Debe introducir una cantidad para crear una mercancía", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }else if(v.getId() == botonCafe.getId()){

            try {
                if (!cafe_kg.getText().equals("0") && !cafe_kg.getText().equals("")) {
                    if (Integer.parseInt(String.valueOf(cafe_kg.getText())) > (control.getEspana().getPlata().getRecoleccionCafe().getCantidad() * 50) / 100) {
                        control.crearMercancias(control.getEspana().getPlata(), Integer.parseInt(String.valueOf(cafe_kg.getText())), ProductoNombre.Cafe);
                        cafe.setText(control.getEspana().getPlata().getRecoleccionCafe().getNombre() + " " + control.getEspana().getPlata().getRecoleccionCafe().getCantidad() + " kg");
                        cafe_seekbar.setMax(control.getEspana().getPlata().getRecoleccionCafe().getCantidad());
                        Toast.makeText(this, "Mercancia de Café creada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, " Tiene que crear una mercancia superior a " + (control.getEspana().getPlata().getRecoleccionCafe().getCantidad() * 50)/100, Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(this, "Debe introducir una cantidad para crear una mercancía", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }else{

            try {
                if (!patatas_kg.getText().equals("0") && !patatas_kg.getText().equals("")) {
                    if (Integer.parseInt(String.valueOf(patatas_kg.getText())) > (control.getEspana().getPlata().getRecoleccionPatata().getCantidad() * 50) / 100) {
                        control.crearMercancias(control.getEspana().getPlata(), Integer.parseInt(String.valueOf(patatas_kg.getText())), ProductoNombre.Patata);
                        patatas.setText(control.getEspana().getPlata().getRecoleccionPatata().getNombre() + " " + control.getEspana().getPlata().getRecoleccionPatata().getCantidad() + " kg");
                        patatas_seekbar.setMax(control.getEspana().getPlata().getRecoleccionPatata().getCantidad());
                        Toast.makeText(this, "Mercancia de Patatas creada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, " Tiene que crear una mercancia superior a " + (control.getEspana().getPlata().getRecoleccionPatata().getCantidad() * 50)/100, Toast.LENGTH_LONG).show();
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