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

import com.example.QueryClase.QueryMercanciasFlotas;
import com.example.QueryClase.QueryProductos;
import com.example.proyecto.Base.BaseDatos;
import com.example.proyecto.CustomListViews.CustomListMercanciasFlotas;
import com.example.proyecto.CustomListViews.CustomListProductos;
import com.example.proyecto.R;

import java.util.List;


public class FragmentMercanciasFlota extends Fragment implements AdapterView.OnItemClickListener{

    private BaseDatos database;
    private ListView listaMF;
    private CustomListMercanciasFlotas adaptador;
    private String partida;
    private Context context;


    public FragmentMercanciasFlota(BaseDatos data, String partida, Context context){
        this.database=data;
        this.partida=partida;
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View frag = inflater.inflate(R.layout.fragment_mercancias_flota, container, false);
        this.listaMF=frag.findViewById(R.id.lista_MFlo);
        this.listaMF.setOnItemClickListener(this);

        List<QueryMercanciasFlotas> lista =database.selectMercanciasFlota(this.partida);

        this.adaptador = new CustomListMercanciasFlotas(this.context,R.layout.row_mercancias_flota,lista);
        this.listaMF.setAdapter(this.adaptador);
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
}
