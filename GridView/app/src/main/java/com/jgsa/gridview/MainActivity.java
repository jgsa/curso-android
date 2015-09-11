package com.jgsa.gridview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Constante donde definimos el tamaño del array que se mandará al adapter
    private final int TAMANODATOS = 50;

    // array que se mandará al adapter
    private String[] datos = new String[TAMANODATOS];

    // Contexto de nuestra aplicación
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener contexto de la aplicación e igualarlo a nuestra variable context
        this.context = MainActivity.this;

        // Generando array a utulizar en el adaptador
        for (int i = 1; i <= this.TAMANODATOS; i++) {
            datos[i - 1] = "Dato " + i;
        }

        // adaptador a usar en nuestro GridView
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, datos);

        // "Buscamos" nuestro gridView con id = GridOpciones en nuestro layout
        GridView grdOpciones = (GridView) findViewById(R.id.girdOpciones);

        // Ponemos el adaptador generado arriba en nuestro gridView
        grdOpciones.setAdapter(adaptador);

        // registramos escuchaadores en nuestro gridView, cada vez que se de click a uno de nuestros items
        grdOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(MainActivity.this, "Opcione seleccionada: " + datos[position], Toast.LENGTH_SHORT).show();
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
