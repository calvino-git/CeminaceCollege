/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.model;

import com.github.adminfaces.persistence.model.BaseEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author calviniloki
 */
@Entity
@Table(name = "annee_academique")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnneeAcademique.findAll", query = "SELECT a FROM AnneeAcademique a"),
    @NamedQuery(name = "AnneeAcademique.findById", query = "SELECT a FROM AnneeAcademique a WHERE a.id = :id"),
    @NamedQuery(name = "AnneeAcademique.findByAnnee", query = "SELECT a FROM AnneeAcademique a WHERE a.annee = :annee"),
    @NamedQuery(name = "AnneeAcademique.findByStatut", query = "SELECT a FROM AnneeAcademique a WHERE a.statut = :statut")})
public class AnneeAcademique extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANNEE")
    private String annee;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUT")
    private boolean statut;
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "anneeAcademique",fetch = FetchType.EAGER)
    private List<Eleve> eleveList;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "anneeAcademique",fetch = FetchType.EAGER)
    private List<Bulletin> bulletinList;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "anneeAcademique",fetch = FetchType.EAGER)
    private List<Classe> classeList;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "anneeAcademique",fetch = FetchType.EAGER)
    private List<Discipline> disciplineList;

    public AnneeAcademique() {
    }

    public AnneeAcademique(Integer id) {
        this.id = id;
    }

    public AnneeAcademique(Integer id, String annee, boolean statut) {
        this.id = id;
        this.annee = annee;
        this.statut = statut;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public boolean getStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    @XmlTransient
    public List<Eleve> getEleveList() {
        return eleveList;
    }

    public void setEleveList(List<Eleve> eleveList) {
        this.eleveList = eleveList;
    }

    @XmlTransient
    public List<Bulletin> getBulletinList() {
        return bulletinList;
    }

    public void setBulletinList(List<Bulletin> bulletinList) {
        this.bulletinList = bulletinList;
    }

    public List<Classe> getClasseList() {
        return classeList;
    }

    public void setClasseList(List<Classe> classeList) {
        this.classeList = classeList;
    }

    public List<Discipline> getDisciplineList() {
        return disciplineList;
    }

    public void setDisciplineList(List<Discipline> disciplineList) {
        this.disciplineList = disciplineList;
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
        if (!(object instanceof AnneeAcademique)) {
            return false;
        }
        AnneeAcademique other = (AnneeAcademique) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.annee;
    }

    public boolean hasAnnee() {
        return annee != null && !"".equals(annee.trim());
    }
    
}
