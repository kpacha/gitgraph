package com.github.kpacha.gitgraph.service;

import org.eclipse.egit.github.core.Repository;

import com.github.kpacha.gitgraph.model.GitHubRepo;
import com.github.kpacha.gitgraph.service.impl.ServiceException;

public interface IGitHubRepoAdapter {

    /**
     * Create a new GitHubRepo from a Repository
     * 
     * @param repo
     * @return
     * @throws ServiceException
     */
    public abstract GitHubRepo getGitHubRepo(Repository repo)
	    throws ServiceException;

}