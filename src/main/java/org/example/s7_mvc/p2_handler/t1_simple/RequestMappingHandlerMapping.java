package org.example.s7_mvc.p2_handler.t1_simple;

import org.example.s7_mvc.p3_controller.ForwardController;
import org.example.s7_mvc.p3_controller.UserCreateController;
import org.example.s7_mvc.p3_controller.UserListController;
import org.example.s7_mvc.p2_handler.core.HandlerKey;
import org.example.s7_mvc.p2_handler.core.HandlerMapping;
import org.example.s7_mvc.p2_handler.core.RequestMethod;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping implements HandlerMapping {

    private Map<HandlerKey, Controller> handlers = new HashMap<>();

    @Override
    public void init() {
//        map.put(new HandlerKey(RequestMethod.GET, "/"), new HomeController());

        // User
        handlers.put(new HandlerKey(RequestMethod.GET, "/user/form"), new ForwardController("user/form"));
        handlers.put(new HandlerKey(RequestMethod.POST, "/users"), new UserCreateController());
        handlers.put(new HandlerKey(RequestMethod.GET, "/users"), new UserListController());
    }

    @Override
    public Object findHandler(HandlerKey key) {
        System.out.println("HandlerKey = " + key.toString());
        return handlers.get(key);
    }
}
