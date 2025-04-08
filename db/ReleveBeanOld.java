/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.bean;

import static com.github.adminfaces.persistence.util.Messages.addDetailMessage;
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
import com.github.adminfaces.starter.model.Registre;
import com.github.adminfaces.starter.model.Resultat;
import com.github.adminfaces.starter.model.Resultat_;
import com.github.adminfaces.starter.service.BulletinService;
import com.github.adminfaces.starter.service.DisciplineService;
import com.github.adminfaces.starter.service.EleveService;
import com.github.adminfaces.starter.service.ExamenService;
import com.github.adminfaces.starter.service.NoteService;
import com.github.adminfaces.starter.service.RegistreService;
import com.github.adminfaces.starter.service.ResultatService;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    ExamenService examenService;
    @Inject
    NoteService noteService;
    @Inject
    RegistreService registreService;
    @Inject
    EleveService eleveService;
    @Inject
    DisciplineService disciplineService;
    @Inject
    BulletinService bulletinService;
    @Inject
    ResultatService resultatService;

    //Champs necessaires
    private Classe classe;
    private Eleve eleve;
    private Matiere matiere;
    private Integer trimestre;
    private Resultat resultat;
    @NotNull(message = "Ne doit pas être vide")
    @Min(value = 2020, message = "Doit etre superieur a 2020")
    @Max(value = 2030, message = "Doit etre inferieur a 2030")
    private Integer annee;

    private List<Eleve> eleves;
    private List<Eleve> elevesParClasse;

    private List<Bulletin> bulletins;
    private List<Bulletin> releveParMatiere;

    private List<Resultat> resultats;
    private Map<Integer, Double> listRang;

    private List<Registre> registres;
    private List<Registre> registresCompo;
    private List<Registre> registresTrim;

    @PostConstruct
    public void init() {
        eleves = new ArrayList<>();
        bulletins = null;
    }

    public void genererBulletin() {
        List<Discipline> disciplines = disciplineService.criteria()
                .eq(Discipline_.classe, classe)
                .getResultList();
        disciplines.forEach(d -> {
            matiere = d.getMatiere();
            genererReleveParMatiere();
        });
        genererResultat();
        bulletins = bulletinService.bulletinParEleveEtClasse(eleve, trimestre, annee);
        Comparator<Bulletin> order = (o1, o2) -> {
            int d = o1.getDiscipline().getMatiere().getIndex() - o2.getDiscipline().getMatiere().getIndex();
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
        resultat = resultatParEleve(eleve, trimestre, annee);
    }

    public void genererResultat() {

        classe.getEleveCollection().stream().map((e) -> {
            List<Bulletin> bs = bulletinService.bulletinParEleveEtClasse(e, trimestre, annee);
            Double moyenne = 0.0;
            Integer sumCoef = 0;
            for (Bulletin b : bs) {
                moyenne += b.getMoyTrimestre() * b.getCoef();
                sumCoef += b.getCoef();
            }
            Resultat rslt = new Resultat();
            rslt.setAnnee(annee);
            rslt.setClasse(classe);
            rslt.setTrimestre(trimestre);
            rslt.setEleve(e);
            rslt.setMoyenne(moyenne / sumCoef);
            Resultat r = resultatService.exists(classe, trimestre, annee, e);
            if (r != null) {
                rslt.setId(r.getId());
            }
            return rslt;
        }).forEachOrdered((rslt) -> {
            resultatService.saveOrUpdate(rslt);
        });
        resultats = resultatService.resultatParClasse(classe, trimestre, annee);
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

        int i = 0;
        resultats.sort(comp);
        for (Resultat r : resultats) {
            r.setRang(++i);
            resultatService.update(r);
//            System.out.println(r.getEleve().toString()
//                    + " moyenne " + r.getMoyenne()
//                    + " rang " + r.getRang()
//                    + " observation " + r.getObservation());
        }

        if (resultats != null) {
            resultats.sort((o1, o2) -> {
                return o1.compareTo(o2); //To change body of generated lambdas, choose Tools | Templates.
            });
        }
    }

    public void genererReleveParMatiere() {
        Discipline discipline = findDisciplineByClasseMatiere(classe, matiere);
        if (discipline != null) {
            elevesParClasse = eleveService.listeParClasse(classe);
            List<Double> listMoy = new ArrayList<>();
            elevesParClasse.forEach(e -> {

                Note interro1 = noteExamenParEleve(e, "INTERRO 1");
                Note interro2 = noteExamenParEleve(e, "INTERRO 2");

                Double moyInterro = moyenne(interro1, interro2);

                Note eval = noteExamenParEleve(e, "EVALUATION");

                Double moyClasse = moyenne2(moyInterro, eval);

                Note compo = noteExamenParEleve(e, "COMPOSITION");

                Double moyTrimestre = moyenne2(moyClasse, compo);

                double moy = discipline
                        .getCoefficient() * moyTrimestre;
                listMoy.add(moy);
            });

            listMoy.sort((o1, o2) -> {
                return o1.compareTo(o2); //To change body of generated lambdas, choose Tools | Templates.
            });
            listRang = new HashMap();
            int i = 0;
            for (Double moy : listMoy) {
                listRang.put(++i, moy);
            }
        } else {
            addDetailMessage("La discipline " + matiere.getLibelle() + " n'existe pas en classe " + classe.getLibelle());
            return;
        }
        elevesParClasse.forEach(e -> {
            Bulletin bulletin = new Bulletin();
            bulletin.setAnnee(annee);
            bulletin.setEleve(e);
            bulletin.setDiscipline(discipline);
            bulletin.setTrimestre(trimestre);

            Note interro1 = noteExamenParEleve(e, "INTERRO 1");
            Double noteInterro1 = interro1 != null ? interro1.getNote() : 0.0;
            bulletin.setInterro1(noteInterro1);

            Note interro2 = noteExamenParEleve(e, "INTERRO 2");
            Double noteInterro2 = interro2 != null ? interro2.getNote() : 0.0;
            bulletin.setInterro2(noteInterro2);

            Double moyInterro = moyenne(interro1, interro2);
            bulletin.setMoyInterro(moyInterro);

            Note eval = noteExamenParEleve(e, "EVALUATION");
            Double noteEval = eval != null ? eval.getNote() : 0.0;
            bulletin.setEvaluation(noteEval);

            Double moyClasse = moyenne2(moyInterro, eval);
            bulletin.setMoyClasse(moyClasse);

            Note compo = noteExamenParEleve(e, "COMPOSITION");
            Double noteCompo = compo != null ? compo.getNote() : 0.0;
            bulletin.setComposition(noteCompo);

            Double moyTrimestre = moyenne2(moyClasse, compo);
            bulletin.setMoyTrimestre(moyTrimestre);

            bulletin.setMoyTrimestreCoef(bulletin.getMoyTrimestre() * discipline.getCoefficient());
            bulletin.setCoef(discipline.getCoefficient());
            Bulletin b = bulletinService.exists(e, trimestre, annee, discipline);
            if (b != null) {
                bulletin.setId(b.getId());
            }
            bulletinService.saveOrUpdate(bulletin);
        });

        List<Discipline> disciplines = disciplineService.criteria()
                .eq(Discipline_.classe, classe)
                .getResultList();
        disciplines.forEach(d -> {
            releveParMatiere = bulletinService.criteria()
                    .eq(Bulletin_.discipline, d)
                    .eq(Bulletin_.trimestre, trimestre)
                    .eq(Bulletin_.annee, annee)
                    .orderAsc(Bulletin_.eleve)
                    .getResultList();
            Comparator<Bulletin> comp = (o1, o2) -> {
                Double dd = o2.getMoyTrimestreCoef() - o1.getMoyTrimestreCoef();
                if (Math.abs(dd) > 1) {
                    return dd.intValue();
                } else {
                    if (dd > 0) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            };

            int ii = 0;
            releveParMatiere.sort(comp);
            for (Bulletin b : releveParMatiere) {
                b.setRang(++ii);
                bulletinService.update(b);
                System.out.println(b.getDiscipline().getMatiere()
                        + " moyenne " + b.getMoyTrimestreCoef()
                        + " rang " + b.getRang()
                        + " observation " + b.getObservation());
            }
        });
        releveParMatiere = bulletinService.criteria()
                .eq(Bulletin_.discipline, discipline)
                .eq(Bulletin_.trimestre, trimestre)
                .eq(Bulletin_.annee, annee)
                .getResultList()
                .stream()
                .collect(Collectors.toList());
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
            genererResultat();
        }

    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        Rectangle rectangle = new Rectangle(PageSize.A4.getTop(), PageSize.A4.getRight());
        pdf.setPageSize(rectangle);

        pdf.open();
    }
    
    
    public void exportRegistreIV() throws JRException, IOException, SQLException {
        String jasperReportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/report/REGISTRE_4.jrxml");
        jasperReportPath = JasperCompileManager.compileReportToFile(jasperReportPath);
        Map<String, Object> map = new HashMap<>();
        String anneeAcademique = (trimestre == 1?annee + "-" + (annee+1):(annee-1) + "-" + annee);
        
        map.put("annee", annee);
        map.put("annee", anneeAcademique);
        map.put("classe", classe.getCode());
        map.put(JRParameter.REPORT_LOCALE, Locale.FRANCE);
        
        Connection con = DriverManager.getConnection("jdbc:sqlite:ceminace_college.db");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReportPath, map, con);

        SimplePdfReportConfiguration configuration = new SimplePdfReportConfiguration();
        JRPdfExporter exporter = new JRPdfExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.reset();
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=\"" + "REGISTRE_IV-" + classe.getCode() + "-" + anneeAcademique + ".pdf\"");
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
        map.put("annee", annee);
        map.put("classe", classe);
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
        response.setHeader("Content-disposition", "attachment; filename=\"" + "REGISTRE " + trimestre + "Trimestre " + annee + ".pdf\"");
        ServletOutputStream out = response.getOutputStream();
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void exportComposition() throws JRException, IOException {
        List<Examen> examens = examenService.criteria()
                .eq(Examen_.trimestre, trimestre)
                .eq(Examen_.annee, annee)
                .eq(Examen_.type, "COMPOSITION")
                .getResultList();

        JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(registresCompo);

        String jasperReportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/report/composition.jrxml");
        jasperReportPath = JasperCompileManager.compileReportToFile(jasperReportPath);
        Map<String, Object> map = new HashMap<>();
        map.put("annee", annee);
        map.put("classe", classe);
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
        response.setHeader("Content-disposition", "attachment; filename=\"" + "COMPOSITION " + trimestre + "Trimestre " + annee + ".pdf\"");
        ServletOutputStream out = response.getOutputStream();
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void exportEvaluation() throws JRException, IOException {
        List<Examen> examens = examenService.criteria()
                .eq(Examen_.trimestre, trimestre)
                .eq(Examen_.annee, annee)
                .eq(Examen_.type, "EVALUATION")
                .getResultList();

        JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(registres);

        String jasperReportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/report/evaluation.jrxml");
        jasperReportPath = JasperCompileManager.compileReportToFile(jasperReportPath);
        Map<String, Object> map = new HashMap<>();
        map.put("annee", annee);
        map.put("classe", classe);
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
        response.setHeader("Content-disposition", "attachment; filename=\"" + "EVALUATION " + trimestre + "Trimestre " + annee + ".pdf\"");
        ServletOutputStream out = response.getOutputStream();
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void exportTrim() throws JRException, IOException {
        List<Examen> examens = examenService.criteria()
                .eq(Examen_.trimestre, trimestre)
                .eq(Examen_.annee, annee)
                .eq(Examen_.type, "EVALUATION")
                .getResultList();
        List<Note> notes = new ArrayList<>();
        examens.forEach(exa -> {
            notes.addAll(exa.getNoteCollection());
        });

        JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(notes);

        String jasperReportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/report/evaluation.jrxml");
        jasperReportPath = JasperCompileManager.compileReportToFile(jasperReportPath);
        Map<String, Object> map = new HashMap<>();
        map.put("annee", annee);
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

    public void updateRegistre(ActionEvent event) {
        registres = registreService.updateRegistre(classe, annee, trimestre, "EVALUATION");
        registresCompo = registreService.updateRegistre(classe, annee, trimestre, "COMPOSITION");
        registresTrim = registreService.updateRegistreTrimestre(classe, annee, trimestre);
    }

    public void onClasseSelection(SelectEvent event) {
        Classe tmpClasse = (Classe) event.getObject();
        elevesParClasse = eleveService.listeParClasse(tmpClasse).stream().sorted().collect(Collectors.toList());
    }

    public Resultat resultatParEleve(Eleve el, int trim, int an) {
        Resultat r = resultatService.criteria()
                .eq(Resultat_.eleve, el)
                .eq(Resultat_.trimestre, trim)
                .eq(Resultat_.annee, an)
                .getOptionalResult();
        return r;
    }

    public Discipline findDisciplineByClasseMatiere(Classe classe, Matiere matiere) {
        Optional<Discipline> disciplineTrouve = disciplineService.findByClasseMatiere(classe, matiere);
        return disciplineTrouve.orElse(null);
    }

    private double moyenne2(Double a, Note nb) {
        Double note = 0.0;
        if (nb != null && nb.getPresence().equals("MALADE")) {
            note = a;
        } else if (nb == null) {
            note = a;
        } else {
            note = (a + nb.getNote()) / 2;
        }
        return note;
    }

    private double moyenne(Note na, Note nb) {
        Double note = 0.0;
        if (na == null && nb == null) {
        } else if (na == null && nb != null) {
            note = nb.getNote();
        } else if (na != null && nb == null) {
            note = na.getNote();
        } else {
            if (na.getPresence().equals("MALADE")) {
                note = nb.getNote();
            } else if (nb.getPresence().equals("MALADE")) {
                note = na.getNote();
            } else {
                note = (na.getNote() + nb.getNote()) / 2;
            }
        }
        return note;
    }

    public Note noteExamenParEleve(Eleve eleve, String typeExamen) {
        Examen examen = null;
        Note note = null;
        List<Examen> listExamen = examenService.listeParClasseMatiereTrimestre(eleve.getClasse(), matiere, trimestre, annee);
        if (listExamen != null && (!listExamen.isEmpty())) {
            List<Examen> liste = listExamen.stream().filter(e -> e.getType().equalsIgnoreCase(typeExamen))
                    .collect(Collectors.toList());
            if (liste.size() == 1) {
                examen = liste.get(0);
            } else {
                System.out.println("liste examen " + liste.size());
            }
        }
        List<Note> listNote = null;
        if (examen != null) {
            listNote = noteService.listeNoteParExamenEleve(examen, eleve);
        }
        if (listNote != null && !listNote.isEmpty()) {
            note = listNote.get(0);
        }
        return note;
    }

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

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

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

    public List<Registre> getRegistres() {
        return registres;
    }

    public void setRegistres(List<Registre> registres) {
        this.registres = registres;
    }

    public List<Registre> getRegistresCompo() {
        return registresCompo;
    }

    public void setRegistresCompo(List<Registre> registresCompo) {
        this.registresCompo = registresCompo;
    }

    public List<Registre> getRegistresTrim() {
        return registresTrim;
    }

    public void setRegistresTrim(List<Registre> registresTrim) {
        this.registresTrim = registresTrim;
    }

    public List<Bulletin> getReleveParMatiere() {
        return releveParMatiere;
    }

    public void setReleveParMatiere(List<Bulletin> releveParMatiere) {
        this.releveParMatiere = releveParMatiere;
    }

    public Resultat getResultat() {
        return resultat;
    }
}
