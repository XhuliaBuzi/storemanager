package com.storemanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;
    private Integer quantity;
    private Float total;
    //Another home work that you left me it was to create auto data/time here you have it. Also i have done changes in the database plase update that.
    private LocalDate date=LocalDate.now();
    private LocalTime time=LocalTime.now();
    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;
}
