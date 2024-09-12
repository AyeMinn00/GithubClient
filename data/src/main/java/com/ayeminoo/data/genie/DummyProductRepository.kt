package com.ayeminoo.data.genie

import android.content.Context
import com.ayeminoo.common.di.IoDispatcher
import com.ayeminoo.data.utils.AssetUtil
import com.ayeminoo.domain.Resource
import com.ayeminoo.domain.genie.Product
import com.ayeminoo.domain.genie.ProductRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

class DummyProductRepository @Inject constructor(
    @ApplicationContext
    private val context: Context,
    @IoDispatcher private
    val dispatcher: CoroutineDispatcher
) : ProductRepository {

    private var products: List<Product> = emptyList()

    override suspend fun list(): Resource<List<Product>> = withContext(dispatcher) {
        delay(1000)
        if (products.isEmpty()) refreshProducts()
        Resource.Success(
            data = products
        )
    }

    override suspend fun detail(id: Int): Resource<Product> = withContext(dispatcher) {
        delay(1000)
        if (products.isEmpty()) refreshProducts()
        val found = products.find { item -> item.id == id }
        Resource.Success(data = found!!)
    }

    private fun refreshProducts() {
        val json = AssetUtil.readTextFileFromAssets(context, "products.json")
            ?: return
        val parser = Json { ignoreUnknownKeys = true }
        val prd = parser.decodeFromString<List<ProductDto>>(json)
        products = prd.toDomain()
    }

}