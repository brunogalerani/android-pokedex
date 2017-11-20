package bhg.pokedex.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import bhg.pokedex.R;
import bhg.pokedex.database.DatabaseHandler;
import bhg.pokedex.model.Score;

public class QuizActivity extends MenuActivity {

    private RadioGroup question1;
    private RadioGroup question2;
    private RadioGroup question3;
    private RadioGroup question4;
    private RadioGroup question5;
    private Button submitButton;

    private int idQuestion1;
    private int idQuestion2;
    private int idQuestion3;
    private int idQuestion4;
    private int idQuestion5;

    private int result;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        question1 = (RadioGroup) findViewById(R.id.question1);
        question2 = (RadioGroup) findViewById(R.id.question2);
        question3 = (RadioGroup) findViewById(R.id.question3);
        question4 = (RadioGroup) findViewById(R.id.question4);
        question5 = (RadioGroup) findViewById(R.id.question5);
        submitButton = (Button) findViewById(R.id.submitButton);

        idQuestion1 = R.id.q1a3;
        idQuestion2 = R.id.q2a1;
        idQuestion3 = R.id.q3a1;
        idQuestion4 = R.id.q4a1;
        idQuestion5 = R.id.q5a4;

        mediaPlayer = MediaPlayer.create(QuizActivity.this, R.raw.pikachu_sound);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFullySelected()) {
                    Toast.makeText(QuizActivity.this, "Please, select an answer for each question before submitting!", Toast.LENGTH_SHORT).show();
                } else {
                    mediaPlayer.start();
                    result = 0;
                    if (question1.getCheckedRadioButtonId() == idQuestion1) result++;
                    if (question2.getCheckedRadioButtonId() == idQuestion2) result++;
                    if (question3.getCheckedRadioButtonId() == idQuestion3) result++;
                    if (question4.getCheckedRadioButtonId() == idQuestion4) result++;
                    if (question5.getCheckedRadioButtonId() == idQuestion5) result++;

                    final AlertDialog.Builder builder = new AlertDialog.Builder(
                            QuizActivity.this);
                    builder.setMessage("Enter your name: ");
                    final EditText name = new EditText(QuizActivity.this);
                    builder.setView(name);
                    builder.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int whichButton) {
                                    if (name.getText().toString().isEmpty()) {
                                        Toast.makeText(QuizActivity.this, "Enter a name!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        DatabaseHandler db = new DatabaseHandler(QuizActivity.this);
                                        Score score = new Score(name.getText().toString(), result);
                                        db.addScore(score);
                                        Intent intent = new Intent(QuizActivity.this, ScoreActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        finish();
                                        startActivity(intent);
                                    }
                                }
                            });
                    builder.show();
                }
            }
        });
    }

    private boolean isFullySelected() {
        return question1.getCheckedRadioButtonId() != -1 &&
                question2.getCheckedRadioButtonId() != -1 &&
                question3.getCheckedRadioButtonId() != -1 &&
                question4.getCheckedRadioButtonId() != -1 &&
                question5.getCheckedRadioButtonId() != -1;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }
}
