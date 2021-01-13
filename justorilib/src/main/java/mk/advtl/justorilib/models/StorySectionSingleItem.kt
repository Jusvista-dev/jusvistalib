package mk.advtl.justorilib.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StorySectionSingleItem {
    @SerializedName("section_id")
    @Expose
    var sectionId: String? = null

    @SerializedName("file_path")
    @Expose
    var filePath: String? = null

    @SerializedName("file_name")
    @Expose
    var fileName: String? = null

    @SerializedName("file_duration")
    @Expose
    var fileDuration: String? = null

    @SerializedName("section_status")
    @Expose
    var sectionStatus: String? = null

    @SerializedName("section_order")
    @Expose
    var sectionOrder: String? = null

    @SerializedName("updated")
    @Expose
    var updated: String? = null
}