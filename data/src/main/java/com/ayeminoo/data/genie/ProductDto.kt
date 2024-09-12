package com.ayeminoo.data.genie

import com.ayeminoo.domain.genie.Product
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("category")
    val category: String,
    @SerialName("estimate_cash")
    val estimateCash: Double,
    @SerialName("price_unit")
    val priceUnit: String,
    @SerialName("available_unit")
    val availableUnit: Int,
    @SerialName("tag")
    val tag: List<String>,
    @SerialName("favorite")
    val favorite: Boolean,
    @SerialName("image")
    val image: String? = null
)

fun List<ProductDto>.toDomain() = map { item ->
    Product(
        id = item.id,
        title = item.title,
        description = item.description,
        category = item.category,
        estimateCash = item.estimateCash,
        priceUnit = item.priceUnit,
        availableUnit = item.availableUnit,
        tag = item.tag,
        favorite = item.favorite,
        image = item.image
    )
}