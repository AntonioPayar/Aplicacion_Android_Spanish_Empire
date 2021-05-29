package com.example.proyecto.Flotas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.FragmentoGoogleMaps;
import com.example.proyecto.Juego;
import com.example.proyecto.PanelDeControl;
import com.example.proyecto.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class EnviarFlotasBorgona extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private LinearLayout contenedor;
    private PanelDeControl control;
    private RadioGroup radio;
    private Button embarca;
    private FragmentoGoogleMaps fragmentMaps;
    private String texto;

    private View decorView;
    private MediaPlayer media;
    public static int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_flotas_borgona);

        time = getIntent().getIntExtra("segundosMerc", 4);
        texto = "";

        control = Juego.getPanelDeControl();
        contenedor = (LinearLayout)findViewById(R.id.contenedorBorgona);
        radio = (RadioGroup)findViewById(R.id.radioGroupBorgona);
        embarca = (Button)findViewById(R.id.embarcarBotonBorgona);

        media = MediaPlayer.create(this, R.raw.partida);
        media.setVolume(10, 10);
        media.setLooping(true);
        media.seekTo(time);
        media.start();

        //GOGLE MAPAS
        Fragment fragmentMaps01 = new FragmentoGoogleMaps();
        this.fragmentMaps= (FragmentoGoogleMaps) fragmentMaps01;
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment4,fragmentMaps01).commit();

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
        Iterator it = this.control.getEspana().getBorgona().getFlota().getArrayMercancias().keySet().iterator();
        String texto;
        String nombre;
        int iv = 0;
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        while(it.hasNext()) {
            int id;
            id=(int) it.next();

            texto = this.control.getEspana().getBorgona().getFlota().getArrayMercancias().get(id).getNombre()+" cantidad "+this.control.getEspana().getBorgona().getFlota().getArrayMercancias().get(id).getTotalkg()+" kg ";
            nombre = this.control.getEspana().getBorgona().getFlota().getArrayMercancias().get(id).getNombre().toUpperCase();
            nombre = nombre.substring(13, nombre.length());

            switch(nombre){
                case "HIERRO":
                    iv = R.drawable.hierro;
                    break;
                case "ARROZ":
                    iv = R.drawable.arroz;
                    break;
                case "TOMATE":
                    iv = R.drawable.tomate;
                    break;
                case "PATATA":
                    iv = R.drawable.patata;
                    break;
            }

            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", texto);
            hm.put("listview_image", Integer.toString(iv));
            aList.add(hm);
//            tv.setWidth(contenedor.getWidth());
//            tv.setTextColor(Color.WHITE);
//            tv.setTextSize(15);
//            contenedor.addView(tv);
            contador++;
        }

        String[] from = {"listview_image", "listview_title"};
        int[] to = {R.id.listview_image, R.id.listview_item_title};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.list_view_imagenes, from, to);
        ListView androidListView = (ListView) findViewById(R.id.listViewMercancias);
        androidListView.setAdapter(simpleAdapter);

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

        RadioButton rb = null;
        int contadorPorDefecto = 0;

        if(control.getEspana().getNuevaEspana().isSublevaciones()==false) {
            rb = new RadioButton(getApplicationContext());
            rb.setPadding(5, 5, 5, 5);
            rb.setTextColor(Color.WHITE);
            rb.setTextSize(20);
            rb.setText("Nueva España");
            rb.setId(R.id.borgonaNuevaEspana);
            radio.addView(rb);
        }

        if(control.getEspana().getNuevaGranda().isSublevaciones()==false) {
            rb = new RadioButton(getApplicationContext());
            rb.setPadding(5, 5, 5, 5);
            rb.setTextColor(Color.WHITE);
            rb.setTextSize(20);
            rb.setText("Nueva Granada");
            rb.setId(R.id.borgonaNuevaGranada);
            radio.addView(rb);
        }

        if(control.getEspana().getPeru().isSublevaciones()==false) {
            rb = new RadioButton(getApplicationContext());
            rb.setPadding(5, 5, 5, 5);
            rb.setTextColor(Color.WHITE);
            rb.setTextSize(20);
            rb.setText("Peru");
            rb.setId(R.id.borgonaPeru);
            radio.addView(rb);
        }

        if(control.getEspana().getPlata().isSublevaciones()==false) {
            rb = new RadioButton(getApplicationContext());
            rb.setPadding(5, 5, 5, 5);
            rb.setTextColor(Color.WHITE);
            rb.setTextSize(20);
            rb.setText("Plata");
            rb.setId(R.id.borgonaPlata);
            radio.addView(rb);
        }

        if(control.getEspana().getAustria().isSublevaciones()==false) {
            rb = new RadioButton(getApplicationContext());
            rb.setPadding(5, 5, 5, 5);
            rb.setTextColor(Color.WHITE);
            rb.setTextSize(20);
            rb.setText("Austria");
            rb.setId(R.id.borgonaAustria);
            radio.addView(rb);
        }

        if(control.getEspana().getCastilla().isSublevaciones()==false) {
            rb = new RadioButton(getApplicationContext());
            rb.setPadding(5, 5, 5, 5);
            rb.setTextColor(Color.WHITE);
            rb.setTextSize(20);
            rb.setText("Castilla");
            rb.setId(R.id.borgonaCastilla);
            radio.addView(rb);
        }

        if(control.getEspana().getAragon().isSublevaciones()==false){
            rb = new RadioButton(getApplicationContext());
            rb.setPadding(5, 5, 5, 5);
            rb.setTextColor(Color.WHITE);
            rb.setTextSize(20);
            rb.setText("Aragon");
            rb.setId(R.id.borgonaAragon);
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

        if(control.getEspana().getBorgona().getFlota().getArrayMercancias().size()!=0) {
            try {
                if(texto.equals("")){
                    Toast.makeText(this, "Debe seleccionar un reino", Toast.LENGTH_SHORT).show();
                }else {
                    switch (texto) {
                        case "NUEVA ESPAÑA":
                            control.getEspana().enviarFlota(control.getEspana().getBorgona(), control.getEspana().getNuevaEspana());
                            System.out.println("Importaciones Nueva España");
                            control.getEspana().getNuevaEspana().verMercanciasImportacion();
                            break;
                        case "NUEVA GRANADA":
                            control.getEspana().enviarFlota(control.getEspana().getBorgona(), control.getEspana().getNuevaGranda());
                            System.out.println("Importaciones Nueva Granada");
                            control.getEspana().getNuevaGranda().verMercanciasImportacion();
                            break;
                        case "PERU":
                            control.getEspana().enviarFlota(control.getEspana().getBorgona(), control.getEspana().getPeru());
                            System.out.println("Importaciones Peru");
                            control.getEspana().getPeru().verMercanciasImportacion();
                            break;
                        case "PLATA":
                            control.getEspana().enviarFlota(control.getEspana().getBorgona(), control.getEspana().getPlata());
                            System.out.println("Importaciones Plata");
                            control.getEspana().getPlata().verMercanciasImportacion();
                            break;
                        case "AUSTRIA":
                            control.getEspana().enviarFlota(control.getEspana().getBorgona(), control.getEspana().getAustria());
                            System.out.println("Importaciones Austria ");
                            control.getEspana().getAustria().verMercanciasImportacion();
                            break;
                        case "ARAGON":
                            control.getEspana().enviarFlota(control.getEspana().getBorgona(), control.getEspana().getAragon());
                            System.out.println("Importaciones Aragón");
                            control.getEspana().getBorgona().verMercanciasImportacion();
                            break;
                        case "CASTILLA":
                            control.getEspana().enviarFlota(control.getEspana().getBorgona(), control.getEspana().getCastilla());
                            System.out.println("Importaciones Castilla");
                            control.getEspana().getCastilla().verMercanciasImportacion();
                            break;
                        default:
//                        throw new IllegalArgumentException(box2.getSelectedItem().toString().toUpperCase());
                            Toast.makeText(this, "Debe seleccionar un reino", Toast.LENGTH_SHORT).show();
                    }
                    contenedor.removeAllViews();
                    Toast.makeText(this, "Flota enviada", Toast.LENGTH_SHORT).show();
                }


//                jmenu.removeAll();

                System.out.println("Mercancias Reino Castilla");
                control.getEspana().getAragon().verMercancias();
                System.out.println("Mercancias FLota Castilla");
                control.getEspana().getAragon().getFlota().verMercancias();


            }catch (Exception o) {
                // TODO: handle exception
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

        int id;
        RadioButton rb;
        this.fragmentMaps.ponerPutoOrigen("Borgona");

        switch (checkedId) {
            case R.id.borgonaAragon:
                id = group.getCheckedRadioButtonId();
                rb = (RadioButton)findViewById(id);
                texto = rb.getText().toString().toUpperCase();
                this.fragmentMaps.cambiarmapa("Aragon");
                break;
            case R.id.borgonaAustria:
                id = group.getCheckedRadioButtonId();
                rb = (RadioButton)findViewById(id);
                texto = rb.getText().toString().toUpperCase();
                this.fragmentMaps.cambiarmapa("Austria");
                break;
            case R.id.borgonaCastilla:
                id = group.getCheckedRadioButtonId();
                rb = (RadioButton)findViewById(id);
                texto = rb.getText().toString().toUpperCase();
                this.fragmentMaps.cambiarmapa("Castilla");
                break;
            case R.id.borgonaNuevaEspana:
                id = group.getCheckedRadioButtonId();
                rb = (RadioButton)findViewById(id);
                texto = rb.getText().toString().toUpperCase();
                this.fragmentMaps.cambiarmapa("Nueva Espana");
                break;
            case R.id.borgonaNuevaGranada:
                id = group.getCheckedRadioButtonId();
                rb = (RadioButton)findViewById(id);
                texto = rb.getText().toString().toUpperCase();
                this.fragmentMaps.cambiarmapa("Nueva Granada");
                break;
            case R.id.borgonaPeru:
                id = group.getCheckedRadioButtonId();
                rb = (RadioButton)findViewById(id);
                texto = rb.getText().toString().toUpperCase();
                this.fragmentMaps.cambiarmapa("Peru");
                break;
            case R.id.borgonaPlata:
                id = group.getCheckedRadioButtonId();
                rb = (RadioButton)findViewById(id);
                texto = rb.getText().toString().toUpperCase();
                this.fragmentMaps.cambiarmapa("Plata");
                break;
        }

    }
}


