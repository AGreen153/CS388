package com.codepath.articlesearch

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val ARTICLE_EXTRA = "ARTICLE_EXTRA"

const val FOOD_EXTRA = "FOOD_EXTRA"

private const val TAG = "ArticleAdapter"

class ArticleAdapter(private val context: Context, private val items: List<Any>) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val mediaImageView = itemView.findViewById<ImageView>(R.id.mediaImage)
        private val titleTextView = itemView.findViewById<TextView>(R.id.mediaTitle)
        private val abstractTextView = itemView.findViewById<TextView>(R.id.mediaAbstract)

        private val foodNameTextView = itemView.findViewById<TextView>(R.id.FoodItemName)
        private val foodCaloriesTextView = itemView.findViewById<TextView>(R.id.FoodItemCaloriesNumber)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(item: Any) {
            when (item) {
                is DisplayArticle -> {
                    titleTextView.text = item.headline
                    abstractTextView.text = item.abstract
                    Glide.with(context)
                        .load(item.mediaImageUrl)
                        .into(mediaImageView)
                }
                is DisplayFood -> {
                    foodNameTextView.text = "${item.calories}"
                    foodCaloriesTextView.text = item.name.toString()
                }
            }
        }

        override fun onClick(v: View?) {
            val item = items[absoluteAdapterPosition]
            val intent = Intent(context, DetailActivity::class.java)

            when (item) {
                is DisplayArticle -> intent.putExtra(ARTICLE_EXTRA, item)
                is DisplayFood -> intent.putExtra(FOOD_EXTRA, item)
            }

            context.startActivity(intent)
        }
    }
}
