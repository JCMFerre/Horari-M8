package com.reskitow.horari_m8.Control;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.reskitow.horari_m8.Model.Horari;
import com.reskitow.horari_m8.R;

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
        ArrayList<Horari> horaris = new ArrayList<>();
        if (position != 0) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mainActivity);
            String grup = sharedPreferences.getString(mainActivity.getString(R.string.valor_grupo), "A1");
            horaris = mainActivity.getGestorBD().getHorariPorDia(position, grup);
            mainActivity.getGestorBD().close();
        }
        mostrarInfo(position, horaris);
        mainActivity.actualizarEstado(horaris);
    }

    private void mostrarInfo(int position, ArrayList<Horari> horaris) {
        final boolean comprobacion = (position == 0 || horaris.size() == 0);
        if (position == 0) {
            ((TextView) mainActivity.findViewById(R.id.tv_info)).setText(mainActivity.getString(R.string.selecciona_opcion));
        } else if (horaris.size() == 0) {
            ((TextView) mainActivity.findViewById(R.id.tv_info)).setText(mainActivity.getString(R.string.no_resultados));
        }
        mainActivity.findViewById(R.id.tv_info).setVisibility((comprobacion ? View.VISIBLE : View.GONE));
        mainActivity.findViewById(R.id.vistaReciclada).setVisibility((comprobacion ? View.GONE : View.VISIBLE));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.i("NADA SELECCIONADO", "No hay nada seleccionado.");
    }
}
