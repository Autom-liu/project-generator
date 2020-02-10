spring:
  profiles:
    include:
    - common-dev

mybatis:
  type-aliases-package: ${beanPackage}
  mapper-locations: classpath:mapper/**.xml,classpath:mapper/ext/**.xml
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl