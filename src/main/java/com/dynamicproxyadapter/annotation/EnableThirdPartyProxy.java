package com.dynamicproxyadapter.annotation;

import com.dynamicproxyadapter.proxy.InitThirdPartyProxy;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 将该注解添加至springboot入口类中, 加入到spring容器
 */
@Target(ElementType.TYPE)
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Import(value = InitThirdPartyProxy.class)
public @interface EnableThirdPartyProxy {
}
