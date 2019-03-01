package com.vartanian.android.iucnredlist.data.redlist

import io.reactivex.Flowable
import retrofit2.http.GET

interface RedListService {

    @GET("country/list?token=$API_KEY")
    fun listCountries(): Flowable<ListCountriesResponse>

    companion object {
        const val API_KEY = "9bb4facb6d23f48efbf424bb05c0c1ef1cf6f468393bc745d42179ac4aca5fee"
    }
}