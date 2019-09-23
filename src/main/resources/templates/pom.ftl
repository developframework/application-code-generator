<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>${pom.groupId}</groupId>
    <artifactId>${pom.artifactId}</artifactId>
    <version>${pom.version}</version>

    <description>${pom.description}</description>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.3.RELEASE</version>
        <relativePath/>
    </parent>

    <#if components.docker.enable>
    <properties>
        <!-- docker镜像名的前缀 指定库地址 -->
        <docker.image.prefix>${components.docker.registry}</docker.image.prefix>

        <!-- docker暴露应用端口 -->
        <docker.export.port>${port}</docker.export.port>
    </properties>
    </#if>

    <dependencies>
    <#list pom.getDependenciesValue() as dependency>
        <dependency>
            <groupId>${dependency.firstValue}</groupId>
            <artifactId>${dependency.secondValue}</artifactId>
            <#if dependency.thirdValue?exists>
            <version>${dependency.thirdValue}</version>
            </#if>
        </dependency>
    </#list>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.0</version>
                <configuration>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                            <version>1.18.8</version>
                        </path>
                        <path>
                            <groupId>org.mapstruct</groupId>
                            <artifactId>mapstruct-processor</artifactId>
                            <version>1.2.0.Final</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
        </plugins>
        <#if components.docker.enable>
        <pluginManagement>
            <plugins>
                <plugin>
                    <groupId>com.spotify</groupId>
                    <artifactId>dockerfile-maven-plugin</artifactId>
                    <version>1.4.0</version>
                    <dependencies>
                        <dependency>
                            <groupId>javax.activation</groupId>
                            <artifactId>activation</artifactId>
                            <version>1.1.1</version>
                        </dependency>
                    </dependencies>
                    <executions>
                        <execution>
                            <id>default</id>
                            <goals>
                                <goal>build</goal>
                                <goal>push</goal>
                            </goals>
                        </execution>
                    </executions>
                    <configuration>
                        <!-- 使用Maven settings.xml内的私库账户配置 -->
                        <useMavenSettingsForAuth>true</useMavenSettingsForAuth>

                        <!-- 指定 镜像的名称 -->
                        <repository>${r'${docker.image.prefix}'}/${r'${project.artifactId}'}</repository>

                        <!-- 指定 镜像的tag -->
                        <tag>${r'${project.version}'}</tag>

                        <!-- Dockerfile内的参数 -->
                        <buildArgs>
                            <!-- 暴露的端口 -->
                            <PORT>${r'${docker.export.port}'}</PORT>
                        </buildArgs>
                    </configuration>
                </plugin>
            </plugins>
        </pluginManagement>
        </#if>
    </build>

    <#if components.docker.enable>
    <profiles>
        <profile>
            <id>docker</id>
            <build>
                <plugins>
                    <plugin>
                        <groupId>com.spotify</groupId>
                        <artifactId>dockerfile-maven-plugin</artifactId>
                    </plugin>
                </plugins>
            </build>
        </profile>
    </profiles>
    </#if>
</project>