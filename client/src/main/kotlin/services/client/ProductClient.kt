package services.client

import org.springframework.ws.client.core.support.WebServiceGatewaySupport
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

import services.client.dto.CreateProductDTO
import services.client.dto.GetOrderDTO
import services.client.dto.OrderDTO
import services.client.dto.OrderDTORS
import services.client.utils.Date
import soap.server.CreateProductRequest
import soap.server.GetProductResponse
import soap.server.GetProductRequest
import soap.server.Product
import kotlin.random.Random

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

    fun setNodeProducts(products: List<Product>) {
        val webClient = WebClient.create()
        webClient.post()
            .uri("http://localhost:5000/products/createList")
            .body(Mono.just(products), Product::class.java)
            .retrieve()
            .bodyToMono(String::class.java)
            .block();
    }

    fun getNodeProducts(): List<Product>? {
        val webClient = WebClient.create()
        val nodeResponse = webClient.get()
            .uri("http://localhost:5000/products")
            .retrieve()
            .bodyToFlux(Product::class.java)
            .collectList()
            .block();

        return nodeResponse;
    }

    fun createOrder(): OrderDTORS? {
        val products = getNodeProducts();
        val webClient = WebClient.create()

        val orderReq = OrderDTO(
            products = products,
            number = Random.nextInt(0, 100000000),
            dateReceive = Date().getTomorrowDate(),
            id = null,
        )

        val nodeResponse = webClient.post()
            .uri("http://localhost:5001/orders")
            .body(Mono.just(orderReq), OrderDTO::class.java)
            .retrieve()
            .bodyToMono(OrderDTORS::class.java)
            .block();

        return nodeResponse;
    }

    fun getOrders(): List<OrderDTORS>? {
        val webClient = WebClient.create()
        val orders = webClient.get()
            .uri("http://localhost:5001/orders")
            .retrieve()
            .bodyToFlux(OrderDTORS::class.java)
            .collectList()
            .block();

        return orders;
    }

    fun getOrder(body: GetOrderDTO): OrderDTORS? {
        val webClient = WebClient.create()
        val uri = "http://localhost:5001/orders/" + body.id;
        val order = webClient.get()
            .uri(uri)
            .retrieve()
            .bodyToMono(OrderDTORS::class.java)
            .block();

        return order;
    }
}