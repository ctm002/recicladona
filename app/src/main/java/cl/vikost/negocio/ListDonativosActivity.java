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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import cl.vikost.data.MyBaseDatos;
import cl.vikost.modelo.Donativo;
import cl.vikost.modelo.VariablesGlobales;


public class ListDonativosActivity extends AppCompatActivity {

    SQLiteDatabase       _database;
    FloatingActionButton _btnAgregar;
    ArrayList<Donativo>  _lstDonativos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_donativos);

        _lstDonativos = new ArrayList<>();
        Cursor      cursor       = null;
        MyBaseDatos dbDataHelper = new MyBaseDatos(this);
        _database = dbDataHelper.getWritableDatabase();
        if (_database != null) {
            cursor = _database.rawQuery("SELECT * FROM donativos WHERE _usuario !=?;",  new String[] {VariablesGlobales.getInstance().usuario });
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Donativo donativo = new Donativo();
                String   usuario  = cursor.getString(cursor.getColumnIndex("_usuario"));
                donativo.setUsuario(usuario);
                String producto = cursor.getString(cursor.getColumnIndex("_producto"));
                donativo.setTitulo(producto);
                Long id = cursor.getLong(cursor.getColumnIndex("_id"));
                donativo.setId(id);
                _lstDonativos.add(donativo);
                cursor.moveToNext();
            }
            cursor.close();
        }


        final AdaptadorDonativos adapter  = new AdaptadorDonativos(this);
        final ListView           listview = (ListView) findViewById(R.id.lstDonativos);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(ListDonativosActivity.this, PostularActivity.class);
                intent.putExtra("donativo", _lstDonativos.get(i));

                startActivityForResult(intent, 0);
//              Toast.makeText(ListDonativosActivity.this, _lstDonativos.get(i).getTitulo(), Toast.LENGTH_LONG).show();

            }
        });

        _btnAgregar = findViewById(R.id.btn_agregar_donativo);
        _btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DonativoActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        String titleBar = "Donativos";
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

            return (item);
        }
    }

}