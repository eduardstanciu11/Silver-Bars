/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.factory;

import java.math.BigDecimal;
import model.OrderModel;
import model.OrderModel.Type;

/**
 *
 * @author EduardS
 */
public class OrderFactory {

    public static OrderModel createOrderModel(int id, int userId, BigDecimal orderQuantity, BigDecimal pricePerKg, Type orderType, boolean registered) {
        OrderModel order = new OrderModel();
        order.setId(id);
        order.setUserId(userId);
        order.setOrderQunatity(orderQuantity);
        order.setPricePerKg(pricePerKg);
        order.setType(orderType);

        return order;
    }
}
