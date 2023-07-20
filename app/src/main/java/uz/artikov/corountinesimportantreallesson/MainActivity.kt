package uz.artikov.corountinesimportantreallesson

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.artikov.corountinesimportantreallesson.databinding.ActivityMainBinding
import uz.artikov.corountinesimportantreallesson.models.UserItem
import uz.artikov.corountinesimportantreallesson.networking.ApiClient
import uz.artikov.corountinesimportantreallesson.networking.ApiService
import kotlin.coroutines.CoroutineContext


//CoroutineScope responseni kutmasdan chiqib ketganda xatolikni oldini oladi

class MainActivity : AppCompatActivity(), CoroutineScope {

    lateinit var apiService: ApiService
    lateinit var binding: ActivityMainBinding

    //JOB
    // Destroy activity bganda  reject ni amalga oshiradi
    //Har doim Scope bn birga ishlaydi
    lateinit var job: Job

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        job = Job()
        apiService = ApiClient.getRetrofit().create(ApiService::class.java)


        //Scope dan foydalanib Api dan ma'lumotlarni olib kelish va launch
        // withContext bizga ketma ketlik ishlashga yordam beradi fun larni


        //Job qo'shganimiz sabab,launchni to'g'ri o'zini ishlata olamiz
        launch {

            try {

                //coroutineScope request yuborganda tarmoq uzilish to'satdan sodir bsa,
                // shuni oldini oladi

                coroutineScope {

                    val userlist = apiService.getUser()
                    withContext(Dispatchers.Main) {
                        showUser(userlist)
                    }

                }

            } catch (e: Exception) {

                e.printStackTrace()

            }


        }


    }


    //async fun larni parallel ishlashga yordam beradi,natijani qaytarib ham beradi


    private fun showUser(userlist: List<UserItem>) {

        binding.tvId.text = userlist.toString()

    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    //Exception,har doim qo'shilishi shart chunki biz bila olmaymiz xato
    // bo'lishi yo yo'q


    //responseni kutmasdan chiqib ketganda xatolikni oldini oladi
    //OnDestroy yo OnPauseni ham sinab ko'r
    override fun onPause() {
        super.onPause()
        job.cancel()
    }


}