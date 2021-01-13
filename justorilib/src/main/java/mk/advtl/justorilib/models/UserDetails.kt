package mk.advtl.justorilib.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class UserDetails {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("fname")
    @Expose
    var fname: String? = null

    @SerializedName("lname")
    @Expose
    var lname: String? = null

    @SerializedName("alias")
    @Expose
    var alias: String? = null

    @SerializedName("dob")
    @Expose
    var dob: String? = null

    @SerializedName("gender")
    @Expose
    var gender: String? = null

    @SerializedName("profile_photo")
    @Expose
    var profilePhoto: String? = null

    @SerializedName("profile_photo_thumb")
    @Expose
    var profilePhotoThumb: String? = null

    @SerializedName("color_code_hex")
    @Expose
    var colorCodeHex: String? = null

    @SerializedName("forecolor_code_hex")
    @Expose
    var forecolorCodeHex: String? = null
}