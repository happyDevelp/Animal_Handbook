package com.example.animalhandbook.Adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.animalhandbook.DB.animals.AnimalEntity
import com.example.animalhandbook.R

class FavouriteAdapter: RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {
    var data = listOf<AnimalEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val name: TextView = itemView.findViewById(R.id.animal_name_inside)
        private val description: TextView = itemView.findViewById(R.id.animal_describe_inside)
        private val image: ImageView = itemView.findViewById(R.id.animal_image_inside)
        private val is_favourite: ImageView = itemView.findViewById(R.id.animal_image_inside)


    }
}