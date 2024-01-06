package com.example.animalhandbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.animalhandbook.Fragments.AnimalListFragment
import com.example.animalhandbook.Fragments.InsideAnimalFragment
import com.example.animalhandbook.Fragments.Tab.FavouritesFragment
import com.example.animalhandbook.Fragments.Tab.SettingsFragment
import com.example.animalhandbook.Fragments.WelcomeFragment
import com.example.animalhandbook.Fragments.WelcomeFragmentDirections
import com.example.animalhandbook.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.nio.file.attribute.FileAttributeView

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_favourites -> replace(FavouritesFragment())
                R.id.navigation_home -> replace(WelcomeFragment())
                R.id.navigation_settings -> replace(SettingsFragment())
            }
            true
        }

    }

    private fun replace(fragment: Fragment) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.nav_host_fragment, fragment)
            fragmentTransaction.commit()

    }


}
