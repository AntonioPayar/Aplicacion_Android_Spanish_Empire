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

import com.example.QueryClase.QueryMercancias;
import com.example.QueryClase.QueryProductos;
import com.example.proyecto.Base.BaseDatos;
import com.example.proyecto.CustomListViews.CustomListMercancias;
import com.example.proyecto.CustomListViews.CustomListProductos;
import com.example.proyecto.R;

import java.util.List;


public class FragmentMercancias extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener{

    private BaseDatos database;
    private ListView listaMercancias;
    private CustomListMercancias adaptador;
    private String partida;
    private Context context;

    private List<QueryMercancias> lista;

    //Botones
    private Button botonMercancias;
    private Button botonPais;
    private Button botonTurno;
    private Button borrar;

    

    public FragmentMercancias(BaseDatos data, String partida, Context context){
        this.database=data;
        this.partida=partida;
        this.context=context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frag =inflater.inflate(R.layout.fragment_mercancias, container, false);

        borrar = (Button)getActivity().findViewById(R.id.button5);
        botonMercancias = (Button)frag.findViewById(R.id.imageMercPais);
        botonPais = (Button)frag.findViewById(R.id.imageMercTurno);
        botonTurno = (Button)frag.findViewById(R.id.imageMercCantidad2);

        this.listaMercancias =frag.findViewById(R.id.lista_mercancias);
        this.listaMercancias.setOnItemClickListener(this);

        lista = database.selectMercancias(this.partida);
        this.adaptador = new CustomListMercancias(this.context,R.layout.row_mercancias,lista);
        this.listaMercancias.setAdapter(this.adaptador);

        botonMercancias.setOnClickListener(this);
        botonPais.setOnClickListener(this);
        botonTurno.setOnClickListener(this);

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista = database.selectMercancias(partida);
                adaptador = new CustomListMercancias(context, R.layout.row_mercancias, lista);
                listaMercancias.setAdapter(adaptador);
            }
        });

        return frag;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder adb=new AlertDialog.Builder(this.context);
        adb.setTitle("Informacion Seleccionada");
        adb.setMessage("Mercancia : "+this.adaptador.getItem(position).getMercancia()+"\nPais : "+this.adaptador.getItem(position).getPais()+"\nProduccion Toatal : "+this.adaptador.getItem(position).getProducion()+"\nCantidad Seleccionada : "+this.adaptador.getItem(position).getCantidadSelec()+"\nTurno : "+this.adaptador.getItem(position).getTurno());
        adb.setNegativeButton("Ok", null);
        adb.show();
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == botonMercancias.getId()){
            mostrarFiltros("mercancia");
        }else if(v.getId() == botonPais.getId()){
            mostrarFiltros("paises");
        }else if(v.getId() == botonTurno.getId()){
            mostrarFiltros("turno");
        }
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
                            lista = database.selectFiltroMercancia(partida, mercancias[0]);
                            break;
                        case 1:
                            lista = database.selectFiltroMercancia(partida, mercancias[1]);
                            break;
                        case 2:
                            lista = database.selectFiltroMercancia(partida, mercancias[2]);
                            break;
                        case 3:
                            lista = database.selectFiltroMercancia(partida, mercancias[3]);
                            break;
                        case 4:
                            lista = database.selectFiltroMercancia(partida, mercancias[4]);
                            break;
                        case 5:
                            lista = database.selectFiltroMercancia(partida, mercancias[5]);
                            break;
                        case 6:
                            lista = database.selectFiltroMercancia(partida, mercancias[6]);
                            break;
                        case 7:
                            lista = database.selectFiltroMercancia(partida, mercancias[7]);
                            break;
                        case 8:
                            lista = database.selectFiltroMercancia(partida, mercancias[8]);
                            break;
                        case 9:
                            lista = database.selectFiltroMercancia(partida, mercancias[9]);
                            break;
                        case 10:
                            lista = database.selectFiltroMercancia(partida, mercancias[10]);
                            break;
                        case 11:
                            lista = database.selectFiltroMercancia(partida, mercancias[11]);
                            break;
                    }

                    adaptador = new CustomListMercancias(context, R.layout.row_mercancias, lista);
                    listaMercancias.setAdapter(adaptador);
                    dialog.dismiss();
                }
            });


        }else if(columna == "paises"){

            alert.setSingleChoiceItems(paises, select, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            lista = database.selectFiltroPais(partida, paises[0]);
                            break;
                        case 1:
                            lista = database.selectFiltroPais(partida, paises[1]);
                            break;
                        case 2:
                            lista = database.selectFiltroPais(partida, paises[2]);
                            break;
                        case 3:
                            lista = database.selectFiltroPais(partida, paises[3]);
                            break;
                        case 4:
                            lista = database.selectFiltroPais(partida, paises[4]);
                            break;
                        case 5:
                            lista = database.selectFiltroPais(partida, paises[5]);
                            break;
                        case 6:
                            lista = database.selectFiltroPais(partida, paises[6]);
                            break;
                        case 7:
                            lista = database.selectFiltroPais(partida, paises[7]);
                            break;
                    }

                    adaptador = new CustomListMercancias(context, R.layout.row_mercancias, lista);
                    listaMercancias.setAdapter(adaptador);
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

                    lista = database.selectFiltroTurno(partida, et.getText().toString());
                    adaptador = new CustomListMercancias(context, R.layout.row_mercancias, lista);
                    listaMercancias.setAdapter(adaptador);
                    dialog.dismiss();
                }
            });

            alert.setNegativeButton("Salir", null);

        }

        alert.setCancelable(false);
        alert.show();
    }

}