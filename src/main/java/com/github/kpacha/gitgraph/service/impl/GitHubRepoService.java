package com.github.kpacha.gitgraph.service.impl;

import java.io.IOException;
import java.util.Collection;

import org.eclipse.egit.github.core.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.kpacha.gitgraph.dao.GitHubRepoDao;
import com.github.kpacha.gitgraph.model.GitHubRepo;
import com.github.kpacha.gitgraph.service.IGitHubRepoAdapter;

public class GitHubRepoService {

    private static final String CREDENTIALS_SEPARATOR = "/";

    private static final Logger log = LoggerFactory
	    .getLogger(GitHubRepoService.class);

    private static GitHubRepoDao dao = null;
    private static GitHubRepoService instance = null;
    private static GitHubService service = null;

    private static IGitHubRepoAdapter gitHubRepoAdapter = new GitHubRepoAdapter();

    public synchronized static GitHubRepoService getInstance() {
	log.debug("Service instance requested");
	if (instance == null) {
	    log.debug("Creating new service instance");
	    instance = new GitHubRepoService();
	}
	return instance;
    }

    private GitHubRepoService() {
	dao = new GitHubRepoDao();
    }

    public GitHubRepo get(final String id) {
	log.debug("Looking for a repo with id=" + id);
	GitHubRepo gitHubRepo = null;
	try {
	    String[] credentials = getCredentials(id);
	    gitHubRepo = get(credentials[0], credentials[1]);
	} catch (ServiceException e) {
	    log.error(e.getMessage(), e);
	}
	return gitHubRepo;
    }

    public GitHubRepo get(final String owner, final String repoName) {
	log.debug("Looking for a repo with owner=" + owner + " and name="
		+ repoName);
	GitHubRepo gitHubRepo = null;
	gitHubRepo = getGitHubRepoFromStore(owner, repoName);
	if (gitHubRepo == null) {
	    log.debug("Nothing in the store, let's ask to GitHub!");
	    gitHubRepo = getGitHubRepoFromGitHub(owner, repoName);
	}
	return gitHubRepo;
    }

    public Collection<GitHubRepo> getAll() {
	return dao.getAll();
    }

    private GitHubRepo getGitHubRepoFromStore(final String owner,
	    final String repoName) {
	log.debug("Looking for a repo in the store with owner=" + owner
		+ " and name=" + repoName);
	return dao.getById(getRepoId(owner, repoName));
    }

    private GitHubRepo getGitHubRepoFromGitHub(final String owner,
	    final String repoName) {
	log.debug("Looking for a repo in GitHub with owner=" + owner
		+ " and name=" + repoName);
	GitHubRepo gitHubRepo = null;
	try {
	    gitHubRepo = adapt(getGitHubService()
		    .getRepository(owner, repoName));
	    log.debug("Saving the GitHub repo " + gitHubRepo);
	    dao.create(gitHubRepo);
	} catch (IOException e) {
	    log.error(e.getMessage(), e);
	} catch (ServiceException e) {
	    log.error(e.getMessage(), e);
	}
	return gitHubRepo;
    }

    private final String getRepoId(final String owner, final String repoName) {
	return owner + CREDENTIALS_SEPARATOR + repoName;
    }

    private final String[] getCredentials(final String id)
	    throws ServiceException {
	log.debug("extracting credentials from: " + id);
	final String[] credentials = id.split(CREDENTIALS_SEPARATOR);
	if (credentials.length != 2) {
	    throw new ServiceException("Invalid id");
	}
	log.debug("Returning this repo credentials " + credentials);
	return credentials;
    }

    private GitHubRepo adapt(Repository repo) throws ServiceException {
	return gitHubRepoAdapter.getGitHubRepo(repo);
    }

    /**
     * Simple setter for testing purpouses
     * 
     * @param adapter
     */
    public void setGitHubRepoAdapter(final IGitHubRepoAdapter adapter) {
	this.gitHubRepoAdapter = adapter;
    }

    /**
     * Simple setter for testing purpouses
     * 
     * @param dao
     */
    public void setDao(final GitHubRepoDao dao) {
	this.dao = dao;
    }

    /**
     * Simple setter for testing purpouses
     * 
     * @param service
     */
    public void setGitHubService(final GitHubService service) {
	this.service = service;
    }

    /**
     * Lazy init for the GitHubService instance
     * 
     * @return
     */
    private synchronized GitHubService getGitHubService() {
	if (service == null) {
	    service = GitHubService.getInstance();
	}
	return service;
    }
}
