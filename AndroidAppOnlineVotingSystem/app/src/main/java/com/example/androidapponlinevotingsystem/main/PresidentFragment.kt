package com.example.androidapponlinevotingsystem.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.TransactionTooLargeException
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.androidapponlinevotingsystem.R
import com.example.androidapponlinevotingsystem.data.*
import com.example.androidapponlinevotingsystem.databinding.FragmentPresidentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PresidentFragment() : Fragment() {

    private var _binding: FragmentPresidentBinding? = null
    private val binding get() = _binding!!
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var candidateList : List<CandidateGet>
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPresidentBinding.inflate(inflater, container, false)

        mRecyclerView = binding.root.findViewById(R.id.recyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(context)


        getList()

        swipeRefreshLayout = binding.root.findViewById(R.id.swipeRefresh)
        swipeRefreshLayout.setOnRefreshListener {

            updateList()
            swipeRefreshLayout.isRefreshing = false
        }

        return binding.root
    }


    private fun getList() {

        val retrofitService =  RetrofitService()
        val userApi = retrofitService.getRetrofit() .create(UserApi::class.java)

        CoroutineScope(Dispatchers.Default).launch {

            userApi.getCandidatesPresident().enqueue(object : Callback<List<CandidateGet>> {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onResponse(call: Call<List<CandidateGet>>, response: Response<List<CandidateGet>>) {
                    if(response.code() == 200){

                        candidateList = response.body()!!
                        itemAdapter = ItemAdapter(candidateList)
                        itemAdapter.setOnItemClickListener(object : ItemAdapter.onItemClickListener {
                            override fun onItemClick(position: Int) {

                                val intent = Intent(activity,VoteActivity::class.java)
                                intent.putExtra("idUser",itemAdapter.getCandidate(position).idUser)
                                startActivity(intent)


                            }
                        })
                        mRecyclerView.adapter = itemAdapter
                    }
                }

                override fun onFailure(call: Call<List<CandidateGet>>, t: Throwable) {
                    Log.e("rau","eroare la incarcare lista")
                }
            })
        }

    }

    private fun updateList(){

        val retrofitService =  RetrofitService()
        val userApi = retrofitService.getRetrofit() .create(UserApi::class.java)

        CoroutineScope(Dispatchers.Default).launch {

            userApi.getCandidatesPresident().enqueue(object : Callback<List<CandidateGet>> {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onResponse(call: Call<List<CandidateGet>>, response: Response<List<CandidateGet>>) {
                    if(response.code() == 200){

                        candidateList = response.body()!!
                        itemAdapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<List<CandidateGet>>, t: Throwable) {
                    Log.e("rau","eroare la incarcare lista")
                }
            })
        }
    }

}