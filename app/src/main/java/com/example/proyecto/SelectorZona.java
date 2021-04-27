package com.example.proyecto;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.proyecto.Flotas.EnviarFlotasAragon;
import com.example.proyecto.Flotas.EnviarFlotasAustria;
import com.example.proyecto.Flotas.EnviarFlotasBorgona;
import com.example.proyecto.Flotas.EnviarFlotasCastilla;
import com.example.proyecto.Flotas.EnviarFlotasNuevaEspana;
import com.example.proyecto.Flotas.EnviarFlotasNuevaGranada;
import com.example.proyecto.Flotas.EnviarFlotasPeru;
import com.example.proyecto.Flotas.EnviarFlotasPlata;
import com.example.proyecto.Mercancias.CrearMercanciasAragon;
import com.example.proyecto.Mercancias.CrearMercanciasAustria;
import com.example.proyecto.Mercancias.CrearMercanciasBorgona;
import com.example.proyecto.Mercancias.CrearMercanciasCastilla;
import com.example.proyecto.Mercancias.CrearMercanciasNuevaEspana;
import com.example.proyecto.Mercancias.CrearMercanciasNuevaGranada;
import com.example.proyecto.Mercancias.CrearMercanciasPeru;
import com.example.proyecto.Mercancias.CrearMercanciasPlata;

public class SelectorZona extends Fragment implements View.OnClickListener{


    private Button botonCastillaMerc;
    private Button botonAragonMerc;
    private Button botonAustriaMerc;
    private Button botonBorgonaMerc;
    private Button botonNuevaEspanaMerc;
    private Button botonNuevaGranadaMerc;
    private Button botonPeruMerc;
    private Button botonPlataMerc;
    private int zona;
    private PanelDeControl control;
    private boolean disponible;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View frag = inflater.inflate(R.layout.fragment_selector_zona, container, false);

        botonAragonMerc = (Button)frag.findViewById(R.id.botonAragonMerc);
        botonAustriaMerc = (Button)frag.findViewById(R.id.botonAustriaMerc);
        botonCastillaMerc = (Button)frag.findViewById(R.id.botonCastillaMerc);
        botonBorgonaMerc = (Button)frag.findViewById(R.id.botonBorgonaMerc);
        botonNuevaEspanaMerc = (Button)frag.findViewById(R.id.botonNuevaEspanaMerc);
        botonNuevaGranadaMerc = (Button)frag.findViewById(R.id.botonNuevaGranadaMerc);
        botonPeruMerc = (Button)frag.findViewById(R.id.botonPeruMerc);
        botonPlataMerc = (Button)frag.findViewById(R.id.botonPlataMerc);

        botonAragonMerc.setOnClickListener(this);
        botonAustriaMerc.setOnClickListener(this);
        botonCastillaMerc.setOnClickListener(this);
        botonBorgonaMerc.setOnClickListener(this);
        botonNuevaEspanaMerc.setOnClickListener(this);
        botonNuevaGranadaMerc.setOnClickListener(this);
        botonPeruMerc.setOnClickListener(this);
        botonPlataMerc.setOnClickListener(this);

        control = Juego.getPanelDeControl();

        return frag;
    }

    public SelectorZona(int zona){
        this.zona=zona;
    }

    /**
     * Método onClick en el que se accede a las diferentes activities ( Reinos ) que hay para crear mercancias
     * @param v
     */

    @Override
    public void onClick(View v) {
        Intent intent=null;

        AlertDialog.Builder adb=new AlertDialog.Builder(getActivity());
        adb.setTitle("Confirmar");
        adb.setMessage("La flota no está disponible");
        adb.setPositiveButton("Volver atrás", null);

        disponible = false;

        if(v.getId() == botonCastillaMerc.getId()) {
            if (control.getEspana().getCastilla().getFlota().isDisponible()) {
                switch (this.zona) {
                    case 1:
                        intent = new Intent(getActivity(), CrearMercanciasCastilla.class);
                        break;
                    case 2:
                        intent = new Intent(getActivity(), CrearFlotaCastilla.class);
                        intent.putExtra("zona", "Castilla");
                        break;
                    case 3:
                        intent = new Intent(getActivity(), EnviarFlotasCastilla.class);
                        break;
                }
            }else{
                disponible = true;
                adb.show();
            }

        }else if(v.getId() == botonAragonMerc.getId()){
            if (control.getEspana().getAragon().getFlota().isDisponible()) {
                switch (this.zona) {
                    case 1:
                        intent = new Intent(getActivity(), CrearMercanciasAragon.class);
                        break;
                    case 2:
                        intent = new Intent(getActivity(), CrearFlotaCastilla.class);
                        intent.putExtra("zona", "Aragon");
                        break;
                    case 3:
                        intent = new Intent(getActivity(), EnviarFlotasAragon.class);
                        break;
                }
            }else{
                disponible = true;
                adb.show();
            }

        }else if(v.getId() == botonBorgonaMerc.getId()){
            if (control.getEspana().getBorgona().getFlota().isDisponible()) {
                switch (this.zona) {
                    case 1:
                        intent = new Intent(getActivity(), CrearMercanciasBorgona.class);
                        break;
                    case 2:
                        intent = new Intent(getActivity(), CrearFlotaCastilla.class);
                        intent.putExtra("zona", "Borgona");
                        break;
                    case 3:
                        intent = new Intent(getActivity(), EnviarFlotasBorgona.class);
                        break;
                }
            }else{
                disponible = true;
                adb.show();
            }

        }else if(v.getId() == botonAustriaMerc.getId()){
            if (control.getEspana().getAustria().getFlota().isDisponible()) {
                switch (this.zona) {
                    case 1:
                        intent = new Intent(getActivity(), CrearMercanciasAustria.class);
                        break;
                    case 2:
                        intent = new Intent(getActivity(), CrearFlotaCastilla.class);
                        intent.putExtra("zona", "Austria");
                        break;
                    case 3:
                        intent = new Intent(getActivity(), EnviarFlotasAustria.class);
                        break;
                }
            }else{
                disponible = true;
                adb.show();
            }

        }else if(v.getId() == botonNuevaEspanaMerc.getId()){
            if (control.getEspana().getNuevaEspana().getFlota().isDisponible()) {
                switch (this.zona) {
                    case 1:
                        intent = new Intent(getActivity(), CrearMercanciasNuevaEspana.class);
                        break;
                    case 2:
                        intent = new Intent(getActivity(), CrearFlotaCastilla.class);
                        intent.putExtra("zona", "NuevaEspana");
                        break;
                    case 3:
                        intent = new Intent(getActivity(), EnviarFlotasNuevaEspana.class);
                        break;
                }
            }else{
                disponible = true;
                adb.show();
            }

        }else if(v.getId() == botonNuevaGranadaMerc.getId()){
            if (control.getEspana().getNuevaGranda().getFlota().isDisponible()) {
                switch (this.zona) {
                    case 1:
                        intent = new Intent(getActivity(), CrearMercanciasNuevaGranada.class);
                        break;
                    case 2:
                        intent = new Intent(getActivity(), CrearFlotaCastilla.class);
                        intent.putExtra("zona", "NuevaGranada");
                        break;
                    case 3:
                        intent = new Intent(getActivity(), EnviarFlotasNuevaGranada.class);
                        break;
                }
            }else{
                disponible = true;
                adb.show();
            }

        }else if(v.getId() == botonPeruMerc.getId()){
            if (control.getEspana().getPeru().getFlota().isDisponible()) {
                switch (this.zona) {
                    case 1:
                        intent = new Intent(getActivity(), CrearMercanciasPeru.class);
                        break;
                    case 2:
                        intent = new Intent(getActivity(), CrearFlotaCastilla.class);
                        intent.putExtra("zona", "Peru");
                        break;
                    case 3:
                        intent = new Intent(getActivity(), EnviarFlotasPeru.class);
                        break;
                }
            }else{
                disponible = true;
                adb.show();
            }

        }else if(v.getId() == botonPlataMerc.getId()){
            if (control.getEspana().getPlata().getFlota().isDisponible()) {
                switch (this.zona) {
                    case 1:
                        intent = new Intent(getActivity(), CrearMercanciasPlata.class);
                        break;
                    case 2:
                        intent = new Intent(getActivity(), CrearFlotaCastilla.class);
                        intent.putExtra("zona", "Plata");
                        break;
                    case 3:
                        intent = new Intent(getActivity(), EnviarFlotasPlata.class);
                        break;
                }
            }else{
                disponible = true;
                adb.show();
            }
        }

        if(disponible != true) {
            intent.putExtra("segundosMerc", ((Juego) getActivity()).getMedia());
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.entrada, R.anim.salida);
            getActivity().getSupportFragmentManager().popBackStack();
            ((Juego) getActivity()).setContadorVentanas(0);
        }

        disponible = false;
    }

}