package com.github.adminfaces.starter.app;


import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.service.EleveService;
import com.github.adminfaces.starter.service.NiveauService;
import jakarta.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
@ApplicationScope
@Log4j2
public class AppDataCollege implements Serializable {
    private final EleveService eleveService;
    private final NiveauService niveauService;

    private SimpleDateFormat format;
    @Getter @Setter
    private Long nombreEleve;
    @Getter @Setter
    private Long nombreEleveF;
    @Getter @Setter
    private Long nombreEleveM;

    private AnneeAcademique annee;

    public AppDataCollege(NiveauService niveauService, EleveService eleveService) {
        this.niveauService = niveauService;
        this.eleveService = eleveService;
    }

    @PostConstruct
    public void init() {
        log.info("Init...");
//        update(anneeAcademiqueService.anneeEnCours());
    }
//
//    public void update(AnneeAcademique anneeAcademique) {
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "Debut - INIT ");
//        format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
//        annee = anneeAcademique;
//        nombreEleve = eleveService.nombreEleve(anneeAcademique);
//        nombreEleveF = eleveService.nombreEleveParSexe(anneeAcademique,"F");
//        nombreEleveM = eleveService.nombreEleveParSexe(anneeAcademique,"M");
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "TOTAL ELEVE : {0}", nombreEleve);
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "TOTAL ELEVE(F) : {0}", nombreEleveF);
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "TOTAL ELEVE(M) : {0}", nombreEleveM);
//    }
//
    //SIXIEME
    @Bean("nombreEleveSixieme")
    public Long nombreEleveSixieme() {
        log.info("Debut - 6e");
        Long nombre = eleveService.findByAnneeAndNiveau(annee, "6e");
        log.info("Fin - 6e");
        return nombre;
    }

    @Bean("listOfNiveau")
    public List<String> cycles() {
        return niveauService.findAllCode();
    }
//
//    @Bean
//    @Named("nombreEleveSixiemeFille")
//    public Long nombreEleveSixiemeFille() {
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "Debut - 6e - Fille");
//        Long nombre = eleveService.nombreEleveParNiveauSexe(annee, "6e","F");
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "Fin - 6e - Fille");
//        return nombre;
//    }
//
//    @Bean
//    @Named("nombreEleveSixiemeGarcon")
//    public Long nombreEleveSixiemeGarcon() {
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "Debut - 6e - Garcon");
//        Long nombre = eleveService.nombreEleveParNiveauSexe(annee, "6e","M");
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "Fin - 6e - Garcon");
//        return nombre;
//    }
//
//    //CINQUIEME
//    @Bean
//    @Named("nombreEleveCinquieme")
//    public Long nombreEleveCinquieme() {
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "Debut - 5e");
//        Long nombre = eleveService.nombreEleveParNiveau(annee, "5e");
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "Fin - 5e");
//        return nombre;
//    }
//
//    @Bean
//    @Named("nombreEleveCinquiemeFille")
//    public Long nombreEleveCinquiemeFille() {
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "Debut - 5e - Fille");
//        Long nombre = eleveService.nombreEleveParNiveauSexe(annee, "5e","F");
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "Fin - 5e - Fille");
//        return nombre;
//    }
//
//    @Bean
//    @Named("nombreEleveCinquiemeGarcon")
//    public Long nombreEleveCinquiemeGarcon() {
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "Debut - 5e - Garcon");
//        Long nombre = eleveService.nombreEleveParNiveauSexe(annee, "5e","M");
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "Fin - 5e - Garcon");
//        return nombre;
//    }
//
//    //QUATRIEME
//    @Bean
//    @Named("nombreEleveQuatrieme")
//    public Long nombreEleveQuatrieme() {
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "Debut - 4e");
//        Long nombre = eleveService.nombreEleveParNiveau(annee, "4e");
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "Fin - 4e");
//        return nombre;
//    }
//
//    @Bean
//    @Named("nombreEleveQuatriemeFille")
//    public Long nombreEleveQuatriemeFille() {
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "Debut - 4e - Fille");
//        Long nombre = eleveService.nombreEleveParNiveauSexe(annee, "4e","F");
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "Fin - 4e - Fille");
//        return nombre;
//    }
//
//    @Bean
//    @Named("nombreEleveQuatriemeGarcon")
//    public Long nombreEleveQuatriemeGarcon() {
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "Debut - 4e - Garcon");
//        Long nombre = eleveService.nombreEleveParNiveauSexe(annee, "4e","M");
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "Fin - 4e - Garcon");
//        return nombre;
//    }
//
//    //TROISIEME
//    @Bean
//    @Named("nombreEleveTroisieme")
//    public long nombreEleveTroisieme() {
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "Debut - 3e");
//        Long nombre = eleveService.nombreEleveParNiveau(annee, "3e");
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "Fin - 3e");
//        return nombre;
//    }
//
//    @Bean
//    @Named("nombreEleveTroisiemeFille")
//    public long nombreEleveTroisiemeFille() {
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "Debut - 3e - Fille");
//        Long nombre = eleveService.nombreEleveParNiveauSexe(annee, "3e","F");
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "Fin - 3e - Fille");
//        return nombre;
//    }
//
//    @Bean
//    @Named("nombreEleveTroisiemeGarcon")
//    public long nombreEleveTroisiemeGarcon() {
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "Debut - 3e - Garcon");
//        Long nombre = eleveService.nombreEleveParNiveauSexe(annee, "3e","M");
//        Logger.getLogger(getClass().getName()).log(Level.INFO, "Fin - 3e - Garcon");
//        return nombre;
//    }
}