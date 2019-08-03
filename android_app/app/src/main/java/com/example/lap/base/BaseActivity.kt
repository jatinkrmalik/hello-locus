package com.example.lap.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.lap.notifiers.Loader
import com.example.lap.notifiers.Notify
import com.example.lap.notifiers.NotifyException
import com.example.lap.utils.Utility
import org.koin.core.KoinComponent

abstract class BaseActivity : AppCompatActivity(), KoinComponent {
    private lateinit var baseBinding: ViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preDataBinding()
        if (!dataBinding) {
            setContentView(layoutResource)
        } else {
            baseBinding = DataBindingUtil.setContentView(this, layoutResource)
        }

        setBindings()
        initView()

        getViewModel()?.let {
            it.notifier.recieve { event ->
                when (event) {
                    is NotifyException -> {
                        event.exception.message?.let { msg ->
                            Utility.showErrorSnackBar(getBinding().root, this, msg)
                        }
                    }
                    is Loader -> {
                        if (event.loading) {
                            Utility.showProgressDialog(this)
                        } else {
                            Utility.hideProgressDialog()
                        }
                    }
                    else -> {
                        onNotificationReceived(event)
                    }
                }
            }
        }
    }

    open fun preDataBinding() {

    }

    override fun onStart() {
        super.onStart()
        if (registerEventBus) {
        }
    }

    override fun onStop() {
        if (registerEventBus) {
        }
        super.onStop()
    }

    fun getBinding(): ViewDataBinding {
        return baseBinding
    }

    open val registerEventBus = false

    abstract val dataBinding: Boolean
    abstract val layoutResource: Int
    abstract fun setBindings()
    abstract fun onNotificationReceived(data: Notify)
    abstract fun getViewModel(): BaseViewModel?
    abstract fun initView()

    inline fun <reified T> lazyBinding(): Lazy<T> = lazy { getBinding() as T }

}