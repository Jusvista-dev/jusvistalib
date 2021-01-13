package mk.advtl.justorilib.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class StoryList {
    @SerializedName("story_id")
    @Expose
    var storyId: String? = null

    @SerializedName("story_title")
    @Expose
    var storyTitle: String? = null

    @SerializedName("story_status")
    @Expose
    var storyStatus: String? = null

    @SerializedName("story_weblink")
    @Expose
    var storyWeblink: String? = null

    @SerializedName("play_later")
    @Expose
    var playLater: String? = null

    @SerializedName("narrator")
    @Expose
    var narrator: Narrator? = null

    @SerializedName("country")
    @Expose
    var country: Country? = null

    @SerializedName("language")
    @Expose
    var language: Language? = null

    @SerializedName("genre")
    @Expose
    var genre: Genre? = null

    @SerializedName("author")
    @Expose
    var author: Author? = null

    @SerializedName("story_default_image")
    @Expose
    var storyDefaultImage: String? = null

    @SerializedName("shared_to_business")
    @Expose
    var sharedToBusiness: String? = null

    @SerializedName("acknowledgement")
    @Expose
    var acknowledgement: String? = null

    @SerializedName("translator_name")
    @Expose
    var translatorName: String? = null

    @SerializedName("story_summary")
    @Expose
    var storySummary: String? = null

    @SerializedName("narrator_note")
    @Expose
    var narratorNote: String? = null

    @SerializedName("true_story")
    @Expose
    var trueStory: String? = null

    @SerializedName("story_file")
    @Expose
    var storyFile: String? = null

    @SerializedName("story_rating")
    @Expose
    var storyRating: String? = null

    @SerializedName("publication_date")
    @Expose
    var publicationDate: String? = null

    @SerializedName("origin_type")
    @Expose
    var originType: String? = null

    @SerializedName("read_out")
    @Expose
    var readOut: String? = null

    @SerializedName("adopted_name")
    @Expose
    var adoptedName: String? = null

    @SerializedName("publication_name")
    @Expose
    var publicationName: String? = null

    @SerializedName("read_out_own_words_name")
    @Expose
    var readOutOwnWordsName: String? = null

    @SerializedName("other_media")
    @Expose
    var otherMedia: String? = null

    @SerializedName("other_media_desc")
    @Expose
    var otherMediaDesc: String? = null

    @SerializedName("translated")
    @Expose
    var translated: String? = null

    @SerializedName("age_restriction")
    @Expose
    var ageRestriction: String? = null

    @SerializedName("listen_count")
    @Expose
    var listenCount: String? = null

    @SerializedName("share_count")
    @Expose
    var shareCount: String? = null

    @SerializedName("my_rating")
    @Expose
    var myRating: Int? = null

    @SerializedName("story_rating_count")
    @Expose
    var storyRatingCount: Int? = null

    @SerializedName("story_comment_count")
    @Expose
    var storyCommentCount: Int? = null

    @SerializedName("story_moderation_count")
    @Expose
    var storyModerationCount: String? = null

    @SerializedName("story_duration")
    @Expose
    var storyDuration: String? = null

    @SerializedName("tags")
    @Expose
    var tags: List<String>? = null

    @SerializedName("story_images")
    @Expose
    var storyImages: ArrayList<StoryImage>? = null
}