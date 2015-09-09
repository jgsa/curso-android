package com.jgsa.listas;

import android.app.ListActivity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String[] programas =  { "Arrow", "The Flash", "The Walking Dead", "Breaking Bad",
            "The Big Bang Theory", "Forever", "Lie to me" };
    private Context context;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Usamos activity_main.xml como interfaz gráfica
        setContentView(R.layout.activity_main);

        // Obtenermos el contexto de la aplicación
        this.context = MainActivity.this;

        // Creamos un adaptador para nuestra lista, el constructor necesita como parámetros el
        // contexto de la aplicación, el layout en el que mostrará y el contenido que
        // tendrá el adaptador.
        ListAdapter adaptadorFilaGenerica = new ArrayAdapter<String>(this.context, android.R.layout.simple_list_item_1, this.programas);


        // Creamos un adaptador utilizando nuestra fila customizada en mi_fila.xml
        // el consturctor necesita como parámetros el layout y el ID del TextView
        // dentro de


        // Hacemos referencia a la lista declarada en activity_main.xml
        this.lista = (ListView) findViewById(R.id.listView);

        // Seleccionamos el adaptador que tendrá nuestra lista
        this.lista.setAdapter(adaptadorFilaGenerica);


        // Afrefamos un escuchador a la lista, cada vez que cualquier elemento de la qlista
        // sea seleccionada el método onItemClick será llamado automáticamente
        this.lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Mostramos una notificación Toast con la opción seleccionada
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
