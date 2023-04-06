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

    public Contrat() {
    }

    public Contrat(String NUMCNT, Long CODPROD, String FORMCNT, String NATCLT, Long IDCLT, Long IDDELEGA, Date DEBCNT, Date FINCNT, String SITUAT) {
        this.NUMCNT = NUMCNT;
        this.CODPROD = CODPROD;
        this.FORMCNT = FORMCNT;
        this.NATCLT = NATCLT;
        this.IDCLT = IDCLT;
        this.IDDELEGA = IDDELEGA;
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
    public Date getDEBCNT() {
        return DEBCNT;
    }


    @JsonProperty("DEBCNT")
    public void setDEBCNT(Date DEBCNT) {
        this.DEBCNT = DEBCNT;
    }


    @JsonProperty("FINCNT")
    public Date getFINCNT() {
        return FINCNT;
    }


    @JsonProperty("FINCNT")
    public void setFINCNT(Date FINCNT) {
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

    private Date DEBCNT;
    private Date FINCNT;

    private String SITUAT;

}
