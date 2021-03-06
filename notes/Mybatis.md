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



parameters

when multiple parameters are passed in, mybatis would encapsulate them as a map. 

key: param1...paramN or 0...N

value: the parameters

@Param("name") to designate the names of parameters

if the parameters belong to a data model, a POJO is suggested.

if not, a TO(transfer object) is recommended.

or a map is passed in.



`select * from user_table where id = ${id} and password=#{password}`

-> `select * from user_table where id=Jim and password=?`

#{} is like PreparedStatement, which is recommended

{} is like Statement, which can be used like `select * from ${table} where ...`



jdbc type for null

default: OTHER

but Oracle can't recognize it

- #{password, jdbcType=NULL}
- set jdbcTypeForNull=NULL in the configuration file 