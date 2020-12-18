package cl.vikost.negocio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cl.vikost.modelo.Donativo;

public class PostularActivity extends AppCompatActivity {

    Donativo _donativo;
    TextView _txtTituloProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postular);


        Intent i = getIntent();
        _donativo = (Donativo) i.getSerializableExtra("donativo");

        _txtTituloProducto = findViewById(R.id.txt_titulo_postular);
        _txtTituloProducto.setText(_donativo.getTitulo());
    }

}