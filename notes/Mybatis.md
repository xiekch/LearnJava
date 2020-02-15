# Mybatis

traditional JDBC

- simple functions
- sql in java code, high coupling, hard coded



Hibernate

- ORM(Object Relation Mapping)
- fully automatic framework, to eliminate SQL, auto-generated sql
- difficult to write self-defined SQL



Mybatis

- separate SQL and java code
- semi-automatic framework, sql is defined by programmers



use interfaces instead of generic, program to interfaces

- stronger type checking
- mybatis generates proxy classes 
- separate specification and implementation

origin: Dao -> DaoImpl

mybatis: Mapper -> Mapper.xml



mybatis-config.xml

mapper

- resource: sql mapping files
- class
  - sql mapping files in the same package of the interface classes
  - annotation: not advocated, couples sql and java code