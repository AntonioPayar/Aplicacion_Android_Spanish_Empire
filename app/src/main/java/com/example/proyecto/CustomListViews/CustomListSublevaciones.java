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

import com.example.QueryClase.QueryMercanciasFlotas;
import com.example.QueryClase.QuerySublevaciones;
import com.example.proyecto.R;

import java.util.List;

public class CustomListSublevaciones extends ArrayAdapter<QuerySublevaciones> {

    private List<QuerySublevaciones> sublevacioness;
    private Context contexto;
    private  int resorceLayout;
    private View view;


    public CustomListSublevaciones(@NonNull Context context, int resource, @NonNull List<QuerySublevaciones> objects) {
        super(context, resource, objects);
        this.sublevacioness =objects;
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

        QuerySublevaciones sublevacion_seleccionada= sublevacioness.get(position);

        /**Id**/
        TextView id = view.findViewById(R.id.textViewrowSubId);
        id.setText(""+position);
        /**Pais**/
        TextView pais = view.findViewById(R.id.textViewrowSubPais);
        String pais01=sublevacion_seleccionada.getPais();
        pais.setText(crearString(pais01,13));
        /**Hora**/
        TextView merca = view.findViewById(R.id.textViewrowSubHora);
        String merca01=sublevacion_seleccionada.getHora();
        merca.setText(crearString(merca01,13));
        /**Turno**/
        TextView turno = view.findViewById(R.id.textViewrowSubTurno);
        turno.setText(sublevacion_seleccionada.getTurno()+"");
        /**Partida**/
        //TextView partida = view.findViewById(R.id.textViewrowSubPartid);
        //partida.setText(sublevacion_seleccionada.getPartida());

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
