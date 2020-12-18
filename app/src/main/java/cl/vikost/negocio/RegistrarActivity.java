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

public class RegistrarActivity extends AppCompatActivity {

    Button         _botonRegistrar;
    SQLiteDatabase _database;
    EditText       _userName;
    EditText       _userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        _userName = findViewById(R.id.txt_username_registrar);
        _userPassword = findViewById(R.id.txt_password_registrar);

        _botonRegistrar = findViewById(R.id.btn_registro_nuevo_usuario);
        _botonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Insert con ContentValues
                ContentValues cv = new ContentValues();
                cv.put("_username", _userName.getText().toString());
                cv.put("_password", _userPassword.getText().toString());
                if (_database != null) {
                    Long id =  _database.insert("usuarios", null, cv);
                    if (id > 0 ){
                        startActivity(new Intent(RegistrarActivity.this,  LoginActivity.class));
                    }
                }
            }
        });


        MyBaseDatos dbDataHelper = new MyBaseDatos(this);
        _database = dbDataHelper.getWritableDatabase();
        if (_database != null) {
            // Hacer las operaciones que queramos sobre la base de datos
        }


        String titleBar = "REGISTRARSE";
        if (getActionBar() != null) {
            getActionBar().setTitle(titleBar);
            getActionBar().setDisplayHomeAsUpEnabled(true);
        } else {
            getSupportActionBar().setTitle(titleBar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(this, LoginActivity.class));
        return super.onOptionsItemSelected(item);
    }
}