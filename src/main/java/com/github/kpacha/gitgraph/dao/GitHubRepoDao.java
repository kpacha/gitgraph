package com.github.kpacha.gitgraph.dao;

import java.util.Date;
import java.util.zip.CRC32;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.kpacha.gitgraph.model.GitHubRepo;
import com.google.appengine.api.datastore.KeyFactory;

/**
 * The GitHubRepo Dao. It extends the AbstractDao
 * 
 * @see AbstractDao
 * @author kpacha
 */
public class GitHubRepoDao extends AbstractDao<GitHubRepo> {

    private static final Logger log = LoggerFactory
	    .getLogger(GitHubRepoDao.class);

    /**
     * The constructor sets up the entity class
     */
    public GitHubRepoDao() {
	log.debug("Creating new GitHubRepoDao");
	setEntityClass(GitHubRepo.class.getSimpleName());
    }

    /**
     * Create a new GitHubRepo
     * 
     * @param gitHubRepo
     */
    public void create(final GitHubRepo gitHubRepo) {
	log.debug("Creating new GitHubRepo from " + gitHubRepo);
	long created = (new Date()).getTime();
	final CRC32 crc32 = new CRC32();
	crc32.reset();
	crc32.update((int) created);
	gitHubRepo.setRepoId(Long.toString(created)
		+ Long.toString(crc32.getValue()));
	gitHubRepo.setKey(KeyFactory.createKey(
		GitHubRepo.class.getSimpleName(), gitHubRepo.getRepoId()));
	gitHubRepo.setFirstScanedAt(created);
	save(gitHubRepo);
    }

    @Override
    public void save(final GitHubRepo gitHubRepo) {
	log.debug("Saving the GitHubRepo " + gitHubRepo + " ["
		+ gitHubRepo.getKey() + ", " + gitHubRepo.getRepoId()
		+ "] named " + gitHubRepo.getName());
	gitHubRepo.setLastScanedAt((new Date()).getTime());
	super.save(gitHubRepo);
    }
}
