package com.example.elexaamart.base

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Process
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.elexaamart.core.DataState

@Suppress("DEPRECATION")
abstract class BaseFragment<VB : ViewBinding>(
    private val bindingInflater: (inflater:LayoutInflater)  ->VB //Higher Order Function
) :Fragment() {

    private var _binding : VB? = null

    val binding:VB
        get() =  _binding as VB

    lateinit var loading:ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        loading = ProgressDialog(requireContext())


        setListner()
        allObserber()
        return binding.root
    }
    abstract fun setListner()
    abstract fun allObserber()
}