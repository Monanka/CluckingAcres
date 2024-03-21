package com.example.cluckingacres

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar
import java.util.Date

class NotificationActivity : AppCompatActivity() {

    private lateinit var datePicker: DatePicker
    private lateinit var timePicker: TimePicker
    private lateinit var db: FirebaseFirestore
    private lateinit var itemNameEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_notification)

        // Get references to the date and time pickers
        datePicker = findViewById(R.id.date_picker)
        timePicker = findViewById(R.id.time_picker)
        itemNameEditText = findViewById(R.id.item_name_edit_text)

        // Set up the save button to create a reminder on the calendar
        findViewById<Button>(R.id.save_button).setOnClickListener {
            createReminder()
        }

        db = FirebaseFirestore.getInstance()
    }

    private fun createReminder() {
        // Get the selected date and time from the date and time pickers
        val year = datePicker.year
        val month = datePicker.month
        val day = datePicker.dayOfMonth
        val hour = timePicker.hour
        val minute = timePicker.minute

        val itemName = itemNameEditText.text.toString()

        // Create a Calendar object with the selected date and time
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day, hour, minute, 0)

        // Get the current time
        val currentTime = System.currentTimeMillis()

        // Check if the selected time is in the past
        if (calendar.timeInMillis <= currentTime) {
            Toast.makeText(this, "Please select a future date and time", Toast.LENGTH_SHORT).show()
            return
        }

        // Store the reminder in Firestore
        val reminderData = hashMapOf(
            "year" to year,
            "month" to month,
            "day" to day,
            "hour" to hour,
            "minute" to minute,
        )

        db.collection("reminders")
            .add(reminderData)
            .addOnSuccessListener { documentReference ->
                scheduleNotification(calendar.timeInMillis, documentReference.id)
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to create reminder", Toast.LENGTH_SHORT).show()
            }
    }

    private fun scheduleNotification(timeInMillis: Long, reminderId: String) {
        // Schedule a notification with the AlarmManager
        val notificationIntent = Intent(this, NotificationBroadcastReceiver::class.java)
        notificationIntent.putExtra("reminder_id", reminderId)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE // Updated flags
        )
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent)

        // Show a toast to confirm that the reminder was created
        Toast.makeText(this, "Reminder created for ${Date(timeInMillis)}", Toast.LENGTH_SHORT)
            .show()

        // Finish the activity
        finish()
    }

    class NotificationBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // Retrieve reminder ID from intent
            val reminderId = intent.getStringExtra("reminder_id")

            // Access Firestore instance
            val db = FirebaseFirestore.getInstance()

            // Fetch reminder details from Firestore using reminderId
            db.collection("reminders")
                .document(reminderId!!)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val reminderData = document.data
                        // Extract reminder details
                        val year = reminderData?.get("year") as Long
                        val month = reminderData["month"] as Long
                        val day = reminderData["day"] as Long
                        val hour = reminderData["hour"] as Long
                        val minute = reminderData["minute"] as Long

                        // Display notification with the reminder details
                        val notificationText = "Reminder: $year-$month-$day $hour:$minute"
                        Toast.makeText(context, notificationText, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Reminder not found", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(context, "Failed to fetch reminder details", Toast.LENGTH_SHORT)
                        .show()
                }
        }
    }
}
