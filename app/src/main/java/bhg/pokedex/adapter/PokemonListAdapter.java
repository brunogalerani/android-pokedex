package bhg.pokedex.adapter;

import android.content.Context;
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
import bhg.pokedex.model.Pokemon;
import bhg.pokedex.util.FirebaseInstance;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by Bruno on 05/11/2017.
 */

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder> {

    private ArrayList<Pokemon> pokemonList;
    private Context context;

    public PokemonListAdapter(Context context) {
        this.context = context;
        pokemonList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pokemon pokemon = pokemonList.get(position);
        holder.pokemonNameTextView.setText(pokemon.getName());
        Glide.with(context)
                .load(FirebaseInstance.getInstance().getUrlToImage(position + 1))
                .apply(RequestOptions.centerCropTransform())
                .transition(withCrossFade())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(holder.pokemonPicImageView);
        holder.type1ImageView.setImageResource(R.drawable.type_fire);
        holder.type2ImageView.setImageResource(R.drawable.type_electric);
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public void setPokemonList(List<Pokemon> pokemonList) {
        this.pokemonList.addAll(pokemonList);
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
