package com.reskitow.horari_m8.Control;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.reskitow.horari_m8.Model.Horari;

import java.util.ArrayList;

/**
 * Created by RW-PC on 27/12/2016.
 */

public class Listener implements AdapterView.OnItemSelectedListener {

    private MainActivity mainActivity;

    public Listener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ArrayList<Horari> horaris = null;
        if (position != 0) {
            horaris = mainActivity.getGestorBD().getHorariPorDia(position);
        }
        mainActivity.actualizarEstado(horaris);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
