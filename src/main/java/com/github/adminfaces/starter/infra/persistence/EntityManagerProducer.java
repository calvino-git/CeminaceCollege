package com.github.adminfaces.starter.infra.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class EntityManagerProducer {

    @PersistenceContext
    EntityManager em;

    @Produces
    public EntityManager produce() {
        return em;
    }
    
    public void close(@Disposes EntityManager em)
    {
        if (em.isOpen())
        {
            em.close();
        }
    }
}
