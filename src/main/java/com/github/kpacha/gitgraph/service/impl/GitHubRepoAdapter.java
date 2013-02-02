package com.github.kpacha.gitgraph.service.impl;

import org.eclipse.egit.github.core.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.kpacha.gitgraph.model.GitHubRepo;
import com.github.kpacha.gitgraph.service.IGitHubRepoAdapter;

/**
 * A simple adapter between GitHubRepo and Repository
 * 
 * @see GitHubRepo
 * @see Repository
 * @author kpacha
 */
public class GitHubRepoAdapter implements IGitHubRepoAdapter {

    private static final Logger log = LoggerFactory
	    .getLogger(GitHubRepoAdapter.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.github.kpacha.gitgraph.service.impl.IGitHubRepoAdapter#getGitHubRepo
     * (org.eclipse.egit.github.core.Repository)
     */
    @Override
    public final GitHubRepo getGitHubRepo(final Repository repo)
	    throws ServiceException {
	log.debug("Getting a GitHubRepo from the Respository " + repo);
	if (repo == null) {
	    throw new ServiceException("Null repo received");
	}
	final GitHubRepo gitHubRepo = new GitHubRepo();
	gitHubRepo.setCloneUrl(repo.getCloneUrl());
	gitHubRepo.setCreatedAt(repo.getCreatedAt());
	gitHubRepo.setDescription(repo.getDescription());
	gitHubRepo.setFork(repo.isFork());
	gitHubRepo.setForks(repo.getForks());
	gitHubRepo.setGitUrl(repo.getGitUrl());
	gitHubRepo.setHasDownloads(repo.isHasDownloads());
	gitHubRepo.setHasIssues(repo.isHasIssues());
	gitHubRepo.setHasWiki(repo.isHasWiki());
	gitHubRepo.setHomepage(repo.getHomepage());
	gitHubRepo.setHtmlUrl(repo.getHtmlUrl());
	gitHubRepo.setId(repo.generateId());
	gitHubRepo.setLanguage(repo.getLanguage());
	gitHubRepo.setMasterBranch(repo.getMasterBranch());
	gitHubRepo.setMirrorUrl(repo.getMirrorUrl());
	gitHubRepo.setName(repo.getName());
	gitHubRepo.setOpenIssues(repo.getOpenIssues());
	gitHubRepo.setOwnerId(repo.getOwner().getLogin());
	if (repo.getParent() != null) {
	    gitHubRepo.setParentId(repo.getParent().generateId());
	}
	gitHubRepo.setPushedAt(repo.getPushedAt());
	gitHubRepo.setSize(repo.getSize());
	if (repo.getSource() != null) {
	    gitHubRepo.setSourceId(repo.getSource().generateId());
	}
	gitHubRepo.setSshUrl(repo.getSshUrl());
	gitHubRepo.setSvnUrl(repo.getSvnUrl());
	gitHubRepo.setUpdatedAt(repo.getUpdatedAt());
	gitHubRepo.setUrl(repo.getUrl());
	gitHubRepo.setWatchers(repo.getWatchers());
	log.debug("Returning the GitHubRepo " + gitHubRepo);
	return gitHubRepo;
    }
}
