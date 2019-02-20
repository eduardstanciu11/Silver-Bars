/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import model.OrderBoardModel;
import model.OrderModel;

/**
 *
 * @author EduardS
 */
public class OrderService {
    
    // Because we don't use any sort of storage mechanism like a DB
    // we are using this variable as a way to store the Order Board Model
    // which contains all the orders. This variable is essentially a database
    // replacement which is kept alive as long as this class instance is used.
    // This variable and anything related to it would NOT get shipped to live
    private OrderBoardModel orderBoard = new OrderBoardModel();
    
    public OrderBoardModel getLocalOrderBoard() {
        return this.orderBoard;
    }
    
    public void registerOrder(OrderModel order) {
        
    }

    public void cancelOrder(OrderModel order) {

    }

    public ArrayList<OrderModel> getAllOrders() {
        return new ArrayList<OrderModel>();
    }

    public ArrayList<OrderModel> getLiveOrders() {
        return new ArrayList<OrderModel>();
    }
}
