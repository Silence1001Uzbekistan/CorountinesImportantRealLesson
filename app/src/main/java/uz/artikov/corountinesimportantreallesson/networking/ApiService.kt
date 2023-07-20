package uz.artikov.corountinesimportantreallesson.networking

import retrofit2.Call
import retrofit2.http.GET
import uz.artikov.corountinesimportantreallesson.models.UserItem

interface ApiService {

    //using corountine

    @GET("users")
    suspend fun getUser(): List<UserItem>

}