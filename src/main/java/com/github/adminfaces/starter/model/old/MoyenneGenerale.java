/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.model;

/**
 *
 * @author calviniloki
 */
public class MoyenneGenerale {
    
    private Integer annee;

    private Eleve eleve;

    private Double trim1;

    private Double trim2;

    private Double trim3;

    private Double total;

    private Double moyenne;

    private Integer rang;

    public Integer getRang() {
        return rang;
    }

    public void setRang(Integer rang) {
        this.rang = rang;
    }

    public Double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(Double moyenne) {
        this.moyenne = moyenne;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTrim3() {
        return trim3;
    }

    public void setTrim3(Double trim3) {
        this.trim3 = trim3;
    }

    public Double getTrim2() {
        return trim2;
    }

    public void setTrim2(Double trim2) {
        this.trim2 = trim2;
    }

    public Double getTrim1() {
        return trim1;
    }

    public void setTrim1(Double trim1) {
        this.trim1 = trim1;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

}
