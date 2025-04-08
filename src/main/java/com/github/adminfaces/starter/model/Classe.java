/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.model;

import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "classe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Classe.findAll", query = "SELECT c FROM Classe c"),
    @NamedQuery(name = "Classe.findById", query = "SELECT c FROM Classe c WHERE c.id = :id"),
    @NamedQuery(name = "Classe.findByCode", query = "SELECT c FROM Classe c WHERE c.code = :code"),
    @NamedQuery(name = "Classe.findByLibelle", query = "SELECT c FROM Classe c WHERE c.libelle = :libelle"),
    @NamedQuery(name = "Classe.findByDescription", query = "SELECT c FROM Classe c WHERE c.description = :description"),
    @NamedQuery(name = "Classe.findByCycle", query = "SELECT c FROM Classe c WHERE c.cycle = :cycle")})
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Classe implements Serializable, Comparable<Classe> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator = "generateur_classe")
//    @TableGenerator(name = "generateur_classe", table = "sqlite_sequence",
//            pkColumnName = "name", valueColumnName = "seq",
//            pkColumnValue = "classe",
//            initialValue = 1, allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CODE")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "LIBELLE")
    private String libelle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CYCLE")
    private String cycle;
    @JoinColumn(name = "ANNEE_ACADEMIQUE", referencedColumnName = "ID")
    @ManyToOne
    private AnneeAcademique anneeAcademique;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classe", fetch = FetchType.LAZY)
    private Collection<Eleve> eleveCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classe", fetch = FetchType.LAZY)
    private Collection<Discipline> disciplineCollection;
    @OneToMany(mappedBy = "classe", fetch = FetchType.LAZY)
    private List<Resultat> resultatList;

    //Le niveau qu'une classe est lié
    @JoinColumn(name = "NIVEAU", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Niveau niveau;

    @Override
    public int compareTo(Classe o) {
        String classe1 = niveau.getId() + code;
        String classe2 = o.niveau.getId() + o.code;
        return classe1.compareTo(classe2);
    }

}
