package com.crime.util;

import com.crime.dtos.FlatDTO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CrimeFileParser {


    public static List<FlatDTO> parse( String locationPattern) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy H:mm");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        List<FlatDTO> crimeDataList = new ArrayList<>();
        try {
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources(locationPattern);
            for (Resource resource : resources) {
                try (Reader reader = new InputStreamReader(resource.getInputStream());
                     CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {
                    for (CSVRecord record : csvParser) {
                        FlatDTO flatDTO = new FlatDTO();
                        flatDTO.setDrNo(record.get(0));
                        LocalDateTime dateRptd = LocalDateTime.parse(record.get(1), formatter);
                        flatDTO.setDateRptd(dateRptd);
                        LocalDateTime dateOcc = LocalDateTime.parse(record.get(2), formatter);
                        flatDTO.setDateOcc(dateOcc);
                        flatDTO.setTimeOcc(record.get(3));
                        flatDTO.setArea(Integer.parseInt(record.get(4)));
                        flatDTO.setAreaName(record.get(5));
                        flatDTO.setRptDistNo(Integer.parseInt(record.get(6)));
                        flatDTO.setPart1Or2(Integer.parseInt(record.get(7)));
                        flatDTO.setCrmCd(record.get(8));
                        flatDTO.setCrmCdDesc(record.get(9));
                        flatDTO.setMocodes(record.get(10));
                        flatDTO.setVictAge(record.get(11));
                        flatDTO.setVictSex(record.get(12));
                        flatDTO.setVictDescent(record.get(13));
                        flatDTO.setPremisCd(record.get(14));
                        flatDTO.setPremisDesc(record.get(15));
                        flatDTO.setWeaponUsedCd(record.get(16));
                        flatDTO.setWeaponDesc(record.get(17));
                        flatDTO.setStatus(record.get(18));
                        flatDTO.setStatusDesc(record.get(19));
                        flatDTO.setCrmCd1(record.get(20));
                        flatDTO.setCrmCd2(record.get(21));
                        flatDTO.setCrmCd3(record.get(22));
                        flatDTO.setCrmCd4(record.get(23));
                        flatDTO.setLocation(record.get(24));
                        flatDTO.setCrossStreet(record.get(25));
                        flatDTO.setLat(Float.parseFloat(record.get(26)));
                        flatDTO.setLon(Float.parseFloat(record.get(27)));
                        crimeDataList.add(flatDTO);

                    }
                }
            }
            return crimeDataList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
