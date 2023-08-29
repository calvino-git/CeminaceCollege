/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.model;

import com.github.adminfaces.persistence.model.BaseEntity;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author calviniloki
 */
@Entity
@Table(name = "bilan_specialite_culture_college")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BilanParSpecialiteCultureCollege.findAll", query = "SELECT b FROM BilanParSpecialiteCultureCollege b")})
public class BilanParSpecialiteCultureCollege extends BaseEntity implements Serializable, Comparable<BilanParSpecialiteCultureCollege> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator = "generateurBilanParSpecialiteCulture")
//    @TableGenerator(name = "generateurBilanParSpecialiteCulture", table = "sqlite_sequence",
//            pkColumnName = "name", valueColumnName = "seq",
//            pkColumnValue = "bilanParSpecialiteCulture",
//            initialValue = 1, allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "trimestre")
    private Integer trimestre;
    @JoinColumn(name = "ANNEE_ACADEMIQUE", referencedColumnName = "ID")
    @ManyToOne
    private AnneeAcademique anneeAcademique;
    @Size(max = 255)
    @Column(name = "observation")
    private String observation;
    @Column(name = "rang")
    private Integer rang;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ic")
    private Double instructionCivique;
    @Column(name = "eps")
    private Double eps;
    @Column(name = "con")
    private Double con;
    @Column(name = "art")
    private Double art;
    @Column(name = "total")
    private Double total;
    @Column(name = "moyenne")
    private Double moyenne;
    @JoinColumn(name = "eleve", referencedColumnName = "ID")
    @ManyToOne
    private Eleve eleve;

    public BilanParSpecialiteCultureCollege() {
    }

    public BilanParSpecialiteCultureCollege(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(Integer trimestre) {
        this.trimestre = trimestre;
    }

    public AnneeAcademique getAnneeAcademique() {
        return anneeAcademique;
    }

    public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
        this.anneeAcademique = anneeAcademique;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Double getEps() {
        return eps;
    }

    public void setEps(Double eps) {
        this.eps = eps;
    }

    public Double getCon() {
        return con;
    }

    public void setCon(Double con) {
        this.con = con;
    }

    public Double getArt() {
        return art;
    }

    public void setArt(Double art) {
        this.art = art;
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

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public Double getInstructionCivique() {
        return instructionCivique;
    }

    public void setInstructionCivique(Double instructionCivique) {
        this.instructionCivique = instructionCivique;
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
        if (!(object instanceof BilanParSpecialiteCultureCollege)) {
            return false;
        }
        BilanParSpecialiteCultureCollege other = (BilanParSpecialiteCultureCollege) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public Integer getRang() {
        return rang;
    }

    public void setRang(Integer rang) {
        this.rang = rang;
    }

    @Override
    public String toString() {
        return "id=" + id + " " + anneeAcademique + " Trim " + trimestre + " " + rang;
    }

    @Override
    public int compareTo(BilanParSpecialiteCultureCollege b) {
        return moyenne.compareTo(b.moyenne);
    }

}
