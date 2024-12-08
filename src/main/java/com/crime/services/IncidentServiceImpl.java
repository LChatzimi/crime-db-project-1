package com.crime.services;

import com.crime.dtos.*;
import com.crime.entities.Incident;
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
    public Incident save(Incident incident) {
        return incidentRepository.save(incident);
    }

    @Override
    public void saveAll(List<Incident> incidents) {
        incidentRepository.saveAll(incidents);
    }

    @Override
    public void flushClear() {
        incidentRepository.flush();
        entityManager.clear();
    }

    @Override
    public List<Query1ResponseDTO> getQuery1Results(String startDate, String endDate) throws ParseException {
        Date start = formatter.parse(startDate);
        Date end = formatter.parse(endDate);
        List<Object[]> results = incidentRepository.query1(start, end);

        // Map the results to DTO
        return results.stream()
                .map(row -> new Query1ResponseDTO(
                        (String) row[0],
                        ((Number) row[1]).longValue()
                ))
                .toList();
    }

    @Override
    public List<Query2ResponseDTO> getQuery2Results(String startDate, String endDate, String crimeCode) throws ParseException {
        Date start = formatter.parse(startDate);
        Date end = formatter.parse(endDate);

        List<Object[]> results = incidentRepository.query2(start, end, crimeCode);

        return results.stream()
                .map(row -> new Query2ResponseDTO(
                        ((java.sql.Date) row[0]).toLocalDate().atStartOfDay(),
                        ((Number) row[1]).longValue()))
                .toList();
    }


    @Override
    public List<Query3ResponseDTO> getQuery3Results(String date) throws ParseException {
        Date queryDate = formatter.parse(date);
        List<Object[]> results = incidentRepository.query3(queryDate);

        return results.stream()
                .map(row -> new Query3ResponseDTO(
                        (String) row[0],
                        (String) row[1],
                        ((Number) row[2]).longValue()
                ))
                .toList();
    }

    @Override
    public List<Query4ResponseDTO> getQuery4Results(String startDate, String endDate) throws ParseException {
        Date start = formatter.parse(startDate);
        Date end = formatter.parse(endDate);
        List<Object[]> results = incidentRepository.query4(start, end);

        return results.stream()
                .map(row -> new Query4ResponseDTO(
                        ((BigDecimal) row[0]).intValue(),
                        ((BigDecimal) row[1]).doubleValue()
                ))
                .toList();
    }

    @Override
    public List<Query5ResponseDTO> getQuery5Results(String specificDayString, double latFrom , double latTo , double lonFrom , double lonTo) throws ParseException {
        Date specificDay = formatter.parse(specificDayString);
        List<Object[]> results = incidentRepository.query5(specificDay, latFrom, latTo, lonFrom, lonTo);

        return results.stream()
                .map(row -> new Query5ResponseDTO(
                        (String) row[0],
                        ((Number) row[1]).longValue()
                ))
                .toList();
    }


    @Override
    public List<Query6ResponseDTO> getQuery6Results(String startDate, String endDate) throws ParseException {
        Date start = formatter.parse(startDate);
        Date end = formatter.parse(endDate);
        List<Object[]> results = incidentRepository.query6(start, end);

        return results.stream()
                .map(row -> new Query6ResponseDTO(
                        (String) row[0],
                        ((java.sql.Date) row[1]).toLocalDate().atStartOfDay(),
                        ((Number) row[2]).longValue()
                ))
                .toList();
    }


    @Override
    public List<Query6ResponseDTO> getQuery6bResults(String startDate, String endDate) throws ParseException {
        Date start = formatter.parse(startDate);
        Date end = formatter.parse(endDate);
        List<Object[]> results = incidentRepository.query6b(start, end);

        return results.stream()
                .map(row -> new Query6ResponseDTO(
                        ((Integer) row[0]).toString(),
                        ((java.sql.Date) row[1]).toLocalDate().atStartOfDay(),
                        ((Number) row[2]).longValue()
                ))
                .toList();
    }

    @Override
    public List<Query7ResponseDTO> getQuery7Results(String startDate, String endDate) throws ParseException {
        Date start = formatter.parse(startDate);
        Date end = formatter.parse(endDate);
        List<Object[]> results = incidentRepository.query7(start, end);

        return results.stream()
                .map(row -> new Query7ResponseDTO(
                        (String) row[0],
                        (String) row[1],
                        ((java.sql.Date) row[2]).toLocalDate().atStartOfDay(),
                        (String) row[3],
                        (String) row[4],
                        (String) row[5],
                        ((Number) row[6]).longValue()
                ))
                .toList();
    }

    @Override
    public List<Query8ResponseDTO> getQuery8Results(String startDate, String endDate , String crimeCode) throws ParseException {
        Date start = formatter.parse(startDate);
        Date end = formatter.parse(endDate);
        List<Object[]> results = incidentRepository.query8(start, end, crimeCode);

        return results.stream()
                .map(row -> new Query8ResponseDTO(
                        (String) row[0],
                        ((Number) row[1]).longValue()
                ))
                .toList();
    }

    @Override
    public List<Query9ResponseDTO> getQuery9Results() throws ParseException {
        List<Object[]> results = incidentRepository.query9();

        return results.stream()
                .map(row -> new Query9ResponseDTO(
                        (Double) row[0],
                        (String) row[1],
                        ((Number) row[2]).longValue()
                ))
                .toList();
    }


    @Override
    public List<Query10ResponseDTO> getQuery10Results(String crimeCode) throws ParseException {
        List<Object[]> results = incidentRepository.query10(crimeCode);

        return results.stream()
                .map(row -> new Query10ResponseDTO(
                        (String) row[0],
                        ((Number) row[1]).longValue(),
                        ((java.sql.Date) row[2]).toLocalDate().atStartOfDay(),
                        ((java.sql.Date) row[3]).toLocalDate().atStartOfDay()
                ))
                .toList();
    }

    @Override
    public List<Query10ResponseDTO> getQuery10bResults(String crimeCode) throws ParseException {
        List<Object[]> results = incidentRepository.query10b(crimeCode);

        return results.stream()
                .map(row -> new Query10ResponseDTO(
                        ((Integer) row[0]).toString(),
                        ((Number) row[1]).longValue(),
                        ((java.sql.Date) row[2]).toLocalDate().atStartOfDay(),
                        ((java.sql.Date) row[3]).toLocalDate().atStartOfDay()
                ))
                .toList();
    }

    @Override
    public List<Query11ResponseDTO> getQuery11Results(String crimeCode , String  crimeCode2) throws ParseException {
        List<Object[]> results = incidentRepository.query11(crimeCode, crimeCode2);

        return results.stream()
                .map(row -> new Query11ResponseDTO(
                        (String) row[0],
                        ((java.sql.Date) row[1]).toLocalDate().atStartOfDay(),
                        (String) row[2],
                        (String) row[3],
                        (String) row[4],
                        (String) row[5]
                ))
                .toList();
    }

    @Override
    public List<Query12ResponseDTO> getQuery12Results(String startDate, String endDate) throws ParseException {
        Date start = formatter.parse(startDate);
        Date end = formatter.parse(endDate);
        List<Object[]> results = incidentRepository.query12(start, end);

        return results.stream()
                .map(row -> new Query12ResponseDTO(
                        ((Number) row[0]).longValue(),
                        ((Number) row[1]).longValue()
                ))
                .toList();
    }

    @Override
    public List<Query13ResponseDTO> getQuery13Results(String startDate, String endDate , int crimeCount) throws ParseException {
        Date start = formatter.parse(startDate);
        Date end = formatter.parse(endDate);
        List<Object[]> results = incidentRepository.query13(start, end, crimeCount);

        return results.stream().map(row -> new Query13ResponseDTO(
                ((Number) row[0]).longValue(),
                (String) row[1],
                (String) row[2],
                (String) row[3],
                ((Number) row[4]).longValue()
        )).toList();
    }
}