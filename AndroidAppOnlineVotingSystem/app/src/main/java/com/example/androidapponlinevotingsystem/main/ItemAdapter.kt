package com.example.androidapponlinevotingsystem.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidapponlinevotingsystem.R
import com.example.androidapponlinevotingsystem.data.Candidates
import kotlin.collections.ArrayList

class ItemAdapter(private val candidatesList:ArrayList<Candidates>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view){

          var name:TextView
          var image:ImageView
          var description:TextView

          init {
              name = view.findViewById(R.id.itemtextViewNume)
              image = view.findViewById(R.id.itemimagePerson)
              description = view.findViewById(R.id.itemtextViewDescriere)
          }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val curentItem = candidatesList[position]
         holder.name.text = curentItem.fname
         holder.description.text = curentItem.shortDescription
        holder.image.setImageResource(curentItem.image)

    }

    override fun getItemCount(): Int {
       return candidatesList.size
    }

}