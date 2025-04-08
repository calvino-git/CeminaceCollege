/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.app;

import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.model.Classe;
import com.github.adminfaces.starter.service.ClasseService;
import com.github.adminfaces.starter.service.EleveService;
import com.github.adminfaces.starter.service.ExamenService;
import com.github.adminfaces.starter.service.NoteService;
import com.github.adminfaces.template.config.AdminConfig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.annotation.PostConstruct;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 *
 * @author calviniloki
 */
@Component
@RequestScope
public class ImportBean implements Serializable {

//    private Eleve eleve = new Eleve();
    private UploadedFile file;
    private AnneeAcademique anneeAcademique;
    private int trimestre;
    private Classe classe;
    private Map<String, List<String>> classeMap;
    private Map<String, String> eleves;
    private String nom;

    private boolean skip;

    private Integer progress;

    @Autowired
    AdminConfig adminConfig;
    @Autowired
    EleveService eleveService;
    @Autowired
    ClasseService classeService;
    @Autowired
    ExamenService examenService;
    @Autowired
    NoteService noteService;
    private int i;
    private ArrayList<String> selectListOfClasse;
    private String selectedClasse;
//    private XSSFWorkbook workbk;

    @PostConstruct
    public void init() {
        classeMap = new HashMap<>();
        eleves = new HashMap<>();
        selectListOfClasse = new ArrayList<>();
    }
//
//    public Eleve getEleve() {
//        return eleve;
//    }
//
//    public void setUser(Eleve eleve) {
//        this.eleve = eleve;
//    }
//
//    public void save() {
//        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + eleve.getNom());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
//
//    public boolean isSkip() {
//        return skip;
//    }
//
//    public void setSkip(boolean skip) {
//        this.skip = skip;
//    }

//    public void insertData(FileUploadEvent event) throws SQLException, IOException, InvalidFormatException {
//        file = event.getFile();
//        File excelFile = new File(file.getFileName());
//
//        System.out.println(excelFile.getAbsolutePath());
//        try {
//            Files.write(excelFile.toPath(), file.getContents()).toFile();
////            FileWriter fileWriter = new FileWriter(excelFile);
////            fileWriter.flush();
////            fileWriter.close();
////            Files.write(excelFile.toPath(), excel.getContents());
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        File f = new File(file.getFileName());
//        workbk = new XSSFWorkbook(f);
//        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-hhmm");
//        System.out.println("Nombre de feuilles:" + workbk.getNumberOfSheets());
//        int i = 0;
////        Iterator<Sheet> sheets = workbk.sheetIterator();
////        while (sheets.hasNext()) {
////            XSSFSheet sheet = (XSSFSheet) sheets.next();
//        XSSFSheet sheet = workbk.getSheet(classe.getCode());
//        selectListOfClasse.clear();
//        if (sheet == null) {
//            Iterator<Sheet> sheetIterator = workbk.sheetIterator();
//            while (sheetIterator.hasNext()) {
//                XSSFSheet sheetId = (XSSFSheet) sheetIterator.next();
//                Logger.getLogger(this.getClass().getName()).log(Level.OFF, "Classe : {0}", sheetId.getSheetName());
//                selectListOfClasse.add(sheetId.getSheetName());
//            }
//
//            PrimeFaces.current().dialog().openDynamic("dialog");
//        } else {
//            System.out.println("Feuille : " + sheet.getSheetName());
//
//            Iterator rows = sheet.rowIterator();
////            List<String> list = new ArrayList<>();
//            while (rows.hasNext()) {
//                XSSFRow row = (XSSFRow) rows.next();
//                System.out.println("" + row.getRowNum() + "|" + row.getCell(0).getStringCellValue());
//                if (row.getRowNum() == 0 || row.getCell(0) == null) {
//                    System.out.println(" LIGNE " + row.getRowNum());
//                    continue;
//                }
//
//                eleves.put(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue());
////                list.add(row.getCell(0).getStringCellValue());
//                i++;
//            }
////            classeMap.put(sheet.getSheetName(), list);
//        }
//        FacesContext.getCurrentInstance().addMessage("import", new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Fichier " + file.getFileName() + " importés avec succès : " + i + " elèves de la classe " + classe));
//    }
//
//    public void insertData2(FileUploadEvent event) throws SQLException, IOException, InvalidFormatException {
//        file = event.getFile();
//        File excelFile = new File(file.getFileName());
//
//        System.out.println(excelFile.getAbsolutePath());
//        try {
//            Files.write(excelFile.toPath(), file.getContents()).toFile();
////            FileWriter fileWriter = new FileWriter(excelFile);
////            fileWriter.flush();
////            fileWriter.close();
////            Files.write(excelFile.toPath(), excel.getContents());
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        File f = new File(file.getFileName());
//        workbk = new XSSFWorkbook(f);
//        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-hhmm");
//        System.out.println("Nombre de feuilles:" + workbk.getNumberOfSheets());
//        int i = 0;
////        Iterator<Sheet> sheets = workbk.sheetIterator();
////        while (sheets.hasNext()) {
////            XSSFSheet sheet = (XSSFSheet) sheets.next();
//        workbk.forEach((sheet) -> {
//            if (sheet == null) {
//                Iterator<Sheet> sheetIterator = workbk.sheetIterator();
//                while (sheetIterator.hasNext()) {
//                    XSSFSheet sheetId = (XSSFSheet) sheetIterator.next();
//                    Logger.getLogger(this.getClass().getName()).log(Level.OFF, "Classe : {0}", sheetId.getSheetName());
//                    selectListOfClasse.add(sheetId.getSheetName());
//                }
//
//                PrimeFaces.current().dialog().openDynamic("dialog");
//            } else {
//                String code_classe = sheet.getSheetName().split("_")[0];
//                String code_matiere = sheet.getSheetName().split("_")[1];
//                String code_trimestre = sheet.getSheetName().split("_")[2];
//                System.out.println("Classe : " + code_classe);
//                System.out.println("Matiere : " + code_matiere);
//                System.out.println("Trimestre : " + code_trimestre);
//                if (trimestre == Integer.valueOf(code_trimestre)) {
//                    Optional<Classe> c = classeService.classeParAnneeEtCode(anneeAcademique, code_classe);
//                    if (c.isPresent()) {
//                        classe = c.get();
//                        Optional<Discipline> d = classe.getDisciplineCollection().stream().filter(p -> p.getMatiere().getCode().endsWith(code_matiere)).findAny();
//                        if (d.isPresent()) {
//                            System.out.println("Discipline : " + d.get());
//                            System.out.println("Feuille : " + sheet.getSheetName());
//                            List<Examen> examens = examenService.examensParClasseMatiereTrimestre(classe, d.get().getMatiere(), Integer.valueOf(trimestre));
//
//                            Iterator rows = sheet.rowIterator();
//                            while (rows.hasNext()) {
//                                XSSFRow row = (XSSFRow) rows.next();
//                                if (row.getRowNum() == 0 || row.getCell(0) == null) {
//                                    System.out.println("" + row.getRowNum() + "|" + row.getCell(0).getStringCellValue());
//                                    continue;
//                                }
//                                int id = Double.valueOf(row.getCell(0).getNumericCellValue()).intValue();
//                                Eleve eleve = eleveService.findById(id);
//
//                                if (eleve != null) {
//                                    System.out.println(id + " " + eleve);
//                                    for (Examen ex : examens) {
//                                        Double noteExamen = 0.0;
//                                        XSSFCell cell = null;
//                                        if (ex.getType().equals("INTERRO 1")) {
//                                            cell = row.getCell(2);
//                                        }
//                                        if (ex.getType().equals("INTERRO 2")) {
//                                            cell = row.getCell(3);
//                                        }
//                                        if (ex.getType().equals("EVALUATION")) {
//                                            cell = row.getCell(5);
//                                        }
//                                        if (ex.getType().equals("COMPOSITION")) {
//                                            cell = row.getCell(7);
//                                        }
//                                        if (cell != null) {
//                                            if (cell.getCellType() != CellType.BLANK) {
//                                                try {
//                                                    noteExamen = cell.getNumericCellValue();
//                                                } catch (IllegalStateException e) {
//                                                    String msg = sheet.getSheetName() + "[" + cell.getReference() + "]"
//                                                            + " Impossible de récuperer une valeur NUMERIC d'une cellule " + cell.getCellType()
//                                                            + " : " + cell.getStringCellValue();
//                                                    throw new IllegalStateException(msg);
//                                                }
//                                            } else {
//                                                noteExamen = null;
//                                            }
//
//                                            System.out.println(ex.getType() + " : " + noteExamen);
//                                            Optional<Note> noteOptional = ex.getNoteCollection().stream().filter(n -> n.getEleve().equals(eleve)).findAny();
//                                            if (noteOptional.isPresent()) {
//                                                Note note = noteOptional.get();
//                                                if (noteExamen == null) {
//                                                    note.setPresence("MALADE");
//                                                    noteExamen = 0.0;
//                                                } else if (noteExamen == 0.0) {
//                                                    note.setPresence("ABSENT");
//                                                } else {
//                                                    note.setPresence("PRESENT");
//                                                }
//                                                note.setNote(noteExamen);
//                                                Note n = noteService.update(note);
//                                            } else {
//                                                System.out.println(ex.getType() + " : " + noteExamen);
//                                            }
////                    eleves.put(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue());
////                    list.add(row.getCell(0).getStringCellValue());
////                    i++;
//                                            Comparator<Note> comp = (o1, o2) -> {
//                                                return o2.getNote().compareTo(o1.getNote());
//                                            };
//
//                                            int ii = 0;
////                            List<Note> notes = noteService.notesParExamen(ex);
//
//                                            ex.getNoteCollection().sort(comp);
//                                            for (Note n : ex.getNoteCollection()) {
//                                                n.setRang(++ii);
//                                                noteService.update(n);
//                                            }
//                                        }
//                                    }
//                                } else {
//                                    System.out.println(id + " " + row.getCell(1).getStringCellValue() + " n'existe pas dans la classe " + code_classe);
//                                }
//                            }
//
//                        } else {
//                            System.out.println("Feuille non existente : " + sheet.getSheetName());
//                        }
//                    }
//                }
////            classeMap.put(sheet.getSheetName(), list);
//            }
//        });
////        FacesContext.getCurrentInstance().addMessage("import", new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Fichier " + file.getFileName() + " importés avec succès : " + i + " elèves de la classe " + classe));
//    }
//
//    public ArrayList<String> getSelectListOfClasse() {
//        return selectListOfClasse;
//    }
//
//    public void setSelectListOfClasse(ArrayList<String> selectListOfClasse) {
//        this.selectListOfClasse = selectListOfClasse;
//    }
//
//    public void onValueChange(AjaxBehaviorEvent event) {
//        XSSFSheet sheet = workbk.getSheet(selectedClasse);
//        Iterator rows = sheet.rowIterator();
//        eleves.clear();
//        while (rows.hasNext()) {
//            XSSFRow row = (XSSFRow) rows.next();
//            System.out.println("" + row.getRowNum() + "|" + row.getCell(0).getStringCellValue());
//            if (row.getRowNum() == 0 || row.getCell(0) == null) {
//                System.out.println(" LIGNE " + row.getRowNum());
//                continue;
//            }
//
//            eleves.put(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue());
////                list.add(row.getCell(0).getStringCellValue());
//        }
//    }
//
//    public void onComplete() {
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Importation terminé avec succès"));
//    }
//
//    public void longRunning() throws InterruptedException {
//        adminConfig.setRenderAjaxStatus(false);
//        progress = 0;
//        i = 0;
//
//        eleves.forEach((name, sexe) -> {
////            String[] array = nomComplet.split(" ");
////            int t = nomComplet.lastIndexOf(" ");
////            String nom1 = nomComplet.substring(0, t);
////            String prenom1 = array[array.length - 1];
//            Eleve eleve = new Eleve(name);
//            eleve.setSexe(sexe);
//            eleve.setClasse(classe);
//            eleve.setAnneeAcademique(anneeAcademique);
//            eleveService.insert(eleve);
////            nom = nomComplet;
//            progress = 100 * (++i) / eleves.size();
//            PrimeFaces.current().ajax().update("form:nom");
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(ImportBean.class.getName()).log(Level.SEVERE, "Erreur lors de la progression de l'opération", ex);
//            }
//        });
//
//    }
//
//    public String onFlowProcess(FlowEvent event) {
//        System.out.println("Annee : " + anneeAcademique);
//        System.out.println("Classe : " + classe);
//
//        if (has(file)) {
//            System.out.println("File : " + file.getFileName());
//        }
//        if (skip) {
//            skip = false;   //reset in case user goes back
//            return "confirm";
//        } else {
//
//            return event.getNewStep();
//        }
//    }
//
//    public UploadedFile getFile() {
//        return file;
//    }
//
//    public void setFile(UploadedFile file) {
//        this.file = file;
//    }
//
//    public AnneeAcademique getAnneeAcademique() {
//        return anneeAcademique;
//    }
//
//    public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
//        this.anneeAcademique = anneeAcademique;
//    }
//
//    public Classe getClasse() {
//        return classe;
//    }
//
//    public void setClasse(Classe classe) {
//        this.classe = classe;
//    }
//
//    public Map<String, List<String>> getClasseMap() {
//        return classeMap;
//    }
//
//    public void setClasseMap(Map<String, List<String>> classeMap) {
//        this.classeMap = classeMap;
//    }
//
//    public Map<String, String> getEleves() {
//        return eleves;
//    }
//
//    public void setEleves(Map<String, String> eleves) {
//        this.eleves = eleves;
//    }
//
//    /**
//     * Get the value of progress
//     *
//     * @return the value of progress
//     */
//    public Integer getProgress() {
//        return progress;
//    }
//
//    /**
//     * Set the value of progress
//     *
//     * @param progress new value of progress
//     */
//    public void setProgress(Integer progress) {
//        this.progress = progress;
//    }
//
//    public String getNom() {
//        return nom;
//    }
//
//    public void setNom(String nom) {
//        this.nom = nom;
//    }
//
//    public String getSelectedClasse() {
//        return selectedClasse;
//    }
//
//    public void setSelectedClasse(String selectedClasse) {
//        this.selectedClasse = selectedClasse;
//    }
//
//    public int getTrimestre() {
//        return trimestre;
//    }
//
//    public void setTrimestre(int trimestre) {
//        this.trimestre = trimestre;
//    }
//
}
