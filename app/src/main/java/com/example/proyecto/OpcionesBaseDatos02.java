package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.Base.BaseDatos;
import com.example.proyecto.CustomListViews.CustomListProductos;
import com.example.proyecto.Fragments.FragmentDemandas;
import com.example.proyecto.Fragments.FragmentFlotaEnviada;
import com.example.proyecto.Fragments.FragmentMercancias;
import com.example.proyecto.Fragments.FragmentMercanciasFlota;
import com.example.proyecto.Fragments.FragmentSublevaciones;
import com.example.proyecto.Fragments.FragmetProducciones;

public class OpcionesBaseDatos02 extends AppCompatActivity{

    private View decorView;
    private MediaPlayer media2;
    private int time;
    private String activity_seleccionada;
    private BaseDatos database;
    private String partida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_base_datos02);

        time = getIntent().getIntExtra("segundos", 4);
        this.activity_seleccionada=getIntent().getStringExtra("activity_seleccionada");
        this.partida=getIntent().getStringExtra("partida");
        database= new BaseDatos(this);
        cargarFragment();

        media2 = MediaPlayer.create(this, R.raw.menu_theme);
        media2.setVolume(10, 10);
        media2.setLooping(true);
        media2.seekTo(time);
        media2.start();

        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility == 0){
                    decorView.setSystemUiVisibility(hideSystemBars());
                }
            }
        });
    }

    protected void cargarFragment(){
        TextView tv = findViewById(R.id.textView19);
        tv.setText(this.activity_seleccionada+"");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment=null;
        switch (this.activity_seleccionada){
            case "Tabla Producciones":
                fragment = new FragmetProducciones(this.database,this.partida,this);
                break;
            case "Tabla Demandas":
                fragment = new FragmentDemandas(this.database,this.partida,this);
                break;
            case "Tabla Mercancias":
                fragment = new FragmentMercancias(this.database,this.partida,this);
                break;
            case "Tabla Mercancias-Flota":
                fragment = new FragmentMercanciasFlota(this.database,this.partida,this);
                break;
            case "Tabla Flota-Envio":
                fragment = new FragmentFlotaEnviada(this.database,this.partida,this);
                break;
            case "Tabla Sublevaciones":
                fragment = new FragmentSublevaciones(this.database,this.partida,this);
                break;

        }
        transaction.replace(R.id.framebaseDatos, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        //Por algun motivo no me pilla la lista de producciones



        //List<QueryProductos> lista =database.selectProductos(this.partida);

        //this.adaptador = new CustomListProductos(this,R.layout.row_producciones,lista);
        //this.listaProductos.setAdapter(this.adaptador);
        //this.listaProductos.setOnItemClickListener(this);
    }

    public void atras(View view){
        Intent i = new Intent(this, OpcionesBaseDatos.class);
        startActivity(i);
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
    public void onBackPressed(){

        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("segundos", media2.getCurrentPosition());
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        this.finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(media2.isPlaying()){
            media2.stop();
            media2.release();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if(media2 != null && media2.isPlaying()) {
            time = media2.getCurrentPosition();
            media2.stop();
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

        if(!media2.isPlaying() && media2 != null){
            media2 = MediaPlayer.create(this, R.raw.menu_theme);
            media2.setVolume(10, 10);
            media2.setLooping(true);
            media2.seekTo(time);
            media2.start();
        }

    }

    public int devolverSegundos(){

        int segundos = 0;

        segundos = media2.getCurrentPosition();
        return segundos;
    }

}