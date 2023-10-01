package com.example.microservicepfe.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Contrat<S, O> {
    private String NUMCNT;
    private Long CODPROD;
    private String FORMCNT;
    private String NATCLT;
    private Long IDCLT;
    private Long IDDELEGA;

    private String CIN;
    private String LIBPRDT;

    private String FRACT;
    private String NOM_INT;



    private String LIBGRNT;

    private String NOMCOMMERCIAL;

    private String RESULT;

    private String BULL;

    private Float SOMME_PRIMGRNT;
    private String DEBEFFET;
    private String FINEFFET;
    private Long NBUNITLM;
    private String UNTLIMIT;


    @JsonProperty("DEBEFFET")
    public String getDEBEFFET() {
        return DEBEFFET;
    }
    @JsonProperty("DEBEFFET")
    public void setDEBEFFET(String DEBEFFET) {
        this.DEBEFFET = DEBEFFET;
    }

    @JsonProperty("FINEFFET")
    public String getFINEFFET() {
        return FINEFFET;
    }
    @JsonProperty("FINEFFET")
    public void setFINEFFET(String FINEFFET) {
        this.FINEFFET = FINEFFET;
    }

    @JsonProperty("NBUNITLM")
    public Long getNBUNITLM() {
        return NBUNITLM;
    }


    @JsonProperty("NBUNITLM")
    public void setNBUNITLM(Long NBUNITLM) {
        this.NBUNITLM = NBUNITLM;
    }
    @JsonProperty("UNTLIMIT")
    public String getUNTLIMIT() {
        return UNTLIMIT;
    }
    @JsonProperty("UNTLIMIT")
    public void setUNTLIMIT(String UNTLIMIT) {
        this.UNTLIMIT = UNTLIMIT;
    }

    @JsonProperty("SOMME_PRIMGRNT")
    public Float getSOMME_PRIMGRNT() {
        return SOMME_PRIMGRNT;
    }


    @JsonProperty("SOMME_PRIMGRNT")
    public void setSOMME_PRIMGRNT(Float SOMME_PRIMGRNT) {
        this.SOMME_PRIMGRNT = SOMME_PRIMGRNT;
    }

    @JsonProperty("BULL")
    public String getBULL() {
        return BULL;
    }
    @JsonProperty("BULL")
    public void setBULL(String BULL) {
        this.BULL = BULL;
    }

    @JsonProperty("RESULT")
    public String getRESULT() {
        return RESULT;
    }
    @JsonProperty("RESULT")
    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    @JsonProperty("NOMCOMMERCIAL")
    public String getNOMCOMMERCIAL() {
        return NOMCOMMERCIAL;
    }

    @JsonProperty("NOMCOMMERCIAL")
    public void setNOMCOMMERCIAL(String NOMCOMMERCIAL) {
        this.NOMCOMMERCIAL = NOMCOMMERCIAL;
    }

    @JsonProperty("LIBGRNT")
    public String getLIBGRNT() {
        return LIBGRNT;
    }
    @JsonProperty("LIBGRNT")
    public void setLIBGRNT(String LIBGRNT) {
        this.LIBGRNT = LIBGRNT;
    }

    @JsonProperty("NOM_INT")

    public String getNOM_INT() {
        return NOM_INT;
    }

    @JsonProperty("NOM_INT")
    public void setNOM_INT(String NOM_INT) {
        this.NOM_INT = NOM_INT;
    }

    @JsonProperty("FRACT")

    public String getFRACT() {
        return FRACT;
    }


    @JsonProperty("FRACT")
    public void setFRACT(String FRACT) {
        this.FRACT = FRACT;
    }

    @JsonProperty("LIBPRDT")

    public String getLIBPRDT() {
        return LIBPRDT;
    }

    @JsonProperty("LIBPRDT")

    public void setLIBPRDT(String LIBPRDT) {
        this.LIBPRDT = LIBPRDT;
    }

    @JsonProperty("CIN")
    public String getCIN() {
        return CIN;
    }
    @JsonProperty("CIN")
    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public Contrat() {
    }

    public Contrat(String NUMCNT, Long CODPROD, String FORMCNT, String NATCLT, Long IDCLT, Long IDDELEGA, String CIN, String LIBPRDT, String FRACT, String NOM_INT, String LIBGRNT, String NOMCOMMERCIAL, String RESULT, String BULL, Float SOMME_PRIMGRNT, String DEBEFFET, String FINEFFET, Long NBUNITLM, String UNTLIMIT, String DEBCNT, String FINCNT, String SITUAT) {
        this.NUMCNT = NUMCNT;
        this.CODPROD = CODPROD;
        this.FORMCNT = FORMCNT;
        this.NATCLT = NATCLT;
        this.IDCLT = IDCLT;
        this.IDDELEGA = IDDELEGA;
        this.CIN = CIN;
        this.LIBPRDT = LIBPRDT;
        this.FRACT = FRACT;
        this.NOM_INT = NOM_INT;
        this.LIBGRNT = LIBGRNT;
        this.NOMCOMMERCIAL = NOMCOMMERCIAL;
        this.RESULT = RESULT;
        this.BULL = BULL;
        this.SOMME_PRIMGRNT = SOMME_PRIMGRNT;
        this.DEBEFFET = DEBEFFET;
        this.FINEFFET = FINEFFET;
        this.NBUNITLM = NBUNITLM;
        this.UNTLIMIT = UNTLIMIT;
        this.DEBCNT = DEBCNT;
        this.FINCNT = FINCNT;
        this.SITUAT = SITUAT;
    }

    @JsonProperty("NUMCNT")
    public String getNUMCNT() {
        return NUMCNT;
    }
    @JsonProperty("NUMCNT")
    public void setNUMCNT(String NUMCNT) {
        this.NUMCNT = NUMCNT;
    }


    @JsonProperty("CODPROD")
    public Long getCODPROD() {
        return CODPROD;
    }
    @JsonProperty("CODPROD")
    public void setCODPROD(Long CODPROD) {
        this.CODPROD = CODPROD;
    }

    @JsonProperty("FORMCNT")
    public String getFORMCNT() {
        return FORMCNT;
    }

    @JsonProperty("FORMCNT")
    public void setFORMCNT(String FORMCNT) {
        this.FORMCNT = FORMCNT;
    }


    @JsonProperty("NATCLT")
    public String getNATCLT() {
        return NATCLT;
    }

    @JsonProperty("NATCLT")
    public void setNATCLT(String NATCLT) {
        this.NATCLT = NATCLT;
    }

    @JsonProperty("IDCLT")
    public Long getIDCLT() {
        return IDCLT;
    }

    @JsonProperty("IDCLT")
    public void setIDCLT(Long IDCLT) {
        this.IDCLT = IDCLT;
    }

    @JsonProperty("IDDELEGA")
    public Long getIDDELEGA() {
        return IDDELEGA;
    }

    @JsonProperty("IDDELEGA")
    public void setIDDELEGA(Long IDDELEGA) {
        this.IDDELEGA = IDDELEGA;
    }


    @JsonProperty("DEBCNT")
    public String getDEBCNT() {
        return DEBCNT;
    }


    @JsonProperty("DEBCNT")
    public void setDEBCNT(String DEBCNT) {
        this.DEBCNT = DEBCNT;
    }


    @JsonProperty("FINCNT")
    public String getFINCNT() {
        return FINCNT;
    }


    @JsonProperty("FINCNT")
    public void setFINCNT(String FINCNT) {
        this.FINCNT = FINCNT;
    }

    @JsonProperty("SITUAT")
    public String getSITUAT() {
        return SITUAT;
    }

    @JsonProperty("SITUAT")

    public void setSITUAT(String SITUAT) {
        this.SITUAT = SITUAT;
    }

    private String DEBCNT;
    private String FINCNT;

    private String SITUAT;

}
