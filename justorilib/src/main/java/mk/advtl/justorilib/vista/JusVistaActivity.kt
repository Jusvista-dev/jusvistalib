package mk.advtl.justorilib.vista

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_jusvista.*
import kotlinx.android.synthetic.main.liblistitems.*
import mk.advtl.justorilib.R
import mk.advtl.justorilib.adapters.SliderAdapter
import mk.advtl.justorilib.jcplayer.JcPlayerManagerListener
import mk.advtl.justorilib.jcplayer.general.JcStatus
import mk.advtl.justorilib.jcplayer.general.errors.OnInvalidPathListener
import mk.advtl.justorilib.jcplayer.model.JcAudio
import mk.advtl.justorilib.models.*
import mk.advtl.justorilib.retrofit.RetrofitError
import mk.advtl.justorilib.retrofit.RetrofitFactory
import mk.advtl.justorilib.utils.PaginationScrollListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class JusVistaActivity : BaseActivity(), OnInvalidPathListener, JcPlayerManagerListener {

    var storyListAdapter: CategorySingleViewAdapter? = null
    var storyList: ArrayList<StoryList>? = null
    lateinit var rvUserStoryList: RecyclerView
    var index = 1
    var noOfItem = 10
    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    var checkToken = ""
    var email = ""
    var userID = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jusvista)
        loadProgressDialogue(this)

        rvUserStoryList = findViewById(R.id.rvUserStoryList)
        val extras = intent.extras

        if (extras != null) {
            checkToken = extras.getString("checkToken", checkToken)
            email = extras.getString("email", email)
        }
        try {
            storyList = ArrayList()
            storyListAdapter = CategorySingleViewAdapter(storyList!!, this)

            val linearLayoutManager =
                LinearLayoutManager(this)
            rvUserStoryList.adapter = storyListAdapter
            rvUserStoryList.layoutManager = linearLayoutManager

            storyListAdapter!!.notifyDataSetChanged()

            rvUserStoryList.addOnScrollListener(object :
                PaginationScrollListener(linearLayoutManager) {
                override fun isLastPage(): Boolean {
                    return isLastPage
                }

                override fun isLoading(): Boolean {
                    return isLoading
                }

                override fun loadMoreItems() {
                    isLoading = true
                    //you have to re call to get more data
                    getAllStories(userID)
                }
            })


            getUserDetails()
            //getAllStories(userID)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getUserDetails() {
        try {
            openLoader()

            val call: Call<UserDetailsModel> = RetrofitFactory.getApiService().validateUserDetails(
                email,
                "jusvistaUsers",
                "jusvistaUsers",
                checkToken,
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""
            )

            call.enqueue(object : Callback<UserDetailsModel> {
                override fun onResponse(
                    call: retrofit2.Call<UserDetailsModel>,
                    response: Response<UserDetailsModel>
                ) {
                    closeLoader()
                    // Log.e("Response body", "Response >> " + Gson().toJson(response.body()))

                    if (response.isSuccessful) {
                        try {
                            val registrationResult: UserDetailsModel? = response.body()

                            if (registrationResult?.success == 1) {
                                try {
                                    userID = registrationResult.userId!!
                                    getAllStories(userID)

                                } catch (e: java.lang.Exception) {
                                    e.printStackTrace()
                                }

                            } else {
                                Toast.makeText(
                                    this@JusVistaActivity,
                                    "Something went wrong, please contact with support team.",
                                    Toast.LENGTH_LONG
                                ).show()
                                finish()
                            }

                        } catch (e: Exception) {
                            e.printStackTrace()
                        }

                    } else {
                        val error = RetrofitError.codeToErrorMessage(response.code())
                        Log.e("Error", "onResponse: $error")

                    }
                }

                override fun onFailure(call: retrofit2.Call<UserDetailsModel>, t: Throwable) {
                    t.printStackTrace()
                    try {
                        closeLoader()
                        val str = RetrofitError.showErrorMessage(t)
                        Log.e("Attraction ", str)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    Toast.makeText(
                        this@JusVistaActivity,
                        "Something went wrong, please contact with support team.",
                        Toast.LENGTH_LONG
                    ).show()
                    finish()

                }

            })
        } catch (e: Exception) {

            e.printStackTrace()
        }

    }

    private fun getAllStories(userID: String) {
        try {
            openLoader()


            val call: Call<StoryDetailsModel> = RetrofitFactory.getApiService().getAllStories(
                noOfItem.toString(),
                "4.2",
                "e717b856c84ffb7b",
                "",
                checkToken,
                index.toString(),
                "",
                "Android",
                "",
                "",
                "",
                "en",
                userID,
                "",
                ""
            )

            call.enqueue(object : Callback<StoryDetailsModel> {
                override fun onResponse(
                    call: retrofit2.Call<StoryDetailsModel>,
                    response: Response<StoryDetailsModel>
                ) {
                    closeLoader()
                    // Log.e("Response body", "Response >> " + Gson().toJson(response.body()))

                    if (response.isSuccessful) {
                        try {
                            val registrationResult: StoryDetailsModel? = response.body()

                            if (registrationResult?.success == 1) {
                                index++
                                try {
                                    isLoading = false
                                    storyListAdapter!!.addData(registrationResult.storyList!!)

                                } catch (e: java.lang.Exception) {
                                    e.printStackTrace()
                                }

                            } else {
                                Toast.makeText(
                                    this@JusVistaActivity,
                                    "Something went wrong, please contact with support team.",
                                    Toast.LENGTH_LONG
                                ).show()
                                finish()
                            }

                        } catch (e: Exception) {
                            e.printStackTrace()
                        }

                    } else {
                        val error = RetrofitError.codeToErrorMessage(response.code())
                        Log.e("Error", "onResponse: $error")

                    }
                }

                override fun onFailure(call: retrofit2.Call<StoryDetailsModel>, t: Throwable) {
                    t.printStackTrace()
                    try {
                        closeLoader()
                        val str = RetrofitError.showErrorMessage(t)
                        Log.e("Attraction ", str)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    Toast.makeText(
                        this@JusVistaActivity,
                        "Something went wrong, please contact with support team.",
                        Toast.LENGTH_LONG
                    ).show()
                    finish()

                }

            })
        } catch (e: Exception) {

            e.printStackTrace()
        }
    }

    inner class CategorySingleViewAdapter(
        var allstorylist: ArrayList<StoryList>,
        var activity: Activity
    ) : RecyclerView.Adapter<CategorySingleViewAdapter.DashboardHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardHolder {
            return DashboardHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.liblistitems, parent, false)
            )
        }

        /* interface SingleItemClickListener {
             fun onItemClick(position: Int, productsModel: ProductsModel)
         }*/

        override fun onBindViewHolder(holder: DashboardHolder, position: Int) {

            if (allstorylist[position].storyStatus == "Private") {
                holder.disk_img!!.setImageDrawable(resources.getDrawable(R.drawable.storyprivate))
            } else {
                holder.disk_img!!.setImageDrawable(resources.getDrawable(R.drawable.disk))
            }


            holder.tv_description!!.text = allstorylist[position].storySummary
            holder.tv_narrator_name!!.text = allstorylist[position].narrator!!.name
            holder.tv_no_of_follower!!.text =
                allstorylist[position].narrator!!.noOfFollowers.toString()
            if (allstorylist[position].narrator!!.followYn == "N") {
                holder.txt_follow!!.visibility = View.VISIBLE
                holder.pb!!.visibility = View.GONE
                holder.im_follow_block!!.visibility = View.GONE
                holder.txt_follow!!.setImageDrawable(resources.getDrawable(R.drawable.icon_follower))
            } else if (allstorylist[position].narrator!!.followYn == "Y") {
                holder.txt_follow!!.visibility = View.VISIBLE
                holder.pb!!.visibility = View.GONE
                holder.im_follow_block!!.visibility = View.GONE
                holder.txt_follow!!.setImageDrawable(resources.getDrawable(R.drawable.icon_following))
            } else {
                holder.pb!!.visibility = View.GONE
                holder.txt_follow!!.visibility = View.GONE
                holder.im_follow_block!!.visibility = View.VISIBLE
            }
            holder.tv_language!!.text = allstorylist[position].language!!.langCode
            val shape = GradientDrawable()
            shape.shape = GradientDrawable.OVAL
            shape.setColor(Color.parseColor("#" + allstorylist[position].language!!.colorCodeHex))
            holder.tv_language!!.setBackgroundDrawable(shape)
            holder.tv_language!!.setTextColor(Color.parseColor("#" + allstorylist[position].language!!.langForegroundcolor))
            try {
                holder.tv_publication_date!!.text =
                    convertDate(allstorylist[position].publicationDate!!)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            try {
                Glide.with(activity).load(allstorylist[position].country!!.countryFlag)
                    .into(holder.iv_country!!)
                Glide.with(activity).load(allstorylist[position].genre!!.genreImage)
                    .into(holder.iv_genre!!)
                Glide.with(activity).load(allstorylist[position].narrator!!.profilePhotoThumb)
                    .into(holder.civ_narrator_profile_photo!!)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            if (allstorylist[position].ageRestriction == "above_18") {
                holder.tv_age_restriction!!.visibility = View.VISIBLE
            } else if (allstorylist[position].ageRestriction == "above_13") {
                holder.tv_age_restriction!!.visibility = View.VISIBLE
                holder.tv_age_restriction!!.text = "13+"
            } else {
                holder.tv_age_restriction!!.visibility = View.GONE
            }
            holder.tv_share_count!!.text = allstorylist[position].shareCount
            val storyTitle = allstorylist[position].storyTitle
            val content = SpannableString(storyTitle)
            content.setSpan(UnderlineSpan(), 0, storyTitle!!.length, 0)
            holder.tv_story_title!!.text = content
            holder.tv_listen_count!!.text = allstorylist[position].listenCount
            holder.txt_listen_count!!.text = allstorylist[position].listenCount
            holder.tv_comment_count!!.text = "" + allstorylist[position].storyCommentCount!!


            holder.tv_story_rating_count!!.text =
                "(" + allstorylist[position].storyRatingCount + ")"
            holder.rating_figure!!.text = allstorylist[position].storyRating + "/5"
            holder.rb_rating!!.rating = allstorylist[position].storyRating!!.toFloat()

            try {
                if (allstorylist[position].storyImages!!.isNotEmpty()) {
                    val adapter = SliderAdapter(
                        this@JusVistaActivity,
                        allstorylist[position].storyImages!!
                    )
                    holder.imageSlider!!.setSliderAdapter(adapter)
                    holder.imageSlider!!.setIndicatorAnimation(IndicatorAnimationType.WORM) //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                    holder.imageSlider!!.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
                    holder.imageSlider!!.autoCycleDirection =
                        SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
                    holder.imageSlider!!.scrollTimeInSec =
                        4 //set scroll delay in seconds :
                    holder.imageSlider!!.startAutoCycle()
                    holder.layoutProductSlider!!.visibility = View.VISIBLE

                    /* Log.e(
                         "ImageList",
                         "Banner Images >>  " + Gson().toJson(allstorylist[position].storyImages!!)
                     )*/
                } else {
                    holder.layoutProductSlider!!.visibility = View.VISIBLE
                    var storyImageList: ArrayList<StoryImage> = ArrayList()
                    val storyImage = StoryImage()

                    storyImage.imageName = allstorylist[position].storyDefaultImage
                    storyImageList.add(storyImage)
                    val adapter = SliderAdapter(
                        this@JusVistaActivity,
                        storyImageList
                    )
                    holder.imageSlider!!.setSliderAdapter(adapter)
                    holder.imageSlider!!.setIndicatorAnimation(IndicatorAnimationType.WORM) //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                    holder.imageSlider!!.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
                    holder.imageSlider!!.autoCycleDirection =
                        SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
                    holder.imageSlider!!.scrollTimeInSec =
                        4 //set scroll delay in seconds :
                    holder.imageSlider!!.startAutoCycle()

                    //Log.e("ImageList", "Banner Images >>  " + Gson().toJson(storyImageList))


                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()

                holder.layoutProductSlider!!.visibility = View.VISIBLE
                var storyImageList: ArrayList<StoryImage> = ArrayList()
                val storyImage = StoryImage()

                storyImage.imageName = allstorylist[position].storyDefaultImage

                storyImageList.add(storyImage)
                val adapter = SliderAdapter(
                    this@JusVistaActivity,
                    allstorylist[position].storyImages!!
                )
                holder.imageSlider!!.setSliderAdapter(adapter)
                holder.imageSlider!!.setIndicatorAnimation(IndicatorAnimationType.WORM) //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                holder.imageSlider!!.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
                holder.imageSlider!!.autoCycleDirection =
                    SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
                holder.imageSlider!!.scrollTimeInSec =
                    4 //set scroll delay in seconds :
                holder.imageSlider!!.startAutoCycle()

            }

            holder.iv_start_story!!.setOnClickListener {
                getStorySections(allstorylist[position].storyId)
            }

        }

        override fun getItemCount(): Int {
            return allstorylist.size
        }

        inner class DashboardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var short_name: TextView? = null
            var txt_listen_count: TextView? = null
            var tv_narrator_name: TextView? = null
            var tv_no_of_follower: TextView? = null
            var tv_language: TextView? = null
            var tv_publication_date: TextView? = null
            var tv_age_restriction: TextView? = null
            var tv_share_count: TextView? = null
            var tv_comment_count: TextView? = null
            var iv_country: CircleImageView? = null
            var civ_narrator_profile_photo: CircleImageView? = null
            var iv_genre: ImageView? = null
            var iv_story_image: ImageView? = null
            var iv_start_story: ImageView? = null
            var rb_rating: RatingBar? = null
            var tv_story_title: TextView? = null
            var tv_listen_count: TextView? = null
            var tv_story_rating_count: TextView? = null
            var rating_figure: TextView? = null
            var tv_description: TextView? = null
            var disk_img: ImageView? = null
            var txt_follow: ImageView? = null
            var im_follow_block: ImageView? = null
            var title_lay: LinearLayout? = null
            var ll_show_rating_bar: LinearLayout? = null
            var name_lay: LinearLayout? = null
            var ll_share_count: LinearLayout? = null
            var ll_comment_count: LinearLayout? = null
            var ll_description: LinearLayout? = null
            var pb: ProgressBar? = null
            var person_img: ImageView? = null
            var ivOptionMenu: ImageView? = null
            var imageSlider: SliderView? = null
            var layoutProductSlider: RelativeLayout? = null

            init {
                name_lay = itemView.findViewById<View>(R.id.name_lay) as LinearLayout
                disk_img = itemView.findViewById<View>(R.id.disk_img) as ImageView
                txt_follow = itemView.findViewById<View>(R.id.txt_follow) as ImageView
                im_follow_block = itemView.findViewById<View>(R.id.im_follow_block) as ImageView
                tv_narrator_name = itemView.findViewById<View>(R.id.tv_narrator_name) as TextView
                tv_no_of_follower = itemView.findViewById<View>(R.id.tv_no_of_follower) as TextView
                tv_language = itemView.findViewById<View>(R.id.tv_language) as TextView
                tv_publication_date =
                    itemView.findViewById<View>(R.id.tv_publication_date) as TextView
                iv_country = itemView.findViewById<View>(R.id.iv_country) as CircleImageView
                iv_genre = itemView.findViewById<View>(R.id.iv_genre) as ImageView
                tv_age_restriction =
                    itemView.findViewById<View>(R.id.tv_age_restriction) as TextView
                tv_share_count = itemView.findViewById<View>(R.id.tv_share_count) as TextView
                tv_comment_count = itemView.findViewById<View>(R.id.tv_comment_count) as TextView
                tv_story_title = itemView.findViewById<View>(R.id.tv_story_title) as TextView
                tv_listen_count = itemView.findViewById<View>(R.id.tv_listen_count) as TextView
                civ_narrator_profile_photo =
                    itemView.findViewById<View>(R.id.civ_narrator_profile_photo) as CircleImageView
                tv_story_rating_count =
                    itemView.findViewById<View>(R.id.tv_story_rating_count) as TextView
                rating_figure = itemView.findViewById<View>(R.id.rating_figure) as TextView
                rb_rating = itemView.findViewById<View>(R.id.rb_rating) as RatingBar
                iv_story_image = itemView.findViewById<View>(R.id.iv_story_image) as ImageView
                iv_start_story = itemView.findViewById<View>(R.id.iv_start_story) as ImageView
                title_lay = itemView.findViewById<View>(R.id.title_lay) as LinearLayout
                ll_show_rating_bar =
                    itemView.findViewById<View>(R.id.ll_show_rating_bar) as LinearLayout
                pb = itemView.findViewById<View>(R.id.pb_loading) as ProgressBar
                short_name = itemView.findViewById<View>(R.id.short_name_lib) as TextView
                txt_listen_count = itemView.findViewById<View>(R.id.txt_listen_count) as TextView
                ll_share_count = itemView.findViewById<View>(R.id.ll_share_count) as LinearLayout
                ll_comment_count =
                    itemView.findViewById<View>(R.id.ll_comment_count) as LinearLayout
                person_img = itemView.findViewById<View>(R.id.person_img) as ImageView
                ivOptionMenu = itemView.findViewById<View>(R.id.ivOptionMenu) as ImageView
                ll_description = itemView.findViewById<View>(R.id.description_lay) as LinearLayout
                tv_description = itemView.findViewById<View>(R.id.tv_story_description) as TextView
                imageSlider = itemView.findViewById<View>(R.id.imageSlider) as SliderView
                layoutProductSlider =
                    itemView.findViewById<View>(R.id.layoutProductSlider) as RelativeLayout

            }

        }

        fun addData(productList: ArrayList<StoryList>) {

            val size = this.allstorylist.size
            this.allstorylist.addAll(productList)
            val sizeNew = this.allstorylist.size
            notifyItemRangeChanged(size, sizeNew)
        }
    }

    private fun getStorySections(storyId: String?) {
        try {
            openLoader()

            val call: Call<SectionDetailsModel> = RetrofitFactory.getApiService().getStorySections(
                "1218",
                storyId,
                checkToken,
                "4.2",
                "Android",
                "en",
                "e717b856c84ffb7b"
            )
            call.enqueue(object : Callback<SectionDetailsModel> {
                override fun onResponse(
                    call: retrofit2.Call<SectionDetailsModel>,
                    response: Response<SectionDetailsModel>
                ) {
                    closeLoader()
                    Log.e("Response body", "Story Section >> " + Gson().toJson(response.body()))

                    if (response.isSuccessful) {
                        try {
                            val sectionDetails: SectionDetailsModel? = response.body()

                            if (sectionDetails?.success == 1) {
                                try {
                                    if (sectionDetails!!.storySectionList!!.isNotEmpty()) {
                                        jcplayer.visibility = View.VISIBLE
                                        val jcAudios: ArrayList<JcAudio> = ArrayList<JcAudio>()
                                        for (i in 0 until sectionDetails.storySectionList!!.size) {

                                            val title =
                                                sectionDetails.storySectionList!!.get(i).fileName!!
                                            val fileUrl =
                                                sectionDetails.storySectionList!!.get(i).filePath!!
                                            jcAudios.add(
                                                JcAudio.createFromURL(
                                                    title,
                                                    fileUrl
                                                )
                                            )
                                        }
//                                    jcplayer.initPlaylist(jcAudios, this@JusVistaActivity)
                                        jcplayer.initAnonPlaylist(jcAudios)


                                        Log.e("Section", "File path" + jcplayer.myPlaylist[0])
                                        jcplayer.playAudio(jcplayer.myPlaylist[0])
                                    }
                                } catch (e: java.lang.Exception) {
                                    e.printStackTrace()
                                }

                            }

                        } catch (e: Exception) {
                            e.printStackTrace()
                        }

                    } else {
                        val error = RetrofitError.codeToErrorMessage(response.code())
                        Log.e("Error", "onResponse: $error")

                    }
                }

                override fun onFailure(call: retrofit2.Call<SectionDetailsModel>, t: Throwable) {
                    t.printStackTrace()
                    try {
                        closeLoader()
                        val str = RetrofitError.showErrorMessage(t)
                        Log.e("Attraction ", str)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    Toast.makeText(
                        this@JusVistaActivity,
                        "Error occurred, Please contact with Support Team.",
                        Toast.LENGTH_SHORT
                    ).show()

                }

            })
        } catch (e: Exception) {

            e.printStackTrace()
        }


    }


    fun convertDate(dateString: String): String {
        val result = dateString.split(" ".toRegex()).toTypedArray()[0]
        val year = result.split("-".toRegex()).toTypedArray()[0]
        var month = result.split("-".toRegex()).toTypedArray()[1]
        val day = result.split("-".toRegex()).toTypedArray()[2]
        if (month == "01") {
            month = "January"
        } else if (month == "02") {
            month = "February"
        } else if (month == "03") {
            month = "March"
        } else if (month == "04") {
            month = "April"
        } else if (month == "05") {
            month = "May"
        } else if (month == "06") {
            month = "June"
        } else if (month == "07") {
            month = "July"
        } else if (month == "08") {
            month = "August"
        } else if (month == "09") {
            month = "September"
        } else if (month == "10") {
            month = "October"
        } else if (month == "11") {
            month = "November"
        } else if (month == "12") {
            month = "December"
        }
        return "$day $month, $year"
    }

    override fun onStop() {
        super.onStop()
        jcplayer.createNotification()
    }

    override fun onPause() {
        super.onPause()
        jcplayer.createNotification()
    }

    override fun onDestroy() {
        super.onDestroy()
        jcplayer.kill()
    }

    override fun onPathError(jcAudio: JcAudio) {
        Toast.makeText(this, jcAudio.path.toString() + " with problems", Toast.LENGTH_LONG)
            .show()
//        player.removeAudio(jcAudio);
//        player.next();
    }

    override fun onPreparedAudio(status: JcStatus) {
        TODO("Not yet implemented")
    }

    override fun onCompletedAudio() {
        TODO("Not yet implemented")
    }

    override fun onPaused(status: JcStatus) {
        TODO("Not yet implemented")
    }

    override fun onContinueAudio(status: JcStatus) {
        TODO("Not yet implemented")
    }

    override fun onPlaying(status: JcStatus) {
        TODO("Not yet implemented")
    }


    override fun onTimeChanged(@NonNull status: JcStatus) {
        updateProgress(status)
    }

    override fun onStopped(status: JcStatus) {
        TODO("Not yet implemented")
    }

    override fun onJcpError(@NonNull throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_LONG).show()
    }

    private fun updateProgress(jcStatus: JcStatus) {
        Log.d(
            "TAG", "Song duration = " + jcStatus.duration
                .toString() + "\n song position = " + jcStatus.currentPosition
        )
        runOnUiThread { // calculate progress
            var progress = ((jcStatus.duration - jcStatus.currentPosition).toFloat()
                    / jcStatus.duration.toFloat())
            progress = 1.0f - progress
            //audioAdapter.updateProgress(jcStatus.jcAudio, progress)
        }
    }

    /*private fun removeItem(position: Int) {
        (recyclerView.getItemAnimator() as SimpleItemAnimator).setSupportsChangeAnimations(true)

        //        jcAudios.remove(position);
        jcplayer.removeAudio(jcplayer.myPlaylist.get(position))
        audioAdapter.notifyItemRemoved(position)
        (recyclerView.getItemAnimator() as SimpleItemAnimator).setSupportsChangeAnimations(false)
    }*/


}