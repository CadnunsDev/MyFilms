package com.cadnunsdev.myfilms;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cadnunsdev.myfilms.infra.ui.UIUtils;
import com.cadnunsdev.myfilms.models.FilmeProcurado;
import com.cadnunsdev.myfilms.models.api.FilmeDetalhado;
import com.cadnunsdev.myfilms.models.api.OmdbSearchResponse;
import com.cadnunsdev.myfilms.services.api.OmdbService;
import com.cadnunsdev.myfilms.services.db.OrmService;
import com.cadnunsdev.myfilms.services.web.WebImageService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView _listaBuscas;
    private EditText _edtBuscar;
    private ArrayList<FilmeProcurado> _itensLista;
    private ArrayAdapter<FilmeProcurado> _adapterLista;
    private LinearLayout _savedItensLayout;
    private View _detalhesFilmeBusca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final Activity ctx = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.setVisibility(View.GONE);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        _listaBuscas = (ListView)findViewById(R.id.listaBuscas);
        _itensLista = new ArrayList<FilmeProcurado>();
        _adapterLista = UIUtils.SimpleAdpater(this,_itensLista);
        _listaBuscas.setAdapter(_adapterLista);
        _edtBuscar = (EditText)findViewById(R.id.edtBuscar);
        _savedItensLayout = (LinearLayout)findViewById(R.id.filmeSalvosListLayout);
        _detalhesFilmeBusca = findViewById(R.id.detalhesFilmeBusca);
        SetViewDetalhes();

        _edtBuscar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    UIUtils.hideKeyBoard(ctx, v);
                    RequestSearch(v.getText().toString());
                    return true;
                }
                return false;
            }
        });


    }

    private void SetViewDetalhes() {

        _detalhesFilmeBusca.setVisibility(View.GONE);

        _listaBuscas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FilmeProcurado filme = _itensLista.get(i);
                new DetalhesFilmesViewHelper(filme, _detalhesFilmeBusca);
            }
        });
    }

    private void RequestSearch(String keyWords) {
        final Context ctx = this;

        OmdbService.Search(keyWords, new Callback<OmdbSearchResponse>() {
            @Override
            public void onResponse(Call<OmdbSearchResponse> call, Response<OmdbSearchResponse> response) {
                OmdbSearchResponse data = response.body();
                _itensLista.clear();
                if (data.totalResults == 0){
                    UIUtils.notificar(_listaBuscas,"Nada foi encontrado");
                }
                else {
                    _itensLista.addAll(data.Search);
                    _adapterLista.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<OmdbSearchResponse> call, Throwable t) {
                UIUtils.notificar(_listaBuscas,t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_search_web) {
            ShowSearchElements();
        } else if (id == R.id.nav_search_saved) {
            ShowSavedItens();
        }
//         else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void ShowSavedItens() {
        UIUtils.hideKeyBoard(this, _edtBuscar);
        _edtBuscar.setVisibility(View.GONE);
        _listaBuscas.setVisibility(View.GONE);
        _savedItensLayout.setVisibility(View.VISIBLE);
        _detalhesFilmeBusca.setVisibility(View.GONE);
        ListView listaItensSalvos = (ListView)_savedItensLayout.findViewById(R.id.filmesSalvosListView);
        ListaFilmesSalvosViewHelper.configListView(this, listaItensSalvos);
    }

    private void ShowSearchElements() {
        _edtBuscar.setVisibility(View.VISIBLE);
        _listaBuscas.setVisibility(View.VISIBLE);
        _savedItensLayout.setVisibility(View.GONE);
        _detalhesFilmeBusca.setVisibility(View.GONE);
        _edtBuscar.setText(null);
        _itensLista.clear();
        _adapterLista.notifyDataSetChanged();
    }
}
