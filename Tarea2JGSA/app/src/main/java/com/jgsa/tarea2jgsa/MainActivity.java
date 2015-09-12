package com.jgsa.tarea2jgsa;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private String[] programas = { "Arrow", "Mr. Robot", "Parks and Recreation",
                                    "Agents of S.H.I.E.L.D.", "Fargo", "Portlandia", "Daredevil" };
    private int[] imagenes = { R.drawable.arrow, R.drawable.mrrobot, R.drawable.parksandrec,
                               R.drawable.shield, R.drawable.fargo, R.drawable.portlandia, R.drawable.daredevil };
    private Context context;
    private ListView lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Usamos main_activity como interfaz gráfica
        setContentView(R.layout.activity_main);

        // Obtenemos el contexto de la aplicación
        this.context = MainActivity.this;

        // Adaptador personalizado
        ListAdapter adaptadorTV = new MiAdaptador(this.context, 0, this.programas, this.imagenes);

        // Referenciamos la lista de activity_main y le seteamos el adaptador
        this.lista = (ListView) findViewById(R.id.listView);
        this.lista.setAdapter(adaptadorTV);

        // Evento de click en los elementos de la lista
        this.lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "Has seleccionado: " + programas[position], Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
