package services.client.controller

import services.client.ProductClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import services.client.dto.GetOrderDTO
import services.client.dto.OrderDTO
import soap.server.Product

@Controller()
class OrderController {
    @Autowired
    lateinit var productClient: ProductClient

    @RequestMapping("/createOrder")
    fun createOrder(model: Model): String {
        productClient.createOrder()
        return "redirect:/orders"
    }

    @RequestMapping("/orders")
    fun getOrders(model: Model): String {
        val response = productClient.getOrders()

        model.addAttribute("orders", response)
        return "orders";
    }

    @PostMapping("/order")
    fun getOrder(@ModelAttribute body: GetOrderDTO, model: Model): String {
        val order = productClient.getOrder(body)

        val listType = object : TypeToken<List<Product>>() {}.type

        val gson = Gson()
        val products = gson.fromJson<List<Product>>(order?.products, listType)

        val resModel = OrderDTO(
            id = order?.id,
            number = order!!.number,
            dateReceive = order.dateReceive,
            products = products,
        )

        model.addAttribute("order", resModel)
        return "order"
    }
}
