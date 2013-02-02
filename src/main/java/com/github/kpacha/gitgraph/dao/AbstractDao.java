package com.github.kpacha.gitgraph.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The abstract generic repository
 * 
 * @author kpacha
 * 
 * @param <T>
 */
public class AbstractDao<T> {
    /**
     * The logger
     */
    private static final Logger log = LoggerFactory
	    .getLogger(AbstractDao.class);

    /**
     * The entity manager factory
     */
    protected static EntityManagerFactory emf = Persistence
	    .createEntityManagerFactory("transactions-optional");

    /**
     * The class name of the extended repository
     */
    private String entityClass;

    /**
     * Set the class name of the extended repository
     * 
     * @param entityClass
     */
    protected void setEntityClass(final String entityClass) {
	log.debug("Setting the entity class name [" + entityClass + "]");
	this.entityClass = entityClass;
    }

    /**
     * Get the class name of the extended repository
     * 
     * @return
     */
    protected String getEntityClass() {
	return entityClass;
    }

    /**
     * Save entity
     * 
     * @param entity
     */
    public void save(final T entity) {
	log.debug("Saving the entity " + entity + " as a " + getEntityClass());
	final EntityManager entityManager = emf.createEntityManager();
	try {
	    entityManager.persist(entity);
	    log.debug("Entity " + entity + " saved.");
	} finally {
	    entityManager.close();
	}
    }

    /**
     * Simple get all method
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public Collection<T> getAll() {
	log.debug("Getting all the stored " + getEntityClass() + "s");
	final EntityManager entityManager = emf.createEntityManager();
	List<T> entities = new ArrayList<T>();
	try {
	    final Query query = entityManager.createQuery("SELECT e FROM "
		    + entityClass + " e");
	    entities = query.getResultList();
	    log.debug("Total retrieved entities: " + entities.size());
	} finally {
	    entityManager.close();
	}
	return entities;
    }

    /**
     * Default remove method
     * 
     * TODO: make it easier. it could be done in a single step
     * 
     * @param id
     */
    public void deleteById(final String id) {
	log.debug("Removing the stored " + getEntityClass() + " with id=" + id);
	T entity = getById(id);
	if (entity != null) {
	    final EntityManager entityManager = emf.createEntityManager();
	    try {
		log.debug("Entity " + entity + " has been hidrated");
		entityManager.remove(entity);
		log.debug("Entity " + entity + " has been removed");
	    } finally {
		entityManager.close();
	    }
	}
    }

    /**
     * Simple getter by id method
     * 
     * @param id
     */
    @SuppressWarnings("unchecked")
    public T getById(final String id) {
	log.debug("Looking for a stored " + getEntityClass() + " with id=" + id);
	final EntityManager entityManager = emf.createEntityManager();
	T entity = null;
	try {
	    final Query query = entityManager.createQuery("SELECT e FROM "
		    + entityClass + " e WHERE e.id=:id");
	    query.setParameter("id", id);
	    entity = (T) query.getSingleResult();
	    log.debug("Entity " + entity + " has been hidrated");
	} catch (NoResultException e) {
	    log.debug("No " + getEntityClass() + " has been found with id="
		    + id);
	} finally {
	    entityManager.close();
	}
	return entity;
    }

    /**
     * Public EMF setter for testing purpouses
     * 
     * @param emf
     */
    public void setEntityManagerFactory(EntityManagerFactory emf) {
	this.emf = emf;
    }
}