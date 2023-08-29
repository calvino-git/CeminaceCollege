package com.github.adminfaces.starter.service;

import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.model.BilanParSpecialiteCultureCollege;
import com.github.adminfaces.starter.model.BilanParSpecialiteCultureLycee;
import com.github.adminfaces.starter.model.BilanParSpecialiteLettreCollege;
import com.github.adminfaces.starter.model.BilanParSpecialiteLettreLycee;
import com.github.adminfaces.starter.model.BilanParSpecialiteScience;
import com.github.adminfaces.starter.model.Bulletin;
import com.github.adminfaces.starter.model.Classe;
import com.github.adminfaces.starter.model.Eleve;
import com.github.adminfaces.starter.model.Examen;
import com.github.adminfaces.starter.model.Note;
import com.github.adminfaces.starter.model.RegistreCollege;
import com.github.adminfaces.starter.model.RegistreLycee;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

@Stateless
public class UpdateService implements Serializable {

    @Inject
    ExamenService examenService;
    private double total;
    
    @Inject
    BulletinService bulletinService;
    
    @Inject
    BssService bssService;
    @Inject
    BslCollegeService bslCollegeService;
    @Inject
    BscCollegeService bscCollegeService;
    @Inject
    BslLyceeService bslLyceeService;
    @Inject
    BscLyceeService bscLyceeService;
    
    private int coef;
    
    private double totalLettre;
    private double totalScience;
    private double totalCulture;
    
    private int p1;
    private int p2;
    private int p3;

    public List<RegistreCollege> updateRegistreTrimestreCollege(Classe classe, Integer trimestre) {
        List<RegistreCollege> registres = new ArrayList<>();

        classe.getEleveCollection().forEach(eleve -> {
            RegistreCollege registre = new RegistreCollege();
            registre.setEleve(eleve);
            registre.setAnneeAcademique(classe.getAnneeAcademique());
            registre.setTrimestre(trimestre);
            List<Bulletin> bulletins = bulletinService.bulletinsParEleveEtTrimestre(eleve, trimestre);
            total = 0.0;
            coef = 0;
            bulletins.stream().filter(b -> b.getMoyTrimestre() != null).forEach(b -> {
                if (b.getDiscipline().getMatiere().getCode().equals("ORTH")) {

                    registre.setOrthographe(b.getMoyTrimestre());
                    registre.setOrthographeCoef(b.getDiscipline().getCoefficient());
                    total += registre.getOrthographe() * registre.getOrthographeCoef();
                    coef += registre.getOrthographeCoef();
                }
                if (b.getDiscipline().getMatiere().getCode().equals("EXP.ECR.")) {
                    registre.setExpressionEcrite(b.getMoyTrimestre());
                    registre.setExpressionEcriteCoef(b.getDiscipline().getCoefficient());
                    total += registre.getExpressionEcrite() * registre.getExpressionEcriteCoef();
                    coef += registre.getExpressionEcriteCoef();
                }
                if (b.getDiscipline().getMatiere().getCode().equals("ANGLAIS")) {

                    registre.setAnglais(b.getMoyTrimestre());
                    registre.setAnglaisCoef(b.getDiscipline().getCoefficient());
                    total += registre.getAnglais() * registre.getAnglaisCoef();
                    coef += registre.getAnglaisCoef();
                }
                if (b.getDiscipline().getMatiere().getCode().equals("ESP")) {

                    registre.setEspagnol(b.getMoyTrimestre());
                    registre.setEspagnolCoef(b.getDiscipline().getCoefficient());
                    total += registre.getEspagnol() * registre.getEspagnolCoef();
                    coef += registre.getEspagnolCoef();
                }
                if (b.getDiscipline().getMatiere().getCode().equals("IC")) {
                    registre.setInstructionCivique(b.getMoyTrimestre());
                    registre.setInstructionCiviqueCoef(b.getDiscipline().getCoefficient());
                    total += registre.getInstructionCivique() * registre.getInstructionCiviqueCoef();
                    coef += registre.getInstructionCiviqueCoef();
                }
                if (b.getDiscipline().getMatiere().getCode().equals("HG")) {
                    registre.setHistoireGeo(b.getMoyTrimestre());
                    registre.setHistoireGeoCoef(b.getDiscipline().getCoefficient());
                    total += registre.getHistoireGeo() * registre.getHistoireGeoCoef();
                    coef += registre.getHistoireGeoCoef();
                }
                if (b.getDiscipline().getMatiere().getCode().equals("MATHS")) {

                    registre.setMaths(b.getMoyTrimestre());
                    registre.setMathsCoef(b.getDiscipline().getCoefficient());
                    total += registre.getMaths() * registre.getMathsCoef();
                    coef += registre.getMathsCoef();
                }
                if (b.getDiscipline().getMatiere().getCode().equals("PC")) {

                    registre.setPhyChimie(b.getMoyTrimestre());
                    registre.setPhyChimieCoef(b.getDiscipline().getCoefficient());
                    total += registre.getPhyChimie() * registre.getPhyChimieCoef();
                    coef += registre.getPhyChimieCoef();
                }
                if (b.getDiscipline().getMatiere().getCode().equals("SVT")) {

                    registre.setSvt(b.getMoyTrimestre());
                    registre.setSvtCoef(b.getDiscipline().getCoefficient());
                    total += registre.getSvt() * registre.getSvtCoef();
                    coef += registre.getSvtCoef();
                }
                if (b.getDiscipline().getMatiere().getCode().equals("EPS")) {

                    registre.setEps(b.getMoyTrimestre());
                    registre.setEpsCoef(b.getDiscipline().getCoefficient());
                    total += registre.getEps() * registre.getEpsCoef();
                    coef += registre.getEpsCoef();
                }
                if (b.getDiscipline().getMatiere().getCode().equals("ART")) {

                    registre.setArt(b.getMoyTrimestre());
                    registre.setArtCoef(b.getDiscipline().getCoefficient());
                    total += registre.getArt() * registre.getArtCoef();
                    coef += registre.getArtCoef();
                }
                if (b.getDiscipline().getMatiere().getCode().equals("CON")) {

                    registre.setCon(b.getMoyTrimestre());
                    registre.setConCoef(b.getDiscipline().getCoefficient());
                    total += registre.getCon() * registre.getConCoef();
                    coef += registre.getConCoef();
                }
            });
            registre.setTotal(total);
            registre.setMoyenne(total / coef);
            registres.add(registre);
        });
        Comparator<RegistreCollege> comp = (o1, o2) -> {
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
        for (RegistreCollege r : registres) {
            r.setRang(++i);
        }
        registres.sort((r1, r2) -> r1.getEleve().compareTo(r2.getEleve()));
        return registres;
    }

    public List<RegistreCollege> updateRegistreCollege(Classe classe, Integer trimestre, String type) {
        List<RegistreCollege> registres = new ArrayList<>();
        List<Examen> examens = examenService.listeParClasseType(classe, trimestre, classe.getAnneeAcademique(), type);
        classe.getEleveCollection().forEach(eleve -> {
            RegistreCollege registre = new RegistreCollege();
            registre.setEleve(eleve);
            registre.setAnneeAcademique(classe.getAnneeAcademique());
            registre.setTrimestre(trimestre);
//            registre.setType(type);
            total = 0.0;
            coef = 0;
            examens.forEach(e -> {
                if (e.getDiscipline().getMatiere().getCode().equals("ORTH")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setOrthographe(n.get().getNote());
                    registre.setOrthographeCoef(e.getDiscipline().getCoefficient());
                    total += registre.getOrthographe() * registre.getOrthographeCoef();
                    coef += registre.getOrthographeCoef();
                }
                if (e.getDiscipline().getMatiere().getCode().equals("ANGLAIS")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setAnglais(n.get().getNote());
                    registre.setAnglaisCoef(e.getDiscipline().getCoefficient());
                    total += registre.getAnglais() * registre.getAnglaisCoef();
                    coef += registre.getAnglaisCoef();
                }
                if (e.getDiscipline().getMatiere().getCode().equals("ESP")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setEspagnol(n.get().getNote());
                    registre.setEspagnolCoef(e.getDiscipline().getCoefficient());
                    total += registre.getEspagnol() * registre.getEspagnolCoef();
                    coef += registre.getEspagnolCoef();
                }
                if (e.getDiscipline().getMatiere().getCode().equals("EXP.ECR.")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setExpressionEcrite(n.get().getNote());
                    registre.setExpressionEcriteCoef(e.getDiscipline().getCoefficient());
                    total += registre.getExpressionEcrite() * registre.getExpressionEcriteCoef();
                    coef += registre.getExpressionEcriteCoef();
                }
                if (e.getDiscipline().getMatiere().getCode().equals("IC")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setInstructionCivique(n.get().getNote());
                    registre.setInstructionCiviqueCoef(e.getDiscipline().getCoefficient());
                    total += registre.getInstructionCivique() * registre.getInstructionCiviqueCoef();
                    coef += registre.getInstructionCiviqueCoef();
                }
                if (e.getDiscipline().getMatiere().getCode().equals("HG")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setHistoireGeo(n.get().getNote());
                    registre.setHistoireGeoCoef(e.getDiscipline().getCoefficient());
                    total += registre.getHistoireGeo() * registre.getHistoireGeoCoef();
                    coef += registre.getHistoireGeoCoef();
                }
                if (e.getDiscipline().getMatiere().getCode().equals("MATHS")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setMaths(n.get().getNote());
                    registre.setMathsCoef(e.getDiscipline().getCoefficient());
                    total += registre.getMaths() * registre.getMathsCoef();
                    coef += registre.getMathsCoef();
                }
                if (e.getDiscipline().getMatiere().getCode().equals("PC")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setPhyChimie(n.get().getNote());
                    registre.setPhyChimieCoef(e.getDiscipline().getCoefficient());
                    total += registre.getPhyChimie() * registre.getPhyChimieCoef();
                    coef += registre.getPhyChimieCoef();
                }
                if (e.getDiscipline().getMatiere().getCode().equals("SVT")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setSvt(n.get().getNote());
                    registre.setSvtCoef(e.getDiscipline().getCoefficient());
                    total += registre.getSvt() * registre.getSvtCoef();
                    coef += registre.getSvtCoef();
                }
                if (e.getDiscipline().getMatiere().getCode().equals("EPS")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setEps(n.get().getNote());
                    registre.setEpsCoef(e.getDiscipline().getCoefficient());
                    total += registre.getEps() * registre.getEpsCoef();
                    coef += registre.getEpsCoef();
                }

//                if (e.getDiscipline().getMatiere().getCode().equals("ART")) {
//                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
//                            .findFirst();
//                    registre.setArt(n.get().getNote());
//                    registre.setArtCoef(e.getDiscipline().getCoefficient());
//                    total += registre.getArt() * registre.getArtCoef();
//                    coef += registre.getArtCoef();
//                }
//                if (e.getDiscipline().getMatiere().getCode().equals("CON")) {
//                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
//                            .findFirst();
//                    registre.setCon(n.get().getNote());
//                    registre.setConCoef(e.getDiscipline().getCoefficient());
//                    total += registre.getCon() * registre.getConCoef();
//                    coef += registre.getConCoef();
//                }
            });
            registre.setTotal(total);
            registre.setMoyenne(total / coef);
            registres.add(registre);
        });
        Comparator<RegistreCollege> comp = (o1, o2) -> {
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
        for (RegistreCollege r : registres) {
            r.setRang(++i);
        }
        registres.sort((r1, r2) -> r1.getEleve().compareTo(r2.getEleve()));
        return registres;
    }

    public void updateBilanParSpecialiteCollege(Classe classe) {
        Logger.getGlobal().log(Level.INFO, "Debut preparation des bilans : {0} {1}", new Object[]{classe.getCode(), classe.getAnneeAcademique().getAnnee()});
        for (int trimestre = 1; trimestre < 4; trimestre++) {
            List<BilanParSpecialiteLettreCollege> bsls = new ArrayList<>();
            List<BilanParSpecialiteScience> bsss = new ArrayList<>();
            List<BilanParSpecialiteCultureCollege> bscs = new ArrayList<>();

            for (Eleve eleve : classe.getEleveCollection()) {
                BilanParSpecialiteLettreCollege bsl = new BilanParSpecialiteLettreCollege();
                BilanParSpecialiteScience bss = new BilanParSpecialiteScience();
                BilanParSpecialiteCultureCollege bsc = new BilanParSpecialiteCultureCollege();

                bsl.setEleve(eleve);
                bsl.setAnneeAcademique(classe.getAnneeAcademique());
                bsl.setTrimestre(trimestre);

                bss.setEleve(eleve);
                bss.setAnneeAcademique(classe.getAnneeAcademique());
                bss.setTrimestre(trimestre);

                bsc.setEleve(eleve);
                bsc.setAnneeAcademique(classe.getAnneeAcademique());
                bsc.setTrimestre(trimestre);
                List<Bulletin> bulletins = bulletinService.bulletinsParEleveEtTrimestre(eleve, trimestre);
                totalLettre = 0.0;
                totalScience = 0.0;
                totalCulture = 0.0;
                p1 = 0;
                p2 = 0;
                p3 = 0;
                if (bulletins != null && !bulletins.isEmpty()) {
                    for (Bulletin b : bulletins) {
                        if (b.getMoyTrimestre() == null) {
                            b.setMoyTrimestre(0.0);
                        }

                        try {
                            if (b.getDiscipline().getMatiere().getCode().equals("ORTH")) {
                                bsl.setOrthographe(b.getMoyTrimestre());
                                totalLettre += bsl.getOrthographe();
                                p1++;
                            }
                            if (b.getDiscipline().getMatiere().getCode().equals("EXP.ECR.")) {
                                bsl.setExpressionEcrite(b.getMoyTrimestre());
                                totalLettre += bsl.getExpressionEcrite();
                                p1++;
                            }
                            if (b.getDiscipline().getMatiere().getCode().equals("ANGLAIS")) {
                                bsl.setAnglais(b.getMoyTrimestre());
                                totalLettre += bsl.getAnglais();
                                p1++;
                            }
                            if (b.getDiscipline().getMatiere().getCode().equals("HG")) {
                                bsl.setHistGeo(b.getMoyTrimestre());
                                totalLettre += bsl.getHistGeo();
                                p1++;
                            }

                            bsl.setTotal(totalLettre);
                            bsl.setMoyenne(totalLettre / p1);

                            if (b.getDiscipline().getMatiere().getCode().equals("MATHS")) {
                                bss.setMaths(b.getMoyTrimestre());
                                totalScience += bss.getMaths();
                                p2++;
                            }
                            if (b.getDiscipline().getMatiere().getCode().equals("PC")) {
                                bss.setPhysiqueChimie(b.getMoyTrimestre());
                                totalScience += bss.getPhysiqueChimie();
                                p2++;
                            }
                            if (b.getDiscipline().getMatiere().getCode().equals("SVT")) {
                                bss.setSvt(b.getMoyTrimestre());
                                totalScience += bss.getSvt();
                                p2++;
                            }

                            bss.setTotal(totalScience);
                            bss.setMoyenne(totalScience / p2);

                            if (b.getDiscipline().getMatiere().getCode().equals("IC")) {
                                bsc.setInstructionCivique(b.getMoyTrimestre());
                                totalCulture += bsc.getInstructionCivique();
                                p3++;
                            }
                            if (b.getDiscipline().getMatiere().getCode().equals("EPS")) {
                                bsc.setEps(b.getMoyTrimestre());
                                totalCulture += bsc.getEps();
                                p3++;
                            }
                            if (b.getDiscipline().getMatiere().getCode().equals("ART")) {
                                bsc.setArt(b.getMoyTrimestre());
                                totalCulture += bsc.getArt();
                                p3++;
                            }
                            if (b.getDiscipline().getMatiere().getCode().equals("CON")) {
                                bsc.setCon(b.getMoyTrimestre());
                                totalCulture += bsc.getCon();
                                p3++;
                            }

                            bsc.setTotal(totalCulture);
                            bsc.setMoyenne(totalCulture / p3);
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    }

                    bsls.add(bsl);
                    bsss.add(bss);
                    bscs.add(bsc);

                }
            };

            Comparator<BilanParSpecialiteLettreCollege> comp1 = (o1, o2) -> {
                try {
                    return o2.compareTo(o1);
                } catch (NullPointerException ex) {
                    return 0;
                }
            };
            Comparator<BilanParSpecialiteScience> comp2 = (o1, o2) -> {
                try {
                    return o2.compareTo(o1);
                } catch (NullPointerException ex) {
                    return 0;
                }
            };
            Comparator<BilanParSpecialiteCultureCollege> comp3 = (o1, o2) -> {
                try {
                    return o2.compareTo(o1);
                } catch (NullPointerException ex) {
                    return 0;
                }
            };

//        if (registres.isEmpty()) {
//            return registres;
//        }
            int i = 0;
            int j = 0;
            int k = 0;

            bsls.sort(comp1);
            bsss.sort(comp2);
            bscs.sort(comp3);

            for (BilanParSpecialiteLettreCollege r : bsls) {
                r.setRang(++i);
            }
            for (BilanParSpecialiteScience r : bsss) {
                r.setRang(++j);
            }
            for (BilanParSpecialiteCultureCollege r : bscs) {
                r.setRang(++k);
            }

            bsls.stream().map((t) -> {
                BilanParSpecialiteLettreCollege bsl = bslCollegeService.exists(t.getEleve(), t.getTrimestre(), t.getAnneeAcademique());
                if (bsl != null) {
                    t.setId(bsl.getId());
                }
                return t;
            }).forEachOrdered((t) -> {
                bslCollegeService.saveOrUpdate(t);
            });
            bsss.stream().map((t) -> {
                BilanParSpecialiteScience bss = bssService.exists(t.getEleve(), t.getTrimestre(), t.getAnneeAcademique());
                if (bss != null) {
                    t.setId(bss.getId());
                }
                return t;
            }).forEachOrdered((t) -> {
                bssService.saveOrUpdate(t);
            });
            bscs.stream().map((t) -> {
                BilanParSpecialiteCultureCollege bsc = bscCollegeService.exists(t.getEleve(), t.getTrimestre(), t.getAnneeAcademique());
                if (bsc != null) {
                    t.setId(bsc.getId());
                }
                return t;
            }).forEachOrdered((t) -> {
                bscCollegeService.saveOrUpdate(t);
            });

//        HashMap<String, List> map = new HashMap<>();
//        map.put("Lettre", bsls);
//        map.put("Science", bsss);
//        map.put("Culture", bscs);
//        registres.sort((r1, r2) -> r1.getEleve().compareTo(r2.getEleve()));
        }
        Logger.getGlobal().log(Level.WARNING, "Fin de l'operation des bilans : {0} {1}", new Object[]{classe.getCode(), classe.getAnneeAcademique().getAnnee()});
//        return map;
    }
    
    
    
    public List<RegistreLycee> updateRegistreLycee(Classe classe, AnneeAcademique annee, Integer trimestre, String type) {
        List<RegistreLycee> registres = new ArrayList<>();

        classe.getEleveCollection().forEach(eleve -> {
            RegistreLycee registre = new RegistreLycee();
            registre.setEleve(eleve);
            registre.setAnneeAcademique(annee);
            registre.setTrimestre(trimestre);
            registre.setType(type);
            List<Bulletin> bulletins = bulletinService.bulletinParEleveEtClasse(eleve, trimestre, annee);
            total = 0.0;
            coef = 0;
            if (bulletins != null && !bulletins.isEmpty()) {
                bulletins.forEach(b -> {
                    if (b.getDiscipline().getMatiere().getCode().equals("FRAN")) {
                        if (b.getEvaluation() != null && type.equals("EVAL")) {
                            registre.setFrancais(b.getEvaluation());
                            registre.setFrancaisCoef(b.getEvaluation() * b.getDiscipline().getCoefficient());
                        }
                        if (b.getComposition() != null && type.equals("COMPO")) {
                            registre.setFrancais(b.getComposition());
                            registre.setFrancaisCoef(b.getComposition() * b.getDiscipline().getCoefficient());
                        };
                        if (b.getMoyTrimestre() != null && type.equals("TRIM")) {
                            registre.setFrancais(b.getMoyTrimestre());
                            registre.setFrancaisCoef(b.getMoyTrimestreCoef());
                        };
                        if (registre.getFrancaisCoef() != null) {
                            total += registre.getFrancaisCoef();
                        }

                    }
                    if (b.getDiscipline().getMatiere().getCode().equals("ANGLAIS")) {

                        if (b.getEvaluation() != null && type.equals("EVAL")) {
                            registre.setAnglais(b.getEvaluation());
                            registre.setAnglaisCoef(b.getEvaluation() * b.getDiscipline().getCoefficient());
                        }
                        if (b.getComposition() != null && type.equals("COMPO")) {
                            registre.setAnglais(b.getComposition());
                            registre.setAnglaisCoef(b.getComposition() * b.getDiscipline().getCoefficient());
                        };
                        if (b.getMoyTrimestre() != null && type.equals("TRIM")) {
                            registre.setAnglais(b.getMoyTrimestre());
                            registre.setAnglaisCoef(b.getMoyTrimestreCoef());
                        };
                        if (registre.getAnglaisCoef() != null) {
                            total += registre.getAnglaisCoef();
                        }
                    }
                    if (b.getDiscipline().getMatiere().getCode().equals("ESP")) {
                        if (b.getEvaluation() != null && type.equals("EVAL")) {
                            registre.setEspagnol(b.getEvaluation());
                            registre.setEspagnolCoef(b.getEvaluation() * b.getDiscipline().getCoefficient());
                        }
                        if (b.getComposition() != null && type.equals("COMPO")) {
                            registre.setEspagnol(b.getComposition());
                            registre.setEspagnolCoef(b.getComposition() * b.getDiscipline().getCoefficient());
                        };
                        if (b.getMoyTrimestre() != null && type.equals("TRIM")) {
                            registre.setEspagnol(b.getMoyTrimestre());
                            registre.setEspagnolCoef(b.getMoyTrimestreCoef());
                        };
                        if (registre.getEspagnolCoef() != null) {
                            total += registre.getEspagnolCoef();
                        }
                    }
                    if (b.getDiscipline().getMatiere().getCode().equals("PHILO")) {
                        if (b.getEvaluation() != null && type.equals("EVAL")) {
                            registre.setPhilo(b.getEvaluation());
                            registre.setPhiloCoef(b.getEvaluation() * b.getDiscipline().getCoefficient());
                        }
                        if (b.getComposition() != null && type.equals("COMPO")) {
                            registre.setPhilo(b.getComposition());
                            registre.setPhiloCoef(b.getComposition() * b.getDiscipline().getCoefficient());
                        };
                        if (b.getMoyTrimestre() != null && type.equals("TRIM")) {
                            registre.setPhilo(b.getMoyTrimestre());
                            registre.setPhiloCoef(b.getMoyTrimestreCoef());
                        };
                        if (registre.getPhiloCoef() != null) {
                            total += registre.getPhiloCoef();
                        }
                    }
                    if (b.getDiscipline().getMatiere().getCode().equals("HG")) {
                        if (b.getEvaluation() != null && type.equals("EVAL")) {
                            registre.setHistoireGeo(b.getEvaluation());
                            registre.setHistoireGeoCoef(b.getEvaluation() * b.getDiscipline().getCoefficient());
                        }
                        if (b.getComposition() != null && type.equals("COMPO")) {
                            registre.setHistoireGeo(b.getComposition());
                            registre.setHistoireGeoCoef(b.getComposition() * b.getDiscipline().getCoefficient());
                        };
                        if (b.getMoyTrimestre() != null && type.equals("TRIM")) {
                            registre.setHistoireGeo(b.getMoyTrimestre());
                            registre.setHistoireGeoCoef(b.getMoyTrimestreCoef());
                        };
                        if (registre.getHistoireGeoCoef() != null) {
                            total += registre.getHistoireGeoCoef();
                        }
                    }
                    if (b.getDiscipline().getMatiere().getCode().equals("MATHS")) {
                        if (b.getEvaluation() != null && type.equals("EVAL")) {
                            registre.setMaths(b.getEvaluation());
                            registre.setMathsCoef(b.getEvaluation() * b.getDiscipline().getCoefficient());
                        }
                        if (b.getComposition() != null && type.equals("COMPO")) {
                            registre.setMaths(b.getComposition());
                            registre.setMathsCoef(b.getComposition() * b.getDiscipline().getCoefficient());
                        };
                        if (b.getMoyTrimestre() != null && type.equals("TRIM")) {
                            registre.setMaths(b.getMoyTrimestre());
                            registre.setMathsCoef(b.getMoyTrimestreCoef());
                        };
                        if (registre.getMathsCoef() != null) {
                            total += registre.getMathsCoef();
                        }
                    }
                    if (b.getDiscipline().getMatiere().getCode().equals("PC")) {
                        if (b.getEvaluation() != null && type.equals("EVAL")) {
                            registre.setPhyChimie(b.getEvaluation());
                            registre.setPhyChimieCoef(b.getEvaluation() * b.getDiscipline().getCoefficient());
                        }
                        if (b.getComposition() != null && type.equals("COMPO")) {
                            registre.setPhyChimie(b.getComposition());
                            registre.setPhyChimieCoef(b.getComposition() * b.getDiscipline().getCoefficient());
                        };
                        if (b.getMoyTrimestre() != null && type.equals("TRIM")) {
                            registre.setPhyChimie(b.getMoyTrimestre());
                            registre.setPhyChimieCoef(b.getMoyTrimestreCoef());
                        };
                        if (registre.getPhyChimieCoef() != null) {
                            total += registre.getPhyChimieCoef();
                        }
                    }
                    if (b.getDiscipline().getMatiere().getCode().equals("SVT")) {
                        if (b.getEvaluation() != null && type.equals("EVAL")) {
                            registre.setSvt(b.getEvaluation());
                            registre.setSvtCoef(b.getEvaluation() * b.getDiscipline().getCoefficient());
                        }
                        if (b.getComposition() != null && type.equals("COMPO")) {
                            registre.setSvt(b.getComposition());
                            registre.setSvtCoef(b.getComposition() * b.getDiscipline().getCoefficient());
                        };
                        if (b.getMoyTrimestre() != null && type.equals("TRIM")) {
                            registre.setSvt(b.getMoyTrimestre());
                            registre.setSvtCoef(b.getMoyTrimestreCoef());
                        };
                        if (registre.getSvtCoef() != null) {
                            total += registre.getSvtCoef();
                        }
                    }
                    if (b.getDiscipline().getMatiere().getCode().equals("EPS")) {
                        if (b.getEvaluation() != null && type.equals("EVAL")) {
                            registre.setEps(b.getEvaluation());
                            registre.setEpsCoef(b.getEvaluation() * b.getDiscipline().getCoefficient());
                        }
                        if (b.getComposition() != null && type.equals("COMPO")) {
                            registre.setEps(b.getComposition());
                            registre.setEpsCoef(b.getComposition() * b.getDiscipline().getCoefficient());
                        };
                        if (b.getMoyTrimestre() != null && type.equals("TRIM")) {
                            registre.setEps(b.getMoyTrimestre());
                            registre.setEpsCoef(b.getMoyTrimestreCoef());
                        };
                        if (registre.getEpsCoef() != null) {
                            total += registre.getEpsCoef();
                        }
                    }
                    if (b.getDiscipline().getMatiere().getCode().equals("ART")) {
//                        if (b.getEvaluation() != null && type.equals("EVAL")) {
//                            registre.setArt(b.getEvaluation());
//                            registre.setArtCoef(b.getEvaluation() * b.getDiscipline().getCoefficient());
//                        }
//                        if (b.getComposition() != null && type.equals("COMPO")) {
//                            registre.setArt(b.getComposition());
//                            registre.setArtCoef(b.getComposition() * b.getDiscipline().getCoefficient());
//                        };
                        if (b.getMoyTrimestre() != null && type.equals("TRIM")) {
                            registre.setArt(b.getMoyTrimestre());
                            registre.setArtCoef(b.getMoyTrimestreCoef());
                        };
                        if (registre.getArtCoef() != null) {
                            total += registre.getArtCoef();
                        }
                    }
                    if (b.getDiscipline().getMatiere().getCode().equals("CON")) {
//                        if (b.getEvaluation() != null && type.equals("EVAL")) {
//                            registre.setCon(b.getEvaluation());
//                            registre.setConCoef(b.getEvaluation() * b.getDiscipline().getCoefficient());
//                        }
//                        if (b.getComposition() != null && type.equals("COMPO")) {
//                            registre.setCon(b.getComposition());
//                            registre.setConCoef(b.getComposition() * b.getDiscipline().getCoefficient());
//                        };
                        if (b.getMoyTrimestre() != null && type.equals("TRIM")) {
                            registre.setCon(b.getMoyTrimestre());
                            registre.setConCoef(b.getMoyTrimestreCoef());
                        };

                        if (registre.getConCoef() != null) {
                            total += registre.getConCoef();
                        }
                    }
                    if (b.getEvaluation() != null && type.equals("EVAL") && !b.getDiscipline().getMatiere().getCode().equals("ART") && !b.getDiscipline().getMatiere().getCode().equals("CON")) {
                        coef += b.getDiscipline().getCoefficient();
                    }
                    if (b.getComposition() != null && type.equals("COMPO") && !b.getDiscipline().getMatiere().getCode().equals("ART") && !b.getDiscipline().getMatiere().getCode().equals("CON")) {
                        coef += b.getDiscipline().getCoefficient();
                    };
                    if (b.getMoyTrimestre() != null && type.equals("TRIM")) {
                        coef += b.getDiscipline().getCoefficient();
                    };
                });
                registre.setTotal(total);
                registre.setMoyenne(total / coef);
                registres.add(registre);
            }
        });

        Comparator<RegistreLycee> comp = (o1, o2) -> {
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

        if (registres.isEmpty()) {
            return registres;
        }
        int i = 0;

        registres.sort(comp);
        for (RegistreLycee r : registres) {
            r.setRang(++i);
        }
        registres.sort((r1, r2) -> r1.getEleve().compareTo(r2.getEleve()));
        return registres;

    }

    public List<RegistreLycee> updateRegistreLycee2(Classe classe, AnneeAcademique annee, Integer trimestre, String type) {
        List<RegistreLycee> registres = new ArrayList<>();
        List<Examen> examens = examenService.listeParClasseType(classe, trimestre, annee, type);
        classe.getEleveCollection().forEach(eleve -> {
            RegistreLycee registre = new RegistreLycee();
            registre.setEleve(eleve);
            registre.setAnneeAcademique(annee);
            registre.setTrimestre(trimestre);
            registre.setType(type);
            total = 0.0;
            coef = 0;
            examens.forEach(e -> {
                if (e.getDiscipline().getMatiere().getCode().equals("FRAN")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setFrancais(n.isPresent() ? n.get().getNote() : 0.0);
                    registre.setFrancaisCoef((n.isPresent() ? n.get().getNote() : 0.0) * e.getDiscipline().getCoefficient());
                    total += registre.getFrancaisCoef();
                }
                if (e.getDiscipline().getMatiere().getCode().equals("ANGLAIS")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setAnglais(n.isPresent() ? n.get().getNote() : 0.0);
                    registre.setAnglaisCoef((n.isPresent() ? n.get().getNote() : 0.0) * e.getDiscipline().getCoefficient());
                    total += registre.getAnglaisCoef();
                }
                if (e.getDiscipline().getMatiere().getCode().equals("ESP")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setEspagnol(n.isPresent() ? n.get().getNote() : 0.0);
                    registre.setEspagnolCoef((n.isPresent() ? n.get().getNote() : 0.0) * e.getDiscipline().getCoefficient());
                    total += registre.getEspagnolCoef();
                }
                if (e.getDiscipline().getMatiere().getCode().equals("PHILO")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setPhilo(n.isPresent() ? n.get().getNote() : 0.0);
                    registre.setPhiloCoef((n.isPresent() ? n.get().getNote() : 0.0) * e.getDiscipline().getCoefficient());
                    total += registre.getPhiloCoef();
                }
                if (e.getDiscipline().getMatiere().getCode().equals("HG")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setHistoireGeo(n.isPresent() ? n.get().getNote() : 0.0);
                    registre.setHistoireGeoCoef((n.isPresent() ? n.get().getNote() : 0.0) * e.getDiscipline().getCoefficient());
                    total += registre.getHistoireGeoCoef();
                }
                if (e.getDiscipline().getMatiere().getCode().equals("MATHS")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setMaths(n.isPresent() ? n.get().getNote() : 0.0);
                    registre.setMathsCoef((n.isPresent() ? n.get().getNote() : 0.0) * e.getDiscipline().getCoefficient());
                    total += registre.getMathsCoef();
                }
                if (e.getDiscipline().getMatiere().getCode().equals("PC")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setPhyChimie(n.isPresent() ? n.get().getNote() : 0.0);
                    registre.setPhyChimieCoef((n.isPresent() ? n.get().getNote() : 0.0) * e.getDiscipline().getCoefficient());
                    total += registre.getPhyChimieCoef();
                }
                if (e.getDiscipline().getMatiere().getCode().equals("SVT")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setSvt(n.isPresent() ? n.get().getNote() : 0.0);
                    registre.setSvtCoef((n.isPresent() ? n.get().getNote() : 0.0) * e.getDiscipline().getCoefficient());
                    total += registre.getSvtCoef();
                }
                if (e.getDiscipline().getMatiere().getCode().equals("EPS")) {
                    Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                            .findFirst();
                    registre.setEps(n.isPresent() ? n.get().getNote() : 0.0);
                    registre.setEpsCoef((n.isPresent() ? n.get().getNote() : 0.0) * e.getDiscipline().getCoefficient());
                    total += registre.getEpsCoef();
                }
                Optional<Note> n = e.getNoteCollection().stream().filter(note -> note.getEleve().equals(eleve))
                        .findFirst();
                if (n.isPresent() && !n.get().getPresence().equals("MALADE")) {
                    coef += e.getDiscipline().getCoefficient();
                }
            });
            registre.setTotal(total);
            registre.setMoyenne(total / coef);
            registres.add(registre);
        });
        Comparator<RegistreLycee> comp = (o1, o2) -> {
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
        for (RegistreLycee r : registres) {
            r.setRang(++i);
        }
        registres.sort((r1, r2) -> r1.getEleve().compareTo(r2.getEleve()));
        return registres;
    }

    public void updateBilanParSpecialiteLycee(Classe classe, AnneeAcademique anneeAcademique) {

        Logger.getGlobal().log(Level.INFO, "Debut preparation des bilans : {0} {1}", new Object[]{classe.getCode(), anneeAcademique.getAnnee()});
        for (int trimestre = 1; trimestre < 4; trimestre++) {
            List<BilanParSpecialiteLettreLycee> bsls = new ArrayList<>();
            List<BilanParSpecialiteScience> bsss = new ArrayList<>();
            List<BilanParSpecialiteCultureLycee> bscs = new ArrayList<>();

            for (Eleve eleve : classe.getEleveCollection()) {
                BilanParSpecialiteLettreLycee bsl = new BilanParSpecialiteLettreLycee();
                BilanParSpecialiteScience bss = new BilanParSpecialiteScience();
                BilanParSpecialiteCultureLycee bsc = new BilanParSpecialiteCultureLycee();

                bsl.setEleve(eleve);
                bsl.setAnneeAcademique(anneeAcademique);
                bsl.setTrimestre(trimestre);

                bss.setEleve(eleve);
                bss.setAnneeAcademique(anneeAcademique);
                bss.setTrimestre(trimestre);

                bsc.setEleve(eleve);
                bsc.setAnneeAcademique(anneeAcademique);
                bsc.setTrimestre(trimestre);
                List<Bulletin> bulletins = bulletinService.bulletinParEleveEtClasse(eleve, trimestre, anneeAcademique);
                totalLettre = 0.0;
                totalScience = 0.0;
                totalCulture = 0.0;
                p1 = 0;
                p2 = 0;
                p3 = 0;
                if (bulletins != null && !bulletins.isEmpty()) {
                    bulletins.forEach(b -> {
                        try {
                            if (b.getDiscipline().getMatiere().getCode().equals("FRAN")) {
                                bsl.setFrancais(b.getMoyTrimestre());
                                totalLettre += bsl.getFrancais();
                                p1++;
                            }
                            if (b.getDiscipline().getMatiere().getCode().equals("ANGLAIS")) {
                                bsl.setAnglais(b.getMoyTrimestre());
                                totalLettre += bsl.getAnglais();
                                p1++;
                            }
                            if (b.getDiscipline().getMatiere().getCode().equals("ESP")) {
                                bsl.setEspagnol(b.getMoyTrimestre());
                                totalLettre += bsl.getEspagnol();
                                p1++;
                            }
                            if (b.getDiscipline().getMatiere().getCode().equals("PHILO")) {
                                bsl.setPhilo(b.getMoyTrimestre());
                                totalLettre += bsl.getPhilo();
                                p1++;
                            }
                            if (b.getDiscipline().getMatiere().getCode().equals("HG")) {
                                bsl.setHistGeo(b.getMoyTrimestre());
                                totalLettre += bsl.getHistGeo();
                                p1++;
                            }

                            bsl.setTotal(totalLettre);
                            bsl.setMoyenne(totalLettre / p1);

                            if (b.getDiscipline().getMatiere().getCode().equals("MATHS")) {
                                bss.setMaths(b.getMoyTrimestre());
                                totalScience += bss.getMaths();
                                p2++;
                            }
                            if (b.getDiscipline().getMatiere().getCode().equals("PC")) {
                                bss.setPhysiqueChimie(b.getMoyTrimestre());
                                totalScience += bss.getPhysiqueChimie();
                                p2++;
                            }
                            if (b.getDiscipline().getMatiere().getCode().equals("SVT")) {
                                bss.setSvt(b.getMoyTrimestre());
                                totalScience += bss.getSvt();
                                p2++;
                            }

                            bss.setTotal(totalScience);
                            bss.setMoyenne(totalScience / p2);

                            if (b.getDiscipline().getMatiere().getCode().equals("EPS")) {
                                bsc.setEps(b.getMoyTrimestre());
                                totalCulture += bsc.getEps();
                                p3++;
                            }
                            if (b.getDiscipline().getMatiere().getCode().equals("ART")) {
                                bsc.setArt(b.getMoyTrimestre());
                                totalCulture += bsc.getArt();
                                p3++;
                            }
                            if (b.getDiscipline().getMatiere().getCode().equals("CON")) {
                                bsc.setCon(b.getMoyTrimestre());
                                totalCulture += bsc.getCon();
                                p3++;
                            }

                            bsc.setTotal(totalCulture);
                            bsc.setMoyenne(totalCulture / p3);
                        } catch (NullPointerException e) {
                            System.err.println(e.getMessage());
                        }
                    });

                    bsls.add(bsl);
                    bsss.add(bss);
                    bscs.add(bsc);

                }
            };

            Comparator<BilanParSpecialiteLettreLycee> comp1 = (o1, o2) -> {
                try {
                    return o2.compareTo(o1);
                } catch (NullPointerException ex) {
                    return 0;
                }
            };
            Comparator<BilanParSpecialiteScience> comp2 = (o1, o2) -> {
                try {
                    return o2.compareTo(o1);
                } catch (NullPointerException ex) {
                    return 0;
                }
            };
            Comparator<BilanParSpecialiteCultureLycee> comp3 = (o1, o2) -> {
                try {
                    return o2.compareTo(o1);
                } catch (NullPointerException ex) {
                    return 0;
                }
            };

//        if (registres.isEmpty()) {
//            return registres;
//        }
            int i = 0;
            int j = 0;
            int k = 0;

            bsls.sort(comp1);
            bsss.sort(comp2);
            bscs.sort(comp3);

            for (BilanParSpecialiteLettreLycee r : bsls) {
                r.setRang(++i);
            }
            for (BilanParSpecialiteScience r : bsss) {
                r.setRang(++j);
            }
            for (BilanParSpecialiteCultureLycee r : bscs) {
                r.setRang(++k);
            }

            bsls.stream().map((t) -> {
                BilanParSpecialiteLettreLycee bsl = bslLyceeService.exists(t.getEleve(), t.getTrimestre(), t.getAnneeAcademique());
                if (bsl != null) {
                    t.setId(bsl.getId());
                }
                return t;
            }).forEachOrdered((t) -> {
                bslLyceeService.saveOrUpdate(t);
            });
            bsss.stream().map((t) -> {
                BilanParSpecialiteScience bss = bssService.exists(t.getEleve(), t.getTrimestre(), t.getAnneeAcademique());
                if (bss != null) {
                    t.setId(bss.getId());
                }
                return t;
            }).forEachOrdered((t) -> {
                bssService.saveOrUpdate(t);
            });
            bscs.stream().map((t) -> {
                BilanParSpecialiteCultureLycee bsc = bscLyceeService.exists(t.getEleve(), t.getTrimestre(), t.getAnneeAcademique());
                if (bsc != null) {
                    t.setId(bsc.getId());
                }
                return t;
            }).forEachOrdered((t) -> {
                bscLyceeService.saveOrUpdate(t);
            });

//        HashMap<String, List> map = new HashMap<>();
//        map.put("Lettre", bsls);
//        map.put("Science", bsss);
//        map.put("Culture", bscs);
//        registres.sort((r1, r2) -> r1.getEleve().compareTo(r2.getEleve()));
        }
        Logger.getGlobal().log(Level.WARNING, "Fin de l'operation des bilans : {0} {1}", new Object[]{classe.getCode(), anneeAcademique.getAnnee()});

//        return map;
    }
}
