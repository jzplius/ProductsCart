package com.products.cart.utils

import com.products.cart.BuildConfig

object AppConstants {

    const val PREF_NAME = BuildConfig.APPLICATION_ID + "_pref"
    const val SELECTED_THEME = "selected_theme"
    const val DARK_THEME = "dark_theme"
    const val LIGHT_THEME = "light_theme"
    const val DEFAULT_THEME = "default_theme"

    const val DATABASE_NAME = "db_name"
    const val TABLE_NAME_PRODUCT = "product"

    const val VIEW_TYPE_EMPTY = 0
    const val VIEW_TYPE_NORMAL = 1
    const val VIEW_TYPE_BROWSE = 2
}