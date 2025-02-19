package com.codepath.bestsellerlistapp

import com.google.gson.annotations.SerializedName

/**
 * The Model for storing a single book from the NY Times API
 *
 * SerializedName tags MUST match the JSON response for the
 * object to correctly parse with the gson library.
 */
class BestSellerBook {

    @SerializedName("rank")
    var rank = 0

    @JvmField
    @SerializedName("title")
    var title: String? = null

    @JvmField
    @SerializedName("author")
    var author: String? = null

    //0000

    @SerializedName("original_title")
    var original_title: String? = null

    @SerializedName("backdrop_path")
    var backdrop_path: String? = null

    @SerializedName("overview")
    var overview: String? = null


}