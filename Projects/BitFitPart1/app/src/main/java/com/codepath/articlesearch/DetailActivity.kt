package com.codepath.articlesearch

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

private const val TAG = "DetailActivity"

class DetailActivity : AppCompatActivity() {
    private lateinit var mediaImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var bylineTextView: TextView
    private lateinit var abstractTextView: TextView

    private lateinit var FoodTextView: TextView
    private lateinit var CaloriesTextView: TextView
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mediaImageView = findViewById(R.id.mediaImage)
        titleTextView = findViewById(R.id.mediaTitle)
        bylineTextView = findViewById(R.id.mediaByline)
        abstractTextView = findViewById(R.id.mediaAbstract)

        FoodTextView = findViewById(R.id.FoodText)
        CaloriesTextView = findViewById(R.id.CaloriesText)
        submitButton = findViewById(R.id.SubmitButton)

        val article = intent.getSerializableExtra(ARTICLE_EXTRA) as? DisplayArticle
        val food = intent.getSerializableExtra(FOOD_EXTRA) as? DisplayFood

        if (article != null) {
            // Set title and abstract information for the article
            //titleTextView.text = article.headline //?.main
            //bylineTextView.text = article.byline //?.original
            //abstractTextView.text = article.abstract

            // Set title and abstract information for the article


            // Load the media image
//        Glide.with(this)
//            .load(article.mediaImageUrl)
//            .into(mediaImageView)
        } else if (food != null) {
            FoodTextView.text = food.name.toString()
            CaloriesTextView.text = food.calories
        } else {
            // Case when no extras are passed (Add new Food)
            submitButton.setOnClickListener {
                FoodTextView = findViewById(R.id.FoodText)
                CaloriesTextView = findViewById(R.id.CaloriesText)

                val foodName = FoodTextView.text.toString()
                val calories = CaloriesTextView.text.toString().toIntOrNull()

                if (foodName.isNotEmpty() && calories != null) {
                    lifecycleScope.launch(IO) {
                        (application as ArticleApplication).db.foodDao().insertAll(
                            listOf(FoodEntity(name = foodName, calories = calories))
                        )
                        finish() // Close activity and return to MainActivity
                    }
                }
            }
        }

        // Set title and abstract information for the article
        //titleTextView.text = article.headline //?.main
        //bylineTextView.text = article.byline //?.original
        //abstractTextView.text = article.abstract

        // Set title and abstract information for the article


        // Load the media image
//        Glide.with(this)
//            .load(article.mediaImageUrl)
//            .into(mediaImageView)
    }
}