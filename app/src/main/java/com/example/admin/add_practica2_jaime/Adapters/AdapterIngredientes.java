package com.example.admin.add_practica2_jaime.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.add_practica2_jaime.Contratos.Contrato;
import com.example.admin.add_practica2_jaime.R;

/**
 * Created by 2dam on 24/11/2015.
 */
public class AdapterIngredientes extends CursorAdapter {
    public AdapterIngredientes(Context context, Cursor c) {
        super(context, c,true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater i = LayoutInflater.from(parent.getContext());
        View v = i.inflate(R.layout.item_ingrediente, parent, false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv = (TextView)view.findViewById(R.id.textView9);
        tv.setText(cursor.getString(cursor.getColumnIndex(Contrato.TablaIngredientes.NOMBRE)));
    }
}
