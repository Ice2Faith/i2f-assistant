# 从0构建SpringBoot+Vue单体系统
---
## 构建环境
- Jdk8
- Node6 webpack 环境
- vue-cli 脚手架
- IDEA 集成开发环境
- Maven3 项目管理工具
- npm 项目管理工具

## 构建项目
- 项目总览
```
- i2f-assistant 项目根
    - assistant-svc 后端SVC
    - assistant-web 前端WEB
```
- i2f-assistant 项目根创建
    - IDEA
    - File
    - New
    - Project
    - Maven
    - {i2f-assistant}
    - Jdk8
    - Finish
- assistant-svc 后端服务创建
    - IDEA
    - 右键{i2f-assistant}
    - New
    - Module
    - SpringBoot
    - {assistant-svc}
    - Finish
- assistant-web 前端创建
    - cd {i2f-assistant}
    - vue init webpack {assistant-web}
    - use router
    - use eslint
    - use npm
    - cd {assistant-web}
    - npm install
## 配置工程
### 配置i2f-assistant父工程
- 指定父工程的父
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.3.7.RELEASE</version>
</parent>
```
- 修改项目坐标
```xml
<groupId>i2f.assistant</groupId>
<artifactId>i2f-assistant</artifactId>
<version>1.0</version>
<packaging>pom</packaging>
```
- 定义maven参数
```xml
<properties>
    <java.version>1.8</java.version>
    <spring-boot.version>2.3.7.RELEASE</spring-boot.version>
    <spring-cloud.version>Hoxton.SR12</spring-cloud.version>
    <alibaba-cloud.version>0.2.2.RELEASE</alibaba-cloud.version>
</properties>
```
- 添加子模块
```xml
<modules>
    <module>assistant-svc</module>
</modules>
```
- 管理依赖
```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.16</version>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>${spring-boot.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>${spring-cloud.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-alibaba-dependencies</artifactId>
            <version>${alibaba-cloud.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>

        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.2.2</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.26</version>
        </dependency>

        <!--redis 连接池 -->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId>
            <version>2.7.0</version>
        </dependency>

        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper-spring-boot-starter</artifactId>
            <version>1.3.0</version>
        </dependency>
    </dependencies>
</dependencyManagement>
```
### 配置assistant-svc后端工程
- 变更父，指向父工程
```xml
<parent>
    <groupId>i2f.assistant</groupId>
    <artifactId>i2f-assistant</artifactId>
    <version>1.0</version>
</parent>
```
- 指定三坐标
```xml
<groupId>i2f.assistant</groupId>
<artifactId>assistant-svc</artifactId>
<version>1.0</version>
<packaging>jar</packaging>
```
- 添加所需依赖
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
    <!--redis 连接池 -->
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-pool2</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
    </dependency>
    <dependency>
        <groupId>com.github.pagehelper</groupId>
        <artifactId>pagehelper-spring-boot-starter</artifactId>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>

    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```
- 添加配置文件{application.yml}
```yml
spring:
  application:
    name: assistant-svc
  profiles:
    active: dev
```
- 添加配置文件{application-dev.yml}
```yml
server:
  port: 8082
  tomcat:
    max-swallow-size: -1
  compression:
    enabled: true
    min-response-size: 1024
    mime-types: application/javascript,application/json,application/xml,text/html,text/xml,text/plain,text/css,image/*

spring:
  servlet:
    multipart:
      max-file-size: 5200MB
      max-request-size: 5200MB
  #json 时间戳统一转换
  jackson:
    date-format:   yyyy-MM-dd HH:mm:ss
    time-zone:   GMT+8
    locale: zh_CN
  aop:
    proxy-target-class: true
  # 设置静态文件路径，js,css等
  mvc:
    static-path-pattern: /res/**
  resource:
    static-locations: classpath:/static/,classpath:/public/
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/assistant_db?characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
    username: root
    password: 123456
    driver-class-name: com.mysql.cj.jdbc.Driver
  #redis 配置
  redis:
    database: 0
    host: 127.0.0.1
    password: 123456
    port: 6379
    lettuce:
      pool:
        max-active: 8   #最大连接数据库连接数,设 0 为没有限制
        max-idle: 8     #最大等待连接中的数量,设 0 为没有限制
        max-wait: -1ms  #最大建立连接等待时间。如果超过此时间将接到异常。设为-1表示无限制。
        min-idle: 0     #最小等待连接中的数量,设 0 为没有限制
      shutdown-timeout: 100ms
  security:
    user:
      name: root
      password: 123456
      roles:
        - root

mybatis:
  mapper-locations:
    - classpath*:mapper/**/*Mapper.xml
    - classpath*:i2f/assistant/svc/**/xml/*Mapper.xml
  configuration:
    log-impl: org.apache.ibatis.logging.slf4j.Slf4jImpl
    call-setters-on-nulls: true

#Mybatis输出sql日志
logging:
  level:
    root: info
    i2f.assistant.svc: info
    i2f.assistant.svc.**.mapper: debug
```
### 配置assistant-web前端工程
#### 修改打包脚本，支持多环境配置
- 添加跨平台环境变量支持依赖
```bash
npm i --save-dev cross-env
```
- 修改package.json > scripts
- 删除此行，原来的默认打包方式
```json
"build": "node build/build.js",
```
- 添加以下三行，添加不同环境的打包方式配置
- 注意这里的 NODE_ENV ENV_CONFIG 就是自定义的打包参数
```json
"build:dev": "cross-env NODE_ENV=development ENV_CONFIG=dev node build/build.js",
"build:test": "cross-env NODE_ENV=testing ENV_CONFIG=test node build/build.js",
"build:prod": "cross-env NODE_ENV=production ENV_CONFIG=prod node build/build.js"
```
- 修改config 添加dev/test/prod环境配置
    - NODE_ENV 对应NODE标准的环境
    - ENV_CONFIG 对应自定义的环境名称
    - BASE_URL 对应此环境下的后端服务
    - 特别注意，此处的字符串值，需要用'""'包含
- config/dev.env.js
```js
'use strict'
module.exports = {
  NODE_ENV: '"development"',
  ENV_CONFIG: '"dev"',
  BASE_URL: '"/api"'
}
```
- config/test.env.js
```js
'use strict'
module.exports = {
  NODE_ENV: '"testing"',
  ENV_CONFIG: '"test"',
  BASE_URL: '"http://127.0.0.1:8080/"'
}
```
- config/prod.env.js
```js
'use strict'
module.exports = {
  NODE_ENV: '"production"',
  ENV_CONFIG: '"prod"',
  BASE_URL: '"http://127.0.0.1:8080/"'
}
```
- 修改config/index.js > module.exports > build 添加环境变量
```json
devEnv: require('./dev.env'),
testEnv: require('./test.env'),
prodEnv: require('./prod.env'),
```
- 修改config/index.js > module.exports > dev 添加代理配置
- 上面在dev环境，配置了/api来访问后端，也就是反向代理的做法，这里对应配置代理
```json
proxyTable: {
  '/api': {
    target: 'http://127.0.0.1/8082/',
    changeOrigin: true,
    pathRewrite: {
      '^/api': ''
    }
  }
},
```
- 修改config/index.js > module.exports > build 修改资源路径
```json
assetsPublicPath: './',
```
- 修改build/webpack.prod.conf.js 修改环境配置为动态载入
- 这里就是拿到上面环境变量中的某一个来作为环境配置
```js
// const env = require('../config/prod.env')
const env=config.build[process.env.ENV_CONFIG+'Env']
```
- 修改build/build.js 删除固定的环境配置行
```js
// process.env.NODE_ENV = 'production'
```
- 修改build/build.js 修改为动态的环境配置
```js
// const spinner = ora('building for production...')
const spinner = ora('building for '+process.env.NODE_ENV+' of '+process.env.ENV_CONFIG+' ...')
spinner.start()
```
- 修改build/utils.js > exports.assetsPath 添加其他环境的配置识别
```js
const assetsSubDirectory = process.env.NODE_ENV === 'production'
    || process.env.NODE_ENV === 'development'
    || process.env.NODE_ENV === 'testing'
    ? config.build.assetsSubDirectory
    : config.dev.assetsSubDirectory
```
- 同样修改build/webpack.base.config.js > module.exports > output 添加其他环境的配置识别
```js
publicPath: process.env.NODE_ENV === 'production'
    || process.env.NODE_ENV === 'development'
    || process.env.NODE_ENV === 'testing'
      ? config.build.assetsPublicPath
      : config.dev.assetsPublicPath
```
- 同样修改build/vue-loader.conf.js 添加其他环境的配置识别
```js
const isProduction = process.env.NODE_ENV === 'production'
  || process.env.NODE_ENV === 'development'
  || process.env.NODE_ENV === 'testing'
```

- 最后，为了方便项目中使用，将环境配置挂载到Vue原型上
- 修改src/main.js 添加环境的挂载
```js
Vue.prototype.$env = process.env
```
- 同时，如果有使用axios或其他地方需要使用环境信息，都可以在原型上获取变量
```html
{{$env}}
```
- 项目的运行与打包
```shell
npm run dev
npm run build:dev
npm run build:test
npm run build:prod
```
