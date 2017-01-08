package com.reskitow.horari_m8.Control;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.reskitow.horari_m8.R;

public class PreferenciasFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias);
    }
}
