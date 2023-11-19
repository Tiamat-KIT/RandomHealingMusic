package com.example.nantokanare

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast

private const val ACTION_PLAY: String = "com.example.action.PLAY"

class MusicService : Service(),MediaPlayer.OnPreparedListener,MediaPlayer.OnErrorListener {
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        val soundlist = listOf(
            R.raw.fjordnosundakaze,
            R.raw.yume,
            R.raw.shichigatsunokomorebi,
            R.raw.toikioku_healing)
        mediaPlayer = MediaPlayer.create(applicationContext,soundlist.random())
    }

    /*fun initMediaPlayer(){
        mediaPlayer?.setOnErrorListener(this)
    }*/

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        /*val context = applicationContext
        val chanelId = "default"
        val title = getString(R.string.app_name)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val pendingIntent: PendingIntent = Intent(this,MainActivity::class.java).let {
            notificationManager -> PendingIntent.getActivity(this,0,notificationManager,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        }

        val channel = NotificationChannel(
            chanelId,title,NotificationManager.IMPORTANCE_DEFAULT
        )

        notificationManager.createNotificationChannel(channel)

        val notification = Notification.Builder(context, chanelId)
            .setContentTitle(title) // android標準アイコンから
            .setSmallIcon(android.R.drawable.ic_media_play)
            .setContentText("MediaPlay")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setWhen(System.currentTimeMillis())
            .build()

            // startForeground
            startForeground(1, notification)*/
            audioStart()
            return START_NOT_STICKY
            //return START_STICKY;
            //return START_REDELIVER_INTENT;
    }

    private fun audioStart(){
        mediaPlayer.start()

        var str = "Healing..."
        val toast: Toast = Toast.makeText(this,str,Toast.LENGTH_LONG)
        toast.show()

        mediaPlayer.setOnCompletionListener {
            audioStop()
            // Service終了
            stopSelf()
        }
    }

    private fun audioStop() {
        // 再生終了
        mediaPlayer?.stop()
        // リセット
        mediaPlayer?.reset()
        // リソースの解放
        mediaPlayer?.release()
    }

    override fun onDestroy() {
        super.onDestroy()
        audioStop()
        // Service終了
        stopSelf()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onPrepared(mp: MediaPlayer?) {
        TODO("Not yet implemented")
    }

    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        TODO("Not yet implemented")
    }
}