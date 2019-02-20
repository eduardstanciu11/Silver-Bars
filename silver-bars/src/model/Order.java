/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;

/**
 *
 * @author EduardS
 */
public class Order {

    private int id;
    private int userId;
    private BigDecimal orderQunatity;
    private BigDecimal pricePerKg;
    private final Type orderType;
    private boolean registered;

    public enum Type {
        SELL,
        BUY;
    }

    public Order(int id, int userId, BigDecimal orderQunatity, BigDecimal pricePerKg, Type orderType) {
        this.id = id;
        this.userId = userId;
        this.orderQunatity = orderQunatity;
        this.pricePerKg = pricePerKg;
        this.orderType = orderType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getOrderQunatity() {
        return orderQunatity;
    }

    public void setOrderQunatity(BigDecimal orderQunatity) {
        this.orderQunatity = orderQunatity;
    }

    public BigDecimal getPricePerKg() {
        return pricePerKg;
    }

    public void setPricePerKg(BigDecimal pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    public Type getType() {
        return orderType;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

}
