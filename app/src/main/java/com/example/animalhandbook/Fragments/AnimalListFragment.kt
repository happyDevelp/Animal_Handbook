package com.example.animalhandbook.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.animalhandbook.R
import com.example.animalhandbook.databinding.FragmentAnimalListBinding
import com.example.animalhandbook.databinding.FragmentWelcomeBinding

class AnimalListFragment : Fragment() {
    private lateinit var binding: FragmentAnimalListBinding
    //AnimalListFragmentArgs this class generate automatically cuz I created an argument in navigation. Safe Args make everything instead of us
    private val args: AnimalListFragmentArgs by navArgs()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAnimalListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = args.id

    }


}