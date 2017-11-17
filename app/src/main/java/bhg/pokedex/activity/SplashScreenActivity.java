package bhg.pokedex.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import bhg.pokedex.R;
import bhg.pokedex.util.FirebaseInstance;

public class SplashScreenActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        progressBar = (ProgressBar) findViewById(R.id.splashScreenProgressBar);

        new Thread(new Runnable() {
            public void run() {
                FirebaseInstance.getInstance();
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                doProgress();
                startApp();
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
        if (FirebaseInstance.getInstance().getPokemonList().isEmpty()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    alert = new AlertDialog.Builder(SplashScreenActivity.this);
                    alert.setTitle("Sync error!")
                            .setMessage("Please, connect to the Internet before accessing this app for the first time! The data must be sync with the cloud. " +
                                    "Please, do notice that the images and texts will be cached into your device." +
                                    "By clicking \"OK\" button this app will be closed, so you can turn on the Internet via WiFi or 3G and start it again!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    android.os.Process.killProcess(android.os.Process.myPid());
                                    System.exit(1);
                                }
                            });
                    alert.create();
                    alert.show();
                }
            });
        } else {
            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public boolean hasActiveInternetConnection(Context context) {
        if (isNetworkAvailable(context)) {
            try {
                HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
                urlc.setRequestProperty("User-Agent", "Test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(1500);
                urlc.connect();
                return (urlc.getResponseCode() == 200);
            } catch (IOException e) {
                Log.e("Pokedex", "Error checking internet connection", e);
            }
        } else {
            Log.d("Pokedex", "No network available!");
        }
        return false;
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}


