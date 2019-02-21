/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
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
    private final OrderBoardModel orderBoard = new OrderBoardModel();

    public OrderBoardModel getLocalOrderBoard() {
        return this.orderBoard;
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

    public ArrayList<OrderModel> getLiveSellOrders() {
        ArrayList<OrderModel> listOfLiveSellOrders = new ArrayList<OrderModel>();
        ArrayList<OrderModel> listOfAllOrders = new ArrayList<OrderModel>(orderBoard.getOrders());

        for (OrderModel orderModel : listOfAllOrders) {
            if (orderModel.isRegistered() && orderModel.getType().equals(OrderModel.Type.SELL)) {
                listOfLiveSellOrders.add(orderModel);
            }
        }

        return listOfLiveSellOrders;
    }

    public ArrayList<OrderModel> getLiveBuyOrders() {
        ArrayList<OrderModel> listOfLiveBuyOrders = new ArrayList<OrderModel>();
        ArrayList<OrderModel> listOfAllOrders = new ArrayList<OrderModel>(orderBoard.getOrders());

        for (OrderModel orderModel : listOfAllOrders) {
            if (orderModel.isRegistered() && orderModel.getType().equals(OrderModel.Type.BUY)) {
                listOfLiveBuyOrders.add(orderModel);
            }
        }

        return listOfLiveBuyOrders;
    }

    public String getSummaryInformationOfLiveOrders() {
        return getSummaryInformationOfLiveSellOrders() + getSummaryInformationOfLiveBuyOrders();
    }

    public String getSummaryInformationOfLiveSellOrders() {
        String result = new String();
        ArrayList<OrderModel> listOfLiveSaleOrders = getLiveSellOrders();

        // Price -> Number of Kilograms
        TreeMap<BigDecimal, BigDecimal> mapOfCollapsedLiveSaleOrders = new TreeMap<BigDecimal, BigDecimal>();

        for (OrderModel orderModel : listOfLiveSaleOrders) {
            BigDecimal numberOfKgs = orderModel.getOrderQunatity();
            BigDecimal price = orderModel.getPricePerKg();

            if (mapOfCollapsedLiveSaleOrders.containsKey(price)) {
                // Get the current number of kilograms at that price
                BigDecimal numberOfKgsAtThatPrice = mapOfCollapsedLiveSaleOrders.get(price);
                // Adding the extra number of kilograms from our iterated order model
                BigDecimal newNumberOfKgsAtThatPrice = numberOfKgsAtThatPrice.add(numberOfKgs);
                // Update the map with the new number of kilograms at that price
                mapOfCollapsedLiveSaleOrders.put(price, newNumberOfKgsAtThatPrice);
            } else {
                mapOfCollapsedLiveSaleOrders.put(price, numberOfKgs);
            }
        }

        Set set = mapOfCollapsedLiveSaleOrders.entrySet();
        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            Map.Entry mapEntry = (Map.Entry) iterator.next();
            result = result + mapEntry.getKey().toString() + " kg for £" + mapEntry.getValue().toString() + "\n";
        }

        return result;
    }

    public String getSummaryInformationOfLiveBuyOrders() {
        String result = new String();
        ArrayList<OrderModel> listOfLiveSaleOrders = getLiveSellOrders();

        // Price -> Number of Kilograms
        TreeMap<BigDecimal, BigDecimal> mapOfCollapsedLiveSaleOrders = new TreeMap<BigDecimal, BigDecimal>(Collections.reverseOrder());

        for (OrderModel orderModel : listOfLiveSaleOrders) {
            BigDecimal numberOfKgs = orderModel.getOrderQunatity();
            BigDecimal price = orderModel.getPricePerKg();

            if (mapOfCollapsedLiveSaleOrders.containsKey(price)) {
                // Get the current number of kilograms at that price
                BigDecimal numberOfKgsAtThatPrice = mapOfCollapsedLiveSaleOrders.get(price);
                // Adding the extra number of kilograms from our iterated order model
                BigDecimal newNumberOfKgsAtThatPrice = numberOfKgsAtThatPrice.add(numberOfKgs);
                // Update the map with the new number of kilograms at that price
                mapOfCollapsedLiveSaleOrders.put(price, newNumberOfKgsAtThatPrice);
            } else {
                mapOfCollapsedLiveSaleOrders.put(price, numberOfKgs);
            }
        }

        Set set = mapOfCollapsedLiveSaleOrders.entrySet();
        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            Map.Entry mapEntry = (Map.Entry) iterator.next();
            result = result + mapEntry.getKey().toString() + " kg for £" + mapEntry.getValue().toString() + "\n";
        }

        return result;
    }
}
