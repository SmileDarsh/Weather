package com.emiratesauction.weather.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch

@Throws(InterruptedException::class)
fun <T> LiveData<T>.assertLiveData(onDataReceived: (T) -> Unit) {
    val liveData: LiveData<T> = this
    val data = arrayOfNulls<Any>(1)
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            if (o != null) {
                data[0] = o
                latch.countDown()
                liveData.removeObserver(this)
                onDataReceived(o)
            }
        }
    }
    liveData.observeForever(observer)
}
