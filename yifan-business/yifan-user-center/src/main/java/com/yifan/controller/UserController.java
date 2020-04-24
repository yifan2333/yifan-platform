package com.yifan.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yifan.dto.SysUserDto;
import com.yifan.entity.SysUserEntity;
import com.yifan.repository.SysUserRepository;
import com.yifan.result.ActionResult;
import com.yifan.result.ResultType;

@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private SysUserRepository sysUserRepository;

    @GetMapping("userByUsername")
    public ActionResult<SysUserDto> userByUsername(@RequestParam String userName) {
        ActionResult.Builder<SysUserDto> builder = new ActionResult.Builder<>();
        SysUserEntity entity = sysUserRepository.findByUsername(userName);
        if (entity == null) {
            return builder.resultType(ResultType.BAD_REQUEST).message("未查询到用户").build();
        }
        return builder.data(SysUserDto.getUser(entity)).build();
    }
}
