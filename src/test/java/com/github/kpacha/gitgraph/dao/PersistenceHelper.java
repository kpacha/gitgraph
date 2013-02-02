package com.github.kpacha.gitgraph.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.easymock.EasyMock;

import com.github.kpacha.gitgraph.model.GitHubRepo;

/**
 * Simple helper for the persistence layer tests
 * 
 * @author kpacha
 */
public class PersistenceHelper {

    /**
     * Get a mocked instance of an EntityManagerFactory with a stubbed method
     * returning the EntityManager set-up on init
     * 
     * @param entityManager
     * @param times
     * @return
     */
    public static EntityManagerFactory getMockedEMF(
	    final EntityManager entityManager, final int times) {
	EntityManagerFactory entityManagerFactory = EasyMock
		.createMock(EntityManagerFactory.class);
	EasyMock.expect(entityManagerFactory.createEntityManager())
		.andReturn(entityManager).times(times);
	EasyMock.replay(entityManagerFactory);
	return entityManagerFactory;
    }

    /**
     * Get a mocked instance of an EntityManager with a stubbed method returning
     * the Query set-up on init
     * 
     * @param query
     * @return
     */
    public static EntityManager getMockedEM(final Query query) {
	EntityManager entityManager = EasyMock.createMock(EntityManager.class);
	EasyMock.expect(
		entityManager.createQuery(EasyMock.anyObject(String.class)))
		.andReturn(query);
	entityManager.close();
	EasyMock.expectLastCall();
	EasyMock.replay(entityManager);
	return entityManager;
    }

    /**
     * Get a mocked Query with a stubbed method returning the entity set-up on
     * init
     * 
     * @param entity
     * @return
     */
    public static Query getMockedGetByIdQuery(GitHubRepo entity) {
	Query query = EasyMock.createMock(Query.class);
	EasyMock.expect(
		query.setParameter(EasyMock.matches("id"),
			EasyMock.matches(entity.getId()))).andReturn(query);
	EasyMock.expect(query.getSingleResult()).andReturn(entity);
	EasyMock.replay(query);
	return query;
    }

    /**
     * Get a mocked Query with a stubbed method returning the list set-up on
     * init
     * 
     * @param entities
     * @return
     */
    public static Query getMockedGetAll(List<GitHubRepo> entities) {
	Query query = EasyMock.createMock(Query.class);
	EasyMock.expect(query.getResultList()).andReturn(entities);
	EasyMock.replay(query);
	return query;
    }
}
