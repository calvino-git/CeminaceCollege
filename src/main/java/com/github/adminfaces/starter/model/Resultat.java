/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
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

import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author calviniloki
 */
@Entity
@Table(name = "resultat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resultat.findAll", query = "SELECT r FROM Resultat r"),
    @NamedQuery(name = "Resultat.findById", query = "SELECT r FROM Resultat r WHERE r.id = :id"),
    @NamedQuery(name = "Resultat.findByTrimestre", query = "SELECT r FROM Resultat r WHERE r.trimestre = :trimestre"),
    @NamedQuery(name = "Resultat.findByMoyenne", query = "SELECT r FROM Resultat r WHERE r.moyenne = :moyenne"),
    @NamedQuery(name = "Resultat.findByRang", query = "SELECT r FROM Resultat r WHERE r.rang = :rang")})
@Getter
@Setter
@NoArgsConstructor
public class Resultat implements Comparable<Object>, Serializable {

    @Size(max = 255)
    @Column(name = "observation")
    private String observation;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator = "generateurResultat")
//    @TableGenerator(name = "generateurResultat", table = "sqlite_sequence",
//            pkColumnName = "name", valueColumnName = "seq",
//            pkColumnValue = "resultat",
//            initialValue = 1, allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "trimestre")
    private Integer trimestre;
    @JoinColumn(name = "annee_academique", referencedColumnName = "ID")
    @ManyToOne
    private AnneeAcademique anneeAcademique;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "moyenne")
    private Double moyenne;
    @Column(name = "rang")
    private Integer rang;
    @JoinColumn(name = "classe", referencedColumnName = "ID")
    @ManyToOne
    private Classe classe;

    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "eleve", referencedColumnName = "ID")
    @ManyToOne
    private Eleve eleve;

    @Override
    public String toString() {
        NumberFormat nf = DecimalFormat.getInstance(Locale.FRANCE);
        nf.setMaximumFractionDigits(2);
        return "Moyenne: " + nf.format(moyenne) + " Rang : " + rang + (rang == 1 ? "er" : "eme");
    }

    @Override
    public int compareTo(Object o) {
        return this.eleve.compareTo(((Resultat) o).eleve);
    }

}
