package com.jqorz.test2.messenger

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.*
import android.widget.Toast

/**
 * @author  jqorz
 * @since  2021/8/28
 */
class Messenger2Service : Service() {

    internal class MyHandler(val context: Context) : Handler() {
        override fun handleMessage(msg: Message) {
            Toast.makeText(context.applicationContext, "${msg.what}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate() {
        super.onCreate()
        setNotification()
    }

    private fun setNotification() {
        val notification: Notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("screen", "同屏", NotificationManager.IMPORTANCE_HIGH)
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            Notification.Builder(this.applicationContext, "screen")
                .setContentTitle("1")
                .setContentText("同屏中...")
                .setWhen(System.currentTimeMillis())
                .build()
        } else {
            Notification.Builder(this.applicationContext)
                .setContentTitle("1")
                .setContentText("同屏中...")
                .setWhen(System.currentTimeMillis())
                .build()
        }
        startForeground(NOTIFICATION_ID, notification)
    }

    override fun onBind(intent: Intent?): IBinder? {
        Toast.makeText(applicationContext, "onBind", Toast.LENGTH_SHORT).show()
        val messenger = Messenger(MyHandler(this))
        return messenger.binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(applicationContext, "onStartCommand", Toast.LENGTH_SHORT).show()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopForeground(true)
    }

    companion object {
        private const val NOTIFICATION_ID = 110 // 如果id设置为0,会导致不能设置为前台service
    }
}