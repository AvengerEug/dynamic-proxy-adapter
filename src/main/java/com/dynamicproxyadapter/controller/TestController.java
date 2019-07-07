package com.dynamicproxyadapter.controller;

import com.dynamicproxyadapter.adapter.ThirdPartyAdapter;
import com.eugene.common.controller.BaseController;
import com.eugene.common.web.http.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/test")
public class TestController extends BaseController {

    @Autowired
    private ThirdPartyAdapter thirdPartyProxy;

    @GetMapping(value = "/sync-user")
    public Message syncUser() {
        thirdPartyProxy.syncUserInfo(new Object());
        return Message.ok();
    }

    @GetMapping(value = "/sync-order")
    public Message syncOrder() {
        thirdPartyProxy.syncOrderInfo(new Object());
        return Message.ok();
    }
}
