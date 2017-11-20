package bhg.pokedex.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

import bhg.pokedex.R;
import bhg.pokedex.database.DatabaseHandler;
import bhg.pokedex.model.Score;

public class ScoreActivity extends MenuActivity {

    private TableLayout tableLayout;
    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        databaseHandler = new DatabaseHandler(this);
        tableLayout.setStretchAllColumns(true);

        List<Score> scores = databaseHandler.getAllScores();
        for (Score score : scores) {
            TableRow row = new TableRow(this);
            row.setGravity(Gravity.CENTER);

            TextView nameTextView = new TextView(this);
            TextView scoreTextView = new TextView(this);

            nameTextView.setText(score.getName());
            nameTextView.setGravity(Gravity.CENTER);

            scoreTextView.setText(String.valueOf(score.getScore()));
            scoreTextView.setGravity(Gravity.CENTER);

            row.addView(nameTextView);
            row.addView(scoreTextView);
            row.setBackgroundResource(R.drawable.row_border);
            tableLayout.addView(row, score.getId());
        }
    }
}
