package com.example.admin.add_practica2_jaime.Adapters;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.add_practica2_jaime.Contratos.Contrato;
import com.example.admin.add_practica2_jaime.POJO.Receta;
import com.example.admin.add_practica2_jaime.R;

import java.io.File;

/**
 * Created by Admin on 23/11/2015.
 */
public class AdapterRecetas extends CursorAdapter {

    public AdapterRecetas (Context co, Cursor cu) {
        super(co, cu, true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater i = LayoutInflater.from(parent.getContext());
        View v = i.inflate(R.layout.item, parent, false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv = (TextView)view.findViewById(R.id.textView);
        ImageView iv= (ImageView) view.findViewById(R.id.imageView);
        if(cursor.getString(cursor.getColumnIndex(Contrato.TablaReceta.FOTO))!=null){
            File photo=new File(cursor.getString(cursor.getColumnIndex(Contrato.TablaReceta.FOTO)));
            Uri uri=Uri.parse(photo.toString());
            iv.setImageURI(uri);
        }else {
            iv.setBackgroundResource(R.drawable.pordefecto);
        }
        tv.setText(cursor.getString(cursor.getColumnIndex(Contrato.TablaReceta.NOMBRE)));
    }
}
