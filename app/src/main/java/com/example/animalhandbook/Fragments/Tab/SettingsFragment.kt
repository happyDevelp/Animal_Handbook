package com.example.animalhandbook.Fragments.Tab

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.animalhandbook.R
import com.example.animalhandbook.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.instImageView.setOnClickListener {
            openUrl("https://www.instagram.com/valtornist?igsh=MXVucnV6ODB4YXFxNw==")
        }

        binding.linkedInImageView.setOnClickListener {
            openUrl("https://www.linkedin.com/in/oles-malysh-3bb888257/")
        }

        binding.gitHubImageView.setOnClickListener {
            openUrl("https://github.com/happyDevelp")
        }
    }

    private fun openUrl(url: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)

        startActivity(intent)
    }

}