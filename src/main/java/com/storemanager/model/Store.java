package com.storemanager.model;

public class Store {
    private long idStore;
    private String nameStore;
    private String addressStore;
    private String description;

    public Store(long id, String name, String address, String description) {
        this.idStore = id;
        this.nameStore = name;
        this.addressStore = address;
        this.description = description;
    }

    public long getId() {
        return idStore;
    }

    public void setId(long id) {
        this.idStore = id;
    }

    public String getName() {
        return nameStore;
    }

    public void setName(String name) {
        this.nameStore = name;
    }

    public String getAddress() {
        return addressStore;
    }

    public void setAddress(String address) {
        this.addressStore = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

