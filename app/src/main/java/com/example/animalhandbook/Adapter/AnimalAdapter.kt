package com.example.animalhandbook.Adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.animalhandbook.DB.animals.AnimalEntity
import com.example.animalhandbook.R
import com.example.animalhandbook.databinding.ItemAnimalListBinding
import org.w3c.dom.Text

//indicate who clicked (from which fragment). We set it in next method setOnItemClickListener()
class AnimalAdapter :
    androidx.recyclerview.widget.ListAdapter<AnimalEntity, AnimalAdapter.ViewHolder>(AnimalDiffCallBack()) {
    interface onItemClickListener {
        fun onItemClick(name: String)
    }
    lateinit var myListener: onItemClickListener

    //Отже коли я викликаю об'єкт цього інтерфейсу, він знає (clickListener) інформацію з якого саме фрагменту я був викликаний
    fun setOnItemClickListener(clickListener: onItemClickListener) {
        myListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_animal_list, parent, false)

        return ViewHolder(view, myListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class ViewHolder (itemView: View, clickListener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.animal_name_inside)
        private val description: TextView = itemView.findViewById(R.id.animal_describe_inside)
        private val image: ImageView = itemView.findViewById(R.id.animal_image_inside)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(name.text.toString())
            }
        }

        fun bind(item: AnimalEntity) {
            name.text = item.name
            if(item.description.length >= 50){
                val newDescription = itemView.resources.getString(R.string.trim_text, item.description.substring(0..100))
                description.text = newDescription
            } else description.text = item.description

            val imageResource = itemView.resources.getIdentifier(item.picName, "drawable", itemView.context.packageName)
            image.setImageResource(imageResource)

        /*    if (item.type == "Artiodactyl")
            background.setBackgroundColor(itemView.resources.getColor(R.color.artodactyl, *//*itemView.resources*//*))*/
        }

/*        fun getFilter(): Filter = animalFilter


        private val animalFilter = object: Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList: ArrayList<AnimalEntity> = ArrayList()
                if (constraint.isNullOrEmpty()){
                    var listttt = list
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                TODO("Not yet implemented")
            }

        }*/

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