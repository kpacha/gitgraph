package com.github.kpacha.gitgraph.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.CRC32;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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

    public Collection<GitHubRepo> getAllBy(Map<String, String> parameter) {
	return getAllBy(parameter, 2 * MAX_RESULTS);
    }

    public Collection<GitHubRepo> getAllBy(Map<String, String> parameter,
	    final int maxResult) {
	log.debug("Getting all the stored " + getEntityClass() + "s");

	StringBuffer hql = new StringBuffer("SELECT e FROM " + getEntityClass()
		+ " e ");
	boolean first = true;

	for (Entry<String, String> entry : parameter.entrySet()) {
	    if (!entry.getValue().trim().equals("")) {
		hql.append(first ? " where " : " and ");
		hql.append("e." + entry.getKey() + " = :" + entry.getKey());
		first = false;
	    }
	}

	final EntityManager entityManager = emf.createEntityManager();
	List<GitHubRepo> entities = new ArrayList<GitHubRepo>();
	try {
	    log.debug("Query: " + hql.toString());
	    final Query query = entityManager.createQuery(hql.toString());
	    for (Entry<String, String> entry : parameter.entrySet()) {
		if (!entry.getValue().trim().equals("")) {
		    query.setParameter(entry.getKey(), entry.getValue());
		}
	    }
	    log.debug("Query: " + query.toString());
	    entities = query.setMaxResults(maxResult).getResultList();
	    log.debug("Total retrieved entities: " + entities.size());
	} finally {
	    entityManager.close();
	}
	return entities;
    }
}
