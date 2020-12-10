package com.example.loginapp.ui.home

import com.example.loginapp.R
import com.example.loginapp.databinding.ActivityHomeBinding
import com.example.loginapp.ui.common.Status
import com.example.loginapp.ui.common.base.ViewModelActivity
import com.google.android.material.snackbar.Snackbar

class HomeActivity :
    ViewModelActivity<HomeViewModel, ActivityHomeBinding>({ ActivityHomeBinding.inflate(it) }) {

    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun initData() {
        super.initData()
        viewModel.getUser()
    }

    override fun observeLiveData() {
        super.observeLiveData()
        viewModel.userLiveData.observe(this, { resource ->
            when (resource.status) {
                Status.LOADING -> Unit
                Status.SUCCESS -> {
                    resource.data?.let {
                        binding.tvWelcome.text =
                            getString(R.string.welcome, it.name, it.id.toString(), it.token)
                    }
                }
                Status.ERROR -> resource.message?.let {
                    Snackbar.make(
                        binding.root,
                        it,
                        Snackbar.LENGTH_LONG
                    )
                }
            }
        })
    }
}