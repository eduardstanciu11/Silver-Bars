package test;

import java.math.BigDecimal;
import model.OrderModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import service.OrderService;
import test.utils.TestObjectFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author EduardS
 */
public class TestLiveOrderBoard {

    OrderService orderService = Mockito.spy(new OrderService());

    @Before
    public void cleanOrders() {
        orderService.getLocalOrderBoard().getOrders().clear();
    }

    @Test
    public void TestAddOrder() {
        OrderModel orderModel = TestObjectFactory.createOrderModel();
        orderModel.setId(0);
        orderModel.setOrderQunatity(BigDecimal.ONE);
        orderModel.setPricePerKg(BigDecimal.ONE);
        orderModel.setRegistered(true);
        orderModel.setType(OrderModel.Type.SELL);
        orderModel.setUserId(1);

        orderService.registerOrder(orderModel);

        Assert.assertEquals(1, orderService.getLocalOrderBoard().getOrders().size());
        Assert.assertEquals(0, orderService.getLocalOrderBoard().getOrders().get(0).getId());
    }

    @Test
    public void TestCancelOrder() {
        OrderModel orderModel = TestObjectFactory.createOrderModel();
        orderModel.setId(0);
        orderModel.setOrderQunatity(BigDecimal.ONE);
        orderModel.setPricePerKg(BigDecimal.ONE);
        orderModel.setRegistered(true);
        orderModel.setType(OrderModel.Type.SELL);
        orderModel.setUserId(1);

        orderService.registerOrder(orderModel);

        Assert.assertEquals(1, orderService.getLocalOrderBoard().getOrders().size());
        
        orderService.cancelOrder(orderModel);
        
        Assert.assertEquals(0, orderService.getLocalOrderBoard().getOrders().size());
    }

    @Test
    public void TestGetAllOrders() {
        OrderModel orderModel = TestObjectFactory.createOrderModel();
        orderModel.setId(0);
        orderModel.setOrderQunatity(BigDecimal.ONE);
        orderModel.setPricePerKg(BigDecimal.ONE);
        orderModel.setRegistered(true);
        orderModel.setType(OrderModel.Type.SELL);
        orderModel.setUserId(1);
        
        orderService.registerOrder(orderModel);
        
        OrderModel orderModel2 = TestObjectFactory.createOrderModel();
        orderModel2.setId(1);
        orderModel2.setOrderQunatity(BigDecimal.ONE);
        orderModel2.setPricePerKg(BigDecimal.ONE);
        orderModel2.setRegistered(false);
        orderModel2.setType(OrderModel.Type.SELL);
        orderModel2.setUserId(2);

        orderService.registerOrder(orderModel2);
        
        Assert.assertEquals(2, orderService.getLocalOrderBoard().getOrders().size());
    }

    @Test
    public void TestGetAllActiveOrders() {
        OrderModel orderModel = TestObjectFactory.createOrderModel();
        orderModel.setId(0);
        orderModel.setOrderQunatity(BigDecimal.ONE);
        orderModel.setPricePerKg(BigDecimal.ONE);
        orderModel.setRegistered(true);
        orderModel.setType(OrderModel.Type.SELL);
        orderModel.setUserId(1);
        
        orderService.registerOrder(orderModel);
        
        OrderModel orderModel2 = TestObjectFactory.createOrderModel();
        orderModel2.setId(1);
        orderModel2.setOrderQunatity(BigDecimal.ONE);
        orderModel2.setPricePerKg(BigDecimal.ONE);
        orderModel2.setRegistered(false);
        orderModel2.setType(OrderModel.Type.SELL);
        orderModel2.setUserId(2);
        
        orderService.registerOrder(orderModel2);
        
        Assert.assertEquals(1, orderService.getLocalOrderBoard().getOrders().size());
    }
}
