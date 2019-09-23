spring:
  profiles:
    active: dev
  jackson:
    propertyNamingStrategy: SNAKE_CASE
    timeZone: 'GMT+8'
    dateFormat: 'yyyy-MM-dd HH:mm:ss'
    deserialization:
      ACCEPT_SINGLE_VALUE_AS_ARRAY: true
    serialization:
      WRITE_BIGDECIMAL_AS_PLAIN: true
  jpa:
    openInView: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL5InnoDBDialect
    hibernate:
      ddlAuto: update
      showSql: false