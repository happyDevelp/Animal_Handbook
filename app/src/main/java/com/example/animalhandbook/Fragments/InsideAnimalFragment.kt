package com.example.animalhandbook.Fragments

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.animalhandbook.DB.DataBase
import com.example.animalhandbook.DB.animals.AnimalEntity
import com.example.animalhandbook.R
import com.example.animalhandbook.databinding.FragmentInsideAnimalBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InsideAnimalFragment : Fragment() {
    private lateinit var binding: FragmentInsideAnimalBinding
    private lateinit var db: DataBase

    //AnimalListFragmentArgs this class generate automatically cuz I created an argument in navigation. Safe Args make everything instead of us
    private val args: InsideAnimalFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentInsideAnimalBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = args.name
        db = DataBase.getInstance(requireContext())
         firstSetupUI(name)

        binding.imageStarState.setOnClickListener { starState(name) }

    }


    private fun starState(name: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val currentAnimal = getAnimalByName(name)
            if (!currentAnimal.isFavourite) {
                setStarState(1, currentAnimal.name)
                binding.imageStarState.setImageResource(R.drawable.star_fav)
            } else {
                setStarState(0, currentAnimal.name)
                binding.imageStarState.setImageResource(R.drawable.star_notfav)
            }
        }
    }

    private fun firstSetupUI(name: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val currentAnimal = getAnimalByName(name)
            binding.nameAnimalInside.text = currentAnimal.name
            binding.describeAnimalInside.text = currentAnimal.description
            val imageResource =
                resources.getIdentifier(currentAnimal.picName, "drawable", context?.packageName)
            binding.imageAnimalInsideFragment.setImageResource(imageResource)
            binding.imageStarState.setImageResource(if (currentAnimal.isFavourite == true) R.drawable.star_fav else R.drawable.star_notfav)
        }
    }

    private suspend fun getAnimalByID(id: Int): AnimalEntity {
        return withContext(Dispatchers.IO) {
            db.animalDAO.getAnimalByID(id)
        }
    }

    private suspend fun setStarState(state: Int, name: String) {
        return withContext(Dispatchers.IO) {
            db.animalDAO.setStarState(state, name)
        }
    }

    private suspend fun getAnimalByName(name: String): AnimalEntity {
        return withContext(Dispatchers.IO) {
            db.animalDAO.getAnimalByName(name)
        }
    }


}