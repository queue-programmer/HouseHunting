package com.example.homehunting.Adapter

import android.content.Context
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.homehunting.CustomHousingLayout
import com.example.homehunting.Housing
import kotlinx.android.synthetic.main.layout_custom.view.*

class HousingListAdapter(
    var dataset: MutableList<Housing>
) : RecyclerView.Adapter<HousingListAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        context = parent.context
        val listItem = CustomHousingLayout(context)

        listItem.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )



        return ViewHolder(listItem)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.listItem.setAddress(dataset[position].address)
        holder.listItem.setPrice(dataset[position].price.toString())
        holder.listItem.setType(dataset[position].type.name)
        holder.listItem.delete_button.setOnClickListener {
            Toast.makeText(context,"I pressed the delete button on ${dataset[position].address}", Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    class ViewHolder(val listItem: CustomHousingLayout) : RecyclerView.ViewHolder(listItem)
    
}