package com.example.animalhandbook.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.animalhandbook.DB.TypesEntity
import com.example.animalhandbook.R


class WelcomeAdapter: androidx.recyclerview.widget.ListAdapter<TypesEntity, WelcomeAdapter.ViewHolder>(TypesDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }


    class ViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        private val name: TextView = itemView.findViewById(R.id.name_of_animal_welcome)
        private val description: TextView = itemView.findViewById(R.id.description_of_animal_welcome)
        private val image: ImageView = itemView.findViewById(R.id.image_animal_welcome)

        fun bind(item: TypesEntity){
            name.text = item.name
            description.text = item.description
            val imageResource = itemView.resources.getIdentifier(item.picName, "drawable", itemView.context.packageName)
            image.setImageResource(imageResource)

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_welcome_recycleview, parent, false)
                return ViewHolder(view)
            }
        }


    }

}

class TypesDiffCallBack: DiffUtil.ItemCallback<TypesEntity>(){
    override fun areItemsTheSame(oldItem: TypesEntity, newItem: TypesEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TypesEntity, newItem: TypesEntity): Boolean {
        return oldItem == newItem
    }

}