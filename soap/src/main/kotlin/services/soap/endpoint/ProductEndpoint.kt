package services.soap.endpoint

import org.springframework.ws.server.endpoint.annotation.Endpoint
import org.springframework.ws.server.endpoint.annotation.PayloadRoot
import org.springframework.ws.server.endpoint.annotation.RequestPayload
import org.springframework.ws.server.endpoint.annotation.ResponsePayload
import product.Product
import product.GetProductRequest
import product.GetProductResponse
import product.CreateProductRequest

const val NAMESPACE_URI = "http://product"

@Endpoint
class ProductEndpoint {
    val products = mutableListOf<Product>(
        Product().apply {
            id = 1
            name = "Первый"
            description = "Первый продукт"
            price = 1000.00
        },
        Product().apply {
            id = 2
            name = "Второй"
            description = "Второй продукт"
            price = 1000.00
        }
    )

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetProductRequest")
    @ResponsePayload
    fun getProduct(@RequestPayload request: GetProductRequest): GetProductResponse {
        val response = GetProductResponse()
        response.product.addAll(products)
        return response
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreateProductRequest")
    @ResponsePayload
    fun createProduct(@RequestPayload request: CreateProductRequest) {
        this.products.add( Product().apply {
            id = products.size.toLong() + 1
            name = request.name
            description = request.description
            price = request.price
        })
    }
}