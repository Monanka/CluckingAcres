package com.example.cluckingacres

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment()) // Replace with your HomeFragment
                    return@OnNavigationItemSelectedListener true
                }

                R.id.nav_notifications -> {
                    loadFragment(NotificationActivity())
                    return@OnNavigationItemSelectedListener true
                }

                R.id.nav_profile -> {
                    loadFragment(ProfileFragment())
                    return@OnNavigationItemSelectedListener true
                }

                // Add more cases for other items if needed

                else -> false
            }
        }

    private fun loadFragment(homeActivity: HomeActivity) {
        TODO("Not yet implemented")
    }

    private fun loadFragment(fragment: NotificationActivity) {
        TODO("Not yet implemented")
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val marketplaceContainer: FrameLayout = findViewById(R.id.cardMarketplace)
        marketplaceContainer.setOnClickListener {
            val MarketplaceFragment = MarketplaceFragment()
            replaceFragment(MarketplaceFragment)
        }


        val helpContainer: FrameLayout = findViewById(R.id.cardHelp)
        helpContainer.setOnClickListener {
            val HelpFragment = DiseasesFragment()
            replaceFragment(HelpFragment)
        }


        val eggsReportContainer: FrameLayout = findViewById(R.id.cardEggs)
        eggsReportContainer.setOnClickListener {
            val EggsReportFragment = EggsReportFragment()
            replaceFragment(EggsReportFragment)
        }

        val vaccinationsContainer: FrameLayout = findViewById(R.id.cardVaccinations)
        vaccinationsContainer.setOnClickListener {
            val VaccinationsFragment = VaccinationsFragment()
            replaceFragment(VaccinationsFragment)
        }

        val feedsContainer: FrameLayout = findViewById(R.id.cardFeeds)

        feedsContainer.setOnClickListener {
            val FeedsFragment = FeedsFragment()
            replaceFragment(FeedsFragment)
        }

       val reportsContainer: FrameLayout = findViewById(R.id.cardReports)

        reportsContainer.setOnClickListener {
            val ReportsFragment = ReportsFragment()
            replaceFragment(ReportsFragment)
        }

    }

     fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}