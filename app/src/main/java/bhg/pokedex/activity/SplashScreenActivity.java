package bhg.pokedex.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import bhg.pokedex.R;
import bhg.pokedex.util.FirebaseInstance;

public class SplashScreenActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        progressBar = (ProgressBar) findViewById(R.id.splashScreenProgressBar);

        new Thread(new Runnable() {
            public void run() {
                FirebaseInstance.getInstance();
                doProgress();
                startApp();
                finish();
            }
        }).start();
    }

    private void doProgress() {
        try {
            for (int progress = 0; progress <= 100; progress += 10) {

                Thread.sleep(250);
                progressBar.setProgress(progress);
            }
            Thread.sleep(250);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startApp() {
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(intent);
    }
}


