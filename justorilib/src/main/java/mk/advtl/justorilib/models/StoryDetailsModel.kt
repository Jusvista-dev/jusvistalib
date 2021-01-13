package mk.advtl.justorilib.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class StoryDetailsModel {
    @SerializedName("success")
    @Expose
    var success: Int? = null

    @SerializedName("blocked")
    @Expose
    var blocked: String? = null

    @SerializedName("story_list")
    @Expose
    var storyList: ArrayList<StoryList>? = null
}