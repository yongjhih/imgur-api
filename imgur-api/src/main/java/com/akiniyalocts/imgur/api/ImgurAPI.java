package com.akiniyalocts.imgur.api;

import com.akiniyalocts.imgur.api.model.*;
import com.akiniyalocts.imgur.api.model.Response;
import com.akiniyalocts.imgur.api.model.post.AlbumResponse;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Interface representing the imgur api methods
 */
public interface ImgurAPI {

    @GET("/album/{id}")
    void getAlbumInfo(@NonNull @Path("id") String id,
                      @NonNull Callback<Response<Album>> cb);

    @GET("/album/{id}/images")
    void getAlbumImages(@NonNull @Path("id") String albumId,
                        @NonNull Callback<Response<List<Image>>> cb);

    @GET("/album/{albumId}/image/{imageId}")
    void getAlbumImage(@NonNull @Path("albumId") String albumId,
                       @NonNull @Path("imageId") String imageId,
                       @NonNull Callback<Response<Image>> cb);

    @POST("/album")
    void createAlbum(@Body com.akiniyalocts.imgur.api.model.post.Album album,
                     @NonNull Callback<Response<AlbumResponse>> cb);

    @PUT("/album/{album}")
    void updateAlbum(@NonNull @Path("album") String idOrDeleteHash,
                     @Body com.akiniyalocts.imgur.api.model.post.Album album,
                     @NonNull Callback<Response> cb);

    @retrofit.http.Retrofit.DELETE("/album/{album}")
    void deleteAlbum(@NonNull @Path("album") String idOrDeleteHash,
                     @NonNull Callback<Response> cb);

    @POST("/album/{id}/favorite")
    void favoriteAlbum(@NonNull @Path("id") String albumId,
                       @NonNull Callback<Response> cb);

    @POST("/album/{album}")
    void setAlbumImages(@NonNull @Path("album") String idOrDeleteHash,
                        @Body String[] imageIds,
                        @NonNull Callback<Response> cb);

    @PUT("/album/{album}/add")
    void addImagesToAlbum(@NonNull @Path("album") String idOrDeleteHash,
                          @Body String[] imageIds,
                          @NonNull Callback<Response> cb);

    @retrofit.http.Retrofit.DELETE("/album/{album}/remove_images")
    void deleteImagesFromAlbum(@NonNull @Path("album") String idOrDeleteHash,
                               @Body String[] imageIds,
                               @NonNull Callback<Response> cb);

    /**
     * To make requests for the current account, you may use me as the {username} parameter.
     * For example, https://api.imgur.com/3/account/me/images will request all the images
     * for the account that is currently authenticated.
     *
     * @param username Username
     *
     * @see https://api.imgur.com/endpoints/account#current
     */
    @GET("/account/{username}/images")
    void getImages(@NonNull @Path("username") String username, @NonNull Callback<List<Image>> cb);

    /**
     * Request standard user information.
     * If you need the username for the account that is logged in,
     * it is returned in the request for an access token.
     * Note: This endpoint also supports the ability to lookup account base info by account ID.
     * To do so, pass the query parameter account_id.
     *
     * @param username Username
     *
     * @see https://api.imgur.com/endpoints/account#account
     */
    @GET("/account/{username}")
    void getAccount(@NonNull @Path("username") String username, @NonNull Callback<Account> cb);

    /**
     * Return the images the user has favorited in the gallery.
     *
     * @param page optional allows you to set the page number so you don't have to retrieve all the data at once.
     * @param sort optional 'oldest', or 'newest'. Defaults to 'newest'
     *
     * @see https://api.imgur.com/endpoints/account#account-gallery-favorites
     */
    @GET("/account/{username}/gallery_favorites/{page}/{sort}")
    void getGalleryImages(@NonNull @Path("username") String username, @NonNull @Path("page") int page, @NonNull @Path("sort") String sort, @NonNull Callback<List<GalleryImage>> cb);

    /**
     * Get list of all conversations for the logged in user.
     *
     * @see https://api.imgur.com/endpoints/conversation#conversation-list
     */
    @GET("/conversations")
    void getGalleryImages(@NonNull Callback<List<Conversation>> cb);

    /**
     * Get the list of default memes.
     *
     * @see https://api.imgur.com/endpoints/memegen#defaults
     */
    @GET("/memegen/defaults")
    void getMemes(@NonNull Callback<List<Image>> cb);

    /**
     * Get the list of default topics.
     *
     * @see https://api.imgur.com/endpoints/topic#defaults
     */
    @GET("/topics/defaults")
    void getTopics(@NonNull Callback<List<Topic>> cb);
}
