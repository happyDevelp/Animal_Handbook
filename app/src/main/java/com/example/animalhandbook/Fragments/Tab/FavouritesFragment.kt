package com.example.animalhandbook.Fragments.Tab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.animalhandbook.R
import com.example.animalhandbook.databinding.FragmentFavouritesBinding

class FavouritesFragment : Fragment() {
    lateinit var binding: FragmentFavouritesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentFavouritesBinding.inflate(layoutInflater)

        binding.favTestButton.setOnClickListener {
            findNavController().navigate(FavouritesFragmentDirections.actionFavouritesFragmentToWelcomeFragment())
        }


        return binding.root
    }


}