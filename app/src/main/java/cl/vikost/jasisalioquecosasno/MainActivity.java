package cl.vikost.jasisalioquecosasno;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ImageViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity{
   ImageViewCompat _foto;
    Button _botonContacto;
    Button _botonAsignar;
    Button _botonDonar;
    Button _botonAsociaciones;

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
                Intent intent = new Intent(v.getContext(), DonarActivity.class);
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


        String titleBar = "Recicladona";
        if (getActionBar() != null) {
            getActionBar().setTitle(titleBar);
        } else {
            getSupportActionBar().setTitle(titleBar);
        }
    }

}