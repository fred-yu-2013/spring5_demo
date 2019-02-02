package com.fred.spring.transaction.demo.xmlconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 此类用于演示如何使用xml里面定义的bean
 *
 * @author Fred
 */
@RestController
public class XmlConfigController {

    /**
     * 来自xml里面的bean
     */
    @Autowired
    private String xmlstr1;

    @RequestMapping("/xmlconfig")
    public String hello() {
        String val = "s";
        return xmlstr1;
    }

}
