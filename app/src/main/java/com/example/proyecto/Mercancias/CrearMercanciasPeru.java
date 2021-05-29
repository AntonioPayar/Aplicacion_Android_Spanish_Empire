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

public class CrearMercanciasPeru extends AppCompatActivity implements MercanciasInterfaz {

    private View decorView;
    private MediaPlayer media;
    private PanelDeControl control;
    public static int time;
    private int contador = 0;

    private TextView oro;
    private TextView maiz;
    private TextView tomates;
    private TextView patatas;

    private SeekBar oro_seekbar;
    private SeekBar maiz_seekbar;
    private SeekBar tomates_seekbar;
    private SeekBar patatas_seekbar;

    private TextView oro_kg;
    private TextView maiz_kg;
    private TextView tomates_kg;
    private TextView patatas_kg;

    private Button botonOro;
    private Button botonMaiz;
    private Button botonTomates;
    private Button botonPatatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_mercancias_peru);

        control = Juego.getPanelDeControl();
        time = getIntent().getIntExtra("segundosMerc", 4);

        oro = (TextView)findViewById(R.id.oroPeru_txtv);
        maiz = (TextView)findViewById(R.id.maizPeru_txtv);
        tomates = (TextView)findViewById(R.id.tomatesPeru_txtv);
        patatas = (TextView)findViewById(R.id.patatasPeru_txtv);

        oro_seekbar = (SeekBar)findViewById(R.id.seekBar_oroPeru);
        maiz_seekbar = (SeekBar)findViewById(R.id.seekBar_maizPeru);
        tomates_seekbar = (SeekBar)findViewById(R.id.seekBar_tomatesPeru);
        patatas_seekbar = (SeekBar)findViewById(R.id.seekBar_patatasPeru);

        oro_kg = (TextView)findViewById(R.id.oro_kilosPeru);
        maiz_kg = (TextView)findViewById(R.id.maiz_kilosPeru);
        tomates_kg = (TextView)findViewById(R.id.tomates_kilosPeru);
        patatas_kg = (TextView)findViewById(R.id.patatas_kilosPeru);


        botonOro = (Button)findViewById(R.id.boton_oroPeru);
        botonMaiz = (Button)findViewById(R.id.boton_maizPeru);
        botonTomates = (Button)findViewById(R.id.boton_tomatePeru);
        botonPatatas = (Button)findViewById(R.id.boton_patatasPeru);

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

    /*public int devolverSegundos(){

        int segundos = 0;

        segundos = media.getCurrentPosition();
        return segundos;
    }*/

    public void mostrarCantidadProductos() {

        //Oro ------ x Kg
        oro.setText(control.getEspana().getPeru().getRecoleccionOro().getNombre()+" "+control.getEspana().getPeru().getRecoleccionOro().getCantidad()+" kg");
        //Maiz ------ x Kg
        maiz.setText(control.getEspana().getPeru().getRecoleccionMaiz().getNombre()+" "+control.getEspana().getPeru().getRecoleccionMaiz().getCantidad()+" kg");
        //Tomates ------ x Kg
        tomates.setText(control.getEspana().getPeru().getRecoleccionTomate().getNombre()+" "+control.getEspana().getPeru().getRecoleccionTomate().getCantidad()+" kg");
        //Patatas ------ x Kg
        patatas.setText(control.getEspana().getPeru().getRecoleccionPatata().getNombre()+" "+control.getEspana().getPeru().getRecoleccionPatata().getCantidad()+" kg");
    }

    /**
     * Método que se encargar de inicializar los deslizadores y que a la hora de seleccionar un valor se reproduzca en la caja de texto
     * mediante el método onProgressChanged
     */

    public void colocarSeekBars() {

        oro_seekbar.setMax(control.getEspana().getPeru().getRecoleccionOro().getCantidad());
        maiz_seekbar.setMax(control.getEspana().getPeru().getRecoleccionMaiz().getCantidad());
        tomates_seekbar.setMax(control.getEspana().getPeru().getRecoleccionTomate().getCantidad());
        patatas_seekbar.setMax(control.getEspana().getPeru().getRecoleccionPatata().getCantidad());

        oro_seekbar.setOnSeekBarChangeListener(this);
        maiz_seekbar.setOnSeekBarChangeListener(this);
        tomates_seekbar.setOnSeekBarChangeListener(this);
        patatas_seekbar.setOnSeekBarChangeListener(this);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if(seekBar.getId() == oro_seekbar.getId()){
            oro_kg.setText(""+progress);
        }else if(seekBar.getId() == maiz_seekbar.getId()){
            maiz_kg.setText(""+progress);
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

        if(v.getId() == botonOro.getId()) {

            try {
                if (!oro_kg.getText().equals("0") && !oro_kg.getText().equals("")) {
                    if (Integer.parseInt(String.valueOf(oro_kg.getText())) > (control.getEspana().getPeru().getRecoleccionOro().getCantidad() * 50) / 100) {
                        control.crearMercancias(control.getEspana().getPeru(), Integer.parseInt(String.valueOf(oro_kg.getText())), ProductoNombre.Oro);
                        oro.setText(control.getEspana().getPeru().getRecoleccionOro().getNombre() + " " + control.getEspana().getPeru().getRecoleccionOro().getCantidad() + " kg");
                        oro_seekbar.setMax(control.getEspana().getPeru().getRecoleccionOro().getCantidad());
                        Toast.makeText(this, "Mercancia de Oro creada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, " Tiene que crear una mercancia superior a " + (control.getEspana().getPeru().getRecoleccionOro().getCantidad() * 50)/100, Toast.LENGTH_LONG).show();
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
                    if (Integer.parseInt(String.valueOf(maiz_kg.getText())) > (control.getEspana().getPeru().getRecoleccionMaiz().getCantidad() * 50) / 100) {
                        control.crearMercancias(control.getEspana().getPeru(), Integer.parseInt(String.valueOf(maiz_kg.getText())), ProductoNombre.Maiz);
                        maiz.setText(control.getEspana().getPeru().getRecoleccionMaiz().getNombre() + " " + control.getEspana().getPeru().getRecoleccionMaiz().getCantidad() + " kg");
                        maiz_seekbar.setMax(control.getEspana().getPeru().getRecoleccionMaiz().getCantidad());
                        Toast.makeText(this, "Mercancia de Maíz creada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, " Tiene que crear una mercancia superior a " + (control.getEspana().getPeru().getRecoleccionMaiz().getCantidad() * 50)/100, Toast.LENGTH_LONG).show();
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
                    if (Integer.parseInt(String.valueOf(tomates_kg.getText())) > (control.getEspana().getPeru().getRecoleccionTomate().getCantidad() * 50) / 100) {
                        control.crearMercancias(control.getEspana().getPeru(), Integer.parseInt(String.valueOf(tomates_kg.getText())), ProductoNombre.Tomate);
                        tomates.setText(control.getEspana().getPeru().getRecoleccionTomate().getNombre() + " " + control.getEspana().getPeru().getRecoleccionTomate().getCantidad() + " kg");
                        tomates_seekbar.setMax(control.getEspana().getPeru().getRecoleccionTomate().getCantidad());
                        Toast.makeText(this, "Mercancia de Tomates creada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, " Tiene que crear una mercancia superior a " + (control.getEspana().getPeru().getRecoleccionTomate().getCantidad() * 50)/100, Toast.LENGTH_LONG).show();
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
                    if (Integer.parseInt(String.valueOf(patatas_kg.getText())) > (control.getEspana().getPeru().getRecoleccionPatata().getCantidad() * 50) / 100) {
                        control.crearMercancias(control.getEspana().getPeru(), Integer.parseInt(String.valueOf(patatas_kg.getText())), ProductoNombre.Patata);
                        patatas.setText(control.getEspana().getPeru().getRecoleccionPatata().getNombre() + " " + control.getEspana().getPeru().getRecoleccionPatata().getCantidad() + " kg");
                        patatas_seekbar.setMax(control.getEspana().getPeru().getRecoleccionPatata().getCantidad());
                        Toast.makeText(this, "Mercancia de Patatas creada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, " Tiene que crear una mercancia superior a " + (control.getEspana().getPeru().getRecoleccionPatata().getCantidad() * 50)/100, Toast.LENGTH_LONG).show();
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