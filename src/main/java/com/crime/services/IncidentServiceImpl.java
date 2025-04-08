package com.crime.services;

import com.crime.entities.Order;
import com.crime.repositories.IncidentRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class IncidentServiceImpl implements IncidentService {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

    final IncidentRepository incidentRepository;

    @Autowired
    EntityManager entityManager;

    public IncidentServiceImpl(IncidentRepository cryptoTypeRepository) {
        this.incidentRepository = cryptoTypeRepository;
    }
//
    @Override
    public Order save(Order incident) {
        return incidentRepository.save(incident);
    }

    @Override
    public void saveAll(List<Order> incidents) {
        incidentRepository.saveAll(incidents);
    }

    @Override
    public void flushClear() {
        incidentRepository.flush();
        entityManager.clear();
    }



    @Override
    public List<Object> getQuery2Results(String startDate, String endDate, String crimeCode) throws ParseException {
        Date start = formatter.parse(startDate);
        Date end = formatter.parse(endDate);

        List<Object> results = null; //incidentRepository.query2(start, end, crimeCode);

        return results;
    }


}