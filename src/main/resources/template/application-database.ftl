datasource:
  ip: ENC(${encryDataSourceIp})
 
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://${r'${datasource.ip}'}?characterEncoding=utf-8&useSSL=false
    username: ${userId}
    password: ENC(${encryPassword})
    type: com.zaxxer.hikari.HikariDataSource
    hikari:
      minimum-idle: 5                           # 池中维护的最小空闲连接数
      connection-timeout: 30000                 # 数据库连接超时时间,默认即30000s
      maximum-pool-size: 10                     # 连接池最大连接数，默认是10
      idle-timeout: 60000                       # 空闲连接存活最大时间，默认600000s
      max-lifetime: 1800000                     # 池中连接的最长生命周期，值0表示无限生命周期，默认1800000s

jasypt:
  encryptor:
    algorithm: PBEWithMD5AndDES
    iv-generator-classname: org.jasypt.iv.NoIvGenerator
    password: ${encryKey}
