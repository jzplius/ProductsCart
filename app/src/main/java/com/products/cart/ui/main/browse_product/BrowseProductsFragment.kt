package com.products.cart.ui.main.browse_product

import android.os.Bundle
import android.view.View
import androidx.lifecycle.SavedStateHandle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.products.cart.BR
import com.products.cart.R
import com.products.cart.data.model.db.Product
import com.products.cart.databinding.FragmentBrowseMoviesBinding
import com.products.cart.ui.base.BaseFragment
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf


class BrowseProductsFragment : BaseFragment<FragmentBrowseMoviesBinding, BrowseProductsViewModel>(),
    BrowseProductsAdapter.BrowseProductsAdapterListener {

    private lateinit var BrowseProductsViewModel: BrowseProductsViewModel

    private val browseProductsAdapter = BrowseProductsAdapter(mutableListOf(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        BrowseProductsViewModel =
            getViewModel { parametersOf(SavedStateHandle(mapOf())) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewDataBinding().mainSwiperefresh.setOnRefreshListener {
            getViewModel().fetchBrowseProductsList()
            browseProductsAdapter.notifyDataSetChanged()
            getViewDataBinding().mainSwiperefresh.isRefreshing = false
        }

        initRecyclerView(
            getViewDataBinding().moviesRv,
            browseProductsAdapter,
            RecyclerView.VERTICAL
        )
    }

    private fun initRecyclerView(
        recyclerView: RecyclerView,
        adapter: RecyclerView.Adapter<*>,
        orientation: Int
    ) {
        recyclerView.layoutManager = LinearLayoutManager(context, orientation, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    override val layoutId: Int
        get() = R.layout.fragment_browse_movies
    override val bindingVariable: Int
        get() = BR.moviesViewModel

    override fun onAddClick(product: Product) {
        getViewModel().onAddClick(product)
        Snackbar.make(getRootView(), product.name + " added to cart!", Snackbar.LENGTH_SHORT).show()
    }

    override fun onItemClick(view: View, item: Product) {
        // Do nothing
    }

    override fun onRetryClick() {
        getViewModel().fetchBrowseProductsList()
    }

    override fun getViewModel(): BrowseProductsViewModel {
        return BrowseProductsViewModel
    }
}