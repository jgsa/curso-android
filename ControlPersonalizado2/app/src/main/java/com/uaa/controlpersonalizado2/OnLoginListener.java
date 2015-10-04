package com.uaa.controlpersonalizado2;

/**
 * Evento personalizado para ControlLogin, es llamado
 */
public interface OnLoginListener {
    void onLogin(String usuario, String password);
}
