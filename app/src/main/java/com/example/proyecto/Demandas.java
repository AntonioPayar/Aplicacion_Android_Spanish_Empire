package com.example.proyecto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Demandas extends Fragment {

    private PanelDeControl control;
    private TextView etiqueta1;
    private TextView etiqueta2;
    private TextView etiqueta3;
    private TextView etiqueta4;
    private TextView etiqueta5;
    private TextView etiqueta6;
    private TextView etiqueta7;
    private TextView etiqueta8;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_demandas, container, false);

        etiqueta1 = vista.findViewById(R.id.tarea1);
        etiqueta2 = vista.findViewById(R.id.tarea2);
        etiqueta3 = vista.findViewById(R.id.tarea3);
        etiqueta4 = vista.findViewById(R.id.tarea4);
        etiqueta5 = vista.findViewById(R.id.tarea5);
        etiqueta6 = vista.findViewById(R.id.tarea6);
        etiqueta7 = vista.findViewById(R.id.tarea7);
        etiqueta8 = vista.findViewById(R.id.tarea8);
        control = ((Juego) getActivity()).getPanelDeControl();
        recorrerDemandas();
        return vista;
    }

    public void recorrerDemandas() {
        int contador=0;
        int bounds=10;

        if(control.getEspana().getNuevaEspana().getProductosDemandados().length!=0) {

            if(control.getEspana().getNuevaEspana().getProductosDemandados()[0]!=null) {
                etiqueta1.setText("Nueva España demanda: "+control.getEspana().getNuevaEspana().getProductosDemandados()[0].toString());
            }else {
                etiqueta1.setText("Nueva España ha obtenido su demanda");
            }

            bounds=bounds+(20*contador);
//            etiqueta1.setBounds(75, bounds, 400, 100);
            contador++;
            bounds=10;
//            panel.add(etiqueta1);
        }else{
            System.out.println("a1");
        }

        if(control.getEspana().getNuevaGranda().getProductosDemandados().length!=0) {

            if(control.getEspana().getNuevaGranda().getProductosDemandados()[0]!=null) {
                etiqueta2.setText("Nueva Granada demanda: "+control.getEspana().getNuevaGranda().getProductosDemandados()[0].toString());
            }else {
                etiqueta2.setText("Nueva Granada ha obtenido su demanda");
            }

            bounds=bounds+(20*contador);
//            etiqueta2.setBounds(75, bounds, 400, 100);
            contador++;
            bounds=10;
//            panel.add(etiqueta2);
        }else{
            System.out.println("a2");
        }

        if(control.getEspana().getPeru().getProductosDemandados().length!=0) {

            if(control.getEspana().getPeru().getProductosDemandados()[0]!=null) {
                etiqueta3.setText("Peru demanda: "+control.getEspana().getPeru().getProductosDemandados()[0].toString());
            }else {
                etiqueta3.setText("Perú ha obtenido su demanda");
            }

            bounds=bounds+(20*contador);
//            etiqueta3.setBounds(75, bounds, 400, 100);
            contador++;
            bounds=10;
//            panel.add(etiqueta3);
        }else{
            System.out.println("a3");
        }

        if(control.getEspana().getPlata().getProductosDemandados().length!=0) {

            if(control.getEspana().getPlata().getProductosDemandados()[0]!=null) {
                etiqueta4.setText("Plata demanda: "+control.getEspana().getPlata().getProductosDemandados()[0].toString());
            }else {
                etiqueta4.setText("Plata ha obtenido su demanda");
            }

            bounds=bounds+(20*contador);
//            etiqueta4.setBounds(75, bounds, 400, 100);
            contador++;
            bounds=10;
//            panel.add(etiqueta4);
        }else{
            System.out.println("a4");
        }

        if(control.getEspana().getCastilla().getProductosDemandados().length!=0) {

            if(control.getEspana().getCastilla().getProductosDemandados()[0]!=null) {
                etiqueta5.setText("Castilla demanda: "+control.getEspana().getCastilla().getProductosDemandados()[0].toString());
            }else {
                etiqueta5.setText("Castilla ha obtenido su demanda");
            }

            bounds=bounds+(20*contador);
//            etiqueta5.setBounds(75, bounds, 400, 100);
            contador++;
            bounds=10;
//            panel.add(etiqueta5);
        }else{
            System.out.println("a5");
        }

        if(control.getEspana().getAragon().getProductosDemandados().length!=0) {

            if(control.getEspana().getAragon().getProductosDemandados()[0]!=null) {
                etiqueta6.setText("Aragon demanda: "+control.getEspana().getAragon().getProductosDemandados()[0].toString());
            }else {
                etiqueta6.setText("Aragon ha obtenido su demanda");
            }

            bounds=bounds+(20*contador);
//            etiqueta6.setBounds(75, bounds, 400, 100);
            contador++;
            bounds=10;
//            panel.add(etiqueta6);
        }else{
            System.out.println("a6");
        }

        if(control.getEspana().getBorgona().getProductosDemandados().length!=0) {

            if(control.getEspana().getBorgona().getProductosDemandados()[0]!=null) {
                etiqueta7.setText("Borgoña demanda: "+control.getEspana().getBorgona().getProductosDemandados()[0].toString());
            }else {
                etiqueta7.setText("Borgoña ha obtenido su demanda");
            }

            bounds=bounds+(20*contador);
//            etiqueta7.setBounds(75, bounds, 400, 100);
            contador++;
            bounds=10;
//            panel.add(etiqueta7);
        }else{
            System.out.println("a7");
        }

        if(control.getEspana().getAustria().getProductosDemandados().length!=0) {

            if(control.getEspana().getAustria().getProductosDemandados()[0]!=null) {
                etiqueta8.setText("Austria demanda: "+control.getEspana().getAustria().getProductosDemandados()[0].toString());
            }else {
                etiqueta8.setText("Austria ha obtenido su demanda");
            }

            bounds=bounds+(20*contador);
//            etiqueta8.setBounds(75, bounds, 400, 100);
            contador++;
            bounds=10;
//            panel.add(etiqueta8);
        }else{
            System.out.println("a8");
        }


    }

}