package com.ocado.basket;

import java.util.List;

public class DeliveryOption implements Comparable<DeliveryOption> {
    private String name;
    private int numberOfDeliverableProducts;
    private boolean hasForcingProduct;

    private List<String> products;

    public DeliveryOption(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfDeliverableProducts() {
        return numberOfDeliverableProducts;
    }

    public boolean HasForcingProduct() {
        return hasForcingProduct;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfDeliverableProducts(int numberOfDeliverableProducts) {
        this.numberOfDeliverableProducts = numberOfDeliverableProducts;
    }

    public void setHasForcingProduct(boolean hasForcingProduct) {
        this.hasForcingProduct = hasForcingProduct;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public String toString() {
        return name +
                ", " + numberOfDeliverableProducts +
                ", " + hasForcingProduct +
                ", " + products;
    }

    @Override
    public int compareTo(DeliveryOption secondOption) {
        if(this.HasForcingProduct() && !secondOption.HasForcingProduct()){
            return -1;
        } else if (!this.HasForcingProduct() && secondOption.HasForcingProduct()) {
            return 1;
        }
        else {
            if(this.getNumberOfDeliverableProducts() > secondOption.getNumberOfDeliverableProducts()){
                return -1;
            } else if (this.getNumberOfDeliverableProducts() < secondOption.getNumberOfDeliverableProducts()) {
                return 1;
            }
            else return 0;
        }
    }
}
