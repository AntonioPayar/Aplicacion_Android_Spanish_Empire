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

import com.example.QueryClase.QueryDemandas;
import com.example.QueryClase.QueryProductos;
import com.example.proyecto.R;

import java.util.List;

public class CustomListDemandas extends ArrayAdapter<QueryDemandas> {

    private List<QueryDemandas> demandas;
    private Context contexto;
    private  int resorceLayout;
    private View view;


    public CustomListDemandas(@NonNull Context context, int resource, @NonNull List<QueryDemandas> objects) {
        super(context, resource, objects);
        this.demandas =objects;
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

        QueryDemandas demanda_seleccionada= demandas.get(position);


        TextView id = view.findViewById(R.id.textViewrowDemID);
        id.setText(""+position);
        /**Descripcion**/
        TextView descripcion = view.findViewById(R.id.textViewrowDemMerca);
        String demanda=demanda_seleccionada.getDescripcion();
        descripcion.setText(crearString(demanda,10));
        /**Pais**/
        TextView pais01 = view.findViewById(R.id.textViewrowDemPais);
        String pais=demanda_seleccionada.getPais();
        pais01.setText(crearString(pais,13));
        /**Realizada**/
        TextView realiza = view.findViewById(R.id.textViewrowDemPro);
        /**Partida**/
        TextView partida = view.findViewById(R.id.textViewrowDemCant);
        partida.setText(demanda_seleccionada.getPartida());

        return view;
    }

    protected String crearString(String strin, int tamaño){
        for(int i=strin.length();i<=tamaño;i++){
            strin=strin+" ";
        }
        return strin;
    }

    public void elementoSeleccionado(int posicion){
        Toast.makeText(contexto, "Hola", Toast.LENGTH_SHORT).show();
        //imagen02.setImageResource(R.drawable.select);
    }
}
