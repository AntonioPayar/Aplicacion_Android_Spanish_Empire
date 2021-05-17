package com.example.proyecto.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.QueryClase.QueryProductos;
import com.example.QueryClase.QuerySublevaciones;
import com.example.proyecto.Base.BaseDatos;
import com.example.proyecto.CustomListViews.CustomListProductos;
import com.example.proyecto.CustomListViews.CustomListSublevaciones;
import com.example.proyecto.R;

import java.util.List;


public class FragmentSublevaciones extends Fragment implements AdapterView.OnItemClickListener{

    private BaseDatos database;
    private ListView listaProductos;
    private CustomListSublevaciones adaptador;
    private String partida;
    private Context context;

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
        this.listaProductos=frag.findViewById(R.id.lista_Suble);
        this.listaProductos.setOnItemClickListener(this);

        List<QuerySublevaciones> lista =database.selectSublevaciones(this.partida);

        this.adaptador = new CustomListSublevaciones(this.context,R.layout.row_sublevaciones,lista);
        this.listaProductos.setAdapter(this.adaptador);

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
}