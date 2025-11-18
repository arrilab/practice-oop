package org.example.s8_di.controller;

import org.example.s8_di.annotation.Controller;
import org.example.s8_di.annotation.Inject;
import org.example.s8_di.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }
}
