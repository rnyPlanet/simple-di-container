package com.grin.services.impl;

import com.grin.services.ServiceA;
import com.grin.services.ServiceB;

public class ServiceAImpl implements ServiceA {

    private ServiceB serviceB;

    public ServiceAImpl(ServiceB serviceB) {
        this.serviceB = serviceB;
    }

    public String  perform() {
        return "Service A (" + this.serviceB.perform() + ")";
    }

}