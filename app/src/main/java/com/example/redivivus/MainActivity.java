package com.example.redivivus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static boolean LOCATION_PERMISSION_REQUEST_CODE = false;
    private Button btPermitirLocalizacao, btMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        checarPermissao();
    }

    public void checarPermissao(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            Intent tela = new Intent(MainActivity.this, MapaActivity.class);
            startActivity(tela);
        } else {
            setContentView(R.layout.tela_inicial_permissao);
            btPermitirLocalizacao = (Button)findViewById(R.id.btnPermitirLocalizacao);
            btPermitirLocalizacao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ativaPermissao();
                }
            });
            btMapa=(Button)findViewById(R.id.btnMapa);
            btMapa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                        Intent tela = new Intent(MainActivity.this, MapaActivity.class);
                        startActivity(tela);
                    } else {
                        Toast.makeText(MainActivity.this, "Sem a permissão a aplicação não irá funcionar", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    public void ativaPermissao(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            LOCATION_PERMISSION_REQUEST_CODE = PermissionUtils.validate(this, 1, Manifest.permission.ACCESS_FINE_LOCATION);
        }
    }
}
