package services.client.dto

data class ProductDTO(
    val id: Long?,
    val title: String,
    val description: String?,
    val price: Double,
)
