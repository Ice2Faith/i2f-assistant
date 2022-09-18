package com.i2f.assistant;

import i2f.spring.slf4j.Slf4jPrintStream;
import i2f.springboot.asyn.EnableAsyncConfig;
import i2f.springboot.context.EnableSpringContextConfig;
import i2f.springboot.cors.EnableCorsConfig;
import i2f.springboot.exception.EnableExceptionHandlerConfig;
import i2f.springboot.mvc.EnableMvcConfig;
import i2f.springboot.mybatis.EnableMybatisConfig;
import i2f.springboot.property.EnablePropertyDecryptConfig;
import i2f.springboot.redis.EnableRedisConfig;
import i2f.springboot.redis.LettuceRedisHeartbeatConfig;
import i2f.springboot.schedule.EnableScheduleConfig;
import i2f.springboot.secure.EnableSecureConfig;
import i2f.springboot.security.EnableSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableSpringContextConfig
@EnableMvcConfig
@EnableSecureConfig
@EnableSecurityConfig
@EnableRedisConfig
@EnableAsyncConfig
@EnableCorsConfig
@EnableExceptionHandlerConfig
@EnableMybatisConfig
@EnablePropertyDecryptConfig
@EnableScheduleConfig
@Import(LettuceRedisHeartbeatConfig.class)
public class AssistantSvcApplication {

    public static void main(String[] args) {
        Slf4jPrintStream.redirectSysoutSyserr();
        SpringApplication.run(AssistantSvcApplication.class, args);
    }

}
