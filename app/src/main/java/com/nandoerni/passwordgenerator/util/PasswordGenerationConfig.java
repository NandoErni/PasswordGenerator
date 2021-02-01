package com.nandoerni.passwordgenerator.util;

public class PasswordGenerationConfig {
    private final int numOfChars;

    private final boolean numbers;

    private final boolean lowerCaseLetters;

    private final boolean upperCaseLetters;

    private final boolean specialCharacters;

    public PasswordGenerationConfig(int numOfChars, boolean numbers, boolean lowerCaseLetters, boolean upperCaseLetters, boolean specialCharacters) {
        this.numOfChars = numOfChars;
        this.numbers = numbers;
        this.lowerCaseLetters = lowerCaseLetters;
        this.upperCaseLetters = upperCaseLetters;
        this.specialCharacters = specialCharacters;
    }

    public int getNumOfChars() {
        return numOfChars;
    }

    public boolean isNumbers() {
        return numbers;
    }

    public boolean isLowerCaseLetters() {
        return lowerCaseLetters;
    }

    public boolean isUpperCaseLetters() {
        return upperCaseLetters;
    }

    public boolean isSpecialCharacters() {
        return specialCharacters;
    }
}
