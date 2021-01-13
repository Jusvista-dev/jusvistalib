package mk.advtl.justorilib.retrofit;

import mk.advtl.justorilib.models.SectionDetailsModel;
import mk.advtl.justorilib.models.StoryDetailsModel;
import mk.advtl.justorilib.models.UserDetailsModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestInterface {

    @POST("check_user")
    @FormUrlEncoded
    Call<UserDetailsModel> validateUserDetails(
            @Field("email") String email,
            @Field("device_id") String device_id,
            @Field("firebase_reg_id") String firebase_reg_id,
            @Field("check_token") String check_token,
            @Field("app_version") String app_version,
            @Field("platform") String platform,
            @Field("lang_code") String lang_code,
            @Field("device_model") String device_model,
            @Field("os_version") String os_version,
            @Field("hardware_version") String hardware_version,
            @Field("device_location") String device_location,
            @Field("device_mobile_operator") String device_mobile_operator,
            @Field("device_language") String device_language,
            @Field("device_timezone") String device_timezone,
            @Field("device_manufacturer") String device_manufacturer,
            @Field("screen_size") String screen_size,
            @Field("device_name") String device_name
    );

  @POST("get_user_story_listing")
    @FormUrlEncoded
    Call<StoryDetailsModel> getAllStories(
            @Field("per_page") String per_page,
            @Field("app_version") String app_version,
            @Field("device_id") String device_id,
            @Field("lang_id") String lang_id,
            @Field("check_token") String check_token,
            @Field("page_no") String page_no,
            @Field("genre_id") String genre_id,
            @Field("platform") String platform,
            @Field("user_col") String user_col,
            @Field("tags") String tags,
            @Field("user_col_val") String user_col_val,
            @Field("lang_code") String lang_code,
            @Field("user_id") String user_id,
            @Field("following_id") String following_id,
            @Field("story_title") String story_title
    );

    @POST("play_story_section")
    @FormUrlEncoded
    Call<SectionDetailsModel> getStorySections(
            @Field("user_id") String user_id,
            @Field("story_id") String story_id,
            @Field("check_token") String check_token,
            @Field("app_version") String app_version,
            @Field("platform") String platform,
            @Field("lang_code") String lang_code,
            @Field("device_id") String device_id
    );


}

