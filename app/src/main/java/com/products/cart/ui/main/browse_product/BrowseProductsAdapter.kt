package com.products.cart.ui.main.browse_product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.products.cart.data.model.db.Product
import com.products.cart.databinding.ItemBrowseProductBinding
import com.products.cart.databinding.ItemEmptyBrowseProductsBinding
import com.products.cart.ui.base.BaseCartItemListener
import com.products.cart.ui.base.BaseEmptyItemListener
import com.products.cart.ui.base.BaseRecyclerViewAdapter
import com.products.cart.ui.base.BaseViewHolder
import com.products.cart.utils.AppConstants

class BrowseProductsAdapter(
    items: MutableList<Product>,
    private val listener: BrowseProductsAdapterListener
) : BaseRecyclerViewAdapter<Product>(items) {

    override fun getItemViewType(position: Int): Int {
        return if (getItems().isNotEmpty()) AppConstants.VIEW_TYPE_BROWSE else AppConstants.VIEW_TYPE_EMPTY
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            AppConstants.VIEW_TYPE_BROWSE -> MoviesBrowseProductsViewHolder(
                ItemBrowseProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> EmptyViewHolder(
                ItemEmptyBrowseProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    interface BrowseProductsAdapterListener : BaseCartItemListener<Product>, BaseEmptyItemListener

    inner class MoviesBrowseProductsViewHolder(private val binding: ItemBrowseProductBinding) :
        BaseViewHolder(binding.root),
        BrowseProductsItemViewModel.BrowseProductsItemListener {

        override fun onBind(position: Int) {
            val movie: Product = getItems()[position]
            binding.movieItemViewModel = BrowseProductsItemViewModel(movie, this)
            binding.executePendingBindings()
        }

        override fun onAddClick(product: Product) {
            listener.onAddClick(product)
        }

        override fun onItemClick(view: View, product: Product) {
            listener.onItemClick(view, product)
        }
    }

    inner class EmptyViewHolder(private val binding: ItemEmptyBrowseProductsBinding) :
        BaseViewHolder(binding.root), EmptyItemViewModel.MovieEmptyItemListener {

        override fun onBind(position: Int) {
            binding.emptyItemViewModel = EmptyItemViewModel(this)
            binding.executePendingBindings()
        }

        override fun onRetryClick() {
            listener.onRetryClick()
        }

    }

}

