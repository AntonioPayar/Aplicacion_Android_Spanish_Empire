package com.example.proyecto;

import android.widget.TextView;

public class ThreadTexto implements Runnable {

    private String texto;
    private TextView txtTutorial;
    private int diapositiva;

    public ThreadTexto (String txt, TextView txt2, int diapositiva){
        this.texto = txt;
        this.txtTutorial = txt2;
        this.diapositiva = diapositiva;
    }

    @Override
    public void run() {

        String cadena = "";
        txtTutorial.setText("");

        if(diapositiva == -1){
            try {
                Thread.sleep(3000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        for(int i = 0; i<texto.length(); i++){
            cadena += String.valueOf(texto.charAt(i));
            txtTutorial.setText(cadena);
            try {
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
