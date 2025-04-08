package com.crime.controllers;


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


    public CrimeController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }



    @Operation(
            summary = "Query 2 ",
            description = "Find the total number of reports per day for a specific “Crm Cd” and time range.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(value = "query2",name = "query2", produces = "application/json")
    public List<Object> getQuery2(@RequestParam(name = "startTime", required = true) String startTime,
                                             @RequestParam(name = "endTime", required = true) String endTime,
                                             @RequestParam(name = "crimeCode", required = true) String crimeCode) throws ParseException {

        return incidentService.getQuery2Results(startTime,endTime, crimeCode);
    }


    @Operation(
            summary = "Query 13 ",
            description = "Find the crimes that occurred for a given number of times N on the same day, in the same area, using the same weapon, for a specific time range. Return the list of division of records numbers, the area name, the crime code description and the weapon description.")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PostMapping(value = "add-crime",name = "add-crime", produces = "application/json")
    public ResponseEntity addCrime(@RequestBody List<Object> flatDTO) throws ParseException {
//        try {
//            dataImportService.importData(flatDTO,incidentService,crimeCodeService,weaponService,persistService);
//            return new ResponseEntity<>(HttpStatus.OK);
//        }catch (Exception e){
//            e.printStackTrace();
//            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
