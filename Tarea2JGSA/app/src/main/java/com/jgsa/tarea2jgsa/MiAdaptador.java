package com.jgsa.tarea2jgsa;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Memo on 12/09/2015.
 */
public class MiAdaptador extends BaseAdapter{

    // Variable en la que almacenaremos los programas de TV que se mostrarán en la lista
    private final String[] programas;
    private final int[] imagenes;
    // LayoutInflater, sirve para "inflar" la UI en un layout declarado en un layout xml
    // "inflar" es el termino utilizado para "convetir" un XML a objetos Java
    private final LayoutInflater li;
    private Context contexto;

    // Clase estática. ViewHolder es un patrón de diseño para listas que nos permite
    // ahorrar recursos, reutilizando vistas en las listas ya creadas anteriormente.
    static class ViewHolder {
        // Dentro del ViewHolder guardaremos el textView guardaremos el textView y el imageview dentro del layout
        // utilizado por el adaptador
        public TextView tvPrograma;
        public ImageView tvImagen;

        // Constructor, recibe el TextView a guardar dentro del layout utilizado
        public ViewHolder(TextView programa, ImageView imagen){
            this.tvPrograma = programa;
            this.tvImagen = imagen;
        }
    }

    // Constructor recibe el contexto de la aplicación
    public MiAdaptador(Context context, int resource, String[] programas, int[] imagenes) {
        // guardamos el contenido de la lista en el atributo programas e imagenes
        this.programas = programas;
        this.imagenes = imagenes;
        this.contexto = context;

        // Obtenemos el servicio "Layout infater" el cual "infla" nuestra UI dentro de un xml
        // y convierte este contenido en objetos Java
        this.li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return programas.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
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
        ImageView imagen;

        // ViewHolder utilizado para otrimización
        ViewHolder holder;

        // si no existe es la primera vez que getView es llamada
        if (vistaResultado == null) {
            // se "infla" el layout "mi_fila2.xml"
            vistaResultado = li.inflate(R.layout.fila_tv, parent, false);

            // Se hace referencia al textView dentro de mi_fila2.xml
            tv = (TextView) vistaResultado.findViewById(R.id.tv_textView);
            imagen = (ImageView) vistaResultado.findViewById(R.id.tv_imageView);

            // Se genera un ViewHolder
            holder = new ViewHolder(tv,imagen);

            // se guarda el ViewHolder dentro de la vista resultado
            vistaResultado.setTag(holder);
        } else {
            // en caso de ya existir la vista se obtiene el ViewHolder
            holder = (ViewHolder) vistaResultado.getTag();
        }

        // Se pone como texto el item en la posición que tendrá esta vista en la lista
        holder.tvPrograma.setText(programas[position]);
        //holder.tvImagen.setImageDrawable(contexto.getDrawable(imagenes[position]));
        holder.tvImagen.setImageResource(imagenes[position]);

        return vistaResultado;
    }

}
