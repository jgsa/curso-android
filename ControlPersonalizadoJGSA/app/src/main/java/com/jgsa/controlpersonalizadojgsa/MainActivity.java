package com.jgsa.controlpersonalizadojgsa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ControlSearch controlBusqueda;
    List<String> canciones;
    int imagenes[] = {
            R.drawable.bigdata2,
            R.drawable.afterthedisco,
            R.drawable.brokenbells,
            R.drawable.chutestoonarrow,
            R.drawable.currents,
            R.drawable.gettoheaven,
            R.drawable.ladidadi,
            R.drawable.mothboys,
            R.drawable.neonbible,
            R.drawable.ohinvertedworld,
            R.drawable.portofmorrow,
            R.drawable.reflektor,
            R.drawable.weezerblue,
            R.drawable.whenyoulandhere,
            R.drawable.wincingthenightaway
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        controlBusqueda = (ControlSearch) findViewById(R.id.control_busqueda);

        canciones = Arrays.asList(this.getResources().getStringArray(R.array.albums));

        controlBusqueda.setAdapter(canciones, imagenes, MainActivity.this);


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
