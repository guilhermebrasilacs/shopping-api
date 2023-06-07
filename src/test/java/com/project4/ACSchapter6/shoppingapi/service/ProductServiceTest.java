package com.project4.ACSchapter6.shoppingapi.service;

import	com.fasterxml.jackson.databind.ObjectMapper;
import	com.project5.ACSchapter8.shoppingclient.dto.ProductDTO;
import	okhttp3.mockwebserver.MockResponse;
import	okhttp3.mockwebserver.MockWebServer;
import	org.junit.jupiter.api.*;
import	org.junit.jupiter.api.extension.ExtendWith;
import	org.mockito.InjectMocks;
import	org.mockito.junit.jupiter.MockitoExtension;
import	org.springframework.test.util.ReflectionTestUtils;
import java.io.IOException;

@ExtendWith((MockitoExtension.class))
public class ProductServiceTest {
    public static MockWebServer mockBackEnd;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() throws IOException{
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();

        String baseUrl = String.format("http://localhost:%s", mockBackEnd.getPort());
        ReflectionTestUtils.setField(productService, "productApiURL", baseUrl);
    }
    @AfterEach
    void tearDown() throws IOException{
        mockBackEnd.shutdown();
    }

    @Test
    void test_getProductByIdentifier() throws Exception{
        ProductDTO productDTO = new ProductDTO();
        productDTO.setPreco(100F);
        productDTO.setProductIdentifier("prod-identifier");

        ObjectMapper objectMapper = new ObjectMapper();

        mockBackEnd.enqueue((new MockResponse()
                .setBody(objectMapper.writeValueAsString(productDTO))
                .addHeader("Content-type", "apllicationn/json")));
        productDTO = productService.getProductIdentifier("product-identifier");
        Assertions.assertEquals(1000F,	productDTO.getPreco());
        Assertions.assertEquals("prod-identifier",	productDTO.getProductIdentifier());
    }
}
