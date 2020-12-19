package cl.vikost.negocio;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cl.vikost.data.MyBaseDatos;
import cl.vikost.modelo.Donativo;
import cl.vikost.modelo.VariablesGlobales;

public class ListAsignarActivity extends AppCompatActivity {

    SQLiteDatabase _database;
    List<Donativo> _lstDonativos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_asignar);

        _lstDonativos = new ArrayList<>();
        Cursor      cursor       = null;
        MyBaseDatos dbDataHelper = new MyBaseDatos(this);
        _database = dbDataHelper.getWritableDatabase();
        if (_database != null) {
            cursor = _database.rawQuery("SELECT d._id, d._usuario, _producto, count(p._id) AS _cantidad FROM donativos d LEFT JOIN postulantes p ON d._id = p._donativo WHERE d._usuario=? GROUP BY d._id, d._usuario, d._producto;",  new String[] {VariablesGlobales.getInstance().usuario });
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Donativo donativo = new Donativo();
                String   usuario  = cursor.getString(cursor.getColumnIndex("_usuario"));
                donativo.setUsuario(usuario);
                String producto = cursor.getString(cursor.getColumnIndex("_producto"));
                donativo.setTitulo(producto);
                int cantidad = cursor.getInt(cursor.getColumnIndex("_cantidad"));
                donativo.setCantidadPostulantes(cantidad);
                Long id = cursor.getLong(cursor.getColumnIndex("_id"));
                donativo.setId(id);
                _lstDonativos.add(donativo);
                cursor.moveToNext();
            }
            cursor.close();
        }

        ListAsignarActivity.AdaptadorDonativos adapter  = new ListAsignarActivity.AdaptadorDonativos(this);
        ListView                               listview = (ListView) findViewById(R.id.lst_asignaciones);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListAsignarActivity.this, AsignarActivity.class);
                intent.putExtra("donativo", _lstDonativos.get(i));
                startActivityForResult(intent, 0);
            }
        });

        String titleBar = "Mis Donativos";
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
        startActivity(new Intent(this, MainActivity.class));
        return super.onOptionsItemSelected(item);
    }

    class AdaptadorDonativos extends ArrayAdapter<Donativo> {

        AppCompatActivity appCompatActivity;

        AdaptadorDonativos(AppCompatActivity context) {
            super(context, R.layout.detalle_donativo, _lstDonativos);
            appCompatActivity = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View           item     = inflater.inflate(R.layout.detalle_donativo, null);

            TextView txtTitulo = (TextView) item.findViewById(R.id.txt_titulo_donativo);
            txtTitulo.setText(_lstDonativos.get(position).getTitulo().toUpperCase());

            TextView txtUsuario = (TextView) item.findViewById(R.id.txt_usuario_donativo);
            txtUsuario.setText(_lstDonativos.get(position).getUsuario());

            TextView txtCantPostulantes = (TextView) item.findViewById(R.id.txt_cantidad_postulantes_donativo);
            txtCantPostulantes.setText(String.format( "Postulantes: %s", _lstDonativos.get(position).getCantidadPostulantes()));

            TextView txtFecha = (TextView) item.findViewById(R.id.txt_fecha_donativo);
            txtFecha.setText("");

            return (item);
        }
    }
}