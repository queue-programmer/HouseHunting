package com.example.homehunting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val forestCottage = Housing( "Inside the forest in a clearing in the trees", "Cottage",
                                      600000, "Self own",
                                    "On property well, herbal garden & shrine in garden for hedonistic Gods.")

        val hauntedPenthouse = Housing("Friday street nr. 13", "Penthouse",
                                        10000000, "Self own",
                                    "Creaky floors, possessed super, haunted couch, WiFi and pet friendly.")

        val vampireRoom = Housing("Castle road nr. 1666", "Room",
                                    8000, "Rent",
                                "Fully equipped kitchen, WiFi, bathroom, washroom & sexy vampire roommates.")

        val cityFlat = Housing("Courier road nr. 7E", "Flat",
                                10000, "Rent",
                            "Washing cellar, extra storage(attic & Cellar), WiFi, quiet ghost in wall neighbours and close to subway.")

        val cardboardBox = Housing("Under bridge by the park", "Box, Wooden",
                                    500, "Self Own",
                                "Right on the river, walking distance to the park, no noisy neighbours.")


        val list = mutableListOf<Housing>(forestCottage, hauntedPenthouse, vampireRoom, cityFlat, cardboardBox)

        showFragment(HouseListFragment(list))
    }

    private fun showFragment (fragmentToShow: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragmentToShow)
            .commitNow()
    }

    fun goToAddFragment(fragmentToShow: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragmentToShow)
            .addToBackStack(null)
            .commit()
    }

}