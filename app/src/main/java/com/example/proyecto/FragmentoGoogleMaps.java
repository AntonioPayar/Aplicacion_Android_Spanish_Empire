package com.example.proyecto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.proyecto.Clases.Aragon;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class FragmentoGoogleMaps extends Fragment{

    GoogleMap map;
    MapView mapView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fragmento_google_maps, container, false);
        SupportMapFragment supportMapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.mapa01);
        System.out.println(supportMapFragment.toString());
        //SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapa01);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                map=googleMap;
                LatLng pp = new LatLng(11.5448729,104.8921668);
                MarkerOptions options= new MarkerOptions();
                options.position(pp).title("Phonn");
                map.addMarker(options);
                map.moveCamera(CameraUpdateFactory.newLatLng(pp));
            }
        });
        return view;
    }

    public void ponerPutoOrigen(String zona){
        MarkerOptions options= new MarkerOptions();
        map.clear();
        LatLng pp;
        switch (zona){
            case "Castilla":
                pp = new LatLng(39.86207227028889, -4.028269508922524);
                options.position(pp).title("Castilla");
                map.addMarker(options);
                map.moveCamera(CameraUpdateFactory.newLatLng(pp));
                break;
            case "Aragon":
                pp = new LatLng(41.68859259085209, -0.8883781321957582);
                options.position(pp).title("Aragon");
                map.addMarker(options);
                map.moveCamera(CameraUpdateFactory.newLatLng(pp));
                break;
            case "Borgona":
                pp = new LatLng(52.136935588827875, 5.537230101213061);
                options.position(pp).title("Borgoña");
                map.addMarker(options);
                map.moveCamera(CameraUpdateFactory.newLatLng(pp));
                break;
            case "Austria":
                pp = new LatLng(47.605899346229954, 13.982598996281792);
                options.position(pp).title("Austria");
                map.addMarker(options);
                map.moveCamera(CameraUpdateFactory.newLatLng(pp));
                break;
            case "Nueva Espana":
                pp = new LatLng(23.978563250367273, -102.27871314048332);
                options.position(pp).title("Nueva España");
                map.addMarker(options);
                map.moveCamera(CameraUpdateFactory.newLatLng(pp));
                break;
            case "Nueva Granada":
                pp = new LatLng(3.294038028616302, -73.23185342121373);
                options.position(pp).title("Nueva Granada");
                map.addMarker(options);
                map.moveCamera(CameraUpdateFactory.newLatLng(pp));
                break;
            case "Peru":
                pp = new LatLng(-10.449589496043345, -74.96648737006696);
                options.position(pp).title("Peru");
                map.addMarker(options);
                map.moveCamera(CameraUpdateFactory.newLatLng(pp));
                break;
            case "Plata":
                pp = new LatLng(-35.073035341625776, -66.12383704028487);
                options.position(pp).title("Plata");
                map.addMarker(options);
                map.moveCamera(CameraUpdateFactory.newLatLng(pp));
                break;
        }
    }

    public void cambiarmapa(String zona){
        MarkerOptions options= new MarkerOptions();
        //map.clear();
        LatLng pp;
        switch (zona){
            case "Castilla":
                pp = new LatLng(39.86207227028889, -4.028269508922524);
                options.position(pp).title("Castilla");
                map.addMarker(options);
                map.moveCamera(CameraUpdateFactory.newLatLng(pp));
                break;
            case "Aragon":
                pp = new LatLng(41.68859259085209, -0.8883781321957582);
                options.position(pp).title("Aragon");
                map.addMarker(options);
                map.moveCamera(CameraUpdateFactory.newLatLng(pp));
                break;
            case "Borgona":
                pp = new LatLng(52.136935588827875, 5.537230101213061);
                options.position(pp).title("Borgoña");
                map.addMarker(options);
                map.moveCamera(CameraUpdateFactory.newLatLng(pp));
                break;
            case "Austria":
                pp = new LatLng(47.605899346229954, 13.982598996281792);
                options.position(pp).title("Austria");
                map.addMarker(options);
                map.moveCamera(CameraUpdateFactory.newLatLng(pp));
                break;
            case "Nueva Espana":
                pp = new LatLng(23.978563250367273, -102.27871314048332);
                options.position(pp).title("Nueva España");
                map.addMarker(options);
                map.moveCamera(CameraUpdateFactory.newLatLng(pp));
                break;
            case "Nueva Granada":
                pp = new LatLng(3.294038028616302, -73.23185342121373);
                options.position(pp).title("Nueva Granada");
                map.addMarker(options);
                map.moveCamera(CameraUpdateFactory.newLatLng(pp));
                break;
            case "Peru":
                pp = new LatLng(-10.449589496043345, -74.96648737006696);
                options.position(pp).title("Peru");
                map.addMarker(options);
                map.moveCamera(CameraUpdateFactory.newLatLng(pp));
                break;
            case "Plata":
                pp = new LatLng(-35.073035341625776, -66.12383704028487);
                options.position(pp).title("Plata");
                map.addMarker(options);
                map.moveCamera(CameraUpdateFactory.newLatLng(pp));
                break;
        }
    }

}