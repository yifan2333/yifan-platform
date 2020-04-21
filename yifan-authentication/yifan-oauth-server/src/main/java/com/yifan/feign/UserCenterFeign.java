package com.yifan.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yifan.dto.SysUserDto;
import com.yifan.result.ActionResult;

@FeignClient(value = "yifan-user-center")
public interface UserCenterFeign {

    @GetMapping("user/userByUsername")
    ActionResult<SysUserDto> userByUsername(@RequestParam(name = "userName") String userName);

}
