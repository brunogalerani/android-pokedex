package bhg.pokedex.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import bhg.pokedex.R;
import bhg.pokedex.util.FirebaseInstance;

public class PokemonInfo extends AppCompatActivity {

    private TextView textViewPokemonName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_info);
        String pokemonName = getIntent().getStringExtra("name");
        textViewPokemonName = findViewById(R.id.textViewPokemonName);
        textViewPokemonName.setText(FirebaseInstance.getInstance().getPokemon(pokemonName).getName());
    }
}
