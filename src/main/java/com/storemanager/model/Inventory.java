package com.storemanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    @OneToMany(mappedBy = "inventory")
    public List<Sale> sales;
    @Id
    private Long id;
    private Integer quantity;
    private Float price;
    @ManyToOne
    @JoinTable(
            name = "inventory",
            joinColumns = {@JoinColumn(name = "id_inventory")},
            inverseJoinColumns = {@JoinColumn(name = "id_products")}
    )
    private Products products;
    @ManyToOne
    @JoinTable(
            name = "inventory",
            joinColumns = {@JoinColumn(name = "id_inventory")},
            inverseJoinColumns = {@JoinColumn(name = "id_store")}

    )
    private Store store;
}
