/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.model;

import com.github.adminfaces.persistence.model.BaseEntity;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
public class Classe extends BaseEntity implements Serializable, Comparable<Classe> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "generateur_classe")
    @TableGenerator(name = "generateur_classe", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue = "classe",
            initialValue = 1, allocationSize = 1)
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
    @JoinColumn(name = "annee_academique", referencedColumnName = "ID")
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
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Niveau niveau;

    public Classe() {
    }

    public Classe(Integer id) {
        this.id = id;
    }

    public Classe(Integer id, String code, String libelle, String description, String cycle) {
        this.id = id;
        this.code = code;
        this.libelle = libelle;
        this.description = description;
        this.cycle = cycle;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public long getEffectifMale() {
        return eleveCollection.stream().filter(e -> e.getSexe() != null && e.getSexe().equalsIgnoreCase("M")).collect(Collectors.counting());
    }

    public long getEffectifFemelle() {
        return eleveCollection.stream().filter(e -> e.getSexe() != null && e.getSexe().equalsIgnoreCase("F")).collect(Collectors.counting());
    }

    public int getEffectifTotal() {
        return eleveCollection.size();
    }

    @XmlTransient
    public Collection<Eleve> getEleveCollection() {
        return eleveCollection;
    }

    public void setEleveCollection(Collection<Eleve> eleveCollection) {
        this.eleveCollection = eleveCollection;
    }

    @XmlTransient
    public Collection<Discipline> getDisciplineCollection() {
        return disciplineCollection.stream().sorted().collect(Collectors.toList());
    }

    public void setDisciplineCollection(Collection<Discipline> disciplineCollection) {
        this.disciplineCollection = disciplineCollection;
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
        if (!(object instanceof Classe)) {
            return false;
        }
        Classe other = (Classe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return libelle + "(" + code + ")";
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public boolean hasCode() {
        return code != null && !"".equals(code.trim());
    }

    public boolean hasLibelle() {
        return libelle != null && !"".equals(libelle.trim());
    }

    public boolean hasCycle() {
        return cycle != null && !"".equals(cycle.trim());
    }

    public boolean hasNiveau() {
        return niveau != null && !"".equals(niveau.getTitre().trim());
    }

    @XmlTransient
    public List<Resultat> getResultatList() {
        return resultatList;
    }

    public void setResultatList(List<Resultat> resultatList) {
        this.resultatList = resultatList;
    }

    public AnneeAcademique getAnneeAcademique() {
        return anneeAcademique;
    }

    public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
        this.anneeAcademique = anneeAcademique;
    }

    @Override
    public int compareTo(Classe o) {
        String classe1 = niveau.getId() + code;
        String classe2 = o.niveau.getId() + o.code;
        return classe1.compareTo(classe2);
    }

}
