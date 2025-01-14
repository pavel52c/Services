package services.client.dto

import soap.server.Product

data class OrderDTO(
    val id: Long?,
    val number: Int,
    val dateReceive: String?,
    val products: List<Product>?,
)