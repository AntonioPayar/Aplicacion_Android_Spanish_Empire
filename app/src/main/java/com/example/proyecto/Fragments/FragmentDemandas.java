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
import android.widget.ListView;
import android.widget.Toast;

import com.example.QueryClase.QueryDemandas;
import com.example.QueryClase.QueryProductos;
import com.example.proyecto.Base.BaseDatos;
import com.example.proyecto.Clases.Mercancia;
import com.example.proyecto.CrearFlotaCastilla;
import com.example.proyecto.CustomListViews.CustomListDemandas;
import com.example.proyecto.CustomListViews.CustomListProductos;
import com.example.proyecto.R;

import java.util.List;

public class FragmentDemandas extends Fragment  implements AdapterView.OnItemClickListener{

    private BaseDatos database;
    private ListView listaDemandas;
    private CustomListDemandas adaptador;
    private String partida;
    private Context context;

    public FragmentDemandas(BaseDatos data, String partida, Context context){
        this.database=data;
        this.partida=partida;
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frag =inflater.inflate(R.layout.fragment_demandas2, container, false);
        // Inflate the layout for this fragment
        this.listaDemandas=frag.findViewById(R.id.lista_demandas);
        this.listaDemandas.setOnItemClickListener(this);

        List<QueryDemandas> lista =database.selectDemandas(this.partida);
        this.adaptador = new CustomListDemandas(this.context,R.layout.row_demandas,lista);
        this.listaDemandas.setAdapter(this.adaptador);

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
}