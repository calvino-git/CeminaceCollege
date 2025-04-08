/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.model;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.ToString;

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
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AnneeAcademique implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    @ToString.Exclude
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANNEE")
    private String annee;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUT")
    @ToString.Exclude
    private boolean statut;
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "anneeAcademique",fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Eleve> eleveList;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "anneeAcademique",fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Bulletin> bulletinList;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "anneeAcademique",fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Classe> classeList;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "anneeAcademique",fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Discipline> disciplineList;
}
