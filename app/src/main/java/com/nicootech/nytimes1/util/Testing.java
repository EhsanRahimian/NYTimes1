package com.nicootech.nytimes1.util;

import android.util.Log;

import com.nicootech.nytimes1.model.Docs;

import java.util.List;

public class Testing {

    private static final String TAG = "Testing";
    public static void printRecipes(List<Docs> list, String tag){
        for(Docs doc: list){
            Log.d(tag, "printRecipes: "+ doc.getSnippet());
        }
    }
}
