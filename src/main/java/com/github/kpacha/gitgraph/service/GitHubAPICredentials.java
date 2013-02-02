package com.github.kpacha.gitgraph.service;

import java.util.ResourceBundle;

/**
 * Simple property reader for the GitHub API credentials
 * 
 * @author kpacha
 */
public class GitHubAPICredentials {

    private static final GitHubAPICredentials INSTANCE = new GitHubAPICredentials();

    /**
     * Get the property value as a string
     * 
     * @param name
     * @return
     */
    public static String getConfigStringValue(final String name) {
	return INSTANCE.resourceBundle.getString(name);
    }

    private ResourceBundle resourceBundle;

    /**
     * Set up the resource bundle
     */
    private GitHubAPICredentials() {
	resourceBundle = ResourceBundle.getBundle("GitHubAPICredentials");
    }
}
