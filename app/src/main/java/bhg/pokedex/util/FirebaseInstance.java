package bhg.pokedex.util;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import bhg.pokedex.model.Pokemon;

/**
 * Created by bgalerani on 10/31/2017.
 */

public class FirebaseInstance {

    private static FirebaseInstance firebaseInstance;
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;
    private ArrayList<Pokemon> pokemonList;

    private FirebaseInstance() {
    }

    public DatabaseReference getDataBase() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance().getReference().child("Pokemons");
            mDatabase.keepSynced(true);
        }
        return mDatabase;
    }

    public FirebaseAuth getFirebaseAuth() {

        if (firebaseAuth == null) {
            firebaseAuth = FirebaseAuth.getInstance();
        }
        return firebaseAuth;
    }

    public ArrayList<Pokemon> getPokemonList() {
        return pokemonList;
    }

    public String getUrlToImage(String name) {
        int id = 1;
        for (int i = 0; i < pokemonList.size(); i++) {
            if (pokemonList.get(i).getName().equals(name)) {
                id += i;
                break;
            }
        }
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + id + ".png";
    }

    private void loadDataFromFireBase() {
        pokemonList = new ArrayList<>();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        getInstance().getDataBase().orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    pokemonList.add(data.getValue(Pokemon.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static synchronized FirebaseInstance getInstance() {
        if (firebaseInstance == null) {
            firebaseInstance = new FirebaseInstance();
            firebaseInstance.loadDataFromFireBase();
        }
        return firebaseInstance;
    }

    public Pokemon getPokemon(String pokemonName) {
        for (Pokemon pokemon : pokemonList) {
            if (pokemon.getName().equals(pokemonName)) {
                return pokemon;
            }
        }
        return pokemonList.get(0);
    }
}
