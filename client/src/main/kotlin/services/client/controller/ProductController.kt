package services.client.controller

import services.client.ProductClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import services.client.dto.CreateProductDTO

@Controller()
class ProductController {
    @Autowired
    lateinit var product: ProductClient

    @RequestMapping("/getProduct")
    fun getProduct(model: Model): String {
        val resp = product.getProducts()
        model.addAttribute("products", resp.product)
        return "product"
    }

    @RequestMapping("/createProductForm")
    fun getProductForm(model: Model): String {
        return "createProduct"
    }

    @PostMapping("/createProduct")
    fun createProduct(@ModelAttribute body: CreateProductDTO, model: Model): String {
        product.createProduct(body)
        return "redirect:/getProduct"
    }

    @RequestMapping("/sendProducts")
    fun getProducts(model: Model): String {
        val resp = product.getProducts()

        product.setNodeProducts(resp.product)
        val nodeProducts = product.getNodeProducts();

        model.addAttribute("products", nodeProducts)
        return "productsNode"
    }
}
