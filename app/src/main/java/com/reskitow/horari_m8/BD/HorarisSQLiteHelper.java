package com.reskitow.horari_m8.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HorarisSQLiteHelper extends SQLiteOpenHelper {

    private String[] crearTablas = {"CREATE TABLE HORARIS (ID_HORARI INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "GRUP )",
            "",
            "",
            ""};

    public HorarisSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        for (String sentencia : crearTablas) {
            sqLiteDatabase.execSQL(sentencia);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int versionAnterior, int versionNueva) {

    }
}
