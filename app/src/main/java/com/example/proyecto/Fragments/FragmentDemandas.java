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

import com.example.QueryClase.QueryDemandas;
import com.example.QueryClase.QueryProductos;
import com.example.proyecto.Base.BaseDatos;
import com.example.proyecto.Clases.Mercancia;
import com.example.proyecto.CrearFlotaCastilla;
import com.example.proyecto.CustomListViews.CustomListDemandas;
import com.example.proyecto.CustomListViews.CustomListMercancias;
import com.example.proyecto.CustomListViews.CustomListProductos;
import com.example.proyecto.R;

import java.util.List;

public class FragmentDemandas extends Fragment  implements AdapterView.OnItemClickListener, View.OnClickListener {

    private BaseDatos database;
    private ListView listaDemandas;
    private CustomListDemandas adaptador;
    private String partida;
    private Context context;

    private Button botonDescripcion;
    private Button botonPais;
    private Button borrar;

    private List<QueryDemandas> lista;

    public FragmentDemandas(BaseDatos data, String partida, Context context){
        this.database=data;
        this.partida=partida;
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frag =inflater.inflate(R.layout.fragment_demandas2, container, false);

        botonDescripcion = (Button)frag.findViewById(R.id.botonFiltroDemandasDescripcion);
        botonPais = (Button)frag.findViewById(R.id.botonFiltroDemandasPais);
        borrar = (Button)getActivity().findViewById(R.id.button5);

        this.listaDemandas=frag.findViewById(R.id.lista_demandas);
        this.listaDemandas.setOnItemClickListener(this);

        lista= database.selectDemandas(this.partida);
        this.adaptador = new CustomListDemandas(this.context,R.layout.row_demandas,lista);
        this.listaDemandas.setAdapter(this.adaptador);

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista = database.selectDemandas(partida);
                adaptador = new CustomListDemandas(context, R.layout.row_demandas, lista);
                listaDemandas.setAdapter(adaptador);
            }
        });

        botonDescripcion.setOnClickListener(this);
        botonPais.setOnClickListener(this);

        return frag;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder adb=new AlertDialog.Builder(this.context);
        adb.setTitle("Informacion Seleccionada");
        adb.setMessage("Pais : "+this.adaptador.getItem(position).getPais()+"\nDescripcion : "+this.adaptador.getItem(position).getDescripcion()+"\nRealizada : "+this.adaptador.getItem(position).getRealizada()+"\nPartida : "+this.adaptador.getItem(position).getPartida());
        adb.setNegativeButton("Ok", null);
        adb.show();
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == botonDescripcion.getId()){
            mostrarFiltros("mercancia");
        }else if(v.getId() == botonPais.getId()){
            mostrarFiltros("paises");
        }
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
                            lista = database.selectFiltroDemandasDescripcion(partida, mercancias[0]);
                            break;
                        case 1:
                            lista = database.selectFiltroDemandasDescripcion(partida, mercancias[1]);
                            break;
                        case 2:
                            lista = database.selectFiltroDemandasDescripcion(partida, mercancias[2]);
                            break;
                        case 3:
                            lista = database.selectFiltroDemandasDescripcion(partida, mercancias[3]);
                            break;
                        case 4:
                            lista = database.selectFiltroDemandasDescripcion(partida, mercancias[4]);
                            break;
                        case 5:
                            lista = database.selectFiltroDemandasDescripcion(partida, mercancias[5]);
                            break;
                        case 6:
                            lista = database.selectFiltroDemandasDescripcion(partida, mercancias[6]);
                            break;
                        case 7:
                            lista = database.selectFiltroDemandasDescripcion(partida, mercancias[7]);
                            break;
                        case 8:
                            lista = database.selectFiltroDemandasDescripcion(partida, mercancias[8]);
                            break;
                        case 9:
                            lista = database.selectFiltroDemandasDescripcion(partida, mercancias[9]);
                            break;
                        case 10:
                            lista = database.selectFiltroDemandasDescripcion(partida, mercancias[10]);
                            break;
                        case 11:
                            lista = database.selectFiltroDemandasDescripcion(partida, mercancias[11]);
                            break;
                    }

                    adaptador = new CustomListDemandas(context, R.layout.row_demandas, lista);
                    listaDemandas.setAdapter(adaptador);
                    dialog.dismiss();
                }
            });


        }else if(columna == "paises"){

            alert.setSingleChoiceItems(paises, select, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            lista = database.selectFiltroDemandasPais(partida, paises[0]);
                            break;
                        case 1:
                            lista = database.selectFiltroDemandasPais(partida, paises[1]);
                            break;
                        case 2:
                            lista = database.selectFiltroDemandasPais(partida, paises[2]);
                            break;
                        case 3:
                            lista = database.selectFiltroDemandasPais(partida, paises[3]);
                            break;
                        case 4:
                            lista = database.selectFiltroDemandasPais(partida, paises[4]);
                            break;
                        case 5:
                            lista = database.selectFiltroDemandasPais(partida, paises[5]);
                            break;
                        case 6:
                            lista = database.selectFiltroDemandasPais(partida, paises[6]);
                            break;
                        case 7:
                            lista = database.selectFiltroDemandasPais(partida, paises[7]);
                            break;
                    }

                    adaptador = new CustomListDemandas(context, R.layout.row_demandas, lista);
                    listaDemandas.setAdapter(adaptador);
                    dialog.dismiss();
                }
            });

        }

        alert.setCancelable(false);
        alert.show();
    }
}