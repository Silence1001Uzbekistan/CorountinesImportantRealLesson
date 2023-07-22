package uz.artikov.corountinesimportantreallesson.networking

import retrofit2.Call
import retrofit2.http.GET
import uz.artikov.corountinesimportantreallesson.models.UserItem
import uz.artikov.corountinesimportantreallesson.modelsTwo.UserPost

interface ApiService {

    //using corountine

    @GET("users")
    suspend fun getUser(): List<UserItem>

    @GET("posts")
    suspend fun getPosts(): List<UserPost>

}