package com.example.elexaamart.views.register


import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.elexaamart.R
import com.example.elexaamart.base.BaseFragment
import com.example.elexaamart.core.DataState
import com.example.elexaamart.data.models.UserRegister
import com.example.elexaamart.databinding.FragmentRegisterBinding
import com.example.elexaamart.isEmply
import com.example.elexaamart.views.dashboard.seller.sellerdashboard
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: RegistrationViewModel by viewModels()


    override fun setListner() {
        with(binding) {
            btnRegister.setOnClickListener {
                etUserName.isEmply()
                etEmail.isEmply()
                etPassword.isEmply()

                if (!etUserName.isEmply() && !etEmail.isEmply() && !etPassword.isEmply()) {

//                    Toast.makeText(context, "Successfully filled all fields", Toast.LENGTH_LONG).show()
                    val user = UserRegister(
                        etUserName.text.toString(),
                        etEmail.text.toString(),
                        etPassword.text.toString(),
                        "Seller",
                        ""
                    )
                    viewModel.userRegistration(user)


                }
            }
        }
    }

    override fun allObserber() {
        registrationObserver()
    }

    //OOAD-> Object Oriented Analysis Design (Code Design)
    fun registrationObserver() {
        viewModel.registrationResponse.observe(viewLifecycleOwner) {
            when (it) {
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