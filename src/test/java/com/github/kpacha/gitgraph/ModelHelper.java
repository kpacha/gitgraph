package com.github.kpacha.gitgraph;

import com.github.kpacha.gitgraph.model.GitHubRepo;

public class ModelHelper {
    public static GitHubRepo getMockedGitHubRepo() {
	return getMockedGitHubRepo("sampleOwner", "sampleRepoName");
    }

    public static GitHubRepo getMockedGitHubRepo(String owner, String repoName) {
	GitHubRepo repo = new GitHubRepo();
	repo.setId(owner + "/" + repoName);
	repo.setOwnerId(owner);
	repo.setName(repoName);
	return repo;
    }
}
