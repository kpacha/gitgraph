package com.github.kpacha.gitgraph.service.impl;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.client.RequestException;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.kpacha.gitgraph.service.GitHubAPICredentials;

/**
 * The GitHubService encapsulates the GitHub API related logic
 * 
 * @author kpacha
 */
public class GitHubService {
    private static final Logger log = LoggerFactory
	    .getLogger(GitHubService.class);

    private static GitHubService instance = null;
    private static GitHubClient client = null;

    /**
     * Get the instance
     * 
     * @return
     */
    public synchronized static GitHubService getInstance() {
	log.debug("Service instance requested");
	if (instance == null) {
	    log.debug("Creating new service instance");
	    instance = new GitHubService(
		    GitHubAPICredentials.getConfigStringValue("login"),
		    GitHubAPICredentials.getConfigStringValue("password"));
	}
	return instance;
    }

    /**
     * Set up the client with the received credentials
     * 
     * @param githubUser
     * @param githubPassword
     */
    private GitHubService(String githubUser, String githubPassword) {
	log.debug("Getting the client with the received credentials");
	client = new GitHubClient();
	client.setCredentials(githubUser, githubPassword);
    }

    /**
     * Get the current quota
     * 
     * @return
     * @throws IOException
     */
    public int getGitHubQuota() throws IOException {
	return client.getRemainingRequests();
    }

    /**
     * Get all the user's repositories
     * 
     * @param gitHubUser
     * @return
     * @throws IOException
     * @throws ServiceException
     */
    public List<Repository> getRepositories(String gitHubUser)
	    throws IOException, ServiceException {
	log.debug("Looking for the " + gitHubUser + "'s repositories");
	return getRepositoryService().getRepositories(gitHubUser);
    }

    /**
     * Get a repository by owner login and repo name
     * 
     * @param gitHubUser
     * @param repoName
     * @return
     * @throws IOException
     */
    public Repository getRepository(String gitHubUser, String repoName)
	    throws IOException {
	log.debug("Looking for a " + gitHubUser + "'s repository named "
		+ repoName);
	Repository repo = null;
	try {
	    repo = getRepositoryService().getRepository(gitHubUser, repoName);
	} catch (RequestException e) {
	    log.debug("Not found!");
	}
	return repo;
    }

    /**
     * Private factory method
     * 
     * @return
     */
    private RepositoryService getRepositoryService() {
	return new RepositoryService(client);
    }
}
