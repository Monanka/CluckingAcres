package com.example.cluckingacres

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                   // loadFragment(HomeFragment())
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    return@OnNavigationItemSelectedListener true
                }

                R.id.nav_notifications -> {
                    //loadFragment(NotificationFragment1())
                    val intent = Intent(this, NotificationActivity::class.java)
                    startActivity(intent)
                    return@OnNavigationItemSelectedListener true
                }

                R.id.nav_profile -> {
                    //loadFragment(ProfileFragment())
                    val intent = Intent(this, ProfileFragment1::class.java)
                    startActivity(intent)
                    return@OnNavigationItemSelectedListener true
                }

                // Add more cases for other items if needed

                else -> false
            }
        }

    private fun loadFragment(notificationFragment1: NotificationFragment1) {
        TODO("Not yet implemented")
    }


    private fun loadFragment(homeActivity: HomeActivity) {
        TODO("Not yet implemented")
    }




    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initializeBottomBar()

        val marketplaceContainer: CardView = findViewById(R.id.cardMarketplace)
        marketplaceContainer.setOnClickListener {
        //    val MarketplaceFragment = MarketplaceFragment()
       //     replaceFragment(MarketplaceFragment)
            val intent = Intent(this, MarketplaceFragment1::class.java)
            startActivity(intent)
        }


        val helpContainer: CardView = findViewById(R.id.cardHelp)
        helpContainer.setOnClickListener {
          //  val HelpFragment = HelpFragment()
           // replaceFragment(HelpFragment)
            val intent = Intent(this, HelpFragment1::class.java)
            startActivity(intent)
        }


        val eggsReportContainer: FrameLayout = findViewById(R.id.cardEggs)
        eggsReportContainer.setOnClickListener {
           // val EggsReportFragment = EggsReportFragment()
          //  replaceFragment(EggsReportFragment)
            val intent = Intent(this, EggsReportFragment1::class.java)
            startActivity(intent)

        }

        val vaccinationsContainer: FrameLayout = findViewById(R.id.cardVaccinations)
        vaccinationsContainer.setOnClickListener {
            //val VaccinationsFragment = VaccinationsFragment()
           // replaceFragment(VaccinationsFragment)
            val intent = Intent(this, VaccinationsFragment1::class.java)
            startActivity(intent)
        }

        val feedsContainer: FrameLayout = findViewById(R.id.cardFeeds)

        feedsContainer.setOnClickListener {
          //  val FeedsFragment = FeedsFragment()
           // replaceFragment(FeedsFragment)
            val intent = Intent(this, FeedsFragment1::class.java)
            startActivity(intent)
        }

       val reportsContainer: FrameLayout = findViewById(R.id.cardReports)

        reportsContainer.setOnClickListener {
            //val ReportsFragment = ReportsFragment()
            //replaceFragment(ReportsFragment)
            val intent = Intent(this, ReportsFragment1::class.java)
            startActivity(intent)
        }

    }

    private fun initializeBottomBar(){
        val bottomNav : BottomNavigationView = findViewById(R.id.bottomNav)
        bottomNav.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
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