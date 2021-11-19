package com.grin.services;

public class ServiceA {

    private ServiceB serviceB;

    public ServiceA(ServiceB serviceB) {
        this.serviceB = serviceB;
    }

    public String  perform() {
        return "Service A (" + this.serviceB.perform() + ")";
    }

}