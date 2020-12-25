package com.example.passwordgenerator.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesHandler {

    public void save(String key, List<Character> characters, Context context) {
        SharedPreferences settings = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, listToString(characters));
        editor.apply();
    }

    public List<Character> readCharacterList(String key, Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        String out = sharedPref.getString(key, "");

        return stringToList(out);
    }

    private List<Character> stringToList(String string) {
        List<Character> characters = new ArrayList<>();

        for (int i = 0; i < string.length(); i++) {
            characters.add(string.charAt(i));
        }

        return characters;
    }

    private String listToString(List<Character> list) {
        StringBuilder builder = new StringBuilder();
        list.forEach(builder::append);

        return builder.toString();
    }
}
