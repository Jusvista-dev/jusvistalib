package mk.advtl.justorilib.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Country {
    @SerializedName("country_id")
    @Expose
    var countryId: String? = null

    @SerializedName("country_name")
    @Expose
    var countryName: String? = null

    @SerializedName("country_flag")
    @Expose
    var countryFlag: String? = null
}