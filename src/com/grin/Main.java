package com.grin;

import com.grin.services.ServiceA;
import com.grin.services.ServiceB;
import com.grin.services.impl.ServiceAImpl;
import com.grin.services.impl.ServiceBImpl;

public class Main {

    public static void main(String[] args) {
        ServiceB serviceBImpl = new ServiceBImpl();
        ServiceA serviceAImpl = new ServiceAImpl(serviceBImpl);

        System.out.println(serviceAImpl.perform());
    }

}
