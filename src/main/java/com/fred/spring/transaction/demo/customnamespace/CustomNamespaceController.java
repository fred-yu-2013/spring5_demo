package com.fred.spring.transaction.demo.customnamespace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController用于演示Hello工程，需要手动添加该类
 *
 * @author Fred
 */
@RestController
public class CustomNamespaceController {

    @Autowired
    @Qualifier("myservice-123")
    MyService myService;

    @RequestMapping("/customnamespace")
    public String hello() {
        return "Hello customnamespace!" + myService.getDefaultDao().getFields().get(0);
    }

}
