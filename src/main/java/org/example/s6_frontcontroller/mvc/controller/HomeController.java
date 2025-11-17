package org.example.s6_frontcontroller.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController implements Controller {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("handleRequest");

        return "/WEB-INF/views/home.jsp";
    }
}