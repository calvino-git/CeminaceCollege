/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.model;

import com.github.adminfaces.persistence.model.BaseEntity;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
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
import javax.persistence.TableGenerator;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author calviniloki
 */
@Entity
@Table(name = "resultat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resultat.findAll", query = "SELECT r FROM Resultat r"),
    @NamedQuery(name = "Resultat.findById", query = "SELECT r FROM Resultat r WHERE r.id = :id"),
    @NamedQuery(name = "Resultat.findByTrimestre", query = "SELECT r FROM Resultat r WHERE r.trimestre = :trimestre"),
    @NamedQuery(name = "Resultat.findByMoyenne", query = "SELECT r FROM Resultat r WHERE r.moyenne = :moyenne"),
    @NamedQuery(name = "Resultat.findByRang", query = "SELECT r FROM Resultat r WHERE r.rang = :rang")})
public class Resultat extends BaseEntity implements Comparable<Object>,Serializable {

    @Size(max = 255)
    @Column(name = "observation")
    private String observation;

    @Id
    @GeneratedValue(generator = "generateurResultat")
    @TableGenerator(name = "generateurResultat", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue = "resultat",
            initialValue = 1, allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "trimestre")
    private Integer trimestre;
    @JoinColumn(name = "annee_academique",referencedColumnName = "ID")
    @ManyToOne
    private AnneeAcademique anneeAcademique;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
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

    public Resultat() {
    }

    public Resultat(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        if (!(object instanceof Resultat)) {
            return false;
        }
        Resultat other = (Resultat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        NumberFormat nf = DecimalFormat.getInstance(Locale.FRANCE);
        nf.setMaximumFractionDigits(2);
        return "Moyenne: " + nf.format(moyenne) + " Rang : " + rang + (rang==1?"er":"eme");
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
        return this.eleve.compareTo(((Resultat) o).eleve);
    }

}
