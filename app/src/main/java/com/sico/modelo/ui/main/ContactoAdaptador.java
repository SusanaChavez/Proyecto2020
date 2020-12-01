package com.sico.modelo.ui.main;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sico.modelo.R;
import com.sico.modelo.ui.main.pojo.Contacto;
import java.util.ArrayList;

//https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
public class ContactoAdaptador extends ArrayAdapter<Contacto> {
    private LayoutInflater mInflater;

    public ContactoAdaptador(Context context, ArrayList<Contacto> users) {
        super(context, 0, users);
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Contacto unContacto = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.mi_lista_contactos, parent, false);
        }

        ImageView tvFoto = (ImageView)  view.findViewById(R.id.imagen_view);
        TextView tvNom = (TextView) view.findViewById(R.id.nombre_view);
        TextView tvTel = (TextView) view.findViewById(R.id.numero_view);

        if (unContacto.getFoto() != null)
            tvFoto.setImageURI(Uri.parse(unContacto.getFoto()));
        tvNom.setText(unContacto.getNombre());
        tvTel.setText(unContacto.getTelefono());
         return view;
    }
}

/*
public class ContactoAdaptador extends ArrayAdapter<Contacto> {

    private static class ViewHolder {
        private TextView itemView;
    }

    public ContactoAdaptador(Context context, int textViewResourceId, ArrayList<Contacto> items) {
        super(context, textViewResourceId, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.mi_lista_contactos, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.itemView = (TextView) convertView.findViewById(R.id.ItemView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Contacto item = getItem(position);
        if (item!= null) {
            // My layout has only one TextView
            // do whatever you want with your string and long
            viewHolder.itemView.setText(String.format("%s %d", item.reason, item.long_val));
        }

        return convertView;
    }
}
*/

/*
public class ContactoAdaptador extends ArrayAdapter<ContactoAdaptador.ContactoViewHolder> {
    ArrayList<Contacto> contactos = new ArrayList<>();

    public ContactoAdaptador( ArrayList<Contacto> contactos){
        this.contactos = contactos;
    }
    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mi_lista_contactos, parent, false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        Contacto unContacto = contactos.get(position);
    //    ByteBuffer algo = ByteBuffer.wrap(unContacto.getFoto());
     //   holder.ivFoto.setImageResource(algo.getInt());
        holder.ivFoto.setImageURI(Uri.parse(unContacto.getFoto()));
        //android.graphics.BitmapFactory();
      //  holder.ivFoto.set
        holder.tvNombre.setText(unContacto.getNombre());
        holder.tvNumero.setText(unContacto.getTelefono());
    }

    @Override
    public int getItemCount() {
        return contactos.size();
    }

    public static class ContactoViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivFoto;
        private TextView  tvNombre;
        private TextView  tvNumero;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFoto = (ImageView) itemView.findViewById(R.id.imagen_view);
            tvNombre = (TextView) itemView.findViewById(R.id.nombre_view);
            tvNumero = (TextView) itemView.findViewById(R.id.numero_view);
        }
    }
}

 */
