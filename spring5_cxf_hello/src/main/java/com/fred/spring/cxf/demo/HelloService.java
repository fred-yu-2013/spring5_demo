package com.fred.spring.cxf.demo;

import javax.jws.WebService;

@WebService
public interface HelloService {
    String sayHi(String text);
}
