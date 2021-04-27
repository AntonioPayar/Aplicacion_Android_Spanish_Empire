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

public class CrearMercanciasAustria extends AppCompatActivity implements MercanciasInterfaz {

    private View decorView;
    private MediaPlayer media;
    private PanelDeControl control;
    public static int time;
    private int contador = 0;

    //njihibhibbhiu

    private TextView arroz;
    private TextView plata;
    private TextView uvas;
    private TextView hierro;

    private SeekBar arroz_seekbar;
    private SeekBar plata_seekbar;
    private SeekBar uvas_seekbar;
    private SeekBar hierro_seekbar;

    private TextView arroz_kg;
    private TextView plata_kg;
    private TextView uvas_kg;
    private TextView hierro_kg;

    private Button botonArroz;
    private Button botonPlata;
    private Button botonUvas;
    private Button botonhierro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_mercancias_austria);

        control = Juego.getPanelDeControl();
        time = getIntent().getIntExtra("segundosMerc", 4);

        arroz = (TextView)findViewById(R.id.arrozAustria_txtv);
        plata = (TextView)findViewById(R.id.plataAustria_txtv);
        uvas = (TextView)findViewById(R.id.uvasAustria_txtv);
        hierro = (TextView)findViewById(R.id.hierroAustria_txtv);

        arroz_seekbar = (SeekBar)findViewById(R.id.seekBar_arrozAustria);
        plata_seekbar = (SeekBar)findViewById(R.id.seekBar_plataAustria);
        uvas_seekbar = (SeekBar)findViewById(R.id.seekBar_uvasAustria);
        hierro_seekbar = (SeekBar)findViewById(R.id.seekBar_hierroAustria);

        arroz_kg = (TextView)findViewById(R.id.arroz_kilosAustria);
        plata_kg = (TextView)findViewById(R.id.plata_kilosAustria);
        uvas_kg = (TextView)findViewById(R.id.uvas_kilosAustria);
        hierro_kg = (TextView)findViewById(R.id.hierro_kilosAustria);


        botonArroz = (Button)findViewById(R.id.boton_arrozAustria);
        botonPlata = (Button)findViewById(R.id.boton_plataAustria);
        botonUvas = (Button)findViewById(R.id.boton_uvasAustria);
        botonhierro = (Button)findViewById(R.id.boton_hierroAustria);

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
        botonhierro.setOnClickListener(this);
        botonUvas.setOnClickListener(this);
        botonPlata.setOnClickListener(this);
        botonArroz.setOnClickListener(this);
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

        arroz.setText(control.getEspana().getAustria().getRecoleccionArroz().getNombre()+" "+control.getEspana().getAustria().getRecoleccionArroz().getCantidad()+" kg");
        plata.setText(control.getEspana().getAustria().getRecoleccionPlata().getNombre()+" "+control.getEspana().getAustria().getRecoleccionPlata().getCantidad()+" kg");
        uvas.setText(control.getEspana().getAustria().getRecoleccionUvas().getNombre()+" "+control.getEspana().getAustria().getRecoleccionUvas().getCantidad()+" kg");
        hierro.setText(control.getEspana().getAustria().getRecoleccionHierro().getNombre()+" "+control.getEspana().getAustria().getRecoleccionHierro().getCantidad()+" kg");
    }

    /**
     * Método que se encargar de inicializar los deslizadores y que a la hora de seleccionar un valor se reproduzca en la caja de texto
     * mediante el método onProgressChanged
     */

    public void colocarSeekBars() {

        arroz_seekbar.setMax(control.getEspana().getAustria().getRecoleccionArroz().getCantidad());
        plata_seekbar.setMax(control.getEspana().getAustria().getRecoleccionPlata().getCantidad());
        uvas_seekbar.setMax(control.getEspana().getAustria().getRecoleccionUvas().getCantidad());
        hierro_seekbar.setMax(control.getEspana().getAustria().getRecoleccionHierro().getCantidad());

        arroz_seekbar.setOnSeekBarChangeListener(this);
        plata_seekbar.setOnSeekBarChangeListener(this);
        uvas_seekbar.setOnSeekBarChangeListener(this);
        hierro_seekbar.setOnSeekBarChangeListener(this);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if(seekBar.getId() == arroz_seekbar.getId()){
            arroz_kg.setText(""+progress);
        }else if(seekBar.getId() == plata_seekbar.getId()){
            plata_kg.setText(""+progress);
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

        if(v.getId() == botonArroz.getId()) {

            try {
                if (!arroz_kg.getText().equals("0") && !arroz_kg.getText().equals("")) {
                    if (Integer.parseInt(String.valueOf(arroz_kg.getText())) > (control.getEspana().getAustria().getRecoleccionArroz().getCantidad() * 50) / 100) {
                        control.crearMercancias(control.getEspana().getAustria(), Integer.parseInt(String.valueOf(arroz_kg.getText())), ProductoNombre.Arroz);
                        arroz.setText(control.getEspana().getAustria().getRecoleccionArroz().getNombre() + " " + control.getEspana().getAustria().getRecoleccionArroz().getCantidad() + " kg");
                        arroz_seekbar.setMax(control.getEspana().getAustria().getRecoleccionArroz().getCantidad());
                        Toast.makeText(this, "Mercancia de Arroz creada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, " Tiene que crear una mercancia superior a " + (control.getEspana().getAustria().getRecoleccionArroz().getCantidad() * 50)/100, Toast.LENGTH_LONG).show();
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
                    if (Integer.parseInt(String.valueOf(plata_kg.getText())) > (control.getEspana().getAustria().getRecoleccionPlata().getCantidad() * 50) / 100) {
                        control.crearMercancias(control.getEspana().getAustria(), Integer.parseInt(String.valueOf(plata_kg.getText())), ProductoNombre.Plata);
                        plata.setText(control.getEspana().getAustria().getRecoleccionPlata().getNombre() + " " + control.getEspana().getAustria().getRecoleccionPlata().getCantidad() + " kg");
                        plata_seekbar.setMax(control.getEspana().getAustria().getRecoleccionPlata().getCantidad());
                        Toast.makeText(this, "Mercancia de Plata creada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, " Tiene que crear una mercancia superior a " + (control.getEspana().getAustria().getRecoleccionPlata().getCantidad() * 50)/100, Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(this, "Debe introducir una cantidad para crear una mercancía", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }else if(v.getId() == botonUvas.getId()){

            try {
                if (!uvas_kg.getText().equals("0") && !uvas_kg.getText().equals("")) {
                    if (Integer.parseInt(String.valueOf(uvas_kg.getText())) > (control.getEspana().getAustria().getRecoleccionUvas().getCantidad() * 50) / 100) {
                        control.crearMercancias(control.getEspana().getAustria(), Integer.parseInt(String.valueOf(uvas_kg.getText())), ProductoNombre.Uvas);
                        uvas.setText(control.getEspana().getAustria().getRecoleccionUvas().getNombre() + " " + control.getEspana().getAustria().getRecoleccionUvas().getCantidad() + " kg");
                        uvas_seekbar.setMax(control.getEspana().getAustria().getRecoleccionUvas().getCantidad());
                        Toast.makeText(this, "Mercancia de Uvas creada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, " Tiene que crear una mercancia superior a " + (control.getEspana().getAustria().getRecoleccionUvas().getCantidad() * 50)/100, Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(this, "Debe introducir una cantidad para crear una mercancía", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }else{

            try {
                if (!hierro_kg.getText().equals("0") && !hierro_kg.getText().equals("")) {
                    if (Integer.parseInt(String.valueOf(hierro_kg.getText())) > (control.getEspana().getAustria().getRecoleccionHierro().getCantidad() * 50) / 100) {
                        control.crearMercancias(control.getEspana().getAustria(), Integer.parseInt(String.valueOf(hierro_kg.getText())), ProductoNombre.Hierro);
                        hierro.setText(control.getEspana().getAustria().getRecoleccionHierro().getNombre() + " " + control.getEspana().getAustria().getRecoleccionHierro().getCantidad() + " kg");
                        hierro_seekbar.setMax(control.getEspana().getAustria().getRecoleccionHierro().getCantidad());
                        Toast.makeText(this, "Mercancia de Hierro creada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, " Tiene que crear una mercancia superior a " + (control.getEspana().getAustria().getRecoleccionHierro().getCantidad() * 50)/100, Toast.LENGTH_LONG).show();
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