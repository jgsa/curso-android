package com.jgsa.spinners;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Declaramos los controles contenidos en activity_main.xml
    // para poder utilizarlos mas adelante
    private Spinner spinnerList1;
    private Spinner spinnerList2;

    // Usaremos un atributo de tipo Context, donde
    // guardaremos el contexto de nuesta aplicación
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtenemos el contexto de nuestra aplicación
        this.context = MainActivity.this;

        // Hacemos referencia a los controles spinner_list
        // declarados en activity_main.xml
        this.spinnerList1 = (Spinner) findViewById(R.id.spinner_list1);
        this.spinnerList2 = (Spinner) findViewById(R.id.spinner_list2);

        // Declaramos un array de tipo String donde almacenaremos
        // las opciones que se desplegarán dentro del Spinner
        final String[] LENGUAJES = { "JAVA", "PHP", "C", "C++" };

        // Creamos un Adaptador utilizando unvector de Java
        ArrayAdapter<String> adaptador1 = this.getAdapter(LENGUAJES);

        // Creamos un adaptador utilizando un recurso de tipo strin-array
        // en values/strings.xml
        ArrayAdapter<CharSequence> adaptador2 = this.getAdapter(R.array.sa_lenguajes);

        // Ponemos al primer spinner el adaptador creado desde el vector de Java
        this.spinnerList1.setAdapter(adaptador1);

        // De igual manera al segundo spinner pero ahora con el adaptador
        // creado desde el recurso string-array en values/strings.xml
        this.spinnerList2.setAdapter(adaptador2);

        // Registramos eventos
        this.spinnerList1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            /**
             * Método llamado automáticamente al seleccionar algún item
             *
             * @param parent El AdapterView donde la selección ocurrió
             * @param view La vista dentro del AdapterView que fue clickeado
             * @param position La posición del elemento que fue clickeado
             * @param id El id del item seleccionado
             * */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "Item seleccionado:" + LENGUAJES[position], Toast.LENGTH_SHORT);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        this.spinnerList2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            /**
             * Método llamado automáticamente al seleccionar algún item
             *
             * @param parent El AdapterView donde la selección ocurrió
             * @param view La vista dentro del AdapterView que fue clickeado
             * @param position La posición del elemento que fue clickeado
             * @param id El id del item seleccionado
             * */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "Item seleccionado:" +  parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
    }

    /**
     * Método utilizado para obtener un ArrayAdapter desde un
     * vector de Java.
     *
     * @param array String[]
     * @return ArrayAdapter<String>
     **/
    private ArrayAdapter<String> getAdapter(String[] array) {
        return new ArrayAdapter<String>(this.context, android.R.layout.simple_spinner_dropdown_item, array);
    }

    /**
    * Sobrecarga de getAdapter(String[] array)
    * Método utilizado para obtener un ArrayAdapter desde
    * un recurso string-array
    * @param recurso;
    * @return ArrayAdapter<CharSequence>
    **/
    private ArrayAdapter<CharSequence> getAdapter(int recurso) {
        return ArrayAdapter.createFromResource(this.context, recurso, android.R.layout.simple_spinner_dropdown_item);
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
