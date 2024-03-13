package com.example.cluckingacres

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ReportsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reports, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Calculate and display daily and weekly reports
        calculateAndDisplayReports()
    }

    private fun calculateAndDisplayReports() {
        // Implement the logic to calculate eggs harvested, feeds given,
        // and vaccinations given for both daily and weekly reports
        // Retrieve data from database or repository as needed

        // For demonstration, let's assume the calculated values
        val dailyEggs = 10
        val dailyFeeds = 20
        val dailyVaccinations = 5

        val weeklyEggs = 70
        val weeklyFeeds = 140
        val weeklyVaccinations = 35

        // Update text views with calculated values
        view?.findViewById<TextView>(R.id.textViewDailyEggCount)?.text = "Eggs Harvested: $dailyEggs"
        view?.findViewById<TextView>(R.id.textViewDailyFeedsGiven)?.text = "Feeds Given: $dailyFeeds"
        view?.findViewById<TextView>(R.id.textViewDailyVaccinationsGiven)?.text = "Vaccinations Given: $dailyVaccinations"

        view?.findViewById<TextView>(R.id.textViewWeeklyEggCount)?.text = "Eggs Harvested: $weeklyEggs"
        view?.findViewById<TextView>(R.id.textViewWeeklyFeedsGiven)?.text = "Feeds Given: $weeklyFeeds"
        view?.findViewById<TextView>(R.id.textViewWeeklyVaccinationsGiven)?.text = "Vaccinations Given: $weeklyVaccinations"
    }
}
