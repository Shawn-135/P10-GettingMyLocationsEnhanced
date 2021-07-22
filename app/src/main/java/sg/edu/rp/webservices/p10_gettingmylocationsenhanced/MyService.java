package sg.edu.rp.webservices.p10_gettingmylocationsenhanced;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;

import java.io.File;

public class MyService extends Service {

    Button btnStartDetector, btnStopDetector, btnRecords;

    private DownloadBinder mBinder = new DownloadBinder();
    private MediaPlayer player = new MediaPlayer();

    class DownloadBinder extends Binder {
        public void startDownload() {
            Log.d("MyService", "startDownload executed");
        }
        public int getProgress() {
            Log.d("MyService", "getProgress executed");
            return 0;
        }
    }

    boolean started;

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        Log.d("My service", "Service created");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            File file = new
                    File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyFolder",
                    "music.mp3");

            // specify the path of the audio file
            player.setDataSource(file.getPath());
            player.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // providing the boolean value as true to play the audio on Loop
        player.setLooping(true);

        // starting the process
        player.start();

        // returns the status of the program
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d("MyService", "Service exited");
        super.onDestroy();

        player.stop();
    }


}