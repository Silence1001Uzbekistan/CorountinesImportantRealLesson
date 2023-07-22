package uz.artikov.corountinesimportantreallesson.modelsTwo

import android.service.autofill.UserData
import uz.artikov.corountinesimportantreallesson.models.UserItem

data class UserWithPost(
    val userList: List<UserItem>,
    val postList: List<UserPost>
)