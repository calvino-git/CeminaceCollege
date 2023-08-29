package com.github.adminfaces.starter.model;

import com.github.adminfaces.persistence.model.BaseEntity;
import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

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
public class Eleve extends BaseEntity implements Serializable, Comparable<Eleve> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator = "generateurEleve")
//    @TableGenerator(name = "generateurEleve", table = "sqlite_sequence",
//            pkColumnName = "name", valueColumnName = "seq",
//            pkColumnValue = "eleve",
//            initialValue = 1, allocationSize = 1)
    @Basic(optional = false)
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

    public Collection<Resultat> getResultatCollection() {
        return resultatCollection;
    }

    public Collection<Bulletin> getBulletinCollection() {
        return bulletinCollection;
    }

    public Eleve() {
    }

    public Eleve(Integer id) {
        this.id = id;
    }

    public Eleve(Integer id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Eleve(String nom) {
        this.nom = nom;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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
        if (!(object instanceof Eleve)) {
            return false;
        }
        Eleve other = (Eleve) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nom + " " + (prenom == null ? "" : prenom);
    }

    public boolean hasNom() {
        return nom != null && !"".equals(nom.trim());
    }

    public boolean hasSexe() {
        return sexe != null && !"".equals(sexe.trim());
    }

    public boolean hasAge() {
        return age != null && age != 0;
    }

    public boolean hasPrenom() {
        return prenom != null && !"".equals(prenom.trim());
    }

    public String getSexe() {
        return sexe == null ? "" : sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public int compareTo(Eleve e) {
        if (e == null) {
            System.out.println(e);
        }
        return nom.compareTo(e.nom);
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public AnneeAcademique getAnneeAcademique() {
        return anneeAcademique;
    }

    public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
        this.anneeAcademique = anneeAcademique;
    }

}
