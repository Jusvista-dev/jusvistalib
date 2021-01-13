package mk.advtl.justorilib.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Genre {
    @SerializedName("genre_id")
    @Expose
    var genreId: String? = null

    @SerializedName("genre_name")
    @Expose
    var genreName: String? = null

    @SerializedName("genre_image")
    @Expose
    var genreImage: String? = null
}