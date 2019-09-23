# 自定义模板位置
#templateLocation: 'E:\template'
project:
  # 项目根包
  basePackage: 'qiushui.test'
  port: 8080
  pom:
    groupId: '${groupId}'
    artifactId: '${artifactId}'
    version: '1.0-SNAPSHOT'
    author: 'qiushui'
    description: '${artifactId}'
    dependencies:
      - 'com.github.developframework:kite-spring-boot-starter:0.14-SNAPSHOT'
      - 'com.github.developframework:resource-manager-jpa:0.1-SNAPSHOT'
      - 'com.github.developframework:resource-manager-mongodb:0.1-SNAPSHOT'
      - 'org.springframework.boot:spring-boot-starter-web'
  database:
    dev:
      mysql:
        name: 'devDB'
        url: 'jdbc:mysql://dev.mysql.server:3306/resource_manager_test?useSSL=false'
        username: 'root'
        password: 'pgc%DB112'
      mongodb:
        database: 'hclc_informationize'
        host: 'dev.mongodb.server'
        port: '27017'
        username: 'hclc'
        password: 'hclc7788'
    prd:
      mysql:
        name: 'prdDB'
        url: 'jdbc:mysql://prd.mysql.server:3306/resource_manager_test?useSSL=false'
        username: 'root'
        password: 'pgc%DB112'
      mongodb:
        database: 'hclc_informationize'
        host: 'prd.mongodb.server'
        port: '27017'
        username: 'hclc'
        password: 'hclc7788'

  components:
    docker:
      enable: true
      registry: 'nexus.hclc-tech.com:5000'
    git:
      enable: true
      username: 'qiushui'
      userEmail: 'qiushui@zgpgc.com'
      remote: ''

  resources:
    - name: 'User'
      table: 't_user'
      mode: 'PO'
      description: '用户'
      idType: 'Integer'
      fields:
        - 'String name false 名称'
    - name: 'Member'
      table: 't_member'
      mode: 'DOC'
      description: '会员'
      idType: 'Long'
      fields:
        - 'String name false 名称'
        - 'int age false 年龄'