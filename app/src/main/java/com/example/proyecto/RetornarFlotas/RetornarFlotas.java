package com.example.proyecto.RetornarFlotas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.Juego;
import com.example.proyecto.MainActivity;
import com.example.proyecto.PanelDeControl;
import com.example.proyecto.R;

public class RetornarFlotas extends Fragment implements View.OnClickListener {

    //Atributos

    private static MediaPlayer media;

    /**
     * Etiquetas
     */

    private TextView castillaRetorno;
    private TextView aragonRetorno;
    private TextView borgonaRetorno;
    private TextView austriaRetorno;
    private TextView PeruRetorno;
    private TextView NGretorno;
    private TextView NEretorno;
    private TextView plataRetorno;

    /**
     * Lista de flotas que han partido.
     */

    private CheckBox radio1;
    private CheckBox radio2;
    private CheckBox radio3;
    private CheckBox radio4;
    private CheckBox radio5;
    private CheckBox radio6;
    private CheckBox radio7;
    private CheckBox radio8;

    private Button botonRetornar;

    /**
     * Declaramos un atributo de tipo PanelControl que se encargará de administrar los datos
     */

    private PanelDeControl control;
    private LinearLayout contenedor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View frag = inflater.inflate(R.layout.fragment_retornar_flotas, container, false);

        control = Juego.getPanelDeControl();

        contenedor = (LinearLayout)frag.findViewById(R.id.linearLayoutRetornarFlotas);

        castillaRetorno = (TextView)frag.findViewById(R.id.castillaRetorno);
        aragonRetorno = (TextView)frag.findViewById(R.id.aragonRetorno);
        borgonaRetorno = (TextView)frag.findViewById(R.id.borgonaRetorno);
        austriaRetorno = (TextView)frag.findViewById(R.id.austriaRetorno);
        PeruRetorno = (TextView)frag.findViewById(R.id.PeruRetorno);
        NGretorno = (TextView)frag.findViewById(R.id.NGretorno);
        NEretorno = (TextView)frag.findViewById(R.id.NEretorno);
        plataRetorno = (TextView)frag.findViewById(R.id.plataRetorno);

        radio1 = (CheckBox)frag.findViewById(R.id.checkBoxCastilla);
        radio2 = (CheckBox)frag.findViewById(R.id.checkBoxAragon);
        radio3 = (CheckBox)frag.findViewById(R.id.checkBoxBorgona);
        radio4 = (CheckBox)frag.findViewById(R.id.checkBoxAustria);
        radio5 = (CheckBox)frag.findViewById(R.id.checkBoxPeru);
        radio6 = (CheckBox)frag.findViewById(R.id.checkBoxNE);
        radio7 = (CheckBox)frag.findViewById(R.id.checkBoxNG);
        radio8 = (CheckBox)frag.findViewById(R.id.checkBoxPlata);

        botonRetornar = (Button)frag.findViewById(R.id.botonRetornarFlotas);

        colocarEtiquetas();
        colocarCheckBox();
        botonRetornar.setOnClickListener(this);

        return frag;
    }

    //M�todos

    /**
     * M�todo que coloca el panel en la ventana.
     */

    /**
     * M�todo que coloca las etiquetas en el panel en el caso de que dicha flota haya partido.
     */

    public void colocarEtiquetas() {



        castillaRetorno.setText("Castilla: "+" está a una distacia de "+control.getEspana().getCastilla().getFlota().getDestino()+" km de su puerto");
        aragonRetorno.setText("Aragon: "+" está a una distacia de "+control.getEspana().getAragon().getFlota().getDestino()+" km de su puerto");
        borgonaRetorno.setText("Borgoña: "+" está a una distacia de "+control.getEspana().getBorgona().getFlota().getDestino()+" km de su puerto");
        austriaRetorno.setText("Austria: "+" está a una distacia de "+control.getEspana().getAustria().getFlota().getDestino()+" km de su puerto");
        NEretorno.setText("Nueva España: "+" está a una distacia de "+control.getEspana().getNuevaEspana().getFlota().getDestino()+" km de su puerto");
        NGretorno.setText("Nueva Granada: "+" está a una distacia de "+control.getEspana().getNuevaGranda().getFlota().getDestino()+" km de su puerto");
        PeruRetorno.setText("Peru: "+" está a una distacia de "+control.getEspana().getPeru().getFlota().getDestino()+" km de su puerto");
        plataRetorno.setText("Plata: "+" está a una distacia de "+control.getEspana().getPlata().getFlota().getDestino()+" km de su puerto");

//        etiqueta9.setFont(new Font("Serif", Font.PLAIN, 20));


        if(control.getEspana().getCastilla().getFlota().isDisponible()) {
            castillaRetorno.setVisibility(View.INVISIBLE);
        }

        if(control.getEspana().getAragon().getFlota().isDisponible()) {
            aragonRetorno.setVisibility(View.INVISIBLE);
        }
        if(control.getEspana().getBorgona().getFlota().isDisponible()) {
            borgonaRetorno.setVisibility(View.INVISIBLE);
        }

        if(control.getEspana().getAustria().getFlota().isDisponible()) {
            austriaRetorno.setVisibility(View.INVISIBLE);
        }

        if(control.getEspana().getNuevaEspana().getFlota().isDisponible()) {
            NEretorno.setVisibility(View.INVISIBLE);
        }

        if (control.getEspana().getNuevaGranda().getFlota().isDisponible()) {
            NGretorno.setVisibility(View.INVISIBLE);
        }

        if(control.getEspana().getPeru().getFlota().isDisponible()) {
            PeruRetorno.setVisibility(View.INVISIBLE);
        }

        if(control.getEspana().getPlata().getFlota().isDisponible()) {
            plataRetorno.setVisibility(View.INVISIBLE);
        }

    }

    /**
     * M�todo el cual se seleccionar� la flota que quiera que vuelva al lugar de origen.
     * Una vez seleccionada y dada al boton se eliminar� de la lista.
     */

    public void colocarCheckBox() {

        if(control.getEspana().getCastilla().getFlota().isDisponible()) {
            radio1.setEnabled(false);
            radio1.setVisibility(View.INVISIBLE);
        }

        if(control.getEspana().getAragon().getFlota().isDisponible()) {
            radio2.setEnabled(false);
            radio2.setVisibility(View.INVISIBLE);
        }

        if(control.getEspana().getBorgona().getFlota().isDisponible()) {
            radio3.setEnabled(false);
            radio3.setVisibility(View.INVISIBLE);
        }

        if(control.getEspana().getAustria().getFlota().isDisponible()) {
            radio4.setEnabled(false);
            radio4.setVisibility(View.INVISIBLE);
        }

        if(control.getEspana().getPeru().getFlota().isDisponible()) {
            radio5.setEnabled(false);
            radio5.setVisibility(View.INVISIBLE);
        }

        if(control.getEspana().getNuevaEspana().getFlota().isDisponible()) {
            radio6.setEnabled(false);
            radio6.setVisibility(View.INVISIBLE);
        }

        if (control.getEspana().getNuevaGranda().getFlota().isDisponible()) {
            radio7.setEnabled(false);
            radio7.setVisibility(View.INVISIBLE);
        }

        if(control.getEspana().getPlata().getFlota().isDisponible()) {
            radio8.setEnabled(false);
            radio8.setVisibility(View.INVISIBLE);
        }

    }

    /**
     * M�todo que retorna las flotas seleccionadas en los RadioButton.
     */

    @Override
    public void onClick(View v) {

        //        int opcion = JOptionPane.showConfirmDialog(null, "Si no ha marcado alguna Flota, no estará disponible el siguiente turno. ¿ Está seguro de su decisión ?");

        AlertDialog.Builder adb=new AlertDialog.Builder(getActivity());
        adb.setTitle("Confirmar");
        adb.setMessage("Si no ha marcado alguna Flota, no estará disponible el siguiente turno. ¿ Está seguro de su decisión ?");
        adb.setNegativeButton("Volver atrás", null);
        adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                if(!radio1.isChecked() && radio1.getVisibility()==View.VISIBLE) {
                    control.meterZonaSinFlota(control.getEspana().getCastilla().getNombre()+","+control.getEspana().getCastilla().getFlota().getDestino());

                }else if(radio1.isChecked() && radio1.getVisibility()==View.VISIBLE) {
                    Toast.makeText(getActivity(), "castilla 1", Toast.LENGTH_SHORT).show();
                    control.quitarReinoDeZonasSinFlota(control.getEspana().getCastilla().getNombre()+","+control.getEspana().getCastilla().getFlota().getDestino());
                    radio1.setVisibility(View.INVISIBLE);
                    castillaRetorno.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivity(), "castilla 2", Toast.LENGTH_SHORT).show();
                }

                if(!radio2.isChecked() && radio2.getVisibility()==View.VISIBLE) {
                    control.meterZonaSinFlota(control.getEspana().getAragon().getNombre()+","+control.getEspana().getAragon().getFlota().getDestino());


                }else if(radio2.isChecked() && radio2.getVisibility()==View.VISIBLE) {
                    control.quitarReinoDeZonasSinFlota(control.getEspana().getAragon().getNombre()+","+control.getEspana().getAragon().getFlota().getDestino());
                    radio2.setVisibility(View.INVISIBLE);
                    aragonRetorno.setVisibility(View.INVISIBLE);
                }

                if(!radio3.isChecked() && radio3.getVisibility()==View.VISIBLE) {
                    control.meterZonaSinFlota(control.getEspana().getBorgona().getNombre()+","+control.getEspana().getBorgona().getFlota().getDestino());

                }else if(radio3.isChecked() && radio3.getVisibility()==View.VISIBLE) {
                    control.quitarReinoDeZonasSinFlota(control.getEspana().getBorgona().getNombre()+","+control.getEspana().getBorgona().getFlota().getDestino());
                    radio3.setVisibility(View.INVISIBLE);
                    borgonaRetorno.setVisibility(View.INVISIBLE);
                }

                if(!radio4.isChecked() && radio4.getVisibility()==View.VISIBLE) {
                    control.meterZonaSinFlota(control.getEspana().getAustria().getNombre()+","+control.getEspana().getAustria().getFlota().getDestino());

                }else if(radio4.isChecked() && radio4.getVisibility()==View.VISIBLE) {
                    control.quitarReinoDeZonasSinFlota(control.getEspana().getAustria().getNombre()+","+control.getEspana().getAustria().getFlota().getDestino());
                    radio4.setVisibility(View.INVISIBLE);
                    austriaRetorno.setVisibility(View.INVISIBLE);
                }

                if(!radio5.isChecked() && radio5.getVisibility()==View.VISIBLE) {
                    control.meterZonaSinFlota(control.getEspana().getPeru().getNombre()+","+control.getEspana().getPeru().getFlota().getDestino());

                }else if(radio5.isChecked() && radio5.getVisibility()==View.VISIBLE) {
                    control.quitarReinoDeZonasSinFlota(control.getEspana().getPeru().getNombre()+","+control.getEspana().getPeru().getFlota().getDestino());
                    radio5.setVisibility(View.INVISIBLE);
                    PeruRetorno.setVisibility(View.INVISIBLE);
                }

                if(!radio6.isChecked() && radio6.getVisibility()==View.VISIBLE) {
                    control.meterZonaSinFlota(control.getEspana().getNuevaEspana().getNombre()+","+control.getEspana().getNuevaEspana().getFlota().getDestino());


                }else if(radio6.isChecked() && radio6.getVisibility()==View.VISIBLE) {
                    control.quitarReinoDeZonasSinFlota(control.getEspana().getNuevaEspana().getNombre()+","+control.getEspana().getNuevaEspana().getFlota().getDestino());
                    radio6.setVisibility(View.INVISIBLE);
                    NEretorno.setVisibility(View.INVISIBLE);
                }


                if(!radio7.isChecked() && radio7.getVisibility()==View.VISIBLE) {
                    control.meterZonaSinFlota(control.getEspana().getNuevaGranda().getNombre()+","+control.getEspana().getNuevaGranda().getFlota().getDestino());


                }else if(radio7.isChecked() && radio7.getVisibility()==View.VISIBLE) {
                    control.quitarReinoDeZonasSinFlota(control.getEspana().getNuevaGranda().getNombre()+","+control.getEspana().getNuevaGranda().getFlota().getDestino());
                    radio7.setVisibility(View.INVISIBLE);
                    NGretorno.setVisibility(View.INVISIBLE);
                }

                if(!radio8.isChecked() && radio8.getVisibility()==View.VISIBLE) {
                    control.meterZonaSinFlota(control.getEspana().getPlata().getNombre()+","+control.getEspana().getPlata().getFlota().getDestino());

                }else if(radio8.isChecked() && radio8.getVisibility()==View.VISIBLE) {
                    control.quitarReinoDeZonasSinFlota(control.getEspana().getPlata().getNombre()+","+control.getEspana().getPlata().getFlota().getDestino());
                    radio8.setVisibility(View.INVISIBLE);
                    plataRetorno.setVisibility(View.INVISIBLE);
                }

//                AlertDialog.Builder adb2 = new AlertDialog.Builder(getActivity());
//                adb2.setTitle("Confirmar");
//                adb2.setMessage("Las Flotas Seleccionadas, volverán a su Puerto de Origen en el siguiente turno");
//                adb2.setPositiveButton("Ok", null);
//
//                adb2.show();

                control.iteradorZonasSinFLota();

                try {

                    control.cambiarTruno();
                    SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("sec", Juego.getMedia());
                    editor.commit();

                    if(control.getZonasSinProductosDemandados().size()==8) {
                        AlertDialog.Builder adb3 = new AlertDialog.Builder(getActivity());
                        adb3.setTitle("Fin");
                        adb3.setMessage("Fin de la partida, ");
                        adb3.setNegativeButton("Volver al menú de Inicio", new AlertDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(getActivity(), MainActivity.class);
                                Juego.getPanelDeControl().setContadorTurnos(0);
                                startActivity(i);
                                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                getActivity().finish();
                            }
                        });
                        adb3.show();
                    }


                    if(Juego.getContador()>0){
                        Juego.setContador(0);
                    }else if(Juego.getContadorVentanas()>0){
                        Juego.setContadorVentanas(0);
                    }

                    getActivity().getSupportFragmentManager().popBackStack();

//                    Intent i = new Intent(getActivity(), Juego.class);
//                    startActivity(i);
//                    getActivity().finish();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }});
        adb.show();
    }

}

