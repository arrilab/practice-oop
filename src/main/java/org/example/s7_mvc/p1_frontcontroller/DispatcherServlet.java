package org.example.s7_mvc.p1_frontcontroller;

import org.example.s7_mvc.p2_handler.t2_annotation.AnnotationHandlerAdapter;
import org.example.s7_mvc.p2_handler.t2_annotation.AnnotationHandlerMapping;
import org.example.s7_mvc.p2_handler.t1_simple.RequestMappingHandlerMapping;
import org.example.s7_mvc.p2_handler.t1_simple.SimpleControllerHandlerAdapter;
import org.example.s7_mvc.p5_view.JpaViewResolver;
import org.example.s7_mvc.p5_view.ModelAndView;
import org.example.s7_mvc.p5_view.View;
import org.example.s7_mvc.p5_view.ViewResolver;
import org.example.s7_mvc.p2_handler.core.HandlerAdapter;
import org.example.s7_mvc.p2_handler.core.HandlerKey;
import org.example.s7_mvc.p2_handler.core.HandlerMapping;
import org.example.s7_mvc.p2_handler.core.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    private List<HandlerMapping> handlerMappings;

    private List<HandlerAdapter> handlerAdapters;
    private List<JpaViewResolver> viewResolvers;

    @Override
    public void init() throws ServletException {
        var rmhm = new RequestMappingHandlerMapping();
        rmhm.init();

        var ahm = new AnnotationHandlerMapping("org.example");
        ahm.init();

        handlerMappings = List.of(rmhm, ahm);
        handlerAdapters = List.of(new SimpleControllerHandlerAdapter(), new AnnotationHandlerAdapter());
        viewResolvers = Collections.singletonList(new JpaViewResolver());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("DispatcherServlet.service");
        try {
            String requestURI = request.getRequestURI();
            RequestMethod requestMethod = RequestMethod.valueOf(request.getMethod());
            if (requestURI.equals("/favicon.ico")) {
                return;
            }

            // 핸들러 획득
            var handlerKey = new HandlerKey(requestMethod, requestURI);
            Object handler = handlerMappings.stream()
                    .filter(hm -> hm.findHandler(handlerKey) != null)
                    .map(hm -> hm.findHandler(handlerKey))
                    .findFirst()
                    .orElseThrow(() -> new ServletException("No handler found for [" + requestMethod + ", " + requestURI + "]"));

            // 핸들러 어댑터 획득
            HandlerAdapter handlerAdapter = handlerAdapters.stream()
                    .filter(ha -> ha.supports(handler))
                    .findFirst()
                    .orElseThrow(() -> new ServletException("No handler found for " + handler));

            // 핸들러 어댑터 실행
            ModelAndView mav = handlerAdapter.handle(request, response, handler);

            // 뷰 리졸버 실행
            for (ViewResolver viewResolver : viewResolvers) {
                View view = viewResolver.resolveView(mav.getViewName());
                view.render(new HashMap<>(), request, response);
            }
        } catch (Exception e) {
            log.error("exception occurred: [{}]", e.getMessage(), e);
            throw new ServletException(e);
        }
    }
}
