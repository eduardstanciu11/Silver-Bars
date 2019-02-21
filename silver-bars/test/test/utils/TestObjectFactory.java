/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.utils;

import model.OrderBoardModel;
import model.OrderModel;

/**
 *
 * @author EduardS
 */
public class TestObjectFactory {

    public static OrderBoardModel createOrderBoardModel() {
        return new OrderBoardModel();
    }

    public static OrderModel createOrderModel() {
        return new OrderModel();
    }
}
