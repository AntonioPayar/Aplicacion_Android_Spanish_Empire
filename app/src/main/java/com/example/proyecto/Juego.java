package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Juego extends AppCompatActivity {

    private View decorView;
    private MediaPlayer media;
    private static PanelDeControl pdc;
    private int time;
    private int contador = 0;
    private int contadorVentanas = 0;
    private boolean mapa=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        try {
            pdc = new PanelDeControl();
        } catch (Exception e) {
            e.printStackTrace();
        }

        media = MediaPlayer.create(this, R.raw.partida);
        media.setVolume(10, 10);
        media.setLooping(true);
        media.seekTo(0);
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
    }
    /**Onclick al pulsar el Boton Cambio**/
    public void cambioContinente(View view){
        Fragment mapa2;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(this.mapa==false){
            this.mapa=true;
            mapa2 = new mapaFragmento03();
            Toast.makeText(this, "America", Toast.LENGTH_SHORT).show();
        }else{
            this.mapa=false;
            mapa2 = new MapaFragmento02();
            Toast.makeText(this, "Europa", Toast.LENGTH_SHORT).show();
        }
        transaction.replace(R.id.fragment3, mapa2);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    /**Onclick al pulsal el ImagenView Castilla**/
    public void botoncastilla(View view){
        if(mapa==false && contador==0){
            abrirProductosEuropa();
            Toast.makeText(this, "Castilla", Toast.LENGTH_SHORT).show();
        }
    }
    /**Onclick al pulsal el ImagenView Aragon**/
    public void botonaragon(View view){
        if(mapa==false && contador==0){
            abrirProductosEuropa();
            Toast.makeText(this, "Aragon", Toast.LENGTH_SHORT).show();
        }
    }
    /**Onclick al pulsal el ImagenView Flandes**/
    public void botonborgona(View view){
        if(mapa==false && contador==0){
            abrirProductosEuropa();
            Toast.makeText(this, "Borgona", Toast.LENGTH_SHORT).show();
        }
    }
    /**Onclick al pulsal el ImagenView Austia**/
    public void botonhasburgo(View view){
        if(mapa==false && contador==0){
            abrirProductosEuropa();
            Toast.makeText(this, "Hasburgo", Toast.LENGTH_SHORT).show();
        }
    }
    /**Onclick al pulsal el ImagenView NuevaCastilla**/
    public void botonNuevaEspana(View view){
        abrirProductosAmerica();
        Toast.makeText(this, "NuevaCastilla", Toast.LENGTH_SHORT).show();
    }
    /**Onclick al pulsal el ImagenView Nueva Granada**/
    public void botonNuevaGranada(View view){
        abrirProductosAmerica();
        Toast.makeText(this, "Nueva Granada", Toast.LENGTH_SHORT).show();
    }
    /**Onclick al pulsal el ImagenView Peru**/
    public void botonPeru(View view){
        abrirProductosAmerica();
        Toast.makeText(this, "Peru", Toast.LENGTH_SHORT).show();
    }
    /**Onclick al pulsal el ImagenView Plata**/
    public void botonPlata(View view){
        abrirProductosAmerica();
        Toast.makeText(this, "Plata", Toast.LENGTH_SHORT).show();
    }
    /**Metodo no terminado se encarga de abrir el frame donde estan los productos de cada zona**/
    public void abrirProductosAmerica(){
        //De momento he puesto las demandas pero esto esta pensado para abrir la ventana respectiva de cada zona
        if(contador == 0) {
            Fragment fragment = new Demandas();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameamerica, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            contador++;
        }
    }
    /**Metodo no terminado se encarga de abrir el frame donde estan los productos de cada zona**/
    public void abrirProductosEuropa(){
        //De momento he puesto las demandas pero esto esta pensado para abrir la ventana respectiva de cada zona
        if(contador == 0) {
            Fragment fragment = new Demandas();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameuropa, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            contador++;
        }
    }

    /**
     * Método que abre un fragment en el que se informa el usuario de las demandas / tareas que se debe hacer en los turnos
     * @param vista
     */
    public void abrirDemandas(View vista){

        if(contadorVentanas == 0) {
            Fragment fragment = new Demandas();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            contadorVentanas++;
        }else{
            Toast.makeText(this, "Debe cerrar la ventana antes de acceder a otra", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Método que abre un fragment en el que se muestra los diferentes reinos que hay y asi acceder a sus mercancias disponibles
     * @param vista
     */

    public void abrirMercanciasMenu(View vista){

        if(contadorVentanas == 0) {
            Fragment fragment = new Mercancias();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            contadorVentanas++;
        }else{
            Toast.makeText(this, "Debe cerrar la ventana antes de acceder a otra", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Método que cierra el fragment actualmente abierto ( Por ejemplo el fragment de Demandas )
     * @param vista
     */

    public void cerrarFragment(View vista){

//        Toast.makeText(this, "hgrksmk", Toast.LENGTH_SHORT).show();
//        this.getFragmentManager().popBackStack();
        this.getSupportFragmentManager().popBackStack();
        contadorVentanas--;
        contador--;
//        Fragment fragment;
//        fragment = new Demandas();
//
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.remove(fragment);
//        transaction.commit();
    }

//    public static void enableDisableViewGroup(ViewGroup viewGroup, boolean enabled) {
//        int childCount = viewGroup.getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View view = viewGroup.getChildAt(i);
//            view.setEnabled(enabled);
//            if (view instanceof ViewGroup) {
//                enableDisableViewGroup((ViewGroup) view, enabled);
//            }
//        }
//    }


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

        time += CrearMercancias.time - time;

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

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        this.finish();
    }

    /**
     * Método que devuelve la partida actual
     * @return
     */

    public static PanelDeControl getPanelDeControl(){
        return pdc;
    }

    public void setContadorVentanas(int n){
        contadorVentanas = n;
    }

    public int getMedia(){
        return media.getCurrentPosition();
    }
}