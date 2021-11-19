package com.grin;

import com.grin.services.ServiceA;
import com.grin.services.ServiceB;
import com.grin.services.impl.ServiceAImpl;
import com.grin.services.impl.ServiceBImpl;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws Exception {
        DIContext context = createContext();
        perform(context);
    }


    private static DIContext createContext() throws Exception {
        Set<Class<?>> serviceClasses = new HashSet<>();

        serviceClasses.add(ServiceAImpl.class);
        serviceClasses.add(ServiceBImpl.class);

        return new DIContext(serviceClasses);
    }

    private static void perform(DIContext context) {
        ServiceA serviceA = context.getServiceInstance(ServiceA.class);
        ServiceB serviceB = context.getServiceInstance(ServiceB.class);

        System.out.println(serviceA.perform());
        System.out.println(serviceB.perform());
    }

}
