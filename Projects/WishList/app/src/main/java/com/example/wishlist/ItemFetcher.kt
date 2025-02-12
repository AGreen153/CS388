package com.example.codepathmail

import com.example.wishlist.Item

class EmailFetcher {
    companion object {
        var emails : MutableList<Item> = ArrayList()
        //lateinit var emails: MutableList<Item>
        val senders = listOf("Dahlia Cline", "Kevin Miranda", "Kaya Austin", "Laila Calderon", "Marquise Rhodes", "Fletcher Patel", "Luz Barron", "Kamren Dudley", "Jairo Foster", "Lilah Sandoval", "Ansley Blake", "Slade Sawyer", "Jaelyn Holmes", "Phoenix Bright", "Ernesto Gould")
        val title = "Welcome to Kotlin!"
        val summary = "Welcome to the Android Kotlin Course! We're excited to have you join us and learn how to develop Android apps using Kotlin. Here are some tips to get started."

        fun getEmailss(): MutableList<Item> {
            // emails : MutableList<Item> = ArrayList()
            //for (i in 0..9) {
            //    val email = Item(senders[i], title, 0)
            //    emails.add(email)
            //}
            return emails
        }

        fun addItem(name: String, link: String, price: Number): MutableList<Item> {
            val item = Item(name, link, price)
            emails.add(item)

            return emails
        }

//        fun removeEmail(): MutableList<Item> {
//
//        }

        fun getNext5Emails(): MutableList<Item> {
            var newEmails : MutableList<Item> = ArrayList()
            for (i in 10..14) {
                val email = Item(senders[i], title, 0)
                newEmails.add(email)
            }
            return newEmails
        }
    }
}