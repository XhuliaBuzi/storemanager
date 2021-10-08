package com.storemanager.model;

public class Inventory {
    private long inInventory;
    private int productQuantity;
    private float productPrice;

    public Inventory(long inInventory, int productQuantity, float productPrice) {
        this.inInventory = inInventory;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
    }

    public long getInInventory() {
        return inInventory;
    }

    public void setInInventory(long inInventory) {
        this.inInventory = inInventory;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }
}
