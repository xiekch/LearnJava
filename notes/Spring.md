# Spring

Spring is an IOC(DI) and AOP container framework.

IOC(inversion of control)inverse the direction of controlling resources

DI : another formulation of IOC: components accept the resource injections from the container in a predefined way

tradition: component --request--> container --response resource-->component

IOC: container  --push resource--> component

separate interfaces and implements -> factory pattern -> IOC

configuration files -> configuration classes

`@Bean=<bean></bean>`

component registration：

- package scans + component annotations（only self-defined classes）
- @Bean + method
- @Import 
  - @Import
  
  - ImportSelector
  
  - ImportBeanDefinitionRegistrar
- FactoryBean



Bean life cycle

- @Bean init-method and destroy-method
- implements InitializingBean and DisposableBean
- @PostConstruct @PreDestroy



some ways to assemble beans

- xml
- component scan+annotation
- configure class 
- implements interfaces (additional)





properties assignment -> postProcessBeforeInitialization -> init method -> postProcessAfterInitialization



#{} SpEL(Spring Expression Language)

@Autowired: Marks a constructor, field, setter method, or config method as to be autowired by Spring's dependency injection facilities. 

- sometimes @Autowired can be omitted, where in @Bean or only one parametered constructor



xxxAware: pass in underlying components when created

implemented by ApplicationContextAwareProcessor when postProcessBeforeInitialization