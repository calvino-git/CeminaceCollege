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
public class Registre {

    private Integer annee;

    private Integer trimestre;
    
    private Eleve eleve;
    
    private Double francais;
    private Integer francaisCoef;
    
    private Double orthographe;
    private Integer orthographeCoef;
    
    private Double expressionEcrite;
    private Integer expressionEcriteCoef;

    private Double instructionCivique;
    private Integer instructionCiviqueCoef;

    private Double anglais;
    private Integer anglaisCoef;
    
    private Double espagnol;
    private Integer espagnolCoef;

    private Double philo;
    private Integer philoCoef;

    private Double histoireGeo;
    private Integer histoireGeoCoef;

    private Double maths;
    private Integer mathsCoef;

    private Double phyChimie;
    private Integer phyChimieCoef;

    private Double svt;
    private Integer svtCoef;

    private Double eps;
    private Integer epsCoef;
    
    private Double art;
    private Integer artCoef;
    
    private Double con;
    private Integer conCoef;
    
    private Double total;

    private Double moyenne;

    private Integer rang;

    public Registre() {
    }

    public Registre(Eleve eleve,Integer annee, Integer trimestre) {
        this.annee = annee;
        this.trimestre = trimestre;
        this.eleve = eleve;
    }

    /**
     * Get the value of rang
     *
     * @return the value of rang
     */
    public Integer getRang() {
        return rang;
    }

    /**
     * Set the value of rang
     *
     * @param rang new value of rang
     */
    public void setRang(Integer rang) {
        this.rang = rang;
    }

    /**
     * Get the value of moyenne
     *
     * @return the value of moyenne
     */
    public Double getMoyenne() {
        return moyenne;
    }

    /**
     * Set the value of moyenne
     *
     * @param moyenne new value of moyenne
     */
    public void setMoyenne(Double moyenne) {
        this.moyenne = moyenne;
    }

    /**
     * Get the value of total
     *
     * @return the value of total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * Set the value of total
     *
     * @param total new value of total
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * Get the value of eps
     *
     * @return the value of eps
     */
    public Double getEps() {
        return eps;
    }

    /**
     * Set the value of eps
     *
     * @param eps new value of eps
     */
    public void setEps(Double eps) {
        this.eps = eps;
    }

    /**
     * Get the value of svt
     *
     * @return the value of svt
     */
    public Double getSvt() {
        return svt;
    }

    /**
     * Set the value of svt
     *
     * @param svt new value of svt
     */
    public void setSvt(Double svt) {
        this.svt = svt;
    }

    /**
     * Get the value of phyChimie
     *
     * @return the value of phyChimie
     */
    public Double getPhyChimie() {
        return phyChimie;
    }

    /**
     * Set the value of phyChimie
     *
     * @param phyChimie new value of phyChimie
     */
    public void setPhyChimie(Double phyChimie) {
        this.phyChimie = phyChimie;
    }

    /**
     * Get the value of maths
     *
     * @return the value of maths
     */
    public Double getMaths() {
        return maths;
    }

    /**
     * Set the value of maths
     *
     * @param maths new value of maths
     */
    public void setMaths(Double maths) {
        this.maths = maths;
    }

    /**
     * Get the value of histoireGeo
     *
     * @return the value of histoireGeo
     */
    public Double getHistoireGeo() {
        return histoireGeo;
    }

    /**
     * Set the value of histoireGeo
     *
     * @param histoireGeo new value of histoireGeo
     */
    public void setHistoireGeo(Double histoireGeo) {
        this.histoireGeo = histoireGeo;
    }

    /**
     * Get the value of philo
     *
     * @return the value of philo
     */
    public Double getPhilo() {
        return philo;
    }

    /**
     * Set the value of philo
     *
     * @param philo new value of philo
     */
    public void setPhilo(Double philo) {
        this.philo = philo;
    }

    /**
     * Get the value of anglais
     *
     * @return the value of anglais
     */
    public Double getAnglais() {
        return anglais;
    }

    /**
     * Set the value of anglais
     *
     * @param anglais new value of anglais
     */
    public void setAnglais(Double anglais) {
        this.anglais = anglais;
    }

    /**
     * Get the value of francais
     *
     * @return the value of francais
     */
    public Double getFrancais() {
        return francais;
    }

    /**
     * Set the value of francais
     *
     * @param francais new value of francais
     */
    public void setFrancais(Double francais) {
        this.francais = francais;
    }

    /**
     * Get the value of annee
     *
     * @return the value of annee
     */
    public Integer getAnnee() {
        return annee;
    }

    /**
     * Set the value of annee
     *
     * @param annee new value of annee
     */
    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    /**
     * Get the value of trimestre
     *
     * @return the value of trimestre
     */
    public Integer getTrimestre() {
        return trimestre;
    }

    /**
     * Set the value of trimestre
     *
     * @param trimestre new value of trimestre
     */
    public void setTrimestre(Integer trimestre) {
        this.trimestre = trimestre;
    }

    /**
     * Get the value of eleve
     *
     * @return the value of eleve
     */
    public Eleve getEleve() {
        return eleve;
    }

    /**
     * Set the value of eleve
     *
     * @param eleve new value of eleve
     */
    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public Integer getFrancaisCoef() {
        return francaisCoef;
    }

    public void setFrancaisCoef(Integer francaisCoef) {
        this.francaisCoef = francaisCoef;
    }

    public Integer getAnglaisCoef() {
        return anglaisCoef;
    }

    public void setAnglaisCoef(Integer anglaisCoef) {
        this.anglaisCoef = anglaisCoef;
    }

    public Integer getPhiloCoef() {
        return philoCoef;
    }

    public void setPhiloCoef(Integer philoCoef) {
        this.philoCoef = philoCoef;
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

    public Double getArt() {
        return art;
    }

    public void setArt(Double art) {
        this.art = art;
    }

    public Integer getArtCoef() {
        return artCoef;
    }

    public void setArtCoef(Integer artCoef) {
        this.artCoef = artCoef;
    }

    public Double getCon() {
        return con;
    }

    public void setCon(Double con) {
        this.con = con;
    }

    public Integer getConCoef() {
        return conCoef;
    }

    public void setConCoef(Integer conCoef) {
        this.conCoef = conCoef;
    }

    public Double getEspagnol() {
        return espagnol;
    }

    public void setEspagnol(Double espagnol) {
        this.espagnol = espagnol;
    }

    public Integer getEspagnolCoef() {
        return espagnolCoef;
    }

    public void setEspagnolCoef(Integer espagnolCoef) {
        this.espagnolCoef = espagnolCoef;
    }

    public Double getOrthographe() {
        return orthographe;
    }

    public void setOrthographe(Double orthographe) {
        this.orthographe = orthographe;
    }

    public Integer getOrthographeCoef() {
        return orthographeCoef;
    }

    public void setOrthographeCoef(Integer orthographeCoef) {
        this.orthographeCoef = orthographeCoef;
    }

    public Double getExpressionEcrite() {
        return expressionEcrite;
    }

    public void setExpressionEcrite(Double expressionEcrite) {
        this.expressionEcrite = expressionEcrite;
    }

    public Integer getExpressionEcriteCoef() {
        return expressionEcriteCoef;
    }

    public void setExpressionEcriteCoef(Integer expressionEcriteCoef) {
        this.expressionEcriteCoef = expressionEcriteCoef;
    }

    public Double getInstructionCivique() {
        return instructionCivique;
    }

    public void setInstructionCivique(Double instructionCivique) {
        this.instructionCivique = instructionCivique;
    }

    public Integer getInstructionCiviqueCoef() {
        return instructionCiviqueCoef;
    }

    public void setInstructionCiviqueCoef(Integer instructionCiviqueCoef) {
        this.instructionCiviqueCoef = instructionCiviqueCoef;
    }

}
