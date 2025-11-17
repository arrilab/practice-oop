package org.example.s7_mvc.p2_handler.t2_annotation;

import org.example.s7_mvc.p2_handler.core.HandlerKey;
import org.example.s7_mvc.p2_handler.core.HandlerMapping;
import org.example.s7_mvc.p2_handler.core.RequestMethod;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationHandlerMapping implements HandlerMapping {

    private final Object[] basePackage;
    private Map<HandlerKey, AnnotationHandler> handlers = new HashMap<>();

    public AnnotationHandlerMapping(Object... basePackage) {
        this.basePackage = basePackage;
    }

    @Override
    public void init() {
        // 리플랙션 생성
        var reflections = new Reflections(basePackage);

        // 컨트롤러 애노테이션 클래스 획득
        Set<Class<?>> clazzesWithControllerAnnotation = reflections.getTypesAnnotatedWith(Controller.class);

        clazzesWithControllerAnnotation.forEach(clazz ->
                // 리퀘스트 매핑 메서드 획득
                Arrays.stream(clazz.getDeclaredMethods()).forEach(method -> {
                    RequestMapping requestMapping = method.getDeclaredAnnotation(RequestMapping.class);

                    // 애노테이션 핸들러 등록
                    Arrays.stream(getRequestMethods(requestMapping))
                            .forEach(requestMethod -> handlers.put(
                                    new HandlerKey(requestMethod, requestMapping.value()), new AnnotationHandler(clazz, method)
                            ));
                }));
    }

    private RequestMethod[] getRequestMethods(RequestMapping requestMapping) {
        return requestMapping.method();
    }

    @Override
    public Object findHandler(HandlerKey key) {
        return handlers.get(key);
    }
}
