package cl.vikost.negocio;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import cl.vikost.data.MyBaseDatos;
import cl.vikost.modelo.Donativo;
import cl.vikost.modelo.Postulante;
import cl.vikost.modelo.VariablesGlobales;

public class AsignarActivity extends AppCompatActivity {

    List<Postulante>    _postulantes;
    Donativo            _donativo;
    SQLiteDatabase      _database;
    Cursor              _cursor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignar);
        Intent intent = getIntent();
        _donativo = (Donativo) intent.getSerializableExtra("donativo");

        MyBaseDatos dbDataHelper = new MyBaseDatos(this);
        _database = dbDataHelper.getWritableDatabase();
        _cursor = _database.rawQuery("SELECT * FROM postulantes WHERE _donativo=? AND _username !=? AND _estado=1;",
                new String[] { _donativo.getId().toString(), VariablesGlobales.getInstance().usuario});
        _cursor.moveToFirst();
        while (!_cursor.isAfterLast()) {
            Postulante postulante = new Postulante();
            String username = _cursor.getString(_cursor.getColumnIndex("_username"));
            postulante.usuario = username;
            String fecha    = _cursor.getString(_cursor.getColumnIndex("_fecha"));
            postulante.fecha = fecha;
            Long   donativo = _cursor.getLong(_cursor.getColumnIndex("_donativo"));
            postulante.donativo = donativo;
            Long   id = _cursor.getLong(_cursor.getColumnIndex("_id"));
            postulante.id = id;

            _postulantes.add(postulante);
            _cursor.moveToNext();

        }

    }

    class AdaptadorPostulantes extends ArrayAdapter<Postulante> {

        AppCompatActivity appCompatActivity;

        AdaptadorPostulantes(AppCompatActivity context) {
            super(context, R.layout.detalle_donativo, _postulantes);
            appCompatActivity = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View           item     = inflater.inflate(R.layout.detalle_donativo, null);

            TextView txtTitulo = (TextView) item.findViewById(R.id.txt_titulo_donativo);
//            txtTitulo.setText(_postulantes.get(position).getTitulo());

            TextView txtUsuario = (TextView) item.findViewById(R.id.txt_usuario_donativo);
//            txtUsuario.setText(_postulantes.get(position).getUsuario());

            return (item);
        }
    }
}