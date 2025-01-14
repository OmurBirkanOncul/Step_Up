package com.example.stepup.helpers

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.stepup.R
import kotlinx.coroutines.*
import kotlin.random.Random

class NotificationService : Service() {

    private val motivationalMessages = listOf(
        "Every small step today leads to big achievements tomorrow. Keep going!",
        "Stay focused on your habits to make today a great day!",
        "Success begins with habits!",
        "Remember, today's actions shape tomorrow's achievements.",
        "Consistency is the key to success. Stay focused on today's goal!",
        "Success is a journey, not a destination. Enjoy the ride!",
        "Big things are the sum of small habits. You're on the right track!",
        "When motivation dips, remember why you started.",
        "Your habits are shaping your future. Keep building greatness!",
        "Don't skip your goals today—your future self will thank you!",
        "Had your coffee? It's time to crush your goals!"
    )

    private val channelId = "default_channel_id"
    private val serviceScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var job: Job? = null

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }
        startForeground(1, createNotification("Motivational Service is running"))
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        job = serviceScope.launch {
            while (isActive) {
                delay(60 * 1000L)
                val randomMessage = motivationalMessages.random()
                sendNotification(randomMessage)
            }
        }
        return START_STICKY
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "default_channel", // Kanal ID'si
                "Default Channel", // Kanal Adı
                NotificationManager.IMPORTANCE_LOW // Önem Düzeyi
            ).apply {
                description = "Channel for motivational notifications"
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
        serviceScope.cancel()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun createNotification(message: String): Notification {
        return NotificationCompat.Builder(this, "default_channel")
            .setContentTitle("StepUp Motivation") // Başlık
            .setContentText(message) // Mesaj
            .setSmallIcon(R.drawable.logo) // İkon
            .setPriority(NotificationCompat.PRIORITY_LOW) // Düşük öncelikli
            .setOngoing(true) // Kullanıcı tarafından kaldırılamaz
            .build()
    }

    private fun sendNotification(message: String) {
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = createNotification(message)
        manager.notify(Random.nextInt(), notification)
    }
}
