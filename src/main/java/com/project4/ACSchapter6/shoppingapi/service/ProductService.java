package com.project4.ACSchapter6.shoppingapi.service;

import	org.springframework.beans.factory.annotation.Value;
import	org.springframework.stereotype.Service;

import com.project5.ACSchapter8.shoppingclient.dto.ProductDTO;
import	org.springframework.web.reactive.function.client.WebClient;
import	reactor.core.publisher.Mono;

@Service
public class ProductService {
    private String productApiURL = "http://localhost:8081";

    public ProductDTO getProductIdentifier(String productIdentifier){
        try{
            WebClient webClient =  WebClient.builder().baseUrl(productApiURL).build();

            Mono<ProductDTO> product = webClient.get().uri("/product/" + productIdentifier).retrieve().bodyToMono(ProductDTO.class);

            return product.block();
        } catch(Exception e){
            throw new RuntimeException("Product not found");
        }
    }
}
