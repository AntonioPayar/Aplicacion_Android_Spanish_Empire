package com.example.proyecto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class mapaFragmento03 extends Fragment {

    private PanelDeControl control;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_mapa_fragmento03, container, false);

        control = Juego.getPanelDeControl();

        boolean peru = control.getEspana().getPeru().isSublevaciones();
        boolean plata = control.getEspana().getPlata().isSublevaciones();
        boolean ne = control.getEspana().getNuevaEspana().isSublevaciones();
        boolean ng = control.getEspana().getNuevaGranda().isSublevaciones();

        FrameLayout america = view.findViewById(R.id.fragmentMapaFragmentoAmerica);

        if(plata){
            america.setBackgroundResource(R.drawable.america_platasub);
        }

        if(peru){
            america.setBackgroundResource(R.drawable.america_perusub);
        }

        if(ne){
            america.setBackgroundResource(R.drawable.america_nesub);
        }

        if(ng){
            america.setBackgroundResource(R.drawable.america_ngsub);
        }

        if(plata && peru){
            america.setBackgroundResource(R.drawable.america_plataperusub);
        }

        if(plata && ne){
            america.setBackgroundResource(R.drawable.america_platanesub);
        }

        if(plata && ng){
            america.setBackgroundResource(R.drawable.america_platangsub);
        }

        if(peru && ne){
            america.setBackgroundResource(R.drawable.america_perunesub);
        }

        if(peru && ng){
            america.setBackgroundResource(R.drawable.america_perungsub);
        }

        if(ng && ne){
            america.setBackgroundResource(R.drawable.america_ngnesub);
        }

        if(plata && peru && ne){
            america.setBackgroundResource(R.drawable.america_plataperunesub);
        }

        if(plata && ne && ng){
            america.setBackgroundResource(R.drawable.america_ngneplatasub);
        }

        if(plata && peru && ng){
            america.setBackgroundResource(R.drawable.america_plataperungsub);
        }

        if(peru && ne && ng){
            america.setBackgroundResource(R.drawable.america_perunengsub);
        }

        if(peru && ne && ng && plata){
            america.setBackgroundResource(R.drawable.america_todos);
        }

        return view;
    }
}