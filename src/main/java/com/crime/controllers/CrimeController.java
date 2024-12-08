package com.crime.controllers;

import com.crime.dtos.*;
import com.crime.enums.SortOrder;
import com.crime.services.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "Crime", description = "the Crime Api")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/crime")
public class CrimeController {

    final
    IncidentService incidentService;

    final DataImportService dataImportService;
    final CrimeCodeService crimeCodeService;
    final WeaponService weaponService ;
    final PersistService persistService;

    public CrimeController(IncidentService incidentService ,
                           DataImportService dataImportService,
                           CrimeCodeService crimeCodeService,
                           WeaponService weaponService ,
                           PersistService persistService) {
        this.incidentService = incidentService;
        this.dataImportService = dataImportService;
        this.crimeCodeService = crimeCodeService;
        this.weaponService = weaponService;
        this.persistService = persistService;
    }

    @Operation(
            summary = "Test ",
            description = "Test ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(value = "test",name = "test", produces = "application/json")
    public List<String> test(@RequestParam(name = "order", required = false,
            defaultValue = "DESCENDING") SortOrder sortOrder) {
        return new ArrayList<>();
    }

    @Operation(
            summary = "Query 1 ",
            description = "Find the total number of reports per “Crm Cd” that occurred within a specified time range\n" +
                    "and sort them in a descending order. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(value = "query1",name = "query1", produces = "application/json")
    public List<Query1ResponseDTO> getQuery1(@RequestParam(name = "startTime", required = true) String startTime,
                                             @RequestParam(name = "endTime", required = true) String endTime) throws ParseException {

        return incidentService.getQuery1Results(startTime, endTime);
    }


    @Operation(
            summary = "Query 2 ",
            description = "Find the total number of reports per day for a specific “Crm Cd” and time range.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(value = "query2",name = "query2", produces = "application/json")
    public List<Query2ResponseDTO> getQuery2(@RequestParam(name = "startTime", required = true) String startTime,
                                             @RequestParam(name = "endTime", required = true) String endTime,
                                             @RequestParam(name = "crimeCode", required = true) String crimeCode) throws ParseException {

        return incidentService.getQuery2Results(startTime,endTime, crimeCode);
    }

    @Operation(
            summary = "Query 3 ",
            description = "Find the most common crime committed regardless of code 1, 2, 3, and 4, per area for a specific day.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(value = "query3",name = "query3", produces = "application/json")
    public List<Query3ResponseDTO> getQuery3(@RequestParam(name = "date", required = true) String date) throws ParseException {

        return incidentService.getQuery3Results(date);

    }

    @Operation(
            summary = "Query 4 ",
            description = "Find the average number of crimes occurred per hour (24 hours) for a specific date range.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(value = "query4",name = "query4", produces = "application/json")
    public List<Query4ResponseDTO> getQuery4(@RequestParam(name = "startTime", required = true) String startTime,
                                             @RequestParam(name = "endTime", required = true) String endTime) throws ParseException {

        return incidentService.getQuery4Results(startTime,endTime);

    }

    @Operation(
            summary = "Query 5 ",
            description = "Find the most common “Crm Cd” in a specified bounding box (as designated by GPS-coordinates) for a specific day.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(value = "query5",name = "query5", produces = "application/json")
    public List<Query5ResponseDTO> getQuery5(@RequestParam(name = "specificDay", required = true) String specificDay,
                                             @RequestParam(name = "latFrom", required = true) double latFrom,
                                             @RequestParam(name = "latTo", required = true) double latTo,
                                             @RequestParam(name = "lonFrom", required = true) double lonFrom,
                                             @RequestParam(name = "lonTo", required = true) double lonTo) throws ParseException {

        return incidentService.getQuery5Results(specificDay,latFrom,latTo,lonFrom,lonTo);

    }

    @Operation(
            summary = "Query 6 ",
            description = "Find the top-5 Area names with regards to total number of crimes reported per day for a specific date range.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(value = "query6",name = "query6", produces = "application/json")
    public List<Query6ResponseDTO> getQuery6(@RequestParam(name = "startTime", required = true) String startTime,
                                             @RequestParam(name = "endTime", required = true) String endTime) throws ParseException {

        return incidentService.getQuery6Results(startTime,endTime);
    }

    @Operation(
            summary = "Query 6b ",
            description = "Find the top-5 Rpt Dist No with regards to total number of crimes reported per day for a specific date range.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(value = "query6b",name = "query6b", produces = "application/json")
    public List<Query6ResponseDTO> getQuery6b(@RequestParam(name = "startTime", required = true) String startTime,
                                             @RequestParam(name = "endTime", required = true) String endTime) throws ParseException {

        return incidentService.getQuery6bResults(startTime,endTime);
    }

    @Operation(
            summary = "Query 7 ",
            description = "Find the pair of crimes that has co-occurred in the area with the most reported incidents for a specific date range.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(value = "query7",name = "query7", produces = "application/json")
    public List<Query7ResponseDTO> getQuery7(@RequestParam(name = "startTime", required = true) String startTime,
                                             @RequestParam(name = "endTime", required = true) String endTime) throws ParseException {

        return incidentService.getQuery7Results(startTime,endTime);
    }

    @Operation(
            summary = "Query 8 ",
            description = "Find the second most common crime that has co-occurred with a particular crime for a specific date range.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(value = "query8",name = "query8", produces = "application/json")
    public List<Query8ResponseDTO> getQuery8(@RequestParam(name = "startTime", required = true) String startTime,
                                             @RequestParam(name = "endTime", required = true) String endTime,
                                             @RequestParam(name = "crimeCode", required = true) String crimeCode) throws ParseException {

        return incidentService.getQuery8Results(startTime,endTime,crimeCode);
    }


    @Operation(
            summary = "Query 9 ",
            description = "Find the most common type of weapon used against victims depending on their group of age. The age groups are formed by bucketing ages every 5 years, e.g., 0 ≤ x < 5, 5 ≤ x < 10, etc..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(value = "query9",name = "query9", produces = "application/json")
    public List<Query9ResponseDTO> getQuery9() throws ParseException {

        return incidentService.getQuery9Results();
    }

    @Operation(
            summary = "Query 10 ",
            description = "Find the area with the longest time range without an occurrence of a specific crime. Include the time range in the results.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(value = "query10",name = "query10", produces = "application/json")
    public List<Query10ResponseDTO> getQuery10(@RequestParam(name = "crimeCode", required = true) String crimeCode) throws ParseException {

        return incidentService.getQuery10Results(crimeCode);
    }

    @Operation(
            summary = "Query 10b ",
            description = "Find the Rpt Dist No with the longest time range without an occurrence of a specific crime. Include the time range in the results. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(value = "query10b",name = "query10b", produces = "application/json")
    public List<Query10ResponseDTO> getQuery10b(@RequestParam(name = "crimeCode", required = true) String crimeCode) throws ParseException {

        return incidentService.getQuery10bResults(crimeCode);
    }

    @Operation(
            summary = "Query 11 ",
            description = "For 2 crimes of your choice, find all areas that have received more than 1 report on each of these 2 crimes on the same day. The 2 crimes could be for example: “CHILD ANNOYING (17YRS & UNDER)” or “THEFT OF IDENTITY”. Do not restrict yourself to just these 2 specific types of crimes of course!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(value = "query11",name = "query11", produces = "application/json")
    public List<Query11ResponseDTO> getQuery11(@RequestParam(name = "crimeCode", required = true) String crimeCode,
                                               @RequestParam(name = "crimeCode2", required = true) String crimeCode2) throws ParseException {

        return incidentService.getQuery11Results(crimeCode,crimeCode2);
    }

    @Operation(
            summary = "Query 12 ",
            description = "Find the number of division of records for crimes reported on the same day in different areas using the same weapon for a specific time range.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(value = "query12",name = "query12", produces = "application/json")
    public List<Query12ResponseDTO> getQuery12(@RequestParam(name = "startTime", required = true) String startTime,
                                               @RequestParam(name = "endTime", required = true) String endTime) throws ParseException {

        return incidentService.getQuery12Results(startTime,endTime);
    }

    @Operation(
            summary = "Query 13 ",
            description = "Find the crimes that occurred for a given number of times N on the same day, in the same area, using the same weapon, for a specific time range. Return the list of division of records numbers, the area name, the crime code description and the weapon description.")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(value = "query13",name = "query13", produces = "application/json")
    public List<Query13ResponseDTO> getQuery13(@RequestParam(name = "startTime", required = true) String startTime,
                                               @RequestParam(name = "endTime", required = true) String endTime,
                                               @RequestParam(name = "crimeCount", required = true) int crimeCount) throws ParseException {

        return incidentService.getQuery13Results(startTime,endTime,crimeCount);
    }


    @Operation(
            summary = "Query 13 ",
            description = "Find the crimes that occurred for a given number of times N on the same day, in the same area, using the same weapon, for a specific time range. Return the list of division of records numbers, the area name, the crime code description and the weapon description.")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PostMapping(value = "add-crime",name = "add-crime", produces = "application/json")
    public ResponseEntity addCrime(@RequestBody List<FlatDTO> flatDTO) throws ParseException {
        try {
            dataImportService.importData(flatDTO,incidentService,crimeCodeService,weaponService,persistService);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
