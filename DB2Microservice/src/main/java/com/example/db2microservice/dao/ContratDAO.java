package com.example.db2microservice.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
                        "FROM ISAMMDATA.PRTB001,ISAMMDATA.PETB01,ISAMMDATA.PR002 " +
                        "WHERE ISAMMDATA.PRTB001.NATCLT = ISAMMDATA.PETB01.CNAT" +
                        "          AND ISAMMDATA.PRTB001.IDCLT = ISAMMDATA.PETB01.NUMPERS" +
                        "          AND ISAMMDATA.PRTB001.CODPROD = ISAMMDATA.PR002.CODPROD"+
                        "          AND  ISAMMDATA.PRTB001.SITUAT IN ('E','R')"+
                        "          AND ISAMMDATA.PETB01.ID = ?" +
                        "          AND ISAMMDATA.PRTB001.NUMCNT = ?" , cin , numCNT
        );
    }


    public List<Map<String, Object>> findByID(String cin) {
        return jdbcTemplate.queryForList(
                "SELECT I.NOM1 AS NOM_INT, T.*" +
                        "    FROM ISAMMDATA.PETB01 AS I" +
                        "         JOIN (" +
                        "                 SELECT *" +
                        "                     FROM ISAMMDATA.PETB01 AS P" +
                        "                          JOIN ISAMMDATA.PRTB001 AS C" +
                        "                              ON C.NATCLT = P.CNAT" +
                        "                                  AND C.IDCLT = P.NUMPERS" +
                        "                          JOIN ISAMMDATA.PR002 AS PR" +
                        "                              ON C.CODPROD = PR.CODPROD" +
                        "                     WHERE P.ID = ? " +
                        "                           AND C.SITUAT IN ('E', 'R')" +
                        "             ) AS T" +
                        "             ON I.CNAT = 'AG'" +
                        "                 AND I.NUMPERS = T.IDINT",
                cin
        );
    }





    public Optional<Map<String, Object>> findByNUMCNT(String numCNT) {
        List<Map<String, Object>> results = jdbcTemplate.queryForList(
                "SELECT I.NOM1 AS NOM_INT, T.*" +
                        "    FROM ISAMMDATA.PETB01 AS I" +
                        "         JOIN ("+
                        "                 SELECT *" +
                        "                     FROM ISAMMDATA.PETB01 AS P" +
                        "                          JOIN ISAMMDATA.PRTB001 AS C" +
                        "                              ON C.NATCLT = P.CNAT" +
                        "                                  AND C.IDCLT = P.NUMPERS" +
                        "                          JOIN ISAMMDATA.PR002 AS PR" +
                        "                              ON C.CODPROD = PR.CODPROD" +
                        "                     WHERE C.NUMCNT = ? " +
                        "                           AND C.SITUAT IN ('E', 'R')" +
                        "             ) AS T" +
                        "             ON I.CNAT = 'AG'" +
                        "                 AND I.NUMPERS = T.IDINT",
                numCNT
        );

        if (results.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(results.get(0));
        }
    }

 /*   public List <Map<String, Object>> findGRNTByNUMCNTVERSION1(String numCNT) {
        return jdbcTemplate.queryForList(
                "SELECT DISTINCT I.NOM1 AS NOM_INT," +
                        "                T.*," +
                        "                P3.CODGRNT," +
                        "                P13.CODGRNT," +
                        "                P13.LIBGRNT," +
                        "                S.NOMCOMMERCIAL" +
                        "    FROM ISAMMDATA.PETB01 AS I" +
                        "         JOIN (" +
                        "                 SELECT P.CNAT," +
                        "                        P.NUMPERS," +
                        "                        P.CLASSPERS," +
                        "                        P.TYPEID," +
                        "                        P.\"ID\"," +
                        "                        P.TITRE," +
                        "                        P.NOM1," +
                        "                        P.NOM2," +
                        "                        P.\"NATIONAL\"," +
                        "                        P.EMAIL," +
                        "                        P.DATEP," +
                        "                        P.TYPEPERS," +
                        "                        P.\"USER\"," +
                        "                        P.USER1," +
                        "                        C.NUMCNT," +
                        "                        C.NUMAVT," +
                        "                        C.NUMMAJ," +
                        "                        C.CODFAM," +
                        "                        C.CODPROD," +
                        "                        C.FORMCNT," +
                        "                        C.NATCLT," +
                        "                        C.IDCLT," +
                        "                        C.NATINT," +
                        "                        C.IDINT," +
                        "                        C.IDDELEGA," +
                        "                        C.PAYS," +
                        "                        C.MONNAIE," +
                        "                        C.ADRCORES," +
                        "                        C.DUREE," +
                        "                        C.TYPECNT," +
                        "                        C.FRACT," +
                        "                        C.DEBCNT," +
                        "                        C.FINCNT," +
                        "                        C.DEBEFFET," +
                        "                        C.FINEFFET," +
                        "                        C.ECHANNIV," +
                        "                        C.PRORATA," +
                        "                        C.SITUAT," +
                        "                        C.GESTIONAI," +
                        "                        C.DATECREAT," +
                        "                        PR.CODFAM," +
                        "                        PR.LIBPRDT," +
                        "                        PR.DATEFFET," +
                        "                        PR.TYPRISQ" +
                        "                     FROM ISAMMDATA.PETB01 AS P" +
                        "                          JOIN ISAMMDATA.PRTB001 AS C" +
                        "                              ON C.NATCLT = P.CNAT" +
                        "                                  AND C.IDCLT = P.NUMPERS" +
                        "                          JOIN ISAMMDATA.PR002 AS PR" +
                        "                              ON C.CODPROD = PR.CODPROD" +
                        "                     WHERE C.NUMCNT = ? " +
                        "                           AND C.SITUAT IN ('E', 'R')" +
                        "             ) AS T" +
                        "             ON I.CNAT = 'AG'" +
                        "                 AND I.NUMPERS = T.IDINT" +
                        "         JOIN ISAMMDATA.PRTB003 AS P3" +
                        "             ON T.NUMCNT = P3.NUMCNT" +
                        "         JOIN (" +
                        "                 SELECT DISTINCT CODGRNT," +
                        "                                 LIBGRNT," +
                        "                                 codprod" +
                        "                     FROM ISAMMDATA.PR013" +
                        "             ) AS P13" +
                        "             ON P3.CODGRNT = P13.CODGRNT" +
                        "                 AND T.CODPROD = P13.codprod" +
                        "         JOIN ISAMMDATA.SITENOMCOMMERCE AS S" +
                        "             ON T.CODPROD = S.codeprod" +
                        "                 AND P13.CODGRNT = S.CODGRNT" +
                        "    WHERE S.NOMCOMMERCIAL <> 'À éliminer'",
                numCNT
        );
    }
}*/


/*
    public List <Map<String, Object>> findGRNTByNUMCNTVERSION2(String numCNT) {
        return jdbcTemplate.queryForList(
                "SELECT DISTINCT I.NOM1 AS NOM_INT," +
                        "                T.*," +
                        "                P3.CODGRNT," +
                        "                P13.CODGRNT," +
                        "                P13.LIBGRNT," +
                        "                NC.BULL,"+
                        "                COALESCE(NC.NOMCOMMERCIAL, '' || P13.LIBGRNT) AS Result" +
                        "    FROM ISAMMDATA.PETB01 AS I" +
                        "         JOIN (" +
                        "                 SELECT P.CNAT," +
                        "                        P.NUMPERS," +
                        "                        P.CLASSPERS," +
                        "                        P.TYPEID," +
                        "                        P.\"ID\"," +
                        "                        P.TITRE," +
                        "                        P.NOM1," +
                        "                        P.NOM2," +
                        "                        P.\"NATIONAL\"," +
                        "                        P.EMAIL," +
                        "                        P.DATEP," +
                        "                        P.TYPEPERS," +
                        "                        P.\"USER\"," +
                        "                        P.USER1," +
                        "                        C.NUMCNT," +
                        "                        C.NUMAVT," +
                        "                        C.NUMMAJ," +
                        "                        C.CODFAM," +
                        "                        C.CODPROD," +
                        "                        C.FORMCNT," +
                        "                        C.NATCLT," +
                        "                        C.IDCLT," +
                        "                        C.NATINT," +
                        "                        C.IDINT," +
                        "                        C.IDDELEGA," +
                        "                        C.PAYS," +
                        "                        C.MONNAIE," +
                        "                        C.ADRCORES," +
                        "                        C.DUREE," +
                        "                        C.TYPECNT," +
                        "                        C.FRACT," +
                        "                        C.DEBCNT," +
                        "                        C.FINCNT," +
                        "                        C.DEBEFFET," +
                        "                        C.FINEFFET," +
                        "                        C.ECHANNIV," +
                        "                        C.PRORATA," +
                        "                        C.SITUAT," +
                        "                        C.GESTIONAI," +
                        "                        C.DATECREAT," +
                        "                        PR.CODFAM," +
                        "                        PR.LIBPRDT," +
                        "                        PR.DATEFFET," +
                        "                        PR.TYPRISQ" +
                        "                     FROM ISAMMDATA.PETB01 AS P" +
                        "                          JOIN ISAMMDATA.PRTB001 AS C" +
                        "                              ON C.NATCLT = P.CNAT" +
                        "                                  AND C.IDCLT = P.NUMPERS" +
                        "                          JOIN ISAMMDATA.PR002 AS PR" +
                        "                              ON C.CODPROD = PR.CODPROD" +
                        "                     WHERE C.NUMCNT = ? " +
                        "                           AND C.SITUAT IN ('E', 'R')" +
                        "             ) AS T" +
                        "             ON I.CNAT = 'AG'" +
                        "                 AND I.NUMPERS = T.IDINT" +
                        "         JOIN ISAMMDATA.PRTB003 AS P3" +
                        "             ON T.NUMCNT = P3.NUMCNT" +
                        "         JOIN (" +
                        "                 SELECT DISTINCT CODGRNT," +
                        "                                 LIBGRNT," +
                        "                                 codprod" +
                        "                     FROM ISAMMDATA.PR013" +
                        "             ) AS P13" +
                        "             ON P3.CODGRNT = P13.CODGRNT" +
                        "                 AND T.CODPROD = P13.codprod" +
                        "         LEFT JOIN ISAMMDATA.NOMCOMMERCE AS NC" +
                        "             ON T.CODPROD = NC.codprod" +
                        "                 AND P13.CODGRNT = NC.CODGRNT" +
                        "    WHERE COALESCE(NC.NOMCOMMERCIAL, ' ' || P13.LIBGRNT) <> 'À éliminer'",
                numCNT
        );
    }
*/


    public List <Map<String, Object>> findGRNTByNUMCNT(String numCNT) {
        return jdbcTemplate.queryForList(
                "SELECT DISTINCT I.NOM1 AS NOM_INT," +
                        "                T.*," +
                        "                P3.CODGRNT," +
                        "                P13.CODGRNT," +
                        "                P13.LIBGRNT," +
                        "                NC.BULL," +
                        "                COALESCE(NC.NOMCOMMERCIAL, '' || P13.LIBGRNT) AS Result," +
                        "       P3.NBUNITLM," +
                        "                P3.UNTLIMIT         " +
                        "    FROM ISAMMDATA.PETB01 AS I" +
                        "         JOIN (" +
                        "                 SELECT P.CNAT," +
                        "                        P.NUMPERS," +
                        "                        P.CLASSPERS," +
                        "                        P.TYPEID," +
                        "                        P.\"ID\"," +
                        "                        P.TITRE," +
                        "                        P.NOM1," +
                        "                        P.NOM2," +
                        "                        P.\"NATIONAL\"," +
                        "                        P.EMAIL," +
                        "                        P.DATEP," +
                        "                        P.TYPEPERS," +
                        "                        P.\"USER\"," +
                        "                        P.USER1," +
                        "                        C.NUMCNT," +
                        "                        C.NUMAVT," +
                        "                        C.NUMMAJ," +
                        "                        C.CODFAM," +
                        "                        C.CODPROD," +
                        "                        C.FORMCNT," +
                        "                        C.NATCLT," +
                        "                        C.IDCLT," +
                        "                        C.NATINT," +
                        "                        C.IDINT," +
                        "                        C.IDDELEGA," +
                        "                        C.PAYS," +
                        "                        C.MONNAIE," +
                        "                        C.ADRCORES," +
                        "                        C.DUREE," +
                        "                        C.TYPECNT," +
                        "                        C.FRACT," +
                        "                        C.DEBCNT," +
                        "                        C.FINCNT," +
                        "                        C.DEBEFFET," +
                        "                        C.FINEFFET," +
                        "                        C.ECHANNIV," +
                        "                        C.PRORATA," +
                        "                        C.SITUAT," +
                        "                        C.GESTIONAI," +
                        "                        C.DATECREAT," +
                        "                        PR.CODFAM," +
                        "                        PR.LIBPRDT," +
                        "                        PR.DATEFFET," +
                        "                        PR.TYPRISQ" +
                        "                     FROM ISAMMDATA.PETB01 AS P" +
                        "                          JOIN ISAMMDATA.PRTB001 AS C" +
                        "                              ON C.NATCLT = P.CNAT" +
                        "                                  AND C.IDCLT = P.NUMPERS" +
                        "                          JOIN ISAMMDATA.PR002 AS PR" +
                        "                              ON C.CODPROD = PR.CODPROD" +
                        "                     WHERE C.NUMCNT = ? " +
                        "                           AND C.SITUAT IN ('E', 'R')" +
                        "             ) AS T" +
                        "             ON I.CNAT = 'AG'" +
                        "                 AND I.NUMPERS = T.IDINT" +
                        "         JOIN ISAMMDATA.PRTB003 AS P3" +
                        "             ON T.NUMCNT = P3.NUMCNT" +
                        "         JOIN (" +
                        "                 SELECT DISTINCT CODGRNT," +
                        "                                 LIBGRNT," +
                        "                                 codprod" +
                        "                     FROM ISAMMDATA.PR013" +
                        "             ) AS P13" +
                        "             ON P3.CODGRNT = P13.CODGRNT" +
                        "                 AND T.CODPROD = P13.codprod" +
                        "         LEFT JOIN ISAMMDATA.NOMCOMMERCE AS NC" +
                        "             ON T.CODPROD = NC.codprod" +
                        "                 AND P13.CODGRNT = NC.CODGRNT" +
                        "    WHERE COALESCE(NC.NOMCOMMERCIAL, ' ' || P13.LIBGRNT) <> 'À éliminer'",
                numCNT
        );
    }

}
