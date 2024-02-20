package com.moxi.mogublog.gateway.restapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class FallbackHandler {

    @GetMapping("/defaultfallback")
    public Map<String,String> defaultfallback() {
        Map<String,String> map = new HashMap<>();
        map.put("code","5001");
        map.put("msg","服务异常,请稍后重试");
        map.put("data","null");
        return map;
    }

}