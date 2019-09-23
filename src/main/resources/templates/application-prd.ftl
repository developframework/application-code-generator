<#assign profile=database.prd>
spring:
  datasource:
    name: ${profile.mysql.name}
    url: ${profile.mysql.url}
    username: ${profile.mysql.username}
    password: ${profile.mysql.password}
    driverClassName: com.mysql.cj.jdbc.Driver
    hikari:
      poolName: HikariCP
  data:
    mongodb:
      database: ${profile.mongodb.database}
      host: ${profile.mongodb.host}
      port: ${profile.mongodb.port}
      username: ${profile.mongodb.username}
      password: ${profile.mongodb.password}