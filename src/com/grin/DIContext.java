package com.grin;

import com.grin.annotations.Inject;
import com.grin.annotations.Service;
import com.grin.utils.ClassPathScanner;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DIContext {

    private Set<Object> serviceInstances = new HashSet<>();

    public static DIContext contextFromPackage(String packageName) throws Exception {
        Set<Class<?>> classesInPackage = ClassPathScanner.getAllClasesInPackage(packageName);
        Set<Class<?>> serviceClasses = new HashSet<>();

        for (Class<?> cls : classesInPackage) {
            if (cls.isAnnotationPresent(Service.class)) {
                serviceClasses.add(cls);
            }
        }

        return new DIContext(serviceClasses);
    }

    public DIContext(Collection<Class<?>> serviceClasses) throws Exception {

        for (Class<?> serviceClass : serviceClasses) {
            Constructor<?> constructor = serviceClass.getConstructor();
            constructor.setAccessible(true);
            this.serviceInstances.add(constructor.newInstance());
        }

        for (Object serviceInstance : this.serviceInstances) {
            for (Field field : serviceInstance.getClass().getDeclaredFields()) {
                if (!field.isAnnotationPresent(Inject.class)) {
                    continue;
                }

                Class<?> fieldType = field.getType();
                field.setAccessible(true);

                for (Object matchPartner : this.serviceInstances) {
                    if (fieldType.isInstance(matchPartner)) {
                        field.set(serviceInstance, matchPartner);
                    }
                }
            }
        }
    }

    public <T> T getServiceInstance(Class<T> serviceClass) {
        for (Object serviceInstance : this.serviceInstances) {
            if (serviceClass.isInstance(serviceInstance)) {
                return (T) serviceInstance;
            }
        }

        return null;
    }

}
