package com.example.lap.notifiers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class Notifier(val viewModelScope: CoroutineScope) {

    private val channel = BroadcastChannel<Notify>(Channel.BUFFERED)
    private val subscription = channel.openSubscription()

    fun notify(event: Notify, coroutineContext: CoroutineContext = Dispatchers.IO) {
        viewModelScope.launch(coroutineContext) {
            channel.send(event)
        }
    }

    fun recieve(
        coroutineContext: CoroutineContext = Dispatchers.Main,
        callback: (event: Notify) -> Unit
    ) {
        viewModelScope.launch(coroutineContext) {
            subscription.consumeEach { event ->
                callback(event)
            }
        }
    }

    fun close() {
        subscription.cancel()
        channel.cancel()
    }
}