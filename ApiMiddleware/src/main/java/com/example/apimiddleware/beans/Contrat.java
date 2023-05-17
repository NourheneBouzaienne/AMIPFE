package com.example.apimiddleware.beans;




import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Contrat {
    private String NUMCNT;
    private Long CODPROD;
    private String FORMCNT;
    private String NATCLT;
    private Long IDCLT;
    private Long IDDELEGA;
    private  String LIBPRDT ;
    @JsonProperty("LIBPRDT")

    public String getLIBPRDT() {
        return LIBPRDT;
    }

    @JsonProperty("LIBPRDT")

    public void setLIBPRDT(String LIBPRDT) {
        this.LIBPRDT = LIBPRDT;
    }

    public Contrat() {
    }

    public Contrat(String NUMCNT, Long CODPROD, String FORMCNT, String NATCLT, Long IDCLT, Long IDDELEGA, String DEBCNT, String FINCNT, String SITUAT,String LIBPRDT) {
        this.NUMCNT = NUMCNT;
        this.CODPROD = CODPROD;
        this.FORMCNT = FORMCNT;
        this.NATCLT = NATCLT;
        this.IDCLT = IDCLT;
        this.IDDELEGA = IDDELEGA;
        this.DEBCNT = DEBCNT;
        this.FINCNT = FINCNT;
        this.SITUAT = SITUAT;
        this.LIBPRDT =LIBPRDT;
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
