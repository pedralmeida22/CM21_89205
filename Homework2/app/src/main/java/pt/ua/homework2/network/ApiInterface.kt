package pt.ua.homework2.network

import pt.ua.homework2.datamodel.CallResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("open-data/forecast/meteorology/cities/daily/{city}.json")
    fun getData(@Path("city") city: String) : Call<CallResponse.City>

    companion object {

        var BASE_URL = "https://api.ipma.pt/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiInterface::class.java)

        }
    }
}