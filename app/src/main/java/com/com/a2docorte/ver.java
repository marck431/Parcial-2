package com.com.a2docorte;

import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ListView;

public class ver extends AppCompatActivity {
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);
        lista = (ListView) findViewById(R.id.ver);
        final DB A = new DB(getApplicationContext());
        Cursor registros = A.allRegistro();
        adapterA a = new adapterA(this, registros, 0);
        lista.setAdapter(a);
        a.notifyDataSetChanged();
    }

}
