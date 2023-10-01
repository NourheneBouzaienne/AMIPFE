package com.example.apimiddleware.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sinistre {


    private String IMMAT;
    private Long CODE_ETAT;
    private String NUMSNT;
    private String CITESINI;
    private String REGSINI;
    private String GOUVSINI;
    private String LIBPRDT;
    private String NUMCNT;
    private String DTSURV;
    private String HEURESINI;
    private String N_SINISTRE;
    private String DT_ETAT;
    private Float TOTALREMBOURSE;
    private  String ETAT_QUIT;

    private String ETAPEACTUELLE;

    @JsonProperty("ETAPEACTUELLE")

    public String getETAPEACTUELLE() {
        return ETAPEACTUELLE;
    }
    @JsonProperty("ETAPEACTUELLE")

    public void setETAPEACTUELLE(String ETAPEACTUELLE) {
        this.ETAPEACTUELLE = ETAPEACTUELLE;
    }

    @JsonProperty("N_SINISTRE")
    public String getN_SINISTRE() {
        return N_SINISTRE;
    }
    @JsonProperty("N_SINISTRE")

    public void setN_SINISTRE(String n_SINISTRE) {
        N_SINISTRE = n_SINISTRE;
    }

    @JsonProperty("DT_ETAT")
    public String getDT_ETAT() {
        return DT_ETAT;
    }
    @JsonProperty("DT_ETAT")
    public void setDT_ETAT(String DT_ETAT) {
        this.DT_ETAT = DT_ETAT;
    }
    @JsonProperty("TOTALREMBOURSE")
    public Float getTOTALREMBOURSE() {
        return TOTALREMBOURSE;
    }

    @JsonProperty("TOTALREMBOURSE")
    public void setTOTALREMBOURSE(Float TOTALREMBOURSE) {
        this.TOTALREMBOURSE = TOTALREMBOURSE;
    }
    @JsonProperty("ETAT_QUIT")
    public String getETAT_QUIT() {
        return ETAT_QUIT;
    }

    @JsonProperty("ETAT_QUIT")
    public void setETAT_QUIT(String ETAT_QUIT) {
        this.ETAT_QUIT = ETAT_QUIT;
    }


    @JsonProperty("IMMAT")
    public String getIMMAT() {
        return IMMAT;
    }


    @JsonProperty("IMMAT")
    public void setIMMAT(String IMMAT) {
        this.IMMAT = IMMAT;
    }

    @JsonProperty("CODE_ETAT")
    public Long getCODE_ETAT() {
        return CODE_ETAT;
    }

    @JsonProperty("CODE_ETAT")
    public void setCODE_ETAT(Long CODE_ETAT) {
        this.CODE_ETAT = CODE_ETAT;
    }

    @JsonProperty("NUMSNT")
    public String getNUMSNT() {
        return NUMSNT;
    }

    @JsonProperty("NUMSNT")
    public void setNUMSNT(String NUMSNT) {
        this.NUMSNT = NUMSNT;
    }

    @JsonProperty("CITESINI")
    public String getCITESINI() {
        return CITESINI;
    }

    @JsonProperty("CITESINI")
    public void setCITESINI(String CITESINI) {
        this.CITESINI = CITESINI;
    }

    @JsonProperty("REGSINI")
    public String getREGSINI() {
        return REGSINI;
    }
    @JsonProperty("REGSINI")
    public void setREGSINI(String REGSINI) {
        this.REGSINI = REGSINI;
    }

    @JsonProperty("GOUVSINI")
    public String getGOUVSINI() {
        return GOUVSINI;
    }

    @JsonProperty("GOUVSINI")
    public void setGOUVSINI(String GOUVSINI) {
        this.GOUVSINI = GOUVSINI;
    }

    @JsonProperty("LIBPRDT")
    public String getLIBPRDT() {
        return LIBPRDT;
    }

    @JsonProperty("LIBPRDT")
    public void setLIBPRDT(String LIBPRDT) {
        this.LIBPRDT = LIBPRDT;
    }


    @JsonProperty("NUMCNT")
    public String getNUMCNT() {
        return NUMCNT;
    }

    @JsonProperty("NUMCNT")
    public void setNUMCNT(String NUMCNT) {
        this.NUMCNT = NUMCNT;
    }


    @JsonProperty("DTSURV")
    public String getDTSURV() {
        return DTSURV;
    }

    @JsonProperty("DTSURV")
    public void setDTSURV(String DTSURV) {
        this.DTSURV = DTSURV;
    }

    @JsonProperty("HEURESINI")
    public String getHEURESINI() {
        return HEURESINI;
    }

    @JsonProperty("HEURESINI")
    public void setHEURESINI(String HEURESINI) {
        this.HEURESINI = HEURESINI;
    }


    public Sinistre(String IMMAT, Long CODE_ETAT, String NUMSNT, String CITESINI, String REGSINI, String GOUVSINI, String LIBPRDT, String NUMCNT, String DTSURV, String HEURESINI, String n_SINISTRE, String DT_ETAT, Float TOTALREMBOURSE, String ETAT_QUIT, String ETAPEACTUELLE) {
        this.IMMAT = IMMAT;
        this.CODE_ETAT = CODE_ETAT;
        this.NUMSNT = NUMSNT;
        this.CITESINI = CITESINI;
        this.REGSINI = REGSINI;
        this.GOUVSINI = GOUVSINI;
        this.LIBPRDT = LIBPRDT;
        this.NUMCNT = NUMCNT;
        this.DTSURV = DTSURV;
        this.HEURESINI = HEURESINI;
        N_SINISTRE = n_SINISTRE;
        this.DT_ETAT = DT_ETAT;
        this.TOTALREMBOURSE = TOTALREMBOURSE;
        this.ETAT_QUIT = ETAT_QUIT;
        this.ETAPEACTUELLE = ETAPEACTUELLE;
    }
}
