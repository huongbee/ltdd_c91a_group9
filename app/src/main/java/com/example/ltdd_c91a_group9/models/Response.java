package com.example.ltdd_c91a_group9.models;

import java.util.List;

public class Response {
    private User user;
    private List<Product> products;

    public Response(User user, List<Product> products) {
        this.user = user;
        this.products = products;
    }

    public Response() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
