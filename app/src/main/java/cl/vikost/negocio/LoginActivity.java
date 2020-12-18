package cl.vikost.negocio;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import cl.vikost.data.MyBaseDatos;
import cl.vikost.modelo.VariablesGlobales;

public class LoginActivity extends AppCompatActivity {

    Button         _btnLogin;
    EditText       _txtUserName;
    EditText       _txtPassword;
    EditText       _txtRespuesta;
    Button         _btnRegistrar;
    SQLiteDatabase _database;
    Cursor         _cursor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _txtUserName = findViewById(R.id.txtUserName);
        _txtUserName.setText("ctapia");

        _txtPassword = findViewById(R.id.txtPassword);
        _txtPassword.setText("123");

        _txtRespuesta = findViewById(R.id.txtRespuesta);
        _txtRespuesta.setTextColor(Color.RED);


        MyBaseDatos dbDataHelper = new MyBaseDatos(this);
        _database = dbDataHelper.getWritableDatabase();

        _btnLogin = findViewById(R.id.btn_entrar);
        _btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = _txtUserName.getText().toString();
                String password = _txtPassword.getText().toString();
                if (_database != null) {
                    _cursor = _database.rawQuery("SELECT * FROM usuarios where _username=? and _password=?;",
                            new String[]{userName, password});

                    _cursor.moveToFirst();
                    if (!_cursor.isAfterLast()) {
                        VariablesGlobales.getInstance().usuario = userName;
                        _cursor.close();
                        _txtRespuesta.setText("");
                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                        startActivityForResult(intent, 0);
                    } else {
                        VariablesGlobales.getInstance().usuario = "";
                        _cursor.close();
                        _txtRespuesta.setText("Error al entrar en la app");
                    }
                }
            }
        });

        _btnRegistrar = findViewById(R.id.btn_registro);
        _btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_txtUserName.getText().toString().equals(_txtPassword.getText().toString())) {
                    _txtRespuesta.setText("");
                    Intent intent = new Intent(v.getContext(), RegistrarActivity.class);
                    startActivityForResult(intent, 0);
                } else {
                    _txtRespuesta.setText("Error al entrar en la app");

                }
            }
        });
        _txtRespuesta.setEnabled(false);

    }

}