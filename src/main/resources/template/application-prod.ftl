spring:
  profiles:
    include:
    - common-prod

mybatis:
  type-aliases-package: ${beanPackage}
  mapper-locations: classpath:mapper/**.xml,classpath:mapper/ext/**.xml
