package com.example.passwordgenerator.ui.home;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.passwordgenerator.util.PasswordGenerationConfig;
import com.example.passwordgenerator.util.PasswordGenerator;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private final PasswordGenerator generator;

    private final MutableLiveData<String> password;

    public HomeViewModel() {
        password = new MutableLiveData<>();
        generator = new PasswordGenerator();
    }

    public void setSpecialCharacters(List<Character> specialCharacters, Context context) {
        generator.saveSpecialCharacter(specialCharacters, context);
    }

    public List<Character> getSpecialCharacters(Context context) {
        return generator.readSpecialCharacter(context);
    }

    public void updateSpecialCharacters(Context context) {
        setSpecialCharacters(getSpecialCharacters(context), context);
    }

    public void generatePassword(PasswordGenerationConfig config) {
        password.setValue(generator.generatePassword(config));
    }

    public LiveData<String> getPassword() {
        return password;
    }
}