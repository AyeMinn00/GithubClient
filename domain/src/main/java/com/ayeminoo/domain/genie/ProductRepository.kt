package com.ayeminoo.domain.genie

import com.ayeminoo.domain.Resource

interface ProductRepository {
    suspend fun list(): Resource<List<Product>>
    suspend fun detail(id: Int): Resource<Product>
}