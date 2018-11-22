package com.fred.spring.transaction.demo.customnamespace;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class CustomNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("createservice", new CreateServiceNamespaceBeanDefinitionParser());
    }

}
