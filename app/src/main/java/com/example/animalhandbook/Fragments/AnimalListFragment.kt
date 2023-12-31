package com.example.animalhandbook.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.animalhandbook.Adapter.AnimalAdapter
import com.example.animalhandbook.Adapter.WelcomeAdapter
import com.example.animalhandbook.DB.DataBase
import com.example.animalhandbook.DB.animals.AnimalEntity
import com.example.animalhandbook.R
import com.example.animalhandbook.databinding.FragmentAnimalListBinding
import com.example.animalhandbook.databinding.FragmentWelcomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnimalListFragment : Fragment() {
    private lateinit var binding: FragmentAnimalListBinding
    private lateinit var db: DataBase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAnimalListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = DataBase.getInstance(requireContext())

        CoroutineScope(Dispatchers.Main).launch {
            val animalList = getAllAnimals()

            val adapter = AnimalAdapter()
            binding.recycleViewAnimal.adapter = adapter

            animalList.observe(viewLifecycleOwner) { adapter.submitList(it) }

            adapter.setOnItemClickListener(object : AnimalAdapter.onItemClickListener{ //object : WelcomeAdapter.onItemClickListener - create anonymous object (створення анонімного об'єкту)
                override fun onItemClick(position: Int) {
                    CoroutineScope(Dispatchers.Main).launch {
                        findNavController().navigate(AnimalListFragmentDirections.actionAnimalListFragmentToInsideAnimalFragment(position))

                    }
                }

            })
        }

    }

    private suspend fun getAllAnimals(): LiveData<List<AnimalEntity>> {
        return withContext(Dispatchers.IO) {
            db.animalDAO.getAllAnimals()
        }
    }

    private suspend fun getAnimalByID(id: Int): AnimalEntity {
        return withContext(Dispatchers.IO) {
            db.animalDAO.getByID(id)
        }
    }


}