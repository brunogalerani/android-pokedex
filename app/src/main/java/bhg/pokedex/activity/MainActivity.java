package bhg.pokedex.activity;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import bhg.pokedex.R;
import bhg.pokedex.adapter.PokemonListAdapter;
import bhg.pokedex.model.Pokemon;
import bhg.pokedex.util.FirebaseInstance;

public class MainActivity extends MenuActivity implements SearchView.OnQueryTextListener {

    ArrayList<Pokemon> arrayList = FirebaseInstance.getInstance().getPokemonList();
    private RecyclerView recyclerView;
    private PokemonListAdapter pokemonListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareRecyclerView();
    }

    private void prepareRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        pokemonListAdapter = new PokemonListAdapter(this);
        recyclerView.setAdapter(pokemonListAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        pokemonListAdapter.setPokemonList(FirebaseInstance.getInstance().getPokemonList());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_filter_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {

        query = query.toLowerCase();
        ArrayList<Pokemon> newList = new ArrayList<>();
        for (Pokemon entry : arrayList) {
            if (entry.getName().contains(query)) {
                newList.add(entry);
            }
        }
        pokemonListAdapter.setFilter(newList);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }
}
