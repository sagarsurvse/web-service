package com.viralmeme.viralmeme.Api;


import com.google.gson.JsonElement;
import com.viralmeme.viralmeme.models.Bookmark;
import com.viralmeme.viralmeme.models.Comments;
import com.viralmeme.viralmeme.models.ComplementRequest;
import com.viralmeme.viralmeme.models.Complements;
import com.viralmeme.viralmeme.models.CreateComment;
import com.viralmeme.viralmeme.models.CreatePost;
import com.viralmeme.viralmeme.models.CreateStory;
import com.viralmeme.viralmeme.models.CreditsRedeem;
import com.viralmeme.viralmeme.models.DataMeme;
import com.viralmeme.viralmeme.models.DataUser;
import com.viralmeme.viralmeme.models.Delete;
import com.viralmeme.viralmeme.models.EmailResponse;
import com.viralmeme.viralmeme.models.FCMResponse;
import com.viralmeme.viralmeme.models.Feed;
import com.viralmeme.viralmeme.models.FollowModel;
import com.viralmeme.viralmeme.models.Follower;
import com.viralmeme.viralmeme.models.GmailSignInRes;
import com.viralmeme.viralmeme.models.Identifier;
import com.viralmeme.viralmeme.models.Notification;
import com.viralmeme.viralmeme.models.NotificationCount;
import com.viralmeme.viralmeme.models.OtpResponse;
import com.viralmeme.viralmeme.models.PasseordLogin;
import com.viralmeme.viralmeme.models.ProfileResponse;
import com.viralmeme.viralmeme.models.RUser;
import com.viralmeme.viralmeme.models.ReactResponse;
import com.viralmeme.viralmeme.models.Redeem;
import com.viralmeme.viralmeme.models.RegisterUser;
import com.viralmeme.viralmeme.models.Report;
import com.viralmeme.viralmeme.models.SignUpResponse;
import com.viralmeme.viralmeme.models.Storymain;
import com.viralmeme.viralmeme.models.Template;
import com.viralmeme.viralmeme.models.Trendinghashtag;
import com.viralmeme.viralmeme.models.UpadateUsername;
import com.viralmeme.viralmeme.models.UserSearch;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SignUpApi {


    @POST("signIn")
    Call<SignUpResponse> createUser(
            @Body SignUpResponse signUpResponse
    );
    @POST("email/signIn")
    Call<EmailResponse> createUserviaemail(
            @Body EmailResponse emailResponse
    );
    @POST("googleSignIn")
    Call<GmailSignInRes> googleSignIn(
            @Header("Authorization") String auth,
            @Body GmailSignInRes gmailSignInRes
    );
    @POST("verifyOtp")
    Call<OtpResponse> verifyOtp(
            @Body OtpResponse otpResponse
    );

    @Multipart
    @POST("posts")
    Call<CreatePost> createPost(
            @Part MultipartBody.Part meme_file,
            @Part("description") RequestBody description,
            @Header("Authorization") String auth
    );

    @Multipart
    @POST("story")
    Call<CreateStory> createStory(
            @Part MultipartBody.Part story_file,
            @Header("Authorization") String auth
    );

    @GET("posts/feed/new")
    Call<List<Feed>>feed(
            @Header("Authorization") String auth,
            @Query("seen_posts") String seenposts
    );
    @GET("posts/feed/following")
    Call<List<Feed>>following(
            @Header("Authorization") String auth,
            @Query("seen_posts") String seenposts
    );
    @GET("posts/feed/trending")
    Call<List<Feed>> trendingfeed(
            @Header("Authorization") String auth
    );

    @PUT("profile")
    Call<ProfileResponse> profile(
            @Body ProfileResponse profileResponse,
            @Header("Authorization") String auth
    );

    @POST("reaction/{post_id}")
    Call<ReactResponse> react(
            @Path("post_id") String post_id,
            @Header("Authorization") String auth,
            @Body ReactResponse reactResponse
    );
    @GET("profile")
    Call<ProfileResponse> getprofile(
            @Header("Authorization") String auth
    );
    @PUT("profile/username")
    Call<UpadateUsername> updateUsername(
            @Header("Authorization") String auth,
            @Body UpadateUsername upadateUsername
    );

    @Multipart
    @PUT("profile/image")
    Call<ProfileResponse> updateProfilePic(
            @Part MultipartBody.Part profile_image,
            @Header("Authorization") String auth
    );
    @Multipart
    @PUT("profile/cover")
    Call<ProfileResponse> updateCoverPic(
            @Part MultipartBody.Part cover_image,
            @Header("Authorization") String auth
    );
    @POST("bookmark/{post_id}")
    Call<OtpResponse> bookmark(
            @Path("post_id") String post_id,
            @Header("Authorization") String auth,
            @Body Bookmark bookmark
    );
    @GET("reviews/{userId}")
    Call<List<Complements>> getComplement(
            @Path("userId") String userId,
            @Header("Authorization") String auth
    );
    @POST("reviews/{userId}")
    Call<ComplementRequest> postComplement(
            @Path("userId") String userId,
            @Header("Authorization") String auth,
            @Body ComplementRequest complementRequest
    );
    @POST("shared/{post_id}")
    Call<OtpResponse> share(
            @Header("Authorization") String auth
    );
    @POST("comments/{post_id}")
    Call<CreateComment> createcomment(
            @Path("post_id") String postid,
            @Header("Authorization") String auth,
            @Body CreateComment createComment
    );
    @GET("comments/{post_id}")
    Call<List<Comments>> getcomments(
            @Path("post_id") String postid,
            @Header("Authorization") String auth
    );
    @GET("comments/{post_id}")
    Call<JsonElement> getcomment(
            @Path("post_id") String postid,
            @Header("Authorization") String auth
    );
    @GET("posts/bookmarked")
    Call<List<Feed>> fetchbookmarkedposts(
            @Header("Authorization") String auth,
            @Query("page") String page
    );

    @GET("posts/bookmarked")
    Call<List<Feed>> fetchbookmarkedpostsruser(
            @Header("Authorization") String aauth,
            @Query("user") String userid,
            @Query("page")String page
    );
    @GET("posts/reacted")
    Call<List<Feed>> fetchreactedposts(
            @Header("Authorization") String auth,
            @Query("page")String page
    );
    @GET("posts/reacted")
    Call<List<Feed>> fetchreactedpostsofruser(
            @Header("Authorization") String auth,
            @Query("user") String userid,
            @Query("page")String page
    );
    @GET("posts")
    Call<List<Feed>> getallposts(
            @Header("Authorization") String auth,
            @Query("page") String page
    );
    @GET("posts")
    Call<List<Feed>> getalluserposts(
            @Header("Authorization") String auth,
            @Query("user") String userid,
            @Query("page")String page
    );
    @GET("posts")
    Call<List<Feed>> getposts(
            @Body RUser rUser
    );

    @GET("story")
    Call<List<Storymain>> getallstory(
            @Header("Authorization") String auth
    );

    @POST("follow")
    Call<FollowModel> followunfollow(
            @Header("Authorization") String auth,
            @Body FollowModel followModel
    );

    @GET("following")
    Call<List<Follower>> getfollower(
            @Header("Authorization") String auth
    );
    @GET("following")
    Call<List<Follower>> getfollowerrandom(
            @Header("Authorization") String auth,
            @Query("user") String userid
    );
    @GET("followers")
    Call<List<Follower>> getfollowingrandom(
            @Header("Authorization") String auth,
            @Query("user") String userid
    );
    @GET("followers")
    Call<List<Follower>> getfollowing(
            @Header("Authorization") String auth
    );
    @GET("hashtag/trending")
    Call<List<Trendinghashtag>> gethashtags(
            @Header("Authorization") String auth
    );

    @GET("search")
    Call<List<UserSearch>> getusersearh(
            @Header("Authorization") String auth,
            @Query("type") String type,
            @Query("search") String search
    );
    @GET("search")
    Call<List<String>> gethashtagsearh(
            @Header("Authorization") String auth,
            @Query("type") String type,
            @Query("search") String search
    );
    @GET("profile")
    Call<ProfileResponse> getrandomprofile(
            @Header("Authorization") String auth,
            @Query("username") String userid
    );
    @GET("notification")
    Call<List<Notification>> getnotification(
            @Header("Authorization") String auth,
            @Query("type") String type
    );
    @GET("hashtag/post")
    Call<List<Feed>> getviews(
            @Header("Authorization") String auth,
            @Query("hashtag") String hashtag
    );
    @GET("hashtag/post")
    Call<List<Feed>> gethash(
            @Header("Authorization") String auth,
            @Query("hashtag") String hashtag
    );
    @GET("post/{post_id}")
    Call<Feed> getPostbyId(
            @Path("post_id") String postid,
            @Header("Authorization") String auth
    );
    @DELETE("post/{post_id}")
    Call<Delete> deletepostbyid(
                    @Path("post_id") String postid,
                    @Header("Authorization") String auth
    );
    @GET("top/memer")
    Call<DataUser> topUser(
            @Header("Authorization") String auth
    );
    @GET("top/meme")
    Call<DataMeme> topMeme(
            @Header("Authorization") String auth
    );
    @POST("report")
    Call<Report> report(
            @Header("Authorization") String auth,
            @Body Report report
    );
    @GET("notification/count")
    Call<NotificationCount> getCount(
            @Header("Authorization") String auth
    );
    @GET("transactions")
    Call<Redeem> getRedeem(
            @Header("Authorization") String auth
    );

    @POST("requests/redeem")
    Call<CreditsRedeem> requestRedeem(
            @Header("Authorization") String auth,
            @Body CreditsRedeem creditsRedeem
    );
    @GET("requests/redeem/all")
    Call<Redeem> gethistory(
            @Header("Authorization") String auth
    );

    @POST("login/methods")
    Call<Identifier> getmethods(
            @Body Identifier identifier
    );
    @POST("register")
    Call<RegisterUser> register(
            @Body RegisterUser registerUser
    );

    @POST("verifyPassword")
    Call<PasseordLogin> verifyPassword(
            @Body PasseordLogin passeordLogin
    );
    @POST("reviews_reaction/{review_id}")
    Call<ReactResponse> likeReview(
            @Path("review_id") String reviewId,
            @Header("Authorization") String auth,
            @Body ReactResponse reactResponse
    );

    @POST("fcm/token")
    Call<FCMResponse> postfcmtoke(
            @Header("Authorization") String auth,
            @Body FCMResponse fcmResponse
    );
}
