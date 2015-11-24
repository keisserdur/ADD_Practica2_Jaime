package com.example.admin.add_practica2_jaime.Editar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.admin.add_practica2_jaime.R;

public class Editar extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
    }
    public void volver(View v){
        finish();
    }
}
