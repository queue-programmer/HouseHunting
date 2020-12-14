package com.example.homehunting

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.layout_custom.view.*

class CustomHousingLayout(context: Context, attributeSet: AttributeSet? = null) :
        ConstraintLayout(context, attributeSet) {

    val addressTitle: TextView
    val addressText: TextView
    val priceTitle: TextView
    val priceText: TextView
    val typeTitle: TextView
    val typeText: TextView
    val amenitiesTitle: TextView
    val amenitiesText: TextView
    val ownerFormTitle: TextView
    val ownerFormText: TextView

    val deleteButton: Button


    init {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_custom, this)

        addressTitle = view.addressTitle_text
        addressText = view.address_text
        priceTitle = view.priceTitle_text
        priceText = view.price_text
        typeTitle = view.typeTitle_text
        typeText = view.type_text
        amenitiesTitle = view.amenitiesTitle_text
        amenitiesText = view.amenities_text
        ownerFormTitle = view.ownerFormTitle_text
        ownerFormText = view.ownerForm_text

        deleteButton = view.delete_button

       // setBackgroundColor(context.getColor(R.color.colorPrimary))
    }



    fun setAddress(newText: String) {
        addressText.text = newText
    }

    fun setPrice(newText: String){
        priceText.text = newText
    }

    fun setType(newText: String){
        typeText.text = newText
    }

    fun setAmenities(newText: String){
        amenitiesText.text = newText
    }

    fun setOwnerForm(newText: String){
        ownerFormText.text = newText
    }
}