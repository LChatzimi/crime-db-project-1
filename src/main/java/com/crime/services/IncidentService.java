package com.crime.services;

import com.crime.entities.Order;

import java.text.ParseException;
import java.util.List;

public interface IncidentService {


    Order save(Order incident);

    void saveAll(List<Order> incidents);

    void flushClear();


    List<Object> getQuery2Results(String startDate, String endDate, String crimeCode) throws ParseException;


}