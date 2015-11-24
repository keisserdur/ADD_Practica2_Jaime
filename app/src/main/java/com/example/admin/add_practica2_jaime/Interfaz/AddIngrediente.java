package com.example.admin.add_practica2_jaime.Interfaz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.admin.add_practica2_jaime.R;

public class AddIngrediente extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingrediente2);
    }
    public void añadir(View v){
        EditText et= (EditText) findViewById(R.id.editText4);
        if(et.getText()!=null){
            Intent i=new Intent();
            Bundle b=new Bundle();
            b.putString("nuevoIngrediente",et.getText().toString());
            i.putExtras(b);
            setResult(Activity.RESULT_OK, i);
        }
        finish();
    }
    public void cancel(View v){
        finish();
    }
}
