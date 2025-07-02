package com.czm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceB {


    @Autowired
    private ServiceA serviceA;

    public void addA() {
        serviceA.addA();
    }

    public void addB() {
        System.out.println("------ addB------");
    }
}
