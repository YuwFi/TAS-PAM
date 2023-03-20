package edu.uksw.fti.pam.pamactivityintent.Repositories

import edu.uksw.fti.pam.pamactivityintent.Model.Object.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface JsonGitHub2 {

    @GET("genre")
    suspend fun getGenre(): List<GenreModel>

    @GET("search")
    suspend fun getSearch(): List<SearchModel>

    @GET("read")
    suspend fun getRead(): List<ReadModel>

    @GET("bookmark")
    suspend fun getBookmark(): List<BookmarkModel>

    @GET("community")
    suspend fun getCommunity(): List<CommunityModel>


    companion object{
        var _apiClient:JsonGitHub2? = null

        fun getClient(): JsonGitHub2{
            if(_apiClient == null){
                _apiClient = Retrofit.Builder()
                    .baseUrl("https://my-json-server.typicode.com/YuwFi/JsonMangadex2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(JsonGitHub2::class.java)
            }
            return  _apiClient!!
        }
    }
}