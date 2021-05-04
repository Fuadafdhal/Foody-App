package com.afdhal_studio.foody.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 *Created by Muh Fuad Afdhal on 05/05/2021
 */

class PagerAdapter(
    private val resultBundle: Bundle,
    private val fragment: ArrayList<Fragment>,
    private val title: ArrayList<String>,
    fm: FragmentManager
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return fragment.size
    }

    override fun getItem(position: Int): Fragment {
        fragment[position].arguments = resultBundle
        return fragment[position]
    }

    override fun getPageTitle(position: Int): CharSequence {
        return title[position]
    }
}