package com.storemanager.model;

import java.sql.Time;
import java.time.LocalDate;

public class Sale {
    private long idSale;
    private int quantity;
    private float total;
    private LocalDate date;

    public Sale(long idSale, int quantity, float total, LocalDate date) {
        this.idSale = idSale;
        this.quantity = quantity;
        this.total = total;
        this.date = date;
    }

    public long getIdSale() {
        return idSale;
    }

    public void setIdSale(long idSale) {
        this.idSale = idSale;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
