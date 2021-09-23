package com.matchloper

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessaging : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        remoteMessage.notification?.let {

            when(it.title) {
                "방 상태 변화 감지" -> {

                    val notificationBuilder = createNotificationChannel("id", "name")
                        .setSmallIcon(android.R.drawable.ic_menu_search)
                        .setContentTitle(remoteMessage.notification?.title)
                        .setContentText("새로운 방이 생성되었습니다")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                    with(NotificationManagerCompat.from(this)) {
                        notify(0,notificationBuilder.build())
                    }
                }
            }
        }
    }

    private fun createNotificationChannel(id :String, name :String) : NotificationCompat.Builder{

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)

            manager.createNotificationChannel(channel)

            NotificationCompat.Builder(this, id)

        } else NotificationCompat.Builder(this)

    }
}