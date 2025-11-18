package org.example.s8_di.di;

import java.lang.reflect.Constructor;
import java.util.*;

public class BeanFactory {

    private final Set<Class<?>> preInstantiatedClasses;             // 사전 스캔된 빈 후보 클래스 목록
    private final Map<Class<?>, Object> beans = new HashMap<>();    // 생성된 빈 인스턴스 저장소 (Singleton registry)

    public BeanFactory(Set<Class<?>> preInstantiatedClasses) {
        this.preInstantiatedClasses = preInstantiatedClasses;
        initialize();
    }

    /**
     * BeanFactory 초기화
     * - 미리 등록된 모든 클래스에 대해 Bean 생성
     * - 생성된 Bean은 beans Map에 캐싱
     */
    private void initialize() {
        for (Class<?> clazz : preInstantiatedClasses) {
            getOrCreateBean(clazz);
        }
    }

    /**
     * 해당 클래스 타입의 Bean을 생성하거나 이미 생성된 Bean을 반환
     * - Singleton 유지
     */
    private Object getOrCreateBean(Class<?> clazz) {
        // 이미 생성된 Bean 반환
        Object bean = beans.get(clazz);
        if (bean != null) {
            return bean;
        }

        // 해당 클래스 타입의 Bean 생성/등록/반환
        Object created = createInstance(clazz);
        beans.put(clazz, created);
        return created;
    }

    /**
     * 클래스 인스턴스 생성
     * - 적절한 생성자를 선택하여
     * - 필요한 의존성을 resolve 후 인스턴스 생성
     */
    private Object createInstance(Class<?> clazz) {
        // 생성자 획득
        Constructor<?> constructor = findConstructor(clazz);

        // 생성자의 파라미터 타입마다 의존성 Bean을 주입
        List<Object> parameters = new ArrayList<>();
        for (Class<?> typeClass : constructor.getParameterTypes()) {
            parameters.add(getOrCreateBean(typeClass));
        }

        // 인스턴스 생성
        try {
            constructor.setAccessible(true);
            return constructor.newInstance(parameters.toArray());
        } catch (Exception e) {
            throw new RuntimeException("Failed to create instance of " + clazz.getName(), e);
        }
    }

    /**
     * 생성자 선택 전략
     * 1. @Inject 붙은 생성자가 있으면 그것을 사용
     * 2. 없으면 기본 생성자 사용
     */
    private Constructor<?> findConstructor(Class<?> clazz) {
        Constructor<?> injected = BeanFactoryUtils.getInjectedConstructor(clazz); // @Inject가 포함된 생성자
        if (injected != null) {
            return injected;
        }

        try {
            return clazz.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("No usable constructor found in class: " + clazz.getName(), e);
        }
    }

    /**
     * 타입 기반 Bean 조회
     */
    public <T> T getBean(Class<T> requiredType) {
        Object bean = beans.get(requiredType);
        if (bean == null) {
            return null;  // 혹은 예외로 바꾸는 것도 가능
        }
        return requiredType.cast(bean);
    }
}
