package com.reskitow.horari_m8.BD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;

import com.reskitow.horari_m8.Model.Horari;

import java.util.ArrayList;

public class HorarisSQLiteHelper extends SQLiteOpenHelper {

    public static final String NOMBRE_BD = "DB_HORARI";
    private String columnasARecuperar = "ID_HORARI, NOM_GRUP, NOM_MODUL, NOM_PROFESSOR, NOM_AULA, HORA_INICI, HORA_FI, DIA_SETMANA";
    private String tablasARecuperar = "HORARIS H, GRUPS G, MODULS M, PROFESSORS P, AULAS A";
    private String whereJoin = "H.GRUP = G.ID_GRUP AND H.MODUL = M.ID_MODUL AND H.PROFESSOR = P.ID_PROFESSOR AND H.AULA = A.ID_AULA";
    private String grup;

    public HorarisSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String[] sentencias = {"CREATE TABLE PROFESSORS (ID_PROFESSOR INTEGER PRIMARY KEY AUTOINCREMENT, NOM_PROFESSOR TEXT NOT NULL)",
                "CREATE TABLE MODULS (ID_MODUL INTEGER PRIMARY KEY AUTOINCREMENT, NOM_MODUL TEXT NOT NULL)",
                "CREATE TABLE AULAS (ID_AULA INTEGER PRIMARY KEY AUTOINCREMENT, NOM_AULA TEXT NOT NULL)",
                "CREATE TABLE GRUPS (ID_GRUP INTEGER PRIMARY KEY AUTOINCREMENT, NOM_GRUP TEXT NOT NULL)",
                "CREATE TABLE HORARIS (ID_HORARI INTEGER PRIMARY KEY AUTOINCREMENT, GRUP INTEGER, MODUL INTEGER, PROFESSOR INTEGER, " +
                        "AULA INTEGER, HORA_INICI TEXT NOT NULL, HORA_FI TEXT NOT NULL, DIA_SETMANA INTEGER NOT NULL)",
                "INSERT INTO PROFESSORS VALUES (null, 'Jorge Rubio'), (null, 'Josefa Gonzàlez'), (null, 'Jose A. Leo'), (null, 'Lluís Perpiñà'), (null, 'Marta Planas')",
                "INSERT INTO MODULS VALUES (null, 'M05 M02 M06'), (null, 'M03'), (null, 'M07'), (null, 'M08'), (null, 'M09'), (null, 'M10'), (null, 'TUT')",
                "INSERT INTO AULAS VALUES (null, 'A201'), (null, 'A208')",
                "INSERT INTO GRUPS VALUES (null, 'A1'), (null, 'A2')",
                "INSERT INTO HORARIS VALUES (null, 1, 3, 3, 1, '15:00:00', '17:59:59', 1), (null, 2, 3, 3, 1, '15:00:00', '17:59:59', 1), " +
                        "(null, 1, 7, 2, 1, '18:20:00', '19:19:59', 1), (null, 2, 7, 2, 1, '18:20:00', '19:19:59', 1), (null, 2, 2, 2, 1, '18:20:00', '19:19:59', 1)," +
                        "(null, 2, 4, 4, 1, '19:20:00', '21:19:59', 1), " +
                        "(null, 1, 2, 2, 2, '15:00:00', '16:59:59', 2), (null, 2, 4, 4, 1, '15:00:00', '16:59:59', 2), (null, 1, 6, 5, 1, '17:00:00', '17:59:59', 2)," +
                        "(null, 2, 6, 5, 1, '17:00:00', '17:59:59', 2), (null, 1, 6, 5, 1, '18:20:00', '19:19:59', 2), (null, 2, 6, 5, 1, '18:20:00', '19:19:59', 2)," +
                        "(null, 1, 1, 1, 1, '19:20:00', '21:19:59', 2), (null, 2, 1, 1, 1, '19:20:00', '21:59:59', 2)," +
                        "(null, 1, 5, 1, 2, '16:00:00', '17:59:59', 3), (null, 2, 4, 4, 1, '16:00:00', '17:59:59', 3), (null, 1, 1, 1, 1, '18:20:00', '19:19:59', 3)," +
                        "(null, 2, 1, 1, 1, '18:20:00', '19:19:59', 3), (null, 1, 2, 2, 2, '19:20:00', '21:19:59', 3), (null, 2, 5, 1, 1, '19:20:00', '20:19:59', 3)," +
                        "(null, 1, 5, 1, 2, '15:00:00', '15:59:59', 4), (null, 1, 4, 4, 2, '16:00:00', '17:59:59', 4), (null, 2, 2, 2, 1, '16:00:00', '17:59:59', 4)," +
                        "(null, 1, 1, 1, 1, '18:20:00', '21:19:59', 4), (null, 2, 1, 1, 1, '18:20:00', '21:19:59', 4), " +
                        "(null, 1, 6, 5, 1, '15:00:00', '16:59:59', 5), (null, 2, 6, 5, 1, '15:00:00', '16:59:59', 5), (null, 1, 4, 4, 2, '17:00:00', '17:59:59', 5)," +
                        "(null, 2, 5, 1, 1, '17:00:00', '17:59:59', 5), (null, 1, 4, 4, 2, '18:20:00', '19:19:59', 5), (null, 2, 5, 1, 1, '18:20:00', '19:19:59', 5)," +
                        "(null, 1, 1, 1, 1, '19:20:00', '21:19:59', 5), (null, 2, 1, 1, 1, '19:20:00', '21:19:59', 5)"};
        for (String sentencia : sentencias) {
            sqLiteDatabase.execSQL(sentencia);
        }
    }

    public ArrayList<Horari> getHorariPorDia(int dia, String grup) {
        Cursor c = this.getReadableDatabase().rawQuery("SELECT " + columnasARecuperar + " FROM " + tablasARecuperar +
                " WHERE " + whereJoin + " AND DIA_SETMANA = ? AND NOM_GRUP = ?", new String[]{String.valueOf(dia), grup});
        return getHorariPorCursor(c);
    }

    public Horari getHorariPerHora() {
        Horari h = null;
        SQLiteDatabase sqlite = getReadableDatabase();
        return h;
    }

    private ArrayList<Horari> getHorariPorCursor(Cursor c) {
        ArrayList<Horari> horaris = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                horaris.add(new Horari(c.getInt(0), c.getString(1), c.getString(2)
                        , c.getString(3), c.getString(4), c.getString(5), c.getString(6), c.getInt(7)));
            } while (c.moveToNext());
        }
        c.close();
        return horaris;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int versionAnterior, int versionNueva) {

    }
}
