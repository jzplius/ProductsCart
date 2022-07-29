package com.products.cart.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.products.cart.data.model.db.Product
import com.products.cart.utils.AppConstants

@Dao
interface ProductsDAO {
    @Insert
    fun insert(product: Product)

    @Query("select * from ${AppConstants.TABLE_NAME_PRODUCT}")
    fun loadAll(): LiveData<MutableList<Product>>

    @Query("delete from ${AppConstants.TABLE_NAME_PRODUCT} where id = :id")
    fun delete(id: Int)

    @Query("delete from ${AppConstants.TABLE_NAME_PRODUCT}")
    fun deleteAll()

//    @Query("select COUNT(*) from ${AppConstants.TABLE_NAME_PRODUCT} where id = :id")
//    fun isExist(id: Int): Single<Int>

    @Query("select COUNT(*) from ${AppConstants.TABLE_NAME_PRODUCT} where id = :id")
    fun isTestExist(id: Int): Int

    @Query("select SUM (case when costPrice is null then retailPrice else costPrice end) as total from ${AppConstants.TABLE_NAME_PRODUCT}")
    fun countSum(): Int
}