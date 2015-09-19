package com.jgsa.conversordeunidades;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    // Variables de activity_main.xml
    private RadioGroup rgUnidades;
    private Spinner spinnerUnidades;
    private EditText editMagnitud;
    private TextView textUnidad;
    private TextView textUnidadNueva;
    private TextView textResultado;
    private Button btnConvertir;

    private int radioSeleccionado = -1;
    private float equivalencia = 0;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;

        rgUnidades = (RadioGroup) findViewById(R.id.rg_unidades);
        spinnerUnidades = (Spinner) findViewById(R.id.spinner_unidades);
        editMagnitud = (EditText) findViewById(R.id.edit_magnitud);
        textUnidad = (TextView) findViewById(R.id.text_unidad);
        textUnidadNueva = (TextView) findViewById(R.id.text_unidad_nueva);
        textResultado = (TextView) findViewById(R.id.text_resultado);
        btnConvertir = (Button) findViewById(R.id.btn_convertir);

        // Adaptadores para los spinners de acuerdo al string-array en strings.xml
        final ArrayAdapter<CharSequence> adaptadorArea = getAdapter(R.array.sa_area);
        final ArrayAdapter<CharSequence> adaptadorLongitud = getAdapter(R.array.sa_longitud);
        final ArrayAdapter<CharSequence> adaptadorMasa = getAdapter(R.array.sa_masa);
        final ArrayAdapter<CharSequence> adaptadorVelocidad = getAdapter(R.array.sa_velocidad);

        spinnerUnidades.setAdapter(adaptadorLongitud);

        rgUnidades.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                radioSeleccionado = checkedId;
                switch (radioSeleccionado) {
                    case R.id.rb_area:
                        spinnerUnidades.setAdapter(adaptadorArea);
                        break;
                    case R.id.rb_longitud:
                        spinnerUnidades.setAdapter(adaptadorLongitud);
                        break;
                    case R.id.rb_masa:
                        spinnerUnidades.setAdapter(adaptadorMasa);
                        break;
                    case R.id.rb_velocidad:
                        spinnerUnidades.setAdapter(adaptadorVelocidad);
                        break;
                }
                textResultado.setText("");
                editMagnitud.setText("");

            }
        });

        spinnerUnidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String valor = parent.getItemAtPosition(position).toString();
                switch (valor) {
                    case "yd² → m²":
                        equivalencia = 0.83612736f;
                        textUnidad.setText("yd²");
                        textUnidadNueva.setText("m²");
                        break;
                    case "ft² → cm²":
                        equivalencia = 929.0304f;
                        textUnidad.setText("ft²");
                        textUnidadNueva.setText("cm²");
                        break;
                    case "ha → acre":
                        equivalencia = 2.47105381f;
                        textUnidad.setText("ha");
                        textUnidadNueva.setText("acre");
                        break;
                    case "acre → ha":
                        equivalencia = 0.404685642f;
                        textUnidad.setText("acre");
                        textUnidadNueva.setText("ha");
                        break;
                    case "mph → km/h":
                        equivalencia = 1.609344f;
                        textUnidad.setText("mph");
                        textUnidadNueva.setText("km/h");
                        break;
                    case "km/h → mph":
                        equivalencia = 0.621371192f;
                        textUnidad.setText("km/h");
                        textUnidadNueva.setText("mph");
                        break;
                    case "ft/s → m/s":
                        equivalencia = 0.3048f;
                        textUnidad.setText("ft/s");
                        textUnidadNueva.setText("m/s");
                        break;
                    case "m/s → ft/s":
                        equivalencia = 3.2808399f;
                        textUnidad.setText("m/s");
                        textUnidadNueva.setText("ft/s");
                        break;
                    case "lb → kg":
                        equivalencia = 0.45359237f;
                        textUnidad.setText("lb");
                        textUnidadNueva.setText("kg");
                        break;
                    case "kg → lb":
                        equivalencia = 2.20462262f;
                        textUnidad.setText("kg");
                        textUnidadNueva.setText("lb");
                        break;
                    case "oz → g":
                        equivalencia = 28.3495231f;
                        textUnidad.setText("oz");
                        textUnidadNueva.setText("g");
                        break;
                    case "g → oz":
                        equivalencia = 0.0352739619f;
                        textUnidad.setText("g");
                        textUnidadNueva.setText("oz");
                        break;
                    case "mi → km":
                        equivalencia = 1.609344f;
                        textUnidad.setText("mi");
                        textUnidadNueva.setText("km");
                        break;
                    case "km → mi":
                        equivalencia = 0.621371192f;
                        textUnidad.setText("km");
                        textUnidadNueva.setText("mi");
                        break;
                    case "ft → cm":
                        equivalencia = 30.48f;
                        textUnidad.setText("ft");
                        textUnidadNueva.setText("cm");
                        break;
                    case "cm → ft":
                        equivalencia = 0.032808399f;
                        textUnidad.setText("cm");
                        textUnidadNueva.setText("ft");
                        break;
                }
                textResultado.setText("");
                editMagnitud.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                equivalencia = 1.609344f;
                textUnidad.setText("mi");
                textUnidadNueva.setText("km");
            }
        });


        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editMagnitud.getText().length() == 0) {
                    Toast.makeText(context, "¡No has ingresado la cantidad a convertir!", Toast.LENGTH_SHORT).show();
                } else {
                    convertir();
                }
            }
        });


    }

    private void convertir() {
        float cantidad = Float.parseFloat(editMagnitud.getText().toString());
        float conversion = equivalencia * cantidad;
        textResultado.setText(String.valueOf(conversion));
    }

    /**
     * Sobrecarga de getAdapter(String[] array)
     * Método utilizado para obtener un ArrayAdapter desde un recurso string-array
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
