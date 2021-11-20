/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.model;

import com.github.adminfaces.persistence.model.BaseEntity;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author calviniloki
 */
@Entity
@Table(name = "examen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Examen.findAll", query = "SELECT e FROM Examen e"),
    @NamedQuery(name = "Examen.findById", query = "SELECT e FROM Examen e WHERE e.id = :id"),
    @NamedQuery(name = "Examen.findByTrimestre", query = "SELECT e FROM Examen e WHERE e.trimestre = :trimestre"),
    @NamedQuery(name = "Examen.findByDate", query = "SELECT e FROM Examen e WHERE e.date = :date"),
    @NamedQuery(name = "Examen.findByType", query = "SELECT e FROM Examen e WHERE e.type = :type")})
public class Examen extends BaseEntity implements Serializable, Comparable {

    @OneToMany(mappedBy = "examen")
    private List<Note> noteCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "generateurExamen")
    @TableGenerator(name = "generateurExamen", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue = "examen",
            initialValue = 1, allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRIMESTRE")
    private int trimestre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANNEE")
    private Integer annee;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE")
    private String date;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TYPE")
    private String type;
    @JoinColumn(name = "DISCIPLINE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Discipline discipline;

    public Examen() {
    }

    public Examen(Integer id) {
        this.id = id;
    }

    public Examen(Integer id, String titre, int trimestre, String mois, String date, String type) {
        this.id = id;
        this.trimestre = trimestre;
        this.date = date;
        this.type = type;
    }

    public int getNbreAbsents() {
        int absents = noteCollection.stream()
                .filter(n -> n.getPresence().equalsIgnoreCase("ABSENT"))
                .collect(Collectors.toList())
                .size();
        return absents;

    }

    public int getNbrePresents() {
        int absents = noteCollection.stream()
                .filter(n -> n.getPresence().equalsIgnoreCase("PRESENT"))
                .collect(Collectors.toList())
                .size();
        return absents;

    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(int trimestre) {
        this.trimestre = trimestre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        if (!(object instanceof Examen)) {
            return false;
        }
        Examen other = (Examen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getType() + " - " + getDiscipline().getMatiere() + " - " + trimestre
                + (trimestre == 1 ? "er" : "ème") + " Trim. " + " " + getAnnee();
    }

    public boolean hasDiscipline() {
        return discipline != null;
    }

    public boolean hasDate() {
        return date != null;
    }

    public boolean hasTrimestre() {
        return trimestre != 0;
    }

    public boolean hasType() {
        return type != null && !"".equals(type.trim());
    }

    @XmlTransient
    public List<Note> getNoteCollection() {
        return noteCollection;
    }

    public void setNoteCollection(List<Note> noteCollection) {
        this.noteCollection = noteCollection;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public boolean hasId() {
        return id != null && id != 0;
    }

    @Override
    public int compareTo(Object o) {
        Examen e = (Examen) o;
        int d = discipline.getMatiere().getIndex() - e.getDiscipline().getMatiere().getIndex();
        return d;
    }

}
