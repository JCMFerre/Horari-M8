package com.reskitow.horari_m8.Control;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.reskitow.horari_m8.Adaptadores.AdapterRV;
import com.reskitow.horari_m8.BD.HorarisSQLiteHelper;
import com.reskitow.horari_m8.Model.Horari;
import com.reskitow.horari_m8.R;

import java.util.ArrayList;
import java.util.Calendar;
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
        iniciarAttr();
        findViews();
        configSpinner();
        configRecyclerView();
    }

    private void configRecyclerView() {
        recyclerViewv.setLayoutManager(new LinearLayoutManager(this));
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
