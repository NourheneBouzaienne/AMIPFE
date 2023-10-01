package com.example.db2microservice.dao;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository

public class SinistreDAO {

    private final JdbcTemplate jdbcTemplate;

    public SinistreDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Map<String, Object>> SinistreByID(String cin) {
        return jdbcTemplate.queryForList(
                "SELECT" +
                        "    V.DTSURV," +
                        "    V.N_SINISTRE," +
                        "    V.NUMCNT," +
                        "    V.IMMAT," +
                        "    V.IDCLT," +
                        "    V.ETAPEACTUELLE," +
                        "    V.DT_ETAT," +
                        "    V.TOTALREMBOURSE," +
                        "    V.CODE_ETAT," +
                        "    V.ETAT_QUIT," +
                        "    S.GOUVSINI," +
                        "    S.CITESINI"+
                        " FROM" +
                        "    ISAMMDATA.VIEWPORTAILCLIENT V" +
                        "    JOIN ISAMMDATA.PETB01 ON V.IDCLT = ISAMMDATA.PETB01.NUMPERS" +
                        "    JOIN ISAMMDATA.SITB01 S ON V.N_SINISTRE = S.NUMSNT" +
                        " WHERE" +
                        "    ISAMMDATA.PETB01.ID = ? ",
                cin
        );
    }


    public Optional<Map<String, Object>> SinistreByNUMSNT(String NUMSNT) {
        List<Map<String, Object>> results = jdbcTemplate.queryForList(
                "SELECT" +
                        "    V.DTSURV," +
                        "    V.N_SINISTRE," +
                        "    V.NUMCNT," +
                        "    V.IMMAT," +
                        "    V.IDCLT," +
                        "    V.ETAPEACTUELLE," +
                        "    V.DT_ETAT," +
                        "    V.TOTALREMBOURSE," +
                        "    V.CODE_ETAT," +
                        "    V.ETAT_QUIT," +
                        "    S.GOUVSINI," +
                        "    S.CITESINI"+
                        " FROM" +
                        "    ISAMMDATA.VIEWPORTAILCLIENT V" +
                        "    JOIN ISAMMDATA.PETB01 ON V.IDCLT = ISAMMDATA.PETB01.NUMPERS" +
                        "    JOIN ISAMMDATA.SITB01 S ON V.N_SINISTRE = S.NUMSNT" +
                        " WHERE" +
                        "    V.N_SINISTRE = ? ",
                NUMSNT
        );

        if (results.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(results.get(0));
        }
    }


}
