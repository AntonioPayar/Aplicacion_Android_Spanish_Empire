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

import com.example.proyecto.Base.BaseDatos;
import com.example.QueryClase.QueryProductos;
import com.example.proyecto.CustomListViews.CustomListProductos;
import com.example.proyecto.R;

import java.util.List;


public class FragmetProducciones extends Fragment implements AdapterView.OnItemClickListener{

    private BaseDatos database;
    private ListView listaProductos;
    private CustomListProductos adaptador;
    private String partida;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View frag = inflater.inflate(R.layout.fragment_fragmet_producciones, container, false);
        this.listaProductos=frag.findViewById(R.id.lista_produciones);
        this.listaProductos.setOnItemClickListener(this);

        List<QueryProductos> lista =database.selectProductos(this.partida);

        this.adaptador = new CustomListProductos(this.context,R.layout.row_producciones,lista);
        this.listaProductos.setAdapter(this.adaptador);
        //this.listaProductos.setOnItemClickListener(this.context);


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
}