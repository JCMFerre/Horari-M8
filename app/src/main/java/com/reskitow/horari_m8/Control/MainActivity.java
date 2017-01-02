package com.reskitow.horari_m8.Control;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.reskitow.horari_m8.Adaptadores.AdapterRV;
import com.reskitow.horari_m8.BD.HorarisSQLiteHelper;
import com.reskitow.horari_m8.Model.Horari;
import com.reskitow.horari_m8.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewv;
    private AdapterRV adapterRV;
    private Spinner spDias;
    private Listener listener;
    private HorarisSQLiteHelper gestorBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        comprobarPreferencias();
        iniciarAttr();
        findViews();
        configSpinner();
        configRecyclerView();
        /* Calendar cal = Calendar.getInstance(new Locale("es", "ES"));
        cal.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
        Log.i("PRIMER DIA DE LA SEMANA", "" + cal.getFirstDayOfWeek());
        //cal.setFirstDayOfWeek(Calendar.MONDAY);
        //Log.i("PRIMER DIA CAMBIADO", "" + cal.getFirstDayOfWeek());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Log.i("HORA CONVERTIDA: ", sdf.format(cal.getTime()) + ", Dia de la semana: " + (cal.get(Calendar.DAY_OF_WEEK) - cal.getFirstDayOfWeek()));
        */
    }

    private void comprobarPreferencias() {
        String grup = PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.valor_grupo), null);
        if (grup == null) {
            lanzarActivityPrefs();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_op1_preferencias:
                lanzarActivityPrefs();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void lanzarActivityPrefs() {
        startActivity(new Intent(this, PreferenciasActivity.class));
    }

    private void configRecyclerView() {
        recyclerViewv.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewv.setHasFixedSize(true);
        recyclerViewv.setAdapter(adapterRV);
    }

    public void actualizarEstado(ArrayList<Horari> horario) {
        adapterRV.setHoraris(horario);
    }

    private void iniciarAttr() {
        listener = new Listener(this);
        gestorBD = new HorarisSQLiteHelper(this, HorarisSQLiteHelper.NOMBRE_BD, null, 1);
        adapterRV = new AdapterRV();
    }

    private void configSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_dias,
                android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDias.setAdapter(adapter);
        spDias.setOnItemSelectedListener(listener);
    }

    private void findViews() {
        spDias = (Spinner) findViewById(R.id.sp_dias);
        recyclerViewv = (RecyclerView) findViewById(R.id.vistaReciclada);
    }

    public HorarisSQLiteHelper getGestorBD() {
        return gestorBD;
    }
}
