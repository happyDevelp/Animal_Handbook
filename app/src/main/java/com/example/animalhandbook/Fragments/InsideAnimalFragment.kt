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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentInsideAnimalBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = args.id
        db = DataBase.getInstance(requireContext())


        CoroutineScope(Dispatchers.Main).launch{
            val currentAnimal = getAnimalByID(id)

            binding.nameAnimalInside.text = currentAnimal.name
            binding.describeAnimalInside.text = currentAnimal.description
            val imageResource = resources.getIdentifier(currentAnimal.picName, "drawable", context?.packageName)
            binding.imageAnimalInsideFragment.setImageResource(imageResource)

        }

    }

    private suspend fun getAnimalByID(id: Int): AnimalEntity {
        return withContext(Dispatchers.IO){
            db.animalDAO.getByID(id)
        }

    }
}