package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.Base.BaseDatos;
import com.example.proyecto.CustomListViews.CustomListPartidas;

import java.util.ArrayList;
import java.util.List;

public class OpcionesBaseDatos extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private View decorView;
    private MediaPlayer media2;
    private int time;
    private ListView listaPartidas;
    private CustomListPartidas adaptador;
    private BaseDatos database;
    private TextView elementosSeleccionados;
    private RadioGroup radiogroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_base_datos);
        time = getIntent().getIntExtra("segundos", 4);
        media2 = MediaPlayer.create(this, R.raw.menu_theme);
        media2.setVolume(10, 10);
        media2.setLooping(true);
        media2.seekTo(time);
        media2.start();

        this.listaPartidas=findViewById(R.id.list_partidas);
        this.radiogroup=findViewById(R.id.radioGroup01);
        this.listaPartidas.setOnItemClickListener(this);
        this.elementosSeleccionados=findViewById(R.id.textView17);

//        List<String> lista = null;
//        try {
//            lista = database.selectPartidas();
//            if (lista.size() == 0) {
//                lista = new ArrayList<>();
//                lista.add("No hay ninguna partida de momento");
//            }
//
//            System.out.println(lista.size());
//            this.adaptador = new CustomListPartidas(this,R.layout.row_partidas,lista);
//            this.listaPartidas.setAdapter(this.adaptador);
//        }catch(Exception e){
//            lista.add("No hay ninguna partida de momento");
//        }

        database= new BaseDatos(this);
        List<String> lista =database.selectPartidas();
        if(lista.size()==0){
            lista=new ArrayList<>();
            lista.add("No hay ninguna partida de momento");
        }
        this.adaptador = new CustomListPartidas(this,R.layout.row_partidas,lista);
        this.listaPartidas.setAdapter(this.adaptador);

        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility == 0){
                    decorView.setSystemUiVisibility(hideSystemBars());
                }
            }
        });

        this.radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                TextView radio_selecionado=findViewById(R.id.textView18);
                String texto=radio_selecionado.getText().toString();
                radio_selecionado.setText("");

                switch (checkedId){
                    case R.id.rbdemandas:
                        texto="Tabla Demandas";
                        break;
                    case R.id.rbflotaenvia:
                        texto="Tabla Flota-Envio";
                        break;
                    case R.id.rbmercaflota:
                        texto="Tabla Mercancias-Flota";
                        break;
                    case R.id.rbmercancias:
                        texto="Tabla Mercancias";
                        break;
                    case R.id.rbsubleva:
                        texto="Tabla Sublevaciones";
                        break;
                    case R.id.rbproducciones:
                        texto="Tabla Producciones";
                        break;
                }
                radio_selecionado.setText(texto);
            }
        });
    }

    /**Metodo on click de CustomListPartidas**/
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String text=this.elementosSeleccionados.getText().toString();
        text="Partida : "+this.adaptador.getItem(position).toString();

        this.elementosSeleccionados.setText(text);
    }

    public void atras(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void botonEnter(View view){
        TextView radio_selecionado=findViewById(R.id.textView18);

        if(this.elementosSeleccionados.getText().length()>3 && radio_selecionado.getText().length()>3){
            Intent i = new Intent(this, OpcionesBaseDatos02.class);
            i.putExtra("segundos", media2.getCurrentPosition());
            i.putExtra("activity_seleccionada", radio_selecionado.getText().toString());
            i.putExtra("partida", this.elementosSeleccionados.getText().toString().substring(10,this.elementosSeleccionados.getText().toString().length()));
            startActivity(i);
        }else{
            Toast.makeText(this, "Seleccione Partida y Datos", Toast.LENGTH_SHORT).show();
        }
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