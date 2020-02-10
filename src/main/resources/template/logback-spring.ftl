<?xml version="1.0" encoding="UTF-8"?>
<!-- 日志配置文件每60s扫描一遍，debug=false 表示不输出logback本身的日志信息 -->
<configuration scan="true" scanPeriod="60 seconds" debug="false">
	<!-- 设置全局变量 -->
	<!-- 日志输出级别  TRACE<DEBUG<INFO<WRAN<ERROR -->
	<springProfile name="dev">
		<property name="log.level" value="debug" />
	</springProfile>
	<springProfile name="prod">
		<property name="log.level" value="info" />
	</springProfile>
	<!-- 日志保留时间 -->
	<property name="log.maxHistory" value="30" />
	<!-- 日志存储位置 -->
	<springProfile name="dev">
		<property name="log.filePath" value="${devLogPath}"/>
	</springProfile>
	<springProfile name="prod">
		<property name="log.filePath" value="${prodLogPath}"/>
	</springProfile>
	<!-- 日志记录格式： 时间 + 线程 + 级别 + 日志信息（类名方法名：+ 信息） -->
	<property name="log.pattern" value="%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n"/>
	
	<!-- 控制台输出 -->
	<appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
		<!-- encoder作用类似于layout 完成event事件转换成字符串功能，encoder还能将转换后的数据输出到文件中 -->
		<encoder>
			<pattern>${r'${log.pattern}'}</pattern>
		</encoder>
	</appender>
	<!-- deubug日志，滚动输出，按天或按文件大小输出 -->
	<appender name="DEBUG" class="ch.qos.logback.core.rolling.RollingFileAppender">
		<file>${r'${log.filePath}'}/debug.log</file>
		<!-- 滚动策略 按时间滚动-->
		<rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
			<!-- 日志文件路径名称 .gz自动压缩 -->
			<fileNamePattern>${r'${log.filePath}'}/debug/debug.%d{yyyy-MM-dd}.log.gz</fileNamePattern>
			<maxHistory>${r'${log.maxHistory}'}</maxHistory>
		</rollingPolicy>
		<encoder>
			<pattern>${r'${log.pattern}'}</pattern>
		</encoder>
		<!-- 日志级别过滤，只保留DEBUG信息，其他的DENY -->
		<filter class="ch.qos.logback.classic.filter.LevelFilter">
			<level>DEBUG</level>
			<onMatch>ACCEPT</onMatch>
			<onMismatch>DENY</onMismatch>
		</filter>
	</appender>
	
	<appender name="INFO" class="ch.qos.logback.core.rolling.RollingFileAppender">
		<file>${r'${log.filePath}'}/info.log</file>
		<rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
			<fileNamePattern>${r'${log.filePath}'}/info/info.%d{yyyy-MM-dd}.log.gz</fileNamePattern>
			<maxHistory>${r'${log.maxHistory}'}</maxHistory>
		</rollingPolicy>
		<encoder>
			<pattern>${r'${log.pattern}'}</pattern>
		</encoder>
		<filter class="ch.qos.logback.classic.filter.LevelFilter">
			<level>INFO</level>
			<onMatch>ACCEPT</onMatch>
			<onMismatch>DENY</onMismatch>
		</filter>
	</appender>
	
	<appender name="ERROR" class="ch.qos.logback.core.rolling.RollingFileAppender">
		<file>${r'${log.filePath}'}/error.log</file>
		<rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
			<fileNamePattern>${r'${log.filePath}'}/error/error.%d{yyyy-MM-dd}.log.gz</fileNamePattern>
			<maxHistory>${r'${log.maxHistory}'}</maxHistory>
		</rollingPolicy>
		<encoder>
			<pattern>${r'${log.pattern}'}</pattern>
		</encoder>
		<filter class="ch.qos.logback.classic.filter.LevelFilter">
			<level>ERROR</level>
			<onMatch>ACCEPT</onMatch>
			<onMismatch>DENY</onMismatch>
		</filter>
	</appender>
	
	<appender name="WARN" class="ch.qos.logback.core.rolling.RollingFileAppender">
		<file>${r'${log.filePath}'}/warn.log</file>
		<rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
			<fileNamePattern>${r'${log.filePath}'}/warn/warn.%d{yyyy-MM-dd}.log.gz</fileNamePattern>
			<maxHistory>${r'${log.maxHistory}'}</maxHistory>
		</rollingPolicy>
		<encoder>
			<pattern>${r'${log.pattern}'}</pattern>
		</encoder>
		<filter class="ch.qos.logback.classic.filter.LevelFilter">
			<level>WARN</level>
			<onMatch>ACCEPT</onMatch>
			<onMismatch>DENY</onMismatch>
		</filter>
	</appender>
	
	<!-- 哪些内容的信息需要输出，logger和appender进行绑定，additivity会把根日志配置也放到这里面来 -->
	<logger name="com.scnu.zwebapp.baseinfo" level="debug" additivity="true">
		<appender-ref ref="DEBUG" />
		<appender-ref ref="INFO" />
		<appender-ref ref="ERROR" />
	</logger>
	
	<!-- 哪些内容的信息需要输出，logger和appender进行绑定，additivity会把根日志配置也放到这里面来 -->
	<logger name="com.scnu.zwebapp.baseinfo" level="info" additivity="true">
		<appender-ref ref="INFO" />
		<appender-ref ref="ERROR" />
	</logger>
	
	<!-- 根日志信息 -->
	<springProfile name="dev">
		<!-- 根日志信息 -->
		<root level="debug">
			<appender-ref ref="STDOUT" />
		</root>
	</springProfile>

	<springProfile name="prod">
		<!-- 根日志信息 -->
		<root level="info">
			<appender-ref ref="INFO" />
			<appender-ref ref="WARN" />
			<appender-ref ref="ERROR" />
		</root>
	</springProfile>
	
</configuration>
