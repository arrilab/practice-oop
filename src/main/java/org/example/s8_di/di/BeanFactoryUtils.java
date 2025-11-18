package org.example.s8_di.di;

import org.example.s8_di.annotation.Inject;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.util.Set;

public final class BeanFactoryUtils {

    private BeanFactoryUtils() {
    }

    /**
     * 클래스에 선언된 @Inject 생성자 검색
     *
     * @return @Inject 생성자가 존재하면 그 생성자, 없으면 null
     */
    public static Constructor<?> getInjectedConstructor(Class<?> clazz) {
        // @Inject 생성자 검색
        Set<Constructor> constructors = ReflectionUtils.getAllConstructors(
                clazz,
                ReflectionUtils.withAnnotation(Inject.class)
        );

        // @Inject 생성자 없음
        if (constructors.isEmpty()) {
            return null;
        }

        // @Inject 생성자 여러개 (현재는 첫 번째만 사용)
        return constructors.iterator().next();
    }
}
