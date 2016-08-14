package com.cadnunsdev.myfilms;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.cadnunsdev.myfilms.infra.ui.UIUtils;
import com.cadnunsdev.myfilms.models.api.FilmeDetalhado;
import com.cadnunsdev.myfilms.services.db.OrmService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tiago Silva on 13/08/2016.
 */
public class ListaFilmesSalvosViewHelper {
    private final ListView _listView;
    private final ArrayList<FilmeDetalhado> _itensLista;
    private final ArrayAdapter<FilmeDetalhado> _adapterLista;
    private final Activity _ctx;

    private ListaFilmesSalvosViewHelper(Activity ctx, ListView listView){
        _ctx = ctx;
        _listView = listView;
        _itensLista = new ArrayList<FilmeDetalhado>();
        _adapterLista = UIUtils.SimpleAdpater(ctx,_itensLista);
        _listView.setAdapter(_adapterLista);
        _listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FilmeDetalhado filme = _itensLista.get(i);
                ShowModal(filme);
            }
        });
        CarregarLista();
    }

    private void ShowModal(final FilmeDetalhado filme) {
        final Dialog modal = FilmeSalvoDialog.create(_ctx,filme);
        Button removeBtn =  (Button)modal.findViewById(R.id.btnRemover);
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrmService.delete(filme);
                CarregarLista();
                modal.dismiss();
            }
        });
    }

    private void CarregarLista() {
        _itensLista.clear();
        _itensLista.addAll(OrmService.listarSalvos());
        _adapterLista.notifyDataSetChanged();
    }

    public static void configListView(Activity activity, ListView listView) {
        new ListaFilmesSalvosViewHelper(activity, listView);
    }
}
