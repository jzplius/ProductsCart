package com.products.cart.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment() {

    private lateinit var viewDataBinding: T
    private lateinit var navController: NavController
    private lateinit var rootView: View
    private lateinit var baseContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseContext = context
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        rootView = viewDataBinding.root
        return rootView
    }

    abstract val layoutId: Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        viewDataBinding.setVariable(bindingVariable, getViewModel())
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.executePendingBindings()
    }

    abstract val bindingVariable: Int

    fun getViewDataBinding(): T {
        return viewDataBinding
    }

    fun getNavController(): NavController {
        return navController
    }

    abstract fun getViewModel(): V

    fun getRootView(): View {
        return rootView
    }

    fun getBaseContext(): Context {
        return baseContext
    }
}