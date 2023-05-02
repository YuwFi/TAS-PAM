package edu.uksw.fti.pam.pamactivityintent.Repositories

import edu.uksw.fti.pam.pamactivityintent.Model.Object.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface JsonGitHub3 {

    @GET("chap")
    suspend fun getChap(): List<ChapModel>



    companion object{
        var _apiClient:JsonGitHub3? = null

        fun getClient(): JsonGitHub3{
            if(_apiClient == null){
                _apiClient = Retrofit.Builder()
                    .baseUrl("https://my-json-server.typicode.com/YuwFi/JsonMangadex3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(JsonGitHub3::class.java)
            }
            return  _apiClient!!
        }
    }
}