package com.sico.modelo.ui.main;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.sico.modelo.R;
import com.sico.modelo.ui.main.pojo.Contacto;

import java.util.ArrayList;

public class MensajeFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final int CODIGO_SOLICITUD = 0;
    private static final ArrayList<String> NOMBRES = new ArrayList<>();
    private ArrayList<Contacto> misContactos = new ArrayList<>();
    private int mColumnCount = 1;

    private PageViewModel pageViewModel;

    public static MensajeFragment newInstance(int index) {
        MensajeFragment fragment = new MensajeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
  /*      pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
         if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }

        pageViewModel.setIndex(index);

   */
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_SECTION_NUMBER);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mensaje, container, false);
        mostrarContactos(getActivity());
        // https://www.youtube.com/watch?v=JB3ETK5mh3c
        AutoCompleteTextView destino = root.findViewById(R.id.destino);
   //    ArrayAdapter<String> agenda = new ArrayAdapter<String>(this.getActivity(), R.layout.mi_lista_contactos, R.id.nombre_view ,NOMBRES);
       //ArrayAdapter<Contacto> agenda = new ArrayAdapter<Contacto>(this.getActivity(), R.layout.mi_lista_contactos, misContactos);
        LinearLayoutManager lm = new LinearLayoutManager(this.getActivity());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        ContactoAdaptador ca = new ContactoAdaptador(this.getActivity(), misContactos);

        destino.setAdapter(ca);

      //  destino.setThreshold(1);


        final Button btn = root.findViewById(R.id.btn_enviar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = destino.getText().toString();
                Toast.makeText(getContext(), "Enviando....", Toast.LENGTH_SHORT).show();
            }
        });

/*
        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
 */
        return root;
    }

    public void mostrarContactos(FragmentActivity v){
        if (chequearPermiso()){
            consultarContentProvider();
     //       Toast.makeText(getActivity(),"premiso Verdero", Toast.LENGTH_SHORT).show();
        }else{
            solicitarPermiso();
       //     Toast.makeText(getActivity(),"premiso FALSO", Toast.LENGTH_SHORT).show();
        }
    }

    public void solicitarPermiso(){
        Boolean solicitarPermisoR = ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_CONTACTS);
        Boolean solicitarPermisoW = ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_CONTACTS);
        if (solicitarPermisoR && solicitarPermisoW){
            Toast.makeText(getActivity(),"Permisos ortogados",Toast.LENGTH_SHORT).show();
        }else{
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS}, CODIGO_SOLICITUD);
        }
    }

    public Boolean chequearPermiso(){
        Boolean verPermisoR = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED;
        Boolean verPermisoW = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_CONTACTS) == PackageManager.PERMISSION_GRANTED;
        if (verPermisoR && verPermisoW) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case CODIGO_SOLICITUD: if (chequearPermiso()){
                Toast.makeText(getActivity(),"Permisos activados",Toast.LENGTH_SHORT);
                consultarContentProvider();
            }else{
                Toast.makeText(getActivity(),"Permisos No activados",Toast.LENGTH_SHORT);
            }
        }
    }

    public void consultarContentProvider(){
        //https://www.youtube.com/watch?v=Vm32KVjdoSo
        Uri contactosURI = ContactsContract.Data.CONTENT_URI;
        String [] contactos = {
                ContactsContract.Data._ID,
                ContactsContract.Data.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.Contacts.PHOTO_URI
             //   ContactsContract.CommonDataKinds.Photo.PHOTO
        };
        String condicion =  ContactsContract.Data.MIMETYPE + " = '" +  ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE +
                "' AND " + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";
        ContentResolver cr = getActivity().getContentResolver();
        // = getContentResolver();

        Cursor registros =  cr.query(contactosURI,contactos, condicion,null, ContactsContract.Data.DISPLAY_NAME + " ASC");

        while (registros.moveToNext()){
            String id  = registros.getString(registros.getColumnIndex(contactos[0]));
            String nom = registros.getString(registros.getColumnIndex(contactos[1]));
            String nro = registros.getString(registros.getColumnIndex(contactos[2]));
            String foto = registros.getString(registros.getColumnIndex(contactos[3]));

            //      int foto = Integer.parseInt(registros.getBlob(registros.getColumnIndex(contactos[3])).toString());
//https://es.stackoverflow.com/questions/138401/usar-letras-de-material-design-como-vectores-en-aplicaci%C3%B3n-con-android-studio
            NOMBRES.add(nom);
            misContactos.add(new Contacto(id,nom,nro,foto));
            Log.d("Contactos: ",nom + " " +nro);
        }
    }
}