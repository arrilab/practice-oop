package org.example.s6_frontcontroller.mvc;

import org.example.s6_frontcontroller.mvc.controller.Controller;
import org.example.s6_frontcontroller.mvc.controller.HomeController;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping {

    private Map<String, Controller> map = new HashMap<>();

    public void init() {
        map.put("/", new HomeController());
    }

    public Controller findHandler(String requestURI) {
        System.out.println("requestURI = " + requestURI);
        return map.get(requestURI);
    }
}
