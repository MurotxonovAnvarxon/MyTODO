package com.example.mytodo.utils

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.mytodo.R
import dagger.assisted.AssistedInject


class NotificationHelper @AssistedInject constructor(private val context: Context)  {
    private val notificationBuilder: NotificationCompat.Builder by lazy {
        NotificationCompat.Builder(context, "Notification")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("toDo app!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
    }
    val notificationManagerCompat: NotificationManagerCompat by lazy {
        NotificationManagerCompat.from(
            context
        )
    }

    @SuppressLint("NewApi")
    fun getNotification(): Notification {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "First"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel("Notification", name, importance)
            notificationManagerCompat.createNotificationChannel(mChannel)
        }else{
            val name2 = "First"
            val importance2 = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel2 = NotificationChannel("Notification", name2, importance2)
            notificationManagerCompat.createNotificationChannel(mChannel2)
        }

        return notificationBuilder.build()
    }


}