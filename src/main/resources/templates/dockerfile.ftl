FROM nexus.hclc-tech.com:5000/openjdk:11-jre
USER root
ARG PORT
RUN mkdir -p /app/logs
COPY target/${pom.artifactId}.jar /app
EXPOSE ${r"${PORT}"}
ENTRYPOINT ["java", "--add-opens", "java.base/java.lang=ALL-UNNAMED", "-jar", "/app/${pom.artifactId}.jar"]