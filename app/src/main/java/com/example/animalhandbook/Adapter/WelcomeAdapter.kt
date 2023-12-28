package com.example.animalhandbook.Adapter

import android.app.Application
import android.content.Context
import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Database
import com.example.animalhandbook.DB.DataBase
import com.example.animalhandbook.DB.TypesDAO
import com.example.animalhandbook.DB.TypesEntity
import com.example.animalhandbook.R
import com.example.animalhandbook.databinding.FragmentWelcomeBinding

class WelcomeAdapter(): RecyclerView.Adapter<WelcomeAdapter.ViewHolder>() {
    var data = listOf<TypesEntity>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_welcome_recycleview, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = data[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val name: TextView = itemView.findViewById(R.id.name_of_animal_welcome)
        private val description: TextView = itemView.findViewById(R.id.description_of_animal_welcome)
        private val image: ImageView = itemView.findViewById(R.id.image_animal_welcome)

        fun bind(item: TypesEntity){
            name.text = item.name
            description.text = item.description
            val imageResource = itemView.resources.getIdentifier(item.picName, "drawable", itemView.context.packageName)
            image.setImageResource(imageResource)

        }
    }

}