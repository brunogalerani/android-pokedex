package bhg.pokedex.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Vibrator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import bhg.pokedex.R;
import bhg.pokedex.activity.PokemonInfo;
import bhg.pokedex.model.Pokemon;
import bhg.pokedex.util.FirebaseInstance;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by Bruno on 05/11/2017.
 */

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder> {

    private ArrayList<Pokemon> pokemonList;
    private Context context;
    private Vibrator vibe;

    public PokemonListAdapter(Context context) {
        this.context = context;
        pokemonList = new ArrayList<>();
        vibe = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Pokemon pokemon = pokemonList.get(position);
        holder.pokemonNameTextView.setText(pokemon.getName());
        Glide.with(context)
                .load(FirebaseInstance.getInstance().getUrlToImage(pokemon.getName()))
                .apply(RequestOptions.centerCropTransform())
                .transition(withCrossFade())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(holder.pokemonPicImageView);

        Resources resources = context.getResources();
        String type1Name = pokemon.getTypes().get(0).getName();
        int idType1 = resources.getIdentifier("type_" + type1Name, "drawable", context.getPackageName());
        if (pokemon.getTypes().size() != 1) {
            String type2Name = pokemon.getTypes().get(1).getName();
            int idType2 = resources.getIdentifier("type_" + type2Name, "drawable", context.getPackageName());
            holder.type1ImageView.setImageResource(idType2);
            holder.type2ImageView.setImageResource(idType1);
        } else {
            holder.type1ImageView.setImageResource(idType1);
            holder.type2ImageView.setImageResource(android.R.color.transparent);
        }

        holder.pokemonPicImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibe.vibrate(100);
                Intent intent = new Intent(context, PokemonInfo.class);
                intent.putExtra("name", pokemon.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public void setPokemonList(List<Pokemon> pokemonList) {
        this.pokemonList.addAll(pokemonList);
        notifyDataSetChanged();
    }

    public void setFilter(ArrayList<Pokemon> newList) {
        //we have used hashmap,  find how to use it here
        pokemonList = new ArrayList<Pokemon>();
        pokemonList.addAll(newList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        protected ImageView pokemonPicImageView;
        protected TextView pokemonNameTextView;
        protected ImageView type1ImageView;
        protected ImageView type2ImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            pokemonPicImageView = (ImageView) itemView.findViewById(R.id.pokemonPicImageView);
            pokemonNameTextView = (TextView) itemView.findViewById(R.id.textViewPokemonName);
            type1ImageView = (ImageView) itemView.findViewById(R.id.type1ImageView);
            type2ImageView = (ImageView) itemView.findViewById(R.id.type2ImageView);
        }
    }
}
