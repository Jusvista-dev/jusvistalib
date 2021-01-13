package mk.advtl.justorilib.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Narrator {
    @SerializedName("narrator_id")
    @Expose
    var narratorId: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("short_name")
    @Expose
    var shortName: String? = null

    @SerializedName("person")
    @Expose
    var person: String? = null

    @SerializedName("background_color_code")
    @Expose
    var backgroundColorCode: String? = null

    @SerializedName("foreground_color_code")
    @Expose
    var foregroundColorCode: String? = null

    @SerializedName("profile_photo")
    @Expose
    var profilePhoto: String? = null

    @SerializedName("profile_photo_thumb")
    @Expose
    var profilePhotoThumb: String? = null

    @SerializedName("no_of_followers")
    @Expose
    var noOfFollowers: Int? = null

    @SerializedName("blocked")
    @Expose
    var blocked: String? = null

    @SerializedName("follow_yn")
    @Expose
    var followYn: String? = null
}