package com.example.db2microservice.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class QuittanceDAO {

    private final JdbcTemplate jdbcTemplate;

    public QuittanceDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> QuittanceByID(String cin) {
        return jdbcTemplate.queryForList(
                "SELECT * " +
                        "FROM ISAMMDATA.GPTB01 " +
                        "JOIN ISAMMDATA.PETB01 ON ISAMMDATA.GPTB01.NATCLT = ISAMMDATA.PETB01.CNAT " +
                        "                      AND ISAMMDATA.GPTB01.IDCLT = ISAMMDATA.PETB01.NUMPERS " +
                        "JOIN ISAMMDATA.PR002 AS PR ON ISAMMDATA.GPTB01.CODPROD = PR.CODPROD " +
                        "WHERE ISAMMDATA.PETB01.ID = ? " +
                        "      AND ISAMMDATA.GPTB01.STATQUIT = '0' " +
                        "      AND TO_DATE(SUBSTR(ISAMMDATA.GPTB01.FINEFFQUI, 1, 4) || '-' || SUBSTR(ISAMMDATA.GPTB01.FINEFFQUI, 5, 2) || '-' || SUBSTR(ISAMMDATA.GPTB01.FINEFFQUI, 7, 2), 'YYYY-MM-DD') >= CURRENT_DATE",
                cin
        );
    }

    public List<Map<String, Object>> ListQuittanceByID(String cin) {
        return jdbcTemplate.queryForList(
                "SELECT * " +
                        "FROM ISAMMDATA.GPTB01 " +
                        "JOIN ISAMMDATA.PETB01 ON ISAMMDATA.GPTB01.NATCLT = ISAMMDATA.PETB01.CNAT " +
                        "                      AND ISAMMDATA.GPTB01.IDCLT = ISAMMDATA.PETB01.NUMPERS " +
                        "JOIN ISAMMDATA.PR002 AS PR ON ISAMMDATA.GPTB01.CODPROD = PR.CODPROD " +
                        "WHERE ISAMMDATA.PETB01.ID = ? "+
                        "AND  ISAMMDATA.GPTB01.STATQUIT IN ('0','1')",
                cin
        );
    }



}
