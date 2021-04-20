package com.example.proyecto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CustomAdapterListView extends BaseAdapter {
    /**Clase encargada de crear y generar el ListView de la clase CrearFloras**/
    Context context;
    private LinkedHashMap<Integer, Mercancia> mercancias;
    private ArrayList<Mercancia>mercancias01;

    public CustomAdapterListView(Context context,LinkedHashMap<Integer, Mercancia>mercan){
        this.context=context;
        this.mercancias=mercan;
        cargarMercancias();
    }

    private void cargarMercancias(){
    //LinkedHashMap<Integer, Mercancia> mercancias02= new LinkedHashMap<>();
        this.mercancias01= new ArrayList<>();
    int contador=0;

        for( int i : this.mercancias.keySet() ){
            if(this.mercancias.get(i)!=null){
                Mercancia mer = this.mercancias.get(i);
                mer.setvalor_chapuza(i);
                //mer.setNombre(mer.getNombre()+""+i);
                mercancias01.add(mer);
            }
        }

    //    for (Mercancia mer:this.mercancias.values()){
     //       contador++;
     //       mercancias02.put(contador,mer);
     //   }
     //   this.mercancias=mercancias02;
    }

    public Mercancia retornarMercancia(int posicion){
        return this.mercancias01.get(posicion);
    }

    @Override
    public int getCount() {
        return this.mercancias01.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        //Hay que tener en cuenta que los LinkedHasMap no suelen tener valor 0 por lo que para que salte error sumamos uno
        //Mercancia mercancia = this.mercancias.get(position);
        Mercancia mercancia = this.mercancias01.get(position);

        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.listview_crear_flota,null);
            textView=convertView.findViewById(R.id.textView13);

            //Aqui se tiene que especificar la imagen y el texto que se ha de poner dentro de cada listView
            if(mercancia==null){
                textView.setText("Mercancia almacenada");
            }else{
                seleccionarImagen(mercancia,convertView);
                textView.setText(mercancia.getNombre());
            }
        }

        return convertView;
    }

    private void seleccionarImagen(Mercancia mercancia,View convertView){
        ImageView imagen;
        imagen=convertView.findViewById(R.id.imageView10);
            switch (mercancia.getProducto().getNombre()){
                case Oro:
                    imagen.setImageResource(R.drawable.oro);
                    break;
                case Cafe:
                    imagen.setImageResource(R.drawable.cafe);
                    break;
                case Maiz:
                    imagen.setImageResource(R.drawable.maiz);
                    break;
                case Uvas:
                    imagen.setImageResource(R.drawable.uvas);
                    break;
                case Arroz:
                    imagen.setImageResource(R.drawable.arroz);
                    break;
                case Plata:
                    imagen.setImageResource(R.drawable.plata);
                    break;
                case Trigo:
                    imagen.setImageResource(R.drawable.trigo);
                    break;
                case Hierro:
                    imagen.setImageResource(R.drawable.hierro);
                    break;
                case Patata:
                    imagen.setImageResource(R.drawable.patata);
                    break;
                case Tabaco:
                    imagen.setImageResource(R.drawable.tabaco);
                    break;
                case Tomate:
                    imagen.setImageResource(R.drawable.tomate);
                    break;
                default:
                    break;
            }
    }

    public void remove(int position){
        this.mercancias01.remove(position);;
    }
}
