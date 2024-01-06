/*
package com.example.animalhandbook.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.animalhandbook.Fragments.Tab.FavouritesFragment
import com.example.animalhandbook.Fragments.Tab.SettingsFragment
import com.example.animalhandbook.Fragments.WelcomeFragment

class TabPageAdapter(activity: FragmentActivity, private val tabCount: Int): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = tabCount

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> FavouritesFragment()
            1 -> WelcomeFragment()
            2 -> SettingsFragment()
            else -> WelcomeFragment()
        }
    }

}*/
