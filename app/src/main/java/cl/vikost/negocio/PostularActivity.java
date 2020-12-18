package cl.vikost.negocio;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;

import cl.vikost.data.MyBaseDatos;
import cl.vikost.modelo.Donativo;
import cl.vikost.modelo.VariablesGlobales;

public class PostularActivity extends AppCompatActivity {

    Donativo       _donativo;
    TextView       _txtTituloProducto;
    Button         _btnPostular;
    SQLiteDatabase _database;
    Cursor         _cursor = null;
    Boolean        _isOk   = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postular);

        Intent i = getIntent();
        _donativo = (Donativo) i.getSerializableExtra("donativo");

        _txtTituloProducto = findViewById(R.id.txt_titulo_postular);
        _txtTituloProducto.setText(_donativo.getTitulo());

        _btnPostular = findViewById(R.id.btn_postular);

        MyBaseDatos dbDataHelper = new MyBaseDatos(this);
        _database = dbDataHelper.getWritableDatabase();

        if (_database != null) {
            String sql = "SELECT * FROM postulantes WHERE _donativo=? AND _username=?";
            _cursor = _database.rawQuery(sql, new String[]{_donativo.getId().toString(), VariablesGlobales.getInstance().usuario});
            _cursor.moveToFirst();
            if (!_cursor.isAfterLast()) {
                int estado = _cursor.getInt(_cursor.getColumnIndex("_estado"));
                _btnPostular.setText( estado > 0 ? "CANCELAR POSTULACION" : "POSTULAR");
                _isOk = true;
            } else {
                _btnPostular.setText("POSTULAR");
                _isOk = false;
            }
            _cursor.close();
        }

        _btnPostular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_isOk) {
                    ContentValues cv = new ContentValues();
                    cv.put("_estado", _btnPostular.getText().toString().equals("POSTULAR") ? 1 : 0);
                    cv.put("_fecha", LocalDateTime.now().toString());
                    int rowAffected = _database.update("postulantes", cv, "_donativo=? AND _username=?", new String[]{_donativo.getId().toString(), VariablesGlobales.getInstance().usuario});
                    _isOk = rowAffected > 0;
                    _btnPostular.setText(rowAffected > 0 ? "POSTULAR" : "CANCELAR POSTULACION");
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put("_username", VariablesGlobales.getInstance().usuario);
                    cv.put("_fecha", LocalDateTime.now().toString());
                    cv.put("_estado", 1);
                    cv.put("_donativo", _donativo.getId());
                    Long id = _database.insert("postulantes", null, cv);
                    _isOk = id > 0;
                    _btnPostular.setText(_isOk ? "CANCELAR POSTULACION" : "POSTULAR");
                }
            }
        });
    }

}