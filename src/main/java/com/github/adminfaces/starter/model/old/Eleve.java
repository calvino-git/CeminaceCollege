/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.model;

import com.github.adminfaces.persistence.model.BaseEntity;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author calviniloki
 */
@Entity
@Table(name = "eleve")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eleve.findAll", query = "SELECT e FROM Eleve e"),
    @NamedQuery(name = "Eleve.findById", query = "SELECT e FROM Eleve e WHERE e.id = :id"),
    @NamedQuery(name = "Eleve.findBySexe", query = "SELECT e FROM Eleve e WHERE e.sexe = :sexe"),
    @NamedQuery(name = "Eleve.findByNom", query = "SELECT e FROM Eleve e WHERE e.nom = :nom"),
    @NamedQuery(name = "Eleve.findByPrenom", query = "SELECT e FROM Eleve e WHERE e.prenom = :prenom")})
public class Eleve extends BaseEntity implements Serializable, Comparable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eleve")
    private Collection<Note> noteCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "generateurEleve")
    @TableGenerator(name = "generateurEleve", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue = "eleve",
            initialValue = 1, allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOM")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "SEXE")
    private String sexe;
    @Basic(optional = false)
    @Column(name = "AGE")
    private Integer age;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PRENOM")
    private String prenom;
    @JoinColumn(name = "CLASSE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Classe classe;
    @OneToMany(mappedBy = "eleve")
    private Collection<Bulletin> bulletinCollection;
    @OneToMany(mappedBy = "eleve")
    private Collection<Resultat> resultatCollection;

    public Collection<Resultat> getResultatCollection() {
        return resultatCollection;
    }

    public Collection<Bulletin> getBulletinCollection() {
        return bulletinCollection;
    }

    public Eleve() {
    }

    public Eleve(Integer id) {
        this.id = id;
    }

    public Eleve(Integer id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
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
        if (!(object instanceof Eleve)) {
            return false;
        }
        Eleve other = (Eleve) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nom + " " + prenom;
    }

    public boolean hasNom() {
        return nom != null && !"".equals(nom.trim());
    }

    public boolean hasSexe() {
        return sexe != null && !"".equals(sexe.trim());
    }

    public boolean hasAge() {
        return age != null && age != 0;
    }

    public boolean hasPrenom() {
        return prenom != null && !"".equals(prenom.trim());
    }

    public boolean hasClasse() {
        return classe != null && !"".equals(classe.getCode().trim());
    }

    @XmlTransient
    public Collection<Note> getNoteCollection() {
        return noteCollection;
    }

    public void setNoteCollection(Collection<Note> noteCollection) {
        this.noteCollection = noteCollection;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public int compareTo(Object o) {
        return toString().compareTo(((Eleve) o).toString());
    }

}
