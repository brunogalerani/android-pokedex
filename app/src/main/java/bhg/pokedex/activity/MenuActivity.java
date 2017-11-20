package bhg.pokedex.activity;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;

import java.util.List;

public class MenuActivity extends AppCompatActivity {

    protected static final int POKEDEX = 1;
    protected static final int MORE = 2;
    protected static final int QUIZ = 21;
    protected static final int SCORES = 22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, POKEDEX, 0, "Pokédex");

        SubMenu sub = menu.addSubMenu(0, 0, MORE, "More");
        sub.add(0, QUIZ, 0, "Quiz");
        sub.add(0, SCORES, 1, "Scores");
        return true;

    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        switch (i) {
            case POKEDEX:
                changeActivity(new Intent(this, MainActivity.class), MainActivity.class.getName());
                break;
            case QUIZ:
                //Redirecionar para Quiz1
                changeActivity(new Intent(this, QuizActivity.class), QuizActivity.class.getName());
                break;
            case SCORES:
                //Redirecionar para Score
                changeActivity(new Intent(this, ScoreActivity.class), ScoreActivity.class.getName());
                break;
        }
        return false;

    }

    private void changeActivity(Intent intent, String className){

        ActivityManager mngr = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskList = mngr.getRunningTasks(10);

        if (!taskList.get(0).topActivity.getClassName().equals(className)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}

