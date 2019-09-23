<configuration xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			   xsi:noNamespaceSchemaLocation="http://www.padual.com/java/logback.xsd"
			   scan="false" debug="false">

	<appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
		<encoder>
			<pattern>[ %d{yyyy-MM-dd HH:mm:ss.SSS} %-5level] - %logger{35} - %msg%n</pattern>
		</encoder>
	</appender>

	<root level="INFO">
		<appender-ref ref="STDOUT"/>
	</root>
</configuration>