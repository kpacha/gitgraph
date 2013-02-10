package com.github.kpacha.gitgraph.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.kpacha.gitgraph.web.controller.GitHubController;

/**
 * Ridiculous servlet to dispatch the http request from the users to the
 * controller
 * 
 * @author kpacha
 */
public class IndexServlet extends HttpServlet {

    private static final long serialVersionUID = 9163511149703583275L;

    private static final Logger log = LoggerFactory
	    .getLogger(IndexServlet.class);

    private GitHubController controller = new GitHubController();

    @Override
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

	log.debug("doGet");

	String view = null;
	if (request.getParameter("id") != null
		&& !request.getParameter("id").trim().equals("")) {
	    // get by id
	    view = controller.getById(request);
	} else {
	    @SuppressWarnings("unchecked")
	    String[] parameter = controller.extractSearchParameter(request
		    .getParameterMap());
	    String property = parameter[0];
	    String value = parameter[1];
	    if (property != null && value != null) {
		// get all by a parameter
		view = controller.getAllBy(request);
	    } else {
		// get all
		view = controller.home(request);
	    }
	}

	// delegate the rendering to the jsp view
	forward(request, response, view);
    }

    @Override
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * Forwards request and response to given path. Handles any exceptions
     * caused by forward target by printing them to logger.
     * 
     * @param request
     * @param response
     * @param path
     */
    protected void forward(HttpServletRequest request,
	    HttpServletResponse response, String path) {
	try {
	    RequestDispatcher rd = request.getRequestDispatcher(path + ".jsp");
	    rd.forward(request, response);
	} catch (Throwable tr) {
	    if (log.isErrorEnabled()) {
		log.error("Cought Exception: " + tr.getMessage());
		log.debug("StackTrace:", tr);
	    }
	}
    }
}
