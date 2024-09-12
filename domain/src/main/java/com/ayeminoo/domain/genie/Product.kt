package com.ayeminoo.domain.genie

data class Product(
    val id : Int,
    val title: String,
    val description: String,
    val category: String,
    val estimateCash: Double,
    val priceUnit: String,
    val availableUnit: Int,
    val tag: List<String>,
    val favorite: Boolean,
    val image : String? = null
)