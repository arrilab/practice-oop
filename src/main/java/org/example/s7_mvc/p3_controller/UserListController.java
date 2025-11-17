package org.example.s7_mvc.p3_controller;

import org.example.s7_mvc.p2_handler.t1_simple.Controller;
import org.example.s7_mvc.p4_repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

public class UserListController implements Controller {

    private static final Logger log = LoggerFactory.getLogger(UserListController.class);

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("handleRequest");

        Collection<User> users = UserRepository.findAll();
        request.setAttribute("users", users);

        return "user/list";
    }
}