package com.emiratesauction.weather.helper

import kotlinx.coroutines.*

suspend fun io(block: suspend CoroutineScope.() -> Unit) {
    withContext(Dispatchers.IO) {
        block.invoke(this)
    }
}

suspend fun ui(block: suspend CoroutineScope.() -> Unit) {
    withContext(Dispatchers.Main) {
        block.invoke(this)
    }
}