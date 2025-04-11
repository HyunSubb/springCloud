package com.nhnacademy.frontclient.blog.service;

import com.nhnacademy.frontclient.blog.adaptor.GatewayAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GatewayService {
    private final GatewayAdaptor gatewayAdaptor;

    public String fetchFirstServiceData() {
        return gatewayAdaptor.getFirstServiceData();
    }

    public String fetchSecondServiceData() {
        return gatewayAdaptor.getSecondServiceData();
    }

    public String myBlogServiceData(Long id) {
        return gatewayAdaptor.getMyBlogData(id);
    }
}
