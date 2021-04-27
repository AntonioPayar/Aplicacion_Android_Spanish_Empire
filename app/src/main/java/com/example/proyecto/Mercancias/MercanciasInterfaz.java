package com.example.proyecto.Mercancias;

import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto.Clases.ProductoNombre;
import com.google.android.material.slider.Slider;

public interface MercanciasInterfaz extends SeekBar.OnSeekBarChangeListener, View.OnClickListener {


    public void onWindowFocusChanged(boolean hasFocus);

    public int hideSystemBars();

    public void onDestroy();

    public void onPause();

    public void onResume();

    public void onBackPressed();

    public void volverAtras(View vista);

    public void mostrarCantidadProductos();

    public void colocarSeekBars();

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser);

    @Override
    public void onStartTrackingTouch(SeekBar seekBar);

    @Override
    public void onStopTrackingTouch(SeekBar seekBar);

    @Override
    public void onClick(View v);
}
