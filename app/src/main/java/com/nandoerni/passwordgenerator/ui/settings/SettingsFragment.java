package com.nandoerni.passwordgenerator.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.nandoerni.passwordgenerator.R;
import com.google.android.material.button.MaterialButton;

public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;

    private View settingsView;

    private EditText specialCharacterText;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        settingsView = inflater.inflate(R.layout.fragment_settings, container, false);

        initUI();

        return settingsView;
    }

    private void initUI() {
        MaterialButton button = settingsView.findViewById(R.id.save_special_characters);
        specialCharacterText = settingsView.findViewById(R.id.special_characters);

        specialCharacterText.setText(settingsViewModel.getSpecialCharacters(getContext()));
        button.setOnClickListener(v -> settingsViewModel.save(specialCharacterText.getText().toString(), getContext()));
    }

}