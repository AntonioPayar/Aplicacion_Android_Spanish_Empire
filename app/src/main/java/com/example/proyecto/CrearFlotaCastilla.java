package com.example.proyecto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.proyecto.Clases.Mercancia;
import com.example.proyecto.CustomListViews.CustomAdapterListView;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class CrearFlotaCastilla extends AppCompatActivity {

    private MediaPlayer media;
    private View decorView;
    private PanelDeControl control;
    public static int time;
    private static SharedPreferences sharedPref;

    private CustomAdapterListView adaptador;
    private ListView listview_mercancias;
    private LinkedHashMap<Integer, Mercancia> merca;

    private String zona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_flota_castilla);
        //instancier el controlador
        control = Juego.getPanelDeControl();

        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        //instanciar gif
        ImageView imageView = (ImageView)findViewById(R.id.imageView8);
        Glide.with(getApplicationContext()).load(R.drawable.mar).into(imageView);
        //pedir la zona
        this.zona =getIntent().getStringExtra("zona");
        mostrarMercancias();
        //poner musica
        time = getIntent().getIntExtra("segundosMerc", 4);
        if(sharedPref.getBoolean("insertado", false) == true){
            time = sharedPref.getInt("sec", 4);
        }
        media = MediaPlayer.create(this, R.raw.partida);
        media.setVolume(10, 10);
        media.setLooping(true);
        media.seekTo(time);
        media.start();
        //Instanciar Listview y metodo onclick del mismo
        this.recargarListview();
        //onclick del Listview
        this.listview_mercancias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Mercancia mer = retorna(position);
                int finalPosicion=mer.getvalor_chapuz();
                AlertDialog.Builder adb=new AlertDialog.Builder(CrearFlotaCastilla.this);
                adb.setTitle("Confirmar");
                adb.setMessage("¿Quieres añadir la "+mer.getNombre()+" la flota de "+zona+"? ");
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            removeItem(finalPosicion);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }});
                adb.show();
            }
        });
        //Creo que son para oculatar los botones
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
    public Mercancia retorna(int posicion){
        return this.adaptador.retornarMercancia(posicion);
    }

    /**Metodo encargado de recargar o instanciar el ListView**/
    private void recargarListview(){
        this.listview_mercancias=findViewById(R.id.list_view);
        this.adaptador = new CustomAdapterListView(this,this.merca);
        this.listview_mercancias.setAdapter(adaptador);
    }

    private void removeItem(int posicion) throws Exception {
        meterCargamentoFlota(posicion);
        Intent intent = new Intent(this, CrearFlotaCastilla.class);
        intent.putExtra("zona", this.zona);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("sec", media.getCurrentPosition());
        editor.putBoolean("insertado", true);
        editor.commit();
        startActivity(intent);
        finish();
    }


    private void meterCargamentoFlota(int id) throws Exception {
        Iterator it=null;
        switch (this.zona){
            case "Castilla":
                this.control.getEspana().formarFlota(this.control.getEspana().getCastilla(), id);
                it =this.control.getEspana().getCastilla().getFlota().getArrayMercancias().keySet().iterator();
                Toast.makeText(this, this.control.getEspana().getCastilla().getFlota().getArrayMercancias().get(id).getNombre(), Toast.LENGTH_SHORT).show();
                break;
            case "Aragon":
                this.control.getEspana().formarFlota(this.control.getEspana().getAragon(), id);
                it =this.control.getEspana().getCastilla().getFlota().getArrayMercancias().keySet().iterator();
                break;
            case "Borgona":
                this.control.getEspana().formarFlota(this.control.getEspana().getBorgona(), id);
                it =this.control.getEspana().getCastilla().getFlota().getArrayMercancias().keySet().iterator();
                break;
            case "Austria":
                this.control.getEspana().formarFlota(this.control.getEspana().getAustria(), id);
                it =this.control.getEspana().getCastilla().getFlota().getArrayMercancias().keySet().iterator();
                break;
            case "NuevaEspana":
                this.control.getEspana().formarFlota(this.control.getEspana().getNuevaEspana(), id);
                it =this.control.getEspana().getCastilla().getFlota().getArrayMercancias().keySet().iterator();
                break;
            case "NuevaGranada":
                this.control.getEspana().formarFlota(this.control.getEspana().getNuevaGranda(), id);
                it =this.control.getEspana().getCastilla().getFlota().getArrayMercancias().keySet().iterator();
                break;
            case "Peru":
                this.control.getEspana().formarFlota(this.control.getEspana().getPeru(), id);
                it =this.control.getEspana().getCastilla().getFlota().getArrayMercancias().keySet().iterator();
                break;
            case "Plata":
                this.control.getEspana().formarFlota(this.control.getEspana().getPlata(), id);
                it =this.control.getEspana().getCastilla().getFlota().getArrayMercancias().keySet().iterator();
                break;
        }
    }

    private void mostrarMercancias(){
        LinkedHashMap<Integer, Mercancia> mercan=null;
        TextView texto_zona=findViewById(R.id.textView15);
        TextView peso_disponible =findViewById(R.id.textView12);
        TextView peso_total=findViewById(R.id.textView14);
        switch (this.zona){
            case "Castilla":
                texto_zona.setText("Castilla");
                peso_disponible.setText("Peso introducido : "+String.valueOf(this.control.getEspana().getCastilla().getFlota().getPesoTodasMercancias()));
                peso_total.setText("Peso disponible : "+String.valueOf(control.getEspana().getCastilla().getFlota().getPesoMaximo()-control.getEspana().getCastilla().getFlota().getPesoTodasMercancias()));
                mercan=this.control.getEspana().getCastilla().getMercancia();
                break;
            case "Aragon":
                texto_zona.setText("Aragon");
                peso_disponible.setText("Peso introducido : "+String.valueOf(this.control.getEspana().getAragon().getFlota().getPesoTodasMercancias()));
                peso_total.setText("Peso disponible : "+String.valueOf(control.getEspana().getAragon().getFlota().getPesoMaximo()-control.getEspana().getAragon().getFlota().getPesoTodasMercancias()));
                mercan=this.control.getEspana().getAragon().getMercancia();
                break;
            case "Borgona":
                texto_zona.setText("Borgoña");
                peso_disponible.setText("Peso introducido : "+String.valueOf(this.control.getEspana().getBorgona().getFlota().getPesoTodasMercancias()));
                peso_total.setText("Peso disponible : "+String.valueOf(control.getEspana().getBorgona().getFlota().getPesoMaximo()-control.getEspana().getBorgona().getFlota().getPesoTodasMercancias()));
                mercan=this.control.getEspana().getBorgona().getMercancia();
                break;
            case "Austria":
                texto_zona.setText("Austria");
                peso_disponible.setText("Peso introducido : "+String.valueOf(this.control.getEspana().getAustria().getFlota().getPesoTodasMercancias()));
                peso_total.setText("Peso disponible : "+String.valueOf(control.getEspana().getAustria().getFlota().getPesoMaximo()-control.getEspana().getAustria().getFlota().getPesoTodasMercancias()));
                mercan=this.control.getEspana().getAustria().getMercancia();
                break;
            case "NuevaEspana":
                texto_zona.setText("Nueva Españaa");
                peso_disponible.setText("Peso introducido : "+String.valueOf(this.control.getEspana().getNuevaEspana().getFlota().getPesoTodasMercancias()));
                peso_total.setText("Peso disponible : "+String.valueOf(control.getEspana().getNuevaEspana().getFlota().getPesoMaximo()-control.getEspana().getNuevaEspana().getFlota().getPesoTodasMercancias()));
                mercan=this.control.getEspana().getNuevaEspana().getMercancia();
                break;
            case "NuevaGranada":
                texto_zona.setText("Nueva Granada");
                peso_disponible.setText("Peso introducido : "+String.valueOf(this.control.getEspana().getNuevaGranda().getFlota().getPesoTodasMercancias()));
                peso_total.setText("Peso disponible : "+String.valueOf(control.getEspana().getNuevaGranda().getFlota().getPesoMaximo()-control.getEspana().getNuevaGranda().getFlota().getPesoTodasMercancias()));
                mercan=this.control.getEspana().getNuevaGranda().getMercancia();
                break;
            case "Peru":
                texto_zona.setText("Peru");
                peso_disponible.setText("Peso introducido : "+String.valueOf(this.control.getEspana().getPeru().getFlota().getPesoTodasMercancias()));
                peso_total.setText("Peso disponible : "+String.valueOf(control.getEspana().getPeru().getFlota().getPesoMaximo()-control.getEspana().getPeru().getFlota().getPesoTodasMercancias()));
                mercan=this.control.getEspana().getPeru().getMercancia();
                break;
            case "Plata":
                texto_zona.setText("Plata");
                peso_disponible.setText("Peso introducido : "+String.valueOf(this.control.getEspana().getPlata().getFlota().getPesoTodasMercancias()));
                peso_total.setText("Peso disponible : "+String.valueOf(control.getEspana().getPlata().getFlota().getPesoMaximo()-control.getEspana().getPlata().getFlota().getPesoTodasMercancias()));
                mercan=this.control.getEspana().getPlata().getMercancia();
                break;
        }
        this.merca=mercan;
    }


    public void embarcar(View view) throws Exception {
        for (int i=0;i<this.merca.size();i++){
            if(this.merca.get(i)!=null){
                meterCargamentoFlota(i);
            }
        }
        ImageView imageView = (ImageView)findViewById(R.id.imageView9);
        imageView.setImageResource(R.drawable.galleon_mercancias_25);
    }
    /**Metodo muy importante se encarga de meter y mostrar todas las mercancias de dentro de cada flota**/


    /**Boton de ir para atras**/
    public void volverAtras(View vista){

        Juego.setMedia(media.getCurrentPosition());
        onBackPressed();
        overridePendingTransition(R.anim.entrada, R.anim.salida);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("insertado", false);
        editor.commit();
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
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("insertado", false);
        editor.commit();
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
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("insertado", false);
        editor.commit();
        this.finish();
    }

}