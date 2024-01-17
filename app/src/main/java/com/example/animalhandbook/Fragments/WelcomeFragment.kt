package com.example.animalhandbook.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import com.example.animalhandbook.Adapter.WelcomeAdapter
import com.example.animalhandbook.DB.DataBase
import com.example.animalhandbook.DB.types.TypesEntity
import com.example.animalhandbook.databinding.FragmentWelcomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WelcomeFragment : Fragment() {
    private lateinit var binding: FragmentWelcomeBinding
    private lateinit var db: DataBase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentWelcomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = DataBase.getInstance(requireContext())

        val adapter = WelcomeAdapter()
        binding.recycleViewWelcomeScreen.adapter = adapter

        setupItemClickListener(adapter)

        CoroutineScope(Dispatchers.Main).launch {
            val typesList = getAllTypes()
            typesList.observe(viewLifecycleOwner) { adapter.submitList(it) }
        }

    }

    private fun setupItemClickListener(adapter: WelcomeAdapter){
        adapter.setOnItemClickListener(object : WelcomeAdapter.onItemClickListener{ //object : WelcomeAdapter.onItemClickListener - create anonymous object (створення анонімного об'єкту)
            override fun onItemClick(position: Int) {
                CoroutineScope(Dispatchers.Main).launch {

                    val currentType = getTypeByID(position)
                    findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToAnimalListFragment(currentType.name))
                }
            }
        })
    }

    private suspend fun getAllTypes(): LiveData<List<TypesEntity>> {
        return withContext(Dispatchers.IO) {
            db.typesDAO.getAllTypes()
        }
    }

    private suspend fun getTypeByID(id: Int): TypesEntity {
        return withContext(Dispatchers.IO) {
            db.typesDAO.getByID(id)
        }
    }


}