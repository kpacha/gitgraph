package com.github.kpacha.gitgraph.service.impl;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.Repository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GitHubServiceTest {

    private static final String USER = "twitter";
    private static final String REPO_NAME = "bootstrap";

    private GitHubService service;

    @Before
    public void setUp() {
	service = GitHubService.getInstance();
    }

    @Test
    public void testSingletonBehaviour() {
	Assert.assertSame(service, GitHubService.getInstance());
    }

    @Test
    public void getDefaultRepositoryList() throws IOException, ServiceException {
	List<Repository> repos = service.getRepositories(USER);
	Assert.assertTrue(0 < repos.size());
	for (Repository repo : repos) {
	    Assert.assertNotNull(repo.getName());
	}
    }

    @Test
    public void getRepository() throws IOException, ServiceException {
	Repository repo = service.getRepository(USER, REPO_NAME);
	Assert.assertNotNull(repo);
	Assert.assertEquals(REPO_NAME, repo.getName());
    }

    @Test
    public void getRepositoryReturnsNullIfNotFound() throws IOException,
	    ServiceException {
	String user = "RandomUserNickName";
	String repoName = "NotARealRepositoryName";
	Repository repo = service.getRepository(user, repoName);
	Assert.assertNull(repo);
    }
}
