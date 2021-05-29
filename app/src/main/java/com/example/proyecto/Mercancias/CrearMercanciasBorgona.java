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

public class CrearMercanciasBorgona extends AppCompatActivity implements MercanciasInterfaz {

    private View decorView;
    private MediaPlayer media;
    private PanelDeControl control;
    public static int time;
    private int contador = 0;

    private TextView hierro;
    private TextView arroz;
    private TextView tomates;
    private TextView patatas;

    private SeekBar hierro_seekbar;
    private SeekBar arroz_seekbar;
    private SeekBar tomates_seekbar;
    private SeekBar patatas_seekbar;

    private TextView hierro_kg;
    private TextView arroz_kg;
    private TextView tomates_kg;
    private TextView patatas_kg;

    private Button botonHierro;
    private Button botonArroz;
    private Button botonTomates;
    private Button botonPatatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_mercancias_borgona);

        control = Juego.getPanelDeControl();
        time = getIntent().getIntExtra("segundosMerc", 4);

        hierro = (TextView)findViewById(R.id.hierroBorgona_txtv);
        arroz = (TextView)findViewById(R.id.arrozBorgona_txtv);
        tomates = (TextView)findViewById(R.id.tomatesBorgona_txtv);
        patatas = (TextView)findViewById(R.id.patatasBorgona_txtv);

        hierro_seekbar = (SeekBar)findViewById(R.id.seekBar_hierroBorgona);
        arroz_seekbar = (SeekBar)findViewById(R.id.seekBar_arrozBorgona);
        tomates_seekbar = (SeekBar)findViewById(R.id.seekBar_tomatesBorgona);
        patatas_seekbar = (SeekBar)findViewById(R.id.seekBar_patatasBorgona);

        hierro_kg = (TextView)findViewById(R.id.hierro_kilosBorgona);
        arroz_kg = (TextView)findViewById(R.id.arroz_kilosBorgona);
        tomates_kg = (TextView)findViewById(R.id.tomates_kilosBorgona);
        patatas_kg = (TextView)findViewById(R.id.patatas_kilosBorgona);


        botonHierro = (Button)findViewById(R.id.boton_hierroBorgona);
        botonArroz = (Button)findViewById(R.id.boton_arrozBorgona);
        botonTomates = (Button)findViewById(R.id.boton_tomatesBorgona);
        botonPatatas = (Button)findViewById(R.id.boton_patatasBorgona);

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
        botonHierro.setOnClickListener(this);
        botonArroz.setOnClickListener(this);
        botonTomates.setOnClickListener(this);
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

    public int devolverSegundos(){

        int segundos = 0;

        segundos = media.getCurrentPosition();
        return segundos;
    }

    public void mostrarCantidadProductos() {

        hierro.setText(control.getEspana().getBorgona().getRecoleccionHierro().getNombre()+" "+control.getEspana().getBorgona().getRecoleccionHierro().getCantidad()+" kg");
        arroz.setText(control.getEspana().getBorgona().getRecoleccionArroz().getNombre()+" "+control.getEspana().getBorgona().getRecoleccionArroz().getCantidad()+" kg");
        tomates.setText(control.getEspana().getBorgona().getRecoleccionTomates().getNombre()+" "+control.getEspana().getBorgona().getRecoleccionTomates().getCantidad()+" kg");
        patatas.setText(control.getEspana().getBorgona().getRecoleccionPatatas().getNombre()+" "+control.getEspana().getBorgona().getRecoleccionPatatas().getCantidad()+" kg");
    }

    /**
     * Método que se encargar de inicializar los deslizadores y que a la hora de seleccionar un valor se reproduzca en la caja de texto
     * mediante el método onProgressChanged
     */

    public void colocarSeekBars() {

        hierro_seekbar.setMax(control.getEspana().getBorgona().getRecoleccionHierro().getCantidad());
        arroz_seekbar.setMax(control.getEspana().getBorgona().getRecoleccionArroz().getCantidad());
        tomates_seekbar.setMax(control.getEspana().getBorgona().getRecoleccionTomates().getCantidad());
        patatas_seekbar.setMax(control.getEspana().getBorgona().getRecoleccionPatatas().getCantidad());

        hierro_seekbar.setOnSeekBarChangeListener(this);
        arroz_seekbar.setOnSeekBarChangeListener(this);
        tomates_seekbar.setOnSeekBarChangeListener(this);
        patatas_seekbar.setOnSeekBarChangeListener(this);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if(seekBar.getId() == hierro_seekbar.getId()){
            hierro_kg.setText(""+progress);
        }else if(seekBar.getId() == arroz_seekbar.getId()){
            arroz_kg.setText(""+progress);
        }else if(seekBar.getId() == tomates_seekbar.getId()){
            tomates_kg.setText(""+progress);
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

        if(v.getId() == botonHierro.getId()) {

            try {
                if (!hierro_kg.getText().equals("0") && !hierro_kg.getText().equals("")) {
                    if (Integer.parseInt(String.valueOf(hierro_kg.getText())) > (control.getEspana().getBorgona().getRecoleccionHierro().getCantidad() * 50) / 100) {
                        control.crearMercancias(control.getEspana().getBorgona(), Integer.parseInt(String.valueOf(hierro_kg.getText())), ProductoNombre.Hierro);
                        hierro.setText(control.getEspana().getBorgona().getRecoleccionHierro().getNombre() + " " + control.getEspana().getBorgona().getRecoleccionHierro().getCantidad() + " kg");
                        hierro_seekbar.setMax(control.getEspana().getBorgona().getRecoleccionHierro().getCantidad());
                        Toast.makeText(this, "Mercancia de Hierro creada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, " Tiene que crear una mercancia superior a " + (control.getEspana().getBorgona().getRecoleccionHierro().getCantidad() * 50)/100, Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(this, "Debe introducir una cantidad para crear una mercancía", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }else if(v.getId() == botonArroz.getId()){

            try {
                if (!arroz_kg.getText().equals("0") && !arroz_kg.getText().equals("")) {
                    if (Integer.parseInt(String.valueOf(arroz_kg.getText())) > (control.getEspana().getBorgona().getRecoleccionArroz().getCantidad() * 50) / 100) {
                        control.crearMercancias(control.getEspana().getBorgona(), Integer.parseInt(String.valueOf(arroz_kg.getText())), ProductoNombre.Arroz);
                        arroz.setText(control.getEspana().getBorgona().getRecoleccionArroz().getNombre() + " " + control.getEspana().getBorgona().getRecoleccionArroz().getCantidad() + " kg");
                        arroz_seekbar.setMax(control.getEspana().getBorgona().getRecoleccionArroz().getCantidad());
                        Toast.makeText(this, "Mercancia de Arroz creada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, " Tiene que crear una mercancia superior a " + (control.getEspana().getBorgona().getRecoleccionArroz().getCantidad() * 50)/100, Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(this, "Debe introducir una cantidad para crear una mercancía", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }else if(v.getId() == botonTomates.getId()){

            try {
                if (!tomates_kg.getText().equals("0") && !tomates_kg.getText().equals("")) {
                    if (Integer.parseInt(String.valueOf(tomates_kg.getText())) > (control.getEspana().getBorgona().getRecoleccionTomates().getCantidad() * 50) / 100) {
                        control.crearMercancias(control.getEspana().getBorgona(), Integer.parseInt(String.valueOf(tomates_kg.getText())), ProductoNombre.Tomate);
                        tomates.setText(control.getEspana().getBorgona().getRecoleccionTomates().getNombre() + " " + control.getEspana().getBorgona().getRecoleccionTomates().getCantidad() + " kg");
                        tomates_seekbar.setMax(control.getEspana().getBorgona().getRecoleccionTomates().getCantidad());
                        Toast.makeText(this, "Mercancia de Tomates creada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, " Tiene que crear una mercancia superior a " + (control.getEspana().getBorgona().getRecoleccionTomates().getCantidad() * 50)/100, Toast.LENGTH_LONG).show();
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
                    if (Integer.parseInt(String.valueOf(patatas_kg.getText())) > (control.getEspana().getBorgona().getRecoleccionPatatas().getCantidad() * 50) / 100) {
                        control.crearMercancias(control.getEspana().getBorgona(), Integer.parseInt(String.valueOf(patatas_kg.getText())), ProductoNombre.Patata);
                        patatas.setText(control.getEspana().getBorgona().getRecoleccionPatatas().getNombre() + " " + control.getEspana().getBorgona().getRecoleccionPatatas().getCantidad() + " kg");
                        patatas_seekbar.setMax(control.getEspana().getBorgona().getRecoleccionPatatas().getCantidad());
                        Toast.makeText(this, "Mercancia de Patatas creada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, " Tiene que crear una mercancia superior a " + (control.getEspana().getBorgona().getRecoleccionPatatas().getCantidad() * 50)/100, Toast.LENGTH_LONG).show();
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