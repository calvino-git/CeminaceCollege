/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.model;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author calviniloki
 */
@Entity
@Table(name = "bilan_specialite_culture_college")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BilanParSpecialiteCultureCollege.findAll", query = "SELECT b FROM BilanParSpecialiteCultureCollege b")})
@Getter
@Setter
public class BilanParSpecialiteCultureCollege implements Serializable, Comparable<BilanParSpecialiteCultureCollege> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator = "generateurBilanParSpecialiteCulture")
//    @TableGenerator(name = "generateurBilanParSpecialiteCulture", table = "sqlite_sequence",
//            pkColumnName = "name", valueColumnName = "seq",
//            pkColumnValue = "bilanParSpecialiteCulture",
//            initialValue = 1, allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "trimestre")
    private Integer trimestre;
    @JoinColumn(name = "ANNEE_ACADEMIQUE", referencedColumnName = "ID")
    @ManyToOne
    private AnneeAcademique anneeAcademique;
    @Size(max = 255)
    @Column(name = "observation")
    private String observation;
    @Column(name = "rang")
    private Integer rang;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ic")
    private Double instructionCivique;
    @Column(name = "eps")
    private Double eps;
    @Column(name = "con")
    private Double con;
    @Column(name = "art")
    private Double art;
    @Column(name = "total")
    private Double total;
    @Column(name = "moyenne")
    private Double moyenne;
    @JoinColumn(name = "eleve", referencedColumnName = "ID")
    @ManyToOne
    private Eleve eleve;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BilanParSpecialiteCultureCollege)) {
            return false;
        }
        BilanParSpecialiteCultureCollege other = (BilanParSpecialiteCultureCollege) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id=" + id + " " + anneeAcademique + " Trim " + trimestre + " " + rang;
    }

    @Override
    public int compareTo(BilanParSpecialiteCultureCollege b) {
        return moyenne.compareTo(b.moyenne);
    }

}
