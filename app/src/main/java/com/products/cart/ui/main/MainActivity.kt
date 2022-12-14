package com.products.cart.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.products.cart.R
import com.products.cart.databinding.ActivityMainBinding
import com.products.cart.ui.base.BaseActivity
import com.products.cart.utils.AppConstants
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private val mainViewModel: MainViewModel by viewModel { parametersOf(SavedStateHandle(mapOf())) }
    private val sharedPreferences: SharedPreferences by inject()
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        setApplicationTheme()
        super.onCreate(savedInstanceState)
        val toolbar: Toolbar = findViewById(R.id.toolbarX)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = getViewDataBinding().drawerLayout
        val navView: NavigationView = getViewDataBinding().navView
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.browseProductsItem, R.id.addedProductsCartItem
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun setApplicationTheme() {
        when (sharedPreferences.getString(
            AppConstants.SELECTED_THEME,
            AppConstants.DEFAULT_THEME
        )) {
            AppConstants.DARK_THEME -> setTheme(R.style.AppTheme)
            AppConstants.LIGHT_THEME -> setTheme(R.style.LightAppTheme)
            else -> {
                // Hit Shared Preference For First Time (set dark theme as default)
                setTheme(R.style.AppTheme)
                with(receiver = sharedPreferences.edit()) {
                    putString(AppConstants.SELECTED_THEME, AppConstants.DARK_THEME)
                    apply()
                }
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): MainViewModel {
        return mainViewModel
    }


}