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
public class OrderModel {

    private int id;
    private int userId;
    private BigDecimal orderQunatity;
    private BigDecimal pricePerKg;
    private Type orderType;
    private boolean registered;

    public enum Type {
        SELL,
        BUY;
    }

    public OrderModel(int id, int userId, BigDecimal orderQunatity, BigDecimal pricePerKg, Type orderType) {
        this.id = id;
        this.userId = userId;
        this.orderQunatity = orderQunatity;
        this.pricePerKg = pricePerKg;
        this.orderType = orderType;
    }

    public OrderModel() {

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
    
    public void setType(Type orderType) {
        this.orderType = orderType;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.id;
        hash = 41 * hash + this.userId;
        hash = 41 * hash + (this.orderQunatity != null ? this.orderQunatity.hashCode() : 0);
        hash = 41 * hash + (this.pricePerKg != null ? this.pricePerKg.hashCode() : 0);
        hash = 41 * hash + (this.orderType != null ? this.orderType.hashCode() : 0);
        hash = 41 * hash + (this.registered ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return this.hashCode() == obj.hashCode();
    }

    
}
