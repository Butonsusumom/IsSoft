package com.tsybulka;

import com.tsybulka.entity.Order;
import com.tsybulka.entity.Product;
import com.tsybulka.entity.TotalPrice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        HashMap<String, LocalDateTime> orders = new HashMap<>();
        HashMap<String, Product> products = new HashMap<>();
         ArrayList<Order> orderProducts = new ArrayList<Order>();
        HashMap<String, TotalPrice> productValue = new HashMap<>();

        String line = "";
        String splitBy = ",";

        String productFile = "resource/products.csv";
        String orderFile = "resource/orders.csv";
        String orderItemFile = "resource/order_items.csv";

        //Read orderList
        try {
            BufferedReader br = new BufferedReader(new FileReader(orderFile));
            line = br.readLine();
            while ((line = br.readLine()) != null)
            {
                String[] order = line.split(splitBy);
                orders.put(order[0], LocalDateTime.parse(order[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Read productsList
        try {
            BufferedReader br = new BufferedReader(new FileReader(productFile));
            line = br.readLine();
            while ((line = br.readLine()) != null)
            {
                String[] product = line.split(splitBy);
                products.put(product[0], new Product(product[1], Integer.parseInt(product[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Read orderItemsList
        try {
            BufferedReader br = new BufferedReader(new FileReader(orderItemFile));
            line = br.readLine();
            while ((line = br.readLine()) != null)
            {
                String[] orderProd = line.split(splitBy);
                orderProducts.add(new Order(orderProd[0],orderProd[1], Integer.parseInt(orderProd[2])));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int neededDay = 21;
        Month neededMonth = Month.JANUARY;
        List<String> ordersList = orders.entrySet().stream().filter(ord -> ((ord.getValue().getDayOfMonth() == neededDay)&&(ord.getValue().getMonth() == neededMonth)))
                .map(Map.Entry::getKey).collect(Collectors.toList());

        for(int i = 0; i < ordersList.size(); i++){
            for (int j = 0; j< orderProducts.size();j++) {
                if (orderProducts.get(j).getOrderId().equals(ordersList.get(i))){
                    Order orderProduct = orderProducts.get(j);
                    Product product = products.get(orderProduct.getProductId());

                    if(!productValue.containsKey(orderProduct.getProductId())){
                        productValue.put(orderProduct.getProductId(),
                                new TotalPrice(orderProduct.getQuantity() * product.getPricePerUnit()));
                    } else {
                        productValue.replace(orderProduct.getProductId(), productValue.get(orderProduct.getProductId()).addTotalPrice(orderProduct.getQuantity() * product.getPricePerUnit()));
                    }
                }
            }

        }

        productValue.entrySet().forEach(item -> System.out.println(item.getKey() + " " + item.getValue().getTotalPrice()));

        String maxKey = productValue.entrySet().stream().findFirst().get().getKey();
        for(var entry : productValue.entrySet()){
            if(entry.getValue().getTotalPrice() > productValue.get(maxKey).getTotalPrice()){
                maxKey = entry.getKey();
            }
        }

        System.out.println(productValue.get(maxKey).getTotalPrice());
        System.out.println(products.get(maxKey).getName());
    }
}