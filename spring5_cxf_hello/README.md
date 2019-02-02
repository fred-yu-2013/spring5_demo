# CXF 3.3.0 with spring 5

Steps to run application:

- Add dependencies

```$xslt
    <dependency>
        <groupId>org.apache.cxf</groupId>
        <artifactId>cxf-spring-boot-starter-jaxws</artifactId>
        <version>3.3.0</version>
    </dependency>
    <dependency>
        <groupId>org.apache.cxf</groupId>
        <artifactId>cxf-rt-transports-http</artifactId>
        <version>3.3.0</version>
    </dependency>
```

- Add interface **HelloService**

```$xslt
import javax.jws.WebService;

@WebService
public interface HelloService {
    String sayHi(String text);
}
```

- Add service server side implementation **HelloServiceImpl**

```$xslt
import javax.jws.WebService;

@WebService(endpointInterface = "com.fred.spring.cxf.demo.HelloService")
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHi(String text) {
        System.out.println("sayHi called");
        return "Hello " + text;
    }
}
```

- Public service on configuration **AppConfiguration**.

```$xslt
@Configuration
public class AppConfiguration {
    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public ServletRegistrationBean newServlet() {
        return new ServletRegistrationBean(new CXFServlet(), "/cxf/*");
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(this.springBus(), new HelloServiceImpl());
        endpoint.publish("/HelloService");
        return endpoint;
    }
}
```

- Run [http://127.0.0.1:8080/cxf](http://127.0.0.1:8080/cxf) to get available service information.