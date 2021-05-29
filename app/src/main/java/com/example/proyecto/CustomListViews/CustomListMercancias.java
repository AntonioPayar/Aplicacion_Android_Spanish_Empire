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
import com.example.QueryClase.QueryMercancias;
import com.example.proyecto.R;

import java.util.List;

public class CustomListMercancias  extends ArrayAdapter<QueryMercancias> {

    private List<QueryMercancias> mercancias;
    private Context contexto;
    private  int resorceLayout;
    private View view;


    public CustomListMercancias(@NonNull Context context, int resource, @NonNull List<QueryMercancias> objects) {
        super(context, resource, objects);
        this.mercancias =objects;
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

        QueryMercancias mercancia_seleccionada= mercancias.get(position);

        /**Id**/
        TextView id = view.findViewById(R.id.textViewrowMercID);
        id.setText(""+position);
        /**Mercancia**/
        TextView merca = view.findViewById(R.id.textViewrowMercMercancia);
        String merca01=mercancia_seleccionada.getMercancia();
        merca.setText(crearString(merca01,13));
        /**Pais**/
        TextView pais01 = view.findViewById(R.id.textViewrowMercPais);
        String pais=mercancia_seleccionada.getPais();
        pais01.setText(crearString(pais,13));
        /**Produccion**/
        TextView produccioon = view.findViewById(R.id.textViewrowMercaPro);
        produccioon.setText(mercancia_seleccionada.getProducion());
        /**Cantidad**/
        TextView cantidadd = view.findViewById(R.id.textViewrowMercCantidad);
        cantidadd.setText(mercancia_seleccionada.getCantidadSelec()+"");
        /**Turno**/
        TextView turno = view.findViewById(R.id.textViewrowMercTurno);
        turno.setText(mercancia_seleccionada.getTurno()+"");

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

