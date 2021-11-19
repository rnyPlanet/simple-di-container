package com.grin.services.impl;

import com.grin.annotations.Inject;
import com.grin.annotations.Service;
import com.grin.services.ServiceA;
import com.grin.services.ServiceB;

@Service
public class ServiceAImpl implements ServiceA {

    @Inject
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