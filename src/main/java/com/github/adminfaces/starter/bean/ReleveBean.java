package com.github.adminfaces.starter.bean;

import static com.github.adminfaces.persistence.util.Messages.addDetailMessage;
import com.github.adminfaces.starter.model.BilanAnnuel;
import com.github.adminfaces.starter.model.Bulletin;
import com.github.adminfaces.starter.model.Bulletin_;
import com.github.adminfaces.starter.model.Classe;
import com.github.adminfaces.starter.model.Discipline;
import com.github.adminfaces.starter.model.Discipline_;
import com.github.adminfaces.starter.model.Eleve;
import com.github.adminfaces.starter.model.Examen;
import com.github.adminfaces.starter.model.Examen_;
import com.github.adminfaces.starter.model.Matiere;
import com.github.adminfaces.starter.model.Note;
import com.github.adminfaces.starter.model.RegistreCollege;
import com.github.adminfaces.starter.model.RegistreLycee;
import com.github.adminfaces.starter.model.Resultat;
import com.github.adminfaces.starter.model.Resultat_;
import com.github.adminfaces.starter.service.BilanAnnuelService;
import com.github.adminfaces.starter.service.BulletinService;
import com.github.adminfaces.starter.service.DisciplineService;
import com.github.adminfaces.starter.service.EleveService;
import com.github.adminfaces.starter.service.ExamenService;
import com.github.adminfaces.starter.service.NoteService;
import com.github.adminfaces.starter.service.UpdateService;
import com.github.adminfaces.starter.service.ResultatService;
import com.github.adminfaces.template.config.AdminConfig;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author calviniloki
 */
@Named
@SessionScoped
public class ReleveBean implements Serializable {

    //CDI inject elements
    @Inject
    AdminConfig adminConfig;
    @Inject
    ExamenService examenService;
    @Inject
    NoteService noteService;
    @Inject
    EleveService eleveService;
    @Inject
    DisciplineService disciplineService;
    @Inject
    BulletinService bulletinService;
    @Inject
    ResultatService resultatService;
    @Inject
    UpdateService updateService;
    @Inject
    BilanAnnuelService bilanAnnuelService;

    //Champs necessaires
    private Classe classe;
    private Eleve eleve;
    private Matiere matiere;
    private Discipline discipline;
    private Integer trimestre;
    private Resultat resultat;
//    @NotNull(message = "Ne doit pas être vide")
//    private AnneeAcademique anneeAcademique;

    private List<Eleve> eleves;
    private List<Eleve> elevesParClasse;

    private List<Bulletin> bulletins;
    private List<Bulletin> bulletinsParDisciplineTrimestreOrdreEleve;

    private List<Resultat> resultats;
    private Map<Integer, Double> listRang;

    private List<RegistreCollege> registresEval;
    private List<RegistreCollege> registresCompo;
    private List<RegistreCollege> registresTrim;

    private Integer progress;

    @Resource(name="java:app/ceminace")
    DataSource dataSource;
    private int sumCoef;
    private int i;
    private Integer progressBulletin;

    @PostConstruct
    public void init() {
        eleves = new ArrayList<>();
        bulletins = null;
    }

    public void updateRegistre(ActionEvent event) {
        progress = 0;
        i = 0;
        /* Recherche des bulletins, notes et resultats orphelins d'eleve, l'eleve a été créé et les bulletins 
         * générés ensuite l'eleve a été supprimé sans que les bulletins se suppriment.
         * Une fois ces bulletins, notes et resultats trouvés , on les supprime pour eviter les erreurs lors de la generation des resultats
         */
        List<Bulletin> bulletinsOrphelinEleve = bulletinService.bulletinsAyantEleveSupprime();
        List<Bulletin> bulletinsDisciplineOrphelin = bulletinService.bulletinAyantDisciplineOrphelin();
        List<Note> notesOrphelinEleve = noteService.notesAyantEleveSupprime();
        List<Resultat> resultatsAyantEleveOrphelin = resultatService.resultatsAyantEleveOrphelin();
        List<Resultat> resultatsAyantClasseOrpheline = resultatService.resultatsAyantClasseOrphelin();

        System.out.println("Bulletins ayant eleves orphelin : " + bulletinsOrphelinEleve.size());
        System.out.println("Bulletins ayant discipline orpheline : " + bulletinsDisciplineOrphelin.size());
        System.out.println("Notes ayant eleve orphelin : " + notesOrphelinEleve.size());
        System.out.println("Resultats ayant eleve orphelin : " + resultatsAyantEleveOrphelin.size());
        System.out.println("Resultats ayant classe orpheline : " + resultatsAyantClasseOrpheline.size());

        bulletinService.remove(bulletinsOrphelinEleve);
        System.out.println(bulletinsOrphelinEleve.size() + " bulletins ayant eleve orphelin");

        bulletinService.remove(bulletinsDisciplineOrphelin);
        System.out.println(bulletinsDisciplineOrphelin.size() + " bulletins ayant discipline orpheline");

        noteService.remove(notesOrphelinEleve);
        System.out.println(notesOrphelinEleve.size() + " notes ayant eleve orphelin");

        resultatService.remove(resultatsAyantEleveOrphelin);
        System.out.println(resultatsAyantEleveOrphelin.size() + " resultats ayant eleve orphelin");

        resultatService.remove(resultatsAyantClasseOrpheline);
        System.out.println(resultatsAyantClasseOrpheline.size() + " Resultats ayant classe orpheline");

        registresEval = updateService.updateRegistreCollege(classe, trimestre, "EVALUATION");
        registresCompo = updateService.updateRegistreCollege(classe, trimestre, "COMPOSITION");
        registresTrim = updateService.updateRegistreTrimestreCollege(classe, trimestre);
    }

    public void actualiserBilanAnnuel(Classe c) {
        List<BilanAnnuel> bas = new ArrayList<>();
        for (Eleve e : c.getEleveCollection()) {
            BilanAnnuel ba = new BilanAnnuel();
            double m = 0;
            for (int k = 1; k < 4; k++) {
                Resultat r = resultatService.exists(c, k, e);
                ba.setClasse(c);
                ba.setAnneeAcademique(c.getAnneeAcademique());
                ba.setEleve(e);
                if (r != null) {
                    if (k == 1) {
                        ba.setTrim1(r.getMoyenne());
                    }
                    if (k == 2) {
                        ba.setTrim2(r.getMoyenne());
                    }
                    if (k == 3) {
                        ba.setTrim3(r.getMoyenne());
                    }
                    m += r.getMoyenne();
                }
            }
            try {
                ba.setMoyenne(m / 3);
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
            bas.add(ba);
        }
        Comparator<BilanAnnuel> comp = (o1, o2) -> {
            return o2.getMoyenne().compareTo(o1.getMoyenne());
        };
        Comparator<BilanAnnuel> comp1 = (o1, o2) -> {
            try {
                int t = o2.getMoyenne().compareTo(o1.getMoyenne());
                return t;
            } catch (NullPointerException ex) {
                return 0;
            }
        };

        int i = 0;
        bas.sort(comp1);
        for (BilanAnnuel ba : bas) {
            ba.setRang(++i);
        }
        bas.stream().map((t) -> {
            BilanAnnuel ba = bilanAnnuelService.exists(t.getClasse(), t.getEleve());
            if (ba == null) {
                ba = new BilanAnnuel();
                ba.setAnneeAcademique(t.getClasse().getAnneeAcademique());
                ba.setClasse(t.getClasse());
                ba.setEleve(t.getEleve());
            }
            ba.setTrim1(t.getTrim1());
            ba.setTrim2(t.getTrim2());
            ba.setTrim3(t.getTrim3());
            ba.setMoyenne(t.getMoyenne());
            ba.setRang(t.getRang());
            return ba;
        }).forEachOrdered((b) -> {
            bilanAnnuelService.saveOrUpdate(b);
        });
    }

    public void genererBulletin() {
//        adminConfig.setRenderAjaxStatus(false);
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Calcul du bulletin  : {0}/{1}-{2}", new Object[]{eleve, classe, trimestre});
        progressBulletin = 0;
        int k = 0;
        List<Discipline> disciplines = disciplineService.criteria()
                .eq(Discipline_.classe, classe)
                .getResultList();
        for (Discipline d : disciplines) {
            matiere = d.getMatiere();
            calculerReleveParClasseEtMatiereEtTrimestre();
            progressBulletin = 100 * (++k) / disciplines.size();
        }
        consulterResultats();
        bulletins = bulletinService.bulletinsParEleveEtTrimestre(eleve, trimestre);
        Comparator<Bulletin> order = (o1, o2) -> {
            int d = o1.getDiscipline()
                    .getMatiere()
                    .getIndex() - o2.getDiscipline()
                    .getMatiere()
                    .getIndex();
            if (Math.abs(d) > 1) {
                return d;
            } else {
                if (d > 0) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };
        bulletins.sort(order);
        resultat = resultatParEleveEtTrimestre(eleve, trimestre);
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Traitement terminé");
//        adminConfig.setRenderAjaxStatus(true);
    }

    public void calculerReleveParClasseEtMatiereEtTrimestre() {
        progress = 0;
        i = 0;

        Logger.getLogger(getClass().getName()).log(Level.INFO, "Debut du calcul de relev\u00e9 : {0}/{1}-{2}", new Object[]{classe, matiere, trimestre});
        //Recherche de la discipline correspondant à la @classe et la @matiere
        discipline = findDisciplineByClasseMatiere(classe, matiere);
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Discipline trouv\u00e9: {0}", discipline);
        if (discipline != null) {
//            elevesParClasse = eleveService.listeParClasse(classe);
            Logger.getLogger(getClass().getName()).log(Level.INFO, "Liste des eleves \u00e0 calculer les relev\u00e9s : {0}", classe.getEleveCollection().toString());
            List<Double> listMoy = new ArrayList<>();
            classe.getEleveCollection().forEach(e -> {
                Logger.getLogger(getClass().getName()).log(Level.INFO, e.toString());
                Note interro1 = noteParEleveExamen(e, "INTERRO 1", matiere, trimestre);
                Note interro2 = noteParEleveExamen(e, "INTERRO 2", matiere, trimestre);
                Double moyInterro = moyenne(interro1, interro2);

                Note eval = noteParEleveExamen(e, "EVALUATION", matiere, trimestre);

                Double moyClasse = moyenne2(moyInterro, eval);

                Note compo = noteParEleveExamen(e, "COMPOSITION", matiere, trimestre);

                Double moyTrimestre = moyenne2(moyClasse, compo);

                double moy = discipline.getCoefficient() * (moyTrimestre != null ? moyTrimestre : 0.0);
                listMoy.add(moy);

                Bulletin bulletin = new Bulletin();
                bulletin.setAnneeAcademique(e.getAnneeAcademique());
                bulletin.setEleve(e);
                bulletin.setDiscipline(discipline);
                bulletin.setTrimestre(trimestre);

                Note i1 = noteParEleveExamen(e, "INTERRO 1", matiere, trimestre);
                Double noteInterro1 = interro1 != null ? interro1.getPresence().equals("MALADE") ? null : interro1.getNote() : null;
                bulletin.setInterro1(noteInterro1);

                Note i2 = noteParEleveExamen(e, "INTERRO 2", matiere, trimestre);
                Double noteI2 = i2 != null ? i2.getPresence().equals("MALADE") ? null : i2.getNote() : null;
                bulletin.setInterro2(noteI2);

                Double moyI = moyenne(i1, i2);
                bulletin.setMoyInterro(moyI);

                Note ev = noteParEleveExamen(e, "EVALUATION", matiere, trimestre);
                Double noteEv = ev != null ? ev.getPresence().equals("MALADE") ? null : ev.getNote() : null;
                bulletin.setEvaluation(noteEv);

                Double moyenneClasse = moyenne2(moyInterro, eval);
                bulletin.setMoyClasse(moyenneClasse);

                Note composition = noteParEleveExamen(e, "COMPOSITION", matiere, trimestre);
                Double noteComposition = composition != null ? composition.getPresence().equals("MALADE") ? null : composition.getNote() : null;
                bulletin.setComposition(noteComposition);

                Double moyenneTrimestre = moyenne2(moyClasse, compo);
                bulletin.setMoyTrimestre(moyenneTrimestre != null ? moyenneTrimestre : null);

                bulletin.setMoyTrimestreCoef(moyenneTrimestre != null ? moyenneTrimestre * discipline.getCoefficient() : 0);
                bulletin.setCoef(discipline.getCoefficient());
                List<Bulletin> bs = bulletinService.exists(e, trimestre, discipline);
                if (bs != null && !bs.isEmpty()) {
                    if (bs.size() == 1) {
                        bulletin.setId(bs.get(0).getId());
                    } else {
                        bulletinService.remove(bs);
                    }
                }
                bulletinService.saveOrUpdate(bulletin);
                Logger.getLogger(getClass().getName()).log(Level.INFO, "Bulletin  {0}]", bs);

                progress = 90 * (++i) / classe.getEffectifTotal();
            });
            Logger.getLogger(getClass().getName()).log(Level.INFO, "Ordonner des moyennes   {0}/{1}-{2}", new Object[]{classe, matiere, trimestre});

            listMoy.sort((o1, o2) -> {
                return o1.compareTo(o2);
            });
            listRang = new HashMap();
            int j = 0;
            for (Double moy : listMoy) {
                listRang.put(++j, moy);
            }
        } else {
            addDetailMessage("La discipline " + matiere.getLibelle() + " n'existe pas en classe " + classe.getLibelle());
            return;
        }

//        elevesParClasse.forEach(e -> {
//            Bulletin bulletin = new Bulletin();
//            bulletin.setAnneeAcademique(e.getAnneeAcademique());
//            bulletin.setEleve(e);
//            bulletin.setDiscipline(discipline);
//            bulletin.setTrimestre(trimestre);
//            
//            Note interro1 = noteEleveExamen(e, "INTERRO 1");
//            Double noteInterro1 = interro1 != null ? interro1.getPresence().equals("MALADE") ? null : interro1.getNote() : null;
//            bulletin.setInterro1(noteInterro1);
//            
//            Note interro2 = noteEleveExamen(e, "INTERRO 2");
//            Double noteInterro2 = interro2 != null ? interro2.getPresence().equals("MALADE") ? null : interro2.getNote() : null;
//            bulletin.setInterro2(noteInterro2);
//            
//            Double moyInterro = moyenne(interro1, interro2);
//            bulletin.setMoyInterro(moyInterro);
//            
//            Note eval = noteEleveExamen(e, "EVALUATION");
//            Double noteEval = eval != null ? eval.getPresence().equals("MALADE") ? null : eval.getNote() : null;
//            bulletin.setEvaluation(noteEval);
//            
//            Double moyClasse = moyenne2(moyInterro, eval);
//            bulletin.setMoyClasse(moyClasse);
//            
//            Note compo = noteEleveExamen(e, "COMPOSITION");
//            Double noteCompo = compo != null ? compo.getPresence().equals("MALADE") ? null : compo.getNote() : null;
//            bulletin.setComposition(noteCompo);
//            
//            Double moyTrimestre = moyenne2(moyClasse, compo);
//            bulletin.setMoyTrimestre(moyTrimestre != null ? moyTrimestre : null);
//            
//            bulletin.setMoyTrimestreCoef(moyTrimestre != null ? moyTrimestre * discipline.getCoefficient() : null);
//            bulletin.setCoef(discipline.getCoefficient());
//            List<Bulletin> b = bulletinService.exists(e, trimestre, discipline);
//            if (b != null && !b.isEmpty()) {
//                if (b.size() == 1) {
//                    bulletin.setId(b.get(0).getId());
//                } else {
//                    bulletinService.remove(b);
//                }
//            }
//            bulletinService.saveOrUpdate(bulletin);
//            
//        });
        //Actualisation des données de toutes les disciplines de cette classe
        List<Discipline> disciplines = disciplineService.criteria()
                .eq(Discipline_.classe, classe)
                .eq(Discipline_.matiere, matiere)
                .getResultList();
        disciplines.forEach(d -> {
            Logger.getLogger(getClass().getName()).log(Level.INFO, "Ordonner des bulletins par moyenne trimestrielle   {0}/{1}-{2}", new Object[]{classe, d, trimestre});
            bulletinsParDisciplineTrimestreOrdreEleve = bulletinService.criteria()
                    .eq(Bulletin_.discipline, d)
                    .eq(Bulletin_.trimestre, trimestre)
                    //                    .eq(Bulletin_.anneeAcademique, anneeAcademique)
                    .orderAsc(Bulletin_.eleve)
                    .getResultList();

            int ii = 0;
            bulletinsParDisciplineTrimestreOrdreEleve.sort((o1, o2) -> {
                if (o1 == null || o2 == null) {
                    System.out.println("errreur");
                    return 0;
                }
                Double dd = 1000 * ((o2.getMoyTrimestreCoef() != null ? o2.getMoyTrimestreCoef() : 0.0) - (o1.getMoyTrimestreCoef() != null ? o1.getMoyTrimestreCoef() : 0.0));
                return dd.intValue();
            });
            for (Bulletin b : bulletinsParDisciplineTrimestreOrdreEleve) {
                b.setRang(++ii);
                bulletinService.update(b);
                Logger.getLogger(getClass().getName()).log(Level.INFO, "UPDATE : {0}", b.toString());
                progress = 10 * (++i) / classe.getEffectifTotal();
            }
        });
//        bulletinsParDisciplineTrimestreOrdreEleve = bulletinService.criteria()
//                .eq(Bulletin_.discipline, discipline)
//                .eq(Bulletin_.trimestre, trimestre)
//                //                .eq(Bulletin_.anneeAcademique, anneeAcademique)
//                .orderAsc(Bulletin_.eleve)
//                .getResultList();
    }

    public void consulterResultats() {
        progress = 0;
        i = 0;
        sumCoef = classe.getDisciplineCollection().stream().collect(Collectors.summingInt(Discipline::getCoefficient));
        classe.getEleveCollection().stream().map((e) -> {
            List<Bulletin> bs = bulletinService.bulletinsParEleveEtTrimestre(e, trimestre);
            Double moyenne = 0.0;
//            Integer sumCoef = 0;
            if (bs.isEmpty()) {
                return null;
            }
            for (Bulletin b : bs) {
                if (b.getMoyTrimestre() != null) {
                    moyenne += b.getMoyTrimestreCoef()==null?0.0:b.getMoyTrimestreCoef();
//                    sumCoef += b.getCoef();
                }
            }
            Resultat rslt = new Resultat();
            rslt.setAnneeAcademique(classe.getAnneeAcademique());
            rslt.setClasse(classe);
            rslt.setTrimestre(trimestre);
            rslt.setEleve(e);
            rslt.setMoyenne(moyenne / sumCoef);
            Resultat r = resultatService.exists(classe, trimestre, e);
            if (r != null) {
                rslt.setId(r.getId());
            }
            return rslt;
        }).forEachOrdered((rslt) -> {
            if (rslt != null) {
                resultatService.saveOrUpdate(rslt);
            }
            progress = 50 * (++i) / classe.getEffectifTotal();
        });
        resultats = resultatService.resultatParClasseEtTrimestre(classe, trimestre);
        if (resultats != null && !resultats.isEmpty()) {
            Comparator<Resultat> comp = (o1, o2) -> {

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

            int j = 0;
            resultats.sort(comp);
            for (Resultat r : resultats) {

                r.setRang(++j);
                resultatService.update(r);
                progress = 40 * (++i) / classe.getEffectifTotal();
            }

            if (resultats != null) {
                resultats.sort((o1, o2) -> {
                    return o1.compareTo(o2);
                });
            }
        }
        actualiserBilanAnnuel(classe);
        progress = 100;
    }

    //Exporter les PDFs 
    public void exportBilanAnnuel() throws JRException, IOException, SQLException {

        String jasperReportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/report/bilanAnnuel.jrxml");
        jasperReportPath = JasperCompileManager.compileReportToFile(jasperReportPath);
        Map<String, Object> map = new HashMap<>();
        map.put("annee", classe.getAnneeAcademique().getAnnee());
        map.put("classe", classe.getCode());
        map.put(JRParameter.REPORT_LOCALE, Locale.FRANCE);

        Connection con = dataSource.getConnection();
//                DriverManager.getConnection("jdbc:mysql://ceminace-college:3306/ceminace?serverTimezone=UTC&user=ceminace&password=ceminace&allowPublicKeyRetrieval=true&useSSL=false");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReportPath, map, con);

        SimplePdfReportConfiguration configuration = new SimplePdfReportConfiguration();
        JRPdfExporter exporter = new JRPdfExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.reset();
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=\"" + "BILAN_ANNUEL_" + classe.getCode() + "_" + classe.getAnneeAcademique() + ".pdf\"");
        ServletOutputStream out = response.getOutputStream();
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void exportRegistreIV() throws JRException, IOException, SQLException {
        updateService.updateBilanParSpecialiteCollege(classe);
        String jasperReportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/report/REGISTRE_4.jrxml");
        JasperCompileManager.compileReportToFile(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/report/R4_1.jrxml"));
        JasperCompileManager.compileReportToFile(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/report/R4_2.jrxml"));
        JasperCompileManager.compileReportToFile(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/report/R4_3.jrxml"));
        jasperReportPath = JasperCompileManager.compileReportToFile(jasperReportPath);
        Map<String, Object> map = new HashMap<>();
        map.put("annee", classe.getAnneeAcademique().getAnnee());
        map.put("classe", classe.getCode());
        map.put("cycle", "college");
        map.put(JRParameter.REPORT_LOCALE, Locale.FRANCE);
        Connection con = dataSource.getConnection();
//        Connection con = DriverManager.getConnection("jdbc:mysql://ceminace-college:3306/ceminace?serverTimezone=UTC&user=ceminace&password=ceminace&allowPublicKeyRetrieval=true&useSSL=false");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReportPath, map, con);

        SimplePdfReportConfiguration configuration = new SimplePdfReportConfiguration();
        JRPdfExporter exporter = new JRPdfExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.reset();
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=\"" + "REGISTRE IV " + classe.getLibelle() + " " + classe.getAnneeAcademique() + ".pdf\"");
        ServletOutputStream out = response.getOutputStream();
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void exportTrimestre() throws JRException, IOException {
        JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(registresTrim);

        String jasperReportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/report/trimestre.jrxml");
        jasperReportPath = JasperCompileManager.compileReportToFile(jasperReportPath);
        Map<String, Object> map = new HashMap<>();
        map.put("annee", classe.getAnneeAcademique().getAnnee());
        int total = 0;
        total = classe.getDisciplineCollection().stream().map(d -> {
            map.put(d.getMatiere().getCode().toLowerCase(), d.getCoefficient());
            return d;
        }).map(d -> d.getCoefficient()).reduce(total, Integer::sum);
        map.put("total", total);
        map.put("classe", classe);
        map.put("trimestre", trimestre);
        map.put("data", data);
        Integer coef = classe.getDisciplineCollection().stream()
                .collect(Collectors.summingInt(Discipline::getCoefficient));
        map.put("coef", coef);
        map.put(JRParameter.REPORT_LOCALE, Locale.FRANCE);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReportPath, map, new JREmptyDataSource());

        SimplePdfReportConfiguration configuration = new SimplePdfReportConfiguration();
        JRPdfExporter exporter = new JRPdfExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.reset();
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=\"" + "TRIMESTRE_" + classe.getCode() + "_Trim_" + trimestre + "_" + classe.getAnneeAcademique() + ".pdf\"");
        ServletOutputStream out = response.getOutputStream();
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void exportComposition() throws JRException, IOException {
        JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(registresCompo);

        String jasperReportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/report/composition.jrxml");
        jasperReportPath = JasperCompileManager.compileReportToFile(jasperReportPath);
        Map<String, Object> map = new HashMap<>();
        int total = 0;
        total = classe.getDisciplineCollection().stream().filter(d -> (!d.getMatiere().getCode().equals("ART")) && (!d.getMatiere().getCode().equals("CON")))
                .map(d -> {
                    map.put(d.getMatiere().getCode().toLowerCase(), d.getCoefficient());
                    return d;
                }).map(d -> d.getCoefficient()).reduce(total, Integer::sum);
        map.put("total", total);
        map.put("annee", classe.getAnneeAcademique().getAnnee());
        map.put("classe", classe);
        map.put("trimestre", trimestre);
        map.put("data", data);
        Integer coef = classe.getDisciplineCollection().stream()
                .filter(d -> !d.getMatiere().getCode().equals("CON") && !d.getMatiere().getCode().equals("ART"))
                .collect(Collectors.summingInt(Discipline::getCoefficient));
        map.put("coef", coef);
        map.put(JRParameter.REPORT_LOCALE, Locale.FRANCE);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReportPath, map, new JREmptyDataSource());

        SimplePdfReportConfiguration configuration = new SimplePdfReportConfiguration();
        JRPdfExporter exporter = new JRPdfExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.reset();
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=\"" + "COMPOSITION_" + classe.getCode() + "_Trim_" + trimestre + "_" + classe.getAnneeAcademique() + ".pdf\"");
        ServletOutputStream out = response.getOutputStream();
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void exportEvaluation() throws JRException, IOException {
        JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(registresEval);
        String jasperReportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/report/evaluation.jrxml");
        jasperReportPath = JasperCompileManager.compileReportToFile(jasperReportPath);
        Map<String, Object> map = new HashMap<>();
        int total = 0;
        total = classe.getDisciplineCollection().stream().filter(d -> !d.getMatiere().getCode().equals("ART") && !d.getMatiere().getCode().equals("CON"))
                .map(d -> {
                    map.put(d.getMatiere().getCode().toLowerCase(), d.getCoefficient());
                    return d;
                }).map(d -> d.getCoefficient()).reduce(total, Integer::sum);
        map.put("total", total);
        map.put("annee", classe.getAnneeAcademique().getAnnee());
        map.put("classe", classe.getLibelle());
        map.put("trimestre", trimestre);
        map.put("data", data);
        map.put(JRParameter.REPORT_LOCALE, Locale.FRANCE);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReportPath, map, new JREmptyDataSource());

        SimplePdfReportConfiguration configuration = new SimplePdfReportConfiguration();
        JRPdfExporter exporter = new JRPdfExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.reset();
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=\"" + "EVALUATION_" + classe.getCode() + "_Trim_" + trimestre + "_" + classe.getAnneeAcademique() + ".pdf\"");
        ServletOutputStream out = response.getOutputStream();
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void exportTrim() throws JRException, IOException {
        List<Examen> examens = examenService.criteria()
                .eq(Examen_.trimestre, trimestre)
                .eq(Examen_.anneeAcademique, classe.getAnneeAcademique())
                .eq(Examen_.type, "EVALUATION")
                .getResultList();
        List<Note> notes = new ArrayList<>();
        examens.forEach(exa -> {
            notes.addAll(exa.getNoteCollection());
        });

        JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(notes);

        String jasperReportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/report/trimestre.jrxml");
        jasperReportPath = JasperCompileManager.compileReportToFile(jasperReportPath);
        Map<String, Object> map = new HashMap<>();
        map.put("annee", classe.getAnneeAcademique().getAnnee());
        map.put("trimestre", trimestre);
        map.put("data", data);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReportPath, map, new JREmptyDataSource());

        SimplePdfReportConfiguration configuration = new SimplePdfReportConfiguration();
        JRPdfExporter exporter = new JRPdfExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.reset();
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=\"" + eleve + ".pdf\"");
        ServletOutputStream out = response.getOutputStream();
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void exportPDF() throws JRException, IOException {
        JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(bulletins);

        String jasperReportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/report/bulletin.jrxml");
        jasperReportPath = JasperCompileManager.compileReportToFile(jasperReportPath);
        Map<String, Object> map = new HashMap<>();
        map.put("data", data);
        map.put("eleve", eleve);
        map.put("resultat", resultat);
        map.put("REPORT_LOCALE", Locale.FRENCH);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReportPath, map, new JREmptyDataSource());

        SimplePdfReportConfiguration configuration = new SimplePdfReportConfiguration();
        JRPdfExporter exporter = new JRPdfExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.reset();
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=\"" + eleve + ".pdf\"");
        ServletOutputStream out = response.getOutputStream();
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void exportRelevePDF() throws JRException, IOException {
        JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(bulletinsParDisciplineTrimestreOrdreEleve);

        String jasperReportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/report/releve.jrxml");
        jasperReportPath = JasperCompileManager.compileReportToFile(jasperReportPath);
        Map<String, Object> map = new HashMap<>();
        map.put("data", data);
        map.put("classe", classe);
        map.put("discipline", discipline);
        map.put("annee", classe.getAnneeAcademique().getAnnee());
        map.put("trimestre", trimestre);
        map.put("REPORT_LOCALE", Locale.FRENCH);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReportPath, map, new JREmptyDataSource());

        SimplePdfReportConfiguration configuration = new SimplePdfReportConfiguration();
        JRPdfExporter exporter = new JRPdfExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.reset();
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=\"Releve_" + classe.getCode() + "_" + matiere.getCode() + "_Trim" + trimestre + "_" + classe.getAnneeAcademique().getAnnee() + ".pdf\"");
        ServletOutputStream out = response.getOutputStream();
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        FacesContext.getCurrentInstance().responseComplete();
    }

    //Utilitaires
    public void onComplete() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Traitement terminé avec succès"));
    }

    private Double moyenne2(Double a, Note nb) {
        Double note = 0.0;
        if (nb != null && nb.getPresence().equals("MALADE")) {
            note = a;
        } else if (nb == null) {
            note = a;
        } else if (a == null || nb.getExamen().getDiscipline().getMatiere().getCode().equals("CON") || nb.getExamen().getDiscipline().getMatiere().getCode().equals("ART")) {
            note = nb.getNote();
        } else {
            note = (a + nb.getNote()) / 2;
        }
        return note;
    }

    private Double moyenne(Note na, Note nb) {
        Double note = null;
        if (na == null && nb == null) {
        } else if (na == null && nb != null) {
            if (nb.getPresence().equals("MALADE")) {
                note = null;
            } else {
                note = nb.getNote();
            }
        } else if (na != null && nb == null) {
            if (na.getPresence().equals("MALADE")) {
                note = null;
            } else {
                note = na.getNote();
            }
        } else {
            if (na.getPresence().equals("MALADE") && nb.getPresence().equals("MALADE")) {
            } else if (na.getPresence().equals("MALADE") && (!nb.getPresence().equals("MALADE"))) {
                note = nb.getNote();
            } else if (nb.getPresence().equals("MALADE") && (!na.getPresence().equals("MALADE"))) {
                note = na.getNote();
            } else {
                note = (na.getNote() + nb.getNote()) / 2;
            }
        }
        return note;
    }

    public Note noteParEleveExamen(Eleve eleve, String typeExamen, Matiere m, Integer trim) {
        Optional<Examen> examen = examenService.examensParClasseMatiereTrimestreType(eleve.getClasse(), m, trim, typeExamen);
        Optional<Note> note = noteService.noteParExamenEleve(examen.orElse(null), eleve);
        return note.orElse(null);
    }

    public void supprimer(Object object) {
        if (object instanceof Bulletin) {
            Bulletin bulletin = (Bulletin) object;
            bulletinService.remove(bulletin);
            genererBulletin();
        }
        if (object instanceof Resultat) {
            Resultat r = (Resultat) object;
            resultatService.remove(resultat);
            consulterResultats();
        }

    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        Rectangle rectangle = new Rectangle(PageSize.A4.getTop(), PageSize.A4.getRight());
        pdf.setPageSize(rectangle);

        pdf.open();
    }

    public Double dataLycee(RegistreLycee r, String code) {
        switch (code) {
            case "FRAN":
                return r.getFrancais();
            case "ANGLAIS":
                return r.getAnglais();
            case "ESP":
                return r.getEspagnol();
            case "PHILO":
                return r.getPhilo();
            case "MATHS":
                return r.getMaths();
            case "EPS":
                return r.getEps();
            case "HG":
                return r.getHistoireGeo();
            case "PC":
                return r.getPhyChimie();
            case "SVT":
                return r.getSvt();
            case "ART":
                return r.getArt();
            case "CON":
                return r.getCon();
            default:
                return 0.0;

        }
    }

    public Double dataCoefLycee(RegistreLycee r, String code) {
        switch (code) {
            case "FRAN":
                return r.getFrancaisCoef();
            case "ANGLAIS":
                return r.getAnglaisCoef();
            case "ESP":
                return r.getEspagnolCoef();
            case "PHILO":
                return r.getPhiloCoef();
            case "MATHS":
                return r.getMathsCoef();
            case "EPS":
                return r.getEpsCoef();
            case "HG":
                return r.getHistoireGeoCoef();
            case "PC":
                return r.getPhyChimieCoef();
            case "SVT":
                return r.getSvtCoef();
            case "ART":
                return r.getArtCoef();
            case "CON":
                return r.getConCoef();
            default:
                return 0.0;

        }
    }
    public Integer dataCoefCollege(RegistreCollege r, String code) {
        switch (code) {
            case "ORTH":
                return r.getOrthographeCoef();
            case "EXP.ECR.":
                return r.getExpressionEcriteCoef();
            case "ANGLAIS":
                return r.getAnglaisCoef();
            case "MATHS":
                return r.getMathsCoef();
            case "EPS":
                return r.getEpsCoef();
            case "HG":
                return r.getHistoireGeoCoef();
            case "PC":
                return r.getPhyChimieCoef();
            case "SVT":
                return r.getSvtCoef();
            case "IC":
                return r.getInstructionCiviqueCoef();
            case "ESP":
                return r.getEspagnolCoef();
            case "ART":
                return r.getArtCoef();
            case "CON":
                return r.getConCoef();
            default:
                return 0;

        }
    }
    
    public Double dataCollege(RegistreCollege r, String code) {
        switch (code) {
            case "ORTH":
                return r.getOrthographe();
            case "EXP.ECR.":
                return r.getExpressionEcrite();
            case "ANGLAIS":
                return r.getAnglais();
            case "MATHS":
                return r.getMaths();
            case "EPS":
                return r.getEps();
            case "HG":
                return r.getHistoireGeo();
            case "PC":
                return r.getPhyChimie();
            case "SVT":
                return r.getSvt();
            case "IC":
                return r.getInstructionCivique();
            case "ESP":
                return r.getEspagnol();
            case "ART":
                return r.getArt();
            case "CON":
                return r.getCon();
            default:
                return 0.0;

        }
    }

    public int sommeCoef(String type) {
        switch (type) {
            case "EVAL":
                return classe.getDisciplineCollection().stream()
                        .filter(d -> !d.getMatiere().getCode().equals("ART") && !d.getMatiere().getCode().equals("CON"))
                        .collect(Collectors.summingInt(d -> d.getCoefficient()));
            case "COMPO":
                return classe.getDisciplineCollection().stream()
                        .filter(d -> !d.getMatiere().getCode().equals("ART") && !d.getMatiere().getCode().equals("CON"))
                        .collect(Collectors.summingInt(d -> d.getCoefficient()));
            default:
                return classe.getDisciplineCollection().stream()
                        .collect(Collectors.summingInt(d -> d.getCoefficient()));
        }

    }

    public int sommeCoef(RegistreCollege r, String type) {
        switch (type) {
            case "EVAL":
                return classe.getDisciplineCollection().stream()
                        .filter(d -> !d.getMatiere().getCode().equals("ART") && !d.getMatiere().getCode().equals("CON"))
                        .collect(Collectors.summingInt(d -> d.getCoefficient()));
            case "COMPO":
                return classe.getDisciplineCollection().stream()
                        .filter(d -> !d.getMatiere().getCode().equals("ART") && !d.getMatiere().getCode().equals("CON"))
                        .collect(Collectors.summingInt(d -> d.getCoefficient()));
            default:
                return classe.getDisciplineCollection().stream()
                        .filter(d -> dataCollege(r, d.getMatiere().getCode()) != null)
                        .collect(Collectors.summingInt(d -> d.getCoefficient()));
        }
    }

    public void onClasseSelection(SelectEvent event) {
        Classe tmpClasse = (Classe) event.getObject();
        elevesParClasse = eleveService.listeParClasse(tmpClasse).stream().sorted().collect(Collectors.toList());
    }

    public Resultat resultatParEleveEtTrimestre(Eleve el, int trim) {
        Resultat r = resultatService.criteria()
                .eq(Resultat_.eleve, el)
                .eq(Resultat_.trimestre, trim)
                //                .eq(Resultat_.anneeAcademique, anneeAcademique)
                .getOptionalResult();
        return r;
    }

    public Discipline findDisciplineByClasseMatiere(Classe classe, Matiere matiere) {
        Optional<Discipline> disciplineTrouve = disciplineService.disciplineParMatiereEtClasse(classe, matiere);
        return disciplineTrouve.orElse(null);
    }

    //Getters and setters
    public List<Eleve> getElevesParClasse() {
        return elevesParClasse;
    }

    public void setElevesParClasse(List<Eleve> elevesParClasse) {
        this.elevesParClasse = elevesParClasse;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Integer getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(Integer trimestre) {
        this.trimestre = trimestre;
    }

    public List<Eleve> getEleves() {
        return eleves;
    }

    public void setEleves(List<Eleve> eleves) {
        this.eleves = eleves;
    }

    public Map<Integer, Double> getListRang() {
        return listRang;
    }

    public void setListRang(Map<Integer, Double> listRang) {
        this.listRang = listRang;
    }

//    public AnneeAcademique getAnneeAcademique() {
//        return anneeAcademique;
//    }
//
//    public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
//        this.anneeAcademique = anneeAcademique;
//    }
    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public List<Bulletin> getBulletins() {
        return bulletins;
    }

    public void setBulletins(List<Bulletin> bulletins) {
        this.bulletins = bulletins;
    }

    public List<Resultat> getResultats() {
        return resultats;
    }

    public List<RegistreCollege> getRegistresEval() {
        return registresEval;
    }

    public void setRegistresEval(List<RegistreCollege> registresEval) {
        this.registresEval = registresEval;
    }

    public List<RegistreCollege> getRegistresCompo() {
        return registresCompo;
    }

    public void setRegistresCompo(List<RegistreCollege> registresCompo) {
        this.registresCompo = registresCompo;
    }

    public List<RegistreCollege> getRegistresTrim() {
        return registresTrim;
    }

    public void setRegistresTrim(List<RegistreCollege> registresTrim) {
        this.registresTrim = registresTrim;
    }

    public List<Bulletin> getBulletinsParDisciplineTrimestreOrdreEleve() {
        return bulletinsParDisciplineTrimestreOrdreEleve;
    }

    public void setBulletinsParDisciplineTrimestreOrdreEleve(List<Bulletin> bulletinsParDisciplineTrimestreOrdreEleve) {
        this.bulletinsParDisciplineTrimestreOrdreEleve = bulletinsParDisciplineTrimestreOrdreEleve;
    }

    public Resultat getResultat() {
        return resultat;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getProgressBulletin() {
        return progressBulletin;
    }

    public void setProgressBulletin(Integer progressBulletin) {
        this.progressBulletin = progressBulletin;
    }

}
