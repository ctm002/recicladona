package cl.vikost.negocio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

    public class AssociationsActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_asociaciones);

            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);

            String[] values = new String[] {
                    "Recicladona Spa",
                    "Representante Legal: Victoria Tapia",
                    "Domicilio: Cerro Catedral #9523, Villa Comendador, Pudahuel, Santiago",
                    "Celular: 98689025X",
                    "Correo Electronico: vtapia@recicladora.cl"
            };

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.list_content,
                    values);

            ListView listView = (ListView) findViewById(R.id.list_engel_asociaciones);
            listView.setAdapter(adapter);

            String titleBar = "asociacion";
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
    }
