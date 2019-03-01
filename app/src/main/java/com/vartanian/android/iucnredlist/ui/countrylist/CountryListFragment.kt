package com.vartanian.android.iucnredlist.ui.countrylist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.vartanian.android.iucnredlist.R
import kotlinx.android.synthetic.main.country_list_fragment.*

class CountryListFragment : Fragment() {

    companion object {
        fun newInstance() = CountryListFragment()
    }

    private lateinit var viewModel: CountryListViewModel
    private lateinit var countryListAdapter: CountryListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.country_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countryListAdapter = CountryListAdapter()

        listCountriesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)

            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

            adapter = countryListAdapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CountryListViewModel::class.java)

        viewModel.listCourses().observe(this, Observer {
            if (it?.errorMessage?.isNotEmpty() == true) {
                Toast.makeText(context, it.errorMessage, Toast.LENGTH_LONG).show()
            } else {
                countryListAdapter.countryList.clear()
                countryListAdapter.countryList.addAll(it!!.results)
                countryListAdapter.countryList.sortBy { country -> country.country }
                countryListAdapter.notifyDataSetChanged()
            }
        })
    }

}
