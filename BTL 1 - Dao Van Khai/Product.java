package com.example.BTL1;

public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;

    public Product(){

    }

    public Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = DataCleaner.formatName(name);
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Override equals
    // Hai san pham giong nhau khi
    // - chung co cung 1 ID san pham
    // - chung co ten giong nhau khong phan biet viet hoa hay thuong

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;

        if(this == null)
            return false;


        if(getClass() != obj.getClass())
            return false;

        Product other = (Product) obj;
        return this.id == other.id && this.name.equalsIgnoreCase(other.name);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID : ").append(id)
                .append(" | Name : ").append(name)
                .append(" | Price : ").append(price)
                .append(" | Quantity :").append(quantity);
        return sb.toString();
    }
}

