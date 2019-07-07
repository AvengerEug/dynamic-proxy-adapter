package com.dynamicproxyadapter.proxy;

import com.dynamicproxyadapter.adapter.ThirdPartyAdapter;
import com.dynamicproxyadapter.adapter.impl.ThirdPartyAdapterImpl;
import com.eugene.common.utils.ConstructDynamicProxy;
import org.springframework.context.annotation.Bean;

public class InitThirdPartyProxy {

    @Bean
    public ThirdPartyAdapter initThirdPartyAdapter() {
        // 被代理对象
        ThirdPartyAdapter thirdPartyAdapter = new ThirdPartyAdapterImpl();

        // 代理对象
        ThirdPartyProxy thirdPartyProxy = new ThirdPartyProxy();
        // 将被代理对象添加至代理对象的被代理对象属性中
        thirdPartyProxy.setThirdPartyAdapter(thirdPartyAdapter);

        return new ConstructDynamicProxy<ThirdPartyAdapter>(thirdPartyAdapter, thirdPartyProxy).getProxy();
    }
}
