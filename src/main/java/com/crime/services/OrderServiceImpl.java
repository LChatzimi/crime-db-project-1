package com.crime.services;

import com.crime.dto.OrderDTO;
import com.crime.dto.ResponseGenericDTO;
import com.crime.entities.Order;
import com.crime.entities.OrderDetails;
import com.crime.entities.Product;
import com.crime.repositories.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

    final OrderRepository orderRepository;

    @Autowired
    EntityManager entityManager;

    static Map<String, Product> productMap = new HashMap<>();

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
//
    @Override
    public Order save(Order incident) {
        return orderRepository.save(incident);
    }

    @Override
    public void saveAll(List<Order> incidents) {
        orderRepository.saveAll(incidents);
    }

    @Override
    public void flushClear() {
        orderRepository.flush();
        entityManager.clear();
    }

    public void createDummyOrders(OrderDTO orderDTO) throws JsonProcessingException {

        for (int i = 0; i < 15; i++) {
            Order order = mapOrder(orderDTO);

            orderRepository.save(order);
        }
        new ResponseGenericDTO("Order created successfully", true);
    }

    private Order mapOrder(OrderDTO orderDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = objectMapper.convertValue(orderDTO, Order.class);
        order.setOrderId(UUID.randomUUID().toString());
        order.getOrderDetails().forEach(orderDetail -> {
            orderDetail.setOrder(order);
            Product product = getProduct(orderDetail);
            orderDetail.setProduct(product);
        });
        return order;
    }

    private Product getProduct(OrderDetails orderDetail) {

        Product product = entityManager.find(Product.class, orderDetail.getProduct().getProductCode());
        if (product == null) {
            product = productMap.get(orderDetail.getProduct().getProductCode());
        }
        if (product == null) {
            productMap.put(orderDetail.getProduct().getProductCode(), orderDetail.getProduct());
        }
        return product;
    }


    @Override
    public List<Object> getQuery2Results(String startDate, String endDate, String crimeCode) throws ParseException {
        Date start = formatter.parse(startDate);
        Date end = formatter.parse(endDate);

        List<Object> results = null; //incidentRepository.query2(start, end, crimeCode);

        return results;
    }


}