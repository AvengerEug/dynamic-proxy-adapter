package com.dynamicproxyadapter.adapter.impl;

import com.dynamicproxyadapter.adapter.ThirdPartyAdapter;
import com.eugene.sumarry.thirdparty.ThirdPartyClient;

public class ThirdPartyAdapterImpl extends ThirdPartyClient implements ThirdPartyAdapter {

    @Override
    public void syncUserInfo(Object info) {
        super.syncUserInfo(info);
    }

    @Override
    public void syncOrderInfo(Object info) {
        super.syncOrderInfo(info);
    }
}
