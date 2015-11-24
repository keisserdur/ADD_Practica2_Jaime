package com.example.admin.add_practica2_jaime.Interfaz;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.admin.add_practica2_jaime.Adapters.AdapterIngredientes;
import com.example.admin.add_practica2_jaime.Contratos.Contrato;
import com.example.admin.add_practica2_jaime.Editar.EditarIngrediente;
import com.example.admin.add_practica2_jaime.Gestores.GestorIngrediente;
import com.example.admin.add_practica2_jaime.POJO.Ingrediente;
import com.example.admin.add_practica2_jaime.R;

public class VerIngrediente extends Activity {

    private final int NUEVO=2;
    private AdapterIngredientes adp;
    private String ingredientes;
    private ListView lv;
    private GestorIngrediente gi;
    private Cursor c;
    private long id=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingrediente);
        lv= (ListView) findViewById(R.id.listView2);
        gi=new GestorIngrediente(this);
    }
    @Override
    protected void onResume() {
        gi.open();
        c = gi.query();
        adp = new AdapterIngredientes(this, c);
        lv.setAdapter(adp);
        registerForContextMenu(lv);
        super.onResume();
        ini();
    }

    @Override
    protected void onPause() {
        gi.close();
        super.onPause();
    }

    private void ini() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View v, int pos, long idClick) {
                Cursor c = (Cursor) av.getItemAtPosition(pos);
                ingredientes = c.getString(c.getColumnIndex(Contrato.TablaIngredientes.NOMBRE));
                volver();
            }
        });
    }

    public void addIngrediente(View v){
        Intent i=new Intent(this,AddIngrediente.class);
        startActivityForResult(i, NUEVO);
    }
    public void volver(){
        Intent i=new Intent();
        Bundle b=new Bundle();
        b.putString("elemento", ingredientes);
        i.putExtras(b);
        setResult(Activity.RESULT_OK, i);

        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==NUEVO){
            Bundle b=data.getExtras();
            Ingrediente i=new Ingrediente();
            i.setNombre(b.getString("nuevoIngrediente"));
            gi.open();
            gi.insert(i);
            gi.close();
            adp.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menucontextual, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int indice = info.position;
        Cursor c = (Cursor) lv.getItemAtPosition(indice);
        id = c.getLong(c.getColumnIndex(Contrato.TablaIngredientes._ID));
        switch (item.getItemId()) {
            case R.id.mneditar:
                Intent i = new Intent(this, EditarIngrediente.class);
                Bundle b = new Bundle();
                b.putLong("IdCursor", id);
                i.putExtras(b);
                startActivity(i);
                return true;
            case R.id.mnborrar:
                gi.delete(id);
                adp.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
