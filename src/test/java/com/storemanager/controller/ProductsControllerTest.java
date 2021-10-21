package com.storemanager.controller;

import com.storemanager.model.Products;
import com.storemanager.service.ProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ProductsControllerTest {
    private ProductsController productsController;
    private UUID uuid;
    private Products products;

    @Mock
    ProductsService productsService;

    @BeforeEach
    void setUp() {
        productsController = new ProductsController(productsService);
        uuid = UUID.randomUUID();
        products = new Products(UUID.randomUUID(), "", "", null);
    }

    @Test
    void getProductsTest() {
        Page<Products> page = new PageImpl<>(Collections.singletonList(products));

        Mockito.when(productsService.getProducts("")).thenReturn(page);

        assertNotNull(productsController.getProducts(""));
    }

    @Test
    void getOneProductTest() {
        Mockito.when(productsService.getOneProduct(uuid)).thenReturn(Optional.of(products));

        assertNotNull(productsController.getOneProduct(uuid));
    }

    @Test
    void addProductsTest() {
        Mockito.when(productsService.addProduct(products)).thenReturn(products);

        assertNotNull(productsController.addProducts(products));
    }

    @Test
    void deleteProductTest() {
        productsController.deleteProduct(uuid);
        Mockito.verify(productsService, Mockito.atLeastOnce()).deleteProducts(uuid);
    }
}