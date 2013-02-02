package com.github.kpacha.gitgraph.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.kpacha.gitgraph.ModelHelper;
import com.github.kpacha.gitgraph.model.GitHubRepo;
import com.github.kpacha.gitgraph.service.impl.GitHubRepoService;
import com.github.kpacha.gitgraph.service.impl.GitHubService;

/**
 * 
 * @author kpacha
 */
public class GitHubControllerTest {

    public static final Integer FAKE_QUOTA = 10;

    private static GitHubController controller = new GitHubController();
    private GitHubRepoService service = EasyMock
	    .createMock(GitHubRepoService.class);
    private GitHubService gitHubService = EasyMock
	    .createMock(GitHubService.class);
    private HttpServletRequest request = EasyMock
	    .createMock(HttpServletRequest.class);

    @Before
    public void setUp() throws IOException {
	EasyMock.reset(request);
	EasyMock.reset(service);
	EasyMock.reset(gitHubService);

	controller.setService(null);
	controller.setGitHubService(null);
    }

    @After
    public void tearDown() {
	EasyMock.verify(request);
	EasyMock.verify(service);
    }

    @Test
    public void testGetByCredentials() throws IOException {
	GitHubRepo gitHubRepo = ModelHelper.getMockedGitHubRepo();

	// Should ask for a repo to the service
	EasyMock.expect(
		service.get(EasyMock.matches(gitHubRepo.getOwnerId()),
			EasyMock.matches(gitHubRepo.getName()))).andReturn(
		gitHubRepo);
	EasyMock.replay(service);
	controller.setService(service);

	// Should ask for the owner and the repoName parameters
	EasyMock.expect(request.getParameter(EasyMock.matches("owner")))
		.andReturn(gitHubRepo.getOwnerId());
	EasyMock.expect(request.getParameter(EasyMock.matches("repoName")))
		.andReturn(gitHubRepo.getName());

	expectGetResult(gitHubRepo);
	EasyMock.replay(request);

	// Should ask for the quota to github service
	gitHubService = getMockedGitHubService(FAKE_QUOTA);
	controller.setGitHubService(gitHubService);

	// run
	Assert.assertEquals("show", controller.getByCredentials(request));

	// verify
	EasyMock.verify(gitHubService);
    }

    @Test
    public void testGetById() throws IOException {
	GitHubRepo gitHubRepo = ModelHelper.getMockedGitHubRepo();

	// Should ask for a repo to the service
	EasyMock.expect(service.get(EasyMock.matches(gitHubRepo.getId())))
		.andReturn(gitHubRepo);
	EasyMock.replay(service);
	controller.setService(service);

	// Should ask for the id parameter
	EasyMock.expect(request.getParameter(EasyMock.matches("id")))
		.andReturn(gitHubRepo.getId());

	expectGetResult(gitHubRepo);
	EasyMock.replay(request);

	// Should ask for the quota to github service
	gitHubService = getMockedGitHubService(FAKE_QUOTA);
	controller.setGitHubService(gitHubService);

	// run
	Assert.assertEquals("show", controller.getById(request));

	// verify
	EasyMock.verify(gitHubService);
    }

    @Test
    public void testGetAll() throws IOException {
	GitHubRepo gitHubRepo = ModelHelper.getMockedGitHubRepo();
	Collection<GitHubRepo> repos = new ArrayList<GitHubRepo>();
	repos.add(gitHubRepo);

	// Should ask for a repo to the service
	EasyMock.expect(service.getAll()).andReturn(repos);
	EasyMock.replay(service);
	controller.setService(service);

	// Should set the repos parameter
	request.setAttribute(EasyMock.matches("repos"), EasyMock.eq(repos));
	EasyMock.expectLastCall();
	EasyMock.replay(request);

	// run
	Assert.assertEquals("index", controller.getAll(request));
    }

    private void expectGetResult(final GitHubRepo gitHubRepo) {
	// Should set the repo parameter
	request.setAttribute(EasyMock.matches("repo"), EasyMock.eq(gitHubRepo));
	EasyMock.expectLastCall();

	// should set the quota parameter
	request.setAttribute(EasyMock.matches("quota"), EasyMock.eq(FAKE_QUOTA));
	EasyMock.expectLastCall();
    }

    private GitHubService getMockedGitHubService(Integer fakeQuota)
	    throws IOException {
	GitHubService gitHubService = EasyMock.createMock(GitHubService.class);
	EasyMock.expect(gitHubService.getGitHubQuota()).andReturn(fakeQuota);
	EasyMock.replay(gitHubService);
	return gitHubService;
    }
}
