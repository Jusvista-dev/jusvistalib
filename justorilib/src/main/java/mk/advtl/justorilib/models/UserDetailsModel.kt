package mk.advtl.justorilib.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class UserDetailsModel {
    @SerializedName("success")
    @Expose
    var success: Int? = null

    @SerializedName("user_id")
    @Expose
    var userId: String? = null

    @SerializedName("user_details")
    @Expose
    var userDetails: UserDetails? = null

    @SerializedName("profile_photo")
    @Expose
    var profilePhoto: String? = null

    @SerializedName("profile_photo_thumb")
    @Expose
    var profilePhotoThumb: String? = null

    @SerializedName("existing_user")
    @Expose
    var existingUser: String? = null

    @SerializedName("alternate_email_exists")
    @Expose
    var alternateEmailExists: String? = null

    @SerializedName("v_code")
    @Expose
    var vCode: Int? = null

    @SerializedName("msg")
    @Expose
    var msg: String? = null
}