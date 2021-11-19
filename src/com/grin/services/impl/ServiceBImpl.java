package com.grin.services.impl;

import com.grin.annotations.Inject;
import com.grin.services.ServiceA;
import com.grin.services.ServiceB;

public class ServiceBImpl implements ServiceB {

    @Inject
    private ServiceA serviceA;

    @Override
    public ServiceA getServiceA() {
        return serviceA;
    }

    @Override
    public void setServiceA(ServiceA serviceA) {
        this.serviceA = serviceA;
    }

    @Override
    public String  perform() {
        return "Service B";
    }

}
