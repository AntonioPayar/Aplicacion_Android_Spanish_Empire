package com.example.proyecto.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.QueryClase.QueryFlotasEnviadas;
import com.example.QueryClase.QueryProductos;
import com.example.proyecto.Base.BaseDatos;
import com.example.proyecto.CustomListViews.CustomListFlotaEnviada;
import com.example.proyecto.CustomListViews.CustomListMercancias;
import com.example.proyecto.CustomListViews.CustomListProductos;
import com.example.proyecto.R;

import java.util.List;


public class FragmentFlotaEnviada extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private BaseDatos database;
    private ListView listaProductos;
    private CustomListFlotaEnviada adaptador;
    private String partida;
    private Context context;

//    private Button botonPartida;
    private Button botonDestino;
    private Button botonTurno;
    private Button borrar;

    private List<QueryFlotasEnviadas> lista;

    public FragmentFlotaEnviada(BaseDatos data, String partida, Context context){
        this.database=data;
        this.partida=partida;
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View frag = inflater.inflate(R.layout.fragment_flota_enviada, container, false);

//        botonPartida = (Button)frag.findViewById(R.id.botonFiltroFlotaEnviadaPartida);
        botonDestino = (Button)frag.findViewById(R.id.botonFiltroFlotaEnviadaDestino);
        botonTurno = (Button)frag.findViewById(R.id.botonFiltroFlotaEnviadaTurno);
        borrar = (Button)getActivity().findViewById(R.id.button5);

        this.listaProductos=frag.findViewById(R.id.lista_FloEnvi);
        this.listaProductos.setOnItemClickListener(this);

        lista = database.selectFlotaEnviada(this.partida);

        this.adaptador = new CustomListFlotaEnviada(this.context,R.layout.row_flota_enviada,lista);
        this.listaProductos.setAdapter(this.adaptador);

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista = database.selectFlotaEnviada(partida);
                adaptador = new CustomListFlotaEnviada(context, R.layout.row_flota_enviada, lista);
                listaProductos.setAdapter(adaptador);
            }
        });

        botonDestino.setOnClickListener(this);
        botonTurno.setOnClickListener(this);

        return frag;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder adb=new AlertDialog.Builder(this.context);
        adb.setTitle("Informacion Seleccionada");
        adb.setMessage("Flota : "+this.adaptador.getItem(position).getFlota()+"\nAlmacenada : "+this.adaptador.getItem(position).getAlmacenada()+"\nPartida : "+this.adaptador.getItem(position).getPartida()+"\nTurno : "+this.adaptador.getItem(position).getTurno()+"\nDestino : "+this.adaptador.getItem(position).getDestino()+"\nDistancia : "+this.adaptador.getItem(position).getDistancia());
        adb.setNegativeButton("Ok", null);
        adb.show();
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == botonDestino.getId()){
            mostrarFiltros("paises");
        }else if(v.getId() == botonTurno.getId()){
            mostrarFiltros("turno");
        }
//        else if(v.getId() == botonPartida.getId()){
//            mostrarFiltros("partida");
//        }
    }

    private void mostrarFiltros(String columna) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Filtro");
        String[] paises = {"Castilla", "Aragón", "Borgoña", "Austria", "Peru", "Plata", "Nueva España", "Nueva Granada"};
//        String[] mercancias = {"Trigo", "Uvas", "Maiz", "Arroz", "Hierro", "Plata", "Tomates", "Patatas", "Oro", "Tabaco", "Cafe"};
        int select = 0;
        alert.setNegativeButton("Salir", null);

        if(columna == "paises"){

            alert.setSingleChoiceItems(paises, select, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            lista = database.selectFiltroFlotaEnviadaPais(partida, paises[0]);
                            break;
                        case 1:
                            lista = database.selectFiltroFlotaEnviadaPais(partida, paises[1]);
                            break;
                        case 2:
                            lista = database.selectFiltroFlotaEnviadaPais(partida, paises[2]);
                            break;
                        case 3:
                            lista = database.selectFiltroFlotaEnviadaPais(partida, paises[3]);
                            break;
                        case 4:
                            lista = database.selectFiltroFlotaEnviadaPais(partida, paises[4]);
                            break;
                        case 5:
                            lista = database.selectFiltroFlotaEnviadaPais(partida, paises[5]);
                            break;
                        case 6:
                            lista = database.selectFiltroFlotaEnviadaPais(partida, paises[6]);
                            break;
                        case 7:
                            lista = database.selectFiltroFlotaEnviadaPais(partida, paises[7]);
                            break;
                    }

                    adaptador = new CustomListFlotaEnviada(context, R.layout.row_flota_enviada, lista);
                    listaProductos.setAdapter(adaptador);
                    dialog.dismiss();
                }
            });

        }else if(columna == "turno"){

            LayoutInflater li = LayoutInflater.from(getActivity().getApplicationContext());
            View aletDialogView = li.inflate(R.layout.alert_dialog, null);
            alert.setView(aletDialogView);
            EditText et = (EditText)aletDialogView.findViewById(R.id.editTextAlert);

            alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    lista = database.selectFiltroFlotaEnviadaTurno(partida, et.getText().toString());
                    adaptador = new CustomListFlotaEnviada(context, R.layout.row_flota_enviada, lista);
                    listaProductos.setAdapter(adaptador);
                    dialog.dismiss();
                }
            });

            alert.setNegativeButton("Salir", null);

        }
//        else if(columna == "partida"){
//
//            LayoutInflater li = LayoutInflater.from(getActivity().getApplicationContext());
//            View aletDialogView = li.inflate(R.layout.alert_dialog, null);
//            alert.setView(aletDialogView);
//            EditText et = (EditText)aletDialogView.findViewById(R.id.editTextAlert);
//
//            alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                    lista = database.selectFiltroFlotaEnviadaPartida(partida, et.getText().toString());
//                    adaptador = new CustomListFlotaEnviada(context, R.layout.row_flota_enviada, lista);
//                    listaProductos.setAdapter(adaptador);
//                    dialog.dismiss();
//                }
//            });
//
//            alert.setNegativeButton("Salir", null);
//
//        }

        alert.setCancelable(false);
        alert.show();
    }
}
