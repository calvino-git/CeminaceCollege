/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.model;

/**
 *
 * @author calviniloki
 */
public class RegistreCollege {

    private Double orthographe;

    private Double expressionEcrite;

    private Double espagnol;
    
    private Double anglais;

    private Double instructionCivique;

    private Double histoireGeo;

    private Double maths;

    private Double phyChimie;

    private Double svt;

    private Double eps;

    private Double art;

    private Double con;

    private Double total;

    private Double moyenne;

    private Integer rang;

    private Integer orthographeCoef;
    private Integer expressionEcriteCoef;
    private Integer espagnolCoef;
    private Integer anglaisCoef;
    private Integer instructionCiviqueCoef;
    private Integer histoireGeoCoef;
    private Integer mathsCoef;
    private Integer phyChimieCoef;
    private Integer svtCoef;
    private Integer epsCoef;
    private Integer artCoef;
    private Integer conCoef;

    private AnneeAcademique anneeAcademique;

    private Integer trimestre;
    private String type;
    private Eleve eleve;

    public RegistreCollege() {
    }

    public RegistreCollege(Eleve eleve, AnneeAcademique annee, Integer trimestre) {
        this.anneeAcademique = annee;
        this.trimestre = trimestre;
        this.eleve = eleve;
    }

    public Double getOrthographe() {
        return orthographe;
    }

    public void setOrthographe(Double orthographe) {
        this.orthographe = orthographe;
    }

    public Double getExpressionEcrite() {
        return expressionEcrite;
    }

    public void setExpressionEcrite(Double expressionEcrite) {
        this.expressionEcrite = expressionEcrite;
    }

    public Double getEspagnol() {
        return espagnol;
    }

    public void setEspagnol(Double espagnol) {
        this.espagnol = espagnol;
    }

    public Double getInstructionCivique() {
        return instructionCivique;
    }

    public void setInstructionCivique(Double instructionCivique) {
        this.instructionCivique = instructionCivique;
    }

    public Double getHistoireGeo() {
        return histoireGeo;
    }

    public void setHistoireGeo(Double histoireGeo) {
        this.histoireGeo = histoireGeo;
    }

    public Double getMaths() {
        return maths;
    }

    public void setMaths(Double maths) {
        this.maths = maths;
    }

    public Double getPhyChimie() {
        return phyChimie;
    }

    public void setPhyChimie(Double phyChimie) {
        this.phyChimie = phyChimie;
    }

    public Double getSvt() {
        return svt;
    }

    public void setSvt(Double svt) {
        this.svt = svt;
    }

    public Double getEps() {
        return eps;
    }

    public void setEps(Double eps) {
        this.eps = eps;
    }

    public Double getArt() {
        return art;
    }

    public void setArt(Double art) {
        this.art = art;
    }

    public Double getCon() {
        return con;
    }

    public void setCon(Double con) {
        this.con = con;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(Double moyenne) {
        this.moyenne = moyenne;
    }

    public Integer getRang() {
        return rang;
    }

    public void setRang(Integer rang) {
        this.rang = rang;
    }

    public Integer getOrthographeCoef() {
        return orthographeCoef;
    }

    public void setOrthographeCoef(Integer orthographeCoef) {
        this.orthographeCoef = orthographeCoef;
    }

    public Integer getExpressionEcriteCoef() {
        return expressionEcriteCoef;
    }

    public void setExpressionEcriteCoef(Integer expressionEcriteCoef) {
        this.expressionEcriteCoef = expressionEcriteCoef;
    }

    public Integer getEspagnolCoef() {
        return espagnolCoef;
    }

    public void setEspagnolCoef(Integer espagnolCoef) {
        this.espagnolCoef = espagnolCoef;
    }

    public Integer getInstructionCiviqueCoef() {
        return instructionCiviqueCoef;
    }

    public void setInstructionCiviqueCoef(Integer instructionCiviqueCoef) {
        this.instructionCiviqueCoef = instructionCiviqueCoef;
    }

    public Integer getHistoireGeoCoef() {
        return histoireGeoCoef;
    }

    public void setHistoireGeoCoef(Integer histoireGeoCoef) {
        this.histoireGeoCoef = histoireGeoCoef;
    }

    public Integer getMathsCoef() {
        return mathsCoef;
    }

    public void setMathsCoef(Integer mathsCoef) {
        this.mathsCoef = mathsCoef;
    }

    public Integer getPhyChimieCoef() {
        return phyChimieCoef;
    }

    public void setPhyChimieCoef(Integer phyChimieCoef) {
        this.phyChimieCoef = phyChimieCoef;
    }

    public Integer getSvtCoef() {
        return svtCoef;
    }

    public void setSvtCoef(Integer svtCoef) {
        this.svtCoef = svtCoef;
    }

    public Integer getEpsCoef() {
        return epsCoef;
    }

    public void setEpsCoef(Integer epsCoef) {
        this.epsCoef = epsCoef;
    }

    public Integer getArtCoef() {
        return artCoef;
    }

    public void setArtCoef(Integer artCoef) {
        this.artCoef = artCoef;
    }

    public Integer getConCoef() {
        return conCoef;
    }

    public void setConCoef(Integer conCoef) {
        this.conCoef = conCoef;
    }

    public AnneeAcademique getAnneeAcademique() {
        return anneeAcademique;
    }

    public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
        this.anneeAcademique = anneeAcademique;
    }

    public Integer getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(Integer trimestre) {
        this.trimestre = trimestre;
    }

    public String getType() {
        return type;
    }

    public void setType(String Type) {
        this.type = Type;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public Double getAnglais() {
        return anglais;
    }

    public void setAnglais(Double anglais) {
        this.anglais = anglais;
    }

    public Integer getAnglaisCoef() {
        return anglaisCoef;
    }

    public void setAnglaisCoef(Integer anglaisCoef) {
        this.anglaisCoef = anglaisCoef;
    }

    @Override
    public String toString() {
        return "RegistreCollege{" +
                "eleve=" + eleve +
                ", rang=" + rang +
                '}';
    }
}
