package com.vartanian.android.iucnredlist.ui.countrydetail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.ViewModel
import com.vartanian.android.iucnredlist.data.redlist.RedListRepository
import com.vartanian.android.iucnredlist.data.redlist.SpeciesByCountryResponse

class CountryDetailViewModel : ViewModel() {

    private val redListRepository = RedListRepository()
    private var speciesLiveData: LiveData<SpeciesByCountryResponse>? = null

    fun listSpecies(isocode: String): LiveData<SpeciesByCountryResponse> {
        if (speciesLiveData == null) {
            speciesLiveData = LiveDataReactiveStreams.fromPublisher(redListRepository.listSpeciesByCountry(isocode))
        }

        return speciesLiveData!!
    }

}