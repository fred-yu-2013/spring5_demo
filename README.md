# IDE

* Intellij Idea ULTIMATE 2018.1
* 直接打开本工程目录即可

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