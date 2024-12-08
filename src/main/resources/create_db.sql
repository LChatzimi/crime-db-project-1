drop schema public cascade ;
create schema public ;

-- 8. PREMISE Table
CREATE TABLE PREMISE (
                         PREMIS_CD VARCHAR PRIMARY KEY,
                         PREMIS_DESC VARCHAR
);

CREATE INDEX idx_premise_premis_desc ON PREMISE (PREMIS_DESC);


-- 2. AREA Table
CREATE TABLE AREA (
                      AREA INT PRIMARY KEY,
                      AREA_NAME VARCHAR
);

CREATE INDEX idx_area_area_name ON AREA (AREA_NAME);

-- 9. LOCATION Table
--CREATE TABLE LOCATION (
--    LOCATION VARCHAR ,
--    CROSS_STREET VARCHAR,
--    AREA INT REFERENCES AREA(AREA),
--    PREMIS_CD VARCHAR REFERENCES PREMISE(PREMIS_CD),
--    PRIMARY KEY (LOCATION, CROSS_STREET, AREA, PREMIS_CD)
--);

--CREATE INDEX idx_location_cross_street ON LOCATION (CROSS_STREET);
--CREATE INDEX idx_location_area ON LOCATION (AREA);
--CREATE INDEX idx_location_premis_cd ON LOCATION (PREMIS_CD);

-- 1. INCIDENT Table
CREATE TABLE INCIDENT (
                          DR_NO VARCHAR PRIMARY KEY,
                          DATE_RPTD DATE,
                          DATE_OCC DATE,
                          TIME_OCC VARCHAR,
                          RPT_DIST_NO INT,
                          STATUS VARCHAR,
                          STATUS_DESC VARCHAR,
                          LOCATION VARCHAR NOT NULL,
                          CROSS_STREET VARCHAR NOT NULL,
                          AREA INT NOT null REFERENCES AREA(AREA),
                          PREMIS_CD VARCHAR NOT null references PREMISE(PREMIS_CD) ,
                          LAT FLOAT,
                          LON FLOAT
);

CREATE INDEX idx_incident_date_rptd ON INCIDENT (DATE_RPTD);
CREATE INDEX idx_incident_date_occ ON INCIDENT (DATE_OCC);
CREATE INDEX idx_incident_location ON INCIDENT (LOCATION);
CREATE INDEX idx_incident_status ON INCIDENT (STATUS);
CREATE INDEX idx_incident_lat_lon ON INCIDENT (LAT, LON);

-- 3. CRIME_CODE Table
CREATE TABLE CRIME_CODE (
                            CRM_CD VARCHAR PRIMARY KEY,
                            CRM_CD_DESC VARCHAR
);

CREATE INDEX idx_crime_code_desc ON CRIME_CODE (CRM_CD_DESC);


-- 4. INCIDENT_CRIME Table
CREATE TABLE INCIDENT_CRIME (
                                DR_NO VARCHAR REFERENCES INCIDENT(DR_NO),
                                CRM_CD VARCHAR REFERENCES CRIME_CODE(CRM_CD),
                                CRIME_RANK INT ,
                                PRIMARY KEY (DR_NO,CRM_CD,CRIME_RANK)
);

CREATE INDEX idx_incident_crime_dr_no ON INCIDENT_CRIME (DR_NO);
CREATE INDEX idx_incident_crime_crm_cd ON INCIDENT_CRIME (CRM_CD);
CREATE INDEX idx_incident_crime_crime_rank ON INCIDENT_CRIME (CRIME_RANK);

-- 5. VICTIM Table
CREATE TABLE VICTIM (
                        VICTIM_ID SERIAL PRIMARY KEY,
                        DR_NO VARCHAR REFERENCES INCIDENT(DR_NO),
                        VICT_AGE INT,
                        VICT_SEX CHAR(1),
                        VICT_DESCENT CHAR(1)
);


CREATE INDEX idx_victim_dr_no ON VICTIM (DR_NO);
CREATE INDEX idx_victim_age ON VICTIM (VICT_AGE);
CREATE INDEX idx_victim_sex ON VICTIM (VICT_SEX);
CREATE INDEX idx_victim_descent ON VICTIM (VICT_DESCENT);

-- 6. WEAPON Table
CREATE TABLE WEAPON (
                        WEAPON_USED_CD VARCHAR PRIMARY KEY,
                        WEAPON_DESC VARCHAR
);

CREATE INDEX idx_weapon_desc ON WEAPON (WEAPON_DESC);

-- 7. INCIDENT_WEAPON Table
CREATE TABLE INCIDENT_WEAPON (
                                 INCIDENT_WEAPON_ID SERIAL PRIMARY KEY,
                                 DR_NO VARCHAR REFERENCES INCIDENT(DR_NO),
                                 WEAPON_USED_CD VARCHAR REFERENCES WEAPON(WEAPON_USED_CD)
);

CREATE INDEX idx_incident_weapon_dr_no ON INCIDENT_WEAPON (DR_NO);
CREATE INDEX idx_incident_weapon_cd ON INCIDENT_WEAPON (WEAPON_USED_CD);



-- 10. MO_CODE Table
CREATE TABLE MO_CODE (
                         DR_NO VARCHAR REFERENCES INCIDENT(DR_NO),
                         MOCODES VARCHAR,
                         PRIMARY KEY (DR_NO, MOCODES)
);

CREATE INDEX idx_mo_code_dr_no ON MO_CODE (DR_NO);
CREATE INDEX idx_mo_code_mocodes ON MO_CODE (MOCODES);
