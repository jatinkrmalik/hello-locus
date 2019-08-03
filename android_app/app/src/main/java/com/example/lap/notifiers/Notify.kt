package com.example.lap.notifiers

open class Notify(val identifier: String = "", vararg val arguments: Any?)
class Loader(val loading: Boolean) : Notify()
class NotifyException(val exception: Exception) : Notify()

object NotifierIdentifier {
}