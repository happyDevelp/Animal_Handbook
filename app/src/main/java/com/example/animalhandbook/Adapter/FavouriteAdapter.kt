package com.example.animalhandbook.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.animalhandbook.DB.animals.AnimalEntity
import com.example.animalhandbook.R

class FavouriteAdapter: ListAdapter<AnimalEntity, FavouriteAdapter.ViewHolder>(AnimalDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favourite_list, parent, false)
        return ViewHolder(view, myListener, myStarListener) /*set anonymous object from AnimalListFragment to each element of RW. It means that each element of RW is listener (so element wait for a click)*/
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    class ViewHolder(view: View, clickListener: OnItemClickListener, starClickListener: OnStarClickListener): RecyclerView.ViewHolder(view){
        private val name: TextView = view.findViewById(R.id.text_fav)
        private val image: ImageView = view.findViewById(R.id.pic_fav)
        private val is_favourite_image: ImageView = itemView.findViewById(R.id.star_fav)

        fun bind(item: AnimalEntity){
            name.text = item.name
            val animalImageResource = itemView.resources.getIdentifier(item.picName, "drawable", itemView.context.packageName)
            image.setImageResource(animalImageResource)
            is_favourite_image.setImageResource(R.drawable.star_fav)
        }

        init {
            // So when I click RW element it passing a name of clicked animal
            view.setOnClickListener { clickListener.onItemClick(name.text.toString()) }
            is_favourite_image.setOnClickListener { starClickListener.onStarClick(name.text.toString()) }
        }
    }

    class AnimalDiffCallBack : DiffUtil.ItemCallback<AnimalEntity>() {
        override fun areItemsTheSame(oldItem: AnimalEntity, newItem: AnimalEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AnimalEntity, newItem: AnimalEntity): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener { fun onItemClick(name: String) }

    private lateinit var myListener: OnItemClickListener
    fun setOnItemClickListener(clickListener: OnItemClickListener) {
        myListener = clickListener
    }


    interface OnStarClickListener { fun onStarClick(name: String) }

    private lateinit var myStarListener: OnStarClickListener
    fun setOnStarClickListener(starClickListener: OnStarClickListener){
        myStarListener = starClickListener
    }

}