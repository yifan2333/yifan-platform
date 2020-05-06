package com.yifan.user.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yifan.common.entity.security.SysUserEntity;
import com.yifan.user.service.SysUserService;
import com.yifan.web.result.ActionResult;
import com.yifan.web.result.ResultType;

@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private SysUserService sysUserService;

    @GetMapping("userByUsername")
    public ActionResult<SysUserEntity> userByUsername(@RequestParam String userName) {
        ActionResult.Builder<SysUserEntity> builder = new ActionResult.Builder<>();
        SysUserEntity dto = sysUserService.getSysUser(userName);
        if (dto == null) {
            return builder.resultType(ResultType.BAD_REQUEST).message("未查询到用户").build();
        }
        return builder.data(dto).build();
    }
}
