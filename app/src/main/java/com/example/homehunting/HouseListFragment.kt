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
import kotlinx.android.synthetic.main.fragment_housing_list.view.*
import kotlinx.android.synthetic.main.layout_custom.*

class HouseListFragment(var houseList: MutableList<Housing>): Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var houseSearchBar: SearchView

    private lateinit var recyclerAdapter: HousingListAdapter
    private lateinit var recyclerLayoutManager: RecyclerView.LayoutManager
    private lateinit var housingAdapter: HousingListAdapter



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_housing_list, container, false)

        houseSearchBar = view.housing_search_view
        recyclerView = view.houseList_RecyclerView

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        housingAdapter = HousingListAdapter(
            houseList
        )

        recyclerAdapter = HousingListAdapter(houseList)
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

//        houseSearchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(text: String): Boolean {
//                filterList(text)
//
//                return false
//            }
//
//            override fun onQueryTextChange(text: String): Boolean {
//                filterList(text)
//
//                return false
//            }
//        })
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

//    fun filterList(queryText: String) {
//        val filteredList = mutableListOf<String>()
//
//        for (Housing in fullHousingList) {
//            if (Housing.contains(queryText, true)) {
//                filteredList.add(Housing)
//            }
//        }
//        recyclerAdapter. = filteredList
//        recyclerAdapter.notifyDataSetChanged()
//    }
}