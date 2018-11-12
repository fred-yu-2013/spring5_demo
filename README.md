# IDE

* Intellij Idea ULTIMATE 2018.1
* 直接打开本工程目录即可
* [Hello Demo](#hello-demo)
* [Transaction Hello](#transaction-hello)
* [DAO Hello](#dao-hello)
* [JSP Hello](#jsp-hello)
* [JSP Form Tag Hello](#jsp-form-tag-hello)
* [Internationalization Hello](#internationalization-hello)
* [Custom JSP Tag](#custom-jsp-tag)

# Hello Demo

* [https://start.spring.io/](https://start.spring.io/) 生成hello工程包
* 导入到idea里面
* 编译的时候，会引入相关的依赖包，时间有点久
* 添加类HelloController
* 修改运行的端口号为8200
* 运行DemoApplication，并浏览器访问：[http://127.0.0.1:8200/hello](http://127.0.0.1:8200/hello)

## 一些问题

* 几个问题：找不到或无法加载主类（project structure里面配置的工程太多，仅保留demo）

## 设置端口号

resources目录下, 增加application.properties文件，并添加如下项

> server.port=8200

## xml 配置文件

* resources目录下, 添加xml配置文件application-context.xml
* 添加类XmlConfiguration来引入xml配置文件
* xml里面若需要支持配置字段，需要配置路径

> <context:property-placeholder location="classpath:hello.properties"/>

## java doc

Tools/Generate JavaDoc里面，需要设置命令行参数支持中文

> -encoding utf-8 -charset utf-8

## Ali Java代码检测工具

安装插件：

> Alibaba Java Coding Guidelines

# Transaction Hello

* 需要定义dataSource, txManger

> <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close"/\>
> <bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager"/\>

* 定义txAdvice, 及使用AOP触发, 相当于txAdvice通过AOP调用txManager，让事务起作用

> <tx:advice id="txAdvice" transaction-manager="txManager"/\>
> <aop:config\>
>   <aop:pointcut id="fooServiceOperation" expression="execution(* com.fred.spring.transaction.demo.trans.*.*(..))"/\>
>   <aop:advisor advice-ref="txAdvice" pointcut-ref="fooServiceOperation"/\>
> </aop:config\>

* 定义需要执行transaction的bean, 每个接口里面都跑出异常，仅作演示用

> <bean id="fooService" class="com.fred.spring.transaction.demo.trans.DefaultFooServiceImpl"/\>

* 调用方式和普通的bean一样，事务通过AOP起作用

> fooService.insertFoo(new Foo());

## 依赖的包

> <\!-- https://mvnrepository.com/artifact/commons-dbcp/commons-dbcp --\><br>
> <\!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc --\><br>
> <\!-- https://mvnrepository.com/artifact/org.springframework/spring-tx --\><br>
> <\!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver --\><br>

## TransactionTemplate

* xml里面创建transactionTemplate, 此template可以用来执行事务

> <bean id="transactionTemplate" class="org.springframework.transaction.support.TransactionTemplate"\>

* 新建类SimpleFooServiceImpl，注入transactionTemplate

```
    @Autowired<br>
    private TransactionTemplate transactionTemplate;
```

* 通过transactionTemplate执行事务，具体要执行那些数据库操作，还需要DAO层来定

```
    transactionTemplate.execute(new TransactionCallbackWithoutResult() {
        // the code in this method executes in a transactional context
        @Override
        public void doInTransactionWithoutResult(TransactionStatus status) {
            throw new UnsupportedOperationException();
        }
    });
```

# DAO Hello

* 创建Repository JdbcMovieFinderImpl，用如下方式创建jdbcTemplete.

init函数，会将dataSource按参数名字注入

```
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void init(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
```

* 用jdbcTemplete执行数据库查询

```
    int rowCount = this.jdbcTemplate.queryForObject("select count(*) from search_key", Integer.class);
    System.out.println("RESULT: " + rowCount);
```

## DAO with Transaction

* 直接在DefaultFooServiceImpl里面注入JdbcMovieFinderImpl,调用即可

## 事务的整体结构

tx代表事务，事务概念脱离了数据库操作本身

txTemplate用来管理事务，不会管理数据库相关的操作

![Alt text](doc/transaction-arch.jpg?raw=true "Title")

# JSP hello

- 添加pom依赖

```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-tomcat</artifactId>
        <scope>provided</scope>
    </dependency>

    <dependency>
        <groupId>org.apache.tomcat.embed</groupId>
        <artifactId>tomcat-embed-jasper</artifactId>
        <scope>provided</scope>
    </dependency>

    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>jstl</artifactId>
        <version>1.2</version>
    </dependency>
```

- 配置jsp路径，位于application.properties中，并且添加hello.jsp文件

```
    spring.mvc.view.prefix=/jsp/
    spring.mvc.view.suffix=.jsp
```

- 添加controller，HelloJspController
- 注意，controller需要添加到com.fred.spring.transaction下面
可能是：**springboot默认扫描规则是,自动扫描启动器类的同包或者其子包的下的注解**
- 注意：spring-boot-devtools似乎没法让idea做到，修改代码后，App自动重启

# JSP Form Tag Hello

* 具体参见类：JspFormController

# Internationalization Hello

* resources下面添加多语言文件messages*.properties
* XmlConfiguration中添加3个和locale相关的成员函数

```
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
```

* 添加international.jsp,用来引用多语言字段
* 注意：不要引用thymeleaf包，这个是和jsp同一级别的模板库，和JSP兼容，需要另行设置

# Custom JSP Tag

- 需要引入web.xml文件，在webapp下面建WEB-INF目录即可
- 创建Tag的描述文件webapp/WEB-INF/custom.tld
- 创建Tag的处理类及对应的controller

> com.fred.spring.transaction.demo.tag.demo.HelloTag <br/>
> com.fred.spring.transaction.demo.tag.demo.HelloTagController <br/>

- 创建一个引用tag的jsp

> webapp/jsp/customTag.jsp
