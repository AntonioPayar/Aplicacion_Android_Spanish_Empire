package com.example.proyecto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomListPartidas extends ArrayAdapter<String> {

    private List<String> partidas;
    private Context contexto;
    private  int resorceLayout;


    public CustomListPartidas(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        this.partidas=objects;
        this.contexto=context;
        this.resorceLayout=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=convertView;

        if(view==null){
            view = LayoutInflater.from(this.contexto).inflate(this.resorceLayout,null);
        }

        String partida_seleccionada=partidas.get(position);

        ImageView imagen = view.findViewById(R.id.ImagenrowPartida);
        imagen.setImageResource(R.drawable.engranaje);

        TextView texto01 = view.findViewById(R.id.textViewrowPartida);
        texto01.setText(partida_seleccionada);

        TextView texto02 = view.findViewById(R.id.textViewrowPartida02);
        texto02.setText("");

        return view;
    }
}
