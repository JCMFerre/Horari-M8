package com.reskitow.horari_m8.Control;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.reskitow.horari_m8.R;

/**
 * Created by RW-PC on 02/01/2017.
 */

public class PreferenciasFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias);
    }
}
