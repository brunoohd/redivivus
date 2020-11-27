package com.example.redivivus;

import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaActivity extends AppCompatActivity  implements
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback {

    private static boolean LOCATION_PERMISSION_REQUEST_CODE = false;
    public GoogleMap mapa;
    public LatLng localizacao = new LatLng(-23.5914954, -48.0649624);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapa_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.nossoMapa);
        mapFragment.getMapAsync(MapaActivity.this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mapa = googleMap;
        mapa.setMyLocationEnabled(true);
        mapa.setOnMyLocationButtonClickListener(this);
        mapa.setOnMyLocationClickListener(this);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(localizacao, 11);
        mapa.animateCamera(update);
        pontosDescarte(); //método para iniciar os marcadores dos pontos de descarte
    }

    private void pontosDescarte(){
        final LatLng ECOPONTO = new LatLng(-23.5670508, -48.0273684);
        final LatLng COOPERITA = new LatLng(-23.5684936,-48.0304146);
        final LatLng RAIA = new LatLng(-23.5858486,-48.0480597);

        //Marcador do ponto de descarte Ecoponto
        mapa.addMarker(new MarkerOptions()
                .position(ECOPONTO)
                .title("Ecoponto")
                .snippet("Descarte de entulhos, resto de construção, movéis velhos"));

        //Marcador do ponto de descarte Cooperita
        mapa.addMarker(new MarkerOptions()
                .position(COOPERITA)
                .title("Cooperita - Coop. de Reciclagem de Itapetininga")
                .snippet("Descarte de recicláveis")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        //Marcador do ponto de descarte Drogaria Raia
        mapa.addMarker(new MarkerOptions()
                .position(RAIA)
                .title("Drogaria Raia")
                .snippet("Descarte de medicamentos")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "Você está aqui", Toast.LENGTH_LONG).show();
        return false;
    }

}