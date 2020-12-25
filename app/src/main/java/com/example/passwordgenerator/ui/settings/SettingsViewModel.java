package com.example.passwordgenerator.ui.settings;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.passwordgenerator.util.PasswordGenerator;
import com.example.passwordgenerator.util.SharedPreferencesHandler;

public class SettingsViewModel extends ViewModel {
    public void save(String specialCharacters, Context context) {
        SharedPreferencesHandler.save(PasswordGenerator.SPECIAL_CHARACTER_KEY, specialCharacters, context);
    }

    public String getSpecialCharacters(Context context) {
        return SharedPreferencesHandler.readString(PasswordGenerator.SPECIAL_CHARACTER_KEY, context);
    }
}