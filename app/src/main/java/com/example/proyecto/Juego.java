package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Juego extends AppCompatActivity {

    private View decorView;
    private MediaPlayer media;
    private static PanelDeControl pdc;
    private static int time;
    private int contador = 0;
    private int contadorVentanas = 0;
    private boolean mapa=false;
    private boolean frag_prod=false;

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
        FragmentTransaction transaction ;
        //Comprobar que ha cerrado las producciones primero
        if(this.frag_prod==false && contadorVentanas==0){
            //Si mapa es true significa que la trasicion de Fragments es de America a Europa
            if(this.mapa==true){
                this.mapa=false;
                this.contador=0;
                this.frag_prod=false;
                FragmentManager fragMgr = this.getSupportFragmentManager();
                mapa2= fragMgr.findFragmentById(R.id.fragment3);
                if(mapa2!=null){
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.remove(mapa2);
                    //transaction.addToBackStack("remove1");
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                    transaction.commit();
                }else{
                    Toast.makeText(this, "Error transicion mapa", Toast.LENGTH_SHORT).show();
                }
                //America a Europa
            }else{
                /**Tenemos que guardar el fragment de Europa como Fragmento "eurpoa" para tenerlo en el "historial" de los FragemntTrasction y si queremos volver
                 * y que todo funcione como la primera vez que abrimos la aplicacion  tenemos que eliminar el fragment de America con el remove() **/
                this.mapa=true;
                //this.contador=0;
                this.frag_prod=false;
                mapa2 = new mapaFragmento03();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.fragment3,mapa2,"europa");
                transaction.addToBackStack("europa");
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.commit();
                //Europa a America
            }
        }else{
            Toast.makeText(this, "Cierre las Producciones", Toast.LENGTH_SHORT).show();
        }
    }
    /**Onclick al pulsal el ImagenView Castilla**/
    public void botoncastilla(View view){
        if(mapa==false && contador==0 && contadorVentanas==0){
            abrirFrameProductos(1);
        }else{
            Toast.makeText(this, "contadro"+contador, Toast.LENGTH_SHORT).show();
        }
    }
    /**Onclick al pulsal el ImagenView Aragon**/
    public void botonaragon(View view){
        if(mapa==false && contador==0 && contadorVentanas==0){
            abrirFrameProductos(2);
        }else{
            Toast.makeText(this, "contadro"+contador, Toast.LENGTH_SHORT).show();
        }
    }
    /**Onclick al pulsal el ImagenView Flandes**/
    public void botonborgona(View view){
        if(mapa==false && contador==0 && contadorVentanas==0){
            abrirFrameProductos(3);
        }else{
            Toast.makeText(this, "contadro"+contador, Toast.LENGTH_SHORT).show();
        }
    }
    /**Onclick al pulsal el ImagenView Austia**/
    public void botonhasburgo(View view){
        if(mapa==false && contador==0 && contadorVentanas==0){
            abrirFrameProductos(4);
        }else{
            Toast.makeText(this, "contadro"+contador, Toast.LENGTH_SHORT).show();
        }
    }
    /**Onclick al pulsal el ImagenView NuevaCastilla**/
    public void botonNuevaEspana(View view){
        if(contador==0){
            abrirFrameProductos(5);
        }else{
            Toast.makeText(this, "contadro"+contador, Toast.LENGTH_SHORT).show();
        }
    }
    /**Onclick al pulsal el ImagenView Nueva Granada**/
    public void botonNuevaGranada(View view){
        if(contador==0){
            abrirFrameProductos(6);
        }else{
            Toast.makeText(this, "contadro"+contador, Toast.LENGTH_SHORT).show();
        }
    }
    /**Onclick al pulsal el ImagenView Peru**/
    public void botonPeru(View view){
        if(contador==0){
            abrirFrameProductos(7);
        }else{
            Toast.makeText(this, "contadro"+contador, Toast.LENGTH_SHORT).show();
        }
    }
    /**Onclick al pulsal el ImagenView Plata**/
    public void botonPlata(View view){
        if(contador==0){
            abrirFrameProductos(8);
        }else{
            Toast.makeText(this, "contadro"+contador, Toast.LENGTH_SHORT).show();
        }
    }

    /**Metodo no terminado se encarga de abrir el frame donde estan los productos de cada zona**/
    public void abrirFrameProductos(int zona){
        if(contador == 0 && this.frag_prod==false) {
            //Toast.makeText(this, "opem", Toast.LENGTH_SHORT).show();
            Fragment fragment;
            FragmentTransaction transaction;
            this.frag_prod=true;
            //Si la el int de la zona es > que 4 se abre el frame de America si es al contrario el de Europa
            if(zona>4){
                fragment = new ProductosFragment(zona);
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameamerica, fragment);
            }else{
                fragment = new ProductosFragment(zona);
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.addToBackStack("Fragment1");
                transaction.replace(R.id.frameuropa, fragment);
            }
            transaction.addToBackStack(null);
            transaction.commit();
        }else{
            Toast.makeText(this, "Cierre las producciones primero", Toast.LENGTH_SHORT).show();
        }
    }

    /**Metodo encargado de cerrar el Fragment de los Productos**/
    public void cerrarFrameProductos(View vista){
        if(this.frag_prod==true){
            this.getSupportFragmentManager().popBackStack();
            this.frag_prod=false;
            //Toast.makeText(this, "close", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Ya esta abierto", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Método que abre un fragment en el que se informa el usuario de las demandas / tareas que se debe hacer en los turnos
     * @param vista
     */
    public void abrirDemandas(View vista){

        if(contadorVentanas == 0 && this.frag_prod==false) {
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

        if(contadorVentanas == 0 && this.frag_prod==false) {
            Fragment fragment = new SelectorZona(1);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            contadorVentanas++;
            //abierto = true;
        }else{
            Toast.makeText(this, "Debe cerrar la ventana antes de acceder a otra", Toast.LENGTH_LONG).show();
        }
    }

    public void abrirFlotassMenu(View vista){

        if(contadorVentanas == 0 && this.frag_prod==false) {
            Fragment fragment = new SelectorZona(2);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            contadorVentanas++;
            //abierto = true;
        }else{
            Toast.makeText(this, "Debe cerrar la ventana antes de acceder a otra", Toast.LENGTH_LONG).show();
        }
    }

    public void abrirEnviarFlotas(View vista){

        if(contadorVentanas == 0 && this.frag_prod==false) {
            Fragment fragment = new EnviarFlotas();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            contadorVentanas++;
            //abierto = true;
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

        if(this.contador>0){
            contador--;
        }else if(this.contadorVentanas>0){
            contadorVentanas--;
        }

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

//        time += CrearMercanciasCastilla.time - time;

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

    public static void setMedia(int time){ Juego.time = time; }
}