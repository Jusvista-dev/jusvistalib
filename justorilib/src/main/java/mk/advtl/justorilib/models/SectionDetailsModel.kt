package mk.advtl.justorilib.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class SectionDetailsModel {
    @SerializedName("success")
    @Expose
    var success: Int? = null

    @SerializedName("blocked")
    @Expose
    var blocked: String? = null

    @SerializedName("last_played_section_id")
    @Expose
    var lastPlayedSectionId: String? = null

    @SerializedName("last_played_section_duration")
    @Expose
    var lastPlayedSectionDuration: String? = null

    @SerializedName("last_played_story_duration")
    @Expose
    var lastPlayedStoryDuration: String? = null

    @SerializedName("total_duration")
    @Expose
    var totalDuration: Int? = null

    @SerializedName("is_active")
    @Expose
    var isActive: String? = null

    @SerializedName("story_status")
    @Expose
    var storyStatus: String? = null

    @SerializedName("user_blocked")
    @Expose
    var userBlocked: String? = null

    @SerializedName("story_section_list")
    @Expose
    var storySectionList: ArrayList<StorySectionSingleItem>? = null
}