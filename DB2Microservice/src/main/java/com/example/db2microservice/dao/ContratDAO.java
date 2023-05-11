package com.example.db2microservice.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ContratDAO {
    private final JdbcTemplate jdbcTemplate;

    public ContratDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> findAll() {
        return jdbcTemplate.queryForList("select * from prtb001  Limit 10 ");
    }


    public List<Map<String, Object>> findByIDAndNUMCNT(String cin , String numCNT) {
        return jdbcTemplate.queryForList(
                "SELECT  *  " +
                        "FROM ISAMMDATA.PRTB001,ISAMMDATA.PETB01 " +
                        "WHERE ISAMMDATA.PRTB001.NATCLT = ISAMMDATA.PETB01.CNAT" +
                        "          AND ISAMMDATA.PRTB001.IDCLT = ISAMMDATA.PETB01.NUMPERS" +
                        "          AND  ISAMMDATA.PRTB001.SITUAT IN ('E','R')"+
                        "          AND ISAMMDATA.PETB01.ID = ?" +
                        "          AND ISAMMDATA.PRTB001.NUMCNT = ?" , cin , numCNT
        );
    }
}
