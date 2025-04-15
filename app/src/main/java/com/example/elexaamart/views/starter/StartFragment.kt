package com.example.elexaamart.views.starter

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.elexaamart.R
import com.example.elexaamart.base.BaseFragment
import com.example.elexaamart.databinding.FragmentStartBinding
import com.example.elexaamart.views.dashboard.seller.sellerdashboard
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment : BaseFragment<FragmentStartBinding>(FragmentStartBinding::inflate) {

   override fun setListner() {

       setUpAutoLogin()

        with(binding){
            btnLogin.setOnClickListener {
                findNavController().navigate(R.id.action_startFragment_to_loginFragment)

            }
            btnRegister.setOnClickListener {
                findNavController().navigate(R.id.action_startFragment_to_registerFragment)

            }
        }

    }

    private fun setUpAutoLogin() {
        FirebaseAuth.getInstance().currentUser?.let {

            startActivity(Intent(requireContext(), sellerdashboard::class.java))
            requireActivity().finish()
        }

    }

    override fun allObserber() {

    }

}