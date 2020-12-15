package com.example.homehunting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.homehunting.Interfaces.AddHouseListener
import kotlinx.android.synthetic.main.add_house_layout.*
import kotlinx.android.synthetic.main.add_house_layout.view.*


class AddHouseFragment(private val addHouse: AddHouseListener) : Fragment() {

    private lateinit var addressInput: EditText
    private lateinit var typeInput: EditText
    private lateinit var ownerFormInput: EditText
    private lateinit var priceInput: EditText
    private lateinit var amenitiesInput: EditText

    private lateinit var addButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.add_house_layout, container, false)

        addressInput = view.new_address
        typeInput = view.new_type
        ownerFormInput = view.new_ownerForm
        priceInput = view.new_price
        amenitiesInput = view.new_amenities

        addButton = view.final_add_button

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListeners()
    }

    private fun setOnClickListeners (){


        addButton.setOnClickListener {
            tryToCreateHousing()

        }
    }

    private fun tryToCreateHousing ( ){

        val address: String = addressInput.text.toString()
        val type: String = typeInput.text.toString()
        val ownerForm: String = ownerFormInput.text.toString()
        val price: Int? = priceInput.text.toString().toIntOrNull()
        val amenities: String = amenitiesInput.text.toString()

        if (address != "" && type != "" && ownerForm != "" && price != null){
            val newHousing = Housing(address, type, price, ownerForm, amenities)

            addHouse.addListener(newHousing)
        }
        else{
            Toast.makeText(context,"Please fill out all spaces.", Toast.LENGTH_LONG).show()
        }
    }
}