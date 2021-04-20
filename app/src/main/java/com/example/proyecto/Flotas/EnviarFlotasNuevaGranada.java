package com.example.proyecto.Flotas;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.Juego;
import com.example.proyecto.PanelDeControl;
import com.example.proyecto.R;

import java.util.Iterator;

public class EnviarFlotasNuevaGranada extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private LinearLayout contenedor;
    private PanelDeControl control;
    private RadioGroup radio;
    private Button embarca;
    private ImageView imagen;

    private View decorView;
    private MediaPlayer media;
    public static int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_flotas_nueva_granada);

        time = getIntent().getIntExtra("segundosMerc", 4);

        control = Juego.getPanelDeControl();
        contenedor = (LinearLayout)findViewById(R.id.contenedorNuevaGranada);
        radio = (RadioGroup)findViewById(R.id.radioGroupNuevaGranada);
        embarca = (Button)findViewById(R.id.embarcarBotonNuevaGranada);
        imagen = (ImageView)findViewById(R.id.imageViewNG);
        imagen.setBackgroundResource(R.drawable.continentes_por_defecto);

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

        colocarBox();
        recorrerPaises();
        radio.setOnCheckedChangeListener(this);
        embarca.setOnClickListener(this);
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

    /**
     * MÃ©todo que guarda las mercancias y las coloca en un menÃº.
     * Recorremos las mercancias de la flota del Reino seleccionado y lo introducimos en forma de String en un JmenuItem.
     * Para posteriormente meterlo dentro del JMenu.
     * Para finalizar metemos el JMenu dentro del JMenuBar.
     *
     */

    public void colocarBox() {

        int contador = 0;
        TextView tv;
        Iterator it = this.control.getEspana().getNuevaGranda().getFlota().getArrayMercancias().keySet().iterator();

        while(it.hasNext()) {
            int id;
            id=(int) it.next();

            tv = new TextView(getApplicationContext());
            tv.setText(id+" "+this.control.getEspana().getNuevaGranda().getFlota().getArrayMercancias().get(id).getNombre()+" cantidad "+this.control.getEspana().getNuevaGranda().getFlota().getArrayMercancias().get(id).getTotalkg()+" kg ");
            tv.setWidth(contenedor.getWidth());
            tv.setTextColor(Color.WHITE);
            tv.setTextSize(15);
            contenedor.addView(tv);
            contador++;
        }

        if(contador == 0){
            tv = new TextView(getApplicationContext());
            tv.setText("No hay Mercancias disponibles para transportar");
            tv.setWidth(contenedor.getWidth());
            tv.setTextColor(Color.WHITE);
            tv.setTextSize(15);
            contenedor.removeAllViews();
            contenedor.addView(tv);
        }
    }

    /**
     * Creamos un metodo que se encarga de comprobar antes de crear un Item de JComboBox tiene sublevacion false.
     * Si el pais tiene sublevacion true no se mostrara impidiendo al usuario iteractuar con el mismo.
     */

    public void recorrerPaises() {

        RadioButton rb;
        int contadorPorDefecto = 0;

        if(control.getEspana().getNuevaEspana().isSublevaciones()==false) {
            rb = new RadioButton(getApplicationContext());
            rb.setPadding(5, 5, 5, 5);
            rb.setTextColor(Color.WHITE);
            rb.setTextSize(20);
            rb.setText("Nueva España");
            rb.setId(R.id.NGNuevaEspana);
            radio.addView(rb);
        }

        if(control.getEspana().getCastilla().isSublevaciones()==false) {
            rb = new RadioButton(getApplicationContext());
            rb.setPadding(5, 5, 5, 5);
            rb.setTextColor(Color.WHITE);
            rb.setTextSize(20);
            rb.setText("Castilla");
            rb.setId(R.id.NGcastilla);
            radio.addView(rb);
        }

        if(control.getEspana().getPeru().isSublevaciones()==false) {
            rb = new RadioButton(getApplicationContext());
            rb.setPadding(5, 5, 5, 5);
            rb.setTextColor(Color.WHITE);
            rb.setTextSize(20);
            rb.setText("Peru");
            rb.setId(R.id.NGperu);
            radio.addView(rb);
        }

        if(control.getEspana().getPlata().isSublevaciones()==false) {
            rb = new RadioButton(getApplicationContext());
            rb.setPadding(5, 5, 5, 5);
            rb.setTextColor(Color.WHITE);
            rb.setTextSize(20);
            rb.setText("Plata");
            rb.setId(R.id.NGplata);
            radio.addView(rb);
        }

        if(control.getEspana().getAustria().isSublevaciones()==false) {
            rb = new RadioButton(getApplicationContext());
            rb.setPadding(5, 5, 5, 5);
            rb.setTextColor(Color.WHITE);
            rb.setTextSize(20);
            rb.setText("Austria");
            rb.setId(R.id.NGaustria);
            radio.addView(rb);
        }

        if(control.getEspana().getBorgona().isSublevaciones()==false) {
            rb = new RadioButton(getApplicationContext());
            rb.setPadding(5, 5, 5, 5);
            rb.setTextColor(Color.WHITE);
            rb.setTextSize(20);
            rb.setText("Borgoña");
            rb.setId(R.id.NGborgona);
            radio.addView(rb);
        }

        if(control.getEspana().getAragon().isSublevaciones()==false){
            rb = new RadioButton(getApplicationContext());
            rb.setPadding(5, 5, 5, 5);
            rb.setTextColor(Color.WHITE);
            rb.setTextSize(20);
            rb.setText("Aragon");
            rb.setId(R.id.NGaragon);
            radio.addView(rb);
        }
    }

    /**
     * MÃ©todo que manda la flota al lugar destino seleccionado..
     * Una vez el usuario presiona el boton Embarcar las mercancias se transportaran al pais seleccionado y se pondra la Flota no disponible
     *
     */

    @Override
    public void onClick(View v) {

        int index = radio.indexOfChild(findViewById(radio.getCheckedRadioButtonId()));
        RadioButton r = (RadioButton)findViewById(index);

        if(control.getEspana().getNuevaGranda().getFlota().getArrayMercancias().size()!=0) {
            try {
                String texto = r.getText().toString().toUpperCase();
                if(texto.equals("")){
                    Toast.makeText(this, "Debe seleccionar un reino", Toast.LENGTH_SHORT).show();
                }else{
                    switch (texto) {
                        case "NUEVA ESPAÑA":
                            control.getEspana().enviarFlota(control.getEspana().getNuevaGranda(), control.getEspana().getNuevaEspana());
                            System.out.println("Importaciones Nueva España");
                            control.getEspana().getNuevaEspana().verMercanciasImportacion();
                            break;
                        case "CASTILLA":
                            control.getEspana().enviarFlota(control.getEspana().getNuevaGranda(), control.getEspana().getCastilla());
                            System.out.println("Importaciones Castilla");
                            control.getEspana().getNuevaGranda().verMercanciasImportacion();
                            break;
                        case "PERU":
                            control.getEspana().enviarFlota(control.getEspana().getNuevaGranda(), control.getEspana().getPeru());
                            System.out.println("Importaciones Peru");
                            control.getEspana().getPeru().verMercanciasImportacion();
                            break;
                        case "PLATA":
                            control.getEspana().enviarFlota(control.getEspana().getNuevaGranda(), control.getEspana().getPlata());
                            System.out.println("Importaciones Plata");
                            control.getEspana().getPlata().verMercanciasImportacion();
                            break;
                        case "AUSTRIA":
                            control.getEspana().enviarFlota(control.getEspana().getNuevaGranda(), control.getEspana().getAustria());
                            System.out.println("Importaciones Austria ");
                            control.getEspana().getAustria().verMercanciasImportacion();
                            break;
                        case "BORGOÑA":
                            control.getEspana().enviarFlota(control.getEspana().getNuevaGranda(), control.getEspana().getBorgona());
                            System.out.println("Importaciones Borgoña");
                            control.getEspana().getBorgona().verMercanciasImportacion();
                            break;
                        case "ARAGON":
                            control.getEspana().enviarFlota(control.getEspana().getNuevaGranda(), control.getEspana().getAragon());
                            System.out.println("Importaciones Aragón");
                            control.getEspana().getAragon().verMercanciasImportacion();
                            break;
                        default:
//                        throw new IllegalArgumentException(box2.getSelectedItem().toString().toUpperCase());
                            Toast.makeText(this, "No existe", Toast.LENGTH_SHORT).show();
                    }
                }

//                jmenu.removeAll();
                //Elimino las mercancias de la interfaz
                contenedor.removeAllViews();

                Toast.makeText(this, "Flota enviada", Toast.LENGTH_SHORT).show();

                System.out.println("Mercancias Reino Castilla");
                control.getEspana().getNuevaGranda().verMercancias();
                System.out.println("Mercancias FLota Castilla");
                control.getEspana().getNuevaGranda().getFlota().verMercancias();


            }catch (Exception o) {

            }
        }else {
            Toast.makeText(this, "No hay Mercancias disponibles para transportar", Toast.LENGTH_SHORT).show();
//            TextView txt = new TextView(getApplicationContext());
//            txt.setText("No hay Mercancias disponibles para transportar");
//            contenedor.removeAllViews();
//            contenedor.addView(txt);
//            JOptionPane.showMessageDialog(null,"No hay Mercancias disponibles para transportar");
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

//        radio.clearCheck();

//        View radioButton = group.findViewById(checkedId);
//        int index = radio.indexOfChild(radioButton);

        switch (checkedId) {
            case R.id.NGcastilla:
                Log.i("adfwaf", "4gerokogjerojk");
                imagen.setBackgroundResource(R.drawable.nueva_granada_to_castilla);
                break;
            case R.id.NGNuevaEspana:
                imagen.setBackgroundResource(R.drawable.nueva_granada_to_nueva_espana);
                break;
            case R.id.NGaragon:
                imagen.setBackgroundResource(R.drawable.nueva_granada_to_aragon);
                break;
            case R.id.NGborgona:
                imagen.setBackgroundResource(R.drawable.nueva_granada_to_borgona);
                break;
            case R.id.NGaustria:
                imagen.setBackgroundResource(R.drawable.nueva_granada_to_austria);
                break;
            case R.id.NGperu:
                imagen.setBackgroundResource(R.drawable.nueva_granada_to_peru);
                break;
            case R.id.NGplata:
                imagen.setBackgroundResource(R.drawable.nueva_granada_to_plata);
                break;
        }

    }
}


