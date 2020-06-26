# JUnit Testing

JUnit is an open source unit testing framework for Java. It is useful for Java developers to write and run repeatable tests for small chunks of code.

More in depth documentation on Spring and JUnit is available from the [Spring Testing Documentation](https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/testing.html).

When using JUnit in Spring, there are several features added that many developers are not aware of.

First, if you are including the Spring Context in your tests, it becomes an Integration Test, no longer a Unit Test.

To integrate Spring with JUnit, you need spring-test.jar
Specifying dependencies in pom.xml.

```xml
<!--Spring boot starter web-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
    <exclusions>
        <exclusion>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <scope>test</scope>
</dependency>
```

# Creating a Unit Test Class:

In order for the unit tests to run a batch job, the framework must load the job’s ApplicationContext. Two annotations are used to trigger this behavior:

**@RunWith(SpringRunner.class)**: Indicates that the class should use Spring’s JUnit facilities

**@ContextConfiguration(…?)**: In general this indicates which resources to configure the ApplicationContext with. 

In this application, we use **@Autoconfigure** with **@WebMvcTest** (imported from *org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest*), which will disable full auto-configuration and instead apply only configuration relevant to MVC tests. 

Annotations relevant to MVC tests include: @Controller @ControllerAdvice, @JsonComponent, @Converter/@GenericConverter, @Service or @Repository beans, @Filter, @WebMvcConfigurer and @HandlerMethodArgumentResolver beans but not @Component.


By default, tests annotated with @WebMvcTest will also auto-configure Spring Security and MockMvc (including support for HtmlUnit WebClient and Selenium WebDriver). For more fine-grained control of MockMVC the @AutoConfigureMockMvc annotation can be used.

Typically @WebMvcTest is used in combination with @MockBean or @Import to create any collaborators required by your @Controller beans.

An example from *src/test/java/org/theenergymashuplab/cts/controller/payloads/EiCreateTenderTypeTest.java*:

```java
@RunWith(SpringRunner.class)
@WebAppConfiguration
@WebMvcTest(EiCreateTenderType.class)
public class EiCreateTenderTypeTest {
/*@Autowired
private EiTenderType tenderDao;*/
@Autowired
private MockMvc mvc;

@MockBean
private EiCreateTenderType eCTT;

@Test
public void home() throws Exception {
    //EiCreateTenderType eCTT = new EiCreateTenderType();
    mvc.perform(get("http://localhost:8080/tenders/"))
            .andExpect(status().isOk());
}

@Test
public void add() throws Exception {
    EiTenderModel bks = new EiTenderModel();
    TenderID tid = new TenderID();
    bks.setTenderID(tid.value());
    bks.setDuration(90000);
    bks.setExpireTime(null);
    bks.setQuantity(10);

    Calendar c1 = Calendar.getInstance();
    Date dateOne = c1.getTime();
    Instant inst = dateOne.toInstant();
    
    bks.setStartTime(inst);
    bks.setTransactiveState(TransactiveStateType.TRANSACTION);

    Map<String, String> map = new HashMap<>();

    map.put("tenderID", "12334");
    map.put("emixBase", "434fsdfssdq2mn3123mnxcvxc");
    map.put("transactionID", "4234234");
    mvc.perform(post("http://localhost:8080/tenders/add"))
            .andExpect(status().isOk());
            /*.andExpect(status().isOk());
            .contentType(APPLICATION_JSON);*/
}
```

The above code structure is a Text fixture.
A test fixture is a context where a Test Case runs. Typically, test fixtures include:
- Objects or resources that are available for any test case.
- Activities that make these objects/resources available.
    - These activities are:
        1. allocation (setup)
        2. de-allocation (teardown)

If you are looking to load your full application configuration, you should consider @SpringBootTest

An example from */src/test/java/com/eml/energy/ EnergyApplicationTests.java*:

```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class EnergyApplicationTests {

    @Autowired
    private EiCreateTenderType ctt;
```
