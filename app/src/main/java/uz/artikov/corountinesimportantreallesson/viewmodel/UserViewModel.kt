package uz.artikov.corountinesimportantreallesson.viewmodel

import android.service.autofill.UserData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import uz.artikov.corountinesimportantreallesson.models.UserItem
import uz.artikov.corountinesimportantreallesson.modelsTwo.UserWithPost
import uz.artikov.corountinesimportantreallesson.networking.ApiClient
import uz.artikov.corountinesimportantreallesson.networking.ApiService

class UserViewModel : ViewModel() {

    private val apiService = ApiClient.getRetrofit().create(ApiService::class.java)

    //Bir necha Apidan Olib kelish uchun SYNC ishlatiladi va parallel ishlaydi
    private val liveData = MutableLiveData<UserWithPost>()

    /*    //Agar bitta Api bsa foydalanish mumkin bundan
        private val liveData = MutableLiveData<List<UserItem>>()*/

    init {

        fetchUser()

    }


    //Scope
    //Activity destroy bganda xatolik bermasligi un Coroutineni reject qiladi
    private fun fetchUser() {


        viewModelScope.launch {

            try {

                //async parallel tarzda bajaradi
                val ApiOne = async { apiService.getUser() }
                val ApiTwo = async { apiService.getPosts() }

                val userList = ApiOne.await()
                val postList = ApiTwo.await()

                coroutineScope {
                    liveData.postValue(UserWithPost(userList, postList))
                }

                /*     //Agar bitta Api bsa foydalanish mumkin bundan
                     coroutineScope {
                         liveData.postValue(apiService.getUser())
                     }*/


            } catch (e: Exception) {

                e.printStackTrace()

            }


        }

    }

    //Bir necha Api lar unx
    fun getUsersLiveData(): LiveData<UserWithPost> {

        return liveData

    }
    /*//Bitta Api un
    fun getUsersLiveData(): LiveData<List<UserItem>> {

        return liveData

    }*/

}