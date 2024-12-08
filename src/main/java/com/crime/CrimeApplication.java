package com.crime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrimeApplication.class, args);
    }


    /**
     * This method is used to initialize the database with the data from the CSV files.
     *
     * @param incidentService
     * @return
     */
//    @Bean
//    public CommandLineRunner initData(IncidentService incidentService ,
//                                      CrimeCodeService crimeCodeService ,
//                                      WeaponService weaponService,
//                                      PersistService persistService,
//                                      DataImportService dataImportService) {
//        return (args) -> {
//            List<FlatDTO> flatDTOS = CrimeFileParser.parse("classpath:csv/Crime_Data_from_2020_to_Present_20241110.csv");
//            dataImportService.importData(flatDTOS, incidentService, crimeCodeService, weaponService , persistService);
//        };
//    }




}
