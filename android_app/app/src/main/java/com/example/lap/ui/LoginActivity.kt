package com.example.lap.ui

import com.example.lap.R
import com.example.lap.base.BaseActivity
import com.example.lap.base.BaseViewModel
import com.example.lap.databinding.ActivityMainBinding
import com.example.lap.notifiers.Notify
import com.example.lap.viewmodel.LoginViewModel
import org.koin.core.inject

class LoginActivity : BaseActivity() {
    override val dataBinding: Boolean = true
    override val layoutResource: Int = R.layout.activity_main
    private val loginViewModel: LoginViewModel by inject()

    override fun getViewModel(): BaseViewModel = loginViewModel

    private val binding: ActivityMainBinding by lazyBinding()

    override fun setBindings() {
        binding.model = loginViewModel
    }

    override fun onNotificationReceived(data: Notify) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initView() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
