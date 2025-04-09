package com.crime.repositories;

import com.crime.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

//    @Query(value = """
//    SELECT
//        i.date_occ AS dateOccurrence,
//        COUNT(*) AS totalReports
//    FROM incident i
//    JOIN incident_crime ic ON i.dr_no = ic.dr_no
//    WHERE
//        i.date_occ BETWEEN :startDate AND :endDate
//        AND ic.crime_rank = '1'
//        AND ic.crm_cd = :crimeCode
//    GROUP BY
//        i.date_occ
//    ORDER BY
//        totalReports DESC
//    """, nativeQuery = true)
//    List<Object[]> query2(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("crimeCode") String crimeCode);
//

}