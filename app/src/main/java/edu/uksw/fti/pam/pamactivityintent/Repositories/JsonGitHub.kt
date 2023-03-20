package edu.uksw.fti.pam.pamactivityintent.Repositories

import edu.uksw.fti.pam.pamactivityintent.Model.Object.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface JsonGitHub {
    //homeScreen
    @GET("populer")
    suspend fun getPopular(): List<PopularModel>

    @GET("terbaru")
    suspend fun getTerbaru(): List<TerbaruModel>

    @GET("musiman")
    suspend fun getMusiman(): List<MusimanModel>

    @GET("added")
    suspend fun getBaruTambah(): List<BaruTambahModel>

    //SearchScreen

    @GET("sortby")
    suspend fun getSortBy():List<SortByModel>





    companion object{
        var _apiClient:JsonGitHub? = null

        fun getClient(): JsonGitHub{
            if(_apiClient == null){
                _apiClient = Retrofit.Builder()
                    .baseUrl("https://my-json-server.typicode.com/YuwFi/JsonMangadex/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(JsonGitHub::class.java)
            }
            return  _apiClient!!
        }
    }
}