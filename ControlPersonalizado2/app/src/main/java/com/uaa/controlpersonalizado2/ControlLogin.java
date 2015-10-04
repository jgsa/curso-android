package com.uaa.controlpersonalizado2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by alumno on 29/09/15.
 */
public class ControlLogin extends LinearLayout {

    private EditText txtUsuario;
    private EditText txtPassword;
    private Button btnLogin;
    private TextView lblMensaje;
    private OnLoginListener listener;

    /* Sobreescritura de constructores de LinearLayout */
    public ControlLogin(Context context) {
        super(context);
        this.inicializar();
    }

    public ControlLogin(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.inicializar();
    }

    /*
    * Aquí inicializaremos todo lo necesario para
    * que funcione nuestro control
    * */
    private void inicializar() {
        // Utilizaremos el layout 'control_login.xml' como interfaz del control
        String infService= Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        li.inflate(R.layout.control_login, this, true);

        // Obtendremos las referencias a los distintos views dentro de este Layout
        txtUsuario = (EditText) findViewById(R.id.TxtUsuario);
        txtPassword = (EditText) findViewById(R.id.TxtPassword);
        btnLogin = (Button) findViewById(R.id.BtnAceptar);
        lblMensaje = (TextView) findViewById(R.id.LblMensaje);

        // Registramos los eventos necesarios dentro de nustro control
        this.asignarEventos();
    }

    private void asignarEventos() {
        // Cada vez que el usuario de click al botón de login
        // se disparará nuestro evento personalizado "onLogin"
        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Al dar click en el boton se dispara nuestro evento OnLogin
                // utilizado de parametros el Usuario y la contraseña
                listener.onLogin(txtUsuario.getText().toString(), txtPassword.getText().toString());
            }
        });
    }

    /**
     * Método utilizado para mostrar un mensaje al usuario dentro de nuestro control
     *
     * @param msg - String
     * @param color - int
     *
     * **/
    public void setMensaje(String msg, int color) {
        lblMensaje.setText(msg);
        lblMensaje.setTextColor(color);
    }


    /**
     * Método utilizado para registrar un evento onLogin dentro del control
     *
     * @param l - OnLoginListener
     *
     * **/
    public void setOnLoginListener(OnLoginListener l) {
        listener = l;
    }
}
