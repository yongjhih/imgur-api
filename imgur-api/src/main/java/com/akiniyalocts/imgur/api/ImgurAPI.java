package com.akiniyalocts.imgur.api;

import com.akiniyalocts.imgur.api.model.*;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by anthony on 7/26/15.
 */
public interface ImgurAPI {

    /**
     * Get information about a specific album.
     *
     * @param id album id
     *
     * @see https://api.imgur.com/endpoints/album#album
     */
    @GET("/album/{id}")
    void getAlbumInfo(@Path("id") int id, Callback<Album> cb);

    /**
     * Return all of the images in the album
     *
     * @param albumId album id
     *
     * @see https://api.imgur.com/endpoints/album#album-images
     */
    @GET("/album/{id}/images")
    void getAlbumImages(@Path("id") int albumId, Callback<List<Image>> cb);

    /**
     * To make requests for the current account, you may use me as the {username} parameter.
     * For example, https://api.imgur.com/3/account/me/images will request all the images
     * for the account that is currently authenticated.
     *
     * @param username Username
     *
     * @see https://api.imgur.com/endpoints/account#current
     */
    @GET("/account/{id}/images")
    void getImages(@Path("username") String username, Callback<List<Image>> cb);

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
    void getAccount(@Path("username") String username, Callback<Account> cb);

    /**
     * Return the images the user has favorited in the gallery.
     *
     * @param page optional allows you to set the page number so you don't have to retrieve all the data at once.
     * @param sort optional 'oldest', or 'newest'. Defaults to 'newest'
     *
     * @see https://api.imgur.com/endpoints/account#account-gallery-favorites
     */
    @GET("/account/{username}/gallery_favorites/{page}/{sort}")
    void getGalleryImages(@Path("username") String username, @Path("page") int page, @Path("sort") String sort, Callback<List<GalleryImage>> cb);

    /**
     * Get list of all conversations for the logged in user.
     *
     * @see https://api.imgur.com/endpoints/conversation#conversation-list
     */
    @GET("/conversations")
    void getGalleryImages(Callback<List<Conversation>> cb);

    /**
     * Get the list of default memes.
     *
     * @see https://api.imgur.com/endpoints/memegen#defaults
     */
    @GET("/memegen/defaults")
    void getMemes(Callback<List<Image>> cb);

    /**
     * Get the list of default topics.
     *
     * @see https://api.imgur.com/endpoints/topic#defaults
     */
    @GET("/topics/defaults")
    void getTopics(Callback<List<Topic>> cb);
}
