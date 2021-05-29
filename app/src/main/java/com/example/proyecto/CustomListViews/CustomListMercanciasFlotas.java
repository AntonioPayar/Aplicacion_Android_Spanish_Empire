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

import com.example.QueryClase.QueryMercancias;
import com.example.QueryClase.QueryMercanciasFlotas;
import com.example.proyecto.R;

import java.util.List;

public class CustomListMercanciasFlotas extends ArrayAdapter<QueryMercanciasFlotas> {

    private List<QueryMercanciasFlotas> mercancias_flotas;
    private Context contexto;
    private  int resorceLayout;
    private View view;


    public CustomListMercanciasFlotas(@NonNull Context context, int resource, @NonNull List<QueryMercanciasFlotas> objects) {
        super(context, resource, objects);
        this.mercancias_flotas =objects;
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

        QueryMercanciasFlotas mercancia_seleccionada= mercancias_flotas.get(position);

        /**Id**/
        TextView id = view.findViewById(R.id.textViewrowMFID);
        id.setText(""+position);
        /**FlotaPais**/
        TextView pais = view.findViewById(R.id.textViewrowMFPais);
        String pais01=mercancia_seleccionada.getFlota_pais();
        pais.setText(crearString(pais01,13));
        /**Mercancia**/
        TextView merca = view.findViewById(R.id.textViewrowMFMerca);
        String merca01=mercancia_seleccionada.getMercancia();
        merca.setText(crearString(merca01,13));
        /**Turno**/
        TextView turno = view.findViewById(R.id.textViewrowMFTurno);
        turno.setText(mercancia_seleccionada.getTurno()+"");
        /**Partida**/
        TextView partida = view.findViewById(R.id.textViewrowMFPartid);
        partida.setText(mercancia_seleccionada.getPartida());

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
