package com.nandoerni.passwordgenerator.ui.home;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.nandoerni.passwordgenerator.R;
import com.nandoerni.passwordgenerator.util.PasswordGenerationConfig;
import com.nandoerni.passwordgenerator.util.PasswordGenerator;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class HomeFragment extends Fragment {

    public static final int MIN_PASSWORD_LENGTH = 4;

    private HomeViewModel homeViewModel;

    private View homeView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeView = inflater.inflate(R.layout.fragment_home, container, false);

        initUI();

        if (homeViewModel.getSpecialCharacters(getContext()).isEmpty()) {
            homeViewModel.setSpecialCharacters(PasswordGenerator.DEFAULT_SPECIAL_CHARACTERS, getContext());
        }

        return homeView;
    }

    private void initUI() {
        final TextView textView = homeView.findViewById(R.id.password);
        homeViewModel.getPassword().observe(getViewLifecycleOwner(), textView::setText);

        final MaterialButton button = homeView.findViewById(R.id.generate_pw);
        button.setOnClickListener(v -> generatePassword());

        final TextView seekBarLabel = homeView.findViewById(R.id.label_number_of_characters);
        final SeekBar seekBar = homeView.findViewById(R.id.number_of_characters);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarLabel.setText(String.valueOf(progress + MIN_PASSWORD_LENGTH));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        final FloatingActionButton fab = homeView.findViewById(R.id.copy_password);
        fab.setOnClickListener(v -> copyPasswordToClipboard());
    }

    private void generatePassword() {
        homeViewModel.updateSpecialCharacters(getContext());
        homeViewModel.generatePassword(getPasswordConfig());
    }

    private PasswordGenerationConfig getPasswordConfig() {
        SeekBar numberOfCharacters = homeView.findViewById(R.id.number_of_characters);
        SwitchMaterial includeNumbers = homeView.findViewById(R.id.include_numbers);
        SwitchMaterial includeLowercase = homeView.findViewById(R.id.include_lowercase);
        SwitchMaterial includeUppercase = homeView.findViewById(R.id.include_uppercase);
        SwitchMaterial includeSpecial = homeView.findViewById(R.id.include_special);

        return new PasswordGenerationConfig(numberOfCharacters.getProgress() + MIN_PASSWORD_LENGTH,
                includeNumbers.isChecked(),
                includeLowercase.isChecked(),
                includeUppercase.isChecked(),
                includeSpecial.isChecked());
    }

    public void copyPasswordToClipboard() {
        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Password", homeViewModel.getPassword().getValue());
        clipboard.setPrimaryClip(clip);
        print(getResources().getString(R.string.copyPassword));
    }

    private void print(String message) {
        Toast.makeText(homeView.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}