package com.vartanian.android.iucnredlist.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vartanian.android.iucnredlist.R
import com.vartanian.android.iucnredlist.ui.countrylist.CountryListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!isFinishing) {
            setContentView(R.layout.activity_main)

            setSupportActionBar(mainToolbar)

            if (savedInstanceState == null) {
                val countryFragment = CountryListFragment.newInstance()
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.mainContainer, countryFragment, CountryListFragment::class.simpleName)
                    .commit()
            }
        }
    }
}
