package com.uaa.controlesbasicos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // main_activity.xml
    private TextView salida;
    private EditText entrada;
    private Button btnAceptar;

    // radio_buttons.xml
    private RadioGroup rg;
    private Button rb_btnAceptar;
    private int radioSeleccionado = -1;
    private String mensaje;

    // check_boxes.xml
    private CheckBox[] opciones;
    private Button cb_btnAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.initMainActivity();
        this.initRadioActivity();
        this.initCheckBoxActivity();
    }

    // inicializa layout "activity_main.xml"
    private void initMainActivity() {
        setContentView(R.layout.activity_main);
        this.entrada = (EditText) findViewById(R.id.edEntrada);
        this.salida = (TextView) findViewById(R.id.txSalida);
        this.btnAceptar= (Button) findViewById(R.id.btnAceptar);

        this.btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = entrada.getText().toString();
                salida.setText(texto);
            }
        });
    }

    private void initRadioActivity() {
        // Definimos radio_buttons.xml como la
        // interfaz que tendrá nuestra actividad
        setContentView(R.layout.radio_buttons);

        // Obtenemos referencia de los controles dentro
        // de radio_buttons.xml
        this.rg = (RadioGroup) findViewById(R.id.radio_grupo);
        this.rb_btnAceptar = (Button) findViewById(R.id.btn_radioAceptar);

        // Definimos el escuchador del evento,
        // cuando Android detecte una nueva selección
        // dentro de nuesto radio grupo se mandará
        // llamar automáticamente al método "onCheckedChange"
        this.rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // utilizamos radioSeleccionado como auxiliar,
                // guardamos el id del radio button seleccionado
                radioSeleccionado = checkedId;
            }
        });

        // Definimos el escuchador del evento, cuando Android detecte
        // un click en el botón el método será llamado automáticamente
        this.rb_btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dependiendo de la opción seleccionada
                // desplegaremos un mensaje distinto
                switch (radioSeleccionado) {
                    case R.id.rb_net:
                        mensaje = ".NET";
                        break;
                    case R.id.rb_c:
                        mensaje = "C#";
                        break;
                    case R.id.rb_java:
                        mensaje = "Java";
                        break;
                    default:
                        mensaje = "No has seleccionado nada";
                }
                // Mostramos una notificación "Toast" al usuario con la opción seleccionada.
                // El método make test necesita 3 parámetros:
                //      1. El contexto de la aplicación
                //      2. Mensaje a mostrar
                //      3. Duración en milisegundos (Toast.LENGTH_SHORT es una duración corta)
                // Es muy importante llamar el método show() de la notificación de tipo Toast
                Toast.makeText(MainActivity.this, "Seleccionaste: " + mensaje, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initCheckBoxActivity() {
        // Definimos check_boxes.xml como la interfaz que tendrá nuestra Actividad
        setContentView(R.layout.check_boxes);

        // Obtenemos referencia de los controles dentro de check_box.xml
        this.cb_btnAceptar = (Button) findViewById(R.id.cb_btn_aceptar);
        // Opciones constará de un array con los checkboxes declarados en
        // check_boxes.xml (ios, android, winphone)
        this.opciones = new CheckBox[] {
                (CheckBox) findViewById(R.id.cb_ios),
                (CheckBox) findViewById(R.id.cb_android),
                (CheckBox) findViewById(R.id.cb_winphone)
        };

        // Definimos el escuchador del evento, cuando android detecte un
        // click en el boton dentro de check_boxes.xml el método onClick
        // será llamado automaticamente
        this.cb_btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int opcSeleccionadas = 0;
                String cuales = "";
                for (int i = 0; i < opciones.length; i++) {
                    if (opciones[i].isChecked()) {
                        opcSeleccionadas++;
                        cuales += opciones[i].getText().toString() + ", ";
                    }
                }
                Toast.makeText(MainActivity.this, opcSeleccionadas + " opciones seleccionadas: " + cuales,Toast.LENGTH_SHORT).show();
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
