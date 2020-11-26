package com.example.redivivus;

import android.location.Location;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
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
    private Button btPermitirLocalizacao;
    //private GeoDataClient geoDataClient;
    //private FusedLocationProviderClient mfusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapa_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.nossoMapa);
        mapFragment.getMapAsync(MapaActivity.this);
        //exemplos da aula
        //geoDataClient = Places.getGeoDataClient(MainActivity.this, null);
        //mfusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        //metodoBotao();
        //ativaPermissao();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mapa = googleMap;
        mapa.setMyLocationEnabled(true);
        mapa.setOnMyLocationButtonClickListener(this);
        mapa.setOnMyLocationClickListener(this);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(localizacao, 11);
        mapa.animateCamera(update);
        pontosDescarte();
    }

    private void pontosDescarte(){
        //private static final Rai

        mapa.addMarker(new MarkerOptions().position(new LatLng(-23.5670508, -48.0273684)).title("Ecoponto").snippet("Descarte de entulhos, resto de construção, movéis velhos"));
        mapa.addMarker(new MarkerOptions().position(new LatLng(-23.5684936,-48.0304146)).title("Cooperita - Coop. de Reciclagem de Itapetininga").snippet("Descarte de recicláveis").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mapa.addMarker(new MarkerOptions().position(new LatLng(-23.5858486,-48.0480597)).title("Drogaria Raia").snippet("Descarte de medicamentos").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
    }



    /*private void ativaPermissao(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            LOCATION_PERMISSION_REQUEST_CODE=PermissionUtils.validate(this, 1, Manifest.permission.ACCESS_FINE_LOCATION);
        }

    }*/

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

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
}