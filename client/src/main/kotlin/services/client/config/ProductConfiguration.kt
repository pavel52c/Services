package services.client.config

import services.client.ProductClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.oxm.jaxb.Jaxb2Marshaller

@Configuration
open class ProductConfiguration {
    @Bean
    open fun marshaller(): Jaxb2Marshaller {
        val marshaller = Jaxb2Marshaller()
        marshaller.setContextPaths("soap.server")
        return marshaller
    }

    @Bean
    open fun productClient(marshaller: Jaxb2Marshaller): ProductClient {
        val client = ProductClient()
        client.defaultUri = "http://localhost:3040/ws"
        client.marshaller = marshaller
        client.unmarshaller = marshaller
        return client
    }
}