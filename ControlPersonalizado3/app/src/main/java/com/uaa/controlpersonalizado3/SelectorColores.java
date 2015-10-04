package com.uaa.controlpersonalizado3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by T on 30/09/2015.
 */
public class SelectorColores extends View {

    private Paint pRelleno;
    private Paint pBorde;
    private int alto;
    private int ancho;
    private int colorSeleccionado;
    private OnColorSelectedListener listener;

    public SelectorColores(Context context) {
        super(context);
        this.inicializar();
    }

    public SelectorColores(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.inicializar();
    }

    public SelectorColores(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs,defStyleAttr);
        this.inicializar();

    }

    private void inicializar() {
        pRelleno = new Paint();
        pBorde = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heigthMeasureSpec){
        int  ancho = calcularAncho(widthMeasureSpec);
        int alto = calcularAlto(heigthMeasureSpec);
        setMeasuredDimension(ancho,alto);

    }

    private int calcularAlto(int limiteSpec){
        int res = 100; //alto por defecto
        int modo = MeasureSpec.getMode(limiteSpec);
        int limite = MeasureSpec.getSize(limiteSpec);
        if(modo == MeasureSpec.AT_MOST){
            res = limite;
        }else if (modo == MeasureSpec.EXACTLY){
            res = limite;
        }
        return  res;
    }

    private int calcularAncho(int limitesSpec){
        int res = 200; //ancho por defecto
        int modo = MeasureSpec.getMode(limitesSpec);
        int limite = MeasureSpec.getSize(limitesSpec);
        if(modo == MeasureSpec.AT_MOST){
            res = limite;
        }else if (modo == MeasureSpec.EXACTLY){
            res = limite;
        }
        return  res;
    }


    @Override
    protected void onDraw(Canvas canvas){
        //obtenemos las dimensiones del control
        alto = getMeasuredHeight();
        ancho = getMeasuredWidth();

        //colores disponibles
        pRelleno.setStyle(Paint.Style.FILL);

        pRelleno.setColor(Color.RED);
        canvas.drawRect(0, 0, ancho / 4, alto / 2, pRelleno);

        pRelleno.setColor(Color.GREEN);
        canvas.drawRect(ancho / 4,0,2 * (ancho / 4) , alto / 2,pRelleno);

        pRelleno.setColor(Color.BLUE);
        canvas.drawRect(2 * (ancho / 4),0,3 * (ancho / 4) , alto / 2,pRelleno);

        pRelleno.setColor(Color.YELLOW);
        canvas.drawRect(3 * (ancho / 4),0,4 * (ancho / 4) , alto / 2,pRelleno);

        //color seleccionado
        pRelleno.setColor(colorSeleccionado);
        canvas.drawRect(0, alto / 2, ancho, alto, pRelleno);

        //marco del control
        pBorde.setStyle(Paint.Style.STROKE);
        pBorde.setColor(Color.BLACK);
        canvas.drawRect(0,0,ancho - 1, alto / 2, pBorde);
        canvas.drawRect(0,0,ancho - 1, alto - 1, pBorde);
    }


    public void  setOnColorSelectedListener(OnColorSelectedListener l ){
        listener = l;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX(), y = event.getY();
        //si se ha pulsado en la zon asuperior
        if( y > 0 && y < (getMeasuredHeight() / 2)){
            if(x > 0 && x < ancho / 4){
                colorSeleccionado = Color.RED;
            }else if(x > 0 && x < 2 * (ancho / 4)){
                colorSeleccionado = Color.GREEN;
            }else if(x > 0 && x < 3 * (ancho / 4)){
                colorSeleccionado = Color.BLUE;
            }else if(x > 0 && x < 4 * (ancho / 4)) {
                colorSeleccionado = Color.YELLOW;
            }
            this.invalidate();
        }else if(y > (getMeasuredHeight() / 4) && y < getMeasuredHeight() ){
            //lanzamos el evento externo de seleccion de color
            listener.onColorSelected(colorSeleccionado);
        }
        return super.onTouchEvent(event);
    }
}
