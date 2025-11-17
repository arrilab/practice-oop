package org.example.s7_mvc.p3_controller;

import org.example.s7_mvc.p2_handler.core.RequestMethod;
import org.example.s7_mvc.p2_handler.t2_annotation.Controller;
import org.example.s7_mvc.p2_handler.t2_annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("handleRequest");

        return "home";
    }
}