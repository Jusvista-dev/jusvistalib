package mk.advtl.justorilib.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Author {
    @SerializedName("author_id")
    @Expose
    var authorId: String? = null

    @SerializedName("author_name")
    @Expose
    var authorName: String? = null
}