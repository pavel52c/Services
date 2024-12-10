package services.client.dto

data class OrderDTORS(
    val id: Long,
    val number: Int,
    val dateReceive: String?,
    val products: String,
)