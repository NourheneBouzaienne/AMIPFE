package com.example.apimiddleware.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContratClient {

    private String NUMCNT;
    private Long CODPROD;
    private String FORMCNT;
    private String NATCLT;
    private Long IDCLT;
    private Long IDDELEGA;
     private  String CIN ;

    public ContratClient(String NUMCNT, Long CODPROD, String FORMCNT, String NATCLT, Long IDCLT, Long IDDELEGA, String CIN) {
        this.NUMCNT = NUMCNT;
        this.CODPROD = CODPROD;
        this.FORMCNT = FORMCNT;
        this.NATCLT = NATCLT;
        this.IDCLT = IDCLT;
        this.IDDELEGA = IDDELEGA;
        this.CIN = CIN;
    }

    public ContratClient() {
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
    @JsonProperty("CIN")
    public String getCIN() {
        return CIN;
    }
    @JsonProperty("CIN")
    public void setCIN(String CIN) {
        this.CIN = CIN;
    }
}
