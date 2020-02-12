Spring is an IOC(DI) and AOP container framework.

配置文件-> 配置类

`@Bean=<bean></bean>`

注册组件：

- 包扫描+组件注解标注（仅限自己写的类）
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