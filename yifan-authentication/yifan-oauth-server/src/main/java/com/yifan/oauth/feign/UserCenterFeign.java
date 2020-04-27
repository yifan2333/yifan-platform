package com.yifan.oauth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yifan.common.entity.security.SysUserEntity;
import com.yifan.web.result.ActionResult;

@FeignClient(value = "yifan-user-center")
public interface UserCenterFeign {

    @GetMapping("user/userByUsername")
    ActionResult<SysUserEntity> userByUsername(@RequestParam(name = "userName") String userName);

}
