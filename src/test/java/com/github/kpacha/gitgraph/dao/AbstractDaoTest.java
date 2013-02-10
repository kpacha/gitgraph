package com.github.kpacha.gitgraph.dao;

import static com.github.kpacha.gitgraph.dao.PersistenceHelper.getMockedEM;
import static com.github.kpacha.gitgraph.dao.PersistenceHelper.getMockedEMF;
import static com.github.kpacha.gitgraph.dao.PersistenceHelper.getMockedGetAll;
import static com.github.kpacha.gitgraph.dao.PersistenceHelper.getMockedGetByIdQuery;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.kpacha.gitgraph.ModelHelper;
import com.github.kpacha.gitgraph.model.GitHubRepo;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

/**
 * Test of the AbstractDao class with the GitHubRepo class as the typed entity
 * 
 * @see AbstractDao
 * @author kpacha
 */
public class AbstractDaoTest {

    /**
     * The persistence configuration helper
     */
    private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
	    new LocalDatastoreServiceTestConfig());

    protected static AbstractDao<GitHubRepo> dao = null;

    @Before
    public void setUp() {
	helper.setUp();
	if (dao == null) {
	    dao = new GitHubRepoDao();
	}
    }

    @After
    public void tearDown() {
	helper.tearDown();
    }

    @Test
    public void testGetById() {
	GitHubRepo entity = ModelHelper.getMockedGitHubRepo();
	dao.setEntityManagerFactory(getMockedEMF(
		getMockedEM(getMockedGetByIdQuery(entity)), 1));
	Assert.assertEquals(entity, dao.getById(entity.getId()));
    }

    @Test
    public void getByIdReturnsNullIfNothingFound() {
	Query query = EasyMock.createMock(Query.class);
	EasyMock.expect(
		query.setParameter(EasyMock.matches("value"),
			EasyMock.anyObject(String.class))).andReturn(query);
	EasyMock.expect(query.getSingleResult()).andThrow(
		new NoResultException());
	EasyMock.replay(query);

	dao.setEntityManagerFactory(getMockedEMF(getMockedEM(query), 1));
	Assert.assertNull(dao.getById("unknownId"));
    }

    @Test
    public void testSave() {
	GitHubRepo entity = ModelHelper.getMockedGitHubRepo();

	EntityManager em = EasyMock.createMock(EntityManager.class);
	em.persist(EasyMock.eq(entity));
	EasyMock.expectLastCall();
	em.close();
	EasyMock.expectLastCall();
	EasyMock.replay(em);

	dao.setEntityManagerFactory(getMockedEMF(em, 1));
	dao.save(entity);

	EasyMock.verify(em);
    }

    @Test
    public void testGetAll() {
	List<GitHubRepo> entities = new ArrayList<GitHubRepo>();
	entities.add(ModelHelper.getMockedGitHubRepo());
	entities.add(ModelHelper.getMockedGitHubRepo());

	dao.setEntityManagerFactory(getMockedEMF(
		getMockedEM(getMockedGetAll(entities)), 1));
	Assert.assertEquals(entities, dao.getAll());
    }

    @Test
    public void testDeleteById() {
	GitHubRepo entity = ModelHelper.getMockedGitHubRepo();

	EntityManager em = EasyMock.createMock(EntityManager.class);
	EasyMock.expect(em.createQuery(EasyMock.anyObject(String.class)))
		.andReturn(getMockedGetByIdQuery(entity));
	em.remove(EasyMock.eq(entity));
	EasyMock.expectLastCall();
	em.close();
	EasyMock.expectLastCall().times(2);
	EasyMock.replay(em);

	dao.setEntityManagerFactory(getMockedEMF(em, 2));
	dao.deleteById(entity.getId());

	EasyMock.verify(em);
    }

}
