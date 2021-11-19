package com.grin.services.impl;

import com.grin.services.ServiceA;
import com.grin.services.ServiceB;

public class ServiceAImpl implements ServiceA {

    private ServiceB serviceB;

    @Override
    public ServiceB getServiceB() {
        return serviceB;
    }

    @Override
    public void setServiceB(ServiceB serviceB) {
        this.serviceB = serviceB;
    }

    @Override
    public String perform() {
        return "Service A (" + this.serviceB.perform() + ")";
    }

}