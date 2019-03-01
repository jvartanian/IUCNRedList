package com.vartanian.android.iucnredlist.ui.countrydetail

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
import com.vartanian.android.iucnredlist.data.redlist.Country
import com.vartanian.android.iucnredlist.ui.SettableTitle
import kotlinx.android.synthetic.main.country_detail_fragment.*

class CountryDetailFragment : Fragment() {

    companion object {

        private const val KEY_COUNTRY_NAME = "key_country_name"
        private const val KEY_ISO_CODE = "key_iso_code"

        fun newInstance(country: Country): CountryDetailFragment {
            val fragment = CountryDetailFragment()
            val args = Bundle()
            args.putString(KEY_COUNTRY_NAME, country.country)
            args.putString(KEY_ISO_CODE, country.isocode)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: CountryDetailViewModel
    private lateinit var countryDetailAdapter: CountryDetailAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (activity is SettableTitle) {
            (activity as SettableTitle).setTitle(getString(R.string.app_name) + " - ${arguments!!.getString(KEY_COUNTRY_NAME)}")
        }

        return inflater.inflate(R.layout.country_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countryDetailAdapter = CountryDetailAdapter()

        countryDetailRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)

            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

            adapter = countryDetailAdapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CountryDetailViewModel::class.java)

        viewModel.listSpecies(arguments!!.getString(KEY_ISO_CODE)!!).observe(this, Observer {
            countryDetailProgressBar.visibility = View.GONE
            countryDetailRecyclerView.visibility = View.VISIBLE

            if (it?.errorMessage?.isNotEmpty() == true) {
                Toast.makeText(context, it.errorMessage, Toast.LENGTH_LONG).show()
            } else {
                countryDetailAdapter.speciesList.clear()
                countryDetailAdapter.speciesList.addAll(it!!.result)
                countryDetailAdapter.speciesList.sortBy { species -> species.scientificName }
                countryDetailAdapter.notifyDataSetChanged()
            }
        })
    }
}