package com.example.homehunting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val forestCottage = Housing( "Inside the forest in a clearing in the trees", Type.COTTAGE,
                                      600000, "Self own",
                                    "On property well, herbal garden & shrine in garden for hedonistic Gods.")

        val hauntedPenthouse = Housing("Friday street nr. 13", Type.PENTHOUSE,
                                        10000000, "Self own",
                                    "Creaky floors, possessed super, haunted couch and pet friendly.")

        val vampireRoom = Housing("Castle road nr. 1666", Type.ROOM,
                                    8000, "Rent",
                                "Fully equipped kitchen, bathroom, washroom & sexy vampire roommates.")

        val cityFlat = Housing("Courier road nr. 7E", Type.FLAT,
                                10000, "Rent",
                            "Washing cellar, extra storage in attic, quiet neighbours and close to subway.")

        val cardboardBox = Housing("Under bridge by the park", Type.BOX,
                                    500, "Self own",
                                "Right on the river, walking distance to the park, no noisy neighbours.")

        val list = mutableListOf<Housing>(forestCottage, hauntedPenthouse, vampireRoom, cityFlat, cardboardBox)

        showFragment(HouseListFragment(list))
    }

    private fun showFragment (fragmentToShow: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragmentToShow)
            .commitNow()

    }

}