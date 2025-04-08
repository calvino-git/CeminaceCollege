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
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Eleve implements Serializable, Comparable<Eleve> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator = "generateurEleve")
//    @TableGenerator(name = "generateurEleve", table = "sqlite_sequence",
//            pkColumnName = "name", valueColumnName = "seq",
//            pkColumnValue = "eleve",
//            initialValue = 1, allocationSize = 1)
//    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @NotNull
    @Column(name = "NOM")
    private String nom;
    @Column(name = "SEXE")
    private String sexe;
    @Column(name = "AGE")
    private Integer age;
    @Column(name = "PRENOM")
    private String prenom;
    @Column(name = "CODE")
    private String code;

    @JoinColumn(name = "CLASSE", referencedColumnName = "ID")
    @ManyToOne
    private Classe classe;
    @JoinColumn(name = "annee_academique", referencedColumnName = "ID")
    @ManyToOne
    private AnneeAcademique anneeAcademique;
    @OneToMany(mappedBy = "eleve")
    private Collection<Bulletin> bulletinCollection;
    @OneToMany(mappedBy = "eleve")
    private Collection<Resultat> resultatCollection;

    @Override
    public int compareTo(Eleve e) {
        if (e == null) {
            System.out.println(e);
        }
        return nom.compareTo(e.nom);
    }
}
