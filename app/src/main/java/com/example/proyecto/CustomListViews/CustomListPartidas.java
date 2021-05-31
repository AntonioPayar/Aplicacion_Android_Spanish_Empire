package com.example.proyecto.CustomListViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proyecto.R;

import java.util.ArrayList;
import java.util.List;

public class CustomListPartidas extends ArrayAdapter<String> {

    private List<String> partidas;
    private Context contexto;
    private  int resorceLayout;
    private View view;


    public CustomListPartidas(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        this.partidas=objects;
        this.contexto=context;
        this.resorceLayout=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        view=convertView;

        if(view==null){
            view = LayoutInflater.from(this.contexto).inflate(this.resorceLayout,null);
        }

        String partida_seleccionada=partidas.get(position);

        ImageView imagen = view.findViewById(R.id.ImagenrowPartida);
        imagen.setImageResource(R.drawable.engranaje1);

        TextView texto01 = view.findViewById(R.id.textViewrowPartida);
        texto01.setText(partida_seleccionada);

        return view;
    }

    public void elementoSeleccionado(int posicion){
        ImageView imagen02 = view.findViewById(R.id.Imagenselect);
        //imagen02.setImageResource(R.drawable.select);
    }
}
