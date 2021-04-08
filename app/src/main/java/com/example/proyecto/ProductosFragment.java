package com.example.proyecto;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ProductosFragment extends Fragment {
    private int zona;
    /**Necesito instanciar un constructor para que el metodo abrirFrameProductos de la clase Juego pueda pasarme por parametros la zona antes de mostrar el Fragment**/
    public ProductosFragment(int zona){
        this.zona=0;
        this.zona=zona;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_productos, container, false);
    }

    /**Metodo que cambia la imagen del fragment de Productos dependiendo la zona en la que se haya pulsado**/
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ImageView imagen = (ImageView) getView().findViewById(R.id.imageViewProdu);
        switch (zona){
            case 1:
                imagen.setImageResource(R.drawable.tabloncastilla);
                break;
            case 2:
                imagen.setImageResource(R.drawable.tablonaragon);
                break;
            case 3:
                imagen.setImageResource(R.drawable.tablonborgona);
                break;
            case 4:
                imagen.setImageResource(R.drawable.tablonaustria);
                break;
            case 5:
                imagen.setImageResource(R.drawable.tablonnuevaespana);
                break;
            case 6:
                imagen.setImageResource(R.drawable.tablonnuevagranada);
                break;
            case 7:
                imagen.setImageResource(R.drawable.tablonperu);
                break;
            case 8:
                imagen.setImageResource(R.drawable.tablonplata);
                break;
            case 0:
                break;
        }
    }
}