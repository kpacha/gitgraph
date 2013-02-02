package com.github.kpacha.gitgraph.service.impl;

/**
 * Simple generic exception for the service layer
 * 
 * @author kpacha
 */
public class ServiceException extends Exception {

    private static final long serialVersionUID = 4580361437436012839L;

    public ServiceException(String message) {
	super(message);
    }

}
