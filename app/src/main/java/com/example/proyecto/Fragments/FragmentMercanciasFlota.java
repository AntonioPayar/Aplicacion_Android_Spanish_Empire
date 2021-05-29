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

import com.example.QueryClase.QueryMercanciasFlotas;
import com.example.QueryClase.QueryProductos;
import com.example.proyecto.Base.BaseDatos;
import com.example.proyecto.CustomListViews.CustomListMercancias;
import com.example.proyecto.CustomListViews.CustomListMercanciasFlotas;
import com.example.proyecto.CustomListViews.CustomListProductos;
import com.example.proyecto.R;

import java.util.List;


public class FragmentMercanciasFlota extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener{

    private BaseDatos database;
    private ListView listaMF;
    private CustomListMercanciasFlotas adaptador;
    private String partida;
    private Context context;

    private Button botonFlotaPais;
    private Button botonFlotaTurno;
    private Button botonFlotaMercancia;
    //    private Button botonFlotaPartida;
    private Button borrar;

    private List<QueryMercanciasFlotas> lista;

    public FragmentMercanciasFlota(BaseDatos data, String partida, Context context){
        this.database=data;
        this.partida=partida;
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View frag = inflater.inflate(R.layout.fragment_mercancias_flota, container, false);

        botonFlotaPais = (Button)frag.findViewById(R.id.botonFiltroFlotaPais);
        botonFlotaTurno = (Button)frag.findViewById(R.id.botonFiltroMercanciaTurno);
        botonFlotaMercancia = (Button)frag.findViewById(R.id.botonFiltroMercanciaFlota);
//        botonFlotaPartida = (Button)frag.findViewById(R.id.botonFiltroMercanciaPartida);
        borrar = (Button)getActivity().findViewById(R.id.button5);

        this.listaMF=frag.findViewById(R.id.lista_MFlo);
        this.listaMF.setOnItemClickListener(this);

        lista = database.selectMercanciasFlota(this.partida);

        this.adaptador = new CustomListMercanciasFlotas(this.context,R.layout.row_mercancias_flota,lista);
        this.listaMF.setAdapter(this.adaptador);


        botonFlotaPais.setOnClickListener(this);
        botonFlotaTurno.setOnClickListener(this);
        botonFlotaMercancia.setOnClickListener(this);
//        botonFlotaPartida.setOnClickListener(this);

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista = database.selectMercanciasFlota(partida);
                adaptador = new CustomListMercanciasFlotas(context, R.layout.row_mercancias_flota, lista);
                listaMF.setAdapter(adaptador);
            }
        });

        return frag;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder adb=new AlertDialog.Builder(this.context);
        adb.setTitle("Informacion Seleccionada");
        adb.setMessage("Flota Pais : "+this.adaptador.getItem(position).getFlota_pais()+"\nMercancia : "+this.adaptador.getItem(position).getMercancia()+"\nTurno : "+this.adaptador.getItem(position).getTurno()+"\nPartida : "+this.adaptador.getItem(position).getPartida());
        adb.setNegativeButton("Ok", null);
        adb.show();
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == botonFlotaPais.getId()){
            mostrarFiltros("paises");
        }else if(v.getId() == botonFlotaTurno.getId()){
            mostrarFiltros("turno");
        }else if(v.getId() == botonFlotaMercancia.getId()){
            mostrarFiltros("mercancia");
        }
//        else if(v.getId() == botonFlotaPartida.getId()){
//            mostrarFiltros("partida");
//        }
    }

    private void mostrarFiltros(String columna) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Filtro");
        String[] paises = {"Castilla", "Aragon", "Borgoña", "Austria", "Peru", "Plata", "Nueva España", "Nueva Granada"};
        String[] mercancias = {"Trigo", "Uvas", "Maiz", "Arroz", "Hierro", "Plata", "Tomates", "Patatas", "Oro", "Tabaco", "Cafe"};
        int select = 0;
        alert.setNegativeButton("Salir", null);

        if(columna == "mercancia"){

            alert.setSingleChoiceItems(mercancias, select, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            lista = database.selectFiltroFlotaMercancia(partida, mercancias[0]);
                            break;
                        case 1:
                            lista = database.selectFiltroFlotaMercancia(partida, mercancias[1]);
                            break;
                        case 2:
                            lista = database.selectFiltroFlotaMercancia(partida, mercancias[2]);
                            break;
                        case 3:
                            lista = database.selectFiltroFlotaMercancia(partida, mercancias[3]);
                            break;
                        case 4:
                            lista = database.selectFiltroFlotaMercancia(partida, mercancias[4]);
                            break;
                        case 5:
                            lista = database.selectFiltroFlotaMercancia(partida, mercancias[5]);
                            break;
                        case 6:
                            lista = database.selectFiltroFlotaMercancia(partida, mercancias[6]);
                            break;
                        case 7:
                            lista = database.selectFiltroFlotaMercancia(partida, mercancias[7]);
                            break;
                        case 8:
                            lista = database.selectFiltroFlotaMercancia(partida, mercancias[8]);
                            break;
                        case 9:
                            lista = database.selectFiltroFlotaMercancia(partida, mercancias[9]);
                            break;
                        case 10:
                            lista = database.selectFiltroFlotaMercancia(partida, mercancias[10]);
                            break;
                        case 11:
                            lista = database.selectFiltroFlotaMercancia(partida, mercancias[11]);
                            break;
                    }

                    adaptador = new CustomListMercanciasFlotas(context, R.layout.row_mercancias_flota, lista);
                    listaMF.setAdapter(adaptador);
                    dialog.dismiss();
                }
            });


        }else if(columna == "paises"){

            alert.setSingleChoiceItems(paises, select, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            lista = database.selectFiltroFlotaPais(partida, paises[0]);
                            break;
                        case 1:
                            lista = database.selectFiltroFlotaPais(partida, paises[1]);
                            break;
                        case 2:
                            lista = database.selectFiltroFlotaPais(partida, paises[2]);
                            break;
                        case 3:
                            lista = database.selectFiltroFlotaPais(partida, paises[3]);
                            break;
                        case 4:
                            lista = database.selectFiltroFlotaPais(partida, paises[4]);
                            break;
                        case 5:
                            lista = database.selectFiltroFlotaPais(partida, paises[5]);
                            break;
                        case 6:
                            lista = database.selectFiltroFlotaPais(partida, paises[6]);
                            break;
                        case 7:
                            lista = database.selectFiltroFlotaPais(partida, paises[7]);
                            break;
                    }

                    adaptador = new CustomListMercanciasFlotas(context, R.layout.row_mercancias_flota, lista);
                    listaMF.setAdapter(adaptador);
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

                    lista = database.selectFiltroFlotaTurno(partida, et.getText().toString());
                    adaptador = new CustomListMercanciasFlotas(context, R.layout.row_mercancias_flota, lista);
                    listaMF.setAdapter(adaptador);
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
//                    lista = database.selectFiltroFlotaPartida(partida, et.getText().toString());
//                    adaptador = new CustomListMercanciasFlotas(context, R.layout.row_mercancias_flota, lista);
//                    listaMF.setAdapter(adaptador);
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
