package com.storemanager.service;

import com.storemanager.model.Sale;
import com.storemanager.repository.SaleRepository;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class SaleServiceTest {
    private SaleService saleService;
    private Sale sale;
    @Mock
    private SaleRepository saleRepository;

    @BeforeEach
    public void inti() {
        saleService = new SaleService(saleRepository);
        sale=new Sale(UUID.randomUUID(), 0, 0f, LocalDate.now(), null);
    }

    @Test
    void shouldGetSaleTest() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<Sale> page = new PageImpl<>(Collections.singletonList(sale), pageable, 1);
        Mockito.when(saleRepository.findAll(pageable)).thenReturn(page);

        assertNotNull(saleService.getSale());
    }

    @Test
    void shouldGetOneUserTest() {
        UUID uuid = UUID.randomUUID();
        Mockito.when(saleRepository.existsById(uuid)).thenReturn(true);
        Mockito.when(saleRepository.findById(uuid)).thenReturn(java.util.Optional.of(sale));

        assertNotNull(saleService.getOneUser(uuid));
    }

    @Test
    void shouldAddSaleFindTest() {
    }

    @Test
    void shouldAddSaleTest() {
        Mockito.when(saleRepository.save(sale)).thenReturn(sale);

        assertNotNull(saleService.addSale(sale));
    }

    @Test
    void deleteSale() {
    }
}