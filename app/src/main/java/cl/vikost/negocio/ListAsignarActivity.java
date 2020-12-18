package cl.vikost.negocio;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import cl.vikost.data.MyBaseDatos;
import cl.vikost.modelo.Donativo;
import cl.vikost.modelo.VariablesGlobales;

public class ListAsignarActivity extends AppCompatActivity {

    SQLiteDatabase      _database;
    ArrayList<Donativo> _lstDonativos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_asignar);

        _lstDonativos = new ArrayList<>();
        Cursor      cursor       = null;
        MyBaseDatos dbDataHelper = new MyBaseDatos(this);
        _database = dbDataHelper.getWritableDatabase();
        if (_database != null) {
            cursor = _database.rawQuery("SELECT * FROM donativos WHERE _usuario=?;",  new String[] {VariablesGlobales.getInstance().usuario });
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Donativo donativo = new Donativo();
                String   usuario  = cursor.getString(cursor.getColumnIndex("_usuario"));
                donativo.setUsuario(usuario);
                String producto = cursor.getString(cursor.getColumnIndex("_producto"));
                donativo.setTitulo(producto);
                _lstDonativos.add(donativo);
                cursor.moveToNext();
            }
            cursor.close();
        }

        ListAsignarActivity.AdaptadorDonativos adapter  = new ListAsignarActivity.AdaptadorDonativos(this);
        ListView                               listview = (ListView) findViewById(R.id.lst_asignaciones);
        listview.setAdapter(adapter);
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
            txtTitulo.setText(_lstDonativos.get(position).getTitulo());

            TextView txtUsuario = (TextView) item.findViewById(R.id.txt_usuario_donativo);
            txtUsuario.setText(_lstDonativos.get(position).getUsuario());

//            ImageView img = (ImageView) item.findViewById(R.id.img_logo_donativo);
//            if (donativos.get(position).getGenero() == 'm')
//                imageView1.setImageResource(R.mipmap.hombre);
//            else
//                imageView1.setImageResource(R.mipmap.mujer);
            return (item);
        }
    }
}