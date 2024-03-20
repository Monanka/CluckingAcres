package com.example.cluckingacres

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar

class NotificationActivity : AppCompatActivity() {

    private lateinit var datePicker: DatePicker
    private lateinit var timePicker: TimePicker
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_notification)

        // Get references to the date and time pickers
        datePicker = findViewById(R.id.date_picker)
        timePicker = findViewById(R.id.time_picker)

        // Set up the save button to create a reminder on the calendar
        findViewById<Button>(R.id.save_button).setOnClickListener {
            createReminder()
        }
    }

    private fun createReminder() {
        // Get the selected date and time from the date and time pickers
        val year = datePicker.year
        val month = datePicker.month
        val day = datePicker.dayOfMonth
        val hour = timePicker.hour
        val minute = timePicker.minute

        // Create a Calendar object with the selected date and time
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)

        // Get the current time
        val currentTime = System.currentTimeMillis()

        // Check if the selected time is in the past
        if (calendar.timeInMillis <= currentTime) {
            Toast.makeText(this, "Please select a future date and time", Toast.LENGTH_SHORT).show()
            return
        }

        // Schedule a notification with the AlarmManager
        val notificationIntent = Intent(this, NotificationBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

        // Show a toast to confirm that the reminder was created
        Toast.makeText(this, "Reminder created for ${calendar.time}", Toast.LENGTH_SHORT).show()

        // Finish the activity
        finish()
    }

    class NotificationBroadcastReceiver{


    }
}
