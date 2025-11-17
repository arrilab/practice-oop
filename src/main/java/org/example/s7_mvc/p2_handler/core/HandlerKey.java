package org.example.s7_mvc.p2_handler.core;

import java.util.Objects;

public class HandlerKey {
    private final RequestMethod method;
    private final String path;

    public HandlerKey(RequestMethod method, String path) {
        this.method = method;
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        HandlerKey that = (HandlerKey) o;
        return method == that.method && Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, path);
    }

    @Override
    public String toString() {
        return "HandlerKey{" +
                "method=" + method +
                ", path='" + path + '\'' +
                '}';
    }
}
