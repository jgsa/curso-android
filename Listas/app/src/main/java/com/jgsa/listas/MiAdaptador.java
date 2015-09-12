package com.jgsa.listas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Memo on 09/09/2015.
 */
public class MiAdaptador extends ArrayAdapter<String> {

    // variable en la que almacenaremos los programas de
    // tv que se mostrarán en la lista
    private final String[] programas;

    // Clase estática. ViewHolder es un patrón de diseño para listas que nos
    // permite ahorrar recursos, reutilizando vistas en las listas ya creadas anteriormente
    static class ViewHolder {
        // Demtro del ViewHolder guardaremos el textView guardaremos el textView
        // dentro del layout utilizado por el adaptador
        public TextView tv_programa;

        // Constructor, recibe el TextView a guardar dentro del layout utilizado
        public ViewHolder(TextView programa) {
            this.tv_programa = programa;
        }
    }

    // LayoutInflater, sirve para "inflar" la UI en un layout declarado en un layout xml
    // "inflar" es el termino utilizado para "convetir" un cml a objetos jafa
    private final LayoutInflater li;

    // Constructor recibe el contexto de la aplicación
    public MiAdaptador(Context context, int resource, String[] objects) {
        super(context, resource, objects);

        // guardamos el contenido de la lista en el atributo programas
        this.programas = objects;

        // Obtenemos el servicio "Layout infater" el cual "infla" nuestra UI dentro de un xml
        // y convierte este contenido en objetos Java
        this.li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    /**
     * EL método getView es llamado automáticamente por el ListView cada vez que sea
     * necesario obtener algún elemento de la lista para mostrarlo al usuario
     *
     * @param position Posición del item dentro de la lista
     * @param convertView item ya generado y guardado
     * @param parent El grupo competo de la lista
     *
     * @return view
     * **/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // vistaResultado será la vista final para ser retornada
        View vistaResultado = convertView;

        // TextView dentro del layout a inflar
        TextView tv;

        // ViewHolder utilizado para otrimización
        ViewHolder holder;

        // si no existe es la primera vez que getView es llamada
        if (vistaResultado == null) {
            // se "infla" el layout "mi_fila2.xml"
            vistaResultado = li.inflate(R.layout.mi_fila2, parent, false);

            // Se hace referencia al textView dentro de mi_fila2.xml
            tv = (TextView) vistaResultado.findViewById(R.id.mifila_textview);

            // Se genera un ViewHolder
            holder = new ViewHolder(tv);

            // se guarda el ViewHolder dentro de la vista resultado
            vistaResultado.setTag(holder);
        } else {
            // en caso de ya existir la vista se obtiene el ViewHolder
            holder = (ViewHolder) vistaResultado.getTag();
        }

        // Se pone como texto el item en la posición que tendrá esta vista en la lista
        holder.tv_programa.setText(programas[position]);

        return vistaResultado;
    }


}
