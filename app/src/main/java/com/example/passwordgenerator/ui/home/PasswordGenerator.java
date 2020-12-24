package com.example.passwordgenerator.ui.home;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PasswordGenerator {
    private final Random random;

    public PasswordGenerator() {
        random = new Random();
    }

    public String generatePassword(PasswordGenerationConfig config) {
        StringBuilder generatedPassword = new StringBuilder();

        for(int i = 0; i < config.getNumOfChars(); i++) {
            List<Character> possibleCharacter = new ArrayList<>();

            if (config.isNumbers()) {
                possibleCharacter.add(getRandomNumber());
            }

            if (config.isLowerCaseLetters()) {
                possibleCharacter.add(getRandomLowercaseLetter());
            }

            if (config.isUpperCaseLetters()) {
                possibleCharacter.add(getRandomUppercaseLetter());
            }

            if (config.isSpecialCharacters()) {
                possibleCharacter.add(getRandomSpecialChar());
            }

            if (possibleCharacter.size() <= 0) {
                return "";
            }
            generatedPassword.append(getRandomElement(possibleCharacter));
        }

        return generatedPassword.toString();
    }

    private char getRandomUppercaseLetter() {
        return (char)(random.nextInt(26) + 65);
    }

    private char getRandomLowercaseLetter() {
        return (char)(random.nextInt(26) + 97);
    }

    private char getRandomNumber() {
        return (char)(random.nextInt(10) + 48);
    }

    private char getRandomSpecialChar() {
        final List<Character> specialCharacters = Arrays.asList('$', '#', '&', '!', '%', '*');
        return getRandomElement(specialCharacters);
    }

    private Character getRandomElement(List<Character> list) {
        if (list.size() <= 0) {
            throw new IllegalArgumentException("The list must contain at least one element");
        }
        return list.get(random.nextInt(list.size()));
    }
}
