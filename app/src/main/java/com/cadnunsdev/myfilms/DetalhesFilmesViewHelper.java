package com.cadnunsdev.myfilms;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cadnunsdev.myfilms.infra.ui.UIUtils;
import com.cadnunsdev.myfilms.models.FilmeProcurado;
import com.cadnunsdev.myfilms.models.api.FilmeDetalhado;
import com.cadnunsdev.myfilms.services.api.OmdbService;
import com.cadnunsdev.myfilms.services.web.WebImageService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tiago Silva on 11/08/2016.
 */
public class DetalhesFilmesViewHelper {
    private final View _view;
    private final TextView _edtTitle;
    private final Button _btnFecharDetalhes;
    private final ImageView _imageView;
    private FilmeDetalhado _detalhes;

    public DetalhesFilmesViewHelper(FilmeProcurado filmeProcurado, View detalhesFilmeBusca){
        _view = detalhesFilmeBusca;
        _edtTitle = (TextView)_view.findViewById(R.id.tvTituloFilme);
        _edtTitle.setText(filmeProcurado.getTitle());
        _btnFecharDetalhes = (Button)_view.findViewById(R.id.btnFechar);
        //
         _imageView = (ImageView)_view.findViewById(R.id.imgViewPoster);

        _btnFecharDetalhes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _view.setVisibility(View.GONE);
            }
        });
        OmdbService.GetDetalhes(filmeProcurado.getImdbId(), new Callback<FilmeDetalhado>() {
            @Override
            public void onResponse(Call<FilmeDetalhado> call, Response<FilmeDetalhado> response) {
                _detalhes = response.body();

                if(response.code() == 200 && _detalhes.getResponse()){
                    _view.setVisibility(View.VISIBLE);
                    _edtTitle.setText(_detalhes.getTitle());
                    WebImageService.SetImage(_imageView,_detalhes.getPoster());
                }else {
                    UIUtils.notificar(_view,"Cod. Http: "+response.code());
                }
            }

            @Override
            public void onFailure(Call<FilmeDetalhado> call, Throwable t) {
                UIUtils.notificar(_view,t.getMessage());
            }
        });


    }
}
