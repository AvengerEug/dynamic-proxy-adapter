package com.dynamicproxyadapter.proxy;

import com.dynamicproxyadapter.adapter.ThirdPartyAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ThirdPartyProxy implements InvocationHandler {

    private Logger logger = LoggerFactory.getLogger(ThirdPartyProxy.class);

    private ThirdPartyAdapter thirdPartyAdapter;

    public void setThirdPartyAdapter(ThirdPartyAdapter thirdPartyAdapter) {
        this.thirdPartyAdapter = thirdPartyAdapter;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("proxy ================第三方client代理开始==================");
        logger.info("proxy 代理执行的方法: [ " + method.getName() + " ]");
        logger.info("proxy 代理执行的参数: [ " + Arrays.toString(args) + " ]");

        Object object = null;

        try {
            object = method.invoke(thirdPartyAdapter, args);
        } catch (Exception e) {
            logger.error("proxy 代理调用发生异常, 真实异常信息为: ", e.getCause());
            throw e.getCause();
        } finally {
            logger.info("proxy 代理方法执行完成");
        }

        return object;
    }
}
