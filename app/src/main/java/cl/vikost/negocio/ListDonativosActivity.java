package cl.vikost.negocio;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cl.vikost.modelo.Donativo;
import data.MyBaseDatos;


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
            cursor = _database.rawQuery("SELECT * FROM donativos;", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String usuario  = cursor.getString(cursor.getColumnIndex("_usuario"));
                String producto = cursor.getString(cursor.getColumnIndex("_producto"));
                Donativo donativo = new Donativo();
                donativo.setTitulo(producto);
                _lstDonativos.add(donativo);
                cursor.moveToNext();
            }
            cursor.close();
        }


        final AdaptadorDonativos adapter  = new AdaptadorDonativos(this);
        final ListView           listview = (ListView) findViewById(R.id.lstDonativos);
        listview.setAdapter(adapter);

        _btnAgregar = findViewById(R.id.btn_agregar_donativo);
        _btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DonarActivity.class);
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

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

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

            TextView textView1 = (TextView) item.findViewById(R.id.txt_titulo_donativo);
            textView1.setText(_lstDonativos.get(position).getTitulo());

//            ImageView img = (ImageView) item.findViewById(R.id.img_logo_donativo);
//            if (donativos.get(position).getGenero() == 'm')
//                imageView1.setImageResource(R.mipmap.hombre);
//            else
//                imageView1.setImageResource(R.mipmap.mujer);
            return (item);
        }
    }

}