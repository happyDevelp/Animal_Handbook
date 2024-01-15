package com.example.animalhandbook.Fragments.Tab

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import com.example.animalhandbook.Adapter.FavouriteAdapter
import com.example.animalhandbook.DB.DataBase
import com.example.animalhandbook.DB.animals.AnimalEntity
import com.example.animalhandbook.R
import com.example.animalhandbook.databinding.FragmentFavouritesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouritesFragment : Fragment() {
    private lateinit var binding: FragmentFavouritesBinding
    private lateinit var db: DataBase
    private lateinit var adapter: FavouriteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View {
        binding = FragmentFavouritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = DataBase.getInstance(requireContext())
        adapter = FavouriteAdapter()
        binding.RecycleViewFavourites.adapter = adapter

        updateRW()

        //ClickHandle Methods
        clickHandle(adapter)
        clickStarHandle(adapter)



    }

    private fun updateRW() {
        CoroutineScope(Dispatchers.Main).launch {
            val animalList = getAllFavouriteAnimal(1)
            animalList.observe(viewLifecycleOwner) {

                binding.apply {
                    if (animalList.value?.size == 0) textNoData.visibility = View.VISIBLE
                    else textNoData.visibility = View.INVISIBLE }

                adapter.submitList(it)
            }

        }
    }

    private fun clickHandle(adapter: FavouriteAdapter) {
        adapter.setOnItemClickListener(object : FavouriteAdapter.OnItemClickListener{

            override fun onItemClick(name: String) {
                CoroutineScope(Dispatchers.Main).launch {
                    findNavController().navigate(FavouritesFragmentDirections.actionFavouritesFragmentToInsideAnimalFragment(name))
                }
            }
        })
    }

    private fun clickStarHandle(adapter: FavouriteAdapter) {
        adapter.setOnStarClickListener(object : FavouriteAdapter.OnStarClickListener{

            override fun onStarClick(name: String) {
                CoroutineScope(Dispatchers.Main).launch { setStarState(0, name) }
                Toast.makeText(requireContext(), "$name was removed", Toast.LENGTH_LONG).show()
            }
        })
    }

    private suspend fun getAllFavouriteAnimal(state: Int): LiveData<List<AnimalEntity>> {
        return withContext(Dispatchers.IO){
            db.animalDAO.getAllFavourite(state)
        }
    }

    private suspend fun setStarState(state: Int, name: String) {
        return withContext(Dispatchers.IO){
            db.animalDAO.setStarState(state, name)
        }
    }



}