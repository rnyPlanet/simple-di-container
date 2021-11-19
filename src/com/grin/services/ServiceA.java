package com.grin.services;

public class ServiceA {

    public static String  perform() {
        return "Service A (" + ServiceB.perform() + ")";
    }

}