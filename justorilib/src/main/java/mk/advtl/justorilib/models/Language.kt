package mk.advtl.justorilib.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Language {
    @SerializedName("lang_id")
    @Expose
    var langId: String? = null

    @SerializedName("lang_name")
    @Expose
    var langName: String? = null

    @SerializedName("native_name")
    @Expose
    var nativeName: String? = null

    @SerializedName("lang_code")
    @Expose
    var langCode: String? = null

    @SerializedName("color_code_hex")
    @Expose
    var colorCodeHex: String? = null

    @SerializedName("lang_foregroundcolor")
    @Expose
    var langForegroundcolor: String? = null
}