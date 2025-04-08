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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
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
@Table(name = "matiere")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Matiere.findAll", query = "SELECT m FROM Matiere m"),
    @NamedQuery(name = "Matiere.findById", query = "SELECT m FROM Matiere m WHERE m.id = :id"),
    @NamedQuery(name = "Matiere.findByCode", query = "SELECT m FROM Matiere m WHERE m.code = :code"),
    @NamedQuery(name = "Matiere.findByLibelle", query = "SELECT m FROM Matiere m WHERE m.libelle = :libelle"),
    @NamedQuery(name = "Matiere.findByDescription", query = "SELECT m FROM Matiere m WHERE m.description = :description"),
    @NamedQuery(name = "Matiere.findBySpecialite", query = "SELECT m FROM Matiere m WHERE m.specialite = :specialite")})
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Matiere implements Serializable,Comparable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator = "generateurMatiere")
//    @TableGenerator(name = "generateurMatiere", table = "sqlite_sequence",
//            pkColumnName = "name", valueColumnName = "seq",
//            pkColumnValue = "matiere",
//            initialValue = 1, allocationSize = 1)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CODE")
    private String code;
    @Column(name = "ORDRE")
    private Integer index;
    @Column(name = "LIBELLE")
    private String libelle;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "SPECIALITE")
    private String specialite;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matiere")
    private Collection<Discipline> disciplineCollection;
    @Override
    public int compareTo(Object o) {
        return this.index.compareTo(((Matiere ) o ).index);
    }

}
