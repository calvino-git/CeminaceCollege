/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author calviniloki
 */
@Entity
@Table(name = "bilan_annuel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BilanAnnuel.findAll", query = "SELECT b FROM BilanAnnuel b")})
public class BilanAnnuel implements Comparable<Object>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator = "generateurBilanAnnuel")
//    @TableGenerator(name = "generateurBilanAnnuel", table = "sqlite_sequence",
//            pkColumnName = "name", valueColumnName = "seq",
//            pkColumnValue = "bilanAnnuel",
//            initialValue = 1, allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "observation")
    private String observation;
    @JoinColumn(name = "ANNEE_ACADEMIQUE", referencedColumnName = "ID")
    @ManyToOne
    private AnneeAcademique anneeAcademique;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "trim1")
    private Double trim1;
    @Column(name = "trim2")
    private Double trim2;
    @Column(name = "trim3")
    private Double trim3;
    @Column(name = "moyenne")
    private Double moyenne;
    @Column(name = "rang")
    private Integer rang;
    @JoinColumn(name = "classe", referencedColumnName = "ID")
    @ManyToOne
    private Classe classe;

    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "eleve", referencedColumnName = "ID")
    @ManyToOne
    private Eleve eleve;

    public BilanAnnuel() {
    }

    public BilanAnnuel(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTrim1() {
        return trim1;
    }

    public void setTrim1(Double trim1) {
        this.trim1 = trim1;
    }

    public Double getTrim2() {
        return trim2;
    }

    public void setTrim2(Double trim2) {
        this.trim2 = trim2;
    }

    public Double getTrim3() {
        return trim3;
    }

    public void setTrim3(Double trim3) {
        this.trim3 = trim3;
    }

    public AnneeAcademique getAnneeAcademique() {
        return anneeAcademique;
    }

    public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
        this.anneeAcademique = anneeAcademique;
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

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BilanAnnuel)) {
            return false;
        }
        BilanAnnuel other = (BilanAnnuel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        NumberFormat nf = DecimalFormat.getInstance(Locale.FRANCE);
        nf.setMaximumFractionDigits(2);
        return "Moyenne: " + nf.format(moyenne) + " Rang : " + rang + (rang == 1 ? "er" : "eme");
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    @Override
    public int compareTo(Object o) {
        return this.eleve.compareTo(((BilanAnnuel) o).eleve);
    }

}
