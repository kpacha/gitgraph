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

    private static final int MAX_RESULTS = 5;

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
    public Collection<T> getAll() {
	log.debug("Getting all the stored " + getEntityClass() + "s");
	return executeGetQuery("SELECT e FROM " + entityClass + " e ");
    }

    /**
     * Simple get all method
     * 
     * @return
     */
    public Collection<T> getAllSortedBy(final String orderBy,
	    final boolean isAsc) {
	log.debug("Getting the stored " + getEntityClass() + "s order by "
		+ orderBy);
	return executeGetQuery("SELECT e FROM " + entityClass
		+ " e ORDER BY e." + orderBy + " " + ((isAsc) ? "ASC" : "DESC"));
    }

    /**
     * Execute a get query string with the default limit
     * 
     * @param queryString
     * @return
     */
    public Collection<T> executeGetQuery(final String queryString) {
	return executeGetQuery(queryString, MAX_RESULTS);
    }

    /**
     * Execute a get query string
     * 
     * @param queryString
     * @param maxResult
     * @return
     */
    @SuppressWarnings("unchecked")
    public Collection<T> executeGetQuery(final String queryString,
	    final int maxResult) {
	log.debug("Getting the stored " + getEntityClass()
		+ "s executing the query [" + queryString + "]");
	final EntityManager entityManager = emf.createEntityManager();
	List<T> entities = new ArrayList<T>();
	try {
	    final Query query = entityManager.createQuery(queryString);
	    entities = query.setMaxResults(maxResult).getResultList();
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
	return getBy("id", id);
    }

    /**
     * Simple 'getter by' method
     * 
     * @param property
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
    public T getBy(final String property, final String value) {
	log.debug("Looking for a stored " + getEntityClass() + " with "
		+ property + "=" + value);
	final EntityManager entityManager = emf.createEntityManager();
	T entity = null;
	try {
	    final Query query = constructGetterQuery(property, value,
		    entityManager);
	    entity = (T) query.getSingleResult();
	    log.debug("Entity " + entity + " has been hidrated");
	} catch (NoResultException e) {
	    log.debug("No " + getEntityClass() + " has been found with "
		    + property + "=" + value);
	} catch (Exception e) {
	    log.error(e.getMessage() + " : " + property + "=" + value);
	} finally {
	    entityManager.close();
	}
	return entity;
    }

    /**
     * Simple 'getter by' method
     * 
     * @param property
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<T> getAllBy(final String property, final String value) {
	log.debug("Looking for all stored " + getEntityClass() + "s with "
		+ property + "=" + value);
	final EntityManager entityManager = emf.createEntityManager();
	List<T> entities = new ArrayList<T>();
	try {
	    final Query query = constructGetterQuery(property, value,
		    entityManager);
	    entities = (List<T>) query.getResultList();
	    log.debug(entities.size() + " [" + getEntityClass()
		    + "] entities has been hidrated");
	} catch (NoResultException e) {
	    log.debug("No " + getEntityClass() + " has been found with "
		    + property + "=" + value);
	} catch (Exception e) {
	    log.error(e.getMessage() + " : " + property + "=" + value);
	} finally {
	    entityManager.close();
	}
	return entities;
    }

    /**
     * Check the parameter name and value and build a query ready to be executed
     * 
     * @param property
     * @param value
     * @param entityManager
     * @return
     * @throws Exception
     */
    private Query constructGetterQuery(final String property,
	    final String value, final EntityManager entityManager)
	    throws Exception {
	if (property == null || property.trim().equals("") || value == null
		|| value.trim().equals("")) {
	    throw new Exception("Invalid property name or value");
	}
	Query query = entityManager.createQuery("SELECT e FROM " + entityClass
		+ " e WHERE e." + property + "=:value");
	query.setParameter("value", value);
	return query;
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