package com.tsybulka.entity;

public class Product{
    private String name;
    private int pricePerUnit;

    public Product(String name, int pricePerUnit) {
        this.name = name;
        this.pricePerUnit = pricePerUnit;
    }

    public int getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(int pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
