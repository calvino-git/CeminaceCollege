/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.starter.model.Bulletin;
import com.github.adminfaces.starter.model.Classe;
import com.github.adminfaces.starter.model.Examen;
import com.github.adminfaces.starter.model.Note;
import com.github.adminfaces.starter.model.Registre;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;

/**
 * @author rmpestano
 */
@Stateless
public class RegistreService implements Serializable {

    @Inject
    ExamenService examenService;
    private double total;
    @Inject
    BulletinService bulletinService;
    
    public List<Registre> updateRegistreTrimestre(Classe classe, Integer annee, Integer trimestre) {
        List<Registre> registres = new ArrayList<>();
        
        classe.getEleveCollection().forEach(eleve -> {
            Registre registre = new Registre();
            registre.setEleve(eleve);
            registre.setAnnee(annee);
            registre.setTrimestre(trimestre);
            List<Bulletin> bulletins = bulletinService.bulletinParEleveEtClasse(eleve, trimestre, annee);
            total = 0.0;
            bulletins.forEach(b -> {
                if (b.getDiscipline().getMatiere().getCode().equals("ORTH")) {
                    
                    registre.setOrthographe(b.getMoyTrimestre());
                    registre.setOrthographeCoef(b.getDiscipline().getCoefficient());
                    total += registre.getOrthographe()*registre.getOrthographeCoef();
                }
                if (b.getDiscipline().getMatiere().getCode().equals("EXP.ECR.")) {
                    registre.setExpressionEcrite(b.getMoyTrimestre());
                    registre.setExpressionEcriteCoef(b.getDiscipline().getCoefficient());
                    total += registre.getExpressionEcrite()*registre.getExpressionEcriteCoef();
                }
                if (b.getDiscipline().getMatiere().getCode().equals("ANGLAIS")) {
                    
                            
                    registre.setAnglais(b.getMoyTrimestre());
                    registre.setAnglaisCoef(b.getDiscipline().getCoefficient());
                    total += registre.getAnglais()*registre.getAnglaisCoef();
                }
                if (b.getDiscipline().getMatiere().getCode().equals("ESP")) {
                    
                    registre.setEspagnol(b.getMoyTrimestre());
                    registre.setEspagnolCoef(b.getDiscipline().getCoefficient());
                    total += registre.getEspagnol()*registre.getEspagnolCoef();
                }
                if (b.getDiscipline().getMatiere().getCode().equals("IC")) {
                    
                            
                    registre.setInstructionCivique(b.getMoyTrimestre());
                    registre.setInstructionCiviqueCoef(b.getDiscipline().getCoefficient());
                    total += registre.getInstructionCivique()*registre.getInstructionCiviqueCoef();
                }
                if (b.getDiscipline().getMatiere().getCode().equals("HG")) {
                    
                            
                    registre.setHistoireGeo(b.getMoyTrimestre());
                    registre.setHistoireGeoCoef(b.getDiscipline().getCoefficient());
                    total += registre.getHistoireGeo()*registre.getHistoireGeoCoef();
                }
                if (b.getDiscipline().getMatiere().getCode().equals("MATHS")) {
                    
                            
                    registre.setMaths(b.getMoyTrimestre());
                    registre.setMathsCoef(b.getDiscipline().getCoefficient());
                    total += registre.getMaths()*registre.getMathsCoef();
                }
                if (b.getDiscipline().getMatiere().getCode().equals("PC")) {
                    
                            
                    registre.setPhyChimie(b.getMoyTrimestre());
                    registre.setPhyChimieCoef(b.getDiscipline().getCoefficient());
                    total += registre.getPhyChimie()*registre.getPhyChimieCoef();
                }
                if (b.getDiscipline().getMatiere().getCode().equals("SVT")) {
                    
                            
                    registre.setSvt(b.getMoyTrimestre());
                    registre.setSvtCoef(b.getDiscipline().getCoefficient());
                    total += registre.getSvt()*registre.getSvtCoef();
                }
                if (b.getDiscipline().getMatiere().getCode().equals("EPS")) {
                    
                            
                    registre.setEps(b.getMoyTrimestre());
                    registre.setEpsCoef(b.getDiscipline().getCoefficient());
                    total += registre.getEps()*registre.getEpsCoef();
                }
                if (b.getDiscipline().getMatiere().getCode().equals("ART")) {
                    
                            
                    registre.setArt(b.getMoyTrimestre());
                    registre.setArtCoef(b.getDiscipline().getCoefficient());
                    total += registre.getArt();
                }
                if (b.getDiscipline().getMatiere().getCode().equals("CON")) {
                    
                            
                    registre.setCon(b.getMoyTrimestre());
                    registre.setConCoef(b.getDiscipline().getCoefficient());
                    total += registre.getCon();
                }
            });
            registre.setTotal(total);
            registre.setMoyenne(total/26.0);
            registres.add(registre);
        });
        Comparator<Registre> comp = (o1, o2) -> {
            Double d = o2.getMoyenne() - o1.getMoyenne();
            if (Math.abs(d) > 1) {
                return d.intValue();
            } else {
                if (d > 0) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };

        int i = 0;
        registres.sort(comp);
        for (Registre r : registres) {
            r.setRang(++i);
        }
        registres.sort((r1,r2)->r1.getEleve().compareTo(r2.getEleve()));
        return registres;
    }

    public List<Registre> updateRegistre(Classe classe, Integer annee, Integer trimestre, String type) {
        List<Registre> registres = new ArrayList<>();
        List<Examen> examens = examenService.listeParClasseType(classe, trimestre, annee, type);
        classe.getEleveCollection().forEach(eleve -> {
            Registre registre = new Registre();
            registre.setEleve(eleve);
            registre.setAnnee(annee);
            registre.setTrimestre(trimestre);
            total = 0.0;
            examens.forEach(e -> {
                if (e.getDiscipline().getMatiere().getCode().equals("ORTH")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setOrthographe(n.get().getNote());
                    registre.setOrthographeCoef(e.getDiscipline().getCoefficient());
                    total += registre.getOrthographe()*registre.getOrthographeCoef();
                }
                if (e.getDiscipline().getMatiere().getCode().equals("ANGLAIS")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setAnglais(n.get().getNote());
                    registre.setAnglaisCoef(e.getDiscipline().getCoefficient());
                    total += registre.getAnglais()*registre.getAnglaisCoef();
                }
                if (e.getDiscipline().getMatiere().getCode().equals("ESP")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setEspagnol(n.get().getNote());
                    registre.setEspagnolCoef(e.getDiscipline().getCoefficient());
                    total += registre.getEspagnol()*registre.getEspagnolCoef();
                }
                if (e.getDiscipline().getMatiere().getCode().equals("EXP.ECR.")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setExpressionEcrite(n.get().getNote());
                    registre.setExpressionEcriteCoef(e.getDiscipline().getCoefficient());
                    total += registre.getExpressionEcrite()*registre.getExpressionEcriteCoef();
                }
                if (e.getDiscipline().getMatiere().getCode().equals("IC")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setInstructionCivique(n.get().getNote());
                    registre.setInstructionCiviqueCoef(e.getDiscipline().getCoefficient());
                    total += registre.getInstructionCivique()*registre.getInstructionCiviqueCoef();
                }
                if (e.getDiscipline().getMatiere().getCode().equals("HG")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setHistoireGeo(n.get().getNote());
                    registre.setHistoireGeoCoef(e.getDiscipline().getCoefficient());
                    total += registre.getHistoireGeo()*registre.getHistoireGeoCoef();
                }
                if (e.getDiscipline().getMatiere().getCode().equals("MATHS")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setMaths(n.get().getNote());
                    registre.setMathsCoef(e.getDiscipline().getCoefficient());
                    total += registre.getMaths()*registre.getMathsCoef();
                }
                if (e.getDiscipline().getMatiere().getCode().equals("PC")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setPhyChimie(n.get().getNote());
                    registre.setPhyChimieCoef(e.getDiscipline().getCoefficient());
                    total += registre.getPhyChimie()*registre.getPhyChimieCoef();
                }
                if (e.getDiscipline().getMatiere().getCode().equals("SVT")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setSvt(n.get().getNote());
                    registre.setSvtCoef(e.getDiscipline().getCoefficient());
                    total += registre.getSvt()*registre.getSvtCoef();
                }
                if (e.getDiscipline().getMatiere().getCode().equals("EPS")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setEps(n.get().getNote());
                    registre.setEpsCoef(e.getDiscipline().getCoefficient());
                    total += registre.getEps()*registre.getEpsCoef();
                }
                if (e.getDiscipline().getMatiere().getCode().equals("ART")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setArt(n.get().getNote());
                    registre.setArtCoef(e.getDiscipline().getCoefficient());
                    total += registre.getArt()*registre.getArtCoef();
                }
                if (e.getDiscipline().getMatiere().getCode().equals("CON")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setCon(n.get().getNote());
                    registre.setConCoef(e.getDiscipline().getCoefficient());
                    total += registre.getCon()*registre.getConCoef();
                }
            });
            registre.setTotal(total);
            registre.setMoyenne(total/24.0);
            registres.add(registre);
        });
        Comparator<Registre> comp = (o1, o2) -> {
            Double d = o2.getMoyenne() - o1.getMoyenne();
            if (Math.abs(d) > 1) {
                return d.intValue();
            } else {
                if (d > 0) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };

        int i = 0;
        registres.sort(comp);
        for (Registre r : registres) {
            r.setRang(++i);
        }
        registres.sort((r1,r2)->r1.getEleve().compareTo(r2.getEleve()));
        return registres;
    }
}
