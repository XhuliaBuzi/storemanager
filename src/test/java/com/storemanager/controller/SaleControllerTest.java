package com.storemanager.controller;

import com.storemanager.model.Sale;
import com.storemanager.service.SaleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class SaleControllerTest {
    private SaleController saleController;
    private UUID uuid;
    private Sale sale;

    @Mock
    SaleService saleService;

    @BeforeEach
    void setUp() {
        saleController = new SaleController(saleService);
        uuid = UUID.randomUUID();
        sale = new Sale(UUID.randomUUID(), 0, 0f, LocalDate.now(), null);
    }

    @Test
    void getSaleTest() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<Sale> page = new PageImpl<>(Collections.singletonList(sale), pageable, 1);
        Mockito.when(saleService.getSale()).thenReturn(page);

        assertNotNull(saleController.getSale());
    }

    @Test
    void getOneSaleTest() {
        Mockito.when(saleService.getOneSale(uuid)).thenReturn(Optional.of(sale));

        assertNotNull(saleController.getOneSale(uuid));
    }

    @Test
    void addSaleTest() {
        Mockito.when(saleService.addSale(sale)).thenReturn(sale);

        assertNotNull(saleController.addSale(sale));
    }

    @Test
    void deleteSaleTest() {
        saleController.deleteSale(uuid);
        Mockito.verify(saleService, Mockito.atLeastOnce()).deleteSale(uuid);
    }
}