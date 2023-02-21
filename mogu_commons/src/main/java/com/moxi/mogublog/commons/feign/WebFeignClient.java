package com.moxi.mogublog.commons.feign;

import com.moxi.mogublog.commons.config.feign.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * mogu_admin相关接口
 *
 * @author MadLife
 * @date 2020年1月21日22:19:10
 */

@FeignClient(name = "mogu-web", configuration = FeignConfiguration.class)
public interface WebFeignClient {

    /**
     * 获取系统配置信息
     */
    @RequestMapping(value = "/oauth/getSystemConfig", method = RequestMethod.GET)
    public String getSystemConfig(@RequestParam("token") String token);

    @RequestMapping("/content/getBlogByUid")
    public String getBlogByUid(@RequestParam(name = "uid", required = false) String uid);

    @RequestMapping("/content/getSameBlogByTagUid")
    public String getSameBlogByTagUid(@RequestParam(name = "tagUid", required = true) String tagUid,
                                      @RequestParam(name = "currentPage", required = false, defaultValue = "1") Long currentPage,
                                      @RequestParam(name = "pageSize", required = false, defaultValue = "10") Long pageSize);

    @RequestMapping("/content/getSameBlogByBlogUid")
    public String getSameBlogByBlogUid(@RequestParam(name = "blogUid", required = true) String blogUid, Long currentPage, @RequestParam(name = "pageSize", required = false, defaultValue = "10") Long pageSize);

    @RequestMapping(value = "/index/getNewBlog")
    public String getNewBlog(@RequestParam(name = "currentPage", required = false, defaultValue = "1") Long currentPage,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "10") Long pageSize);

}