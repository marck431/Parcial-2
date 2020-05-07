package com.com.a2docorte;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class adapterA extends android.widget.CursorAdapter {
    public adapterA(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.dato_est, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView codigo = (TextView) view.findViewById(R.id.TXTcedula);
        TextView nombre = (TextView) view.findViewById(R.id.TXTnombre);
        TextView estrato = (TextView) view.findViewById(R.id.TXTestrato);
        TextView salario = (TextView) view.findViewById(R.id.TXTsalario);
        TextView educativo = (TextView) view.findViewById(R.id.TXTneducativo);
        // Extract properties from cursor
        String cod = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
        String nombre1 = cursor.getString(cursor.getColumnIndexOrThrow("NOMBRE"));
        String estrato1 = cursor.getString(cursor.getColumnIndexOrThrow("ESTRATO"));
        String salario1 = cursor.getString(cursor.getColumnIndexOrThrow("SALARIO"));
        String neducativo1 = cursor.getString(cursor.getColumnIndexOrThrow("EDUCACION"));

        // Populate fields with extracted properties
        codigo.setText("Cédula: " + cod);
        nombre.setText(nombre1);
        estrato.setText("Estrato: " + estrato1);
        salario.setText("Salario: " + salario1);
        educativo.setText("Nivel educación: " + neducativo1);
    }
}
