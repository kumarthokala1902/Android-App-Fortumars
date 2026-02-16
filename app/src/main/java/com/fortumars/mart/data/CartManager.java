package com.fortumars.mart.data;

import com.fortumars.mart.model.Product;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<Product> cartItems;

    private CartManager() {
        cartItems = new ArrayList<>();
    }

    public static synchronized CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addToCart(Product product) {
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        if (product != null) {
            cartItems.add(product);
        }
    }

    public void removeFromCart(Product product) {
        if (cartItems != null && product != null) {
            cartItems.remove(product);
        }
    }

    public List<Product> getCartItems() {
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        return cartItems;
    }

    public double getTotalPrice() {
        double total = 0;
        if (cartItems != null) {
            for (Product item : cartItems) {
                total += item.getPrice();
            }
        }
        return total;
    }
}
