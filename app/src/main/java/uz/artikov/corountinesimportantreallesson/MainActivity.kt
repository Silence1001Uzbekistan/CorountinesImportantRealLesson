package uz.artikov.corountinesimportantreallesson

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
import uz.artikov.corountinesimportantreallesson.viewmodel.UserViewModel
import kotlin.coroutines.CoroutineContext


//CoroutineScope responseni kutmasdan chiqib ketganda xatolikni oldini oladi

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        viewModel.getUsersLiveData().observe(this, Observer {

            binding.tvId.text = it.toString()

        })


    }


}