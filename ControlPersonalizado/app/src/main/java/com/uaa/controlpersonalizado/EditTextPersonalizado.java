package com.uaa.controlpersonalizado;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

import java.util.jar.Attributes;

/**
 * Created by alumno on 28/09/15.
 */
public class EditTextPersonalizado extends EditText {

    private Paint p1;
    private Paint p2;

    private float densidad;

    // Constructores utilizados por la clase EditText
    public EditTextPersonalizado(Context context) {
        super(context);
        init();
    }

    public EditTextPersonalizado(Context context, AttributeSet attrs) {
        super(context,attrs);
        init();
    }

    public EditTextPersonalizado(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    // Aqui inicializamos configuración de nuestros pinceles y densidad de pantalla
    private void init() {
        p1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        p1.setColor(Color.BLACK);
        p1.setStyle(Paint.Style.FILL);

        p2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        p2.setColor(Color.WHITE);

        densidad = getResources().getDisplayMetrics().density;
    }

    // Este metodo se manda llamar automaticamente para pintar el control en la pantalla
    // recibe de parámetros un objeto Canvas
    @Override
    public void onDraw(Canvas canvas) {
        // Llamamos al método de la superclase
        // esto pintará el control de manera automática como un
        super.onDraw(canvas);

        // Dibujamos el fondo negro del contador dibujando un rectángulo
        // utilizando ek método drawRect que recibe de parámetros coordenadas
        // relativas al tamaño de nuestro control (float left, float top, float right, float bottom, Paint paint)
        float izquierda = getWidth() - 30 * densidad,
                arriba = 5 * densidad,
                derecha = getWidth() - 5 * densidad,
                abajo = 20* densidad;
        canvas.drawRect(izquierda, arriba, derecha, abajo, p1);

        // Como dibujaremos texto debemos especificar el tamaño de este
        p2.setTextSize(10 * densidad);

        // Dibujamos el numero de caracteres sobre el contador utilizando el método drawText,
        // también utiliza las coordenadas relativas de nuestro control, note que se multiplica
        // por la densidad de pantalla ya que estos valores están dados en pixeles
        // (String text, float x, float y, Paint paint)
        int tamanoTexto = this.getText().toString().length();
        float x = getWidth() - 28 * densidad,
                y = 17 * densidad;
        canvas.drawText(String.valueOf(tamanoTexto), x, y, p2);
    }

}
