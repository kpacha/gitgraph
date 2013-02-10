package com.github.kpacha.gitgraph.web.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.kpacha.gitgraph.service.impl.GitHubRepoService;
import com.github.kpacha.gitgraph.service.impl.GitHubService;

/**
 * Simple Controller for the GitHub domain
 * 
 * @author kpacha
 */
public class GitHubController {

    private static final Logger log = LoggerFactory
	    .getLogger(GitHubController.class);

    private static GitHubRepoService service;
    private GitHubService gitHubService;

    private final static String[] POSIBLE_PARAMETERS = new String[] {
	    "ownerId", "name", "language" };

    /**
     * Get repo by Id
     * 
     * @param request
     * @return
     */
    public String getById(final HttpServletRequest request) {
	String id = request.getParameter("id");
	log.debug("looking for the repo: " + id);
	request.setAttribute("repo", getService().get(id));
	try {
	    request.setAttribute("quota", getGitHubService().getGitHubQuota());
	} catch (IOException e) {
	    log.error(e.getMessage(), e);
	}
	return "show";
    }

    /**
     * Search by a parameter
     * 
     * @param request
     * @return
     */
    public String getAllBy(final HttpServletRequest request) {
	@SuppressWarnings("unchecked")
	String[] parameter = extractSearchParameter(request.getParameterMap());
	String property = parameter[0];
	String value = parameter[1];
	log.debug("looking for the repo with " + property + "=" + value);
	request.setAttribute("repos", getService().getAllBy(property, value));
	try {
	    request.setAttribute("quota", getGitHubService().getGitHubQuota());
	} catch (IOException e) {
	    log.error(e.getMessage(), e);
	}
	return "repositories";
    }

    public String[] extractSearchParameter(Map<String, String[]> parameters) {
	String[] parameter = new String[2];
	for (String posible : POSIBLE_PARAMETERS) {
	    if (parameters.containsKey(posible)) {
		if (!parameters.get(posible)[0].equals("")) {
		    parameter[0] = posible;
		    parameter[1] = parameters.get(posible)[0];
		    break;
		}
	    }
	}
	return parameter;
    }

    /**
     * Get repo by owner login and repository name
     * 
     * @param request
     * @return
     */
    public String getByCredentials(final HttpServletRequest request) {
	String owner = request.getParameter("owner");
	String repoName = request.getParameter("name");
	log.debug("looking for the repo: " + owner + "/" + repoName);
	request.setAttribute("repo", getService().get(owner, repoName));
	try {
	    request.setAttribute("quota", getGitHubService().getGitHubQuota());
	} catch (IOException e) {
	    log.error(e.getMessage(), e);
	}
	return "show";
    }

    /**
     * Get all the stored repositories
     * 
     * @param request
     * @return
     */
    public String home(final HttpServletRequest request) {
	log.debug("looking for all the stored repos");
	request.setAttribute("reposByForks", getService().getSortedBy("forks"));
	request.setAttribute("reposByWatchers",
		getService().getSortedBy("watchers"));
	request.setAttribute("reposByLastPushedAt",
		getService().getSortedBy("pushedAt"));
	return "index";
    }

    /**
     * Simple setter for testing purpouses
     * 
     * @param service
     */
    public void setService(GitHubRepoService service) {
	this.service = service;
    }

    /**
     * Simple setter for testing purpouses
     * 
     * @param service
     */
    public void setGitHubService(GitHubService gitHubService) {
	this.gitHubService = gitHubService;
    }

    /**
     * Lazy init for the GitHubService instance
     * 
     * @return
     */
    private synchronized GitHubService getGitHubService() {
	if (gitHubService == null) {
	    gitHubService = GitHubService.getInstance();
	}
	return gitHubService;
    }

    /**
     * Lazy init for the GitHubRepoService instance
     * 
     * @return
     */
    private synchronized GitHubRepoService getService() {
	if (service == null) {
	    service = GitHubRepoService.getInstance();
	}
	return service;
    }
}
