package org.example.s7_mvc.p2_handler.t2_annotation;

import org.example.s7_mvc.p5_view.ModelAndView;
import org.example.s7_mvc.p2_handler.core.HandlerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AnnotationHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return handler instanceof AnnotationHandler;
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String handle = ((AnnotationHandler) handler).handle(request, response);
        return new ModelAndView(handle);
    }
}
