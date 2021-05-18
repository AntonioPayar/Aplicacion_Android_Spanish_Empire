package com.example.proyecto.CustomListViews;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.QueryClase.QueryProductos;
import com.example.proyecto.R;

import java.util.List;

public class CustomListProductos extends ArrayAdapter<QueryProductos> {

    private List<QueryProductos> products;
    private Context contexto;
    private  int resorceLayout;
    private View view;


    public CustomListProductos(@NonNull Context context, int resource, @NonNull List<QueryProductos> objects) {
        super(context, resource, objects);
        this.products =objects;
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

        QueryProductos producto_seleccionada= products.get(position);


        TextView id = view.findViewById(R.id.textViewrowProID);
        id.setText(""+position);

        TextView pais = view.findViewById(R.id.textViewrowProPais);
        //StringBuilder st = new StringBuilder(producto_seleccionada.getPais());
        //st.setLength(12);
        String pais01=producto_seleccionada.getPais();

        for(int i=pais01.length();i<=13;i++){
            pais01=pais01+" ";
        }
        pais.setText(pais01);

        TextView turno = view.findViewById(R.id.textViewrowProTurno);
        turno.setText(producto_seleccionada.getTurno());

        TextView produc = view.findViewById(R.id.textViewrowProPro);
        produc.setText(producto_seleccionada.getProducto());

        TextView cantidad = view.findViewById(R.id.textViewrowProCant);
        cantidad.setText(producto_seleccionada.getCantidad());

        TextView partida = view.findViewById(R.id.textViewrowProPartid);
        partida.setText(producto_seleccionada.getPartida());

        return view;
    }

    public void elementoSeleccionado(int posicion){
        Toast.makeText(contexto, "Hola", Toast.LENGTH_SHORT).show();
        //imagen02.setImageResource(R.drawable.select);
    }
}

