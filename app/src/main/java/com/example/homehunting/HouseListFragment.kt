package com.example.homehunting

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homehunting.Adapter.HousingListAdapter
import com.example.homehunting.Interfaces.AddHouseListener
import com.example.homehunting.Interfaces.RecyclerEventListener
import kotlinx.android.synthetic.main.fragment_housing_list.*
import kotlinx.android.synthetic.main.fragment_housing_list.view.*
import kotlinx.android.synthetic.main.layout_custom.*

class HouseListFragment(var houseList: MutableList<Housing>): Fragment(), RecyclerEventListener, AddHouseListener{

    lateinit var recyclerView: RecyclerView
    lateinit var houseSearchBar: SearchView

    private lateinit var recyclerAdapter: HousingListAdapter
    private lateinit var recyclerLayoutManager: RecyclerView.LayoutManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_housing_list, container, false)

        houseSearchBar = view.housing_search_view
        recyclerView = view.houseList_RecyclerView

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerAdapter = HousingListAdapter(houseList,this)
        recyclerView.adapter = recyclerAdapter

        recyclerLayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = recyclerLayoutManager

        houseSearchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(text: String): Boolean {
                searchInList(text)

                return false
            }

            override fun onQueryTextChange(text: String): Boolean {
                searchInList(text)

                return false
            }
        })

        add_button.setOnClickListener {
            (activity as MainActivity).goToAddFragment(AddHouseFragment(this))
        }
    }

    fun searchInList(queryText: String){
        val filteredList = mutableListOf<Housing>()
        for (housing in houseList){
            if (housing.address.contains(queryText, true)){
                filteredList.add(housing)
            }
        }

        recyclerAdapter.dataset = filteredList
        recyclerAdapter.notifyDataSetChanged()
    }

    override fun deleteListener(housingBeGone: Housing) {
        houseList.remove(housingBeGone)

        searchInList(houseSearchBar.query.toString())
    }

    override fun addListener(addThatHouse: Housing) {
        houseList.add(addThatHouse)

        searchInList(houseSearchBar.query.toString())

        (activity as MainActivity).onBackPressed()
    }

}