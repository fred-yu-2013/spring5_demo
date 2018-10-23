package com.fred.spring.transaction.demo.xmlconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 添加此类用于，在spring项目里面引入xml配置文件
 *
 * @author Fred
 */
@Configuration
@ImportResource({"classpath*:application-context.xml"})
public class XmlConfiguration {
}
