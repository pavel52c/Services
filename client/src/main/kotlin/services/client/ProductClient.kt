package services.client

import org.springframework.ws.client.core.support.WebServiceGatewaySupport
import services.client.dto.CreateProductDTO
import soap.server.CreateProductRequest
import soap.server.GetProductResponse
import soap.server.GetProductRequest

class ProductClient : WebServiceGatewaySupport() {
    fun getProducts(name: String): GetProductResponse {
        val request = GetProductRequest()
        request.name = name
        return webServiceTemplate.marshalSendAndReceive(
            "http://localhost:3040/ws/product",
            request
        ) as GetProductResponse
    }

    fun createProduct(body: CreateProductDTO) {
        val request = CreateProductRequest().apply {
            name = body.name
            description = body.description
            price = body.price
        }

        webServiceTemplate.marshalSendAndReceive(
            "http://localhost:3040/ws/product",
            request
        )
    }
}