package cl.vikost.negocio;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cl.vikost.data.MyBaseDatos;
import cl.vikost.modelo.Donativo;
import cl.vikost.modelo.Postulante;
import cl.vikost.modelo.VariablesGlobales;

public class AsignarActivity extends AppCompatActivity {

    List<Postulante>    _postulantes = new ArrayList<>();
    Donativo            _donativo;
    SQLiteDatabase      _database;
    Cursor   _cursor = null;
    TextView _txtProducto = null;

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
        _cursor.close();

        _txtProducto = findViewById(R.id.asignar_txt_producto);
        _txtProducto.setText(_donativo.getTitulo());

        final AsignarActivity.AdaptadorPostulantes adapter  = new AsignarActivity.AdaptadorPostulantes(this);
        final ListView                                         listview = (ListView) findViewById(R.id.asignar_lst_postulantes);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(ListDonativosActivity.this, PostularActivity.class);
//                intent.putExtra("donativo", _lstDonativos.get(i));
//                startActivityForResult(intent, 0);
//              Toast.makeText(ListDonativosActivity.this, _lstDonativos.get(i).getTitulo(), Toast.LENGTH_LONG).show();

            }
        });

    }

    class AdaptadorPostulantes extends ArrayAdapter<Postulante> {

        AppCompatActivity appCompatActivity;

        AdaptadorPostulantes(AppCompatActivity context) {
            super(context, R.layout.detalle_postulante , _postulantes);
            appCompatActivity = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View           item     = inflater.inflate(R.layout.detalle_postulante, null);


            TextView txtUsuario = (TextView) item.findViewById(R.id.postulante_nombre);
            txtUsuario.setText(_postulantes.get(position).usuario);

            TextView txtFecha = (TextView) item.findViewById(R.id.postulante_fecha);
            txtFecha.setText(_postulantes.get(position).fecha);


            return (item);
        }
    }
}