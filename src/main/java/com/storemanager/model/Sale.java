package com.storemanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {

    @Id
    private Long id;
    private Integer quantity;
    private Float total;
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "id_inventory")
    private Inventory inventory;
}
