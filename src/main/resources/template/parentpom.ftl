<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>${groupId}</groupId>
  <artifactId>${artifactId}</artifactId>
  <version>${version}</version>
  <packaging>pom</packaging>
  <name>${artifactId}</name>
  <description>${description}</description>
  
  <parent>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-parent</artifactId>
	<version>2.1.4.RELEASE</version>
	<relativePath/>
  </parent>
  
  <properties>
	<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
	<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
	<java.version>1.8</java.version>
	<springboot.version>2.1.4.RELEASE</springboot.version>
	<mapper.starter.version>2.0.3</mapper.starter.version>
	<mysql.version>5.1.32</mysql.version>
	<pageHelper.starter.version>1.2.5</pageHelper.starter.version>
	<fastjson.version>1.2.8</fastjson.version>
	<commonlang.version>3.4</commonlang.version>
	<commonio.version>1.3.2</commonio.version>
	<druid.version>1.1.14</druid.version>
  </properties>
	<dependencyManagement>
		<dependencies>
			<!-- apache-common -->
			<dependency>
				<groupId>org.apache.commons</groupId>
				<artifactId>commons-lang3</artifactId>
				<version>${r'${commonlang.version}'}</version>
			</dependency>
			<dependency>
				<groupId>org.apache.commons</groupId>
				<artifactId>commons-io</artifactId>
				<version>${r'${commonio.version}'}</version>
			</dependency>
			
			<!-- 数据库连接 -->
		  	<dependency>
				<groupId>com.alibaba</groupId>
				<artifactId>druid</artifactId>
				<version>${r'${druid.version}'}</version>
			</dependency>
			<dependency>
				<groupId>com.alibaba</groupId>
				<artifactId>druid-spring-boot-starter</artifactId>
				<version>${r'${druid.version}'}</version>
			</dependency>
			<dependency>
				<groupId>mysql</groupId>
				<artifactId>mysql-connector-java</artifactId>
				<version>${r'${mysql.version}'}</version>
			</dependency>
			<!--mapper -->
			<dependency>
				<groupId>tk.mybatis</groupId>
				<artifactId>mapper-spring-boot-starter</artifactId>
				<version>${r'${mapper.starter.version}'}</version>
			</dependency>
			<dependency>
				<groupId>org.springframework.data</groupId>
				<artifactId>spring-data-commons</artifactId>
				<version>${r'${springboot.version}'}</version>
			</dependency>
		</dependencies>
	</dependencyManagement>
	
	<dependencies>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
		</dependency>
	</dependencies>

	<modules>
		<module>${artifactId}-common</module>
		<module>${artifactId}-common-service</module>
		<#list modules as m>
		<module>${m}</module>
		</#list>
	</modules>
</project>