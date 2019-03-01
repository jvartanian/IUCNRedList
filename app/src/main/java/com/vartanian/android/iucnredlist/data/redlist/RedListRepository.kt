package com.vartanian.android.iucnredlist.data.redlist

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class RedListRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://apiv3.iucnredlist.org/api/v3/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
        .build()

    private val redListService = retrofit.create(RedListService::class.java)

    fun listCountries(): Flowable<ListCountriesResponse> {
        return redListService.listCountries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn {
                ListCountriesResponse(0, emptyList(), it.message ?: "Error fetching list of countries")
            }
    }
}

@Serializable
data class ListCountriesResponse(val count: Int, val results: List<Country>, @Optional var errorMessage: String = "")

@Serializable
data class Country(val country: String, val isocode: String)