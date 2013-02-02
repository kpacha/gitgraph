package com.github.kpacha.gitgraph.service.impl;

import junit.framework.Assert;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.User;
import org.junit.Test;

import com.github.kpacha.gitgraph.model.GitHubRepo;
import com.github.kpacha.gitgraph.service.IGitHubRepoAdapter;

/**
 * Simple adapter test
 * 
 * @author kpacha
 */
public class GitHubRepoAdapterTest {

    private IGitHubRepoAdapter adapter = new GitHubRepoAdapter();

    @Test(expected = ServiceException.class)
    public void adapterRejectsNullRepository() throws ServiceException {
	adapter.getGitHubRepo(null);
	Assert.fail("Exception expected!");
    }

    @Test
    public void checkMainProperties() throws ServiceException {
	Repository repo = new Repository();
	repo.setName("sampleName");
	User owner = new User();
	owner.setLogin("ownerName");
	repo.setOwner(owner);
	GitHubRepo gitHubRepo = adapter.getGitHubRepo(repo);
	Assert.assertEquals(repo.getName(), gitHubRepo.getName());
	Assert.assertEquals(repo.getOwner().getLogin(), gitHubRepo.getOwnerId());
    }
}
