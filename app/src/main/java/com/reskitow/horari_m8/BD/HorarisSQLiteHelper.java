package com.reskitow.horari_m8.BD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.reskitow.horari_m8.Model.Horari;

import java.util.ArrayList;

public class HorarisSQLiteHelper extends SQLiteOpenHelper {

    private String[] sentencias = {"CREATE TABLE HORARIS (ID_HORARI INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "GRUP TEXT NOT NULL, CODI_MODUL TEXT NOT NULL, HORA_INICI TEXT NOT NULL, HORA_FI TEXT NOT NULL, " +
            "DIA_SETMANA INTEGER NOT NULL)",
            "INSERT INTO HORARIS VALUES (null, 'A1', 'M7', '15:00:00', '17:59:59', 1)," +
                    "(null, 'A2', 'M7', '15:00:00', '17:59:59', 1)," +
                    "(null, 'A1', 'TUTORIA', '18:20:00', '19:19:19', 1)," +
                    "(null, 'A2', 'TUTORIA', '18:20:00', '19:19:19', 1)," +
                    "(null, 'A2', 'M3', '19:20:00', '21:19:19', 1)"};

    public HorarisSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        for (String sentencia : sentencias) {
            sqLiteDatabase.execSQL(sentencia);
        }
    }

    public ArrayList<Horari> getHorariPorDia(int dia) {
        ArrayList<Horari> horaris = new ArrayList<>();
        Cursor c = getReadableDatabase().rawQuery("SELECT * FROM HORARIS WHERE DIA_SETMANA = ?", new String[]{"" + dia});
        if (c.moveToFirst()) {
            do {
                horaris.add(new Horari(c.getInt(0), c.getString(1), c.getString(2)
                        , c.getString(3), c.getString(4), c.getInt(5)));
            } while (c.moveToNext());
        }
        return horaris;
    }

    public Horari getHorariPerHora() {
        Horari h = null;
        SQLiteDatabase sqlite = getReadableDatabase();
        return h;
    }

    public void closeDB() {
        close();
    }

    public ArrayList<Horari> getAllHoraris() {
        ArrayList<Horari> horaris = new ArrayList<>();
        Cursor c = getReadableDatabase().rawQuery("SELECT * FROM HORARIS", null);
        if (c.moveToFirst()) {
            do {
                horaris.add(new Horari(c.getInt(0), c.getString(1), c.getString(2)
                        , c.getString(3), c.getString(4), c.getInt(5)));
            } while (c.moveToNext());
        }
        return horaris;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int versionAnterior, int versionNueva) {

    }
}
