package com.example.animalhandbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.app.TaskStackBuilder
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
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
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomView = binding.bottomNavigation

        //Link to XML file where NavHostFragment is located
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottomView.setupWithNavController(navController)



        //try to implement when tap second time on the same destination
       /* navController.addOnDestinationChangedListener{destination ->
            if ( == )
        }*/

    }


}
