package bhg.pokedex.util;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by bgalerani on 10/31/2017.
 */

public class FirebaseInstance {

    private static FirebaseInstance firebaseInstance;
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;

    private FirebaseInstance() {
    }

    public DatabaseReference getDataBase() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance().getReference().child("Pokemons");
        }
        return mDatabase;
    }

    public FirebaseAuth getFirebaseAuth() {

        if (firebaseAuth == null) {
            firebaseAuth = FirebaseAuth.getInstance();
        }
        return firebaseAuth;
    }

    public static synchronized FirebaseInstance getInstance() {
        if (firebaseInstance == null) {
            firebaseInstance = new FirebaseInstance();
        }
        return firebaseInstance;
    }
}
