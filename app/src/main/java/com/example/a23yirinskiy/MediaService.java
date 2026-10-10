package com.example.a23yirinskiy;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MediaService extends Service {

    MediaPlayer ambientMediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        // Сервис не предполагает привязки — возвращаем null
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Создаём плеер из ресурса res/raw/music.mp3
        ambientMediaPlayer = MediaPlayer.create(this, R.raw.music);
        // Зацикливаем воспроизведение
        ambientMediaPlayer.setLooping(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ambientMediaPlayer.start();
        // Если система убьёт сервис — перезапустить его
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ambientMediaPlayer.stop();
        ambientMediaPlayer.release();
    }
}