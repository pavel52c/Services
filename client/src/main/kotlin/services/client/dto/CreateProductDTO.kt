package services.client.dto

data class CreateProductDTO(
    val name: String,
    val description: String?,
    val price: Double,
)