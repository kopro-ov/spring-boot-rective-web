package com.greglturnquist.hackingspringboot.reactive.domain;

import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Objects;

public class Cart {

    private @Id String id;
    private List<CartItem> cartItemList;

    private Cart() { }

    public Cart(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public Cart(String id, List<CartItem> cartItemList) {
        this.id = id;
        this.cartItemList = cartItemList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id) && Objects.equals(cartItemList, cart.cartItemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cartItemList);
    }

}
