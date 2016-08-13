package com.cadnunsdev.myfilms.services.db;

import com.cadnunsdev.myfilms.models.api.FilmeDetalhado;
import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by Tiago Silva on 13/08/2016.
 */
public class OrmService {
    public static void saveFilm(FilmeDetalhado detalhes) {
        if (detalhes != null)
            SugarRecord.save(detalhes);
    }


    public static FilmeDetalhado getFilmByImdbId(String imdbId) {
        List<FilmeDetalhado> query = SugarRecord.find(FilmeDetalhado.class, "imdb_Id = ?", imdbId);
        return query.size() > 0 ? query.get(0) : null;
    }

    public static List<FilmeDetalhado> listarSalvos() {
        return SugarRecord.listAll(FilmeDetalhado.class);
    }
}
