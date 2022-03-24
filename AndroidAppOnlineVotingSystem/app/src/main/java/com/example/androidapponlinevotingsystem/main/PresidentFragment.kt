package com.example.androidapponlinevotingsystem.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidapponlinevotingsystem.R
import com.example.androidapponlinevotingsystem.data.Candidates
import com.example.androidapponlinevotingsystem.databinding.FragmentPresidentBinding
import com.example.androidapponlinevotingsystem.databinding.FragmentSignupBinding

class PresidentFragment() : Fragment() {

    private var _binding: FragmentPresidentBinding? = null
    private val binding get() = _binding!!
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var candidateDataList: ArrayList<Candidates>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPresidentBinding.inflate(inflater, container, false)

        mRecyclerView = binding.root.findViewById(R.id.recyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(context)

        candidateDataList = ArrayList<Candidates>()
        var a:Candidates =   Candidates("fffff","hhhhh","jjjjjj",R.drawable.logo)
        var b: Candidates =  Candidates("fffff","hhhhh","jjjjjj",R.drawable.logo)
        var c: Candidates =  Candidates("fffff","hhhhh","jjjjjj",R.drawable.logo)
        var d: Candidates =  Candidates("fffff","hhhhh","jjjjjj",R.drawable.logo)
        candidateDataList.add(a)
        candidateDataList.add(b)
        candidateDataList.add(c)
        candidateDataList.add(d)

        mRecyclerView.adapter = ItemAdapter(candidateDataList)

        return binding.root
    }

}