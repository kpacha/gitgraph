package com.github.kpacha.gitgraph.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.easymock.EasyMock;
import org.eclipse.egit.github.core.Repository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.kpacha.gitgraph.ModelHelper;
import com.github.kpacha.gitgraph.dao.GitHubRepoDao;
import com.github.kpacha.gitgraph.model.GitHubRepo;
import com.github.kpacha.gitgraph.service.IGitHubRepoAdapter;

/**
 * Test for the GitHubRepoService class
 * 
 * @author kpacha
 */
public class GitHubRepoServiceTest {

    private GitHubRepoService service;

    @Before
    public void setUp() {
	service = GitHubRepoService.getInstance();
    }

    @Test
    public void testSingletonBehaviour() {
	Assert.assertSame(service, GitHubRepoService.getInstance());
    }

    @Test
    public void testGetFromStoreByCredentials() {
	GitHubRepo repo = ModelHelper.getMockedGitHubRepo();
	GitHubRepoDao dao = getMockedDao(repo);
	service.setDao(dao);

	Assert.assertEquals(repo,
		service.get(repo.getOwnerId(), repo.getName()));

	EasyMock.verify(dao);
    }

    @Test
    public void testGetFromGitHubByCredentials() throws IOException,
	    ServiceException {
	Repository repository = new Repository();
	GitHubService gitHubService = getMockedGitHubService(repository);
	service.setGitHubService(gitHubService);

	GitHubRepo gitHubRepo = ModelHelper.getMockedGitHubRepo();
	IGitHubRepoAdapter adapter = getMockedAdapter(repository, gitHubRepo);
	service.setGitHubRepoAdapter(adapter);

	GitHubRepoDao dao = EasyMock.createMock(GitHubRepoDao.class);
	EasyMock.expect(dao.getById(EasyMock.anyObject(String.class)))
		.andReturn(null);
	dao.create(EasyMock.eq(gitHubRepo));
	EasyMock.expectLastCall();
	EasyMock.replay(dao);
	service.setDao(dao);

	Assert.assertEquals(gitHubRepo,
		service.get(gitHubRepo.getOwnerId(), gitHubRepo.getName()));

	EasyMock.verify(dao, gitHubService, adapter);
    }

    @Test
    public void getFromGitHubByCredentialsDoesNotPersistOnFail()
	    throws IOException, ServiceException {
	Repository repository = new Repository();
	GitHubService gitHubService = getMockedGitHubService(repository);
	service.setGitHubService(gitHubService);

	IGitHubRepoAdapter adapter = EasyMock
		.createMock(IGitHubRepoAdapter.class);
	EasyMock.expect(adapter.getGitHubRepo(EasyMock.eq(repository)))
		.andThrow(new ServiceException("Null repo received"));
	EasyMock.replay(adapter);
	service.setGitHubRepoAdapter(adapter);

	GitHubRepoDao dao = EasyMock.createMock(GitHubRepoDao.class);
	EasyMock.expect(dao.getById(EasyMock.anyObject(String.class)))
		.andReturn(null);
	EasyMock.replay(dao);
	service.setDao(dao);

	Assert.assertNull(service.get("unknownOwner", "unknownName"));

	EasyMock.verify(dao, gitHubService);
    }

    @Test
    public void testGetFromStoreById() {
	GitHubRepo repo = ModelHelper.getMockedGitHubRepo();
	GitHubRepoDao dao = getMockedDao(repo);
	service.setDao(dao);

	Assert.assertEquals(repo, service.get(repo.getId()));

	EasyMock.verify(dao);
    }

    @Test
    public void getByIdRejectsBadIds() {
	GitHubRepo repo = ModelHelper.getMockedGitHubRepo();
	repo.setId("idWithoutSlash");
	Assert.assertNull(service.get(repo.getId()));
    }

    @Test
    public void testGetAll() {
	Collection<GitHubRepo> repos = new ArrayList<GitHubRepo>();
	repos.add(ModelHelper.getMockedGitHubRepo());
	repos.add(ModelHelper.getMockedGitHubRepo());

	GitHubRepoDao dao = EasyMock.createMock(GitHubRepoDao.class);
	EasyMock.expect(dao.getAll()).andReturn(repos);
	EasyMock.replay(dao);
	service.setDao(dao);

	Assert.assertEquals(repos, service.getAll());

	EasyMock.verify(dao);
    }

    /**
     * Mock a dao for returning the set-up repo
     * 
     * @param repo
     * @return
     */
    private GitHubRepoDao getMockedDao(GitHubRepo repo) {
	GitHubRepoDao dao = EasyMock.createMock(GitHubRepoDao.class);
	EasyMock.expect(dao.getById(EasyMock.matches(repo.getId()))).andReturn(
		repo);
	EasyMock.replay(dao);
	return dao;
    }

    /**
     * Mock the adapter with a stubbed method returning a set-up GitHubRepo for
     * a given Repository
     * 
     * @param repository
     * @param gitHubRepo
     * @return
     * @throws ServiceException
     */
    private IGitHubRepoAdapter getMockedAdapter(Repository repository,
	    GitHubRepo gitHubRepo) throws ServiceException {
	IGitHubRepoAdapter adapter = EasyMock
		.createMock(IGitHubRepoAdapter.class);
	EasyMock.expect(adapter.getGitHubRepo(EasyMock.eq(repository)))
		.andReturn(gitHubRepo);
	EasyMock.replay(adapter);
	return adapter;
    }

    /**
     * Mock a service with a stubbed method returning a single repository for
     * any pair of user/repository name
     * 
     * @param repository
     * @return
     * @throws IOException
     */
    private GitHubService getMockedGitHubService(Repository repository)
	    throws IOException {
	GitHubService gitHubService = EasyMock.createMock(GitHubService.class);
	EasyMock.expect(
		gitHubService.getRepository(EasyMock.anyObject(String.class),
			EasyMock.anyObject(String.class)))
		.andReturn(repository);
	EasyMock.replay(gitHubService);
	return gitHubService;
    }
}
