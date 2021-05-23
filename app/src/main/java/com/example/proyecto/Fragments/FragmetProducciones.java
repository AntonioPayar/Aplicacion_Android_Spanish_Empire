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

import com.example.proyecto.Base.BaseDatos;
import com.example.QueryClase.QueryProductos;
import com.example.proyecto.CustomListViews.CustomListMercancias;
import com.example.proyecto.CustomListViews.CustomListProductos;
import com.example.proyecto.CustomListViews.CustomListSublevaciones;
import com.example.proyecto.R;

import java.util.List;


public class FragmetProducciones extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private BaseDatos database;
    private ListView listaProductos;
    private CustomListProductos adaptador;
    private String partida;
    private Context context;

    private Button botonPais;
    private Button botonProducto;
    private Button botonTurno;
//    private Button botonPartida;
    private Button borrar;

    private List<QueryProductos> lista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View frag = inflater.inflate(R.layout.fragment_fragmet_producciones, container, false);

        botonPais = (Button)frag.findViewById(R.id.botonFiltroProduccionesPais);
        botonProducto = (Button)frag.findViewById(R.id.botonFiltroProduccionesPais);
        botonTurno = (Button)frag.findViewById(R.id.botonFiltroProduccionesPais);
//        botonPartida = (Button)frag.findViewById(R.id.botonFiltroProduccionesPais);

        borrar = (Button)getActivity().findViewById(R.id.button5);

        this.listaProductos=frag.findViewById(R.id.lista_produciones);
        this.listaProductos.setOnItemClickListener(this);

        lista = database.selectProductos(this.partida);

        this.adaptador = new CustomListProductos(this.context,R.layout.row_producciones,lista);
        this.listaProductos.setAdapter(this.adaptador);
        //this.listaProductos.setOnItemClickListener(this.context);

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista = database.selectProductos(partida);
                adaptador = new CustomListProductos(context, R.layout.row_producciones, lista);
                listaProductos.setAdapter(adaptador);
            }
        });

        botonPais.setOnClickListener(this);
        botonProducto.setOnClickListener(this);
        botonTurno.setOnClickListener(this);
//        botonPartida.setOnClickListener(this);

        return frag;
    }

    public FragmetProducciones(BaseDatos data, String partida, Context context){
        this.database=data;
        this.partida=partida;
        this.context=context;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder adb=new AlertDialog.Builder(this.context);
        adb.setTitle("Informacion Seleccionada");
        adb.setMessage("Pais : "+this.adaptador.getItem(position).getPais()+"\nTurno : "+this.adaptador.getItem(position).getTurno()+"\nProducto : "+this.adaptador.getItem(position).getProducto()+"\nCantidad : "+this.adaptador.getItem(position).getCantidad()+"\nPartida : "+this.adaptador.getItem(position).getPartida());
        adb.setNegativeButton("Ok", null);
        adb.show();
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == botonPais.getId()){
            mostrarFiltros("paises");
        }else if(v.getId() == botonTurno.getId()){
            mostrarFiltros("turno");
        }else if(v.getId() == botonProducto.getId()){
            mostrarFiltros("mercancia");
        }
//        else if(v.getId() == botonPartida.getId()){
//            mostrarFiltros("partida");
//        }
    }

    private void mostrarFiltros(String columna) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Filtro");
        String[] paises = {"Castilla", "Aragón", "Borgoña", "Austria", "Peru", "Plata", "Nueva España", "Nueva Granada"};
        String[] mercancias = {"Trigo", "Uvas", "Maiz", "Arroz", "Hierro", "Plata", "Tomates", "Patatas", "Oro", "Tabaco", "Cafe"};
        int select = 0;
        alert.setNegativeButton("Salir", null);

        if(columna == "mercancia"){

            alert.setSingleChoiceItems(mercancias, select, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            lista = database.selectFiltroProduccionesProducto(partida, mercancias[0]);
                            break;
                        case 1:
                            lista = database.selectFiltroProduccionesProducto(partida, mercancias[1]);
                            break;
                        case 2:
                            lista = database.selectFiltroProduccionesProducto(partida, mercancias[2]);
                            break;
                        case 3:
                            lista = database.selectFiltroProduccionesProducto(partida, mercancias[3]);
                            break;
                        case 4:
                            lista = database.selectFiltroProduccionesProducto(partida, mercancias[4]);
                            break;
                        case 5:
                            lista = database.selectFiltroProduccionesProducto(partida, mercancias[5]);
                            break;
                        case 6:
                            lista = database.selectFiltroProduccionesProducto(partida, mercancias[6]);
                            break;
                        case 7:
                            lista = database.selectFiltroProduccionesProducto(partida, mercancias[7]);
                            break;
                        case 8:
                            lista = database.selectFiltroProduccionesProducto(partida, mercancias[8]);
                            break;
                        case 9:
                            lista = database.selectFiltroProduccionesProducto(partida, mercancias[9]);
                            break;
                        case 10:
                            lista = database.selectFiltroProduccionesProducto(partida, mercancias[10]);
                            break;
                        case 11:
                            lista = database.selectFiltroProduccionesProducto(partida, mercancias[11]);
                            break;
                    }

                    adaptador = new CustomListProductos(context, R.layout.row_producciones, lista);
                    listaProductos.setAdapter(adaptador);
                }
            });


        }else if(columna == "paises"){

            alert.setSingleChoiceItems(paises, select, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            lista = database.selectFiltroProduccionesPaises(partida, paises[0]);
                            break;
                        case 1:
                            lista = database.selectFiltroProduccionesPaises(partida, paises[1]);
                            break;
                        case 2:
                            lista = database.selectFiltroProduccionesPaises(partida, paises[2]);
                            break;
                        case 3:
                            lista = database.selectFiltroProduccionesPaises(partida, paises[3]);
                            break;
                        case 4:
                            lista = database.selectFiltroProduccionesPaises(partida, paises[4]);
                            break;
                        case 5:
                            lista = database.selectFiltroProduccionesPaises(partida, paises[5]);
                            break;
                        case 6:
                            lista = database.selectFiltroProduccionesPaises(partida, paises[6]);
                            break;
                        case 7:
                            lista = database.selectFiltroProduccionesPaises(partida, paises[7]);
                            break;
                    }

                    adaptador = new CustomListProductos(context, R.layout.row_producciones, lista);
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

                    lista = database.selectFiltroProduccionesTurno(partida, et.getText().toString());
                    adaptador = new CustomListProductos(context, R.layout.row_producciones, lista);
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
//                    lista = database.selectFiltroProduccionesPartida(partida, et.getText().toString());
//                    adaptador = new CustomListProductos(context, R.layout.row_producciones, lista);
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