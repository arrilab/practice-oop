package org.example.s7_mvc.p2_handler.core;

public interface HandlerMapping {
    void init();

    Object findHandler(HandlerKey key);
}
