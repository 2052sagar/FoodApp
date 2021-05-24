package com.edumbrella.foodapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.edumbrella.foodapp.model.Popular;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Sagar Pandey on 5/23/2021.
 */
public class PrefConfig {

    public static void writeCartItems(Context context, List<Popular> cartDatas){

        Gson gson = new Gson();

        String jsonString = gson.toJson(cartDatas);

        SharedPreferences sharedpreferences = context.getSharedPreferences("cartItems", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("cartItems", jsonString);

        editor.commit();

    }

    public static void writeCount(Context context, int count){

        SharedPreferences sharedpreferences = context.getSharedPreferences("cartItems", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putInt("count", count);

        editor.commit();

    }

    public static List<Popular> readCartItems(Context context){
        SharedPreferences sharedpreferences = context.getSharedPreferences("cartItems", MODE_PRIVATE);
        String jsonString = sharedpreferences.getString("cartItems", "");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Popular>>(){}.getType();
        List<Popular> cartItemsList = gson.fromJson(jsonString, type);
        return  cartItemsList;

    }

    public static int readCount(Context context){
        SharedPreferences sharedpreferences = context.getSharedPreferences("cartItems", MODE_PRIVATE);
        int count = sharedpreferences.getInt("count", 0);


        return  count;

    }


}
