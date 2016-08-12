package com.cadnunsdev.myfilms.infra.ui;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Tiago Silva on 11/08/2016.
 */
public class UIUtils {
    public static <E> ArrayAdapter<E> SimpleAdpater(Context ctx, List<E> lista){
        return new ArrayAdapter<E>(ctx,
                android.R.layout.simple_list_item_1,
                lista);
    }

    public static void notificar(View view, String msg){
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public static void hideKeyBoard(Context ctx, View view){
        InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
