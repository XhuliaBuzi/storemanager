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
import java.time.LocalTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class SaleServiceTest {
    private SaleService saleService;
    private Sale sale;
    private UUID uuid;
    @Mock
    private SaleRepository saleRepository;

    @BeforeEach
    public void inti() {
        saleService = new SaleService(saleRepository);
        uuid = UUID.randomUUID();
        sale = new Sale(UUID.randomUUID(), 0, 0f, LocalDate.now(), LocalTime.now(), null);
    }

    @Test
    void shouldGetSaleTest() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<Sale> page = new PageImpl<>(Collections.singletonList(sale), pageable, 1);
        Mockito.when(saleRepository.findAll(pageable)).thenReturn(page);

        assertNotNull(saleService.getSale());
    }

    @Test
    void shouldGetOneSaleTest() {
        Mockito.when(saleRepository.findById(uuid)).thenReturn(java.util.Optional.of(sale));

        assertNotNull(saleService.getOneSale(uuid));
    }

    @Test
    void shouldGetOneSaleExceptionTest() {
        assertThrows(IllegalStateException.class, () -> saleService.getOneSale(uuid));
    }

    @Test
    void shouldAddSaleTest() {
        Mockito.when(saleRepository.save(sale)).thenReturn(sale);

        assertNotNull(saleService.addSale(sale));
    }

    @Test
    void deleteSale() {
        Mockito.when(saleRepository.findById(uuid)).thenReturn(Optional.of(sale));

        saleService.deleteSale(uuid);
        Mockito.verify(saleRepository, Mockito.atLeastOnce()).deleteById(uuid);
    }

    @Test
    void shouldUpdateSaleTest() {
        Mockito.when(saleRepository.findById(uuid)).thenReturn(Optional.of(sale));
        Mockito.when(saleRepository.getById(uuid)).thenReturn(sale);
        sale.setQuantity(3);
        sale.setTotal(3.34f);
        Mockito.when(saleRepository.save(sale)).thenReturn(sale);

        assertNotNull(saleService.updateSale(uuid, sale));
    }
}