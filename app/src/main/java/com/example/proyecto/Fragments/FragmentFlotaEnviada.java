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

import com.example.QueryClase.QueryFlotasEnviadas;
import com.example.QueryClase.QueryProductos;
import com.example.proyecto.Base.BaseDatos;
import com.example.proyecto.CustomListViews.CustomListFlotaEnviada;
import com.example.proyecto.CustomListViews.CustomListProductos;
import com.example.proyecto.R;

import java.util.List;


public class FragmentFlotaEnviada extends Fragment implements AdapterView.OnItemClickListener{

    private BaseDatos database;
    private ListView listaProductos;
    private CustomListFlotaEnviada adaptador;
    private String partida;
    private Context context;

    public FragmentFlotaEnviada(BaseDatos data, String partida, Context context){
        this.database=data;
        this.partida=partida;
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View frag = inflater.inflate(R.layout.fragment_flota_enviada, container, false);
        this.listaProductos=frag.findViewById(R.id.lista_FloEnvi);
        this.listaProductos.setOnItemClickListener(this);

        List<QueryFlotasEnviadas> lista =database.selectFlotaEnviada(this.partida);

        this.adaptador = new CustomListFlotaEnviada(this.context,R.layout.row_flota_enviada,lista);
        this.listaProductos.setAdapter(this.adaptador);
        return frag;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder adb=new AlertDialog.Builder(this.context);
        adb.setTitle("Informacion Seleccionada");
        adb.setMessage("Flota : "+this.adaptador.getItem(position).getFlota()+"\nAlmacenada : "+this.adaptador.getItem(position).getAlmacenada()+"\nPartida : "+this.adaptador.getItem(position).getPartida()+"\nTurno : "+this.adaptador.getItem(position).getTurno()+"\nDestino : "+this.adaptador.getItem(position).getDestino()+"\nDistancia : "+this.adaptador.getItem(position).getDistancia());
        adb.setNegativeButton("Ok", null);
        adb.show();
    }
}