package com.example.animalhandbook.Fragments

import android.os.Bundle
import android.provider.DocumentsContract.Root
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.animalhandbook.Adapter.AnimalAdapter
import com.example.animalhandbook.DB.DataBase
import com.example.animalhandbook.DB.animals.AnimalEntity
import com.example.animalhandbook.databinding.FragmentAnimalListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class AnimalListFragment : Fragment() {
    private lateinit var binding: FragmentAnimalListBinding
    private lateinit var db: DataBase
    private val args: AnimalListFragmentArgs by navArgs()
    private lateinit var searchView: SearchView
    private lateinit var adapter: AnimalAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAnimalListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = DataBase.getInstance(requireContext())

        adapter = AnimalAdapter()
        binding.recycleViewAnimal.adapter = adapter

        setupOnItemClickListener(adapter)

        searchView = binding.searchViewAnimalList
        setupSearchListener()

        CoroutineScope(Dispatchers.Main).launch {
            val animalList = getAllAnimalByType(args.type)
            animalList.observe(viewLifecycleOwner) { adapter.submitList(it) }
        }

    }

    private fun setupOnItemClickListener(adapter: AnimalAdapter) {
        adapter.setOnItemClickListener(object : AnimalAdapter.onItemClickListener{ //object : WelcomeAdapter.onItemClickListener - create anonymous object (створення анонімного об'єкту)
            override fun onItemClick(name: String) {
                CoroutineScope(Dispatchers.Main).launch {
                    findNavController().navigate(AnimalListFragmentDirections.actionAnimalListFragmentToInsideAnimalFragment(name))
                }
            }
        })
    }

    private fun setupSearchListener() {
        searchView.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                filterList(query)
                return false }

            override fun onQueryTextChange(newText: String): Boolean {
                filterList(newText)
                return true
            }

        })
    }

    fun filterList(newText: String) {
        val filteredList = ArrayList<AnimalEntity>()
        for (animal in adapter.currentList){
            if (animal.name.lowercase().contains(newText.lowercase())){
                filteredList.add(animal)
            }
        }
         adapter.submitList(filteredList)
    }

    private suspend fun getAllAnimalByType(type: String): LiveData<List<AnimalEntity>> {
        return withContext(Dispatchers.IO){
            db.animalDAO.getAllAnimalByType(type)
        }

    }
}