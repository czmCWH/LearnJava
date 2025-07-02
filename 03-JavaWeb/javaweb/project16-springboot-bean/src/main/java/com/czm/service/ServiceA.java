package com.czm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ServiceA {

    @Lazy   // 解决循环依赖的问题，方式2
    @Autowired
    private ServiceB serviceB;

    public void addA() {
        System.out.println("------ addA------");
    }

    public void addB() {
        serviceB.addB();
    }
}
