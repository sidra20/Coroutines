package com.sidrakotlin.coroutines

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



       GlobalScope.launch(Dispatchers.Main) {
           delay(1000)

           val job = CoroutineScope(Dispatchers.IO).launch {
               Log.d(TAG, "onCreate: Hello")
               delay(1000)
               Log.d(TAG, "onCreate: Hi")

           }
           job.join()
           Log.d(TAG, "onCreate: coroutine cancel ${job.cancel()}")


           delay(1000)
           val job2 = CoroutineScope(Dispatchers.IO).async {
               Log.d(TAG, "onCreate: async Hello")
               delay(1000)
               Log.d(TAG, "onCreate:async  Hi")

           }
           Log.d(TAG, "onCreate: async completed ${job2.await()}")
           Log.d(TAG, "onCreate: async cancel ${job2.cancel()}")

           coroutineTask()
           delay(1000)
           runBlockTask()



       }

    }
    private suspend fun coroutineTask()
    {
        withContext(Dispatchers.IO){
            Log.d(TAG, "coroutineTask: Withcontext start")

            delay(1000)
            Log.d(TAG, "coroutineTask: Withcontext end")

        }
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            Log.d(TAG, "cooutineScope: Start")
        }
        Log.d(TAG, "cooutineScope: Stop")
    }

    private suspend fun runBlockTask()
    {
        runBlocking {
                delay(1000)
                Log.d(TAG, "runBlockTask: Start")
        }
        Log.d(TAG, "runBlockTask: Stop")

    }




}