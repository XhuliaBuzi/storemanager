package com.storemanager.service;

import com.storemanager.model.Products;
import com.storemanager.repository.ProductsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ProductsServiceTest {
    private Products products;
    private ProductsService productsService;
    @Mock
    ProductsRepository productsRepository;

    @BeforeEach
    public void inti() {
        productsService = new ProductsService(productsRepository);
        products = new Products(null, UUID.randomUUID(), "", "");
    }
    @Test
    void shouldGetProductsTest() {
        Pageable pageable = PageRequest.of(0, 2, Sort.by("name"));
        Page<Products> page = new PageImpl<>(Collections.singletonList(products));
        Mockito.when(productsRepository.findAll(pageable)).thenReturn(page);

        assertNotNull(productsService.getProducts("name"));
    }

    @Test
    void shouldGetOneProductTest() {
        UUID uuid = UUID.randomUUID();
        Mockito.when(productsRepository.existsById(uuid)).thenReturn(true);
        Mockito.when(productsRepository.findById(uuid)).thenReturn(Optional.of(products));

        assertNotNull(productsService.getOneProduct(uuid));
    }

    @Test
    void shoulAdddProductFindTest() {
    }

    @Test
    void shoulAdddProductTest() {
        Mockito.when(productsRepository.save(products)).thenReturn(products);

        assertNotNull(productsService.addProduct(products));
    }


    @Test
    void deleteProducts() {
    }
}