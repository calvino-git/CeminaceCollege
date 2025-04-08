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
@Table(name = "niveau")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Niveau.findAll", query = "SELECT e FROM Niveau e"),
    @NamedQuery(name = "Niveau.findById", query = "SELECT e FROM Niveau e WHERE e.id = :id"),
    @NamedQuery(name = "Niveau.selectCode", query = "SELECT e.code FROM Niveau e ORDER BY e.code"),
    @NamedQuery(name = "Niveau.findByCycle", query = "SELECT e FROM Niveau e WHERE e.cycle = :cycle"),
    @NamedQuery(name = "Niveau.findByDescription", query = "SELECT e FROM Niveau e WHERE e.description = :description")})
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Niveau implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator = "generateurNiveau")
//    @TableGenerator(name = "generateurNiveau", table = "sqlite_sequence",
//            pkColumnName = "name", valueColumnName = "seq",
//            pkColumnValue = "niveau",
//            initialValue = 1, allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TITRE")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "CYCLE")
    private String cycle;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "niveau")
    private Collection<Classe> classeCollection;
}
