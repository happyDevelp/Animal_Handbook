package com.example.animalhandbook.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animalhandbook.Adapter.WelcomeAdapter
import com.example.animalhandbook.DB.DataBase
import com.example.animalhandbook.DB.TypesEntity
import com.example.animalhandbook.databinding.FragmentWelcomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class WelcomeFragment : Fragment() {
    private lateinit var binding: FragmentWelcomeBinding
    private lateinit var db: DataBase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentWelcomeBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.testBtn.setOnClickListener {
            findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToInsideAnimalFragment())
        }

        db = DataBase.getInstance(requireContext())

        CoroutineScope(Dispatchers.Main).launch {

            val typesList = getAllTypes()
            val adapter = WelcomeAdapter()
            binding.recycleViewWelcomeScreen.adapter = adapter

            typesList.observe(viewLifecycleOwner) {
                adapter.submitList(it)

            }
        }
    }

    private suspend fun pushType(type: TypesEntity) {
        return withContext(Dispatchers.IO) {
            db.typesDAO.insertType(type)
        }
    }

    private suspend fun getAllTypes(): LiveData<List<TypesEntity>> {
        return withContext(Dispatchers.IO) {
            db.typesDAO.getAllTypes()
        }
    }
}