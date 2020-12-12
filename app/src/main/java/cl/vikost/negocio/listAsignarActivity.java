package cl.vikost.negocio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import data.MyDataHelper;

public class listAsignarActivity extends AppCompatActivity {
    SQLiteDatabase  _database;
    FloatingActionButton btn_Asignar_donativo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_asignar);

        final ArrayList<String> list         = new ArrayList<String>();
        Cursor                  cursor       = null;
        MyDataHelper            dbDataHelper = new MyDataHelper(this);
        _database = dbDataHelper.getWritableDatabase();
        if (_database != null) {
            cursor = _database.rawQuery("SELECT * FROM Asignar;", null);
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String usuario  = cursor.getString(cursor.getColumnIndex("_usuario"));
            String Asignar = cursor.getString(cursor.getColumnIndex("_asignar"));
            list.add(Asignar);
            cursor.moveToNext();
        }
        cursor.close();

        final ListView listview = (ListView) findViewById(R.id.btn_Asignar_donativo);
        final listAsignarActivity.StableArrayAdapter adapter  = new listAsignarActivity.StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        btn_Asignar_donativo = findViewById(R.id.btn_Asignar_donativo);
        btn_Asignar_donativo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DonarActivity.class);
                startActivityForResult(intent, 0);
            }

        });
        String titleBar = "asignacion de beneficios ";
        if (getActionBar() != null) {
            getActionBar().setTitle(titleBar);
            getActionBar().setDisplayHomeAsUpEnabled(true);
        } else {
            getSupportActionBar().setTitle(titleBar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        @Override
        public boolean onOptionsItemSelected(@NonNull  item) {
            startActivity(new Intent(this, listAsignarActivity.class));
            return super.onOptionsItemSelected(item);
        }

        private class StableArrayAdapter extends ArrayAdapter<String> {


            }



        }

    }


