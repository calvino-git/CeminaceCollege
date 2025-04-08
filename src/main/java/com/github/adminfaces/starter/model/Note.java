/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.model;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.xml.bind.annotation.XmlRootElement;

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
@Getter
@Setter
@NoArgsConstructor
public class Note implements Serializable,Comparable<Note> {

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

    public Note(double note, String observation, int rang) {
        this.note = note;
        this.observation = observation;
        this.rang = rang;
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

    @Override
    public String toString() {
        return "[Note <b>"+ eleve + "</b> " + examen;
    }

    @Override
    public int compareTo(Note o) {
        return eleve.compareTo(o.eleve);
    }

}
