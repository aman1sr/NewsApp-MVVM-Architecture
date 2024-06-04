package com.aman.mynewsmvvm_cleanarch.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : BaseViewModel<*>, ViewBindingType : ViewBinding> :
    AppCompatActivity() {

    lateinit var viewModel: T
    private var _binding: ViewBindingType? = null
    protected val binding get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = setupViewBinding(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        setupViewModel()
        setupView(savedInstanceState)
        setupObserver()

    }

    protected open fun setupObserver() {

    }

    abstract fun setupViewModel()
    abstract fun setupView(savedInstanceState: Bundle?)

    abstract fun setupViewBinding(inflater: LayoutInflater): ViewBindingType

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}