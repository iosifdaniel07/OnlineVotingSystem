package com.example.androidapponlinevotingsystem.main

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.androidapponlinevotingsystem.R
import com.example.androidapponlinevotingsystem.data.CandidateGet
import com.example.androidapponlinevotingsystem.serverProblemActivity.ImageConvertor
import java.util.*

class ItemAdapter(private val candidatesList:List<CandidateGet>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){


    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    inner class ItemViewHolder(view: View, listener: onItemClickListener) : RecyclerView.ViewHolder(view){

          var name:TextView
          var image:ImageView
          var description:TextView
          var imageBackround: ImageView

          init {
              name = view.findViewById(R.id.itemtextViewNume)
              image = view.findViewById(R.id.itemimagePerson)
              description = view.findViewById(R.id.itemtextViewDescriere)
              imageBackround = view.findViewById(R.id.imageViewBackground)

              view.setOnClickListener {
                  listener.onItemClick(adapterPosition)
              }
          }

        fun setBackground(){
            imageBackround.setImageResource(R.drawable.ic_launcher_background2)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ItemViewHolder(itemView, mListener)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val curentItem = candidatesList[position]
         holder.name.text = curentItem.name
         holder.description.text = curentItem.description
         holder.image.setImageBitmap(ImageConvertor.getBitmapImage(Base64.getDecoder().decode(curentItem.image.toByteArray())))
         if(position % 2 == 0){
             holder.setBackground()
         }

    }

    override fun getItemCount(): Int {
       return candidatesList.size
    }

    fun getCandidate(position: Int): CandidateGet{
        return candidatesList[position]
    }

}