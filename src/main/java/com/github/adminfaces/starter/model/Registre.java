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

    private Double francais;

    private Double anglais;

    private Double espagnol;

    private Double philo;

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

    private Double francaisCoef;
    private Double anglaisCoef;
    private Double espagnolCoef;
    private Double philoCoef;
    private Double histoireGeoCoef;
    private Double mathsCoef;
    private Double phyChimieCoef;
    private Double svtCoef;
    private Double epsCoef;
    private Double artCoef;
    private Double conCoef;

    private AnneeAcademique anneeAcademique;

    private Integer trimestre;
    private String Type;
    private Eleve eleve;

    public Registre() {
    }

    public Registre(Eleve eleve, AnneeAcademique annee, Integer trimestre) {
        this.anneeAcademique = annee;
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
     * Get the value of anneeAcademique
     *
     * @return the value of anneeAcademique
     */
    public AnneeAcademique getAnneeAcademique() {
        return anneeAcademique;
    }

    /**
     * Set the value of anneeAcademique
     *
     * @param anneeAcademique new value of anneeAcademique
     */
    public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
        this.anneeAcademique = anneeAcademique;
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

    public Double getFrancaisCoef() {
        return francaisCoef;
    }

    public void setFrancaisCoef(Double francaisCoef) {
        this.francaisCoef = francaisCoef;
    }

    public Double getAnglaisCoef() {
        return anglaisCoef;
    }

    public void setAnglaisCoef(Double anglaisCoef) {
        this.anglaisCoef = anglaisCoef;
    }

    public Double getPhiloCoef() {
        return philoCoef;
    }

    public void setPhiloCoef(Double philoCoef) {
        this.philoCoef = philoCoef;
    }

    public Double getHistoireGeoCoef() {
        return histoireGeoCoef;
    }

    public void setHistoireGeoCoef(Double histoireGeoCoef) {
        this.histoireGeoCoef = histoireGeoCoef;
    }

    public Double getMathsCoef() {
        return mathsCoef;
    }

    public void setMathsCoef(Double mathsCoef) {
        this.mathsCoef = mathsCoef;
    }

    public Double getPhyChimieCoef() {
        return phyChimieCoef;
    }

    public void setPhyChimieCoef(Double phyChimieCoef) {
        this.phyChimieCoef = phyChimieCoef;
    }

    public Double getSvtCoef() {
        return svtCoef;
    }

    public void setSvtCoef(Double svtCoef) {
        this.svtCoef = svtCoef;
    }

    public Double getEpsCoef() {
        return epsCoef;
    }

    public void setEpsCoef(Double epsCoef) {
        this.epsCoef = epsCoef;
    }

    public Double getArt() {
        return art;
    }

    public void setArt(Double art) {
        this.art = art;
    }

    public Double getArtCoef() {
        return artCoef;
    }

    public void setArtCoef(Double artCoef) {
        this.artCoef = artCoef;
    }

    public Double getCon() {
        return con;
    }

    public void setCon(Double con) {
        this.con = con;
    }

    public Double getConCoef() {
        return conCoef;
    }

    public void setConCoef(Double conCoef) {
        this.conCoef = conCoef;
    }

    public Double getEspagnol() {
        return espagnol;
    }

    public void setEspagnol(Double espagnol) {
        this.espagnol = espagnol;
    }

    public Double getEspagnolCoef() {
        return espagnolCoef;
    }

    public void setEspagnolCoef(Double espagnolCoef) {
        this.espagnolCoef = espagnolCoef;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }
    

}
