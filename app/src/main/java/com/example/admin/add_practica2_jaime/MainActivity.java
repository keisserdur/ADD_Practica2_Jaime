package com.example.admin.add_practica2_jaime;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.admin.add_practica2_jaime.Adapters.AdapterRecetas;
import com.example.admin.add_practica2_jaime.Contratos.Contrato;
import com.example.admin.add_practica2_jaime.Editar.Editar;
import com.example.admin.add_practica2_jaime.Gestores.GestorALL;
import com.example.admin.add_practica2_jaime.Gestores.GestorReceta;
import com.example.admin.add_practica2_jaime.Interfaz.AddReceta;
import com.example.admin.add_practica2_jaime.Interfaz.VerReceta;
import com.example.admin.add_practica2_jaime.POJO.Receta;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AdapterRecetas adp;
    private List<Receta> list;
    private ListView lv;
    private GestorReceta gr;
    private Cursor c;
    private long id=-1;
    private final int RECETA=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv= (ListView) findViewById(R.id.listView);
        gr=new GestorReceta(this);

    }
    @Override
     protected void onResume() {
        gr.open();
        Log.v("APLICACION", "Resume Principal Open");
        c = gr.query();
        adp = new AdapterRecetas(this, c);
        lv.setAdapter(adp);
        registerForContextMenu(lv);
        ini();
        super.onResume();
    }

    @Override
    protected void onPause() {
        gr.close();
        Log.v("APLICACION", "Resume Principal Close");
        super.onPause();
    }

    private void ini() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View v, int pos, long idClick) {
                Cursor c = (Cursor) lv.getItemAtPosition(pos);
                id = c.getLong(c.getColumnIndex(Contrato.TablaReceta._ID));
                irA(id);
            }
        });

    }

    /**********************************************************************/

    public void addReceta(View v){
        Intent i=new Intent(this,AddReceta.class);
        startActivityForResult(i, RECETA);
    }

    public void irA(long id){
        if(id!=-1){
            Intent i=new Intent (this, VerReceta.class);
            Bundle b=new Bundle();
            b.putLong("IdCursor",id);
            i.putExtras(b);
            startActivity(i);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==RECETA){

        }
    }
    /***********************************************************************/
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
        id = c.getLong(c.getColumnIndex(Contrato.TablaReceta._ID));
        switch (item.getItemId()) {
            case R.id.mneditar:
                Intent i = new Intent(this, Editar.class);
                Bundle b = new Bundle();
                b.putLong("IdCursor", id);
                i.putExtras(b);
                startActivity(i);
                return true;
            case R.id.mnborrar:
                GestorALL gri=new GestorALL(this);
                gri.delete(id);
                adp.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
