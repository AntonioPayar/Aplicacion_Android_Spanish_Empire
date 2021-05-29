package com.example.proyecto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.proyecto.Clases.Demandas;
import com.example.proyecto.RetornarFlotas.RetornarFlotas;

public class Juego extends AppCompatActivity {

    private View decorView;
    private static MediaPlayer media;
    private static PanelDeControl pdc;
    private static int time;
    private static int contador = 0;
    private static int contadorVentanas = 0;
    private boolean mapa=false;
    private boolean frag_prod=false;
    private int sec;
    private ImageView monarca;
    private static boolean tutorial;
    private String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

//        tutorial = getIntent().getBooleanExtra("tutorial", false);
//
//        if(!tutorial){
//            setContentView(R.layout.activity_juego);
//        }else{
//            setContentView(R.layout.activity_juego_tutorial);
//        }

        contadorVentanas = 0;
        frag_prod = false;
        sec = 0;
        monarca = (ImageView)findViewById(R.id.imagenMonarca);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        try {
//            if(pdc.getContadorTurnos() == 0)
            this.usuario=getIntent().getStringExtra("user");
            pdc = new PanelDeControl(this,usuario);
            TextView texto_turno = findViewById(R.id.textView20);
            texto_turno.setText("TURNO\n"+this.pdc.getContadorTurnos());
//            }else{
////                sec = sharedPref.getInt("sec", 0);
////                editor.commit();
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "bbb"+pdc.getContadorTurnos(), Toast.LENGTH_SHORT).show();

//        Toast.makeText(this, "aaaa "+sec, Toast.LENGTH_SHORT).show();

//        int sec = getIntent().getIntExtra("sec", 0);
        media = MediaPlayer.create(this, R.raw.partida);
        media.setVolume(10, 10);
        media.setLooping(true);
        media.seekTo(sec);
        media.start();

        colocarImagenMonarca();

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

    protected void comprobarSublevacionesAmerica(){
        if(pdc.getEspana().getNuevaEspana().isSublevaciones()){
            ImageButton boton=findViewById(R.id.boton_nuevaespana);
            boton.setImageResource(R.drawable.nodispo);
        }
        if(pdc.getEspana().getNuevaGranda().isSublevaciones()){
            ImageButton boton=findViewById(R.id.boton_nuevagranada);
            boton.setImageResource(R.drawable.nodispo);
        }

        if(pdc.getEspana().getPeru().isSublevaciones()){
            ImageButton boton=findViewById(R.id.boton_peru);
            boton.setImageResource(R.drawable.nodispo);
        }

        if(pdc.getEspana().getPlata().isSublevaciones()){
            ImageButton boton=findViewById(R.id.boton_plata);
            boton.setImageResource(R.drawable.nodispo);
        }
    }

    /**Onclick al pulsal el ImagenView Castilla**/
    public void botoncastilla(View view){
        ImageButton boton=findViewById(R.id.boton_castilla);
        if(!this.pdc.getEspana().getCastilla().isSublevaciones()){
            if(mapa==false && contador==0 && contadorVentanas==0){
                abrirFrameProductos(1);
            }else{
                Toast.makeText(this, "contadro"+contador, Toast.LENGTH_SHORT).show();
            }
        }else{
            boton.setImageResource(R.drawable.nodispo);
            Toast.makeText(this, "Castilla esta en sublevación", Toast.LENGTH_SHORT).show();
        }

    }
    /**Onclick al pulsal el ImagenView Aragon**/
    public void botonaragon(View view){
        ImageButton boton=findViewById(R.id.boton_aragon);
        if(!this.pdc.getEspana().getAragon().isSublevaciones()){
            if(mapa==false && contador==0 && contadorVentanas==0){
                abrirFrameProductos(2);
            }else{
                Toast.makeText(this, "contadro"+contador, Toast.LENGTH_SHORT).show();
            }
        }else{
            boton.setImageResource(R.drawable.nodispo);
            Toast.makeText(this, "Aragon esta en sublevación", Toast.LENGTH_SHORT).show();
        }

    }
    /**Onclick al pulsal el ImagenView Flandes**/
    public void botonborgona(View view){
        ImageButton boton=findViewById(R.id.boton_borgona);
        if(!this.pdc.getEspana().getBorgona().isSublevaciones()){
            if(mapa==false && contador==0 && contadorVentanas==0){
                abrirFrameProductos(3);
            }else{
                Toast.makeText(this, "contadro"+contador, Toast.LENGTH_SHORT).show();
            }
        }else{
            boton.setImageResource(R.drawable.nodispo);
            Toast.makeText(this, "Borgoña esta en sublevación", Toast.LENGTH_SHORT).show();
        }

    }
    /**Onclick al pulsal el ImagenView Austia**/
    public void botonhasburgo(View view){
        ImageButton boton=findViewById(R.id.boton_hasburgo);
        if(!this.pdc.getEspana().getAustria().isSublevaciones()){
            if(mapa==false && contador==0 && contadorVentanas==0){
                abrirFrameProductos(4);
            }else{
                Toast.makeText(this, "contadro"+contador, Toast.LENGTH_SHORT).show();
            }
        }else{
            boton.setImageResource(R.drawable.nodispo);
            Toast.makeText(this, "Austria esta en sublevación", Toast.LENGTH_SHORT).show();
        }
    }
    /**Onclick al pulsal el ImagenView NuevaCastilla**/
    public void botonNuevaEspana(View view){
        ImageButton boton=findViewById(R.id.boton_nuevaespana);
        if(!this.pdc.getEspana().getNuevaEspana().isSublevaciones()){
            if(contador==0){
                abrirFrameProductos(5);
            }else{
                Toast.makeText(this, "contadro"+contador, Toast.LENGTH_SHORT).show();
            }
        }else{
            boton.setImageResource(R.drawable.nodispo);
            Toast.makeText(this, "N.España esta en sublevación", Toast.LENGTH_SHORT).show();
        }
        comprobarSublevacionesAmerica();
    }
    /**Onclick al pulsal el ImagenView Nueva Granada**/
    public void botonNuevaGranada(View view){
        ImageButton boton=findViewById(R.id.boton_nuevagranada);
        if(!this.pdc.getEspana().getNuevaGranda().isSublevaciones()){
            if(contador==0){
                abrirFrameProductos(6);
            }else{
                Toast.makeText(this, "contadro"+contador, Toast.LENGTH_SHORT).show();
            }
        }else{
            boton.setImageResource(R.drawable.nodispo);
            Toast.makeText(this, "N.Granada esta en sublevación", Toast.LENGTH_SHORT).show();
        }
        comprobarSublevacionesAmerica();
    }
    /**Onclick al pulsal el ImagenView Peru**/
    public void botonPeru(View view){
        ImageButton boton=findViewById(R.id.boton_peru);
        if(!this.pdc.getEspana().getPeru().isSublevaciones()){
            if(contador==0){
                abrirFrameProductos(7);
            }else{
                Toast.makeText(this, "contadro"+contador, Toast.LENGTH_SHORT).show();
            }
        }else{
            boton.setImageResource(R.drawable.nodispo);
            Toast.makeText(this, "Peru esta en sublevación", Toast.LENGTH_SHORT).show();
        }
        comprobarSublevacionesAmerica();
    }
    /**Onclick al pulsal el ImagenView Plata**/
    public void botonPlata(View view){
        ImageButton boton=findViewById(R.id.boton_plata);
        if(!this.pdc.getEspana().getPeru().isSublevaciones()){
            if(contador==0){
                abrirFrameProductos(8);
            }else{
                Toast.makeText(this, "contadro"+contador, Toast.LENGTH_SHORT).show();
            }
        }else{
            boton.setImageResource(R.drawable.nodispo);
            Toast.makeText(this, "Plata esta en sublevación", Toast.LENGTH_SHORT).show();
        }
        comprobarSublevacionesAmerica();
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
            Fragment fragment = new SelectorZona(3);

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

    public void siguienteTurno(View vista) {

        if(contadorVentanas == 0 && this.frag_prod==false) {
            Fragment fragment = new RetornarFlotas();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout2, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            contadorVentanas++;
            //TextView texto_turno=findViewById(R.id.textView20);
            //texto_turno.setText("TURNO\n"+this.pdc.getContadorTurnos());
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

    public void colocarImagenMonarca() {

        Glide.with(getApplicationContext()).load(R.drawable.carlos_i).into(monarca);

        if(pdc.getContadorTurnos()<10) {
            Glide.with(getApplicationContext()).load(R.drawable.carlos_i).into(monarca);

        }else if(pdc.getContadorTurnos()>=10) {
            Glide.with(getApplicationContext()).load(R.drawable.felipe_ii).into(monarca);

        }else if(pdc.getContadorTurnos()>=20) {
            Glide.with(getApplicationContext()).load(R.drawable.felipe_iii).into(monarca);

        }else if(pdc.getContadorTurnos()>=30) {
            Glide.with(getApplicationContext()).load(R.drawable.felipe_iv).into(monarca);

        }else if(pdc.getContadorTurnos()>=40) {
            Glide.with(getApplicationContext()).load(R.drawable.carlos_ii).into(monarca);
        }else
            Glide.with(getApplicationContext()).load(R.drawable.odin).into(monarca);

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

        AlertDialog.Builder adb=new AlertDialog.Builder(this);
        adb.setTitle("Volver al menú");
        adb.setMessage("¿Está seguro de que desea salir de la partida? La partida no se guardará");
        adb.setNegativeButton("Volver atrás", null);
        adb.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });
        adb.show();
    }

    /**
     * Método que devuelve la partida actual
     * @return
     */

    public static PanelDeControl getPanelDeControl(){
        return pdc;
    }

//    public void setContadorVentanas(int n){
//        contadorVentanas = n;
//    }

    public static int getMedia(){
        return media.getCurrentPosition();
    }

    public static void setMedia(int time){ Juego.time = time; }

    public static int getContador(){
        return contador;
    }
    public static int getContadorVentanas(){
        return contadorVentanas;
    }

    public static void setContador(int contador2){
        contador = contador2;
    }
    public static void setContadorVentanas(int contador){
        contadorVentanas = contador;
    }

}