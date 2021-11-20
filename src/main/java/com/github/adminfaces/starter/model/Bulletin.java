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
import javax.persistence.TableGenerator;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author calviniloki
 */
@Entity
@Table(name = "bulletin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bulletin.findAll", query = "SELECT b FROM Bulletin b"),
    @NamedQuery(name = "Bulletin.findById", query = "SELECT b FROM Bulletin b WHERE b.id = :id"),
    @NamedQuery(name = "Bulletin.findByCoef", query = "SELECT b FROM Bulletin b WHERE b.coef = :coef"),
    @NamedQuery(name = "Bulletin.findByTrimestre", query = "SELECT b FROM Bulletin b WHERE b.trimestre = :trimestre"),
    @NamedQuery(name = "Bulletin.findByObservation", query = "SELECT b FROM Bulletin b WHERE b.observation = :observation"),
    @NamedQuery(name = "Bulletin.findByMois", query = "SELECT b FROM Bulletin b WHERE b.mois = :mois"),
    @NamedQuery(name = "Bulletin.findByInterro1", query = "SELECT b FROM Bulletin b WHERE b.interro1 = :interro1"),
    @NamedQuery(name = "Bulletin.findByInterro2", query = "SELECT b FROM Bulletin b WHERE b.interro2 = :interro2"),
    @NamedQuery(name = "Bulletin.findByMoyInterro", query = "SELECT b FROM Bulletin b WHERE b.moyInterro = :moyInterro"),
    @NamedQuery(name = "Bulletin.findByEvaluation", query = "SELECT b FROM Bulletin b WHERE b.evaluation = :evaluation"),
    @NamedQuery(name = "Bulletin.findByMoyClasse", query = "SELECT b FROM Bulletin b WHERE b.moyClasse = :moyClasse"),
    @NamedQuery(name = "Bulletin.findByComposition", query = "SELECT b FROM Bulletin b WHERE b.composition = :composition"),
    @NamedQuery(name = "Bulletin.findByMoyTrimestre", query = "SELECT b FROM Bulletin b WHERE b.moyTrimestre = :moyTrimestre"),
    @NamedQuery(name = "Bulletin.findByMoyTrimestreCoef", query = "SELECT b FROM Bulletin b WHERE b.moyTrimestreCoef = :moyTrimestreCoef")})
public class Bulletin extends BaseEntity implements Serializable,Comparable<Bulletin> {

    @Column(name = "coef")
    private Integer coef;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "generateurBulletin")
    @TableGenerator(name = "generateurBulletin", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue = "bulletin",
            initialValue = 1, allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "trimestre")
    private Integer trimestre;
    @JoinColumn(name = "annee_academique",referencedColumnName = "ID")
    @ManyToOne
    private AnneeAcademique anneeAcademique;
    @Size(max = 255)
    @Column(name = "observation")
    private String observation;
    @Column(name = "mois")
    private Integer mois;
    @Column(name = "rang")
    private Integer rang;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "interro1")
    private Double interro1;
    @Column(name = "interro2")
    private Double interro2;
    @Column(name = "moy_interro")
    private Double moyInterro;
    @Column(name = "evaluation")
    private Double evaluation;
    @Column(name = "moy_classe")
    private Double moyClasse;
    @Column(name = "composition")
    private Double composition;
    @Column(name = "moy_trimestre")
    private Double moyTrimestre;
    @Column(name = "moy_trimestre_coef")
    private Double moyTrimestreCoef;
    @JoinColumn(name = "eleve", referencedColumnName = "ID")
    @ManyToOne
    private Eleve eleve;
    @JoinColumn(name = "discipline", referencedColumnName = "ID")
    @ManyToOne
    private Discipline discipline;

    public Bulletin() {
    }

    public Bulletin(Integer id) {
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

    public Integer getMois() {
        return mois;
    }

    public void setMois(Integer mois) {
        this.mois = mois;
    }

    public Double getInterro1() {
        return interro1;
    }

    public void setInterro1(Double interro1) {
        this.interro1 = interro1;
    }

    public Double getInterro2() {
        return interro2;
    }

    public void setInterro2(Double interro2) {
        this.interro2 = interro2;
    }

    public Double getMoyInterro() {
        return moyInterro;
    }

    public void setMoyInterro(Double moyInterro) {
        this.moyInterro = moyInterro;
    }

    public Double getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Double evaluation) {
        this.evaluation = evaluation;
    }

    public Double getMoyClasse() {
        return moyClasse;
    }

    public void setMoyClasse(Double moyClasse) {
        this.moyClasse = moyClasse;
    }

    public Double getComposition() {
        return composition;
    }

    public void setComposition(Double composition) {
        this.composition = composition;
    }

    public Double getMoyTrimestre() {
        return moyTrimestre;
    }

    public void setMoyTrimestre(Double moyTrimestre) {
        this.moyTrimestre = moyTrimestre;
    }

    public Double getMoyTrimestreCoef() {
        return moyTrimestreCoef;
    }

    public void setMoyTrimestreCoef(Double moyTrimestreCoef) {
        this.moyTrimestreCoef = moyTrimestreCoef;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
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
        if (!(object instanceof Bulletin)) {
            return false;
        }
        Bulletin other = (Bulletin) object;
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
        return "id=" + id + " " + anneeAcademique + " Trim " + trimestre + " " +  discipline + " " + eleve + " " + moyTrimestreCoef + " " + rang;
    }

    public Integer getCoef() {
        return coef;
    }

    public void setCoef(Integer coef) {
        this.coef = coef;
    }

    @Override
    public int compareTo(Bulletin b) {
        return eleve.compareTo(b.eleve);
    }
    
}
