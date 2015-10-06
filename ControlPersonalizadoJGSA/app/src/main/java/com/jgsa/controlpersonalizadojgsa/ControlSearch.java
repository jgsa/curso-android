package com.jgsa.controlpersonalizadojgsa;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Memo on 04/10/2015.
 */
public class ControlSearch extends LinearLayout {

    private EditText txtSearch;
    private ListView listItems;
    //private ArrayAdapter<CharSequence> adapter;
    private MiAdaptador adapter;

    public ControlSearch(Context context) {
        super(context);
        this.init();
    }

    public ControlSearch(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    private void init() {
        String infService= Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        li.inflate(R.layout.control_search, this, true);

        txtSearch = (EditText) findViewById(R.id.txt_search);
        listItems = (ListView) findViewById(R.id.list_items);

        listItems.setTextFilterEnabled(true);


        // Registramos los eventos necesarios dentro de nustro control
        this.setEvents();
    }

    public void setAdapter(List<String> items, int[] pictures, Context context) {
        //this.adapter = new ArrayAdapter<CharSequence>(context, android.R.layout.simple_list_item_1, items);
        this.adapter = new MiAdaptador(context, 0, items, pictures);
        listItems.setAdapter(adapter);
    }

    private void setEvents() {
        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

}
