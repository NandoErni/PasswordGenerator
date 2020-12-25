package com.example.passwordgenerator.util;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PasswordGenerator {
    private static final String SPECIAL_CHARACTER_KEY = "specialCharacterPreference";

    public static final List<Character> DEFAULT_SPECIAL_CHARACTERS = Arrays.asList('$', '#', '&', '!', '%', '*');

    private final Random random;
    private final SharedPreferencesHandler sharedPreferencesHandler;
    private List<Character> specialCharacters;

    public PasswordGenerator() {
        random = new Random();
        sharedPreferencesHandler = new SharedPreferencesHandler();
        specialCharacters = new ArrayList<>();
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
        return getRandomElement(specialCharacters);
    }

    private Character getRandomElement(List<Character> list) {
        if (list.size() <= 0) {
            throw new IllegalArgumentException("The list must have at least one element.");
        }
        return list.get(random.nextInt(list.size()));
    }

    public void saveSpecialCharacter(List<Character> specialCharacters, Context context) {
        sharedPreferencesHandler.save(SPECIAL_CHARACTER_KEY, specialCharacters, context);
        this.specialCharacters = specialCharacters;
    }

    public List<Character> readSpecialCharacter(Context context) {
        return sharedPreferencesHandler.readCharacterList(SPECIAL_CHARACTER_KEY, context);
    }
}
