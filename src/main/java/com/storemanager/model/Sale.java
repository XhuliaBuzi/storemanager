package com.storemanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @JoinTable(
            name = "sale",
            joinColumns = {@JoinColumn(name = "id_sale")},
            inverseJoinColumns = {@JoinColumn(name = "id_inventory")}

    )
    private Inventory inventory;
}
