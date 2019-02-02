package com.fred.spring.transaction.demo.trans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 此类用于演示如何使用xml里面定义的bean
 *
 * @author Fred
 */
@RestController
public class FooController {

    /**
     * 来自xml里面的bean
     */
    private final FooService fooService;
    private final FooService fooServiceWithTransactionTemplate;

    @Autowired
    public FooController(FooService fooService, FooService fooServiceWithTransactionTemplate) {
        this.fooService = fooService;
        this.fooServiceWithTransactionTemplate = fooServiceWithTransactionTemplate;
    }

    @RequestMapping("/fooService")
    public String fooService() {
        fooService.insertFoo(new Foo());
        return "fooService";
    }

    @RequestMapping("/fooServiceWithTransactionTemplate")
    public String fooServiceWithTransactionTemplate() {
        fooServiceWithTransactionTemplate.insertFoo(new Foo());
        return "fooService";
    }

    @RequestMapping("/transactionWithDao")
    public String transactionWithDao() {
        fooService.updateFoo(new Foo());
        return "transactionWithDao";
    }

}
