/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.model;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
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
import jakarta.xml.bind.annotation.XmlTransient;

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
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Examen implements Serializable, Comparable {

    @OneToMany(mappedBy = "examen", fetch = FetchType.LAZY, orphanRemoval = true,cascade = CascadeType.ALL)
    private List<Note> noteCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator = "generateurExamen")
//    @TableGenerator(name = "generateurExamen", table = "sqlite_sequence",
//            pkColumnName = "name", valueColumnName = "seq",
//            pkColumnValue = "examen",
//            initialValue = 1, allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRIMESTRE")
    private int trimestre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE")
    private String date;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TYPE")
    private String type;
    @Column(name = "Code")
    private String code;
    
    @JoinColumn(name = "DISCIPLINE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Discipline discipline;
    @JoinColumn(name = "annee_academique",referencedColumnName = "ID")
    @ManyToOne
    private AnneeAcademique anneeAcademique;

    @Override
    public int compareTo(Object o) {
        Examen e = (Examen) o;
        int d = discipline.getMatiere().getIndex() - e.getDiscipline().getMatiere().getIndex();
        return d;
    }

}
