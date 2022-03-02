package com.example.androidapponlinevotingsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var usingTablets:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       usingTablets = resources.getBoolean(R.bool.using_dual_pane);

        Toast.makeText(this, "Wide Screen? $usingTablets", Toast.LENGTH_LONG).show()
    }

}

