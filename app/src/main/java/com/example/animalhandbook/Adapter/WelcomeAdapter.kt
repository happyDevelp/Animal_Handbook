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
 //indicate who clicked (from which fragment). We set it in next method setOnItemClickListener()
class WelcomeAdapter :
    androidx.recyclerview.widget.ListAdapter<TypesEntity, WelcomeAdapter.ViewHolder>(TypesDiffCallBack()) {

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }
     lateinit var myListener: onItemClickListener


    //Отже коли я викликаю об'єкт цього інтерфейсу, він знає (clickListener) інформацію з якого саме фрагменту я був викликаний
    fun setOnItemClickListener(clickListener: onItemClickListener) {
        myListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_welcome_recycleview, parent, false)

        return ViewHolder(view, myListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }


    class ViewHolder (itemView: View, clickListener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.name_of_animal_welcome)
        private val description: TextView = itemView.findViewById(R.id.description_of_animal_welcome)
        private val image: ImageView = itemView.findViewById(R.id.image_animal_welcome)


        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }

        fun bind(item: TypesEntity) {
            name.text = item.name
            description.text = item.description
            val imageResource = itemView.resources.getIdentifier(item.picName, "drawable", itemView.context.packageName)
            image.setImageResource(imageResource)

        }

              /*  companion object {
                    fun from(parent: ViewGroup): ViewHolder {
                        val layoutInflater = LayoutInflater.from(parent.context)
                        val view = layoutInflater.inflate(R.layout.item_welcome_recycleview, parent, false)

                        return ViewHolder(view, myListener)
                    }
                }*/

    }

}

class TypesDiffCallBack : DiffUtil.ItemCallback<TypesEntity>() {
    override fun areItemsTheSame(oldItem: TypesEntity, newItem: TypesEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TypesEntity, newItem: TypesEntity): Boolean {
        return oldItem == newItem
    }

}