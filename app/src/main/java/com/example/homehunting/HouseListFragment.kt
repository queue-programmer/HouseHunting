package com.example.homehunting

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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

    lateinit var inputPriceFrom: EditText
    lateinit var inputPriceTo: EditText
    lateinit var filterButton: Button
    lateinit var addButton: Button

    lateinit var recyclerView: RecyclerView
    lateinit var houseSearchBar: SearchView


    private lateinit var recyclerAdapter: HousingListAdapter
    private lateinit var recyclerLayoutManager: RecyclerView.LayoutManager

    var listOfHouseObjects = mutableListOf<Housing>( )


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_housing_list, container, false)

        houseSearchBar = view.housing_search_view
        recyclerView = view.houseList_RecyclerView
        inputPriceFrom = view.filterPriceFrom_inputView
        inputPriceTo = view.filterPriceTo_inputView

        filterButton = view.filter_button
        addButton = view.add_button

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateHousingList(houseList)

        recyclerAdapter = HousingListAdapter(listOfHouseObjects,this)
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
        setOnClickListeners()
    }

    fun setOnClickListeners(){

        addButton.setOnClickListener {
            (activity as MainActivity).goToAddFragment(AddHouseFragment(this))
        }
        filterButton.setOnClickListener {
            updateHousingList(getFilteredPriceList(inputPriceFrom.text.toString().toIntOrNull(),
                inputPriceTo.text.toString().toIntOrNull()))
            searchInList(houseSearchBar.query.toString())
        }
    }

    fun updateHousingList(updatedList: MutableList<Housing>){
        listOfHouseObjects.clear()
        updatedList.forEach{
            listOfHouseObjects.add(it)
        }
    }

    fun searchInList(queryText: String){
        val filteredList = mutableListOf<Housing>()
        for (housing in listOfHouseObjects){
            if (housing.address.contains(queryText, true)){
                filteredList.add(housing)
            }
        }

        recyclerAdapter.dataset = filteredList
        recyclerAdapter.notifyDataSetChanged()
    }

    fun getFilteredPriceList(fromPrice: Int?, toPrice: Int?): MutableList<Housing>{
        val filteredPriceList = mutableListOf<Housing>()
        for (housing in houseList){

            if (fromPrice != null && toPrice != null){
                if (fromPrice <= housing.price && toPrice >= housing.price){
                    filteredPriceList.add(housing)
                }
            }else if (fromPrice == null && toPrice != null){
                if (toPrice >= housing.price){
                    filteredPriceList.add(housing)
                }
            }else if (fromPrice != null && toPrice == null){
                if (fromPrice <= housing.price){
                    filteredPriceList.add(housing)
                }
            }else{
                filteredPriceList.add(housing)
            }
        }
        return filteredPriceList
    }

    override fun deleteListener(housingBeGone: Housing) {
        houseList.remove(housingBeGone)
        listOfHouseObjects.remove(housingBeGone)

        searchInList(houseSearchBar.query.toString())


    }

    override fun addListener(addThatHouse: Housing) {
        houseList.add(addThatHouse)

        searchInList(houseSearchBar.query.toString())

        (activity as MainActivity).onBackPressed()
    }

}