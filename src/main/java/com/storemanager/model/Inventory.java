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
    public List<Sale> sale;
    @Id
    private Long id;
    private Integer quantity;
    private Float price;
    @ManyToOne
    @JoinColumn(name = "id_products")
    private Products products;
    @ManyToOne
    @JoinColumn(name = "id_store")
    private Store store;
}
