/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import model.OrderBoardModel;
import model.OrderModel;
import model.OrderModel.Type;

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
    private final OrderBoardModel orderBoard = new OrderBoardModel();

    public OrderBoardModel getLocalOrderBoard() {
        return orderBoard;
    }

    public void registerOrder(OrderModel order) {
        // Should be replaced with a DAO call to the database in a real life scenario
        orderBoard.getOrders().add(order);
    }

    public void cancelOrder(OrderModel order) {
        // Should be replaced with a DAO call to the database in a real life scenario
        for (OrderModel orderModel : orderBoard.getOrders()) {
            if (orderModel.equals(order)) {
                // We do this instead of just removing it so we can have a full
                // order history
                orderModel.setRegistered(false);
            }
        }
    }

    public ArrayList<OrderModel> getAllOrders() {
        return (ArrayList) orderBoard.getOrders();
    }

    public ArrayList<OrderModel> getLiveOrders() {
        ArrayList<OrderModel> listOfLiveOrders = new ArrayList<OrderModel>();
        ArrayList<OrderModel> listOfAllOrders = new ArrayList<OrderModel>(orderBoard.getOrders());

        for (OrderModel orderModel : listOfAllOrders) {
            if (orderModel.isRegistered()) {
                listOfLiveOrders.add(orderModel);
            }
        }

        return listOfLiveOrders;
    }

    public ArrayList<OrderModel> getLiveOrdersByType(OrderModel.Type type) {
        ArrayList<OrderModel> listOfRequestedLiveOrders = new ArrayList<OrderModel>();
        ArrayList<OrderModel> listOfAllOrders = new ArrayList<OrderModel>(orderBoard.getOrders());

        for (OrderModel orderModel : listOfAllOrders) {
            if (orderModel.isRegistered() && orderModel.getType().equals(type)) {
                listOfRequestedLiveOrders.add(orderModel);
            }
        }

        return listOfRequestedLiveOrders;
    }

    public ArrayList<OrderModel> getLiveSellOrders() {
        return getLiveOrdersByType(OrderModel.Type.SELL);
    }

    public ArrayList<OrderModel> getLiveBuyOrders() {
        return getLiveOrdersByType(OrderModel.Type.BUY);
    }

    public String getSummaryInformationOfLiveOrders() {
        return getSummaryInformationOfLiveSellOrders() + getSummaryInformationOfLiveBuyOrders();
    }

    public String getSummaryInformationOfLiveSellOrders() {
        return getSummaryInformationOfLiveOrdersByType(Type.SELL);
    }

    public String getSummaryInformationOfLiveBuyOrders() {
        return getSummaryInformationOfLiveOrdersByType(Type.BUY);
    }

    public String getSummaryInformationOfLiveOrdersByType(OrderModel.Type type) {
        String result = new String();
        ArrayList<OrderModel> listOfRequestedLiveOrders = getLiveBuyOrders();

        TreeMap<BigDecimal, BigDecimal> mapOfCollapsedLiveRequestedOrders;
        if (type.equals(Type.SELL)) {
            mapOfCollapsedLiveRequestedOrders = new TreeMap<BigDecimal, BigDecimal>();
        } else {
            mapOfCollapsedLiveRequestedOrders = new TreeMap<BigDecimal, BigDecimal>(Collections.reverseOrder());
        }
        // Price -> Number of Kilograms

        for (OrderModel orderModel : listOfRequestedLiveOrders) {
            BigDecimal numberOfKgs = orderModel.getOrderQunatity().setScale(1, RoundingMode.HALF_UP);
            BigDecimal price = orderModel.getPricePerKg();

            if (mapOfCollapsedLiveRequestedOrders.containsKey(price)) {
                // Get the current number of kilograms at that price
                BigDecimal numberOfKgsAtThatPrice = mapOfCollapsedLiveRequestedOrders.get(price);
                // Adding the extra number of kilograms from our iterated order model
                BigDecimal newNumberOfKgsAtThatPrice = numberOfKgsAtThatPrice.add(numberOfKgs).setScale(1, RoundingMode.HALF_UP);
                // Update the map with the new number of kilograms at that price
                mapOfCollapsedLiveRequestedOrders.put(price, newNumberOfKgsAtThatPrice);
            } else {
                mapOfCollapsedLiveRequestedOrders.put(price, numberOfKgs);
            }
        }

        Set set = mapOfCollapsedLiveRequestedOrders.entrySet();
        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            Map.Entry mapEntry = (Map.Entry) iterator.next();
            result = result + mapEntry.getValue().toString() + " kg for £" + mapEntry.getKey().toString() + "\n";
        }

        return result;
    }
}
