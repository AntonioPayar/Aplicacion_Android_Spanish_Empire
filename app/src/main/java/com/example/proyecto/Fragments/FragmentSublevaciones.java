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

import com.example.QueryClase.QueryProductos;
import com.example.QueryClase.QuerySublevaciones;
import com.example.proyecto.Base.BaseDatos;
import com.example.proyecto.CustomListViews.CustomListMercancias;
import com.example.proyecto.CustomListViews.CustomListProductos;
import com.example.proyecto.CustomListViews.CustomListSublevaciones;
import com.example.proyecto.R;

import java.util.List;


public class FragmentSublevaciones extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener{

    private BaseDatos database;
    private ListView listaProductos;
    private CustomListSublevaciones adaptador;
    private String partida;
    private Context context;

    private Button botonPais;
    private Button botonTurno;
    private Button borrar;

    private List<QuerySublevaciones> lista;


    public FragmentSublevaciones(BaseDatos data, String partida, Context context){
        this.database=data;
        this.partida=partida;
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View frag = inflater.inflate(R.layout.fragment_sublevaciones, container, false);

        botonPais = (Button)frag.findViewById(R.id.botonFiltroSublevacionPais);
        botonTurno = (Button)frag.findViewById(R.id.botonFiltroSublevacionTurno);
        borrar = (Button)getActivity().findViewById(R.id.button5);

        this.listaProductos=frag.findViewById(R.id.lista_Suble);
        this.listaProductos.setOnItemClickListener(this);

        lista = database.selectSublevaciones(this.partida);

        this.adaptador = new CustomListSublevaciones(this.context, R.layout.row_sublevaciones, lista);
        this.listaProductos.setAdapter(this.adaptador);

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista = database.selectSublevaciones(partida);
                adaptador = new CustomListSublevaciones(context, R.layout.row_sublevaciones, lista);
                listaProductos.setAdapter(adaptador);
            }
        });

        botonPais.setOnClickListener(this);
        botonTurno.setOnClickListener(this);

        return frag;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder adb=new AlertDialog.Builder(this.context);
        adb.setTitle("Informacion Seleccionada");
        adb.setMessage("Pais : "+this.adaptador.getItem(position).getPais()+"\nHora : "+this.adaptador.getItem(position).getHora()+"\nTurno : "+this.adaptador.getItem(position).getTurno()+"\nPartida : "+this.adaptador.getItem(position).getPartida());
        adb.setNegativeButton("Ok", null);
        adb.show();
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == botonTurno.getId()){
            mostrarFiltros("turno");
        }else if(v.getId() == botonPais.getId()){
            mostrarFiltros("paises");
        }
    }

    private void mostrarFiltros(String columna) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Filtro");
        String[] paises = {"Castilla", "Aragon", "Borgoña", "Austria", "Peru", "Plata", "Nueva España", "Nueva Granada"};
        int select = 0;
        alert.setNegativeButton("Salir", null);

        if(columna == "paises"){

            alert.setSingleChoiceItems(paises, select, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            lista = database.selectFiltroSublevacionPais(partida, paises[0]);
                            break;
                        case 1:
                            lista = database.selectFiltroSublevacionPais(partida, paises[1]);
                            break;
                        case 2:
                            lista = database.selectFiltroSublevacionPais(partida, paises[2]);
                            break;
                        case 3:
                            lista = database.selectFiltroSublevacionPais(partida, paises[3]);
                            break;
                        case 4:
                            lista = database.selectFiltroSublevacionPais(partida, paises[4]);
                            break;
                        case 5:
                            lista = database.selectFiltroSublevacionPais(partida, paises[5]);
                            break;
                        case 6:
                            lista = database.selectFiltroSublevacionPais(partida, paises[6]);
                            break;
                        case 7:
                            lista = database.selectFiltroSublevacionPais(partida, paises[7]);
                            break;
                    }

                    adaptador = new CustomListSublevaciones(context, R.layout.row_sublevaciones, lista);
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

                    lista = database.selectFiltroSublevacionTurno(partida, et.getText().toString());
                    adaptador = new CustomListSublevaciones(context, R.layout.row_sublevaciones, lista);
                    listaProductos.setAdapter(adaptador);
                    dialog.dismiss();
                }
            });

            alert.setNegativeButton("Salir", null);

        }

        alert.setCancelable(false);
        alert.show();
    }
}