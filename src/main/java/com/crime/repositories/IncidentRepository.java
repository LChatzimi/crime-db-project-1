package com.crime.repositories;

import com.crime.entities.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IncidentRepository extends JpaRepository<Incident, Long> {


    @Query(value = """
        SELECT 
            ic.crm_cd AS crimeCode,
            COUNT(*) AS totalReports
        FROM  incident i
        JOIN incident_crime ic ON i.dr_no = ic.dr_no
        WHERE 
            i.date_occ BETWEEN :startDate AND :endDate
            AND ic.crime_rank = '1'
        GROUP BY ic.crm_cd
        ORDER BY totalReports DESC
        """, nativeQuery = true)
    List<Object[]> query1(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );


    @Query(value = """
    SELECT 
        i.date_occ AS dateOccurrence,
        COUNT(*) AS totalReports
    FROM incident i
    JOIN incident_crime ic ON i.dr_no = ic.dr_no
    WHERE 
        i.date_occ BETWEEN :startDate AND :endDate
        AND ic.crime_rank = '1'
        AND ic.crm_cd = :crimeCode
    GROUP BY 
        i.date_occ
    ORDER BY 
        totalReports DESC
    """, nativeQuery = true)
    List<Object[]> query2(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("crimeCode") String crimeCode);

    @Query(value = """
    SELECT 
        a.area_name , a.crm_cd , a.count
    FROM (
        SELECT 
            a.area_name,
            ic.crm_cd,
            COUNT(i.*) AS count,
            ROW_NUMBER() OVER (PARTITION BY a.area ORDER BY COUNT(i.*) DESC) AS crimeAreaRank
        FROM  incident i
        JOIN  incident_crime ic ON i.dr_no = ic.dr_no
        JOIN  area a ON i.area = a.area
        WHERE 
            i.date_occ = :specificDate
        GROUP BY 
            a.area, ic.crm_cd
    ) a
    WHERE 
        a.crimeAreaRank = 1
    """, nativeQuery = true)
    List<Object[]> query3(@Param("specificDate") Date specificDate);

    @Query(value = """
            SELECT distinct
            hour_of_day,
            AVG(total_crimes) OVER (partition by hour_of_day) AS avg_crimes_per_hour
        FROM (
            SELECT
                EXTRACT(HOUR FROM TO_TIMESTAMP(
                    LPAD(i.TIME_OCC || '', 4, '0'), 'HH24MI:SS'
                )) AS hour_of_day,
                i.date_occ,
                COUNT(*) AS total_crimes
            FROM
                INCIDENT i
            WHERE
                i.DATE_OCC BETWEEN :startDate and :endDate
            GROUP BY
                hour_of_day , i.date_occ
        ) AS hourly_crimes
        ORDER BY
            hour_of_day
        """, nativeQuery = true)
    List<Object[]> query4(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );

    @Query(value = """
        SELECT 
        ic.CRM_CD,
        COUNT(*) AS crime_count
        FROM 
            INCIDENT i
        JOIN 
            INCIDENT_CRIME ic ON i.DR_NO = ic.DR_NO
        WHERE 
            i.DATE_OCC = :specificDay
            AND i.LAT BETWEEN :latFrom AND :latTo  
            AND i.LON BETWEEN :lonFrom AND :lonTo   
        GROUP BY 
            ic.CRM_CD
        ORDER BY 
            crime_count DESC
        LIMIT 1
        """, nativeQuery = true)
    List<Object[]> query5(
            @Param("specificDay") Date specificDay,
            @Param("latFrom") double latFrom,
            @Param("latTo") double latTo,
            @Param("lonFrom") double lonFrom,
            @Param("lonTo") double lonTo
    );


    @Query(value = """
        WITH ranked_areas AS (
            SELECT 
                a.AREA_NAME, 
                i.DATE_OCC, 
                COUNT(*) AS total_crimes,
                ROW_NUMBER() OVER (PARTITION BY i.DATE_OCC ORDER BY COUNT(*) DESC) AS rank
            FROM 
                INCIDENT i
            JOIN 
                AREA a ON i.AREA = a.AREA
            WHERE 
                i.DATE_OCC BETWEEN :startDate AND :endDate  
            GROUP BY 
                a.AREA_NAME, i.DATE_OCC
            )
            SELECT 
                AREA_NAME, 
                DATE_OCC, 
                total_crimes
            FROM 
                ranked_areas
            WHERE 
                rank <= 5
            ORDER BY 
                DATE_OCC, rank
        """, nativeQuery = true)
    List<Object[]> query6(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );


    @Query(value = """
        WITH ranked_districts AS (
        SELECT 
            i.RPT_DIST_NO, 
            i.DATE_OCC, 
            COUNT(*) AS total_crimes,
            ROW_NUMBER() OVER (PARTITION BY i.DATE_OCC ORDER BY COUNT(*) DESC) AS rank
        FROM 
            INCIDENT i
        WHERE 
            i.DATE_OCC BETWEEN :startDate AND :endDate
        GROUP BY 
            i.RPT_DIST_NO, i.DATE_OCC
        )
        SELECT 
            RPT_DIST_NO, 
            DATE_OCC, 
            total_crimes
        FROM 
            ranked_districts
        WHERE 
            rank <= 5
        ORDER BY 
            DATE_OCC, rank
        """, nativeQuery = true)
    List<Object[]> query6b(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );


    @Query(value = """
            WITH ranked_areas AS (
            SELECT
                a.AREA_NAME,
                COUNT(*) AS total_crimes,
                ROW_NUMBER() OVER (ORDER BY COUNT(*) DESC) AS rank
            FROM
                INCIDENT i
                    JOIN
                AREA a ON i.AREA = a.AREA
            WHERE
                i.DATE_OCC BETWEEN :startDate AND :endDate
            GROUP BY
                a.AREA_NAME
            )
            select
                i.dr_no ,
                a.AREA_NAME,
                i.DATE_OCC,
                ic1.CRM_CD AS crime1,
                ic2.CRM_CD AS crime2,
                ic3.crm_cd as crime3,
                COUNT(*) AS total_crimes
            FROM
                INCIDENT i
                    JOIN
                INCIDENT_CRIME ic1 ON i.DR_NO = ic1.DR_NO
                    JOIN
                INCIDENT_CRIME ic2 ON i.DR_NO = ic2.DR_NO
                left join incident_crime ic3 ON i.DR_NO = ic3.DR_NO AND ic3.crime_rank = '3'
                    JOIN
                AREA a ON i.AREA = a.AREA
                    JOIN
                ranked_areas ra ON a.AREA_NAME = ra.AREA_NAME 
            WHERE
                i.DATE_OCC BETWEEN :startDate AND :endDate
              AND ra.rank = 1
              AND ic1.crime_rank = '1'
              and  ic2.crime_rank = '2'
            GROUP BY
                i.dr_no ,a.AREA_NAME, i.DATE_OCC, ic1.CRM_CD, ic2.CRM_CD ,ic3.crm_cd 
            ORDER BY
                total_crimes DESC
        """, nativeQuery = true)
    List<Object[]> query7(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );
    
    @Query(value = """
            WITH co_occurring_crimes AS (
                SELECT
                    ic2.CRM_CD AS co_crime,
                    COUNT(*) AS co_occurrences
                FROM
                    INCIDENT_CRIME ic1
                JOIN
                    INCIDENT_CRIME ic2 ON ic1.DR_NO = ic2.DR_NO
                JOIN
                    INCIDENT i ON ic1.DR_NO = i.DR_NO
                WHERE
                    ic1.CRM_CD = :crimeCode
                    AND ic2.CRM_CD <> ic1.CRM_CD  
                    AND i.DATE_OCC BETWEEN :startDate AND :endDate
                GROUP BY
                    ic2.CRM_CD
                ORDER BY
                    co_occurrences DESC
            ),
            ranked_crimes AS (
                SELECT
                    co_crime,
                    co_occurrences,
                    ROW_NUMBER() OVER (ORDER BY co_occurrences DESC) AS rank
                FROM
                    co_occurring_crimes
            )
            SELECT
                co_crime AS second_most_common_crime,
                co_occurrences
            FROM
                ranked_crimes
            WHERE
                rank = 2
        """, nativeQuery = true)
    List<Object[]> query8(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("crimeCode") String crimeCode

    );
    
    @Query(value = """
            WITH age_grouped_weapons AS (
                            SELECT
                                FLOOR(v.VICT_AGE / 5) * 5 AS age_group_start,  
                                w.WEAPON_DESC,                              
                                COUNT(*) AS weapon_usage                    
                            FROM
                                VICTIM v
                            JOIN
                                INCIDENT_WEAPON iw ON v.DR_NO = iw.DR_NO
                            JOIN
                                WEAPON w ON iw.WEAPON_USED_CD = w.WEAPON_USED_CD
                            WHERE
                                v.VICT_AGE IS NOT null and v.vict_sex is not null  
                            GROUP BY
                                age_group_start, w.WEAPON_DESC
                        ),
                        ranked_weapons AS (
                            SELECT
                                age_group_start,
                                WEAPON_DESC,
                                weapon_usage,
                                RANK() OVER (PARTITION BY age_group_start ORDER BY weapon_usage DESC) AS rank
                            FROM
                                age_grouped_weapons
                        )
                        SELECT
                            age_group_start AS age_group,
                            WEAPON_DESC AS most_common_weapon,
                            weapon_usage
                        FROM
                            ranked_weapons
                        WHERE
                            rank = 1 
                        ORDER BY
                            age_group
        """, nativeQuery = true)
    List<Object[]> query9();


    @Query(value = """
            WITH crime_occurrences AS (
                SELECT
                    a.AREA_NAME AS AREA,
                    i.DATE_OCC
                FROM
                    INCIDENT i
                JOIN
                    INCIDENT_CRIME ic ON i.DR_NO = ic.DR_NO
                JOIN 
                    AREA a ON i.AREA = a.AREA
                WHERE
                    ic.CRM_CD = :crimeCode  
                ORDER BY
                    i.AREA, i.DATE_OCC
            ),
            time_gaps AS (
                SELECT
                    AREA,
                    DATE_OCC AS occurrence_time,
                    LAG(DATE_OCC) OVER (PARTITION BY AREA ORDER BY DATE_OCC) AS previous_occurrence_time
                FROM
                    crime_occurrences
            ),
            gap_durations AS (
                SELECT
                    AREA,
                    previous_occurrence_time,
                    occurrence_time,
                    occurrence_time - previous_occurrence_time AS gap_duration
                FROM
                    time_gaps
                WHERE
                    previous_occurrence_time IS NOT NULL
            ),
            longest_gap_per_area AS (
                SELECT
                    AREA,
                    MAX(gap_duration) AS longest_gap
                FROM
                    gap_durations
                GROUP BY
                    AREA
            )
            SELECT
                gd.AREA,
                gd.gap_duration AS longest_gap,
                gd.previous_occurrence_time AS start_time,
                gd.occurrence_time AS end_time
            FROM  gap_durations gd
            JOIN longest_gap_per_area lg ON gd.AREA = lg.AREA AND gd.gap_duration = lg.longest_gap
            ORDER BY
                longest_gap DESC
            LIMIT 1
        """, nativeQuery = true)
    List<Object[]> query10(
            @Param("crimeCode") String crimeCode

    );
    
    
    
    @Query(value = """
            WITH crime_occurrences AS (
                SELECT
                    i.RPT_DIST_NO,
                    i.DATE_OCC
                FROM
                    INCIDENT i
                JOIN
                    INCIDENT_CRIME ic ON i.DR_NO = ic.DR_NO
                WHERE
                    ic.CRM_CD = :crimeCode
                ORDER BY
                    i.RPT_DIST_NO, i.DATE_OCC
            ),
            time_gaps AS (
                SELECT
                    RPT_DIST_NO,
                    DATE_OCC AS occurrence_time,
                    LAG(DATE_OCC) OVER (PARTITION BY RPT_DIST_NO ORDER BY DATE_OCC) AS previous_occurrence_time
                FROM
                    crime_occurrences
            ),
            gap_durations AS (
                SELECT
                    RPT_DIST_NO,
                    previous_occurrence_time,
                    occurrence_time,
                    occurrence_time - previous_occurrence_time AS gap_duration
                FROM
                    time_gaps
                WHERE
                    previous_occurrence_time IS NOT NULL
            ),
            longest_gap_per_rpt_dist_no AS (
                SELECT
                    RPT_DIST_NO,
                    MAX(gap_duration) AS longest_gap
                FROM
                    gap_durations
                GROUP BY
                    RPT_DIST_NO
            )
            SELECT
                gd.RPT_DIST_NO,
                gd.gap_duration,
                gd.previous_occurrence_time AS start_time,
                gd.occurrence_time AS end_time
            FROM gap_durations gd
            JOIN longest_gap_per_rpt_dist_no lg ON  gd.RPT_DIST_NO = lg.RPT_DIST_NO AND gd.gap_duration = lg.longest_gap
            ORDER BY
                longest_gap DESC
            LIMIT 1
        """, nativeQuery = true)
    List<Object[]> query10b(
            @Param("crimeCode") String crimeCode
    );
    
    
    @Query(value = """
            WITH crime_reports AS (
                       SELECT
                           a.AREA_NAME,
                           i.DATE_OCC,
                           ic.CRM_CD,
                           COUNT(*) AS crime_count
                       FROM
                           INCIDENT i
                       JOIN
                           INCIDENT_CRIME ic ON i.DR_NO = ic.DR_NO
                       JOIN
                           AREA a ON i.AREA = a.AREA
                       WHERE
                           ic.CRM_CD IN (:crimeCode, :crimeCode2)  
                       GROUP BY
                           a.AREA_NAME, i.DATE_OCC, ic.CRM_CD
                       HAVING
                           COUNT(*) > 1   
                   ),
                   crime_pairs AS (
                       SELECT
                           r1.AREA_NAME,
                           r1.DATE_OCC,
                           r1.CRM_CD as c1,
                           cr1.crm_cd_desc as desc1,
                           r2.CRM_CD as c2 ,
                           cr2.crm_cd_desc as desc2
                       FROM
                           crime_reports r1
                       join crime_code cr1 on cr1.crm_cd = r1.crm_cd
                       join crime_reports r2
                       join crime_code cr2 on cr2.crm_cd = r2.crm_cd
                           ON r1.AREA_NAME = r2.AREA_NAME
                           AND r1.DATE_OCC = r2.DATE_OCC
                           AND r1.CRM_CD < r2.CRM_CD  
                   )
                   SELECT
                       DISTINCT AREA_NAME,
                       DATE_OCC,
                       c1,
                       desc1,
                       c2,
                       desc2
                   FROM
                       crime_pairs
                   ORDER BY
                       AREA_NAME, DATE_OCC
        """, nativeQuery = true)
    List<Object[]> query11(
            @Param("crimeCode") String crimeCode,
            @Param("crimeCode2") String crimeCode2
    );
    

    @Query(value = """
        WITH weapon_usage AS (
          SELECT
              i.DATE_OCC,
              a.AREA_NAME,
              iw.WEAPON_USED_CD
          FROM
              INCIDENT i
          JOIN
              AREA a ON i.AREA = a.AREA
          JOIN
              INCIDENT_WEAPON iw ON i.DR_NO = iw.DR_NO
          WHERE
              i.DATE_OCC BETWEEN :startDate AND :endDate
          GROUP BY
              i.DATE_OCC, a.AREA_NAME, iw.WEAPON_USED_CD
      ),
      division_counts AS (
          SELECT
              DATE_OCC,
              WEAPON_USED_CD,
              COUNT(DISTINCT AREA_NAME) AS area_count
          FROM
              weapon_usage
          GROUP BY
              DATE_OCC, WEAPON_USED_CD
          HAVING
              COUNT(DISTINCT AREA_NAME) > 1  
      )
      SELECT
          COUNT(*) AS total_divisions,
          SUM(area_count) AS total_area_usages
      FROM
          division_counts
        """, nativeQuery = true)
    List<Object[]> query12(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );

    @Query(value = """

            WITH crime_occurrences AS (
                 SELECT
                     a.AREA_NAME,
                     i.RPT_DIST_NO,
                     i.DATE_OCC,
                     ic.CRM_CD,
                     cc.CRM_CD_DESC,
                     iw.WEAPON_USED_CD,
                     w.WEAPON_DESC,
                     COUNT(*) AS crime_count
                 FROM
                     INCIDENT i
                 JOIN
                     INCIDENT_CRIME ic ON i.DR_NO = ic.DR_NO
                 JOIN
                     CRIME_CODE cc ON ic.CRM_CD = cc.CRM_CD
                 JOIN
                     INCIDENT_WEAPON iw ON i.DR_NO = iw.DR_NO
                 JOIN
                     WEAPON w ON iw.WEAPON_USED_CD = w.WEAPON_USED_CD
                 JOIN
                     AREA a ON i.AREA = a.AREA
                 WHERE
                     i.DATE_OCC BETWEEN :startDate AND :endDate
                 GROUP BY
                     a.AREA_NAME, i.RPT_DIST_NO, i.DATE_OCC, ic.CRM_CD, cc.CRM_CD_DESC, iw.WEAPON_USED_CD, w.WEAPON_DESC
             )
             SELECT
                 RPT_DIST_NO AS division_of_records,
                 AREA_NAME,
                 CRM_CD_DESC AS crime_description,
                 WEAPON_DESC AS weapon_description,
                 crime_count
             FROM
                     crime_occurrences
                 WHERE
                     crime_count = :crimeCount
             ORDER BY
                 AREA_NAME, DATE_OCC, crime_description
        """, nativeQuery = true)
    List<Object[]> query13(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("crimeCount") int crimeCount
    );



}