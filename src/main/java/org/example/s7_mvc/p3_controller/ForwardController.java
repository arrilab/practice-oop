package org.example.s7_mvc.p3_controller;

import org.example.s7_mvc.p2_handler.t1_simple.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardController implements Controller {
    private final String path;

    public ForwardController(String path) {
        this.path = path;
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return path;
    }
}
