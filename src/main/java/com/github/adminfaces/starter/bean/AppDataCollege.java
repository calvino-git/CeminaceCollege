package com.github.adminfaces.starter.bean;

import com.github.adminfaces.persistence.service.CrudService;
import com.github.adminfaces.persistence.service.Service;
import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.model.Niveau;
import com.github.adminfaces.starter.model.Niveau_;
import com.github.adminfaces.starter.service.EleveService;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

@Named
@ApplicationScoped
public class AppDataCollege implements Serializable {

    @Inject
    private EleveService eleveService;
    @Inject
    @Service
    private CrudService<Niveau, Integer> niveauCrudService;
    private SimpleDateFormat format;
    private Long nombreEleve;
    private Long nombreEleveF;
    private Long nombreEleveM;
    private AnneeAcademique annee;

    @PostConstruct
    public void init() {
//        update(anneeAcademiqueService.anneeEnCours());
    }

    public void update(AnneeAcademique anneeAcademique) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Debut - INIT ");
        format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        annee = anneeAcademique;
        nombreEleve = eleveService.nombreEleve(anneeAcademique);
        nombreEleveF = eleveService.nombreEleveParSexe(anneeAcademique,"F");
        nombreEleveM = eleveService.nombreEleveParSexe(anneeAcademique,"M");
        Logger.getLogger(getClass().getName()).log(Level.INFO, "TOTAL ELEVE : {0}", nombreEleve);
        Logger.getLogger(getClass().getName()).log(Level.INFO, "TOTAL ELEVE(F) : {0}", nombreEleveF);
        Logger.getLogger(getClass().getName()).log(Level.INFO, "TOTAL ELEVE(M) : {0}", nombreEleveM);
    }

    //SIXIEME
    @Produces
    @Named("nombreEleveSixieme")
    public Long nombreEleveSixieme() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Debut - 6e ");
        Long nombre = eleveService.nombreEleveParNiveau(annee, "6e");
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Fin - 6e ");
        return nombre;
    }

    @Produces
    @Named("nombreEleveSixiemeFille")
    public Long nombreEleveSixiemeFille() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Debut - 6e - Fille");
        Long nombre = eleveService.nombreEleveParNiveauSexe(annee, "6e","F");
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Fin - 6e - Fille");
        return nombre;
    }

    @Produces
    @Named("nombreEleveSixiemeGarcon")
    public Long nombreEleveSixiemeGarcon() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Debut - 6e - Garcon");
        Long nombre = eleveService.nombreEleveParNiveauSexe(annee, "6e","M");
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Fin - 6e - Garcon");
        return nombre;
    }

    //CINQUIEME
    @Produces
    @Named("nombreEleveCinquieme")
    public Long nombreEleveCinquieme() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Debut - 5e");
        Long nombre = eleveService.nombreEleveParNiveau(annee, "5e");
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Fin - 5e");
        return nombre;
    }

    @Produces
    @Named("nombreEleveCinquiemeFille")
    public Long nombreEleveCinquiemeFille() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Debut - 5e - Fille");
        Long nombre = eleveService.nombreEleveParNiveauSexe(annee, "5e","F");
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Fin - 5e - Fille");
        return nombre;
    }

    @Produces
    @Named("nombreEleveCinquiemeGarcon")
    public Long nombreEleveCinquiemeGarcon() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Debut - 5e - Garcon");
        Long nombre = eleveService.nombreEleveParNiveauSexe(annee, "5e","M");
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Fin - 5e - Garcon");
        return nombre;
    }

    //QUATRIEME
    @Produces
    @Named("nombreEleveQuatrieme")
    public Long nombreEleveQuatrieme() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Debut - 4e");
        Long nombre = eleveService.nombreEleveParNiveau(annee, "4e");
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Fin - 4e");
        return nombre;
    }

    @Produces
    @Named("nombreEleveQuatriemeFille")
    public Long nombreEleveQuatriemeFille() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Debut - 4e - Fille");
        Long nombre = eleveService.nombreEleveParNiveauSexe(annee, "4e","F");
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Fin - 4e - Fille");
        return nombre;
    }

    @Produces
    @Named("nombreEleveQuatriemeGarcon")
    public Long nombreEleveQuatriemeGarcon() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Debut - 4e - Garcon");
        Long nombre = eleveService.nombreEleveParNiveauSexe(annee, "4e","M");
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Fin - 4e - Garcon");
        return nombre;
    }

    //TROISIEME
    @Produces
    @Named("nombreEleveTroisieme")
    public long nombreEleveTroisieme() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Debut - 3e");
        Long nombre = eleveService.nombreEleveParNiveau(annee, "3e");
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Fin - 3e");
        return nombre;
    }

    @Produces
    @Named("nombreEleveTroisiemeFille")
    public long nombreEleveTroisiemeFille() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Debut - 3e - Fille");
        Long nombre = eleveService.nombreEleveParNiveauSexe(annee, "3e","F");
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Fin - 3e - Fille");
        return nombre;
    }

    @Produces
    @Named("nombreEleveTroisiemeGarcon")
    public long nombreEleveTroisiemeGarcon() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Debut - 3e - Garcon");
        Long nombre = eleveService.nombreEleveParNiveauSexe(annee, "3e","M");
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Fin - 3e - Garcon");
        return nombre;
    }

    @Produces
    @Named("cyclesCollege")
    public List<String> cycles() {
        return niveauCrudService.criteria()
                .select(String.class, niveauCrudService.attribute(Niveau_.cycle))
                .getResultList();
    }

    public SimpleDateFormat getFormat() {
        return format;
    }

    public void setFormat(SimpleDateFormat format) {
        this.format = format;
    }

    public Long getNombreEleve() {
        return nombreEleve;
    }

    public void setNombreEleve(Long nombreEleve) {
        this.nombreEleve = nombreEleve;
    }

    public Long getNombreEleveF() {
        return nombreEleveF;
    }

    public void setNombreEleveF(Long nombreEleveF) {
        this.nombreEleveF = nombreEleveF;
    }

    public Long getNombreEleveM() {
        return nombreEleveM;
    }

    public void setNombreEleveM(Long nombreEleveM) {
        this.nombreEleveM = nombreEleveM;
    }
}