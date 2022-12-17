package com.example.androidimpltemplate.ui

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.androidimpltemplate.base.BaseFragment
import com.example.androidimpltemplate.databinding.FragmentMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlin.coroutines.CoroutineContext
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate),
    CoroutineScope {

    private val state =
        MutableStateFlow("empty") // flow to update UI (in our case just print to logcat)

    // called in onViewCreated method. implemented in BaseFragment
    override fun setup() {
        main()
    }

    private fun main() {
        // subscribe to flow updates and print state.value to logcat.
        viewLifecycleOwner.lifecycleScope.launch {
            state.collectLatest {
                Log.d("myTag", "collect state with value: ${state.value}")
            }
        }
        Random.nextBoolean().let { isProcessSync ->
            Log.d("myTag", "${if (isProcessSync) "Sync" else "Async"} process start")
            if (isProcessSync) {
                runSync()
            } else {
                runAsync()
            }
        }
    }

    //  launch 1000 coroutines. Invoke doWork(index/number of coroutine) one after another. Example 1, 2, 3, 4, 5, etc.
    private fun runSync() {
        println("runSync method.")
        launch {
            repeat(THREAD_COUNT) {
                async {
                    doWork(it.toString())
                }.await()
            }
        }
    }

    //  launch 1000 coroutines. Invoke doWork(index/number of coroutine) in async way. Example 1, 2, 5, 3, 4, 8, etc.
    private fun runAsync() {
        println("runAsync method.")
        launch {
            repeat(THREAD_COUNT) {
                launch {
                    doWork(it.toString())
                }
            }
        }
    }

    private suspend fun doWork(name: String) {
        delay(500)
        Log.d("myTag", "doWork func is called with name $name")
        state.update { "$name completed." }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + SupervisorJob() + CoroutineExceptionHandler { _, throwable ->
            Log.d("TAG", "Log $throwable")
        }

    companion object {
        private const val THREAD_COUNT = 1000

        @JvmStatic
        fun newInstance() = MainFragment()
    }
}
