package com.example.proyecto;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

        if(v.getId() == botonCastillaMerc.getId()){
            switch (this.zona){
                case 1:
                    intent = new Intent(getActivity(), CrearMercanciasCastilla.class);
                    break;
                case 2:
                    intent = new Intent(getActivity(), CrearFlotaCastilla.class);
                    intent.putExtra("zona", "Castilla");
                    break;
                case 3:
                    break;
            }

        }else if(v.getId() == botonAragonMerc.getId()){
            switch (this.zona){
                case 1:
                    intent = new Intent(getActivity(), CrearMercanciasAragon.class);
                    break;
                case 2:
                    intent = new Intent(getActivity(), CrearFlotaCastilla.class);
                    intent.putExtra("zona", "Aragon");
                    break;
                case 3:
                    break;
            }

        }else if(v.getId() == botonBorgonaMerc.getId()){
            switch (this.zona){
                case 1:
                    intent = new Intent(getActivity(), CrearMercanciasBorgona.class);
                    break;
                case 2:
                    intent = new Intent(getActivity(), CrearFlotaCastilla.class);
                    intent.putExtra("zona", "Borgona");
                    break;
                case 3:
                    break;
            }

        }else if(v.getId() == botonAustriaMerc.getId()){
            switch (this.zona){
                case 1:
                    intent = new Intent(getActivity(), CrearMercanciasAustria.class);
                    break;
                case 2:
                    intent = new Intent(getActivity(), CrearFlotaCastilla.class);
                    intent.putExtra("zona", "Austria");
                    break;
                case 3:
                    break;
            }

        }else if(v.getId() == botonNuevaEspanaMerc.getId()){
            switch (this.zona){
                case 1:
                    intent = new Intent(getActivity(), CrearMercanciasNuevaEspana.class);
                    break;
                case 2:
                    intent = new Intent(getActivity(), CrearFlotaCastilla.class);
                    intent.putExtra("zona", "NuevaEspana");
                    break;
                case 3:
                    break;
            }

        }else if(v.getId() == botonNuevaGranadaMerc.getId()){
            switch (this.zona){
                case 1:
                    intent = new Intent(getActivity(), CrearMercanciasNuevaGranada.class);
                    break;
                case 2:
                    intent = new Intent(getActivity(), CrearFlotaCastilla.class);
                    intent.putExtra("zona", "NuevaGranada");
                    break;
                case 3:
                    break;
            }

        }else if(v.getId() == botonPeruMerc.getId()){
            switch (this.zona){
                case 1:
                    intent = new Intent(getActivity(), CrearMercanciasPeru.class);
                    break;
                case 2:
                    intent = new Intent(getActivity(), CrearFlotaCastilla.class);
                    intent.putExtra("zona", "Peru");
                    break;
                case 3:
                    break;
            }

        }else if(v.getId() == botonPlataMerc.getId()){
            switch (this.zona){
                case 1:
                    intent = new Intent(getActivity(), CrearMercanciasPlata.class);
                    break;
                case 2:
                    intent = new Intent(getActivity(), CrearFlotaCastilla.class);
                    intent.putExtra("zona", "Plata");
                    break;
                case 3:
                    break;
            }
        }

        intent.putExtra("segundosMerc", ((Juego)getActivity()).getMedia());
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.entrada, R.anim.salida);
        getActivity().getSupportFragmentManager().popBackStack();
        ((Juego)getActivity()).setContadorVentanas(0);
    }
}