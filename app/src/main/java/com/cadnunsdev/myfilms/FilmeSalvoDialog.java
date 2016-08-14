package com.cadnunsdev.myfilms;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.cadnunsdev.myfilms.models.api.FilmeDetalhado;

import java.util.ArrayList;

/**
 * Created by Tiago Silva on 13/08/2016.
 */
public class FilmeSalvoDialog {
    public static Dialog create(final Activity activity, FilmeDetalhado filmeDetalhado) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("detalhes do Filme:");
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.partial_modal_filme_salvo,null);
        new DetalhesFilmesViewHelper(filmeDetalhado, dialoglayout,true, true);
        builder.setView(dialoglayout);
        Dialog modal = builder.create();
        modal.show();
        return  modal;
        }
    }
