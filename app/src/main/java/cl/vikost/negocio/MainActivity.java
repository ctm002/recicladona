package cl.vikost.negocio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ImageViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{
   ImageViewCompat _foto;
    Button _botonContacto;
    Button _botonAsignar;
    Button _botonDonar;
    Button _botonAsociaciones;
    Button _botonCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _botonContacto = findViewById(R.id.btn_contactar);
        _botonContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ContactoActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        _botonDonar = findViewById(R.id.btn_donar);
        _botonDonar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListDonativosActivity.class);
                startActivityForResult(intent, 0);
            }

        });

        _botonAsignar = findViewById(R.id.btn_asignar);
        _botonAsignar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AsignarActivity.class);
                startActivityForResult(intent, 0);
            }

        });

        _botonCerrar = findViewById(R.id.btn_cerrar);
        _botonCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivityForResult(intent, 0);
            }

        });

        String titleBar = "Recicladona";
        if (getActionBar() != null) {
            getActionBar().setTitle(titleBar);
        } else {
            getSupportActionBar().setTitle(titleBar);
        }
    }

}