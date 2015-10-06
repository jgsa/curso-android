package com.jgsa.controlpersonalizadojgsa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Memo on 04/10/2015.
 */
public class MiAdaptador extends BaseAdapter implements Filterable {


    //private final String[] items;
    private final int[] imagenes;
    List<String> items;
    List<String> mOriginalValues;
    List<Integer> mOriginalPictures;
    // LayoutInflater, sirve para "inflar" la UI en un layout declarado en un layout xml
    // "inflar" es el termino utilizado para "convetir" un XML a objetos Java
    private final LayoutInflater li;
    private Context contexto;

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<String> FilteredArrList = new ArrayList<>();

                if (mOriginalValues == null || mOriginalPictures == null) {
                    mOriginalValues = new ArrayList<>(items);
                    mOriginalPictures = new ArrayList<>();
                    for (int i = 0; i < imagenes.length; i++) {
                        mOriginalPictures.add(imagenes[i]);
                    }
                }

                if (constraint == null || constraint.length() == 0) {

                    results.count = mOriginalValues.size();
                    results.values = mOriginalValues;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < mOriginalValues.size(); i++) {
                        String data = mOriginalValues.get(i);

                        if (data.toLowerCase().contains(constraint.toString())) {
                            FilteredArrList.add(data);
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                items = (List<String>) filterResults.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values

            }
        };
        return filter;
    }

    // Clase estática. ViewHolder es un patrón de diseño para listas que nos permite
    // ahorrar recursos, reutilizando vistas en las listas ya creadas anteriormente.
    static class ViewHolder {
        // Dentro del ViewHolder guardaremos el textView guardaremos el textView y el imageview dentro del layout
        // utilizado por el adaptador
        public TextView texto;
        public ImageView imagen;

        // Constructor, recibe el TextView a guardar dentro del layout utilizado
        public ViewHolder(TextView texto, ImageView imagen){
            this.texto = texto;
            this.imagen = imagen;
        }
    }

    // Constructor recibe el contexto de la aplicación
    public MiAdaptador(Context context, int resource, List<String> programas, int[] imagenes) {
        // guardamos el contenido de la lista en el atributo programas e imagenes
        this.items = programas;
        this.imagenes = imagenes;
        this.contexto = context;

        // Obtenemos el servicio "Layout infater" el cual "infla" nuestra UI dentro de un xml
        // y convierte este contenido en objetos Java
        this.li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
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
        TextView text;
        ImageView image;

        // ViewHolder utilizado para otrimización
        ViewHolder holder;

        // si no existe es la primera vez que getView es llamada
        if (vistaResultado == null) {
            vistaResultado = li.inflate(R.layout.mi_fila, parent, false);

            // Se hace referencia al textView dentro de mi_fila2.xml
            text = (TextView) vistaResultado.findViewById(R.id.mifila_textview);
            image = (ImageView) vistaResultado.findViewById(R.id.mifila_imageView);

            // Se genera un ViewHolder
            holder = new ViewHolder(text,image);

            // se guarda el ViewHolder dentro de la vista resultado
            vistaResultado.setTag(holder);
        } else {
            // en caso de ya existir la vista se obtiene el ViewHolder
            holder = (ViewHolder) vistaResultado.getTag();
        }

        // Se pone como texto el item en la posición que tendrá esta vista en la lista
        holder.texto.setText(items.get(position));
        //holder.tvImagen.setImageDrawable(contexto.getDrawable(imagenes[position]));
        holder.imagen.setImageResource(imagenes[position]);

        return vistaResultado;
    }

}