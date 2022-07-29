package com.products.cart.ui.main.cart

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.products.cart.BR
import com.products.cart.R
import com.products.cart.data.model.db.Product
import com.products.cart.databinding.FragmentShoppingCartBinding
import com.products.cart.ui.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class ShoppingCartFragment :
    BaseFragment<FragmentShoppingCartBinding, ShoppingCartViewModel>(),
    ShoppingCartAdapter.Listener {

    private val shoppingCartViewModel: ShoppingCartViewModel by viewModel {
        parametersOf(
            SavedStateHandle(mapOf())
        )
    }
    private val shoppingCartAdapter = ShoppingCartAdapter(mutableListOf(), this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView(
            getViewDataBinding().favoriteMoviesRv,
            shoppingCartAdapter,
            RecyclerView.VERTICAL
        )

        getViewDataBinding().buttonBrowseProducts.setOnClickListener({
            getViewModel().onBrowseClicked()
            findNavController().navigate(R.id.action_addedProductsCarItem_to_browseProductsItem)
        })
        getViewDataBinding().buttonClearBasket.setOnClickListener(View.OnClickListener {
            getViewModel().onClearClicked()
            Snackbar.make(
                getRootView(),
                "Items removed from shopping basket!",
                Snackbar.LENGTH_SHORT
            ).show()
        })
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
        get() = R.layout.fragment_shopping_cart
    override val bindingVariable: Int
        get() = BR.favoriteMoviesViewModel

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onItemClick(view: View, item: Product) {
        // Do nothing
    }

    override fun onResume() {
        super.onResume()
        // Trigger recalculate of price on return to screen
        getViewModel().invalidateSum()
    }

    override fun getViewModel(): ShoppingCartViewModel {
        return shoppingCartViewModel
    }
}