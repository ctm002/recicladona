package cl.vikost.negocio;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import cl.vikost.data.MyBaseDatos;
import cl.vikost.modelo.VariablesGlobales;

public class DonarActivity extends AppCompatActivity {

    EditText       _txtProducto;
    Button         _botonGuardar;
    SQLiteDatabase _database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar);

        String titleBar = "Donar";
        if (getActionBar() != null) {
            getActionBar().setTitle(titleBar);
            getActionBar().setDisplayHomeAsUpEnabled(true);
        } else {
            getSupportActionBar().setTitle(titleBar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        MyBaseDatos dbDataHelper = new MyBaseDatos(this);
        _database = dbDataHelper.getWritableDatabase();
//        if (_database != null) {
//            // Hacer las operaciones que queramos sobre la base de datos
//        }

        _txtProducto = findViewById(R.id.txtProducto);

        this._botonGuardar = findViewById(R.id.btn_guardar_donativo);
        this._botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Insert con ContentValues
                ContentValues cv = new ContentValues();
                cv.put("_usuario", VariablesGlobales.getInstance().usuario);
                cv.put("_producto", _txtProducto.getText().toString());
                if (_database != null) {
                    Long id =  _database.insert("donativos", null, cv);
                    if (id > 0 ){
                        startActivity(new Intent(DonarActivity.this, ListDonativosActivity.class));
                    }
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(this, ListDonativosActivity.class));
        return super.onOptionsItemSelected(item);
    }

}