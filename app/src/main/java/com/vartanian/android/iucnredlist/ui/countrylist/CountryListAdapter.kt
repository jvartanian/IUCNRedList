package com.vartanian.android.iucnredlist.ui.countrylist

import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.TextView
import com.vartanian.android.iucnredlist.data.redlist.Country

class CountryListAdapter() : RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    val countryList = mutableListOf<Country>()

    class CountryViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val textView = TextView(parent.context)

        val padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, parent.context.resources.displayMetrics).toInt()
        textView.setPadding(padding, padding, padding, padding)

        return CountryViewHolder(textView)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.textView.text = countryList[position].country
    }

    override fun getItemCount(): Int {
        return countryList.size
    }
}