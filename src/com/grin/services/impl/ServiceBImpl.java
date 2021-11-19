package com.grin.services.impl;

import com.grin.services.ServiceA;
import com.grin.services.ServiceB;

public class ServiceBImpl implements ServiceB {

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
