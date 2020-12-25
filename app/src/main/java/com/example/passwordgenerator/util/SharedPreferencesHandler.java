package com.example.passwordgenerator.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public final class SharedPreferencesHandler {

    public static void save(String key, List<Character> characters, Context context) {
        save(key, listToString(characters), context);
    }

    public static void save(String key, String data, Context context) {
        SharedPreferences settings = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, data);
        editor.apply();
    }

    public static List<Character> readCharacterList(String key, Context context) {
        return stringToList(readString(key, context));
    }

    public static String readString(String key, Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        return sharedPref.getString(key, "");
    }

    private static List<Character> stringToList(String string) {
        List<Character> characters = new ArrayList<>();

        for (int i = 0; i < string.length(); i++) {
            characters.add(string.charAt(i));
        }

        return characters;
    }

    private static String listToString(List<Character> list) {
        StringBuilder builder = new StringBuilder();
        list.forEach(builder::append);

        return builder.toString();
    }
}
