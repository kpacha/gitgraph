package com.github.kpacha.gitgraph.service;

import java.util.ResourceBundle;

public class GitHubAPICredentials {

    private static final GitHubAPICredentials INSTANCE = new GitHubAPICredentials();

    public static int getConfigIntValue(String name) {
	return Integer.parseInt(getConfigStringValue(name));
    }

    public static String getConfigStringValue(final String name) {
	return INSTANCE.resourceBundle.getString(name);
    }

    private ResourceBundle resourceBundle;

    private GitHubAPICredentials() {
	resourceBundle = ResourceBundle
		.getBundle("com/github/kpacha/gitgraph/service/GitHubAPICredentials");
    }
}
