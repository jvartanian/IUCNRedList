package com.vartanian.android.iucnredlist.ui.countrylist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.ViewModel;
import com.vartanian.android.iucnredlist.data.redlist.ListCountriesResponse
import com.vartanian.android.iucnredlist.data.redlist.RedListRepository

class CountryListViewModel : ViewModel() {

    private val redListRepository = RedListRepository()
    private var countryListLiveData: LiveData<ListCountriesResponse>? = null

    fun listCourses(): LiveData<ListCountriesResponse> {
        if (countryListLiveData == null) {
            countryListLiveData = LiveDataReactiveStreams.fromPublisher(redListRepository.listCountries())
        }

        return countryListLiveData!!
    }
}
