<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <parent>
    <groupId>${groupId}</groupId>
    <artifactId>${artifactId}</artifactId>
    <version>${version}</version>
  </parent>
  <artifactId>${moduleName}</artifactId>
  <dependencies>
  	<!-- web 核心包 -->
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-web</artifactId>
	</dependency>
  	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-aop</artifactId>
	</dependency>
  	<dependency>
		<groupId>tk.mybatis</groupId>
		<artifactId>mapper-spring-boot-starter</artifactId>
	</dependency>
	<dependency>
		<groupId>com.github.pagehelper</groupId>
		<artifactId>pagehelper-spring-boot-starter</artifactId>
	</dependency>
	<!-- encrpty application configuration -->
	<dependency>
        <groupId>com.github.ulisesbocchio</groupId>
        <artifactId>jasypt-spring-boot-starter</artifactId>
        <version>3.0.2</version>
	</dependency>
	<!-- When using @ConfigurationProperties it is recommended to add 'spring-boot-configuration-processor' to your classpath to generate configuration metadata -->
  	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-configuration-processor</artifactId>
		<optional>true</optional>
	</dependency>
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
	</dependency>
	<#list dependents as d>
  	<dependency>
  		<groupId>${d.groupId}</groupId>
  		<artifactId>${d.moduleName}</artifactId>
  		<version>${d.version}</version>
  	</dependency>
  	</#list>
  </dependencies>
</project>