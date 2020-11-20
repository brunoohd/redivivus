package com.example.redivivus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
///import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
//import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements GoogleMap.OnMyLocationClickListener, GoogleMap.OnMyLocationButtonClickListener, OnMapReadyCallback, ActivityResultContract, ActivityCompat.OnRequestPermissionsResultCallback {

    private static boolean LOCATION_PERMISSION_REQUEST_CODE= false;
    public GoogleMap mapa;
    //public LatLng localizacao = new LatLng(-23.951137, -46.339025);
    //private Button btMinhaPosicao;
    //private GeoDataClient geoDataClient;
    //private FusedLocationProviderClient mfusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ativaPermissao();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.nossoMapa);
        mapFragment.getMapAsync(MainActivity.this);
        //exemplos da aula
        //geoDataClient = Places.getGeoDataClient(MainActivity.this, null);
        //mfusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        //metodoBotao();
    }

    //botão usado no exemplo da aula
    /*private void metodoBotao(){
        btMinhaPosicao=(Button)findViewById(R.id.btnPosicao);
        btMinhaPosicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atualizaSuaLocalizacao();
            }
        });
    }
    //método usado para atualizar localização com o botão usado no exemplo da aula
    private void atualizaSuaLocalizacao(){
        try {
            LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            LocationListener locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    mapa.addMarker(new MarkerOptions().position(latLng).title("Oi"));
                    CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, 18);
                    mapa.animateCamera(update);
                    mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                    Toast.makeText(MainActivity.this, "Atualizou", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            };
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
        catch (SecurityException ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }*/

    /*@Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(MainActivity.this, "Falha na conexão", Toast.LENGTH_LONG).show();
    }*/

    /*private void ativaPermissao(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            LOCATION_PERMISSION_REQUEST_CODE=PermissionUtils.validate(this, 1, Manifest.permission.ACCESS_FINE_LOCATION);
        }
    }*/

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Minha atual localização:\n" + location, Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "Minha localização atualizada", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            LOCATION_PERMISSION_REQUEST_CODE=PermissionUtils.validate(this, 1, Manifest.permission.ACCESS_FINE_LOCATION);
        }
        mapa.setMyLocationEnabled(true);
        mapa.setOnMyLocationButtonClickListener(this);
        mapa.setOnMyLocationClickListener(this);
        //CameraUpdate update = CameraUpdateFactory.newLatLngZoom(localizacao, 18);
        //mapa.animateCamera(update);
    }
}
