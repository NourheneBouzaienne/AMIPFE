package com.example.microservicepfe.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Quittance {

    private String NUMQUITT;
    private String NUMCNT;
    private String DATECREA ;
    private String CODFAM ;
    private String CODPROD ;
    private String CODFORMU ;
    private String CODEDEVIS;
    private String STATQUIT;
    private String DEBEFFQUI;
    private String FINEFFQUI;
    private String DATEANNUL ;
    private String DATEPAIEM;
    private String MNTPRIMET;
    private String LIBPRDT;


    @JsonProperty("LIBPRDT")
    public String getLIBPRDT() {
        return LIBPRDT;
    }
    @JsonProperty("LIBPRDT")
    public void setLIBPRDT(String LIBPRDT) {
        this.LIBPRDT = LIBPRDT;
    }

    @JsonProperty("NUMQUITT")
    public String getNUMQUITT() {
        return NUMQUITT;
    }
    @JsonProperty("NUMQUITT")
    public void setNUMQUITT(String NUMQUITT) {
        this.NUMQUITT = NUMQUITT;
    }


    @JsonProperty("NUMCNT")
    public String getNUMCNT() {
        return NUMCNT;
    }


    @JsonProperty("NUMCNT")
    public void setNUMCNT(String NUMCNT) {
        this.NUMCNT = NUMCNT;
    }




    @JsonProperty("DATECREA")
    public String getDATECREA() {
        return DATECREA;
    }
    @JsonProperty("DATECREA")
    public void setDATECREA(String DATECREA) {
        this.DATECREA = DATECREA;
    }

    @JsonProperty("CODFAM")
    public String getCODFAM() {
        return CODFAM;
    }
    @JsonProperty("CODFAM")
    public void setCODFAM(String CODFAM) {
        this.CODFAM = CODFAM;
    }

    @JsonProperty("CODPROD")
    public String getCODPROD() {
        return CODPROD;
    }
    @JsonProperty("CODPROD")
    public void setCODPROD(String CODPROD) {
        this.CODPROD = CODPROD;
    }
    @JsonProperty("CODFORMU")
    public String getCODFORMU() {
        return CODFORMU;
    }
    @JsonProperty("CODFORMU")
    public void setCODFORMU(String CODFORMU) {
        this.CODFORMU = CODFORMU;
    }

    @JsonProperty("CODEDEVIS")
    public String getCODEDEVIS() {
        return CODEDEVIS;
    }
    @JsonProperty("CODEDEVIS")
    public void setCODEDEVIS(String CODEDEVIS) {
        this.CODEDEVIS = CODEDEVIS;
    }
    @JsonProperty("STATQUIT")

    public String getSTATQUIT() {
        return STATQUIT;
    }
    @JsonProperty("STATQUIT")

    public void setSTATQUIT(String STATQUIT) {
        this.STATQUIT = STATQUIT;
    }
    @JsonProperty("DEBEFFQUI")
    public String getDEBEFFQUI() {
        return DEBEFFQUI;
    }
    @JsonProperty("DEBEFFQUI")
    public void setDEBEFFQUI(String DEBEFFQUI) {
        this.DEBEFFQUI = DEBEFFQUI;
    }
    @JsonProperty("FINEFFQUI")
    public String getFINEFFQUI() {
        return FINEFFQUI;
    }
    @JsonProperty("FINEFFQUI")
    public void setFINEFFQUI(String FINEFFQUI) {
        this.FINEFFQUI = FINEFFQUI;
    }
    @JsonProperty("DATEANNUL")
    public String getDATEANNUL() {
        return DATEANNUL;
    }
    @JsonProperty("DATEANNUL")
    public void setDATEANNUL(String DATEANNUL) {
        this.DATEANNUL = DATEANNUL;
    }
    @JsonProperty("DATEPAIEM")
    public String getDATEPAIEM() {
        return DATEPAIEM;
    }

    @JsonProperty("DATEPAIEM")
    public void setDATEPAIEM(String DATEPAIEM) {
        this.DATEPAIEM = DATEPAIEM;
    }
    @JsonProperty("MNTPRNET")
    public String getMNTPRIMET() {
        return MNTPRIMET;
    }
    @JsonProperty("MNTPRNET")
    public void setMNTPRIMET(String MNTPRIMET) {
        this.MNTPRIMET = MNTPRIMET;
    }

    public Quittance(String NUMQUITT, String NUMCNT, String DATECREA, String CODFAM, String CODPROD, String CODFORMU, String CODEDEVIS, String STATQUIT, String DEBEFFQUI, String FINEFFQUI, String DATEANNUL, String DATEPAIEM, String MNTPRIMET, String LIBPRDT) {
        this.NUMQUITT = NUMQUITT;
        this.NUMCNT = NUMCNT;
        this.DATECREA = DATECREA;
        this.CODFAM = CODFAM;
        this.CODPROD = CODPROD;
        this.CODFORMU = CODFORMU;
        this.CODEDEVIS = CODEDEVIS;
        this.STATQUIT = STATQUIT;
        this.DEBEFFQUI = DEBEFFQUI;
        this.FINEFFQUI = FINEFFQUI;
        this.DATEANNUL = DATEANNUL;
        this.DATEPAIEM = DATEPAIEM;
        this.MNTPRIMET = MNTPRIMET;
        this.LIBPRDT = LIBPRDT;
    }


}