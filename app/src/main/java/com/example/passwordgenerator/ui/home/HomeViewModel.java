package com.example.passwordgenerator.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;

public class HomeViewModel extends ViewModel {

    private final PasswordGenerator generator;

    private final MutableLiveData<String> password;

    public HomeViewModel() {
        password = new MutableLiveData<>();
        generator = new PasswordGenerator();
    }

    public void generatePassword(PasswordGenerationConfig config) {
        password.setValue(generator.generatePassword(config));
    }

    public LiveData<String> getPassword() {
        return password;
    }
}