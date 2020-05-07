package com.com.a2docorte;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {

    private static final String DB_Name = "DATOS";
    private static final int version  = 1;
    private static final String TABLA = "CREATE TABLE REGISTRO (CEDULA TEXT PRIMARY KEY, NOMBRE TEXT, ESTRATO TEXT, SALARIO TEXT, EDUCACION TEXT)";

    public DB (Context context) {
        super(context, DB_Name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLA);
        db.execSQL(TABLA);
    }

    public Cursor allRegistro() {
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cur = db.rawQuery("SELECT CEDULA AS _id , NOMBRE, ESTRATO, SALARIO, EDUCACION FROM REGISTRO", null);
            return cur;
        } catch (Exception ex) {
            System.out.println("Error.");
            return null;
        }
    }
    public void agregarRegistro(Registro e){
        try {
            SQLiteDatabase bd = getWritableDatabase();
            if (bd != null) {
                bd.execSQL("INSERT INTO REGISTRO VALUES('"+ e.getCedula()+"', '"+ e.getNombre()+"', '"+ e.getEstrato()+"', '"+ e.getSalario()+"', '"+ e.getEducacion()+"')");
                bd.close();
            }
        }catch (Exception ex){
            System.out.println("Error de inserción");
        }
    }
    public Cursor consulta (String x) {
        String[] campos = new String[] {"NOMBRE", "ESTRATO", "SALARIO", "EDUCACION"};
        String[] args = new String[] {x};
        SQLiteDatabase bd = getWritableDatabase();
        Cursor cur = bd.query("REGISTRO", campos, "CEDULA=?", args, null, null, null);
        return cur;
    }
    public boolean eliminar (String a){
        try{
            SQLiteDatabase db = getWritableDatabase();
            String[] args = new String[] {a};
            db.delete("REGISTRO", "CEDULA=?",args);
            db.close();
            return true;
        }
        catch (Exception ex){
            System.out.println("Error.");
            return false;
        }
    }
    public boolean buscarRegistro(String cod){
        String[] args = new String[] {cod};
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query("REGISTRO", null, "CEDULA=?", args, null, null, null);
        if (c.getCount()>0) {
            db.close();
            return true;
        }
        else{
            db.close();
            return false;}
    }
    public boolean actualizarRegistro (String UNO, String DOS, String TRES, String CUATRO, String CINCO){
        try{
            SQLiteDatabase db = getWritableDatabase();
            ContentValues valores = new ContentValues();
            String[] args = new String[] {UNO};
            valores.put("NOMBRE", DOS);
            valores.put("ESTRATO", TRES);
            valores.put("SALARIO", CUATRO);
            valores.put("EDUCACION", CINCO);
            db.update("REGISTRO", valores,"CEDULA=?",args);
            db.close();
            return true;
        }
        catch (Exception ex){
            System.out.println("Error al actualizar");
            return false;
        }

    }



}
