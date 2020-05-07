package com.com.a2docorte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Registro r;
    String[] datoSp1, datoSp2;
    String op1, op2;
    EditText cedula;
    EditText nombre;
    EditText salario;
    Spinner estrato;
    Spinner educacion;
    Button guardar;
    Button listar;
    Button eliminar;
    Button buscar;
    Button actualizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DB A = new DB(getApplicationContext());
        datoSp1 = getResources().getStringArray(R.array.estrato);
        datoSp2 = getResources().getStringArray(R.array.estrato);
        estrato = (Spinner) findViewById(R.id.estrato);
        educacion = (Spinner) findViewById(R.id.educativo);
        cedula = (EditText) findViewById(R.id.cedula);
        nombre = (EditText) findViewById(R.id.nombre);
        salario = (EditText) findViewById(R.id.salario);
        guardar = (Button) findViewById(R.id.registrar);
        listar = (Button) findViewById(R.id.listar);
        eliminar = (Button) findViewById(R.id.eliminar);
        buscar = (Button) findViewById(R.id.buscar);
        actualizar = (Button) findViewById(R.id.actualizar);
        ArrayAdapter<CharSequence> estratoA = ArrayAdapter.createFromResource(this, R.array.estrato, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> educacionA = ArrayAdapter.createFromResource(this, R.array.educacion, android.R.layout.simple_spinner_item);
        estratoA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        educacionA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estrato.setAdapter(estratoA);
        educacion.setAdapter(educacionA);
        estrato.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                op1 = datoSp1[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        educacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                op2 = datoSp2[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cedula.getText().toString().equals("") || nombre.getText().toString().equals("") || salario.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Agregue texto", Toast.LENGTH_SHORT).show();
                } else {
                    if (!A.buscarRegistro(cedula.getText().toString())) {
                        r = new Registro(cedula.getText().toString(), nombre.getText().toString(), op1, salario.getText().toString(), op2);
                        A.agregarRegistro(r);
                        Toast.makeText(getApplicationContext(), "Agregado!", Toast.LENGTH_SHORT).show();
                        limpiarCampos();
                    } else {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cedula.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Escriba la cedula", Toast.LENGTH_SHORT).show();
                } else {
                    if (A.eliminar(cedula.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Eliminado", Toast.LENGTH_SHORT).show();
                        limpiarCampos();
                    } else {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            ;
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cedula.getText().toString().equals("") && nombre.getText().toString().equals("") && salario.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Escriba campos a actualizar", Toast.LENGTH_SHORT).show();
                }else{
                    if (A.buscarRegistro(cedula.getText().toString())){
                        if (A.actualizarRegistro(cedula.getText().toString(), nombre.getText().toString(), op1, salario.getText().toString(), op2)){
                            Toast.makeText(getApplicationContext(), "Actualizado", Toast.LENGTH_SHORT).show();
                            limpiarCampos();
                        };
                    }else{
                        Toast.makeText(getApplicationContext(), "No encontrado", Toast.LENGTH_SHORT).show();
                    }
                };
            };
        });
        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ver.class);
                startActivity(i);
            }
        });
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!A.buscarRegistro(cedula.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Escriba cedula o no en base de datos.", Toast.LENGTH_SHORT).show();
                }else{
                    Cursor cur = A.consulta(cedula.getText().toString());
                    cur.moveToFirst();
                    nombre.setText(cur.getString(0));
                    int x = sp1(cur.getString(1));
                    estrato.setSelection(x);
                    salario.setText(cur.getString(2));
                    int y = sp2(cur.getString(3));
                    educacion.setSelection(y);
                };
            };
        });
    };
    public void limpiarCampos () {
        cedula.setText("");
        nombre.setText("");
        salario.setText("");
        estrato.setSelection(0);
        educacion.setSelection(0);
    };
    public int sp1 (String x) {
        switch (x){
            case "1":
                return 0;
            case "2":
                return 1;
            case "3":
                return 2;
            case "4":
                return 3;
            case "5":
                return 4;
            case "6":
                return 5;
        }
        return 0;
    };
    public int sp2 (String x) {
        switch (x){
            case "Bachiller":
                return 0;
            case "Maestria":
                return 1;
            case "Pregrado":
                return 2;
            case "Doctorado":
                return 3;
        }
        return 0;
    };
};
