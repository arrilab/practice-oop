package org.example.s7_mvc.p3_controller;

import org.example.s7_mvc.p2_handler.t1_simple.Controller;
import org.example.s7_mvc.p4_repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCreateController implements Controller {

    private static final Logger log = LoggerFactory.getLogger(UserCreateController.class);

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("handleRequest");

        var user = new User(
                request.getParameter("userId"),
                request.getParameter("name")
        );
        UserRepository.save(user);

        return "redirect:/users";
    }
}