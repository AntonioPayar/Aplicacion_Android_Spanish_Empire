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

import com.example.QueryClase.QueryFlotasEnviadas;
import com.example.QueryClase.QueryMercanciasFlotas;
import com.example.proyecto.R;

import java.util.List;

public class CustomListFlotaEnviada extends ArrayAdapter<QueryFlotasEnviadas> {

    private List<QueryFlotasEnviadas> flota_enviada;
    private Context contexto;
    private  int resorceLayout;
    private View view;


    public CustomListFlotaEnviada(@NonNull Context context, int resource, @NonNull List<QueryFlotasEnviadas> objects) {
        super(context, resource, objects);
        this.flota_enviada =objects;
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

        QueryFlotasEnviadas flota_seleccionada= flota_enviada.get(position);

        /**Flota**/
        TextView id = view.findViewById(R.id.textViewrowFEFlota);
        String flotapaiss=flota_seleccionada.getFlota();
        //id.setText(crearString(flotapaiss,14));
        id.setText(flotapaiss);
        /**Alamacenada**/
        TextView almacenadoo = view.findViewById(R.id.textViewrowFEAlmacenada);
        almacenadoo.setText(flota_seleccionada.getAlmacenada()+"");
        /**Partida**/
        TextView parti = view.findViewById(R.id.textViewrowFEPartida);
        parti.setText(flota_seleccionada.getPartida());
        /**Turno**/
        TextView turno = view.findViewById(R.id.textViewrowFETurno);
        turno.setText(flota_seleccionada.getTurno()+"");
        /**Destino**/
        TextView destino = view.findViewById(R.id.textViewrowFEDestino);
        String destino01=flota_seleccionada.getDestino();
        destino.setText(crearString(destino01,13));
        /**Distancia**/
        TextView distancia = view.findViewById(R.id.textViewrowFeDistancia);
        distancia.setText(flota_seleccionada.getDistancia()+"");

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

