/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.starter.model.AnneeAcademique;
import com.github.adminfaces.starter.model.Bulletin;
import com.github.adminfaces.starter.model.Discipline;
import com.github.adminfaces.starter.model.Eleve;
import com.github.adminfaces.starter.repository.BulletinRepository;
import com.github.adminfaces.starter.repository.DisciplineRepository;
import com.github.adminfaces.starter.repository.EleveRepository;
import com.github.adminfaces.template.exception.BusinessException;

import java.util.List;

import static com.github.adminfaces.template.util.Assert.has;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author rmpestano
 */
@Service
public class BulletinService {

    private final EleveRepository eleveRepository;
    private final DisciplineRepository disciplineRepository;
    private final BulletinRepository bulletinRepository;

    public BulletinService(EleveRepository eleveRepository, DisciplineRepository disciplineRepository, BulletinRepository bulletinRepository) {
        this.eleveRepository = eleveRepository;
        this.disciplineRepository = disciplineRepository;
        this.bulletinRepository = bulletinRepository;
    }

//    @Override
//    protected Criteria<Bulletin, Bulletin> configRestrictions(Filter<Bulletin> filter) {
//
//        Criteria<Bulletin, Bulletin> bulletinCriteria = criteria();
//        Criteria<Discipline, Discipline> disciplineCriteria = new QueryCriteria(Discipline.class, Discipline.class, entityManager);
//        Criteria<Eleve, Eleve> eleveCriteria = new QueryCriteria(Eleve.class, Eleve.class, entityManager);
//        //create restrictions based on parameters map
//        if (filter.hasParam("anneeAcademique")) {
//            bulletinCriteria.eq(Bulletin_.anneeAcademique, filter.getParam("anneeAcademique", AnneeAcademique.class));
//        }
//        if (filter.hasParam("anneeAcademique")) {
//            bulletinCriteria.eq(Bulletin_.discipline, filter.getParam("discipline", Discipline.class));
//        }
//        if (filter.hasParam("trimestre")) {
//            bulletinCriteria.eq(Bulletin_.trimestre, filter.getIntParam("trimestre"));
//        }
//
//        if (filter.hasParam("discipline.classe") || filter.hasParam("discipline.matiere")) {
//            if (filter.hasParam("classe")) {
//                disciplineCriteria.eq(Discipline_.classe, filter.getParam("discipline.classe", Classe.class));
//            }
//            if (filter.hasParam("matiere")) {
//                disciplineCriteria.eq(Discipline_.matiere, filter.getParam("discipline.matiere", Matiere.class));
//            }
//            bulletinCriteria.join(Bulletin_.discipline, disciplineCriteria);
//        }
//
//        if (filter.hasParam("eleve")) {
//            eleveCriteria.likeIgnoreCase(Eleve_.nom, filter.getStringParam("eleve"));
//            eleveCriteria.likeIgnoreCase(Eleve_.prenom, filter.getStringParam("eleve"));
//            bulletinCriteria.join(Bulletin_.eleve, eleveCriteria);
//        }
////
////        if (filter.hasParam("minPrice") && filter.hasParam("maxPrice")) {
////            criteria.between(Bulletin_.price, filter.getDoubleParam("minPrice"), filter.getDoubleParam("maxPrice"));
////        } else if (filter.hasParam("minPrice")) {
////            criteria.gtOrEq(Bulletin_.price, filter.getDoubleParam("minPrice"));
////        } else if (filter.hasParam("maxPrice")) {
////            criteria.ltOrEq(Bulletin_.price, filter.getDoubleParam("maxPrice"));
////        }
////
////        //create restrictions based on filter entity
////        if (has(filter.getEntity())) {
////            Bulletin filterEntity = filter.getEntity();
////            if (has(filterEntity.getModel())) {
////                criteria.likeIgnoreCase(Bulletin_.model, "%" + filterEntity.getModel());
////            }
////
////            if (has(filterEntity.getPrice())) {
////                criteria.eq(Bulletin_.price, filterEntity.getPrice());
////            }
////
////            if (has(filterEntity.getName())) {
////                criteria.likeIgnoreCase(Bulletin_.name, "%" + filterEntity.getName() + "%");
////            }
////        }
//        return bulletinCriteria;
//    }

    public void beforeInsert(Bulletin bulletin) {
        validate(bulletin);
    }

    public void beforeUpdate(Bulletin bulletin) {
        validate(bulletin);
    }

    public void validate(Bulletin bulletin) {
        BusinessException be = new BusinessException();
        if (!has(bulletin.getDiscipline())) {
            be.addException(new BusinessException("Bulletin Discipline cannot be empty"));
        }
        if (!has(bulletin.getAnneeAcademique())) {
            be.addException(new BusinessException("Bulletin AnneeAcademique cannot be empty"));
        }

        if (!has(bulletin.getDiscipline().getMatiere())) {
            be.addException(new BusinessException("Bulletin Discipline Matiere cannot be empty"));
        }
        if (!has(bulletin.getDiscipline().getClasse())) {
            be.addException(new BusinessException("Bulletin Discipline Classe cannot be empty"));
        }

        Long count = bulletinRepository.countBulletinsByAnneeAcademiqueAndTrimestreAndEleveAndDiscipline(bulletin.getAnneeAcademique(),
                bulletin.getTrimestre(), bulletin.getEleve(), bulletin.getDiscipline());
        if (count > 0) {

            be.addException(new BusinessException("Bulletin name must be unique"));
        }

        if (has(be.getExceptionList())) {
            throw be;
        }
    }

    public List<Bulletin> listByEleve(Eleve eleve) {
        List<Bulletin> list =bulletinRepository.findBulletinsByEleve(eleve);
        return list;
    }

    public List<Bulletin> bulletinsParEleveEtTrimestre(Eleve eleve, Integer trimestre) {
        List<Bulletin> list = bulletinRepository.findBulletinsByEleveAndTrimestre(eleve, trimestre);
        return list;
    }

    public List<Bulletin> bulletinsAyantEleveSupprime() {
        return bulletinRepository.bulletinsAyantEleveSupprime();
    }
    public List<Bulletin> bulletinAyantDisciplineOrphelin() {
        return disciplineRepository.bulletinAyantDisciplineOrphelin();
    }

    public List<Bulletin> findAll(AnneeAcademique anneeAcademique) {
        return bulletinRepository.findBulletinsByAnneeAcademique(anneeAcademique);
    }

    public List<Bulletin> findByEleveAndTrimestreAndDiscipline(Eleve eleve, Integer trimestre, Discipline discipline) {
        List<Bulletin> bulletin = bulletinRepository.findBulletinsByEleveAndTrimestreAndDiscipline(eleve, trimestre, discipline);
        return bulletin;
    }

    public List<Bulletin> findByEleveAndTrimestreAndAnneeAcademique(Eleve eleve, Integer trimestre, AnneeAcademique anneeAcademique) {
        return bulletinRepository.findBulletinsByEleveAndTrimestreAndAnneeAcademique(eleve, trimestre, anneeAcademique);
    }

}
