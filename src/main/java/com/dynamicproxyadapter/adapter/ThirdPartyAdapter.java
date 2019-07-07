package com.dynamicproxyadapter.adapter;

/**
 * 适配器接口, 通过查看第三方client 确认只需要使用同步用户、订单信息的接口
 * 这一步骤, 是手动写的, 因为生成代理类需要面向接口编程
 */
public interface ThirdPartyAdapter {

    void syncUserInfo(Object info);

    void syncOrderInfo(Object info);
}
