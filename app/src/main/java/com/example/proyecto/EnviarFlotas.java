package com.example.proyecto;

import android.content.Intent;
import android.os.Bundle;

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

public class EnviarFlotas extends Fragment implements View.OnClickListener{


    private Button botonCastillaMerc;
    private Button botonAragonMerc;
    private Button botonAustriaMerc;
    private Button botonBorgonaMerc;
    private Button botonNuevaEspanaMerc;
    private Button botonNuevaGranadaMerc;
    private Button botonPeruMerc;
    private Button botonPlataMerc;

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

    /**
     * Método onClick en el que se accede a las diferentes activities ( Reinos ) que hay para crear mercancias
     * @param v
     */

    @Override
    public void onClick(View v) {

        Intent intent = null;

        if(v.getId() == botonCastillaMerc.getId()){

            intent = new Intent(getActivity(), EnviarFlotasCastilla.class);

        }else if(v.getId() == botonAragonMerc.getId()){

            intent = new Intent(getActivity(), EnviarFlotasAragon.class);

        }else if(v.getId() == botonBorgonaMerc.getId()){

            intent = new Intent(getActivity(), EnviarFlotasBorgona.class);

        }else if(v.getId() == botonAustriaMerc.getId()){

            intent = new Intent(getActivity(), EnviarFlotasAustria.class);

        }else if(v.getId() == botonNuevaEspanaMerc.getId()){

            intent = new Intent(getActivity(), EnviarFlotasNuevaEspana.class);

        }else if(v.getId() == botonNuevaGranadaMerc.getId()){

            intent = new Intent(getActivity(), EnviarFlotasNuevaGranada.class);

        }else if(v.getId() == botonPeruMerc.getId()){

            intent = new Intent(getActivity(), EnviarFlotasPeru.class);

        }else{

            intent = new Intent(getActivity(), EnviarFlotasPlata.class);

        }

        intent.putExtra("segundosMerc", ((Juego)getActivity()).getMedia());
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.entrada, R.anim.salida);
        getActivity().getSupportFragmentManager().popBackStack();
        ((Juego)getActivity()).setContadorVentanas(0);
    }


}