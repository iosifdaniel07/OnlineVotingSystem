package com.example.androidapponlinevotingsystem.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidapponlinevotingsystem.R
import com.example.androidapponlinevotingsystem.data.Candidates
import com.example.androidapponlinevotingsystem.preview.StartAplicationFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

   // private lateinit var mRecyclerView: RecyclerView
   // private lateinit var candidateDataList: ArrayList<Candidates>

    private lateinit var bottomNavigationView:BottomNavigationView
    private lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       /* val fragmentTransaction:FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainframeLayout,PresidentFragment())
        fragmentTransaction.commit()*/

      //  val bottomnavoigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
       // val navController = findNavController(R.id.fragmentContainerView)

       // bottomnavoigationView.setupWithNavController(navController)



        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        navController = findNavController(R.id.fragmentMain)
        bottomNavigationView.setupWithNavController(navController)

    }
}