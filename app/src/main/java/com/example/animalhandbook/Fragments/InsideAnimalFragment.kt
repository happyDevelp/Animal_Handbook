package com.example.animalhandbook.Fragments

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.animalhandbook.R
import com.example.animalhandbook.databinding.FragmentInsideAnimalBinding

class InsideAnimalFragment : Fragment() {
    private lateinit var binding: FragmentInsideAnimalBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentInsideAnimalBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

/*        val string = getString(R.string.brown_bear_description)
        binding.describeAnimalInside.text = Html.fromHtml(string, Html.FROM_HTML_MODE_COMPACT)*/


    }



}