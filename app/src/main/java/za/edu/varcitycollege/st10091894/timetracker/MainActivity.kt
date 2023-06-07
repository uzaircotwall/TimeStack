package za.edu.varcitycollege.st10091894.timetracker

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * Code Attribution

//Some of the following code was taken from youtube
//https://www.youtube.com/watch?v=_cnwpijQZ1w&list=PLklWDN5GwmGEamA2DTsJk631LKuZLdsHf&index=1
//Author: Faheem Hassan
//https://www.youtube.com/@faheemh127

//Some of the following code was taken from youtube
//https://www.youtube.com/watch?v=EwfLZsSSElE&list=PLklWDN5GwmGEamA2DTsJk631LKuZLdsHf&index=2
//Author: FineGap
//https://www.youtube.com/@finegap

//Some of the following code was taken from youtube
//https://www.youtube.com/watch?v=YacRL2buFxk&list=PLklWDN5GwmGEamA2DTsJk631LKuZLdsHf&index=6
//Author: Ahmad Ishaq
//https://www.youtube.com/@ahmadishaq703

//Some of the following code was taken from youtube
//https://www.youtube.com/watch?v=h6g4NpiC0i4&list=PLklWDN5GwmGEamA2DTsJk631LKuZLdsHf&index=7
//Author: TechizVibe
//https://www.youtube.com/@TechizVibe

//Some of the following code was taken from youtube
//https://www.youtube.com/watch?v=jR4GyLXdbTA&list=PLklWDN5GwmGEamA2DTsJk631LKuZLdsHf&index=8
//Author: TechizVibe
//https://www.youtube.com/@TechizVibe

//Some of the following code was taken from youtube
//https://www.youtube.com/watch?v=NqWDnqz6--0&list=PLklWDN5GwmGEamA2DTsJk631LKuZLdsHf&index=9
//Author: Turtle Code
//https://www.youtube.com/@turtlecode

//Some of the following code was taken from youtube
//https://www.youtube.com/watch?v=-nD1aKeLij4&list=PLklWDN5GwmGEamA2DTsJk631LKuZLdsHf&index=10
//Author: YF Land
//https://www.youtube.com/@yfland

//Some of the following code was taken from youtube
//https://www.youtube.com/watch?v=LMPmybCTKDA&list=PLklWDN5GwmGEamA2DTsJk631LKuZLdsHf&index=11
//Author: Atif Pervaiz
//https://www.youtube.com/@AtifSayings

//Some of the following code was taken from youtube
//https://www.youtube.com/watch?v=U3Km_HgbQWw&list=PLklWDN5GwmGEamA2DTsJk631LKuZLdsHf&index=12
//Author: Coding Hands
//https://www.youtube.com/@codinghands2262

//Some of the following code was taken from youtube
//https://www.youtube.com/watch?v=vFziVSSIX6Q&list=PLklWDN5GwmGEamA2DTsJk631LKuZLdsHf&index=14
//Author: Technical Coding
//https://www.youtube.com/@TechnicalCoding

//Some of the following code was taken from youtube
//https://www.youtube.com/watch?v=oDfl-xLXiac&list=PLklWDN5GwmGEamA2DTsJk631LKuZLdsHf&index=15
//Author: tutorialsEU
//https://www.youtube.com/@tutorialsEU

//Some of the following code was taken from youtube
//https://www.youtube.com/watch?v=pMLmVJqL_ys&list=PLklWDN5GwmGEamA2DTsJk631LKuZLdsHf&index=16
//Author: tutorialsEU
//https://www.youtube.com/@tutorialsEU

//Some of the following code was taken from youtube
//https://www.youtube.com/watch?v=gzHy6wKAJh8&list=PLklWDN5GwmGEamA2DTsJk631LKuZLdsHf&index=17
//Author: Philipp Lackner
//https://www.youtube.com/@PhilippLackner

//Some of the following code was taken from youtube
//https://www.youtube.com/watch?v=MYAz3g60SKc&list=PLklWDN5GwmGEamA2DTsJk631LKuZLdsHf&index=21
//Author: Candra Julius Sinaga
//https://www.youtube.com/@candrajuliussinaga5211
 */
class MainActivity : AppCompatActivity() {
    private val timeSheetEntryFragment = TimeSheetEntriesFragment()
    private val timeSheetCategoryFragment = TimeSheetClientFragment()
    private val timeSheetStatsFragment = TimeSheetStatsFragment()
    private var photoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavMenu)

        //default fragment that shows up first when app is first opened
        replaceFragment(timeSheetEntryFragment)

        //set bottom navigation view functionality
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.miEntries -> replaceFragment(timeSheetEntryFragment)
                R.id.miCreateClient -> replaceFragment(timeSheetCategoryFragment)
                R.id.miGraph -> replaceFragment(timeSheetStatsFragment)
            }
            true
        }

        }


    //Method that switches out fragments on the main activity
    fun replaceFragment(fragment: Fragment){
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout, fragment)
            transaction.commit()
        }
    }
}