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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author calviniloki
 */
@Entity
@Table(name = "note")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Note.findAll", query = "SELECT n FROM Note n"),
    @NamedQuery(name = "Note.findById", query = "SELECT n FROM Note n WHERE n.id = :id"),
    @NamedQuery(name = "Note.findByNote", query = "SELECT n FROM Note n WHERE n.note = :note"),
    @NamedQuery(name = "Note.findByObservation", query = "SELECT n FROM Note n WHERE n.observation = :observation"),
    @NamedQuery(name = "Note.findByRang", query = "SELECT n FROM Note n WHERE n.rang = :rang")})
public class Note extends BaseEntity implements Serializable,Comparable<Note> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator = "generateurNote")
//    @TableGenerator(name = "generateurNote", table = "sqlite_sequence",
//            pkColumnName = "name", valueColumnName = "seq",
//            pkColumnValue = "note",
//            initialValue = 1, allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(min = 1, max = 50)
    @Column(name = "OBSERVATION")
    private String observation;
    @NotNull
    @Column(name = "NOTE")
    private Double note;
    @Column(name = "RANG")
    private Integer rang;
    @NotNull
    @Column(name = "PRESENCE")
    private String presence;

    
    @JoinColumn(name = "ELEVE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Eleve eleve;
    @JoinColumn(name = "EXAMEN", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Examen examen;
    @JoinColumn(name = "annee_academique",referencedColumnName = "ID")
    @ManyToOne
    private AnneeAcademique anneeAcademique;

    public Note() {
    }

    public Note(Integer id) {
        this.id = id;
    }

    public Note(double note, String observation, int rang) {
        this.note = note;
        this.observation = observation;
        this.rang = rang;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public boolean isMalade(){
        return presence.equals("MALADE");
    }
    public boolean isPresent(){
        return presence.equals("PRESENT");
    }
    public boolean isAbsent(){
        return presence.equals("ABSENT");
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
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
        if (!(object instanceof Note)) {
            return false;
        }
        Note other = (Note) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[Note <b>"+ eleve + "</b> " + examen;
    }

    public boolean hasEleve() {
        return eleve != null;
    }

    public boolean hasExamen() {
        return examen != null;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public Integer getRang() {
        return rang;
    }

    public void setRang(Integer rang) {
        this.rang = rang;
    }

    public String getPresence() {
        return presence;
    }

    public void setPresence(String presence) {
        this.presence = presence;
    }

    public AnneeAcademique getAnneeAcademique() {
        return anneeAcademique;
    }

    public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
        this.anneeAcademique = anneeAcademique;
    }

    @Override
    public int compareTo(Note o) {
        return eleve.compareTo(o.eleve);
    }

}
