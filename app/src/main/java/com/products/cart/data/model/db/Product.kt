package com.products.cart.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.products.cart.utils.AppConstants

@Entity(tableName = AppConstants.TABLE_NAME_PRODUCT)
class Product(
    @PrimaryKey(autoGenerate = true)
    var primaryId: Int = 0,

    @SerializedName("barcode")
    var barcode: String,
    @SerializedName("cost_price")
    var costPrice: Int? = null,
    @SerializedName("description")
    var description: String,


    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("retail_price")
    var retailPrice: Int
)