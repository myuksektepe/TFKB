package murat.tfkb.data.network.retrofit

import murat.tfkb.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class RetrofitService {
    companion object{
        private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        val retrofitService: RetrofitApi by lazy {
            retrofit.create(RetrofitApi::class.java)
        }
    }
}