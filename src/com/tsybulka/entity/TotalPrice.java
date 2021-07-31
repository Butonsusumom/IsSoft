package com.tsybulka.entity;

public class TotalPrice{
    private int totalPrice;

    public TotalPrice(int startPrice){
        totalPrice = startPrice;
    }

    public int getTotalPrice(){
        return totalPrice;
    }

    public TotalPrice addTotalPrice(int value){
        totalPrice += value;
        return new TotalPrice(totalPrice);
    }
}
