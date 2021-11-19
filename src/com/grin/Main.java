package com.grin;

import com.grin.services.ServiceA;
import com.grin.services.impl.ServiceAImpl;
import com.grin.services.impl.ServiceBImpl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Set<Class<?>> serviceClasses = new HashSet<>();
        serviceClasses.add(ServiceAImpl.class);
        serviceClasses.add(ServiceBImpl.class);

        ServiceA serviceA = createServiceA(serviceClasses);

        System.out.println(serviceA.perform());
    }

    private static ServiceA createServiceA(Set<Class<?>> serviceClasses) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Set<Object> serviceInstances = new HashSet<>();
        for (Class<?> serviceClass : serviceClasses) {
            Constructor<?> constructor = serviceClass.getConstructor();
            constructor.setAccessible(true);
            serviceInstances.add(constructor.newInstance());
        }

        for (Object serviceInstance : serviceInstances) {
            for (Field field : serviceInstance.getClass().getDeclaredFields()) {
                Class<?> fieldType = field.getType();
                field.setAccessible(true);

                for (Object matchPartner : serviceInstances) {
                    if (fieldType.isInstance(matchPartner)) {
                        field.set(serviceInstance, matchPartner);
                    }
                }
            }
        }

        for (Object serviceInstance : serviceInstances) {
            if (serviceInstance instanceof ServiceA) {
                return (ServiceA) serviceInstance;
            }
        }

        return null;
    }

}
