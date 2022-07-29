package com.products.cart.ui.main.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.products.cart.data.model.db.Product
import com.products.cart.databinding.ItemCartProductBinding
import com.products.cart.databinding.ItemEmptyShoppingCartBinding
import com.products.cart.ui.base.BaseItemListener
import com.products.cart.ui.base.BaseRecyclerViewAdapter
import com.products.cart.ui.base.BaseViewHolder
import com.products.cart.utils.AppConstants

class ShoppingCartAdapter(
    items: MutableList<Product>,
    private val listener: Listener
) : BaseRecyclerViewAdapter<Product>(items) {

    override fun getItemViewType(position: Int): Int {
        return if (getItems().isNotEmpty()) AppConstants.VIEW_TYPE_NORMAL else AppConstants.VIEW_TYPE_EMPTY
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            AppConstants.VIEW_TYPE_NORMAL -> MoviesViewHolder(
                ItemCartProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> EmptyViewHolder(
                ItemEmptyShoppingCartBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    interface Listener : BaseItemListener<Product>

    override fun addItems(items: List<Product>) {
        this.clearItems()
        super.addItems(items)
    }

    inner class MoviesViewHolder(private val binding: ItemCartProductBinding) :
        BaseViewHolder(binding.root), CartItemViewModel.CartItemClickListener {

        override fun onBind(position: Int) {
            val product: Product = getItems()[position]
            binding.viewModel = CartItemViewModel(product, this)
            binding.executePendingBindings()
        }

        override fun onAddClick(item: Product) {
            // Do nothing
        }

        override fun onItemClick(view: View, item: Product) {
            listener.onItemClick(view, item)
        }

    }

    inner class EmptyViewHolder(private val itemEmptyFavoriteMovieBinding: ItemEmptyShoppingCartBinding) :
        BaseViewHolder(itemEmptyFavoriteMovieBinding.root) {

        override fun onBind(position: Int) {
            itemEmptyFavoriteMovieBinding.favoriteEmptyItemViewModel = EmptyItemViewModel()
            itemEmptyFavoriteMovieBinding.executePendingBindings()
        }
    }

}