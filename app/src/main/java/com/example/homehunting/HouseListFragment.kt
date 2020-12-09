package com.example.homehunting

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homehunting.Adapter.HousingListAdapter
import kotlinx.android.synthetic.main.fragment_housing_list.view.*

class HouseListFragment(var houseList: MutableList<Housing>): Fragment() {

    lateinit var recyclerView: RecyclerView

    private lateinit var recyclerAdapter: RecyclerView.Adapter<*>
    private lateinit var recyclerLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_housing_list, container, false)
        recyclerView = view.houseList_RecyclerView
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        recyclerAdapter = HousingListAdapter(houseList)
        recyclerView.adapter = recyclerAdapter

        recyclerLayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = recyclerLayoutManager
    }
}