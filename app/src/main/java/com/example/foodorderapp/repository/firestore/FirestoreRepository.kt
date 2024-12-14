package com.example.foodorderapp.repository.firestore

import com.example.foodorderapp.data.DbResult
import com.example.foodorderapp.data.ProductItemData
import kotlinx.coroutines.flow.Flow

interface FirestoreRepository {
    suspend fun getAllProduct() : Flow<DbResult<List<ProductItemData>>>
    suspend fun getCategoryProduct(category : String) : Flow<DbResult<List<ProductItemData>>>

}