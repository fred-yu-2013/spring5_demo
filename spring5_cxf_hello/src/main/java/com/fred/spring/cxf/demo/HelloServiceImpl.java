package com.fred.spring.cxf.demo;

import javax.jws.WebService;

@WebService(endpointInterface = "com.fred.spring.cxf.demo.HelloService")
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHi(String text) {
        System.out.println("sayHi called");
        return "Hello " + text;
    }
}
