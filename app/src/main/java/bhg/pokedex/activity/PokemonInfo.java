package bhg.pokedex.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import bhg.pokedex.R;
import bhg.pokedex.model.Abilities;
import bhg.pokedex.model.Pokemon;
import bhg.pokedex.model.Stats;
import bhg.pokedex.util.FirebaseInstance;

public class PokemonInfo extends MenuActivity {

    private TextView pokemonDescription;
    private TextView heightTextView;
    private TextView weightTextView;
    private TextView hpTextView;
    private TextView attackTextView;
    private TextView defTextView;
    private TextView speedTextView;
    private TextView spattackTextView;
    private TextView spdefTextView;
    private TextView abilitiesTextView;
    private ImageView pokemonImageView;
    private ImageView type1ImageView;
    private ImageView type2ImageView;

    private Pokemon pokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_info);

        pokemonImageView = (ImageView) findViewById(R.id.pokemonInfoImageView);
        type1ImageView = (ImageView) findViewById(R.id.type1ImageView);
        type2ImageView = (ImageView) findViewById(R.id.type2ImageView);
        pokemonDescription = (TextView) findViewById(R.id.pokemonDescriptionTextView);
        heightTextView = (TextView) findViewById(R.id.heightTextView);
        weightTextView = (TextView) findViewById(R.id.widthTextView);
        hpTextView = (TextView) findViewById(R.id.hpTextView);
        attackTextView = (TextView) findViewById(R.id.atckTextView);
        defTextView = (TextView) findViewById(R.id.defTextView);
        speedTextView = (TextView) findViewById(R.id.speedTextView);
        spattackTextView = (TextView) findViewById(R.id.spatckTextView);
        spdefTextView = (TextView) findViewById(R.id.spdefTextView);
        abilitiesTextView = (TextView) findViewById(R.id.abilityTitle);

        pokemonDescription.setMovementMethod(new ScrollingMovementMethod());
        pokemonDescription.setScrollbarFadingEnabled(false);

        String pokemonName = getIntent().getStringExtra("name");
        pokemon = FirebaseInstance.getInstance().getPokemon(pokemonName);

        setInfoInActivity();
    }

    private void setInfoInActivity() {
        pokemonDescription.setText(pokemon.getDescription());

        Glide.with(this)
                .load(FirebaseInstance.getInstance().getUrlToImage(pokemon.getName()))
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(pokemonImageView);

        Resources resources = this.getResources();
        String type1Name = pokemon.getTypes().get(0).getName();
        int idType1 = resources.getIdentifier("type_" + type1Name, "drawable", this.getPackageName());
        if (pokemon.getTypes().size() != 1) {
            String type2Name = pokemon.getTypes().get(1).getName();
            int idType2 = resources.getIdentifier("type_" + type2Name, "drawable", this.getPackageName());
            type1ImageView.setImageResource(idType2);
            type2ImageView.setImageResource(idType1);
        } else {
            type1ImageView.setImageResource(idType1);
            type2ImageView.setImageResource(android.R.color.transparent);
        }

        String weight = weightTextView.getText().toString() + " " + String.valueOf((double)(pokemon.getWeight())/10) + "kg";
        String height = heightTextView.getText().toString() + " " + String.valueOf((double)(pokemon.getHeight())/10) + "m";
        weightTextView.setText(weight);
        heightTextView.setText(height);

        StringBuilder abilities = new StringBuilder(abilitiesTextView.getText().toString() + " ");
        for (Abilities ability : pokemon.getAbilities()) {
            abilities.append(ability.getName()).append("  ");
        }
        abilitiesTextView.setText(abilities.toString());

        String hp = hpTextView.getText().toString() + " ";
        String attack = attackTextView.getText().toString() + " ";
        String def = defTextView.getText().toString() + " ";
        String speed = speedTextView.getText().toString() + " ";
        String spattack = spattackTextView.getText().toString() + " ";
        String spdef = spdefTextView.getText().toString() + " ";

        Stats hpStat = pokemon.getStats().get(5);
        Stats attackStat = pokemon.getStats().get(4);
        Stats defStat = pokemon.getStats().get(3);
        Stats speedStat = pokemon.getStats().get(0);
        Stats spattackStat = pokemon.getStats().get(2);
        Stats spdefStat = pokemon.getStats().get(1);

        hp += String.valueOf(hpStat.getBaseStat()) + "(" + String.valueOf(hpStat.getEffort()) + ")";
        attack += String.valueOf(attackStat.getBaseStat()) + "(" + String.valueOf(attackStat.getEffort()) + ")";
        def += String.valueOf(defStat.getBaseStat()) + "(" + String.valueOf(defStat.getEffort()) + ")";
        speed += String.valueOf(speedStat.getBaseStat()) + "(" + String.valueOf(speedStat.getEffort()) + ")";
        spattack += String.valueOf(spattackStat.getBaseStat()) + "(" + String.valueOf(spattackStat.getEffort()) + ")";
        spdef += String.valueOf(spdefStat.getBaseStat()) + "(" + String.valueOf(spdefStat.getEffort()) + ")";

        hpTextView.setText(hp);
        attackTextView.setText(attack);
        defTextView.setText(def);
        speedTextView.setText(speed);
        spattackTextView.setText(spattack);
        spdefTextView.setText(spdef);
    }
}

