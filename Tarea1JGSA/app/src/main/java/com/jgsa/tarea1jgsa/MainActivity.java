package com.jgsa.tarea1jgsa;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Variables para color_hexadecimal.xml
    private TextView txMensaje;
    private EditText edColor;
    private Button btnColor;
    private String color;

    // Variables para color_rgb.xml
    private TextView txtColor;
    private RadioGroup rgRGB;
    private Button btnRGB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializadores del layout
        this.initColorHexadecimal(); // <- Para el del EditText
        //this.initColorRGB(); // <- Para el de los radio buttons
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

    // Para el layout color_hexadecimal.xml
    private void initColorHexadecimal() {
        setContentView(R.layout.color_hexadecimal);

        // Asignar views a sus variables
        this.txMensaje = (TextView) findViewById(R.id.txMensaje);
        this.edColor = (EditText) findViewById(R.id.edColor);
        this.btnColor = (Button) findViewById(R.id.btnColor);

        // Listener que cambia el color del texto del TextView según la cadena escrita en el EditText
        this.btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edColor.getText().toString().equals("")) {
                    // Se envía una notificación Toast para cuando no haya nada en el EditText
                    Toast.makeText(getApplicationContext(), "Escribe un color en el campo de texto", Toast.LENGTH_SHORT).show();
                } else {
                    color = edColor.getText().toString();
                    // El try-catch lo puse para validar que la cadena sea un color válido para el
                    // método "parseColor" ... este acepta cadenas que matcheen colores hexadecimales
                    // o los nombres en inglés (red, magenta, etc)
                    try {
                        txMensaje.setTextColor(Color.parseColor(color)); // Se cambia el color del texto
                        txMensaje.setText(color); // Pongo el valor del color en el TextView
                    } catch (IllegalArgumentException e) {
                        // Si en el método "parseColor" no se recibió un valor válido mando un Toast que diga eso
                        Toast.makeText(getApplicationContext(), "Ese no es un color :(", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    // Para el layout color_rgb.xml
    private void initColorRGB() {
        setContentView(R.layout.color_rgb);

        this.txtColor = (TextView) findViewById(R.id.txtColor);
        this.rgRGB = (RadioGroup) findViewById(R.id.rg_RGB);
        this.btnRGB = (Button) findViewById(R.id.btn_RGB);

        // Listener de click del botón, en el switch cambio el color del texto y mando un toast según
        // el radio button elegido
        this.btnRGB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (rgRGB.getCheckedRadioButtonId()) {
                    case R.id.rbutton_R:
                        txtColor.setTextColor(Color.RED);
                        Toast.makeText(getApplicationContext(), "Red", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rbutton_G:
                        txtColor.setTextColor(Color.GREEN);
                        Toast.makeText(getApplicationContext(), "Green", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rbutton_B:
                        txtColor.setTextColor(Color.BLUE);
                        Toast.makeText(getApplicationContext(), "Blue", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Selecciona un color", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
