package cl.vikost.negocio;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    Button   _btnLogin;
    EditText _txtUserName;
    EditText _txtPassword;
    EditText _txtRespuesta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _txtUserName = findViewById(R.id.txtUserName);
        _txtPassword = findViewById(R.id.txtPassword);
        _txtRespuesta = findViewById(R.id.txtRespuesta);
        _txtRespuesta.setTextColor(Color.RED);

        _btnLogin = findViewById(R.id.btn_entrar);
        _btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_txtUserName.getText().toString().equals(_txtPassword.getText().toString())) {
                    _txtRespuesta.setText("");
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    startActivityForResult(intent, 0);
                } else {
                    _txtRespuesta.setText("Error al entrar en la app");

                }
            }
        });

        _txtRespuesta.setEnabled(false);
    }
}