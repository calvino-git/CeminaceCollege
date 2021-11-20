/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.persistence.model.Filter;
import com.github.adminfaces.persistence.service.CrudService;
import com.github.adminfaces.starter.model.Classe;
import com.github.adminfaces.starter.model.Discipline;
import com.github.adminfaces.starter.model.Examen;
import com.github.adminfaces.starter.model.Examen_;
import com.github.adminfaces.starter.model.Matiere;
import com.github.adminfaces.starter.model.Examen;
import com.github.adminfaces.template.exception.BusinessException;
import org.apache.deltaspike.data.api.criteria.Criteria;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

import static com.github.adminfaces.template.util.Assert.has;

/**
 * @author rmpestano
 */
@Stateless
public class ExamenService extends CrudService<Examen, Integer> implements Serializable {

    @Inject
    protected ExamenRepository repo;//you can create repositories to extract complex queries from your service

    @Override
    public void beforeInsert(Examen examen) {
        validate(examen);
    }

    @Override
    public void beforeUpdate(Examen examen) {
        validate(examen);
    }

    public void validate(Examen examen) {
        BusinessException be = new BusinessException();
        if (!examen.hasDiscipline()) {
            be.addException(new BusinessException("La matière est obligatoire."));
        }
        if (!examen.hasDate()) {
            be.addException(new BusinessException("La date est obligatoire."));
        }
        if (!examen.hasTrimestre()) {
            be.addException(new BusinessException("La matière est obligatoire."));
        }
        if (!examen.hasType()) {
            be.addException(new BusinessException("La matière est obligatoire."));
        }
        if (count(criteria()
                .eq(Examen_.discipline, examen.getDiscipline())
                .eq(Examen_.date, examen.getDate())
                .eq(Examen_.trimestre, examen.getTrimestre())
                .eq(Examen_.type, examen.getType())
                .notEq(Examen_.id, examen.getId())) > 0) {

            be.addException(new BusinessException("L'examen de " + examen.getType()
                    + " de la classe " +examen.getDiscipline().getClasse().getLibelle() + " enseigné par  " 
                    + examen.getDiscipline().getEnseignant() + "  existe déjà."));
        }

        if (has(be.getExceptionList())) {
            throw be;
        }
    }

    public List<Examen> liste(){
        return repo.liste();
    }
    
    public List<Examen> listeParClasseMatiereTrimestre(Classe classe, Matiere matiere,int trimestre,int annee){
        return repo.listeByClasseByMatiereTrimestre(classe, matiere,trimestre,annee);
    }

    public List<Examen> listeParClasseTrimestre(Classe classe, Integer trimestre,Integer annee) {
        return repo.listeByClasseByTrimestre(classe, trimestre,annee);
    }
    
    public List<Examen> listeParClasseType(Classe classe, Integer trimestre,Integer annee,String type) {
        return repo.listeByClasseType(classe, trimestre,annee,type);
    }

    public List<Examen> listeParDisciplineTrimestreAnnee(Discipline discipline, Integer trimestre, Integer annee, String type) {
        return criteria().eq(Examen_.discipline, discipline)
                .eq(Examen_.trimestre, trimestre)
                .eq(Examen_.annee, annee)
                .eq(Examen_.type, type)
                .getResultList();
    }

}
