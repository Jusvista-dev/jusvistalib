package mk.advtl.justorilib.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class StoryImage {
    @SerializedName("image_id")
    @Expose
    var imageId: String? = null

    @SerializedName("image_name")
    @Expose
    var imageName: String? = null

    @SerializedName("image_name_thumb")
    @Expose
    var imageNameThumb: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("cover_img")
    @Expose
    var coverImg: String? = null
}