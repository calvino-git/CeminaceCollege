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
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Bulletin implements Serializable,Comparable<Bulletin> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator = "generateurBulletin")
//    @TableGenerator(name = "generateurBulletin", table = "sqlite_sequence",
//            pkColumnName = "name", valueColumnName = "seq",
//            pkColumnValue = "bulletin",
//            initialValue = 1, allocationSize = 1)
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
    @Column(name = "coef")
    private Integer coef;
    @Override
    public int compareTo(Bulletin b) {
        return eleve.compareTo(b.eleve);
    }
}
