package com.github.adminfaces.starter.bean;

import com.github.adminfaces.persistence.service.CrudService;
import com.github.adminfaces.persistence.service.Service;
import com.github.adminfaces.starter.model.Car;
import com.github.adminfaces.starter.model.Car_;
import com.github.adminfaces.starter.model.Eleve;
import com.github.adminfaces.starter.model.Niveau;
import com.github.adminfaces.starter.model.Niveau_;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.hibernate.annotations.common.util.impl.Log_$logger;

@Named
@ApplicationScoped
public class Lists implements Serializable {

    @Inject
    @Service
    private CrudService<Eleve, Integer> crudService;
    @Inject
    @Service
    private CrudService<Niveau, Integer> niveauCrudService;

    private SimpleDateFormat format;
    private List<Eleve> eleves;
    private Integer nombreEleveF;
    private Integer nombreEleveM;

    @PostConstruct
    public void init() {
        format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        eleves = crudService.criteria().getResultList();
        
        nombreEleveF = eleves.stream().filter(e -> e.getSexe().equals("F"))
                .collect(Collectors.counting()).intValue();
        nombreEleveM = eleves.stream().filter(e -> e.getSexe().equals("M"))
                .collect(Collectors.counting()).intValue();
        
        Logger.getGlobal().log(Level.INFO, "TOTAL ELEVE : " + eleves.size());
        Logger.getGlobal().log(Level.INFO, "TOTAL ELEVE(F) : " + nombreEleveF);
        Logger.getGlobal().log(Level.INFO, "TOTAL ELEVE(M) : " + nombreEleveM);
    }

    //SIXIEME
    @Produces
    @Named("nombreEleveSixieme")
    public Integer nombreEleveSixieme() {
        Integer nombre = eleves.stream().filter(e -> e.getClasse().getNiveau().getTitre().equals("6e"))
                .collect(Collectors.counting()).intValue();
        return nombre;
    }

    @Produces
    @Named("nombreEleveSixiemeFille")
    public Integer nombreEleveSixiemeFille() {
        Integer nombre = eleves.stream().filter(e -> e.getClasse().getNiveau().getTitre().equals("6e") & e.getSexe().equals("F"))
                .collect(Collectors.counting()).intValue();
        return nombre;
    }

    @Produces
    @Named("nombreEleveSixiemeGarcon")
    public Integer nombreEleveSixiemeGarcon() {
        Integer nombre = eleves.stream().filter(e -> e.getClasse().getNiveau().getTitre().equals("6e") & e.getSexe().equals("M"))
                .collect(Collectors.counting()).intValue();
        return nombre;
    }
    
    //CINQUIEME
    @Produces
    @Named("nombreEleveCinquieme")
    public Integer nombreEleveCinquieme() {
        Integer nombre = eleves.stream().filter(e -> e.getClasse().getNiveau().getTitre().equals("5e"))
                .collect(Collectors.counting()).intValue();
        return nombre;
    }

    @Produces
    @Named("nombreEleveCinquiemeFille")
    public Integer nombreEleveCinquiemeFille() {
        Integer nombre = eleves.stream().filter(e -> e.getClasse().getNiveau().getTitre().equals("5e") & e.getSexe().equals("F"))
                .collect(Collectors.counting()).intValue();
        return nombre;
    }

    @Produces
    @Named("nombreEleveCinquiemeGarcon")
    public Integer nombreEleveCinquiemeGarcon() {
        Integer nombre = eleves.stream().filter(e -> e.getClasse().getNiveau().getTitre().equals("5e") & e.getSexe().equals("M"))
                .collect(Collectors.counting()).intValue();
        return nombre;
    }
    
    //QUATRIEME
    @Produces
    @Named("nombreEleveQuatrieme")
    public Integer nombreEleveQuatrieme() {
        Integer nombre = eleves.stream().filter(e -> e.getClasse().getNiveau().getTitre().equals("4e"))
                .collect(Collectors.counting()).intValue();
        return nombre;
    }

    @Produces
    @Named("nombreEleveQuatriemeFille")
    public Integer nombreEleveQuatriemeFille() {
        Integer nombre = eleves.stream().filter(e -> e.getClasse().getNiveau().getTitre().equals("4e") & e.getSexe().equals("F"))
                .collect(Collectors.counting()).intValue();
        return nombre;
    }

    @Produces
    @Named("nombreEleveQuatriemeGarcon")
    public Integer nombreEleveQuatriemeGarcon() {
        Integer nombre = eleves.stream().filter(e -> e.getClasse().getNiveau().getTitre().equals("4e") & e.getSexe().equals("M"))
                .collect(Collectors.counting()).intValue();
        return nombre;
    }
    
    //TROISIEME
    @Produces
    @Named("nombreEleveTroisieme")
    public Integer nombreEleveTroisieme() {
        Integer nombre = eleves.stream().filter(e -> e.getClasse().getNiveau().getTitre().equals("3e"))
                .collect(Collectors.counting()).intValue();
        return nombre;
    }

    @Produces
    @Named("nombreEleveTroisiemeFille")
    public Integer nombreEleveTroisiemeFille() {
        Integer nombre = eleves.stream().filter(e -> e.getClasse().getNiveau().getTitre().equals("3e") & e.getSexe().equals("F"))
                .collect(Collectors.counting()).intValue();
        return nombre;
    }

    @Produces
    @Named("nombreEleveTroisiemeGarcon")
    public Integer nombreEleveTroisiemeGarcon() {
        Integer nombre = eleves.stream().filter(e -> e.getClasse().getNiveau().getTitre().equals("3e") & e.getSexe().equals("M"))
                .collect(Collectors.counting()).intValue();
        return nombre;
    }
    
    
    @Produces
    @Named("cycles")
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

    public List<Eleve> getEleves() {
        return eleves;
    }

    public void setEleves(List<Eleve> eleves) {
        this.eleves = eleves;
    }

    public Integer getNombreEleveF() {
        return nombreEleveF;
    }

    public void setNombreEleveF(Integer nombreEleveF) {
        this.nombreEleveF = nombreEleveF;
    }

    public Integer getNombreEleveM() {
        return nombreEleveM;
    }

    public void setNombreEleveM(Integer nombreEleveM) {
        this.nombreEleveM = nombreEleveM;
    }

}
