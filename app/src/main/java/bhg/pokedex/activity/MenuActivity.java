package bhg.pokedex.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    protected static final int POKEDEX = 1;
    protected static final int QUIZES = 2;
    protected static final int QUIZ1 = 21;
    protected static final int QUIZ2 = 22;
    protected static final int QUIZ3 = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, POKEDEX, 0, "Pokédex");

        SubMenu sub = menu.addSubMenu(0, 0, QUIZES, "Quizes");
        sub.add(0, QUIZ1, 0, "Quiz 1");
        sub.add(0, QUIZ2, 1, "Quiz 2");
        sub.add(0, QUIZ3, 2, "Quiz 3");
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        Intent intent;
        switch (i) {
            case POKEDEX:
                Toast.makeText(this, "Funcionalidade ainda não implementada!", Toast.LENGTH_SHORT).show();
                break;
            case QUIZ1:
                //Redirecionar para Quiz1
                Toast.makeText(this, "Funcionalidade ainda não implementada!", Toast.LENGTH_SHORT).show();
                break;
            case QUIZ2:
                //Redirecionar para Quiz2
                Toast.makeText(this, "Funcionalidade ainda não implementada!", Toast.LENGTH_SHORT).show();
                break;
            case QUIZ3:
                //Redicrecionar para quiz3
                Toast.makeText(this, "Funcionalidade ainda não implementada!", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;

    }
}

