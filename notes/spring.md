Spring is an IOC(DI) and AOP container framework.

IOC(inversion of control)反转资源的控制方向

DI : another  formulation of IOC: 组件以预先定义好的方式接受来自容器的资源注入	

tradition: component --request--> container --response resource-->component

IOC: container  --push resource--> component

separate interfaces and implements -> factory pattern -> IOC

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





properties assignment -> postProcessBeforeInitialization -> init method -> postProcessAfterInitialization