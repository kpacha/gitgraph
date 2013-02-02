package com.github.kpacha.gitgraph.dao;

import static com.github.kpacha.gitgraph.dao.PersistenceHelper.getMockedEMF;

import javax.persistence.EntityManager;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.kpacha.gitgraph.ModelHelper;
import com.github.kpacha.gitgraph.model.GitHubRepo;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

/**
 * Test of the GitHubRepoDao
 * 
 * @see GitHubRepoDao
 * @author kpacha
 */
public class GitHubRepoDaoTest {

    /**
     * The persistence configuration helper
     */
    private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
	    new LocalDatastoreServiceTestConfig());

    protected static GitHubRepoDao dao = null;

    @Before
    public void setUp() {
	helper.setUp();
	if (dao == null) {
	    dao = new GitHubRepoDao();
	}
	dao.setEntityManagerFactory(null);
    }

    @After
    public void tearDown() {
	helper.tearDown();
    }

    @Test
    public void testCreate() {
	GitHubRepo entity = ModelHelper.getMockedGitHubRepo();

	EntityManager em = EasyMock.createMock(EntityManager.class);
	em.persist(EasyMock.eq(entity));
	EasyMock.expectLastCall();
	em.close();
	EasyMock.expectLastCall();
	EasyMock.replay(em);

	dao.setEntityManagerFactory(getMockedEMF(em, 1));
	dao.create(entity);

	EasyMock.verify(em);

	Assert.assertNotNull(entity.getKey());
	Assert.assertNotNull(entity.getRepoId());
	Assert.assertNotNull(entity.getFirstScanedAt());
	Assert.assertNotNull(entity.getLastScanedAt());
	Assert.assertFalse(entity.getRepoId().trim().isEmpty());
	Assert.assertTrue(entity.getFirstScanedAt() > 0);
	Assert.assertTrue(entity.getLastScanedAt() > 0);
    }
}
