package com.github.kpacha.gitgraph.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

/**
 * The persistent Repository
 * 
 * @see org.eclipse.egit.github.core.Repository
 * @author kpacha
 */
@Entity
public class GitHubRepo implements Serializable {

    private static final long serialVersionUID = 3981450556054313829L;

    @Id
    private Key key;

    private String repoId;

    private Long firstScanedAt;

    private Long lastScanedAt;

    /**
     * Repository properties
     * 
     * @see org.eclipse.egit.github.core.Repository
     */

    private boolean fork;

    private boolean hasDownloads;

    private boolean hasIssues;

    private boolean hasWiki;

    private Date createdAt;

    private Date pushedAt;

    private Date updatedAt;

    private int forks;

    private String id;

    private int openIssues;

    private int size;

    private int watchers;

    private String parentId;

    private String sourceId;

    private String cloneUrl;

    private String description;

    private String homepage;

    private String gitUrl;

    private String htmlUrl;

    private String language;

    private String masterBranch;

    private String mirrorUrl;

    private String name;

    private String sshUrl;

    private String svnUrl;

    private String url;

    private String ownerId;

    /**
     * @return the key
     */
    public Key getKey() {
	return key;
    }

    /**
     * @return the repoId
     */
    public String getRepoId() {
	return repoId;
    }

    /**
     * @return the firstScanedAt
     */
    public Long getFirstScanedAt() {
	return firstScanedAt;
    }

    /**
     * @return the lastScanedAt
     */
    public Long getLastScanedAt() {
	return lastScanedAt;
    }

    /**
     * @return the fork
     */
    public boolean isFork() {
	return fork;
    }

    /**
     * @return the hasDownloads
     */
    public boolean isHasDownloads() {
	return hasDownloads;
    }

    /**
     * @return the hasIssues
     */
    public boolean isHasIssues() {
	return hasIssues;
    }

    /**
     * @return the hasWiki
     */
    public boolean isHasWiki() {
	return hasWiki;
    }

    /**
     * @return the createdAt
     */
    public Date getCreatedAt() {
	return createdAt;
    }

    /**
     * @return the pushedAt
     */
    public Date getPushedAt() {
	return pushedAt;
    }

    /**
     * @return the updatedAt
     */
    public Date getUpdatedAt() {
	return updatedAt;
    }

    /**
     * @return the forks
     */
    public int getForks() {
	return forks;
    }

    /**
     * @return the id
     */
    public String getId() {
	return id;
    }

    /**
     * @return the openIssues
     */
    public int getOpenIssues() {
	return openIssues;
    }

    /**
     * @return the size
     */
    public int getSize() {
	return size;
    }

    /**
     * @return the watchers
     */
    public int getWatchers() {
	return watchers;
    }

    /**
     * @return the parentId
     */
    public String getParentId() {
	return parentId;
    }

    /**
     * @return the sourceId
     */
    public String getSourceId() {
	return sourceId;
    }

    /**
     * @return the cloneUrl
     */
    public String getCloneUrl() {
	return cloneUrl;
    }

    /**
     * @return the description
     */
    public String getDescription() {
	return description;
    }

    /**
     * @return the homepage
     */
    public String getHomepage() {
	return homepage;
    }

    /**
     * @return the gitUrl
     */
    public String getGitUrl() {
	return gitUrl;
    }

    /**
     * @return the htmlUrl
     */
    public String getHtmlUrl() {
	return htmlUrl;
    }

    /**
     * @return the language
     */
    public String getLanguage() {
	return language;
    }

    /**
     * @return the masterBranch
     */
    public String getMasterBranch() {
	return masterBranch;
    }

    /**
     * @return the mirrorUrl
     */
    public String getMirrorUrl() {
	return mirrorUrl;
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @return the sshUrl
     */
    public String getSshUrl() {
	return sshUrl;
    }

    /**
     * @return the svnUrl
     */
    public String getSvnUrl() {
	return svnUrl;
    }

    /**
     * @return the url
     */
    public String getUrl() {
	return url;
    }

    /**
     * @return the ownerId
     */
    public String getOwnerId() {
	return ownerId;
    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey(Key key) {
	this.key = key;
    }

    /**
     * @param repoId
     *            the repoId to set
     */
    public void setRepoId(String repoId) {
	this.repoId = repoId;
    }

    /**
     * @param firstScanedAt
     *            the firstScanedAt to set
     */
    public void setFirstScanedAt(Long firstScanedAt) {
	this.firstScanedAt = firstScanedAt;
    }

    /**
     * @param lastScanedAt
     *            the lastScanedAt to set
     */
    public void setLastScanedAt(Long lastScanedAt) {
	this.lastScanedAt = lastScanedAt;
    }

    /**
     * @param fork
     *            the fork to set
     */
    public void setFork(boolean fork) {
	this.fork = fork;
    }

    /**
     * @param hasDownloads
     *            the hasDownloads to set
     */
    public void setHasDownloads(boolean hasDownloads) {
	this.hasDownloads = hasDownloads;
    }

    /**
     * @param hasIssues
     *            the hasIssues to set
     */
    public void setHasIssues(boolean hasIssues) {
	this.hasIssues = hasIssues;
    }

    /**
     * @param hasWiki
     *            the hasWiki to set
     */
    public void setHasWiki(boolean hasWiki) {
	this.hasWiki = hasWiki;
    }

    /**
     * @param createdAt
     *            the createdAt to set
     */
    public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
    }

    /**
     * @param pushedAt
     *            the pushedAt to set
     */
    public void setPushedAt(Date pushedAt) {
	this.pushedAt = pushedAt;
    }

    /**
     * @param updatedAt
     *            the updatedAt to set
     */
    public void setUpdatedAt(Date updatedAt) {
	this.updatedAt = updatedAt;
    }

    /**
     * @param forks
     *            the forks to set
     */
    public void setForks(int forks) {
	this.forks = forks;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
	this.id = id;
    }

    /**
     * @param openIssues
     *            the openIssues to set
     */
    public void setOpenIssues(int openIssues) {
	this.openIssues = openIssues;
    }

    /**
     * @param size
     *            the size to set
     */
    public void setSize(int size) {
	this.size = size;
    }

    /**
     * @param watchers
     *            the watchers to set
     */
    public void setWatchers(int watchers) {
	this.watchers = watchers;
    }

    /**
     * @param parentId
     *            the parentId to set
     */
    public void setParentId(String parentId) {
	this.parentId = parentId;
    }

    /**
     * @param sourceId
     *            the sourceId to set
     */
    public void setSourceId(String sourceId) {
	this.sourceId = sourceId;
    }

    /**
     * @param cloneUrl
     *            the cloneUrl to set
     */
    public void setCloneUrl(String cloneUrl) {
	this.cloneUrl = cloneUrl;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
	this.description = description;
    }

    /**
     * @param homepage
     *            the homepage to set
     */
    public void setHomepage(String homepage) {
	this.homepage = homepage;
    }

    /**
     * @param gitUrl
     *            the gitUrl to set
     */
    public void setGitUrl(String gitUrl) {
	this.gitUrl = gitUrl;
    }

    /**
     * @param htmlUrl
     *            the htmlUrl to set
     */
    public void setHtmlUrl(String htmlUrl) {
	this.htmlUrl = htmlUrl;
    }

    /**
     * @param language
     *            the language to set
     */
    public void setLanguage(String language) {
	this.language = language;
    }

    /**
     * @param masterBranch
     *            the masterBranch to set
     */
    public void setMasterBranch(String masterBranch) {
	this.masterBranch = masterBranch;
    }

    /**
     * @param mirrorUrl
     *            the mirrorUrl to set
     */
    public void setMirrorUrl(String mirrorUrl) {
	this.mirrorUrl = mirrorUrl;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * @param sshUrl
     *            the sshUrl to set
     */
    public void setSshUrl(String sshUrl) {
	this.sshUrl = sshUrl;
    }

    /**
     * @param svnUrl
     *            the svnUrl to set
     */
    public void setSvnUrl(String svnUrl) {
	this.svnUrl = svnUrl;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
	this.url = url;
    }

    /**
     * @param ownerId
     *            the ownerId to set
     */
    public void setOwnerId(String ownerId) {
	this.ownerId = ownerId;
    }

}
