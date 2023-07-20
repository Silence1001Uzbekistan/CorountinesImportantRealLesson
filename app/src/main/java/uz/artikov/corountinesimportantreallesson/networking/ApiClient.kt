package uz.artikov.corountinesimportantreallesson.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {


    //Asosiy Api
    //https://jsonplaceholder.typicode.com/users

    const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

}