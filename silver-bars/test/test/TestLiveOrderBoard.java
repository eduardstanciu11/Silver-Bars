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

        Assert.assertEquals(1, orderService.getAllOrders().size());
        Assert.assertEquals(0, orderService.getAllOrders().get(0).getId());
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

        Assert.assertEquals(1, orderService.getAllOrders().size());
        Assert.assertEquals(false, orderService.getAllOrders().get(0).isRegistered());
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

        Assert.assertEquals(2, orderService.getAllOrders().size());
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
        orderModel2.setRegistered(true);
        orderModel2.setType(OrderModel.Type.SELL);
        orderModel2.setUserId(2);

        orderService.registerOrder(orderModel2);
        orderService.cancelOrder(orderModel2);

        Assert.assertEquals(2, orderService.getAllOrders().size());
        Assert.assertEquals(true, orderService.getAllOrders().get(0).isRegistered());
        Assert.assertEquals(false, orderService.getAllOrders().get(1).isRegistered());
        Assert.assertEquals(1, orderService.getLiveOrders().size());

    }

    @Test
    public void testGetSummaryInformationOfLiveSellOrders() {
        OrderModel orderModel = TestObjectFactory.createOrderModel();
        orderModel.setId(0);
        orderModel.setOrderQunatity(new BigDecimal(3.5));
        orderModel.setPricePerKg(new BigDecimal(306));
        orderModel.setRegistered(true);
        orderModel.setType(OrderModel.Type.SELL);
        orderModel.setUserId(1);

        orderService.registerOrder(orderModel);

        OrderModel orderModel2 = TestObjectFactory.createOrderModel();
        orderModel2.setId(1);
        orderModel2.setOrderQunatity(new BigDecimal(1.2));
        orderModel2.setPricePerKg(new BigDecimal(310));
        orderModel2.setRegistered(true);
        orderModel2.setType(OrderModel.Type.SELL);
        orderModel2.setUserId(2);

        orderService.registerOrder(orderModel2);

        OrderModel orderModel3 = TestObjectFactory.createOrderModel();
        orderModel3.setId(2);
        orderModel3.setOrderQunatity(new BigDecimal(1.5));
        orderModel3.setPricePerKg(new BigDecimal(307));
        orderModel3.setRegistered(true);
        orderModel3.setType(OrderModel.Type.SELL);
        orderModel3.setUserId(3);

        orderService.registerOrder(orderModel3);

        OrderModel orderModel4 = TestObjectFactory.createOrderModel();
        orderModel4.setId(3);
        orderModel4.setOrderQunatity(new BigDecimal(2.0));
        orderModel4.setPricePerKg(new BigDecimal(306));
        orderModel4.setRegistered(true);
        orderModel4.setType(OrderModel.Type.SELL);
        orderModel4.setUserId(4);

        orderService.registerOrder(orderModel4);

        String result = orderService.getSummaryInformationOfLiveSellOrders();
        String expectedResult = "5.5 kg for £306\n"
                + "1.5 kg for £307\n"
                + "1.2 kg for £310\n";

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void testGetSummaryInformationOfLiveBuyOrders() {
        OrderModel orderModel = TestObjectFactory.createOrderModel();
        orderModel.setId(0);
        orderModel.setOrderQunatity(new BigDecimal(3.5));
        orderModel.setPricePerKg(new BigDecimal(306));
        orderModel.setRegistered(true);
        orderModel.setType(OrderModel.Type.BUY);
        orderModel.setUserId(1);

        orderService.registerOrder(orderModel);

        OrderModel orderModel2 = TestObjectFactory.createOrderModel();
        orderModel2.setId(1);
        orderModel2.setOrderQunatity(new BigDecimal(1.2));
        orderModel2.setPricePerKg(new BigDecimal(310));
        orderModel2.setRegistered(true);
        orderModel2.setType(OrderModel.Type.BUY);
        orderModel2.setUserId(2);

        orderService.registerOrder(orderModel2);

        OrderModel orderModel3 = TestObjectFactory.createOrderModel();
        orderModel3.setId(2);
        orderModel3.setOrderQunatity(new BigDecimal(1.5));
        orderModel3.setPricePerKg(new BigDecimal(307));
        orderModel3.setRegistered(true);
        orderModel3.setType(OrderModel.Type.BUY);
        orderModel3.setUserId(3);

        orderService.registerOrder(orderModel3);

        OrderModel orderModel4 = TestObjectFactory.createOrderModel();
        orderModel4.setId(3);
        orderModel4.setOrderQunatity(new BigDecimal(2.0));
        orderModel4.setPricePerKg(new BigDecimal(306));
        orderModel4.setRegistered(true);
        orderModel4.setType(OrderModel.Type.BUY);
        orderModel4.setUserId(4);

        orderService.registerOrder(orderModel4);

        String result = orderService.getSummaryInformationOfLiveBuyOrders();
        String expectedResult = "1.2 kg for £310\n"
                + "1.5 kg for £307\n"
                + "5.5 kg for £306\n";

        Assert.assertEquals(expectedResult, result);
    }
}
