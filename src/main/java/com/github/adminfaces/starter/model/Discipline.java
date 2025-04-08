/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.model;

import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "discipline")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Discipline.findAll", query = "SELECT d FROM Discipline d"),
    @NamedQuery(name = "Discipline.findById", query = "SELECT d FROM Discipline d WHERE d.id = :id"),
    @NamedQuery(name = "Discipline.findByCoefficient", query = "SELECT d FROM Discipline d WHERE d.coefficient = :coefficient")})
@Getter
@Setter
@NoArgsConstructor
@ToString()
public class Discipline implements Serializable, Comparable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//@GeneratedValue(generator = "generateurDiscipline")
//    @TableGenerator(name = "generateurDiscipline", table = "sqlite_sequence",
//            pkColumnName = "name", valueColumnName = "seq",
//            pkColumnValue = "discipline",
//            initialValue = 1, allocationSize = 1)    
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @NotNull
    @Column(name = "CODE")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COEFFICIENT")
    private Integer coefficient;
    @NotNull
    @Column(name = "ENSEIGNANT")
    private String enseignant;
    @JoinColumn(name = "CLASSE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Classe classe;
    @JoinColumn(name = "MATIERE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Matiere matiere;
    @JoinColumn(name = "annee_academique",referencedColumnName = "ID")
    @ManyToOne
    private AnneeAcademique anneeAcademique;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "discipline",fetch = FetchType.LAZY )
    private Collection<Examen> examenCollection;

    @ToString.Exclude
    @OneToMany(mappedBy = "discipline",fetch = FetchType.LAZY )
    private Collection<Bulletin> bulletinCollection;

    @Override
    public int compareTo(Object o) {
        return this.matiere.getIndex().compareTo(((Discipline) o).matiere.getIndex());
    }
}
