package bhg.pokedex.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import bhg.pokedex.R;
import bhg.pokedex.adapter.PokemonListAdapter;
import bhg.pokedex.util.FirebaseInstance;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PokemonListAdapter pokemonListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareRecyclerView();
    }

    private void prepareRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        pokemonListAdapter = new PokemonListAdapter(this);
        recyclerView.setAdapter(pokemonListAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        pokemonListAdapter.setPokemonList(FirebaseInstance.getInstance().getPokemonList());
    }
}
