package com.example.loginapp.ui.login

import android.content.Intent
import androidx.core.view.isVisible
import com.example.loginapp.databinding.ActivityLoginBinding
import com.example.loginapp.ui.common.Status
import com.example.loginapp.ui.common.base.ViewModelActivity
import com.example.loginapp.ui.home.HomeActivity
import com.google.android.material.snackbar.Snackbar

class LoginActivity : ViewModelActivity<LoginViewModel, ActivityLoginBinding>(
    { ActivityLoginBinding.inflate(it) }
) {

    override fun getViewModelClass(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun initViews() {
        super.initViews()
        binding.btnLogin.setOnClickListener {
            hideKeyboard()
            if (checkInputValidation()) {
                viewModel.login(
                    binding.etUserName.text.toString(),
                    binding.etPassword.text.toString()
                )
            }
        }
    }

    private fun checkInputValidation(): Boolean {
        var isValid = true
        if (binding.etUserName.text.isNullOrBlank()) {
            binding.tiUserName.error = "Please enter username"
            isValid = false
        } else {
            binding.tiUserName.error = ""
        }
        if (binding.etPassword.text.isNullOrBlank()) {
            binding.tiPassword.error = "Please enter password"
            isValid = false
        } else {
            binding.tiPassword.error = ""
        }
        return isValid
    }

    override fun observeLiveData() {
        super.observeLiveData()
        viewModel.loginLiveData.observe(this, {
            when (it.status) {
                Status.LOADING -> showLoading(true)
                Status.SUCCESS -> handleSuccess()
                Status.ERROR -> it.message?.let(this::handleError)
            }
        })
    }

    private fun showLoading(isShow: Boolean) {
        binding.loading.isVisible = isShow
        binding.btnLogin.isEnabled = !isShow
    }

    private fun handleError(message: String) {
        showLoading(false)
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    private fun handleSuccess() {
        showLoading(false)
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}
