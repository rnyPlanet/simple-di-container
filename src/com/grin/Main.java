package com.grin;

import com.grin.services.ServiceA;
import com.grin.services.ServiceB;

public class Main {

    public static void main(String[] args) {
        ServiceB serviceB = new ServiceB();
        ServiceA serviceA = new ServiceA(serviceB);

        System.out.println(serviceA.perform());
    }
}
