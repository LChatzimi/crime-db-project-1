package com.crime.services;

import com.crime.dto.OrderDTO;
import com.crime.entities.Order;

import java.text.ParseException;
import java.util.List;

public interface OrderService {


    Order save(Order order);

    void saveAll(List<Order> orders);

    void flushClear();


    List<Object> getQuery2Results(String startDate, String endDate, String productCode) throws ParseException;

    void createDummyOrders(OrderDTO orderDTO) throws Exception;

}