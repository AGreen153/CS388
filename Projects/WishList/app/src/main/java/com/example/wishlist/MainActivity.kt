package com.example.wishlist

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codepathmail.EmailFetcher

class MainActivity : AppCompatActivity() {

    lateinit var itemsList: List<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonSubmit = findViewById<Button>(R.id.itemSubmit)
        val itemName = findViewById<TextView>(R.id.itemName)
        val itemPrice = findViewById<TextView>(R.id.itemPrice)
        val itemLink = findViewById<TextView>(R.id.itemDescription)

        // Lookup the RecyclerView in activity layout
        val emailsRv = findViewById<RecyclerView>(R.id.itemsRv)
        // Fetch the list of emails
        itemsList = EmailFetcher.getEmailss()
        Log.d("MainScreen", "${itemsList}")
        // Create adapter passing in the list of emails
        val adapter = EmailAdapter(itemsList)
        // Attach the adapter to the RecyclerView to populate items
        emailsRv.adapter = adapter
        // Set layout manager to position the items
        emailsRv.layoutManager = LinearLayoutManager(this)

        buttonSubmit.setOnClickListener {
            // prevent breaking when no price entered
            if (itemPrice.text.length <= 0 || itemName.text.length <= 0 || itemLink.text.length <= 0 ) {
                Log.d("MainScreen", "true")
                return@setOnClickListener
            }

            // Add the field to the EmailFetcher
            //EmailFetcher.addItem(itemName.text.toString(), itemLink.text.toString(), Integer.parseInt(itemPrice.text.toString()))
            EmailFetcher.addItem(itemName.text.toString(), itemLink.text.toString(), (itemPrice.text.toString().toDouble()))
            Log.d("MainScreen", "first ${itemName.text.toString()}")
            Log.d("MainScreen", "second ${itemLink.text.toString()}")
            Log.d("MainScreen", "third ${(itemPrice.text.toString().toDouble())}")
            Log.d("MainScreen", "${itemsList}")

            adapter.notifyDataSetChanged()

            // Clear all fields
            itemName.text = ""
            itemPrice.text = ""
            itemLink.text = ""
        }

    }
}