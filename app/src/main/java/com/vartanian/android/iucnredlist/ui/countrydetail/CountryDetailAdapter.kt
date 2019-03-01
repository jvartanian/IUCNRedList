package com.vartanian.android.iucnredlist.ui.countrydetail

import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.TextView
import com.vartanian.android.iucnredlist.data.redlist.Species

class CountryDetailAdapter : RecyclerView.Adapter<CountryDetailAdapter.SpeciesViewHolder>() {

    val speciesList = mutableListOf<Species>()

    class SpeciesViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesViewHolder {
        val textView = TextView(parent.context)

        textView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        val padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, parent.context.resources.displayMetrics).toInt()
        textView.setPadding(padding, padding, padding, padding)

        return SpeciesViewHolder(textView)
    }

    override fun onBindViewHolder(holder: SpeciesViewHolder, position: Int) {
        holder.textView.text = speciesList[position].scientificName
    }

    override fun getItemCount(): Int {
        return speciesList.size
    }
}