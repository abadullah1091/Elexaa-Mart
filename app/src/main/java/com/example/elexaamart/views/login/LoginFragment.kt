package com.example.elexaamart.views.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.elexaamart.R
import com.example.elexaamart.base.BaseFragment
import com.example.elexaamart.core.DataState
import com.example.elexaamart.data.models.UserLogin
import com.example.elexaamart.data.models.UserRegister
import com.example.elexaamart.databinding.FragmentLoginBinding
import com.example.elexaamart.isEmply
import com.example.elexaamart.views.dashboard.seller.sellerdashboard
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    override fun setListner() {

        with(binding){
            btnRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
            btnLogin.setOnClickListener {
                etEmail.isEmply()
                etPassword.isEmply()
                if (!etEmail.isEmply() && !etPassword.isEmply()){

                   Toast.makeText(context, "Successfully filled all fields", Toast.LENGTH_LONG).show()
                    val user = UserLogin(
                        etEmail.text.toString(),
                        etPassword.text.toString(),
                    )
                    viewModel.userLogin(user)


                }
            }
        }

    }

    override fun allObserber() {
        viewModel.loginResponse.observe(viewLifecycleOwner){
            when(it){
                is DataState.Error -> {
                    loading.dismiss()
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }

                is DataState.Loading -> {
                    loading.show()
                    Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
                }
                is DataState.Success -> {
                    loading.dismiss()
                    Toast.makeText(context, "Created User: ${it.data}", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(requireContext(), sellerdashboard::class.java))
                    requireActivity().finish()
                }
            }
        }

    }


}