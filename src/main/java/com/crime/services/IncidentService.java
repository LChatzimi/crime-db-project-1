package com.crime.services;

import com.crime.dtos.*;
import com.crime.entities.Incident;

import java.text.ParseException;
import java.util.List;

public interface IncidentService {


    Incident save(Incident incident);

    void saveAll(List<Incident> incidents);

    void flushClear();

    List<Query1ResponseDTO> getQuery1Results(String startDate, String endDate) throws ParseException;

    List<Query2ResponseDTO> getQuery2Results(String startDate, String endDate, String crimeCode) throws ParseException;

    List<Query3ResponseDTO> getQuery3Results(String date) throws ParseException ;

    List<Query4ResponseDTO> getQuery4Results(String startDate, String endDate) throws ParseException;

    List<Query5ResponseDTO> getQuery5Results(String specificDay, double latFrom , double latTo , double lonFrom , double lonTo) throws ParseException;

    List<Query6ResponseDTO> getQuery6Results(String startDate, String endDate) throws ParseException;

    List<Query6ResponseDTO> getQuery6bResults(String startDate, String endDate) throws ParseException;

    List<Query7ResponseDTO> getQuery7Results(String startDate, String endDate) throws ParseException;

    List<Query8ResponseDTO> getQuery8Results(String startDate, String endDate , String crimeCode) throws ParseException;

    List<Query9ResponseDTO> getQuery9Results() throws ParseException;

    List<Query10ResponseDTO> getQuery10Results(String crimeCode) throws ParseException;

    List<Query10ResponseDTO> getQuery10bResults(String crimeCode) throws ParseException;

    List<Query11ResponseDTO> getQuery11Results(String crimeCode , String  crimeCode2) throws ParseException;

    List<Query12ResponseDTO> getQuery12Results(String startDate, String endDate) throws ParseException;

    List<Query13ResponseDTO> getQuery13Results(String startDate, String endDate , int crimeCount) throws ParseException;
}