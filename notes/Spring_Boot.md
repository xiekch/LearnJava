# Spring Boot

## What is Spring Boot?

- to simplify the development of Spring 
- a full integration of Spring framework
- a one-stop solution for J2EE



## Microservice

- the microservice architectural style is an approach to developing a single application as a suite of small services, each running in its own process and communicating with lightweight mechanisms, often an HTTP resource API.

- in contrast with All In One 
- built around business capabilities and independently deployable



With Spring Boot, we can develop a microservice application quickly.



## Dependencies

`spring-boot-dependencies-2.2.3.RELEASE.pom`

spring boot version management center

we don't have to write version in spring boot



## Starters

starters are a set of convenient dependency descriptors

`spring-boot-starter` imports the modules we need

`spring-boot-starter-web` imports `spring-boot-starter`



## Main Configuration Class

@SpringBootApplication 

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = {
        @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
        @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication
```

@Configuration is also a @Component

@AutoConfigurationPackage.Register.class registers all the components in the package and subpackages of  @SpringBootApplication 



## Auto Configuration

`**AutoConfiguration`s in `org.springframework.boot.autoconfigure` help us configure

the integrated solution of J2EE and autoconfig: `spring-boot-autoconfigure-2.2.3.RELEASE.jar` 

