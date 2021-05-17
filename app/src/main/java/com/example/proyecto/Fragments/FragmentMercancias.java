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

import com.example.QueryClase.QueryMercancias;
import com.example.QueryClase.QueryProductos;
import com.example.proyecto.Base.BaseDatos;
import com.example.proyecto.CustomListViews.CustomListMercancias;
import com.example.proyecto.CustomListViews.CustomListProductos;
import com.example.proyecto.R;

import java.util.List;


public class FragmentMercancias extends Fragment implements AdapterView.OnItemClickListener{

    private BaseDatos database;
    private ListView listaMercancias;
    private CustomListMercancias adaptador;
    private String partida;
    private Context context;


    public FragmentMercancias(BaseDatos data, String partida, Context context){
        this.database=data;
        this.partida=partida;
        this.context=context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frag =inflater.inflate(R.layout.fragment_mercancias, container, false);
        this.listaMercancias =frag.findViewById(R.id.lista_mercancias);
        this.listaMercancias.setOnItemClickListener(this);

        List<QueryMercancias> lista =database.selectMercancias(this.partida);
        this.adaptador = new CustomListMercancias(this.context,R.layout.row_mercancias,lista);
        this.listaMercancias.setAdapter(this.adaptador);
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
}