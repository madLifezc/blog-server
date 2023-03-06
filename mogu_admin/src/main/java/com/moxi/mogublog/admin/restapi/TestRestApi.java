package com.moxi.mogublog.admin.restapi;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Api(value = "测试接口", tags = {"测试接口"})
@Slf4j
public class TestRestApi {

    @GetMapping("/test")
    public String test(String val){
        log.info("val:" + val);
        return val ;
    }


}
